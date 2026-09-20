package com.aetherteam.aetherii.data.resources.registries;

import java.util.stream.DoubleStream;
import com.aetherteam.aetherii.AetherII;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class AetherIINoises {
    public static final ResourceKey<NormalNoise> TEMPERATURE = createKey("temperature");
    public static final ResourceKey<NormalNoise> VEGETATION = createKey("vegetation");
    public static final ResourceKey<NormalNoise> VEGETATION_RARE = createKey("vegetation_rare");
    public static final ResourceKey<NormalNoise> CONTINENTALNESS = createKey("continentalness");
    public static final ResourceKey<NormalNoise> EROSION = createKey("erosion");
    public static final ResourceKey<NormalNoise> CAVE_BIOMES = createKey("cave_biomes");
    public static final ResourceKey<NormalNoise> AMPLIFICATION = createKey("amplification");
    public static final ResourceKey<NormalNoise> RIDGES = createKey("ridges");
    public static final ResourceKey<NormalNoise> ELEVATION = createKey("elevation");
    public static final ResourceKey<NormalNoise> ELEVATION_SHATTERED = createKey("elevation_shattered");
    public static final ResourceKey<NormalNoise> CAVES = createKey("caves");
    public static final ResourceKey<NormalNoise> CAVE_THICKNESS = createKey("cave_thickness");
    public static final ResourceKey<NormalNoise> ARCTIC_SNOW = createKey("arctic_snow");
    public static final ResourceKey<NormalNoise> QUICKSOIL_IRRADIATED = createKey("quicksoil_irradiated");

    private static ResourceKey<NormalNoise> createKey(String name) {
        return ResourceKey.create(Registries.NOISE, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
    }

    public static void bootstrap(BootstrapContext<NormalNoise> context) {
        register(context, TEMPERATURE, -9, 1.5D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D);
        register(context, VEGETATION, -8, 1.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        register(context, VEGETATION_RARE, -8, 1.5D, 0.0D, 0.0D, 0.0D);
        register(context, CONTINENTALNESS, -9, 1.5D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D);
        register(context, EROSION, -9, 1.5D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        register(context, CAVE_BIOMES, -8, 1.5D, 0.0D, 1.0D, 0.0D, 0.0D);
        register(context, AMPLIFICATION, -7, 1.0D, 2.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        register(context, RIDGES, -9, 1.5D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        register(context, ELEVATION, -8, 1.0D, 0.2D, 0.0D, 0.0D, 0.0D);
        register(context, ELEVATION_SHATTERED, -7, 1.0D, 0.75D, 0.0D, 0.0D, 0.0D);
        register(context, CAVES, -6, 1.0, 2.0, 1.0, 0.0, 0.0, 0.0, 0.0);
        register(context, CAVE_THICKNESS, -7, 0.3, 0.0, 0.0);
        register(context, ARCTIC_SNOW, -5, 1.0, 0.0, 0.5, 0.5, 0.0, 0.0);
        register(context, QUICKSOIL_IRRADIATED, -4, 1.0, 0.5, 0.0, 0.0, 0.0);
    }

    public static void register(BootstrapContext<NormalNoise> context, ResourceKey<NormalNoise> key, int firstOctave, double firstAmplitude, double... amplitudes) {
        context.register(key, NormalNoise.createParity(firstOctave, DoubleStream.concat(DoubleStream.of(firstAmplitude), DoubleStream.of(amplitudes)).toArray()));
    }
}