package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record NoiseLakeConfiguration(DensityFunction lakeNoise, DensityFunction lakeFloorNoise, DensityFunction lakeBarrierNoise, DensityFunction lakeWaterfallNoise, double noiseStartValue, ConstantInt height, BlockStateProvider underwaterBlock, double shoreStartValue, BlockStateProvider shoreBlock, DensityFunction shoreNoise, BlockStateProvider iceBlock, boolean frozen) {
    public static final MapCodec<NoiseLakeConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            DensityFunction.CODEC.fieldOf("lake_noise").forGetter(NoiseLakeConfiguration::lakeNoise),
            DensityFunction.CODEC.fieldOf("lake_floor_noise").forGetter(NoiseLakeConfiguration::lakeFloorNoise),
            DensityFunction.CODEC.fieldOf("lake_barrier_noise").forGetter(NoiseLakeConfiguration::lakeBarrierNoise),
            DensityFunction.CODEC.fieldOf("lake_waterfall_noise").forGetter(NoiseLakeConfiguration::lakeWaterfallNoise),
            Codec.DOUBLE.fieldOf("noise_start_value").forGetter(NoiseLakeConfiguration::noiseStartValue),
            ConstantInt.MAP_CODEC.fieldOf("height").forGetter(NoiseLakeConfiguration::height),
            BlockStateProvider.DIRECT_CODEC.fieldOf("underwater_block").forGetter(NoiseLakeConfiguration::underwaterBlock),
            Codec.DOUBLE.fieldOf("shore_start_value").forGetter(NoiseLakeConfiguration::shoreStartValue),
            BlockStateProvider.DIRECT_CODEC.fieldOf("shore_block").forGetter(NoiseLakeConfiguration::shoreBlock),
            DensityFunction.CODEC.fieldOf("shore_noise").forGetter(NoiseLakeConfiguration::shoreNoise),
            BlockStateProvider.DIRECT_CODEC.fieldOf("ice_block").forGetter(NoiseLakeConfiguration::iceBlock),
            Codec.BOOL.fieldOf("frozen").orElse(false).forGetter(NoiseLakeConfiguration::frozen)
    ).apply(instance, NoiseLakeConfiguration::new));
}