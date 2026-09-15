package com.aetherteam.aetherii.api.datamap;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.network.packet.clientbound.DataMapSyncPacket;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.reloader.SimpleReloadListener;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StrictJsonParser;

import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads every registered {@link DataMapType} from {@code data/<namespace>/data_maps/<registry>/<name>.json}.
 * File format (same as NeoForge): {@code {"replace": false, "values": {"ns:id": <value>, "#ns:tag": <value>}, "remove": ["ns:id"]}}.
 * Files from all enabled packs are merged in pack order.
 */
public final class DataMapManager extends SimpleReloadListener<Map<DataMapType<?, ?>, List<JsonObject>>> {
    public static final Identifier ID = Identifier.fromNamespaceAndPath(AetherII.MODID, "data_maps");
    private static final List<DataMapType<?, ?>> TYPES = new ArrayList<>();
    private static boolean registered = false;

    private DataMapManager() {
    }

    public static void register(DataMapType<?, ?> type) {
        TYPES.add(type);
    }

    public static List<DataMapType<?, ?>> types() {
        return TYPES;
    }

    public static void init() {
        if (registered) {
            return;
        }
        registered = true;
        ResourceLoader.get(PackType.SERVER_DATA).registerReloadListener(ID, new DataMapManager());
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> sync(handler.getPlayer()));
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resources, success) -> {
            if (success) {
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                    sync(player);
                }
            }
        });
    }

    private static void sync(ServerPlayer player) {
        if (ServerPlayNetworking.canSend(player, DataMapSyncPacket.TYPE)) {
            ServerPlayNetworking.send(player, DataMapSyncPacket.create());
        }
    }

    public static Identifier fileLocation(DataMapType<?, ?> type) {
        Identifier registry = type.registryKey().identifier();
        String folder = registry.getNamespace().equals(Identifier.DEFAULT_NAMESPACE) ? registry.getPath() : registry.getNamespace() + "/" + registry.getPath();
        return Identifier.fromNamespaceAndPath(type.id().getNamespace(), "data_maps/" + folder + "/" + type.id().getPath() + ".json");
    }

    @Override
    protected Map<DataMapType<?, ?>, List<JsonObject>> prepare(PreparableReloadListener.SharedState state) {
        ResourceManager manager = state.resourceManager();
        Map<DataMapType<?, ?>, List<JsonObject>> result = new LinkedHashMap<>();
        for (DataMapType<?, ?> type : TYPES) {
            Identifier location = fileLocation(type);
            List<JsonObject> files = new ArrayList<>();
            for (Resource resource : manager.getResourceStack(location)) {
                try (Reader reader = resource.openAsReader()) {
                    JsonElement element = StrictJsonParser.parse(reader);
                    if (element.isJsonObject()) {
                        files.add(element.getAsJsonObject());
                    } else {
                        AetherII.LOGGER.error("Data map file '{}' from pack '{}' is not a JSON object", location, resource.sourcePackId());
                    }
                } catch (Exception e) {
                    AetherII.LOGGER.error("Couldn't parse data map file '{}' from pack '{}'", location, resource.sourcePackId(), e);
                }
            }
            result.put(type, files);
        }
        return result;
    }

    @Override
    protected void apply(Map<DataMapType<?, ?>, List<JsonObject>> prepared, PreparableReloadListener.SharedState state) {
        HolderLookup.Provider registries = state.get(ResourceLoader.REGISTRY_LOOKUP_KEY);
        RegistryOps<JsonElement> ops = registries != null ? RegistryOps.create(JsonOps.INSTANCE, registries) : null;
        prepared.forEach((type, files) -> applyType(type, files, ops));
    }

    private static <R, T> void applyType(DataMapType<R, T> type, List<JsonObject> files, RegistryOps<JsonElement> ops) {
        Map<ResourceKey<R>, T> values = new LinkedHashMap<>();
        Map<TagKey<R>, T> tagValues = new LinkedHashMap<>();
        for (JsonObject file : files) {
            if (file.has("replace") && file.get("replace").getAsBoolean()) {
                values.clear();
                tagValues.clear();
            }
            if (file.has("remove")) {
                for (JsonElement removed : file.getAsJsonArray("remove")) {
                    String key = removed.getAsString();
                    if (key.startsWith("#")) {
                        tagValues.remove(TagKey.create(type.registryKey(), Identifier.parse(key.substring(1))));
                    } else {
                        values.remove(ResourceKey.create(type.registryKey(), Identifier.parse(key)));
                    }
                }
            }
            if (!file.has("values")) {
                continue;
            }
            for (Map.Entry<String, JsonElement> entry : file.getAsJsonObject("values").entrySet()) {
                String key = entry.getKey();
                JsonElement raw = entry.getValue();
                // NeoForge allows {"replace": true, "value": ...} wrappers for per-entry replacement
                if (raw.isJsonObject() && raw.getAsJsonObject().has("value") && raw.getAsJsonObject().size() <= 2) {
                    raw = raw.getAsJsonObject().get("value");
                }
                DataResult<T> parsed = ops != null ? type.codec().parse(ops, raw) : type.codec().parse(JsonOps.INSTANCE, raw);
                if (parsed.isError()) {
                    AetherII.LOGGER.error("Failed to decode data map '{}' entry '{}': {}", type.id(), key, parsed.error().orElseThrow().message());
                    continue;
                }
                T value = parsed.getOrThrow();
                if (key.startsWith("#")) {
                    tagValues.put(TagKey.create(type.registryKey(), Identifier.parse(key.substring(1))), value);
                } else {
                    values.put(ResourceKey.create(type.registryKey(), Identifier.parse(key)), value);
                }
            }
        }
        type.set(values, tagValues);
    }
}
