package com.aetherteam.aetherii.world.feature.modifier.predicate;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicateType;

public class AetherIIBlockPredicateTypes {
    public static final BlockPredicateType<ScanPredicate> SCAN = register("scan", ScanPredicate.CODEC);
    public static final BlockPredicateType<SearchPredicate> SEARCH = register("search", SearchPredicate.CODEC);
    public static final BlockPredicateType<MossyPredicate> MOSSY = register("mossy", MossyPredicate.CODEC);

    private static <P extends BlockPredicate> BlockPredicateType<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.BLOCK_PREDICATE_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), () -> codec);
    }

    public static void init() {}
}
