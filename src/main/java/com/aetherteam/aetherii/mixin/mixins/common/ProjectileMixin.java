package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.event.AetherIIEvents;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Projectile.class)
public abstract class ProjectileMixin {
    @Inject(method = "onHit", at = @At("HEAD"))
    private void aether_ii$projectileImpact(HitResult hitResult, CallbackInfo ci) {
        AetherIIEvents.PROJECTILE_IMPACT.invoker().onImpact((Projectile) (Object) this, hitResult);
    }
}
