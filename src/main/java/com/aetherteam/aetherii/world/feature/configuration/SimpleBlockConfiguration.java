package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

/**
 * A single block state provider, for the mod's plant features (vanilla folded its equivalent into {@code SimpleBlockFeature}).
 */
public record SimpleBlockConfiguration(BlockStateProvider toPlace) {
    public static final MapCodec<SimpleBlockConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("to_place").forGetter(SimpleBlockConfiguration::toPlace)
    ).apply(instance, SimpleBlockConfiguration::new));
}
