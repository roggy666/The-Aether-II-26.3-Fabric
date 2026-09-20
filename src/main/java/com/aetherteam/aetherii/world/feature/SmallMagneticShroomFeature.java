package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.feature.configuration.BigMagneticShroomConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SmallMagneticShroomFeature extends AbstractMagneticShroomFeature {
    public static final MapCodec<SmallMagneticShroomFeature> CODEC = BigMagneticShroomConfiguration.CODEC.xmap(SmallMagneticShroomFeature::new, SmallMagneticShroomFeature::config);

    public SmallMagneticShroomFeature(BigMagneticShroomConfiguration config) {
        super(config);
    }

    @Override
    public MapCodec<SmallMagneticShroomFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;
        BigMagneticShroomConfiguration config = this.config;

        config.groundProvider().ifPresent(provider -> this.placeGround(level, random, pos.below(), provider));
        if (this.canPlace(level, random, pos, config)) {
            this.generateSmallShroom(level, random, pos, config);
            return true;
        }
        return false;
    }

    public boolean canPlace(WorldGenLevel level, RandomSource random, BlockPos pos, BigMagneticShroomConfiguration config) {
        int height = 4;

        for (int y = 0; y <= height; ++y) {
            int i = config.minimumSize().getSizeAtHeight(height, y);

            for (int x = -i; x <= i; ++x) {
                for (int z = -i; z <= i; ++z) {
                    BlockPos checkPos = pos.offset(x, y, z);
                    if (!level.isStateAtPosition(checkPos, BlockBehaviour.BlockStateBase::isAir)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
