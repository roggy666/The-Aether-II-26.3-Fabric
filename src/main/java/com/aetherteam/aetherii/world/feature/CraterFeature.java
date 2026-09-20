package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.feature.configuration.CraterConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;

public class CraterFeature implements Feature {
    public static final MapCodec<CraterFeature> CODEC = CraterConfiguration.CODEC.xmap(CraterFeature::new, CraterFeature::config);
    private final CraterConfiguration config;

    public CraterFeature(CraterConfiguration config) {
        this.config = config;
    }

    public CraterConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<CraterFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;
        CraterConfiguration config = this.config;

        int radius = config.radius().sample(random);
        int radiusSquared = (radius - 1) * (radius - 1);
        int radiusOutlineSquared = radius * radius;
        for (int x = -radius; x < radius; x++) {
            for (int z = -radius; z < radius; z++) {
                for (int y = -radius; y < radius; y++) {
                    int volume = x * x + y * y + z * z;

                    if (volume <= radiusOutlineSquared) {
                        BlockPos offsetPos = pos.offset(x, y, z);
                        if (y < 0) {
                            if (volume >= radiusSquared) {
                                if (!level.getBlockState(offsetPos).is(config.interiorBlock().getState(level, random, offsetPos).getBlock())) {
                                    level.setBlock(offsetPos, config.exteriorBlock().getState(level, random, offsetPos), 3);
                                }
                            } else {
                                level.setBlock(offsetPos, config.interiorBlock().getState(level, random, offsetPos), 3);
                            }
                        } else {
                            level.setBlock(offsetPos, Blocks.AIR.defaultBlockState(), 3);
                        }
                        if (x == 0 && z == 0 && y == -radius + 2) {
                            level.setBlock(offsetPos, config.craterBlock().getState(level, random, offsetPos), 3);
                        }
                    }
                }
            }
        }

        return true;
    }
}