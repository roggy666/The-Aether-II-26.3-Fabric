package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.entity.vehicle.RiderSitContext;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HumanoidMobRenderer.class)
public class HumanoidMobRendererMixin {
    @WrapOperation(method = "extractHumanoidRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FLnet/minecraft/client/renderer/item/ItemModelResolver;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isPassenger()Z"))
    private static boolean shouldRiderSit(LivingEntity entity, Operation<Boolean> original) {
        boolean isPassenger = original.call(entity);
        if (isPassenger) {
            Entity vehicle = entity.getVehicle();
            if (vehicle instanceof RiderSitContext riderSitContext) {
                return riderSitContext.shouldRiderSit(vehicle, entity);
            }
        }
        return isPassenger;
    }
}
