package com.aetherteam.aetherii.block.construction;

import com.aetherteam.aetherii.block.AetherIIBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PathBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AetherDirtPathBlock extends PathBlock {
    public AetherDirtPathBlock(Properties properties) {
        super(AetherIIBlocks.AETHER_DIRT, properties);
    }

    /**
     * [CODE COPY] - {@link DirtPathBlock#getStateForPlacement(BlockPlaceContext)}.
     */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), AetherIIBlocks.AETHER_DIRT.defaultBlockState(), context.getLevel(), context.getClickedPos()) : this.defaultBlockState();
    }

    /**
     * [CODE COPY] - {@link DirtPathBlock#tick(BlockState, ServerLevel, BlockPos, RandomSource)}.
     */
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        AetherFarmlandBlock.turnToDirt(state, level, pos);
    }
}