package com.aetherteam.aetherii.entity.ai.navigator;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;

public class FloatWaterPathNavigation extends FallPathNavigation {
    public FloatWaterPathNavigation(PathfinderMob mob, Level level) {
        super(mob, level);
    }

    @Override
    protected boolean canUpdatePath() { // NeoForge's hasValidPathType(PathType.WATER)
        return super.canUpdatePath() || this.mob.isInWater();
    }

    @Override
    public boolean isStableDestination(BlockPos pos) {
        return this.level.getFluidState(pos).is(FluidTags.WATER) || super.isStableDestination(pos);
    }
}
