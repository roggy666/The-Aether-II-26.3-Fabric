package com.aetherteam.aetherii.world.structure.processor;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;

public class AetherIIStructureProcessorTypes {
    public static final MapCodec<ShayelinnMossProcessor> SHAYELINN_MOSS = register("shayelinn_moss", ShayelinnMossProcessor.CODEC);
    public static final MapCodec<RemoveInAirProcessor> REMOVE_IN_AIR = register("remove_in_air", RemoveInAirProcessor.CODEC);
    public static final MapCodec<DensityFunctionProcessor> DENSITY_FUNCTION = register("density_function", DensityFunctionProcessor.CODEC);
    public static final MapCodec<CopyRuleProcessor> COPY_RULE = register("copy_rule", CopyRuleProcessor.CODEC);
    public static final MapCodec<BossRoomProcessor> BOSS_ROOM = register("boss_room", BossRoomProcessor.CODEC);
    public static final MapCodec<MimicContainerProcessor> MIMIC_CONTAINER = register("mimic_container", MimicContainerProcessor.CODEC);

    private static <P extends StructureProcessor> MapCodec<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, Identifier.fromNamespaceAndPath(AetherII.MODID, name), codec);
    }

    public static void init() {}
}