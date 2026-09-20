package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.BlockPlacementUtil;
import com.aetherteam.aetherii.world.feature.configuration.FerrositeSpikeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.*;

public class FerrositeSpikeFeature implements Feature {

    public static final MapCodec<FerrositeSpikeFeature> CODEC = FerrositeSpikeConfiguration.CODEC.xmap(FerrositeSpikeFeature::new, FerrositeSpikeFeature::config);
    private final FerrositeSpikeConfiguration config;

    public FerrositeSpikeFeature(FerrositeSpikeConfiguration config) {
        this.config = config;
    }

    public FerrositeSpikeConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<FerrositeSpikeFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin.below(2);
        FerrositeSpikeConfiguration config = this.config;

        Set<BlockPos> positions = new HashSet<>();

        float radius = random.nextInt(config.additionalRadius()) + config.baseRadius();
        float heightFactor = 6.5F + random.nextInt(5);

        for (int i = 0; i < radius * heightFactor; ++i) {
            if (i < radius * heightFactor - heightFactor * 0.1F) {
                this.placeDisk(level, new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()), radius - (float) i / 20 - BlockPlacementUtil.shapeVariator(random, 0.5F), true, positions);
            }
            if (i == radius * heightFactor - heightFactor * 0.2F) {
                this.placeDisk(level, new BlockPos(pos.getX() + random.nextIntBetweenInclusive(-1, 1), pos.getY() + i, pos.getZ() + random.nextIntBetweenInclusive(-1, 1)), radius - (float) i / 10, false, positions);
            }
        }

        if (random.nextBoolean()) {
            this.placeSideSpike(level, random, new BlockPos(pos.getX() + random.nextInt(3) + 2, pos.getY() - random.nextInt(2) - 1, pos.getZ() + random.nextInt(3) + 2), positions);
        }
        if (random.nextBoolean()) {
            this.placeSideSpike(level, random, new BlockPos(pos.getX() - random.nextInt(3) + 2, pos.getY() - random.nextInt(2) - 1, pos.getZ() - random.nextInt(3) + 2), positions);
        }
        if (random.nextInt(2) == 0) {
            this.placeSideSpike(level, random, new BlockPos(pos.getX() + random.nextInt(3) + 2, pos.getY() - random.nextInt(2) - 1, pos.getZ() + random.nextInt(3) + 2), positions);
        }
        if (random.nextInt(2) == 0) {
            this.placeSideSpike(level, random, new BlockPos(pos.getX() - random.nextInt(3) + 2, pos.getY() - random.nextInt(2) - 1, pos.getZ() - random.nextInt(3) + 2), positions);
        }

        for (BlockPos position : positions) {
            if (position.getY() == pos.getY()) {
                if (!level.getBlockState(position.below()).isSolid()) {
                    return false;
                }
            }
        }

        for (BlockPos position : positions) {
            level.setBlock(position, config.block().getState(level, random, position), 2);
        }

        return true;
    }

    public void placeSideSpike(WorldGenLevel level, RandomSource random, BlockPos pos, Set<BlockPos> positions) {
        FerrositeSpikeConfiguration config = this.config;

        float radius = random.nextInt(config.additionalRadius()) + config.baseRadius();
        float heightFactor = 3.5F + random.nextInt(2);

        for (int i = 0; i < radius * heightFactor; ++i) {
            if (i < radius * heightFactor - heightFactor * 0.5F) {
                this.placeDisk(level, new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()), radius - (float) i / 10 - BlockPlacementUtil.shapeVariator(random, 0.35F), true, positions);
            }
            if (i == radius * heightFactor - heightFactor * 0.5F) {
                this.placeDisk(level, new BlockPos(pos.getX() + random.nextIntBetweenInclusive(-1, 1), pos.getY() + i, pos.getZ() + random.nextIntBetweenInclusive(-1, 1)), radius - (float) i / 4, true, positions);
            }
        }
    }

    public void placeDisk(WorldGenLevel level, BlockPos center, float radius, boolean replaceBlocks, Set<BlockPos> positions) {
        float radiusSq = radius * radius;
        this.placeProvidedBlock(level, center, replaceBlocks, positions);
        for (int z = 0; z < radius; z++) {
            for (int x = 0; x < radius; x++) {
                if (x * x + z * z > radiusSq) continue;
                this.placeProvidedBlock(level, center.offset(x, 0, z), replaceBlocks, positions);
                this.placeProvidedBlock(level, center.offset(-x, 0, -z), replaceBlocks, positions);
                this.placeProvidedBlock(level, center.offset(-z, 0, x), replaceBlocks, positions);
                this.placeProvidedBlock(level, center.offset(z, 0, -x), replaceBlocks, positions);
            }
        }
    }

    public void placeProvidedBlock(WorldGenLevel level, BlockPos pos, boolean replaceBlocks, Set<BlockPos> positions) {
        if (replaceBlocks) {
            positions.add(pos);
        } else if (level.getBlockState(pos).isAir()) {
            positions.add(pos);
        }
    }
}