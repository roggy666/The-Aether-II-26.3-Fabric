package com.aetherteam.aetherii.world.feature;

import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.world.density.DensitySampling;
import com.aetherteam.aetherii.world.feature.configuration.NoiseLakeConfiguration;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.densityfunction.DensitySampler;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.material.Fluids;

public class NoiseLakeFeature implements Feature {
    public static final MapCodec<NoiseLakeFeature> CODEC = NoiseLakeConfiguration.CODEC.xmap(NoiseLakeFeature::new, NoiseLakeFeature::config);
    private final NoiseLakeConfiguration config;

    public NoiseLakeFeature(NoiseLakeConfiguration config) {
        this.config = config;
    }

    public NoiseLakeConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<NoiseLakeFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        NoiseLakeConfiguration config = this.config;
        int chunkX = origin.getX() - (origin.getX() % 16);
        int chunkZ = origin.getZ() - (origin.getZ() % 16);
        int height = config.height().minInclusive();
        double noiseStartValue = config.noiseStartValue();
        Samplers samplers = Samplers.create(level, config);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int xCoord = chunkX + x;
                int zCoord = chunkZ + z;
                BlockPos layerPos = new BlockPos(xCoord, height, zCoord);
                if (!config.frozen()) {
                    this.placeShore(level, random, samplers, layerPos);
                }
                this.placeShoreLayer(level, random, samplers, layerPos, noiseStartValue, 1.0);
                this.placeLakeLayer(level, random, samplers, layerPos.below(1), noiseStartValue + 0.025, 0.8);
                this.placeLakeLayer(level, random, samplers, layerPos.below(2), noiseStartValue + 0.04, 0.75);
                this.placeLakeLayer(level, random, samplers, layerPos.below(3), noiseStartValue + 0.045, 0.7);
                this.placeLakeLayer(level, random, samplers, layerPos.below(4), noiseStartValue + 0.05, 0.625);
                this.placeLakeLayer(level, random, samplers, layerPos.below(5), noiseStartValue + 0.055, 0.55);
                this.placeLakeLayer(level, random, samplers, layerPos.below(6), noiseStartValue + 0.06, 0.475);
                this.placeLakeLayer(level, random, samplers, layerPos.below(7), noiseStartValue + 0.065, 0.4);
                this.placeLakeLayer(level, random, samplers, layerPos.below(8), noiseStartValue + 0.07, 0.3);
                this.placeLakeLayer(level, random, samplers, layerPos.below(9), noiseStartValue + 0.075, 0.2);
                this.placeLakeLayer(level, random, samplers, layerPos.below(10), noiseStartValue + 0.082, 0.1);
                this.placeLakeLayer(level, random, samplers, layerPos.below(11), noiseStartValue + 0.05, 0.035);
            }
        }
        return true;
    }

    public void placeLakeLayer(WorldGenLevel level, RandomSource random, Samplers samplers, BlockPos pos, double noiseValue, double floorNoiseValue) {
        NoiseLakeConfiguration config = this.config;
        double density = samplers.lake().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        double floor = samplers.floor().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        double barrier = samplers.barrier().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        int thickness = calculateThickness(barrier, pos.getY(), config.height().value());
        if (density > noiseValue && density < 1.5) {
            if (floor < floorNoiseValue) {
                for (int i = 0; i < barrier; i++) {
                    if (!level.isEmptyBlock(pos)
                            && !level.isEmptyBlock(pos.east(thickness))
                            && !level.isEmptyBlock(pos.north(thickness))
                            && !level.isEmptyBlock(pos.south(thickness))
                            && !level.isEmptyBlock(pos.west(thickness))
                            && !level.isEmptyBlock(pos.below(2))
                            && (!level.getBlockState(pos.above()).isSolid() || level.getBlockState(pos.above()).is(AetherIIBlocks.ARCTIC_ICE) || level.getBlockState(pos.above()).is(AetherIIBlocks.FRAGILE_ARCTIC_ICE))
                    ) {
                        if (pos.getY() == config.height().value() - 1 && config.frozen()) {
                            this.setBlock(level, pos, config.iceBlock().getState(level, random, pos));
                        }
                        else this.setBlock(level, pos, Blocks.WATER.defaultBlockState());
                        this.setBlock(level, pos.below(), config.underwaterBlock().getState(level, random, pos.below()));
                        if (level.isEmptyBlock(pos.below(2))) {
                            this.setBlock(level, pos.below(2), AetherIIBlocks.HOLYSTONE.defaultBlockState());
                        }
                    }
                }
            }
        }
    }

    public void placeShoreLayer(WorldGenLevel level, RandomSource random, Samplers samplers, BlockPos pos, double noiseValue, double floorNoiseValue) {
        NoiseLakeConfiguration config = this.config;
        double density = samplers.lake().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        double floor = samplers.floor().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        double barrier = samplers.barrier().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        double waterfalls = samplers.waterfall().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        int thickness = config.frozen() ? calculateThickness(barrier, pos.getY(), config.height().value()) : calculateShoreThickness(barrier, waterfalls, pos.getY(), config.height().value());
        if (density > noiseValue && density < 1.5) {
            if (floor < floorNoiseValue) {
                for (int i = 0; i < barrier; i++) {
                    if (!level.isEmptyBlock(pos)
                            && !level.isEmptyBlock(pos.below().east(thickness))
                            && !level.isEmptyBlock(pos.below().north(thickness))
                            && !level.isEmptyBlock(pos.below().south(thickness))
                            && !level.isEmptyBlock(pos.below().west(thickness))
                            && !level.isEmptyBlock(pos.below().below(2))
                            && !level.getBlockState(pos.above()).isSolid()
                    ) {
                        this.setBlock(level, pos, Blocks.AIR.defaultBlockState());
                        if (thickness > 1) {
                            this.setBlock(level, pos.below(), config.shoreBlock().getState(level, random, pos.below()));
                        } else {
                            this.setBlock(level, pos, Blocks.AIR.defaultBlockState());
                            level.setBlock(pos.below(), Fluids.WATER.defaultFluidState().createLegacyBlock(), 2);
                            level.scheduleTick(pos.below(), Fluids.WATER.defaultFluidState().getType(), 0);
                        }
                        this.setBlock(level, pos.below(2), AetherIIBlocks.AETHER_DIRT.defaultBlockState());
                        if (level.getBlockState(pos.above()).is(AetherIITags.Blocks.LAKE_VEGETATION_REPLACEABLES) || level.getBlockState(pos.above(2)).is(AetherIITags.Blocks.LAKE_VEGETATION_REPLACEABLES)) {
                            this.setBlock(level, pos.above(), Blocks.AIR.defaultBlockState());
                        }
                    }
                }
            }
        }
    }

    public void placeShore(WorldGenLevel level, RandomSource random, Samplers samplers, BlockPos pos) {
        NoiseLakeConfiguration config = this.config;
        double density = samplers.lake().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        double shore = samplers.shore().sampleValue(pos.getX(), pos.getY(), pos.getZ());
        if (density > config.shoreStartValue() + shore) {
            if (level.getBlockState(pos.below()).is(AetherIITags.Blocks.AETHER_GROUND_BLOCKS) && level.getBlockState(pos.above()).is(AetherIITags.Blocks.AETHER_GROUND_BLOCKS)) {
                this.setBlock(level, pos.below(), config.shoreBlock().getState(level, random, pos.below()));
                for (int i = 0; i < 4; i++) {
                    this.setBlock(level, new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()), Blocks.AIR.defaultBlockState());
                }
            }
        }
        if (density > config.shoreStartValue() + shore - 0.005) {
            if (level.getBlockState(pos.above()).is(AetherIIBlocks.AETHER_GRASS_BLOCK)) {
                this.setBlock(level, pos.below(), AetherIIBlocks.AETHER_DIRT.defaultBlockState());
                this.setBlock(level, pos, AetherIIBlocks.AETHER_GRASS_BLOCK.defaultBlockState());
                this.setBlock(level, pos.above(), Blocks.AIR.defaultBlockState());
            }
            if (level.getBlockState(pos.above(2)).is(AetherIIBlocks.AETHER_GRASS_BLOCK)) {
                this.setBlock(level, pos.below(), AetherIIBlocks.AETHER_DIRT.defaultBlockState());
                this.setBlock(level, pos, AetherIIBlocks.AETHER_DIRT.defaultBlockState());
                this.setBlock(level, pos.above(), AetherIIBlocks.AETHER_GRASS_BLOCK.defaultBlockState());
                this.setBlock(level, pos.above(2), Blocks.AIR.defaultBlockState());
            }
            if (level.getBlockState(pos.above()).is(AetherIITags.Blocks.LAKE_VEGETATION_REPLACEABLES) || level.getBlockState(pos.above(2)).is(AetherIITags.Blocks.LAKE_VEGETATION_REPLACEABLES)) {
                this.setBlock(level, pos.above(), Blocks.AIR.defaultBlockState());
            }
        }
    }

    public int calculateThickness(double barrier, int y, int height) {
        return (int) (y == height ? barrier / 2 : barrier);
    }

    public int calculateShoreThickness(double barrier, double waterfalls, int y, int height) {
        return waterfalls < 0.02 ? 0 : (int) (y == height ? barrier / 2 : barrier);
    }

    /**
     * The lake's noise functions compiled against the level's random state.
     */
    public record Samplers(DensitySampler.Bound lake, DensitySampler.Bound floor, DensitySampler.Bound barrier, DensitySampler.Bound waterfall, DensitySampler.Bound shore) {
        public static Samplers create(WorldGenLevel level, NoiseLakeConfiguration config) {
            return new Samplers(
                    DensitySampling.sampler(level, config.lakeNoise()),
                    DensitySampling.sampler(level, config.lakeFloorNoise()),
                    DensitySampling.sampler(level, config.lakeBarrierNoise()),
                    DensitySampling.sampler(level, config.lakeWaterfallNoise()),
                    DensitySampling.sampler(level, config.shoreNoise())
            );
        }
    }
}
