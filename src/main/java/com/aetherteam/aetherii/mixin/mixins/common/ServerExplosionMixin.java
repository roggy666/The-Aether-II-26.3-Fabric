package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.event.AetherIIEvents;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.level.ServerLevel;

/**
 * {@code ExplosionEvent.Detonate}: lets listeners edit the entities affected by an explosion.
 */
@Mixin(ServerExplosion.class)
public abstract class ServerExplosionMixin {
    @WrapOperation(method = "hurtEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getEntities(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;"))
    private List<Entity> aether_ii$detonate(ServerLevel level, Entity source, AABB area, Operation<List<Entity>> original) {
        List<Entity> entities = new ArrayList<>(original.call(level, source, area));
        AetherIIEvents.EXPLOSION_DETONATE.invoker().onDetonate((ServerExplosion) (Object) this, entities);
        return entities;
    }
}
