package com.aetherteam.aetherii.world.feature;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.block.natural.OrangeTreeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import com.aetherteam.aetherii.world.feature.configuration.SimpleBlockConfiguration;

public class OrangeTreeFeature implements Feature {
    public static final MapCodec<OrangeTreeFeature> CODEC = SimpleBlockConfiguration.CODEC.xmap(OrangeTreeFeature::new, OrangeTreeFeature::config);
    private final SimpleBlockConfiguration config;

    public OrangeTreeFeature(SimpleBlockConfiguration config) {
        this.config = config;
    }

    public SimpleBlockConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<OrangeTreeFeature> codec() {
        return CODEC;
    }

    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        SimpleBlockConfiguration config = this.config;
        BlockPos pos = origin;
        BlockState state = config.toPlace().getState(level, random, pos);

        if (state.canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
            OrangeTreeBlock.placeAt(level, state, pos, 2);
            return true;
        } else {
            return false;
        }
    }
}