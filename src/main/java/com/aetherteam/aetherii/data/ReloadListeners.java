package com.aetherteam.aetherii.data;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.block.FreezingBlock;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class ReloadListeners {
    public static final Identifier RECIPE_CACHING = Identifier.fromNamespaceAndPath(AetherII.MODID, "recipe_caching");

    public static void registerReloadListeners() {
        ResourceLoader.get(PackType.SERVER_DATA).registerReloadListener(RECIPE_CACHING, new RecipeReloadListener());
    }

    public static class RecipeReloadListener extends SimpleJsonResourceReloadListener<JsonElement> {
        public RecipeReloadListener() {
            super(ExtraCodecs.JSON, FileToIdConverter.json("recipes"));
        }

        /**
         * Resets the block caches for {@link FreezingBlock} recipes.
         */
        @Override
        protected void apply(Map<Identifier, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) {
            FreezingBlock.cachedBlocks.clear();
            FreezingBlock.cachedResults.clear();
        }
    }
}
