package com.aetherteam.aetherii.loot.conditions;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class AetherIILootConditions {
    private static <T extends LootItemCondition> MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), codec);
    }

    public static final MapCodec<PlayerGrownCondition> PLAYER_GROWN = register("player_grown", PlayerGrownCondition.CODEC);
    public static final MapCodec<TierCompare> TIER_COMPARE = register("tier_compare", TierCompare.CODEC);

    public static void init() {}
}

