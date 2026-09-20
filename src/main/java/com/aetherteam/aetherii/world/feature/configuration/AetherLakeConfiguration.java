package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record AetherLakeConfiguration(IntProvider shrinkScale, BlockStateProvider fluid, BlockStateProvider top) {
    public static final MapCodec<AetherLakeConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            IntProviders.CODEC.fieldOf("shrink_scale").forGetter(AetherLakeConfiguration::shrinkScale),
            BlockStateProvider.DIRECT_CODEC.fieldOf("fluid").forGetter(AetherLakeConfiguration::fluid),
            BlockStateProvider.DIRECT_CODEC.fieldOf("top").forGetter(AetherLakeConfiguration::top)
    ).apply(instance, AetherLakeConfiguration::new));
}