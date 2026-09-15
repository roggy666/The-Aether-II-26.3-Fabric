package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.AetherIITags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AlterGroundDecorator.class)
public class AlterGroundDecoratorMixin {
    @Redirect(method = "placeBlockAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/treedecorators/TreeDecorator$Context;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"))
    private void aetherii$cancelPodzolOnAetherGround(TreeDecorator.Context context, BlockPos pos, BlockState replaceWith) {
        if (replaceWith.is(Blocks.PODZOL) && context.level().isStateAtPosition(pos, state -> state.is(AetherIITags.Blocks.AETHER_GROUND_BLOCKS))) {
            return;
        }
        context.setBlock(pos, replaceWith);
    }
}
