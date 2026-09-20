package com.aetherteam.aetherii.world;

import com.aetherteam.aetherii.world.surfacerule.NoisePalette3DPlacementRule;
import com.aetherteam.aetherii.world.density.PerlinNoiseFunction;
import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.material.rule.RuleEvaluator;
import net.minecraft.world.level.levelgen.synth.Noise;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/** Compares the optimized surface rule with its pre-optimization implementation. */
public final class WorldgenRegressionTest {
    private static volatile int sink;
    private static BlockState original(NoisePalette3DPlacementRule rule, int x, int y, int z) {
        PerlinNoise noise = new PerlinNoise(new XoroshiroRandomSource(0));
        List<BlockState> palette = new ArrayList<>();
        for (int i = 0; i < rule.spotRatio(); i++) palette.add(rule.spot());
        for (int i = 0; i < rule.emptyRatio(); i++) palette.add(null);
        double value = noise.get(x * rule.noiseFreq(), y * rule.noiseFreq(), z * rule.noiseFreq());
        int index = Mth.lerpDiscrete((float) Mth.clamp(value * 0.5 + 0.5, 0, 1), 0, palette.size() - 1);
        return palette.get(index);
    }

    public static void main(String[] args) throws Exception {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
        int[][] ratios = {{3,10}, {9,20}, {16,12}, {0,10}, {10,0}};
        double[] frequencies = {0.045, 0.075, 0.05};
        int checks = 0;
        for (int[] ratio : ratios) for (double frequency : frequencies) {
            var rule = new NoisePalette3DPlacementRule(Blocks.STONE.defaultBlockState(), ratio[0], ratio[1], frequency);
            var optimized = rule.compile(null);
            for (int i = 0; i < 10000; i++) {
                int x = (i * 7919 % 60000001) - 30000000;
                int y = (i * 31 % 512) - 64;
                int z = (i * 3571 % 60000001) - 30000000;
                if (original(rule, x, y, z) != optimized.tryApply(x, y, z)) throw new AssertionError("Surface changed at " + x + "," + y + "," + z);
                checks++;
            }
        }
        // Perlin noise functions are compiled per random state since 26.3; the octave stack must be deterministic per seed
        // no matter which worldgen worker builds it.
        var parameters = new PerlinNoiseFunction.Parameters(-7, 1.0, 0.5, 0.25);
        try (var workers = Executors.newFixedThreadPool(8)) {
            var jobs = new ArrayList<java.util.concurrent.Callable<Void>>();
            for (int i = 0; i < 64; i++) jobs.add(() -> {
                for (long seed = 0; seed < 100; seed++) {
                    Noise first = parameters.create(new XoroshiroRandomSource(seed));
                    Noise second = parameters.create(new XoroshiroRandomSource(seed));
                    if (first.get(1.5, 2.5, 3.5) != second.get(1.5, 2.5, 3.5)) throw new AssertionError("Perlin noise stack is not deterministic for seed " + seed);
                }
                return null;
            });
            for (var result : workers.invokeAll(jobs)) result.get();
        }
        System.out.println("Surface equivalence checks passed: " + checks + "; concurrent noise construction passed");
        var rule = new NoisePalette3DPlacementRule(Blocks.STONE.defaultBlockState(), 3, 10, 0.045);
        var optimized = rule.compile(null);
        for (int i = 0; i < 3; i++) { measure(rule, optimized, false); measure(rule, optimized, true); }
        long oldNs = Long.MAX_VALUE, newNs = Long.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            oldNs = Math.min(oldNs, measure(rule, optimized, false));
            newNs = Math.min(newNs, measure(rule, optimized, true));
        }
        System.out.printf("Isolated surface benchmark (100000 positions): old %.2f ms, new %.2f ms, %.2fx; not a chunk benchmark%n", oldNs / 1e6, newNs / 1e6, (double) oldNs / newNs);
    }

    private static long measure(NoisePalette3DPlacementRule rule, RuleEvaluator optimized, boolean fast) {
        long start = System.nanoTime();
        int count = 0;
        for (int i = 0; i < 100000; i++) {
            int x = i % 1024 - 512, y = i % 256, z = i / 1024 - 512;
            if ((fast ? optimized.tryApply(x, y, z) : original(rule, x, y, z)) != null) count++;
        }
        sink = count;
        return System.nanoTime() - start;
    }
}
