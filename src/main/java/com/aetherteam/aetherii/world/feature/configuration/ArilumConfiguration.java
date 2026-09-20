package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record ArilumConfiguration(BlockStateProvider grassProvider, BlockStateProvider plantProvider, IntProvider height, IntProvider depth) {
    public static final MapCodec<ArilumConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("grass_provider").forGetter(ArilumConfiguration::grassProvider),
            BlockStateProvider.DIRECT_CODEC.fieldOf("plant_provider").forGetter(ArilumConfiguration::plantProvider),
            IntProviders.CODEC.fieldOf("height").forGetter(ArilumConfiguration::height),
            IntProviders.CODEC.fieldOf("depth").forGetter(ArilumConfiguration::depth)
    ).apply(instance, ArilumConfiguration::new));
}
