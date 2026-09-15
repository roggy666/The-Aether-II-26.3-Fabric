package com.aetherteam.aetherii.world.structure.pool;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;

public class AetherIIPoolElementTypes {
    public static final StructurePoolElementType<AetherPoolElement> AETHER = register("aether_pool_element", AetherPoolElement.CODEC);

    private static <P extends StructurePoolElement> StructurePoolElementType<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.STRUCTURE_POOL_ELEMENT, Identifier.fromNamespaceAndPath(AetherII.MODID, name), () -> codec);
    }

    public static void init() {}
}