package com.aetherteam.aetherii.advancement.predicate;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.Codec;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class AetherIIEntitySubPredicates {
    private static <T extends EntitySubPredicate> Codec<T> register(String name, Codec<T> codec) {
        return Registry.register(BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), codec);
    }

    public static final Codec<OnGroundPredicate> ON_GROUND = register("on_ground", OnGroundPredicate.CODEC);
    public static final Codec<AlivePredicate> ALIVE = register("alive", AlivePredicate.CODEC);
    public static final Codec<ArmorSetPredicate> ARMOR_SET = register("armor_set", ArmorSetPredicate.CODEC);
    public static final Codec<EffectBuildupPredicate> EFFECT_BUILDUP = register("effect_buildup", EffectBuildupPredicate.CODEC);
    public static final Codec<KirridPredicate> KIRRID = register("kirrid", KirridPredicate.CODEC);
    public static final Codec<SheepuffPredicate> SHEEPUFF = register("sheepuff", SheepuffPredicate.CODEC);

    public static void init() {}
}

