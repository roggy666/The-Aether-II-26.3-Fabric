package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.event.AetherIIEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.redstone.Orientation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * {@code BlockEvent.NeighborNotifyEvent}.
 */
@Mixin(Level.class)
public abstract class LevelMixin {
    @Inject(method = "updateNeighborsAt(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/redstone/Orientation;)V", at = @At("HEAD"), cancellable = true)
    private void aether_ii$neighborNotify(BlockPos pos, Block sourceBlock, Orientation orientation, CallbackInfo ci) {
        Level self = (Level) (Object) this;
        if (!self.isClientSide() && !AetherIIEvents.NEIGHBOR_NOTIFY.invoker().onNeighborNotify(self, pos)) {
            ci.cancel();
        }
    }
}
