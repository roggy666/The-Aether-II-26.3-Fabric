package com.aetherteam.aetherii.effect.beneficial;

import com.aetherteam.aetherii.attachment.AetherIIDataAttachments;
import com.aetherteam.aetherii.attachment.living.EffectsSystemAttachment;
import com.aetherteam.aetherii.effect.AetherIIMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class NaturalCamouflageEffect extends MobEffect {
    public NaturalCamouflageEffect() {
        super(MobEffectCategory.NEUTRAL, 0x81A76D);
    }

    public static void onEntityPostTick(Entity entity) {
        if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(AetherIIMobEffects.NATURAL_CAMOUFLAGE)) {
            EffectsSystemAttachment attachment = livingEntity.getAttachedOrCreate(AetherIIDataAttachments.EFFECTS_SYSTEM);
            attachment.setMotionMultiplier(attachment.getMotionMultiplier().multiply(new Vec3(0.5, 1.0, 0.5)));
        }
    }

    public static double adjustVisibilityModifier(LivingEntity entity, Entity lookingEntity, double visibility) {
        if (entity.hasEffect(AetherIIMobEffects.NATURAL_CAMOUFLAGE)) {
            return visibility * 0.5F;
        }
        return visibility;
    }
}
