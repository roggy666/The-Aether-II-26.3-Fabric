package com.aetherteam.aetherii.loot.functions;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public class AetherIILootFunctions {
    private static <T extends LootItemFunction> MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), codec);
    }

    public static final MapCodec<SpawnSkyrootLizard> SPAWN_SKYROOT_LIZARD = register("spawn_skyroot_lizard", SpawnSkyrootLizard.CODEC);
    public static final MapCodec<GelDropsFunction> GEL_DROPS = register("gel_drops", GelDropsFunction.CODEC);
    public static final MapCodec<SugarDropsFunction> SUGAR_DROPS = register("sugar_drops", SugarDropsFunction.CODEC);

    public static void init() {}
}