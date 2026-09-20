package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CraterConfiguration(UniformInt radius, DensityFunction noise, BlockStateProvider exteriorBlock, BlockStateProvider interiorBlock, BlockStateProvider craterBlock) {
    public static final MapCodec<CraterConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            UniformInt.MAP_CODEC.fieldOf("radius").forGetter(CraterConfiguration::radius),
            DensityFunction.CODEC.fieldOf("noise").forGetter(CraterConfiguration::noise),
            BlockStateProvider.DIRECT_CODEC.fieldOf("exterior_block").forGetter(CraterConfiguration::exteriorBlock),
            BlockStateProvider.DIRECT_CODEC.fieldOf("interior_block").forGetter(CraterConfiguration::interiorBlock),
            BlockStateProvider.DIRECT_CODEC.fieldOf("crater_block").forGetter(CraterConfiguration::craterBlock)
    ).apply(instance, CraterConfiguration::new));
}