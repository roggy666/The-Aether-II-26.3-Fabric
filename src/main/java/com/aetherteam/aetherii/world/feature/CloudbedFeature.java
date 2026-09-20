package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.levelgen.densityfunction.DensitySampler;
import com.aetherteam.aetherii.world.density.DensitySampling;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.feature.configuration.CloudbedConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;

public class CloudbedFeature implements Feature {

    public static final MapCodec<CloudbedFeature> CODEC = CloudbedConfiguration.CODEC.xmap(CloudbedFeature::new, CloudbedFeature::config);
    private final CloudbedConfiguration config;

    public CloudbedFeature(CloudbedConfiguration config) {
        this.config = config;
    }

    public CloudbedConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<CloudbedFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        CloudbedConfiguration config = this.config;

        DensitySampler.Bound cloudNoise = DensitySampling.sampler(level, config.cloudNoise());
        DensitySampler.Bound yOffsetNoise = DensitySampling.sampler(level, config.yOffset());

        // This should be placed, once per chunk
        int chunkX = origin.getX() - (origin.getX() % 16);
        int chunkZ = origin.getZ() - (origin.getZ() % 16);
        // Place blocks across the entire chunk
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                // calculate new coords based on the for loops' values
                int xCoord = chunkX + x;
                int zCoord = chunkZ + z;
                // The main cloud noise is what is used for the distinction of gaps and non-gaps
                double cloudCalc = cloudNoise.sampleValue(xCoord, config.yLevel(), zCoord);
                // A Y offset is then calculated and applied using a second, smoother and larger noise
                double offsetCalc = yOffsetNoise.sampleValue(xCoord, config.yLevel(), zCoord);
                float realOffset =  cosineInterp((float) Mth.inverseLerp(offsetCalc, -0.5, 0.5), 0F, (float) config.maxYOffset());
                // We don't need to, and shouldn't, generate anything if the cloud noise value is below zero
                if (cloudCalc >= 0) {
                    // Interpolate for some extra smoothness
                    float realCloud = cosineInterp((float) Mth.clamp(cloudCalc, 0, 1), 0, 1);
                    // Calculate how many blocks up from the main y offset plane should be generated
                    float blocksUp = Mth.lerp(realCloud, 0F, (float) config.cloudRadius()) + realOffset;
                    // Calculate how many blocks down from the main y offset plane should be generated
                    float blocksDown = Mth.lerp(realCloud, 0F, (float) config.cloudRadius() - 1F) - realOffset;
                    // Floor these values and then place the blocks
                    BlockState state = config.block().getState(level, random, new BlockPos(xCoord, config.yLevel(), zCoord));
                    for (int i = Mth.floor(-blocksDown); i <= Mth.floor(blocksUp); i++) {
                        int y = Mth.clamp(config.yLevel() + i, level.getMinY(), level.getMaxY());
                        BlockPos pos = new BlockPos(xCoord, y, zCoord);
                        if (config.predicate().test(level, pos)) {
                            this.setBlock(level, pos, state);
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static float cosineInterp(float progress, float start, float end) {
        return (-Mth.cos((float) (Math.PI * progress)) + 1F) * 0.5F * (end - start) + start;
    }
}