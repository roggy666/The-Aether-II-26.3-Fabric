package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.feature.configuration.ArilumConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;

public class ArilumFeature implements Feature {
    public static final MapCodec<ArilumFeature> CODEC = ArilumConfiguration.CODEC.xmap(ArilumFeature::new, ArilumFeature::config);
    private final ArilumConfiguration config;

    public ArilumFeature(ArilumConfiguration config) {
        this.config = config;
    }

    public ArilumConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<ArilumFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        int i = 0;
        BlockPos pos = origin;
        int height = this.config.height().sample(random);
        int depth = this.config.depth().sample(random);
        if (level.getBlockState(pos).is(Blocks.WATER) && level.getBlockState(pos.above(depth)).is(Blocks.WATER)) {
            BlockState endState = this.config.grassProvider().getState(level, random, pos);
            BlockState bodyState = this.config.plantProvider().getState(level, random, pos);
            for (int l = 0; l <= height; l++) {
                if (level.getBlockState(pos).is(Blocks.WATER) && bodyState.canSurvive(level, pos)) {
                    if (l == height) {
                        level.setBlock(pos, endState.setValue(KelpBlock.AGE, random.nextInt(2) + 23), 2);
                        i++;
                    } else {
                        level.setBlock(pos, bodyState, 2);
                    }
                } else if (l > 0) {
                    BlockPos belowPos = pos.below();
                    if (endState.canSurvive(level, belowPos) && !level.getBlockState(belowPos.below()).is(endState.getBlock())) {
                        level.setBlock(belowPos, endState.setValue(KelpBlock.AGE, random.nextInt(2) + 23), 2);
                        i++;
                    }
                    break;
                }

                pos = pos.above();
            }
        }

        return i > 0;
    }
}
