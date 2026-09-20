package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.feature.configuration.FallenLogConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;

public class FallenLogFeature implements Feature {
    public static final MapCodec<FallenLogFeature> CODEC = FallenLogConfiguration.CODEC.xmap(FallenLogFeature::new, FallenLogFeature::config);
    private final FallenLogConfiguration config;

    public FallenLogFeature(FallenLogConfiguration config) {
        this.config = config;
    }

    public FallenLogConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<FallenLogFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;
        FallenLogConfiguration config = this.config;

        int length = config.length().sample(random);
        Direction direction = Direction.from2DDataValue(random.nextInt(4));

        for (int i = 0; i < length; i++) {
            BlockPos placementPos = pos.relative(direction, i);
            BlockState blockState = config.block().getState(level, random, placementPos);
            if ((level.getBlockState(placementPos).canBeReplaced() || level.getBlockState(placementPos).liquid()) && level.getBlockState(placementPos.below()).is(config.validBlocks())) {
                if (blockState.getOptionalValue(BlockStateProperties.AXIS).isPresent()) {
                    blockState = blockState.setValue(BlockStateProperties.AXIS, direction.getAxis());
                    level.setBlock(placementPos, blockState, 2);
                    if (config.vegetationChance() > 0.0F && random.nextFloat() < config.vegetationChance()) {
                        config.vegetationFeature().ifPresent(placedFeatureHolder -> placedFeatureHolder.value().place(level, chunkGenerator, random, placementPos));
                    }
                }
            }
        }

        return false;
    }
}
