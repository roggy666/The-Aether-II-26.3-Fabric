package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.feature.configuration.BigMagneticShroomConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class HugeMagneticShroomFeature extends AbstractMagneticShroomFeature {
    public static final MapCodec<HugeMagneticShroomFeature> CODEC = BigMagneticShroomConfiguration.CODEC.xmap(HugeMagneticShroomFeature::new, HugeMagneticShroomFeature::config);

    public HugeMagneticShroomFeature(BigMagneticShroomConfiguration config) {
        super(config);
    }

    @Override
    public MapCodec<HugeMagneticShroomFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;
        BigMagneticShroomConfiguration config = this.config;

        config.groundProvider().ifPresent(provider -> this.placeGround(level, random, pos.below(), provider));
        if (this.canPlace(level, random, pos, config)) {
            if (!config.tall()) {
                this.generateMediumShroom(level, random, pos, config);
            } else {
                BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
                this.generateStem(level, random, mutableBlockPos, config, Direction.UP, UniformInt.of(1, 2));
                this.generateLargeShroom(level, random, mutableBlockPos, config);
            }
            return true;
        }
        return false;
    }

    public boolean canPlace(WorldGenLevel level, RandomSource random, BlockPos pos, BigMagneticShroomConfiguration config) {
        int height = 10;

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
