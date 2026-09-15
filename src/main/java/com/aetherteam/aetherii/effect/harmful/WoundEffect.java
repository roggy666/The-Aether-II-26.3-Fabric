package com.aetherteam.aetherii.effect.harmful;

import com.aetherteam.aetherii.data.resources.registries.AetherIIDamageTypes;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.InstantaneousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;
import java.util.Map;

public class WoundEffect extends InstantaneousMobEffect {
    private static final Map<EntityType<?>, Float> DAMAGE_AMOUNT = new ImmutableMap.Builder<EntityType<?>, Float>()
            .put(EntityTypes.PLAYER, 4.0F)
            .build();

    public WoundEffect() {
        super(MobEffectCategory.HARMFUL, 0xC82B28);
    }

    @Override
    public void applyInstantaneousEffect(ServerLevel serverLevel, @Nullable Entity source, @Nullable Entity trueSource, LivingEntity livingEntity, int amplifier, double distance) {
        float damageValue = DAMAGE_AMOUNT.getOrDefault(livingEntity.getType(), 5.0F);
        if (source == null) {
            livingEntity.hurtServer(serverLevel, AetherIIDamageTypes.damageSource(livingEntity.level(), AetherIIDamageTypes.WOUND), damageValue);
        } else {
            livingEntity.hurtServer(serverLevel, AetherIIDamageTypes.indirectEntityDamageSource(livingEntity.level(), AetherIIDamageTypes.WOUND, source, trueSource), damageValue);
        }
    }
}
