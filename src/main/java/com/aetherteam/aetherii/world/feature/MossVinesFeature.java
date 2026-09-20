package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.BottomedVineBlock;
import com.aetherteam.aetherii.world.feature.configuration.MossVinesConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;

public class MossVinesFeature implements Feature {
    public static final MapCodec<MossVinesFeature> CODEC = MossVinesConfiguration.CODEC.xmap(MossVinesFeature::new, MossVinesFeature::config);
    private final MossVinesConfiguration config;

    public MossVinesFeature(MossVinesConfiguration config) {
        this.config = config;
    }

    public MossVinesConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<MossVinesFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos blockpos = origin;
        if (level.isEmptyBlock(blockpos)) {
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            if (BottomedVineBlock.isAcceptableNeighbour(level, blockpos.relative(direction), direction) && !level.getBlockState(blockpos.relative(direction)).is(Blocks.STRUCTURE_BLOCK)) {
                BlockState aboveState = level.getBlockState(blockpos.above());
                BlockState blockState = this.config.blockStateProvider().getState(level, random, blockpos);
                blockState = blockState.setValue(VineBlock.getPropertyForFace(direction), true);
                if ((level.getBlockState(blockpos.relative(direction)).is(AetherIIBlocks.BRYALINN_MOSS_BLOCK)
                        || level.getBlockState(blockpos.above().relative(direction)).is(AetherIIBlocks.BRYALINN_MOSS_BLOCK)
                        || level.getBlockState(blockpos.above().relative(direction)).is(AetherIIBlocks.BRYALINN_MOSS_CARPET))
                        && random.nextInt(4) == 0) {
                    blockState = blockState.setValue(BottomedVineBlock.AGE, 25);
                } else {
                    blockState = blockState.setValue(BottomedVineBlock.AGE, 20 + random.nextInt(5));
                }
                if (!aboveState.is(blockState.getBlock()) || (aboveState.hasProperty(BottomedVineBlock.AGE) && aboveState.getValue(BottomedVineBlock.AGE) < 25)) {
                    addHangingVine(blockpos, blockState, level);
                    return true;
                }
            }
        }
        return false;
    }

    private static void addHangingVine(BlockPos pos, BlockState blockState, WorldGenLevel worldgenlevel) {
        worldgenlevel.setBlock(pos, blockState, 2);
        int i = 10;

        for (BlockPos blockpos = pos.below(); worldgenlevel.isEmptyBlock(blockpos) && i > 0; i--) {
            if (blockState.getValue(BottomedVineBlock.AGE) + 1 <= 25) {
                blockState = blockState.setValue(BottomedVineBlock.AGE, blockState.getValue(BottomedVineBlock.AGE) + 1);
                worldgenlevel.setBlock(blockpos, blockState, 2);
                blockpos = blockpos.below();
            } else {
                break;
            }
        }
    }
}
