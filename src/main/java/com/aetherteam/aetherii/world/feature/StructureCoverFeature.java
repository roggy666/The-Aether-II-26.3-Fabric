package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.levelgen.densityfunction.DensitySampler;
import com.aetherteam.aetherii.world.density.DensitySampling;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.feature.configuration.StructureCoverConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class StructureCoverFeature implements Feature {

    public static final MapCodec<StructureCoverFeature> CODEC = StructureCoverConfiguration.CODEC.xmap(StructureCoverFeature::new, StructureCoverFeature::config);
    private final StructureCoverConfiguration config;

    public StructureCoverFeature(StructureCoverConfiguration config) {
        this.config = config;
    }

    public StructureCoverConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<StructureCoverFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin.below(2);
        StructureCoverConfiguration config = this.config;
        Set<BlockPos> positions = new HashSet<>();

        DensitySampler.Bound noise = DensitySampling.sampler(level, config.noise());

        if (config.calculationType() == CalculationType.BOTTOM_TO_TOP) {
            for (int i = 0; i < config.height(); ++i) {
                placeStructureCover(i, level, random, pos, config, noise, positions);
            }
        }
        if (config.calculationType() == CalculationType.TOP_TO_BOTTOM) {
            for (int i = 0; i > config.height(); --i) {
                placeStructureCover(i, level, random, pos, config, noise, positions);
            }
        }

        return true;
    }

    public void placeStructureCover(int i, WorldGenLevel level, RandomSource random, BlockPos pos, StructureCoverConfiguration config, DensitySampler.Bound noise, Set<BlockPos> positions) {
        this.placeDisk(level, new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()), config.radius(), positions);

        for (BlockPos position : positions) {
            double density = noise.sampleValue(position.getX(), position.getY(), position.getZ());
            if (position.getY() == pos.getY() + i) {
                double densitySmoothed = density - Mth.clamp(position.distToCenterSqr(pos.getX(), pos.getY() + i, pos.getZ()) * config.inclineFactor() - config.radius() * config.scatterFactor(), 0, 10);
                if (densitySmoothed > 0) {
                    if (position.getY() > config.blockTransitionHeight()) {
                        level.setBlock(position, config.block().getState(level, random, position), 2);
                    } else {
                        level.setBlock(position, config.secondaryBlock().getState(level, random, position), 2);
                    }
                }
            }
        }
    }

    public void placeDisk(WorldGenLevel level, BlockPos center, float radius, Set<BlockPos> positions) {
        float radiusSq = radius * radius;
        this.placeProvidedBlock(level, center, positions);
        for (int z = 0; z < radius; z++) {
            for (int x = 0; x < radius; x++) {
                if (x * x + z * z > radiusSq) continue;
                this.placeProvidedBlock(level, center.offset(x, 0, z), positions);
                this.placeProvidedBlock(level, center.offset(-x, 0, -z), positions);
                this.placeProvidedBlock(level, center.offset(-z, 0, x), positions);
                this.placeProvidedBlock(level, center.offset(z, 0, -x), positions);
            }
        }
    }

    public void placeProvidedBlock(WorldGenLevel level, BlockPos pos, Set<BlockPos> positions) {
        if ((level.getBlockState(pos).isAir() && level.getBlockState(pos).getBlock() != Blocks.VOID_AIR)
                || level.getBlockState(pos).getBlock() == Blocks.BARRIER //TODO DEBUG
        ) {
            positions.add(pos);
        }
    }

    public enum CalculationType implements StringRepresentable {
        BOTTOM_TO_TOP,
        TOP_TO_BOTTOM;

        public static final Codec<CalculationType> CODEC = StringRepresentable.fromEnum(CalculationType::values);

        @Override
        public String getSerializedName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}