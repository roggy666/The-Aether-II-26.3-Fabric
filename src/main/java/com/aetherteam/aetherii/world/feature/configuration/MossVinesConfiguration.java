package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record MossVinesConfiguration(BlockStateProvider blockStateProvider) {
    public static final MapCodec<MossVinesConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("blocks").forGetter(MossVinesConfiguration::blockStateProvider)
    ).apply(instance, MossVinesConfiguration::new));
}
