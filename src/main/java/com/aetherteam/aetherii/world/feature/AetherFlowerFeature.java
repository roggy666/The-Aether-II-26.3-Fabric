package com.aetherteam.aetherii.world.feature;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.Snowable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import com.aetherteam.aetherii.world.feature.configuration.SimpleBlockConfiguration;

public class AetherFlowerFeature implements Feature {
    public static final MapCodec<AetherFlowerFeature> CODEC = SimpleBlockConfiguration.CODEC.xmap(AetherFlowerFeature::new, AetherFlowerFeature::config);
    private final SimpleBlockConfiguration config;

    public AetherFlowerFeature(SimpleBlockConfiguration config) {
        this.config = config;
    }

    public SimpleBlockConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<AetherFlowerFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        SimpleBlockConfiguration simpleblockconfiguration = this.config;
        BlockPos blockpos = origin;
        BlockState blockstate = simpleblockconfiguration.toPlace().getState(level, random, blockpos);
        BlockState atstate = level.getBlockState(blockpos);
        BlockState belowstate = level.getBlockState(blockpos.below());
        if (blockstate.getBlock() instanceof Snowable && blockstate.hasProperty(BlockStateProperties.SNOWY)
                && (atstate.getBlock() == AetherIIBlocks.ARCTIC_SNOW || (belowstate.getBlock() == AetherIIBlocks.AETHER_GRASS_BLOCK && belowstate.getValue(GrassBlock.SNOWY)))) {
            blockstate = blockstate.setValue(BlockStateProperties.SNOWY, true);
        }
        if (blockstate.canSurvive(level, blockpos)) {
            if (blockstate.getBlock() instanceof DoublePlantBlock) {
                if (!level.isEmptyBlock(blockpos.above())) {
                    return false;
                }

                DoublePlantBlock.placeAt(level, blockstate, blockpos, 2);
            } else {
                level.setBlock(blockpos, blockstate, 2);
            }

            return true;
        } else {
            return false;
        }
    }
}