package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.block.natural.AetherLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Lets plants tagged {@code aether_ii:grows_on_mossy_leaves} survive on mossy Aether leaves (NeoForge's {@code canSustainPlant}).
 */
@Mixin(VegetationBlock.class)
public abstract class VegetationBlockMixin {
    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    private void aether_ii$surviveOnMossyLeaves(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() && AetherLeavesBlock.canSustainPlant(level.getBlockState(pos.below()), state)) {
            cir.setReturnValue(true);
        }
    }
}
