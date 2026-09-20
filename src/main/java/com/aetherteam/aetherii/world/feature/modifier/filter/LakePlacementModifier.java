package com.aetherteam.aetherii.world.feature.modifier.filter;

import com.aetherteam.aetherii.data.resources.registries.AetherIIDensityFunctions;
import com.aetherteam.aetherii.world.density.DensitySampling;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensitySampler;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

public class LakePlacementModifier implements PlacementModifier {
    public static final MapCodec<LakePlacementModifier> CODEC = MapCodec.unit(LakePlacementModifier::new);
    private static final int HEIGHT = 124;
    private static final double NOISE_START_VALUE = 0.3;

    @Override
    public void modify(PlacementContext context, RandomSource random, BlockPos blockPos, Consumer<BlockPos> output) {
        WorldGenLevel level = context.getLevel();
        HolderGetter<DensityFunction> function = level.registryAccess().lookupOrThrow(Registries.DENSITY_FUNCTION);
        Samplers samplers = new Samplers(
                DensitySampling.sampler(level, AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_NOISE)),
                DensitySampling.sampler(level, AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_FLOOR)),
                DensitySampling.sampler(level, AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_BARRIER))
        );
        Set<BlockPos> positions = new TreeSet<>();
        int chunkX = blockPos.getX() - (blockPos.getX() % 16);
        int chunkZ = blockPos.getZ() - (blockPos.getZ() % 16);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int xCoord = chunkX + x;
                int zCoord = chunkZ + z;
                BlockPos layerPos = new BlockPos(xCoord, HEIGHT, zCoord);
                this.gatherLakeLayer(level, samplers, layerPos.below(1), NOISE_START_VALUE + 0.025, 0.8, positions);
                this.gatherLakeLayer(level, samplers, layerPos.below(2), NOISE_START_VALUE + 0.04, 0.75, positions);
                this.gatherLakeLayer(level, samplers, layerPos.below(3), NOISE_START_VALUE + 0.045, 0.7, positions);
                this.gatherLakeLayer(level, samplers, layerPos.below(4), NOISE_START_VALUE + 0.05, 0.625, positions);
                this.gatherLakeLayer(level, samplers, layerPos.below(5), NOISE_START_VALUE + 0.055, 0.55, positions);
                this.gatherLakeLayer(level, samplers, layerPos.below(6), NOISE_START_VALUE + 0.06, 0.475, positions);
                this.gatherLakeLayer(level, samplers, layerPos.below(7), NOISE_START_VALUE + 0.065, 0.4, positions);
                this.gatherLakeLayer(level, samplers, layerPos.below(8), NOISE_START_VALUE + 0.07, 0.3, positions);
                this.gatherLakeLayer(level, samplers, layerPos.below(9), NOISE_START_VALUE + 0.075, 0.2, positions);
                this.gatherLakeLayer(level, samplers, layerPos.below(10), NOISE_START_VALUE + 0.082, 0.1, positions);
                this.gatherLakeLayer(level, samplers, layerPos.below(11), NOISE_START_VALUE + 0.05, 0.035, positions);
            }
        }
        positions.forEach(output);
    }

    public void gatherLakeLayer(WorldGenLevel level, Samplers samplers, BlockPos pos, double noiseValue, double floorNoiseValue, Set<BlockPos> positions) {
        double density = samplers.lake().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        double floor = samplers.floor().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        double barrier = samplers.barrier().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        int thickness = calculateThickness(barrier, pos.getY(), HEIGHT);
        if (density > noiseValue && density < 1.5) {
            if (floor < floorNoiseValue) {
                for (int i = 0; i < barrier; i++) {
                    if (!level.isEmptyBlock(pos)
                            && !level.isEmptyBlock(pos.east(thickness))
                            && !level.isEmptyBlock(pos.north(thickness))
                            && !level.isEmptyBlock(pos.south(thickness))
                            && !level.isEmptyBlock(pos.west(thickness))
                            && !level.isEmptyBlock(pos.below(2))
                            && !level.getBlockState(pos.above()).isSolid()
                    ) {
                        positions.add(pos);
                    }
                }
            }
        }
    }

    public int calculateThickness(double barrier, int y, int height) {
        return (int) (y == height ? barrier / 2 : barrier);
    }

    @Override
    public MapCodec<LakePlacementModifier> codec() {
        return CODEC;
    }

    public record Samplers(DensitySampler.Bound lake, DensitySampler.Bound floor, DensitySampler.Bound barrier) {
    }
}
