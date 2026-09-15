package com.aetherteam.aetherii.client.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIFluids;
import com.aetherteam.aetherii.block.fluid.AlkahestFluid;
import com.aetherteam.aetherii.client.AetherIIClient;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.material.Fluids;

public class AetherIIParticleFactories {
    /**
     * @see AetherIIClient#onInitializeClient() 
     */
    public static void registerParticleFactories() {
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.AETHER_PORTAL, AetherPortalParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.SKYROOT_LEAVES, AetherLeafParticle.SkyrootFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.SKYPLANE_LEAVES, AetherLeafParticle.SkyplaneFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.SKYBIRCH_LEAVES, AetherLeafParticle.SkybirchFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.SKYPINE_LEAVES, AetherLeafParticle.SkypineFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.WISPROOT_LEAVES, AetherLeafParticle.WisprootFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.WISPTOP_LEAVES, AetherLeafParticle.WisptopFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.GREATROOT_LEAVES, AetherLeafParticle.GreatrootFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.GREATOAK_LEAVES, AetherLeafParticle.GreatoakFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.GREATBOA_LEAVES, AetherLeafParticle.GreatboaFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.AMBEROOT_LEAVES, AetherLeafParticle.AmberootFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.IRRADIATED_LEAVES, AetherLeafParticle.AmberootFactory::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.DRIPPING_WATER, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> new DripParticle.DripHangParticle(level, x, y, z, Fluids.WATER, AetherIIParticleTypes.FALLING_WATER, spriteSet.get(random)) {
            @Override
            public SingleQuadParticle.Layer getLayer() {
                return Layer.TRANSLUCENT;
            }
        });
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.FALLING_WATER, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> new DripParticle.FallAndLandParticle(level, x, y, z, Fluids.WATER, AetherIIParticleTypes.SPLASH, spriteSet.get(random)) {
            @Override
            public SingleQuadParticle.Layer getLayer() {
                return Layer.TRANSLUCENT;
            }
        });
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.SPLASH, spriteSet -> (particle, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            SplashParticle splashParticle = new SplashParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet.get(random)) {
                @Override
                public SingleQuadParticle.Layer getLayer() {
                    return Layer.TRANSLUCENT;
                }
            };
            return splashParticle;
        });
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.GLASS_FEATHERS, GlassFeathersParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.AMBROSIUM, AmbrosiumParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.ALKAHEST, AlkahestParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.HESTVEIL, HestveilParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.DRIPPING_ALKAHEST, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            DripParticle particle = new DripParticle.DripHangParticle(level, x, y, z, AetherIIFluids.ALKAHEST, AetherIIParticleTypes.FALLING_ALKAHEST, spriteSet.get(random));
            particle.setColor(0.65F, 0.9F, 0.6F);
            return particle;
        });
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.FALLING_ALKAHEST, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            DripParticle particle = new DripParticle.FallAndLandParticle(level, x, y, z, AetherIIFluids.ALKAHEST, ParticleTypes.WHITE_SMOKE, spriteSet.get(random));
            particle.setColor(0.65F, 0.9F, 0.6F);
            return particle;
        });
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.DRIPPING_DRIPSTONE_ALKAHEST, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            DripParticle particle = new DripParticle.DripHangParticle(level, x, y, z, AetherIIFluids.ALKAHEST, AetherIIParticleTypes.FALLING_DRIPSTONE_ALKAHEST, spriteSet.get(random));
            particle.setColor(0.65F, 0.9F, 0.6F);
            return particle;
        });
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.FALLING_DRIPSTONE_ALKAHEST, spriteSet -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            DripParticle particle = new DripParticle.DripstoneFallAndLandParticle(level, x, y, z, AetherIIFluids.ALKAHEST, ParticleTypes.WHITE_SMOKE, spriteSet.get(random)) {
                @Override
                protected void postMoveUpdate() {
                    if (this.onGround) {
                        BlockPos pos = BlockPos.containing(this.x, this.y, this.z).below();
                        if (this.level.getBlockState(pos).isSolid() && !this.level.getBlockState(pos).is(AetherIITags.Blocks.ALKAHEST_RESISTANT)) {
                            AlkahestFluid.progressivelyDestroyBlock(this.level, pos, 3, true);
                        }
                    }
                    super.postMoveUpdate();
                }
            };
            particle.setColor(0.65F, 0.9F, 0.6F);
            return particle;
        });
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.GRAVITY_DUST, GravityDustParticle.Provider::new);

        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.RAIN, spriteSet -> (particle, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            WaterDropParticle rainParticle = new WaterDropParticle(level, x, y, z, spriteSet.get(random)) {
                @Override
                public SingleQuadParticle.Layer getLayer() {
                    return Layer.TRANSLUCENT;
                }
            };
//            rainParticle.pickSprite(spriteSet);
            return rainParticle;
        });
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.IRRADIATION, IrradiationParticle.Factory::new);

        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.SLASH_DAMAGE, DamageTypeParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.IMPACT_DAMAGE, DamageTypeParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.PIERCE_DAMAGE, DamageTypeParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.SWEEP_ATTACK, BlueAttackSweepParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.SHOCK_ATTACK, YellowAttackShockParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.STAB_ATTACK, RedAttackStabParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.EFFECT_BUILDUP, SpellParticle.MobEffectProvider::new);

        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.ZEPHYR_SNOWFLAKE, SnowflakeParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.TEMPEST_SMOKE, TempestSmokeParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.MOA_HUNGRY, HeartParticle.Provider::new);

        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.LOCKED_BLOCK, CopyBlockParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.BOSS_DOORWAY_BLOCK, CopyBlockParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(AetherIIParticleTypes.TREASURE_DOORWAY_BLOCK, CopyBlockParticle.Provider::new);
    }
}
