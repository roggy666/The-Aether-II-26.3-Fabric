package com.aetherteam.aetherii.effect.harmful;

import com.aetherteam.aetherii.data.resources.registries.AetherIIDamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.InstantaneousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

public class ElectricShockEffect extends InstantaneousMobEffect {
    public ElectricShockEffect() {
        super(MobEffectCategory.HARMFUL, 0xBED1E8);
    }

    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int amplifier) {
        livingEntity.hurtServer(serverLevel, livingEntity.damageSources().magic(), 6 << amplifier);
        return true;
    }

    public void applyInstantaneousEffect(ServerLevel serverLevel, @Nullable Entity source, @Nullable Entity trueSource, LivingEntity livingEntity, int amplifier, double distance) {
        int damageValue = (int) (distance * (6 << amplifier) + 0.5);
        if (source == null) {
            livingEntity.hurtServer(serverLevel, AetherIIDamageTypes.damageSource(livingEntity.level(), AetherIIDamageTypes.SHOCK), damageValue);
        } else {
            livingEntity.hurtServer(serverLevel, AetherIIDamageTypes.indirectEntityDamageSource(livingEntity.level(), AetherIIDamageTypes.SHOCK, source, trueSource), damageValue);
        }
    }
}
