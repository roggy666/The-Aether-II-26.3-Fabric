package com.aetherteam.aetherii.item.consumeeffect;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.consume_effects.ConsumeEffect;

public class AetherIIConsumeEffectTypes {
    private static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(String name, ConsumeEffect.Type<T> type) {
        return Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), type);
    }

    public static final ConsumeEffect.Type<ReduceStatusEffectConsumeEffect> REDUCE_EFFECT_BUILDUP = register("reduce_effect_buildup", new ConsumeEffect.Type<>(ReduceStatusEffectConsumeEffect.CODEC, ReduceStatusEffectConsumeEffect.STREAM_CODEC));

    public static void init() {}
}

