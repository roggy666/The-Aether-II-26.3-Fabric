package com.aetherteam.aetherii.data.resources.builders.worldgen.holyisles;

import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.data.resources.builders.worldgen.AetherIIDensityFunctionBuilders;
import com.aetherteam.aetherii.data.resources.registries.AetherIIDensityFunctions;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.*;

import java.util.List;

public class HolyIslesNoiseBuilders extends AetherIIDensityFunctionBuilders {
    public static NoiseGeneratorSettings holyIslesNoiseSettings(HolderGetter<DensityFunction> function, HolderGetter<Biome> biomes) {
        BlockState holystone = AetherIIBlocks.HOLYSTONE.defaultBlockState();
        return new NoiseGeneratorSettings(
                new NoiseSettings(0, 384), // noiseSettings
                holystone, // defaultBlock
                Blocks.WATER.defaultBlockState(), // defaultFluid
                makeNoiseRouter(function), // noiseRouter
                Holder.direct(HolyIslesSurfaceBuilders.surfaceRules(biomes)), // materialRule
                List.of(), // spawnTarget
                -64, // seaLevel
                false, // disableMobGeneration
                Optional.empty(), // aquifers (disabled)
                false, // useLegacyRandomSource
                NoiseGeneratorSettings.DebugFunctions.EMPTY
        );
    }

    private static NoiseRouter makeNoiseRouter(HolderGetter<DensityFunction> function) {
        return createNoiseRouter(function, getFunction(function, AetherIIDensityFunctions.FINAL_ISLANDS));
    }

    private static NoiseRouter createNoiseRouter(HolderGetter<DensityFunction> function, DensityFunction finalDensity) {
        DensityFunction temperature = getFunction(function, AetherIIDensityFunctions.TEMPERATURE);
        DensityFunction vegetation = getFunction(function, AetherIIDensityFunctions.VEGETATION_RARITY_MAPPER);
        DensityFunction continentalness = getFunction(function, AetherIIDensityFunctions.CONTINENTS);
        DensityFunction erosion = getFunction(function, AetherIIDensityFunctions.EROSION);
        DensityFunction depth = getFunction(function, AetherIIDensityFunctions.CAVE_BIOMES_RARITY_MAPPER);
        DensityFunction ridges = getFunction(function, AetherIIDensityFunctions.LAKES_NOISE);
        // The preliminary surface level of the islands (top of the base island density), sampled per chunk column like vanilla does.
        DensityFunction chunkSurfaceLevel = DensityFunctions.interpolated(getFunction(function, AetherIIDensityFunctions.CONTINENTS_HEIGHTMAP), 16, 1);
        return new NoiseRouter(
                temperature, // temperature
                vegetation, // vegetation
                continentalness, // continents
                erosion, // erosion
                depth, // depth
                ridges, // ridges
                chunkSurfaceLevel, // chunk surface level
                finalDensity); // final density
    }
}