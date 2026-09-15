package com.aetherteam.aetherii.data.generators;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.loot.modifiers.DoubleDropsModifier;
import com.aetherteam.nitrogen.loot.modifiers.IGlobalLootModifier;
import com.aetherteam.nitrogen.loot.modifiers.NitrogenLootModifiers;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Writes Nitrogen's global loot modifiers ({@code data/aether_ii/loot_modifiers/<name>.json} plus the
 * {@code global_loot_modifiers.json} index), in the same layout NeoForge's {@code GlobalLootModifierProvider} used.
 */
public class AetherIILootModifierData implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> provider;
    private final Map<String, IGlobalLootModifier> modifiers = new LinkedHashMap<>();

    public AetherIILootModifierData(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        this.output = output;
        this.provider = provider;
    }

    protected void start() {
        this.add("double_drops", new DoubleDropsModifier(new LootItemCondition[]{}, 0));
    }

    protected void add(String name, IGlobalLootModifier modifier) {
        this.modifiers.put(name, modifier);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return this.provider.thenCompose(registries -> {
            this.start();
            RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, registries);
            Codec<IGlobalLootModifier> codec = NitrogenLootModifiers.GLOBAL_LOOT_MODIFIERS.byNameCodec().dispatch(IGlobalLootModifier::codec, Function.identity());
            Path folder = this.output.getOutputFolder(PackOutput.Target.DATA_PACK).resolve(AetherII.MODID).resolve("loot_modifiers");
            List<CompletableFuture<?>> futures = new ArrayList<>();
            JsonArray entries = new JsonArray();
            this.modifiers.forEach((name, modifier) -> {
                Identifier id = Identifier.fromNamespaceAndPath(AetherII.MODID, name);
                entries.add(id.toString());
                futures.add(DataProvider.saveStable(cache, codec.encodeStart(ops, modifier).getOrThrow(), folder.resolve(name + ".json")));
            });
            JsonObject index = new JsonObject();
            index.addProperty("replace", false);
            index.add("entries", entries);
            futures.add(DataProvider.saveStable(cache, index, folder.resolve("global_loot_modifiers.json")));
            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return "Global Loot Modifiers : " + AetherII.MODID;
    }
}
