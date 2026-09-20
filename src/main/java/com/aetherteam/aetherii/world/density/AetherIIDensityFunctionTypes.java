package com.aetherteam.aetherii.world.density;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;

public class AetherIIDensityFunctionTypes {
    public static final MapCodec<? extends DensityFunction> PERLIN_NOISE = Registry.register(
            BuiltInRegistries.DENSITY_FUNCTION_TYPE,
            Identifier.fromNamespaceAndPath(AetherII.MODID, "perlin_noise"),
            PerlinNoiseFunction.CODEC
    );

    public static void init() {}
}