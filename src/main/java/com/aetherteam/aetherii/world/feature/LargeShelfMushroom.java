package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.BlockPlacementUtil;
import com.aetherteam.aetherii.world.feature.configuration.LargeShelfMushroomConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;

public class LargeShelfMushroom implements Feature {

    public static final MapCodec<LargeShelfMushroom> CODEC = LargeShelfMushroomConfiguration.CODEC.xmap(LargeShelfMushroom::new, LargeShelfMushroom::config);
    private final LargeShelfMushroomConfiguration config;

    public LargeShelfMushroom(LargeShelfMushroomConfiguration config) {
        this.config = config;
    }

    public LargeShelfMushroomConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<LargeShelfMushroom> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;
        LargeShelfMushroomConfiguration config = this.config;

        if (pos.getY() > config.minY()) {
            BlockPlacementUtil.placeDisk(level, config.block(), pos, config.baseRadius() + config.additionalRadius() + 0.5F, random, false);
            return true;
        }
        return false;
    }
}