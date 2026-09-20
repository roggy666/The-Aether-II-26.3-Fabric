package com.aetherteam.aetherii.world.density;

import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensitySampler;
import net.minecraft.world.level.levelgen.densityfunction.SamplerContext;

/**
 * Samples density functions outside the chunk generator, e.g. from features and structure processors.
 */
public final class DensitySampling {
    private DensitySampling() {
    }

    /**
     * Compiles (or reuses) the sampler for a density function using the level's random state, so the noise is seeded
     * consistently with terrain generation.
     */
    public static DensitySampler.Bound sampler(WorldGenLevel level, DensityFunction function) {
        return level.getLevel().getChunkSource().randomState().getSampler(function).bind(SamplerContext.EMPTY_UNCACHED);
    }
}
