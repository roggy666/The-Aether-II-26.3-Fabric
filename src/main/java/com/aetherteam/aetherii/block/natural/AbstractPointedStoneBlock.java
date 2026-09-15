package com.aetherteam.aetherii.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SpeleothemThickness;

public abstract class AbstractPointedStoneBlock extends PointedDripstoneBlock {
    public AbstractPointedStoneBlock(BlockState blockToGrowOn, Properties properties) {
        super(blockToGrowOn, properties);
    }

    public abstract void createDripstone(LevelAccessor level, BlockPos pos, Direction direction, SpeleothemThickness thickness);
}
