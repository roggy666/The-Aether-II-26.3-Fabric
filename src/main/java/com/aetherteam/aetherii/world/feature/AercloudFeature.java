package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.feature.configuration.AercloudConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;

public class AercloudFeature implements Feature {
    public static final MapCodec<AercloudFeature> CODEC = AercloudConfiguration.CODEC.xmap(AercloudFeature::new, AercloudFeature::config);
    private final AercloudConfiguration config;

    public AercloudFeature(AercloudConfiguration config) {
        this.config = config;
    }

    public AercloudConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<AercloudFeature> codec() {
        return CODEC;
    }

    /**
     * Randomly places an area blocks in a direction to create a cloud.
     * The code is taken from older versions.
     *
     * @return Whether the placement was successful, as a {@link Boolean}.
     */
    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        boolean positiveZAngle = random.nextBoolean();
        BlockPos blockPos = origin.offset(0, 0, (positiveZAngle ? 0 : 8));
        AercloudConfiguration config = this.config;
        BlockState blockState = config.block().getState(level, random, blockPos);

        int baseWidth = 3;
        int baseHeight = 1;

        for (int lengthCount = 0; lengthCount < config.bounds(); ++lengthCount) {
            boolean changeYChance = random.nextInt(7) > 5;
            blockPos = blockPos.offset(random.nextInt(2), (changeYChance ? random.nextInt(3) - 1 : 0), random.nextInt(2) * (positiveZAngle ? 1 : -1));

            for (int y = 0; y < baseHeight + random.nextInt(2); ++y) {
                for (int x = 0; x < baseWidth + random.nextInt(3); ++x) {
                    for (int z = 0; z < baseWidth + random.nextInt(3); ++z) {
                        BlockPos newPosition = blockPos.offset(x, y, z);
                        if (level.isEmptyBlock(newPosition)) {
                            if (x + y + z < 9 + random.nextInt(16)) {
                                this.setBlock(level, newPosition, blockState);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}