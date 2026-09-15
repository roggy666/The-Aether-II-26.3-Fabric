package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.block.natural.GelBlock;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Makes {@link GelBlock} sticky for pistons (NeoForge's {@code isStickyBlock}/{@code canStickTo}).
 */
@Mixin(PistonStructureResolver.class)
public abstract class PistonStructureResolverMixin {
    @Inject(method = "isSticky", at = @At("HEAD"), cancellable = true)
    private static void aether_ii$gelIsSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() instanceof GelBlock gel && gel.isStickyBlock(state)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "canStickToEachOther", at = @At("HEAD"), cancellable = true)
    private static void aether_ii$gelCanStickTo(BlockState state1, BlockState state2, CallbackInfoReturnable<Boolean> cir) {
        if (state1.getBlock() instanceof GelBlock gel) {
            cir.setReturnValue(gel.canStickTo(state1, state2));
        } else if (state2.getBlock() instanceof GelBlock gel) {
            cir.setReturnValue(gel.canStickTo(state2, state1));
        }
    }
}
