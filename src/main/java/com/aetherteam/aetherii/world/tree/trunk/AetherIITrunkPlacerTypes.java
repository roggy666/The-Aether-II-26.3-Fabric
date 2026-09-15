package com.aetherteam.aetherii.world.tree.trunk;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class AetherIITrunkPlacerTypes {
    public static final TrunkPlacerType<MultiTreeTrunkPlacer> MULTI_TREE_TRUNK_PLACER = register("multi_tree_trunk_placer", MultiTreeTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), new TrunkPlacerType<>(codec));
    }

    public static void init() {}
}
