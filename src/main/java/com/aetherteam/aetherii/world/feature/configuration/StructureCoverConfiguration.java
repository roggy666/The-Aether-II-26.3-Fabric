package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.world.feature.StructureCoverFeature;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record StructureCoverConfiguration(BlockStateProvider block, BlockStateProvider secondaryBlock, int blockTransitionHeight, DensityFunction noise, float radius, int height, float inclineFactor, float scatterFactor, StructureCoverFeature.CalculationType calculationType) {
    public static final MapCodec<StructureCoverConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("block").forGetter(StructureCoverConfiguration::block),
            BlockStateProvider.DIRECT_CODEC.fieldOf("secondary_block").forGetter(StructureCoverConfiguration::secondaryBlock),
            Codec.INT.fieldOf("block_transition_height").forGetter(StructureCoverConfiguration::blockTransitionHeight),
            DensityFunction.CODEC.fieldOf("noise").forGetter(StructureCoverConfiguration::noise),
            Codec.FLOAT.fieldOf("radius").forGetter(StructureCoverConfiguration::radius),
            Codec.INT.fieldOf("height").forGetter(StructureCoverConfiguration::height),
            Codec.FLOAT.fieldOf("incline_factor").forGetter(StructureCoverConfiguration::inclineFactor),
            Codec.FLOAT.fieldOf("scatter_factor").forGetter(StructureCoverConfiguration::scatterFactor),
            StructureCoverFeature.CalculationType.CODEC.fieldOf("calculation_type").forGetter(StructureCoverConfiguration::calculationType)
    ).apply(instance, StructureCoverConfiguration::new));
}