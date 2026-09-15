package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.block.EntityFrictionBlock;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Routes the ground friction lookup through {@link EntityFrictionBlock} (NeoForge's entity-aware {@code getFriction}).
 */
@Mixin(ItemEntity.class)
public abstract class ItemEntityFrictionMixin {
    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;getFriction()F"))
    private float aether_ii$entityFriction(Block block, Operation<Float> original) {
        ItemEntity self = (ItemEntity) (Object) this;
        var pos = self.getBlockPosBelowThatAffectsMyMovement();
        var state = self.level().getBlockState(pos);
        if (state.getBlock() instanceof EntityFrictionBlock frictionBlock) {
            return frictionBlock.getFriction(state, self.level(), pos, self);
        }
        return original.call(block);
    }
}
