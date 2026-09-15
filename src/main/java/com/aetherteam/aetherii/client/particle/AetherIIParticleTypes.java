package com.aetherteam.aetherii.client.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.client.particle.options.AttackShockParticleOption;
import com.aetherteam.aetherii.client.particle.options.AttackStabParticleOption;
import com.aetherteam.aetherii.client.particle.options.GravityDustParticleOption;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.function.Function;

public class AetherIIParticleTypes {
    public static void init() {}

    public static final SimpleParticleType AETHER_PORTAL = register("aether_portal", false);
    public static final SimpleParticleType SKYROOT_LEAVES = register("skyroot_leaves", false);
    public static final SimpleParticleType SKYPLANE_LEAVES = register("skyplane_leaves", false);
    public static final SimpleParticleType SKYBIRCH_LEAVES = register("skybirch_leaves", false);
    public static final SimpleParticleType SKYPINE_LEAVES = register("skypine_leaves", false);
    public static final SimpleParticleType WISPROOT_LEAVES = register("wisproot_leaves", false);
    public static final SimpleParticleType WISPTOP_LEAVES = register("wisptop_leaves", false);
    public static final SimpleParticleType GREATROOT_LEAVES = register("greatroot_leaves", false);
    public static final SimpleParticleType GREATOAK_LEAVES = register("greatoak_leaves", false);
    public static final SimpleParticleType GREATBOA_LEAVES = register("greatboa_leaves", false);
    public static final SimpleParticleType AMBEROOT_LEAVES = register("amberoot_leaves", false);
    public static final SimpleParticleType IRRADIATED_LEAVES = register("irradiated_leaves", false);
    public static final SimpleParticleType DRIPPING_WATER = register("dripping_water", false);
    public static final SimpleParticleType FALLING_WATER = register("falling_water", false);
    public static final SimpleParticleType SPLASH = register("splash", false);
    public static final SimpleParticleType AMBROSIUM = register("ambrosium_torch", false);
    public static final SimpleParticleType GLASS_FEATHERS = register("glass_feathers", false);
    public static final SimpleParticleType ALKAHEST = register("alkahest", false);
    public static final SimpleParticleType HESTVEIL = register("hestveil", false);
    public static final SimpleParticleType DRIPPING_ALKAHEST = register("dripping_alkahest", false);
    public static final SimpleParticleType FALLING_ALKAHEST = register("falling_alkahest", false);
    public static final SimpleParticleType DRIPPING_DRIPSTONE_ALKAHEST = register("dripping_dripstone_alkahest", false);
    public static final SimpleParticleType FALLING_DRIPSTONE_ALKAHEST = register("falling_dripstone_alkahest", false);
    public static final ParticleType<GravityDustParticleOption> GRAVITY_DUST = register("gravity_dust", false, p -> GravityDustParticleOption.CODEC, p -> GravityDustParticleOption.STREAM_CODEC);

    public static final SimpleParticleType RAIN = register("rain", false);
    public static final SimpleParticleType IRRADIATION = register("irradiation", false);

    public static final SimpleParticleType ZEPHYR_SNOWFLAKE = register("zephyr_snowflake", false);
    public static final SimpleParticleType TEMPEST_ELECTRICITY = register("tempest_electricity.json", false);
    public static final SimpleParticleType SLASH_DAMAGE = register("slash_damage", false);
    public static final SimpleParticleType IMPACT_DAMAGE = register("impact_damage", false);
    public static final SimpleParticleType PIERCE_DAMAGE = register("pierce_damage", false);
    public static final SimpleParticleType SWEEP_ATTACK = register("sweep_attack", false);
    public static final ParticleType<AttackShockParticleOption> SHOCK_ATTACK = register("shock_attack", false, p -> AttackShockParticleOption.CODEC, p -> AttackShockParticleOption.STREAM_CODEC);
    public static final ParticleType<AttackStabParticleOption> STAB_ATTACK = register("stab_attack", false, p -> AttackStabParticleOption.CODEC, p -> AttackStabParticleOption.STREAM_CODEC);
    public static final ParticleType<ColorParticleOption> EFFECT_BUILDUP = register("effect_buildup", false, ColorParticleOption::codec, ColorParticleOption::streamCodec);

    public static final SimpleParticleType TEMPEST_SMOKE = register("tempest_smoke", false);
    public static final SimpleParticleType MOA_HUNGRY = register("moa_hungry", false);

    public static final SimpleParticleType LOCKED_BLOCK = register("locked_block", true);
    public static final SimpleParticleType BOSS_DOORWAY_BLOCK = register("boss_doorway_block", true);
    public static final SimpleParticleType TREASURE_DOORWAY_BLOCK = register("treasure_doorway_block", true);

    private static SimpleParticleType register(String name, boolean overrideLimiter) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), FabricParticleTypes.simple(overrideLimiter));
    }

    private static <T extends ParticleOptions> ParticleType<T> register(String name, boolean overrideLimiter, final Function<ParticleType<T>, MapCodec<T>> codecGetter, final Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> streamCodecGetter) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), new ParticleType<T>(overrideLimiter) {
            @Override
            public MapCodec<T> codec() {
                return codecGetter.apply(this);
            }

            @Override
            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return streamCodecGetter.apply(this);
            }
        });
    }
}