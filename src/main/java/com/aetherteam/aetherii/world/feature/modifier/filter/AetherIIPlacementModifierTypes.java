package com.aetherteam.aetherii.world.feature.modifier.filter;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class AetherIIPlacementModifierTypes {
    public static final MapCodec<StructureBlacklistFilter> STRUCTURE_BLACKLIST_FILTER = register("structure_blacklist_filter", StructureBlacklistFilter.CODEC);
    public static final MapCodec<ElevationFilter> ELEVATION_FILTER = register("elevation_filter", ElevationFilter.CODEC);
    public static final MapCodec<ImprovedLayerPlacementModifier> IMPROVED_LAYER_PLACEMENT = register("improved_layer_placement", ImprovedLayerPlacementModifier.CODEC);
    public static final MapCodec<LakePlacementModifier> LAKE_PLACEMENT = register("lake_placement", LakePlacementModifier.CODEC);

    private static <P extends PlacementModifier> MapCodec<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), codec);
    }

    public static void init() {}
}
