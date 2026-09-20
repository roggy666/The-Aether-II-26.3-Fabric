package com.aetherteam.aetherii.mixin.mixins.common;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.tags.TagKey;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.llamalad7.mixinextras.sugar.Share;
import com.aetherteam.aetherii.event.AetherIIEvents;
import com.aetherteam.aetherii.item.StopUsingItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import com.aetherteam.aetherii.block.EntityFrictionBlock;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.advancement.trigger.AetherIIAdvancementTriggers;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow @Final private Map<Holder<MobEffect>, MobEffectInstance> activeEffects;
    @Shadow protected abstract void onEffectsRemoved(Collection<MobEffectInstance> effects);

    @ModifyArgs(method = "travelInAir(Lnet/minecraft/world/phys/Vec3;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V"))
    private void travelInAir(Args args) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        boolean flag = livingEntity.getDeltaMovement().y <= 0.0;
        if (flag && livingEntity.isUsingItem() && livingEntity.getUseItem().is(AetherIITags.Items.TOOLS_GLIDERS)) {
            args.set(1, -0.08);
        }
    }

    @WrapOperation(method = "causeFallDamage(DFLnet/minecraft/world/damagesource/DamageSource;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;calculateFallDamage(DF)I"))
    private int causeFallDamage(LivingEntity livingEntity, double fallDistance, float damageModifier, Operation<Integer> original) {
        int damage = original.call(livingEntity, fallDistance, damageModifier);
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            AetherIIAdvancementTriggers.FALL_ON_GROUND.trigger(serverPlayer, fallDistance, damage);
        }
        return damage;
    }

    /**
     * Routes the ground friction lookup through {@link EntityFrictionBlock} (NeoForge's entity-aware {@code getFriction}).
     */
    @WrapOperation(method = "travelInAir", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;getFriction()F"))
    private float aether_ii$entityFriction(Block block, Operation<Float> original, @Local BlockPos posBelow) {
        LivingEntity self = (LivingEntity) (Object) this;
        BlockState state = self.level().getBlockState(posBelow);
        if (state.getBlock() instanceof EntityFrictionBlock frictionBlock) {
            return frictionBlock.getFriction(state, self.level(), posBelow, self);
        }
        return original.call(block);
    }

    @Shadow
    protected ItemStack useItem;

    @Shadow
    public abstract int getUseItemRemainingTicks();

    /**
     * NeoForge's {@code Item#onStopUsing}, see {@link StopUsingItem}.
     */
    @Inject(method = "stopUsingItem", at = @At("HEAD"))
    private void aether_ii$onStopUsing(CallbackInfo ci) {
        if (this.useItem.getItem() instanceof StopUsingItem stopUsingItem) {
            stopUsingItem.onStopUsing(this.useItem, (LivingEntity) (Object) this, this.getUseItemRemainingTicks());
        }
    }

    // ---- AetherIIEvents (NeoForge event equivalents) ----

    @Inject(method = "hurtServer", at = @At("HEAD"), cancellable = true)
    private void aether_ii$incomingDamage(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir, @Share("incomingDamage") LocalRef<AetherIIEvents.DamageContainer> containerRef) {
        AetherIIEvents.DamageContainer container = new AetherIIEvents.DamageContainer(source, damage);
        AetherIIEvents.LIVING_INCOMING_DAMAGE.invoker().onDamage((LivingEntity) (Object) this, container);
        if (container.isCanceled()) {
            cir.setReturnValue(false);
        }
        containerRef.set(container);
    }

    @ModifyVariable(method = "hurtServer", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private float aether_ii$incomingDamageAmount(float damage, @Share("incomingDamage") LocalRef<AetherIIEvents.DamageContainer> containerRef) {
        AetherIIEvents.DamageContainer container = containerRef.get();
        return container != null ? container.getNewDamage() : damage;
    }

    @ModifyVariable(method = "actuallyHurt", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private float aether_ii$damagePre(float damage, ServerLevel level, DamageSource source) {
        AetherIIEvents.DamageContainer container = new AetherIIEvents.DamageContainer(source, damage);
        AetherIIEvents.LIVING_DAMAGE_PRE.invoker().onDamage((LivingEntity) (Object) this, container);
        return container.isCanceled() ? 0.0F : container.getNewDamage();
    }

    @Inject(method = "hurtServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;applyItemBlocking(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)F", shift = At.Shift.AFTER))
    private void aether_ii$shieldBlock(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 1) float damageBlocked) {
        if (damageBlocked > 0.0F) {
            AetherIIEvents.LIVING_SHIELD_BLOCK.invoker().onShieldBlock((LivingEntity) (Object) this, source, damageBlocked);
        }
    }

    @Inject(method = "knockback(DDDLnet/minecraft/world/damagesource/DamageSource;FZ)V", at = @At("HEAD"), cancellable = true)
    private void aether_ii$knockback(double power, double xd, double zd, DamageSource source, float damage, boolean comesFromEffect, CallbackInfo ci) {
        if (!AetherIIEvents.LIVING_KNOCKBACK.invoker().allowKnockback((LivingEntity) (Object) this)) {
            ci.cancel();
        }
    }

    @Inject(method = "completeUsingItem", at = @At("HEAD"))
    private void aether_ii$finishUsingItem(CallbackInfo ci) {
        if (!this.useItem.isEmpty()) {
            AetherIIEvents.LIVING_USE_ITEM_FINISH.invoker().onFinishUsing((LivingEntity) (Object) this, this.useItem);
        }
    }

    @Inject(method = "dropAllDeathLoot", at = @At("TAIL"))
    private void aether_ii$livingDrops(ServerLevel level, DamageSource source, CallbackInfo ci) {
        List<ItemEntity> drops = new ArrayList<>();
        AetherIIEvents.LIVING_DROPS.invoker().onDrops((LivingEntity) (Object) this, source, drops);
        for (ItemEntity drop : drops) {
            level.addFreshEntity(drop);
        }
    }

    @Inject(method = "heal", at = @At("HEAD"), cancellable = true)
    private void aether_ii$heal(float amount, CallbackInfo ci) {
        if (!AetherIIEvents.LIVING_HEAL.invoker().allowHeal((LivingEntity) (Object) this, amount)) {
            ci.cancel();
        }
    }

    @Inject(method = "jumpFromGround", at = @At("TAIL"))
    private void aether_ii$jump(CallbackInfo ci) {
        AetherIIEvents.LIVING_JUMP.invoker().onJump((LivingEntity) (Object) this);
    }

    @Inject(method = "getVisibilityPercent", at = @At("RETURN"), cancellable = true)
    private void aether_ii$visibility(ServerLevel serverLevel, Entity lookingEntity, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(AetherIIEvents.LIVING_VISIBILITY.invoker().modifyVisibility((LivingEntity) (Object) this, lookingEntity, cir.getReturnValue()));
    }

    @Inject(method = "removeEffect", at = @At("HEAD"), cancellable = true)
    private void aether_ii$removeEffect(Holder<MobEffect> effect, CallbackInfoReturnable<Boolean> cir) {
        if (!AetherIIEvents.MOB_EFFECT_REMOVE.invoker().allowRemove((LivingEntity) (Object) this, effect)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * {@code removeAllEffects} clears the map directly, so effects the {@link AetherIIEvents#MOB_EFFECT_REMOVE} listeners
     * want to keep are removed one by one instead.
     */
    @Inject(method = "removeAllEffects", at = @At("HEAD"), cancellable = true)
    private void aether_ii$removeAllEffects(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self.level().isClientSide() || this.activeEffects.isEmpty()) {
            return;
        }
        List<Holder<MobEffect>> kept = new ArrayList<>();
        for (Holder<MobEffect> effect : this.activeEffects.keySet()) {
            if (!AetherIIEvents.MOB_EFFECT_REMOVE.invoker().allowRemove(self, effect)) {
                kept.add(effect);
            }
        }
        if (kept.isEmpty()) {
            return;
        }
        List<MobEffectInstance> removed = new ArrayList<>();
        Iterator<Map.Entry<Holder<MobEffect>, MobEffectInstance>> iterator = this.activeEffects.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Holder<MobEffect>, MobEffectInstance> entry = iterator.next();
            if (!kept.contains(entry.getKey())) {
                removed.add(entry.getValue());
                iterator.remove();
            }
        }
        if (!removed.isEmpty()) {
            this.onEffectsRemoved(removed);
        }
        cir.setReturnValue(!removed.isEmpty());
    }

    /**
     * {@code LivingBreatheEvent}: when a listener says the entity can't breathe, the air-supply logic in {@code baseTick}
     * behaves as if the entity were submerged without water breathing.
     */
    @WrapOperation(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean aether_ii$breatheSubmerged(LivingEntity entity, TagKey<Fluid> fluid, Operation<Boolean> original) {
        return original.call(entity, fluid) || !AetherIIEvents.LIVING_BREATHE.invoker().canBreathe(entity, true);
    }

    @WrapOperation(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;canBreatheUnderwater()Z"))
    private boolean aether_ii$breatheUnderwater(LivingEntity entity, Operation<Boolean> original) {
        return original.call(entity) && AetherIIEvents.LIVING_BREATHE.invoker().canBreathe(entity, true);
    }

    @WrapOperation(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectUtil;hasWaterBreathing(Lnet/minecraft/world/entity/LivingEntity;)Z"))
    private boolean aether_ii$breatheEffect(LivingEntity entity, Operation<Boolean> original) {
        return original.call(entity) && AetherIIEvents.LIVING_BREATHE.invoker().canBreathe(entity, true);
    }

    @WrapOperation(method = "doHurtEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V"))
    private void aether_ii$armorHurt(ItemStack stack, int amount, LivingEntity entity, EquipmentSlot slot, Operation<Void> original, @Local(argsOnly = true) DamageSource damageSource) {
        if (AetherIIEvents.ARMOR_HURT.invoker().allowArmorDamage(entity, damageSource, stack)) {
            original.call(stack, amount, entity, slot);
        }
    }

    @Inject(method = "causeFallDamage", at = @At("HEAD"), cancellable = true)
    private void aether_ii$fall(double fallDistance, float damageModifier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir, @Share("fall") LocalRef<AetherIIEvents.FallContainer> containerRef) {
        AetherIIEvents.FallContainer container = new AetherIIEvents.FallContainer(fallDistance, damageModifier);
        AetherIIEvents.LIVING_FALL.invoker().onFall((LivingEntity) (Object) this, container);
        if (container.isCanceled()) {
            cir.setReturnValue(false);
        }
        containerRef.set(container);
    }

    @ModifyVariable(method = "causeFallDamage", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private double aether_ii$fallDistance(double fallDistance, @Share("fall") LocalRef<AetherIIEvents.FallContainer> containerRef) {
        AetherIIEvents.FallContainer container = containerRef.get();
        return container != null ? container.getDistance() : fallDistance;
    }

    @ModifyVariable(method = "causeFallDamage", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private float aether_ii$fallMultiplier(float damageModifier, @Share("fall") LocalRef<AetherIIEvents.FallContainer> containerRef) {
        AetherIIEvents.FallContainer container = containerRef.get();
        return container != null ? container.getDamageMultiplier() : damageModifier;
    }
}
