package com.aetherteam.aetherii.effect.harmful;

import com.aetherteam.aetherii.effect.AetherIIMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class AmbrosiumPoisoningEffect extends MobEffect {
    public AmbrosiumPoisoningEffect() {
        super(MobEffectCategory.HARMFUL, 0xE7D87A);
    }

    public static boolean preventHealing(LivingEntity entity, float amount) {
        return !entity.hasEffect(AetherIIMobEffects.AMBROSIUM_POISONING);
    }
}
