package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.BlockPlacementUtil;
import com.aetherteam.aetherii.world.feature.configuration.ArcticIceSpikeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;

public class ArcticIceSpikeFeature implements Feature {

    public static final MapCodec<ArcticIceSpikeFeature> CODEC = ArcticIceSpikeConfiguration.CODEC.xmap(ArcticIceSpikeFeature::new, ArcticIceSpikeFeature::config);
    private final ArcticIceSpikeConfiguration config;

    public ArcticIceSpikeFeature(ArcticIceSpikeConfiguration config) {
        this.config = config;
    }

    public ArcticIceSpikeConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<ArcticIceSpikeFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;
        ArcticIceSpikeConfiguration config = this.config;

        float radius = random.nextInt(config.additionalRadius()) + config.baseRadius() * 2;
        float slopeIntensity = random.nextInt(config.additionalSlopeIntensity()) + config.baseSlopeIntensity() / 10;

        if (random.nextBoolean()) { // Used for randomizing the rotation
            if (random.nextBoolean()) {
                for (int i = (int) radius; i > 0; --i) {  // Used for making the upper portion
                    BlockPlacementUtil.placeDisk(level, config.block(), new BlockPos((int) ((pos.getX() + i / slopeIntensity) - radius / slopeIntensity), (int) (pos.getY() - i + radius), (int) ((pos.getZ() + i / slopeIntensity) - radius / slopeIntensity)), (float) i / 2, random, false);
                }
                for (int i = (int) radius; i > 0; --i) {  // Used for making the lower portion by inverting the upper spike
                    BlockPlacementUtil.placeDisk(level, config.block(), new BlockPos((int) ((pos.getX() - i / slopeIntensity) + radius / slopeIntensity), (int) (pos.getY() + i - radius), (int) ((pos.getZ() - i / slopeIntensity) + radius / slopeIntensity)), (float) i / 2, random, false);
                }
            } else {
                for (int i = (int) radius; i > 0; --i) {
                    BlockPlacementUtil.placeDisk(level, config.block(), new BlockPos((int) ((pos.getX() - i / slopeIntensity) + radius / slopeIntensity), (int) (pos.getY() - i + radius), (int) ((pos.getZ() + i / slopeIntensity) - radius / slopeIntensity)), (float) i / 2, random, false);
                }
                for (int i = (int) radius; i > 0; --i) {
                    BlockPlacementUtil.placeDisk(level, config.block(), new BlockPos((int) ((pos.getX() + i / slopeIntensity) - radius / slopeIntensity), (int) (pos.getY() + i - radius), (int) ((pos.getZ() - i / slopeIntensity) + radius / slopeIntensity)), (float) i / 2, random, false);
                }
            }
        } else {
            if (random.nextBoolean()) {
                for (int i = (int) radius; i > 0; --i) {
                    BlockPlacementUtil.placeDisk(level, config.block(), new BlockPos((int) ((pos.getX() - i / slopeIntensity) + radius / slopeIntensity), (int) (pos.getY() - i + radius), (int) ((pos.getZ() - i / slopeIntensity) + radius / slopeIntensity)), (float) i / 2, random, false);
                }
                for (int i = (int) radius; i > 0; --i) {
                    BlockPlacementUtil.placeDisk(level, config.block(), new BlockPos((int) ((pos.getX() + i / slopeIntensity) - radius / slopeIntensity), (int) (pos.getY() + i - radius), (int) ((pos.getZ() + i / slopeIntensity) - radius / slopeIntensity)), (float) i / 2, random, false);
                }
            } else {
                for (int i = (int) radius; i > 0; --i) {
                    BlockPlacementUtil.placeDisk(level, config.block(), new BlockPos((int) ((pos.getX() - i / slopeIntensity) + radius / slopeIntensity), (int) (pos.getY() - i + radius), (int) ((pos.getZ() + i / slopeIntensity) - radius / slopeIntensity)), (float) i / 2, random, false);
                }
                for (int i = (int) radius; i > 0; --i) {
                    BlockPlacementUtil.placeDisk(level, config.block(), new BlockPos((int) ((pos.getX() + i / slopeIntensity) - radius / slopeIntensity), (int) (pos.getY() + i - radius), (int) ((pos.getZ() - i / slopeIntensity) + radius / slopeIntensity)), (float) i / 2, random, false);
                }
            }
        }
        return true;
    }
}