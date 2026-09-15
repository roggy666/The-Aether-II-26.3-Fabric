package com.aetherteam.aetherii.entity.ai.brain.memory;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.entity.passive.Kirrid;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.List;
import java.util.Optional;

public class AetherIIMemoryModuleTypes {
    private static <T> MemoryModuleType<T> register(String name, Optional<Codec<T>> codec) {
        return Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), new MemoryModuleType<>(codec));
    }

    public static final MemoryModuleType<Integer> EAT_GRASS_COOLDOWN = register("eat_grass_cooldown", Optional.of(Codec.INT));
    public static final MemoryModuleType<Kirrid> KIRRID_BATTLE_TARGET = register("kirrid_battle_target", Optional.empty());
    public static final MemoryModuleType<BlockPos> TAEGORE_SEARCH_TARGET = register("taegore_search_target", Optional.empty());
    public static final MemoryModuleType<Boolean> TAEGORE_DIGGING = register("taegore_digging", Optional.empty());
    public static final MemoryModuleType<Unit> TAEGORE_SEARCH_COOLDOWN = register("taegore_search_cooldown", Optional.of(Unit.CODEC));
    public static final MemoryModuleType<List<GlobalPos>> TAEGORE_EXPLORED_POSITIONS = register("taegore_explored_positions", Optional.of(Codec.list(GlobalPos.CODEC)));

    public static void init() {}
}

