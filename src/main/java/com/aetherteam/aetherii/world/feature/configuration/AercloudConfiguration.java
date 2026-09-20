package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record AercloudConfiguration(int bounds, BlockStateProvider block) {
    public static final MapCodec<AercloudConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Codec.INT.fieldOf("bounds").forGetter(AercloudConfiguration::bounds),
            BlockStateProvider.DIRECT_CODEC.fieldOf("blocks").forGetter(AercloudConfiguration::block)
    ).apply(instance, AercloudConfiguration::new));
}