package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record LargeShelfMushroomConfiguration(BlockStateProvider block, float baseRadius, int additionalRadius, int minY) {
    public static final MapCodec<LargeShelfMushroomConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("block").forGetter(LargeShelfMushroomConfiguration::block),
            Codec.FLOAT.fieldOf("base_radius").forGetter(LargeShelfMushroomConfiguration::baseRadius),
            Codec.INT.fieldOf("additional_radius").forGetter(LargeShelfMushroomConfiguration::additionalRadius),
            Codec.INT.fieldOf("min_y").forGetter(LargeShelfMushroomConfiguration::minY)
    ).apply(instance, LargeShelfMushroomConfiguration::new));
}