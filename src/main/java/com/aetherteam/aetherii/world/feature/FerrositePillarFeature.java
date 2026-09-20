package com.aetherteam.aetherii.world.feature;

import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.data.resources.registries.holyisles.HolyIslesConfiguredFeatures;
import com.aetherteam.aetherii.world.BlockPlacementUtil;
import com.aetherteam.aetherii.world.feature.configuration.FerrositePillarConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public class FerrositePillarFeature implements Feature {

    public static final MapCodec<FerrositePillarFeature> CODEC = FerrositePillarConfiguration.CODEC.xmap(FerrositePillarFeature::new, FerrositePillarFeature::config);
    private final FerrositePillarConfiguration config;

    public FerrositePillarFeature(FerrositePillarConfiguration config) {
        this.config = config;
    }

    public FerrositePillarConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<FerrositePillarFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;
        FerrositePillarConfiguration config = this.config;
        ChunkGenerator chunk = level.getLevel().getChunkSource().getGenerator();

        float radius = random.nextInt(config.additionalRadius()) + config.baseRadius();
        int baseHeight = config.baseHeight();
        int additionalHeight = config.additionalHeight();
        int height = random.nextInt(additionalHeight) + baseHeight;
        int offset = (int) (-radius * 20 + radius * 16);

        for (int i = offset; i < 0; ++i) {
            BlockPlacementUtil.placeDisk(
                    level,
                    config.block(),
                    new BlockPos(pos.getX(), pos.getY() + i + height + (int) radius, pos.getZ()),
                    radius + i * BlockPlacementUtil.shapeVariator(random, 0.05F),
                    random,
                    true);
        }

        for (int i = (int) (-radius * 0.5); i < 0; ++i) {
            BlockPlacementUtil.placeDisk(
                    level,
                    config.block(),
                    new BlockPos(pos.getX(), pos.getY() + i + height + offset + (int) radius, pos.getZ()),
                    radius + i * 2F,
                    random,
                    true);
        }

        BlockPlacementUtil.placeDisk(
                level,
                config.block(),
                new BlockPos(pos.getX() + random.nextInt(2) - 1, pos.getY() + height + (int) radius, pos.getZ() + random.nextInt(2) - 1),
                radius - 3,
                random,
                true);

        Feature turf = level.registryAccess().lookupOrThrow(Registries.FEATURE).getOrThrow(HolyIslesConfiguredFeatures.FERROSITE_PILLAR_TURF).value();
        turf.place(level, chunk, random, new BlockPos(pos.getX(), pos.getY() + height + (int) radius, pos.getZ()));

        distributeSidePillars(level, pos, random, radius, baseHeight, additionalHeight, 1);
        distributeSidePillars(level, pos, random, radius, baseHeight, additionalHeight, -1);

        return true;
    }

    public void placeSidePillar(WorldGenLevel level, RandomSource random, BlockPos pos) {
        FerrositePillarConfiguration config = this.config;
        ChunkGenerator chunk = level.getLevel().getChunkSource().getGenerator();

        float radius = random.nextInt(3) + 2.5F;
        int offset = (int) (-radius * 20 + radius * 16);

        for (int i = offset; i < 0; ++i) {
            BlockPlacementUtil.placeDisk(
                    level,
                    config.block(),
                    new BlockPos(pos.getX(), pos.getY() + i + (int) radius, pos.getZ()),
                    radius + i * BlockPlacementUtil.shapeVariator(random, 0.05F),
                    random,
                    true);
        }

        for (int i = (int) (-radius * 0.5); i < 0; ++i) {
            BlockPlacementUtil.placeDisk(
                    level,
                    config.block(),
                    new BlockPos(pos.getX(), pos.getY() + i + offset + (int) radius, pos.getZ()),
                    radius + i * BlockPlacementUtil.shapeVariator(random, 2F),
                    random,
                    true);
        }

        BlockPlacementUtil.placeDisk(
                level,
                config.block(),
                new BlockPos(pos.getX() + random.nextInt(2) - 1, pos.getY() + (int) radius, pos.getZ() + random.nextInt(2) - 1),
                radius - 2,
                random,
                true);

        Feature turf = level.registryAccess().lookupOrThrow(Registries.FEATURE).getOrThrow(HolyIslesConfiguredFeatures.FERROSITE_PILLAR_TURF).value();
        turf.place(level, chunk, random, new BlockPos(pos.getX(), pos.getY() + (int) radius, pos.getZ()));
    }

    public void distributeSidePillars(WorldGenLevel level, BlockPos pos, RandomSource random, float radius, int baseHeight, int additionalHeight, int offsetMultiplier) {
        placeSidePillar(level, random, new BlockPos(pos.getX() + random.nextInt((int) (radius * 1.25F)) * offsetMultiplier, pos.getY() + random.nextInt(additionalHeight + 2) + baseHeight, pos.getZ() + random.nextInt((int) (radius * 1.25F)) * offsetMultiplier));
        placeSidePillar(level, random, new BlockPos(pos.getX() + random.nextInt((int) (radius * 1.25F)) * offsetMultiplier, pos.getY() + random.nextInt(additionalHeight + 2) + baseHeight, pos.getZ() + random.nextInt((int) (radius * 1.25F)) * offsetMultiplier));
        if (random.nextBoolean()) {
            placeSidePillar(level, random, new BlockPos(pos.getX() + random.nextInt((int) (radius * 1.25F)) * offsetMultiplier, pos.getY() + random.nextInt(additionalHeight + 2) + baseHeight, pos.getZ() + random.nextInt((int) (radius * 1.25F)) * offsetMultiplier));
        }
        if (random.nextBoolean()) {
            placeSidePillar(level, random, new BlockPos(pos.getX() + random.nextInt((int) (radius * 1.25F)) * offsetMultiplier, pos.getY() + random.nextInt(additionalHeight + 2) + baseHeight, pos.getZ() + random.nextInt((int) (radius * 1.25F)) * offsetMultiplier));
        }
    }
}