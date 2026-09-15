package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.block.natural.AbstractPointedStoneBlock;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SpeleothemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SpeleothemThickness;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;

/**
 * In 26.2, PointedDripstoneBlock extends SpeleothemBlock.
 * SpeleothemBlock already uses is(this) instead of is(Blocks.POINTED_DRIPSTONE),
 * so most of the old PointedDripstoneBlockMixin WrapOperations are no longer needed.
 * We only need to intercept createSpeleothem to redirect to AbstractPointedStoneBlock variants.
 */
@Mixin(SpeleothemBlock.class)
public class SpeleothemBlockMixin {
    @WrapMethod(method = "createSpeleothem")
    private void aether_ii$createDripstone(LevelAccessor level, BlockPos pos, Direction direction, SpeleothemThickness thickness, Operation<Void> original) {
        BlockState source = level.getBlockState(pos.relative(direction.getOpposite()));
        if (source.getBlock() instanceof AbstractPointedStoneBlock abstractPointedStoneBlock) {
            abstractPointedStoneBlock.createDripstone(level, pos, direction, thickness);
        } else {
            original.call(level, pos, direction, thickness);
        }
    }
}
