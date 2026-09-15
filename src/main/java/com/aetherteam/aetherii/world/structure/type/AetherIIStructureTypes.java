package com.aetherteam.aetherii.world.structure.type;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class AetherIIStructureTypes {
    public static final StructureType<AetherJigsawStructure> AETHER_JIGSAW = register("aether_jigsaw", AetherJigsawStructure.CODEC);
    public static final StructureType<SentryRuinsStructure> SENTRY_RUINS = register("sentry_ruins", SentryRuinsStructure.CODEC);

    private static <P extends Structure> StructureType<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), () -> codec);
    }

    public static void init() {}
}