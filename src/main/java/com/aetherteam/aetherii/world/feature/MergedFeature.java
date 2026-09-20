package com.aetherteam.aetherii.world.feature;

import java.util.stream.Stream;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.feature.configuration.MergedConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MergedFeature implements Feature {
    public static final MapCodec<MergedFeature> CODEC = MergedConfiguration.CODEC.xmap(MergedFeature::new, MergedFeature::config);
    private final MergedConfiguration config;

    public MergedFeature(MergedConfiguration config) {
        this.config = config;
    }

    public MergedConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<MergedFeature> codec() {
        return CODEC;
    }

    @Override
    public Stream<Holder<Feature>> getSubFeatures() {
        return this.config.features().stream().flatMap((feature) -> feature.value().getFeatures());
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        MergedConfiguration config = this.config;

        boolean flag = false;
        for (Holder<PlacedFeature> feature : config.features()) {
            if (feature.value().place(level, chunkGenerator, random, origin)) {
                flag = true;
            }
        }
        return flag;
    }
}
