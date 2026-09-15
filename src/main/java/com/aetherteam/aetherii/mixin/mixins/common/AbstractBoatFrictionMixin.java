package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.block.EntityFrictionBlock;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Routes the boat ground friction lookup through {@link EntityFrictionBlock} (NeoForge's entity-aware {@code getFriction}).
 */
@Mixin(AbstractBoat.class)
public abstract class AbstractBoatFrictionMixin {
    @WrapOperation(method = "getGroundFriction", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;getFriction()F"))
    private float aether_ii$entityFriction(Block block, Operation<Float> original, @Local BlockPos.MutableBlockPos blockPos, @Local BlockState blockState) {
        AbstractBoat self = (AbstractBoat) (Object) this;
        if (blockState.getBlock() instanceof EntityFrictionBlock frictionBlock) {
            return frictionBlock.getFriction(blockState, self.level(), blockPos, self);
        }
        return original.call(block);
    }
}
