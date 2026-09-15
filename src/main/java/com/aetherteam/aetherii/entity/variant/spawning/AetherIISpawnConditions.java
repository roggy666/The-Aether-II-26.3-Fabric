package com.aetherteam.aetherii.entity.variant.spawning;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.variant.SpawnCondition;

public class AetherIISpawnConditions {
    private static <T extends SpawnCondition> MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.SPAWN_CONDITION_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), codec);
    }

    public static final MapCodec<LightCheck> LIGHT = register("light", LightCheck.MAP_CODEC);
    public static final MapCodec<RandomCheck> RANDOM = register("random", RandomCheck.MAP_CODEC);

    public static void init() {}
}

