package com.aetherteam.aetherii.client;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.blockentity.MuralBlockEntity;
import com.aetherteam.aetherii.blockentity.MuralSection;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.crafting.RecipeMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AetherIIClientCaches {
    public static final Map<MuralBlockEntity.MuralData, List<BlockStateModelPart>> CACHED_MURAL_BLOCK_PARTS = new ConcurrentHashMap<>();
    public static final Map<MuralSection, List<BakedQuad>> CACHED_MURAL_ITEM_PARTS = new ConcurrentHashMap<>();
    public static RecipeMap CLIENT_CACHES = RecipeMap.EMPTY;

    public static void registerReloadListeners() {
        ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(Identifier.fromNamespaceAndPath(AetherII.MODID, "mural_cache"),
                (ResourceManagerReloadListener) manager -> clearMuralCaches());
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            clearMuralCaches();
            CLIENT_CACHES = RecipeMap.EMPTY;
        });
    }

    public static void clearMuralCaches() {
        CACHED_MURAL_BLOCK_PARTS.clear();
        CACHED_MURAL_ITEM_PARTS.clear();
    }
}
