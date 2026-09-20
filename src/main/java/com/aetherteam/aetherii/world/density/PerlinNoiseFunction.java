package com.aetherteam.aetherii.world.density;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.densityfunction.DensityBuffer;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensitySampler;
import net.minecraft.world.level.levelgen.densityfunction.DensityVolume;
import net.minecraft.world.level.levelgen.densityfunction.DfRewriteRule;
import net.minecraft.world.level.levelgen.densityfunction.SamplerContext;
import net.minecraft.world.level.levelgen.synth.Noise;
import net.minecraft.world.level.levelgen.synth.NoiseStack;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.minecraft.util.Interval;

import java.util.List;
import java.util.function.Function;

/**
 * Octave-summed Perlin noise with its own seed offset, independent of the noise settings' noise router.
 * <p>
 * Reproduces the pre-26.3 {@code PerlinNoise} octave stack (frequency doubling from {@code 2^firstOctave},
 * amplitudes normalized so that the octave weights sum to one). The random source is derived from the world seed
 * through the density function compile context, so every world gets its own layout.
 */
public record PerlinNoiseFunction(Parameters noise, double xzScale, double yScale, long seed) implements DensityFunction {
    public static final MapCodec<PerlinNoiseFunction> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Parameters.CODEC.fieldOf("noise").forGetter(PerlinNoiseFunction::noise),
            Codec.DOUBLE.fieldOf("xz_scale").forGetter(PerlinNoiseFunction::xzScale),
            Codec.DOUBLE.fieldOf("y_scale").forGetter(PerlinNoiseFunction::yScale),
            Codec.LONG.fieldOf("seed").forGetter(PerlinNoiseFunction::seed)
    ).apply(instance, PerlinNoiseFunction::new));

    @Override
    public DensitySampler compileSampler(DensityFunction.CompileContext context) {
        RandomSource random = context.createRandom(Identifier.fromNamespaceAndPath(AetherII.MODID, "perlin_noise/" + Long.toUnsignedString(this.seed)));
        return new Sampler(this.noise.create(random), this.xzScale, this.yScale);
    }

    @Override
    public DensityFunction rewriteChildren(DfRewriteRule rule) {
        return this;
    }

    @Override
    public Interval range() {
        return this.noise.range();
    }

    @Override
    public @DensityFunction.Axes int domainAxes() {
        int axes = DensityFunction.ALL_AXES;
        if (this.yScale == 0.0) {
            axes &= ~DensityFunction.AXIS_Y;
        }
        if (this.xzScale == 0.0) {
            axes &= ~(DensityFunction.AXIS_X | DensityFunction.AXIS_Z);
        }
        return axes;
    }

    @Override
    public MapCodec<PerlinNoiseFunction> codec() {
        return CODEC;
    }

    /**
     * First octave and per-octave amplitudes, serialized like the old {@code NormalNoise}.
     */
    public record Parameters(int firstOctave, DoubleList amplitudes) {
        public static final Codec<Parameters> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                Codec.INT.fieldOf("firstOctave").forGetter(Parameters::firstOctave),
                Codec.DOUBLE.listOf().xmap((Function<List<Double>, DoubleList>) DoubleArrayList::new, (list) -> list).fieldOf("amplitudes").forGetter(Parameters::amplitudes)
        ).apply(instance, Parameters::new));

        public Parameters(int firstOctave, double... amplitudes) {
            this(firstOctave, DoubleList.of(amplitudes));
        }

        public Noise create(RandomSource random) {
            PositionalRandomFactory factory = random.forkPositional();
            NoiseStack.Builder stack = NoiseStack.builder();
            int octaves = this.amplitudes.size();
            double inputFactor = Math.pow(2.0, this.firstOctave);
            double valueFactor = Math.pow(2.0, octaves - 1) / (Math.pow(2.0, octaves) - 1.0);
            for (int i = 0; i < octaves; i++) {
                double amplitude = this.amplitudes.getDouble(i);
                if (amplitude != 0.0) {
                    stack.add(new PerlinNoise(factory.fromHashOf("octave_" + (this.firstOctave + i))), inputFactor, (float) (amplitude * valueFactor));
                }
                inputFactor *= 2.0;
                valueFactor /= 2.0;
            }
            return stack.build();
        }

        public Interval range() {
            int octaves = this.amplitudes.size();
            double valueFactor = Math.pow(2.0, octaves - 1) / (Math.pow(2.0, octaves) - 1.0);
            double max = 0.0;
            for (int i = 0; i < octaves; i++) {
                max += Math.abs(this.amplitudes.getDouble(i)) * valueFactor * 2.0;
                valueFactor /= 2.0;
            }
            return Interval.ofSymmetric((float) max);
        }
    }

    record Sampler(Noise noise, double xzScale, double yScale) implements DensitySampler {
        @Override
        public void sampleVolume(SamplerContext context, DensityBuffer outputBuffer, DensityVolume volume) {
            outputBuffer.fill(0.0F);
            this.noise.addToVolume(outputBuffer, volume, this.xzScale, this.yScale, 1.0F);
        }

        @Override
        public float sampleValue(SamplerContext context, int blockX, int blockY, int blockZ) {
            return this.noise.get(blockX * this.xzScale, blockY * this.yScale, blockZ * this.xzScale);
        }
    }
}
