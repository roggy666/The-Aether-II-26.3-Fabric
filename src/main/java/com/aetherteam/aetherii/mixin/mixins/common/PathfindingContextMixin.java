package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.block.dungeon.BossDoorwayBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfindingContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mob-aware path types (NeoForge's {@code IBlockExtension#getBlockPathType(..., Mob)}): remembers the pathfinding mob so
 * {@link BossDoorwayBlock} can block bosses.
 */
@Mixin(PathfindingContext.class)
public abstract class PathfindingContextMixin {
    @Shadow
    @Final
    private CollisionGetter level;

    @Unique
    private Mob aether_ii$mob;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void aether_ii$rememberMob(CollisionGetter level, Mob mob, CallbackInfo ci) {
        this.aether_ii$mob = mob;
    }

    @Inject(method = "getPathTypeFromState", at = @At("HEAD"), cancellable = true)
    private void aether_ii$mobAwarePathType(int x, int y, int z, CallbackInfoReturnable<PathType> cir) {
        BlockPos pos = new BlockPos(x, y, z);
        BlockState state = this.level.getBlockState(pos);
        if (state.getBlock() instanceof BossDoorwayBlock doorway) {
            PathType type = doorway.getBlockPathType(state, this.level, pos, this.aether_ii$mob);
            if (type != null) {
                cir.setReturnValue(type);
            }
        }
    }
}
