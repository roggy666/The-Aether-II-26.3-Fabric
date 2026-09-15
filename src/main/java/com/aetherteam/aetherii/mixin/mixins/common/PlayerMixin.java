package com.aetherteam.aetherii.mixin.mixins.common;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.item.Items;
import com.aetherteam.aetherii.item.equipment.weapons.TieredCrossbowItem;
import com.aetherteam.aetherii.event.AetherIIEvents;
import com.aetherteam.aetherii.entity.monster.PlantMob;
import com.aetherteam.aetherii.entity.passive.MountableAetherAnimal;
import com.aetherteam.aetherii.item.SpecialAttackStrengthScale;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.mixin.MixinHooks;
import com.aetherteam.aetherii.mixin.mixins.common.accessor.LivingEntityAccessor;
import com.aetherteam.aetherii.mixin.wrappers.common.ItemCooldownsWrapper;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Mutable
    @Final
    @Shadow
    private ItemCooldowns cooldowns;

    @Shadow
    protected abstract boolean wantsToStopRiding();

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(Level level, GameProfile gameProfile, CallbackInfo ci) {
        Player player = (Player) (Object) this;
        ItemCooldowns itemCooldowns = this.cooldowns;
        itemCooldowns = ((ItemCooldownsWrapper) itemCooldowns).aether_ii$setPlayer(player);
        this.cooldowns = itemCooldowns;
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeExtraKnockback(Lnet/minecraft/world/entity/Entity;FLnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/damagesource/DamageSource;FZ)V"), method = "attack(Lnet/minecraft/world/entity/Entity;)V")
    private void attack(Entity target, CallbackInfo ci, @Local ItemStack weapon, @Local(name = "criticalAttack") boolean criticalAttack, @Share("canShortswordSlash") LocalBooleanRef canShortswordSlash, @Share("canHammerShock") LocalBooleanRef canHammerShock, @Share("canSpearStab") LocalBooleanRef canSpearStab) {
        Player player = (Player) (Object) this;
        AetherIIEvents.CRITICAL_HIT.invoker().onCriticalHit(player, target, criticalAttack, criticalAttack ? 1.5F : 1.0F);
        boolean canShortsword = (weapon.getItem() instanceof com.aetherteam.aetherii.item.equipment.WeaponAbilityItem abilityItem && abilityItem.canPerformAction(weapon, com.aetherteam.aetherii.item.equipment.WeaponAbility.SHORTSWORD_SLASH)) || weapon.is(AetherIITags.Items.TOOLS_SHORTSWORDS);
        boolean canHammer = (weapon.getItem() instanceof com.aetherteam.aetherii.item.equipment.WeaponAbilityItem abilityItem && abilityItem.canPerformAction(weapon, com.aetherteam.aetherii.item.equipment.WeaponAbility.HAMMER_SHOCK)) || weapon.is(AetherIITags.Items.TOOLS_HAMMERS);
        boolean canPike = (weapon.getItem() instanceof com.aetherteam.aetherii.item.equipment.WeaponAbilityItem abilityItem && abilityItem.canPerformAction(weapon, com.aetherteam.aetherii.item.equipment.WeaponAbility.PIKE_STAB)) || weapon.is(AetherIITags.Items.TOOLS_PIKES);
        MixinHooks.shortswordSlashBehavior(player, target, canShortsword);
        MixinHooks.hammerShockBehavior(player, target, canHammer);
        MixinHooks.pikeStabBehavior(player, target, canPike);
    }

    @WrapOperation(method = "doSweepAttack(Lnet/minecraft/world/entity/Entity;FLnet/minecraft/world/damagesource/DamageSource;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private boolean wrapHurtServer(LivingEntity instance, ServerLevel serverLevel, DamageSource damageSource, float damage, Operation<Boolean> original) {
        if (instance instanceof PlantMob) {
            return false;
        }
        return original.call(instance, serverLevel, damageSource, damage);
    }

    /**
     * Used to set whether the player tried to crouch for {@link MountableAetherAnimal}, before crouching is cancelled for mounts by the {@link Player} class.
     *
     * @param ci The {@link CallbackInfo} for the void method return.
     */
    @Inject(at = @At(value = "HEAD"), method = "rideTick()V")
    private void rideTickHead(CallbackInfo ci, @Share("wantsToStopRiding") LocalBooleanRef wantsToStopRiding) {
        Player player = (Player) (Object) this;
        wantsToStopRiding.set(this.wantsToStopRiding());
        if (!player.level().isClientSide()) {
            if (player.isPassenger() && player.getVehicle() instanceof MountableAetherAnimal mount) {
                mount.setPlayerTriedToCrouch(player.isShiftKeyDown());
            }
        }
    }

    @Inject(at = @At(value = "TAIL"), method = "rideTick()V")
    private void rideTickTail(CallbackInfo ci, @Share("wantsToStopRiding") LocalBooleanRef wantsToStopRiding) {
        Player player = (Player) (Object) this;
        if (!player.level().isClientSide() && !player.isShiftKeyDown() && wantsToStopRiding.get()) {
            if (player.isPassenger() && player.getVehicle() instanceof MountableAetherAnimal) {
                player.setShiftKeyDown(true);
            }
        }
    }

    @WrapMethod(method = "getAttackStrengthScale(F)F")
    private float getCurrentItemAttackStrengthDelay(float adjustTicks, Operation<Float> original) {
        Player player = (Player) (Object) this;
        ItemStack itemStack = player.getWeaponItem();
        if (itemStack.getItem() instanceof SpecialAttackStrengthScale specialAttackStrengthScale) {
            return specialAttackStrengthScale.getAttackStrengthScale(player.level(), player, itemStack, adjustTicks, ((LivingEntityAccessor) player).aether$getAttackStrengthTicker());
        }
        return original.call(adjustTicks);
    }

    @Inject(method = "tick()V", at = @At("HEAD"))
    private void aether_ii$tickPre(CallbackInfo ci) {
        AetherIIEvents.PLAYER_TICK_PRE.invoker().onPlayerTick((Player) (Object) this);
    }

    @Inject(method = "tick()V", at = @At("TAIL"))
    private void aether_ii$tickPost(CallbackInfo ci) {
        AetherIIEvents.PLAYER_TICK_POST.invoker().onPlayerTick((Player) (Object) this);
    }

    /**
     * NeoForge's {@code ProjectileWeaponItem#getDefaultCreativeAmmo}, see {@link com.aetherteam.aetherii.item.equipment.weapons.TieredCrossbowItem}.
     */
    @Inject(method = "getProjectile", at = @At("RETURN"), cancellable = true)
    private void aether_ii$defaultCreativeAmmo(ItemStack heldWeapon, CallbackInfoReturnable<ItemStack> cir) {
        Player player = (Player) (Object) this;
        if (heldWeapon.getItem() instanceof TieredCrossbowItem crossbow && player.hasInfiniteMaterials() && cir.getReturnValue().is(Items.ARROW)) {
            cir.setReturnValue(crossbow.getDefaultCreativeAmmo(player, heldWeapon));
        }
    }
}
