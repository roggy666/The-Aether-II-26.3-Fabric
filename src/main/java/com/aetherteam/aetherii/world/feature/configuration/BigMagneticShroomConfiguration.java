package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Optional;

public record BigMagneticShroomConfiguration(BlockStateProvider capProvider, BlockStateProvider bottomCapProvider, BlockStateProvider stemProvider, Optional<BlockStateProvider> groundProvider, FeatureSize minimumSize, boolean tall) {
    public static final MapCodec<BigMagneticShroomConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("cap_provider").forGetter(BigMagneticShroomConfiguration::capProvider),
            BlockStateProvider.DIRECT_CODEC.fieldOf("bottom_cap_provider").forGetter(BigMagneticShroomConfiguration::bottomCapProvider),
            BlockStateProvider.DIRECT_CODEC.fieldOf("stem_provider").forGetter(BigMagneticShroomConfiguration::stemProvider),
            BlockStateProvider.DIRECT_CODEC.optionalFieldOf("ground_feature").forGetter(BigMagneticShroomConfiguration::groundProvider),
            FeatureSize.CODEC.fieldOf("minimum_size").forGetter(BigMagneticShroomConfiguration::minimumSize),
            Codec.BOOL.fieldOf("large").forGetter(BigMagneticShroomConfiguration::tall)
    ).apply(instance, BigMagneticShroomConfiguration::new));
}
