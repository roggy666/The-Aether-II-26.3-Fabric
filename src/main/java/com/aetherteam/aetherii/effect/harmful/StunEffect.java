package com.aetherteam.aetherii.effect.harmful;

import org.jetbrains.annotations.Nullable;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import com.aetherteam.aetherii.event.AetherIIEvents;
import com.aetherteam.aetherii.attachment.AetherIIDataAttachments;
import com.aetherteam.aetherii.attachment.living.EffectsSystemAttachment;
import com.aetherteam.aetherii.effect.AetherIIMobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class StunEffect extends MobEffect {
    public StunEffect() {
        super(MobEffectCategory.HARMFUL, 0xFBFFC2);
    }

    public static void onEntityPostTick(Entity entity) {
        if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(AetherIIMobEffects.STUN)) {
            EffectsSystemAttachment attachment = livingEntity.getAttachedOrCreate(AetherIIDataAttachments.EFFECTS_SYSTEM);
            attachment.setMotionMultiplier(attachment.getMotionMultiplier().multiply(new Vec3(0.4, 1.0, 0.4)));
        }
    }

    public static InteractionResult disableAttacks(Player player, Level level, InteractionHand hand, Entity target, @Nullable EntityHitResult hitResult) {
        return player.hasEffect(AetherIIMobEffects.STUN) ? InteractionResult.FAIL : InteractionResult.PASS;
    }

    public static void disableDamage(LivingEntity entity, AetherIIEvents.DamageContainer container) {
        DamageSource damageSource = container.getSource();
        if (damageSource.isDirect() && damageSource.getDirectEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect(AetherIIMobEffects.STUN)) {
                container.setCanceled(true);
            }
        }
    }

    /**
     * Cancels every player interaction while stunned (NeoForge's PlayerInteractEvent family, registered on the Fabric
     * use/attack callbacks in {@link com.aetherteam.aetherii.effect.AetherIIMobEffects#registerUniqueBehaviors()}).
     */
    public static boolean isStunned(Player player) {
        return player.hasEffect(AetherIIMobEffects.STUN);
    }
}
