package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public record MergedConfiguration(List<Holder<PlacedFeature>> features) {
    public static final MapCodec<MergedConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            PlacedFeature.CODEC.listOf().fieldOf("features").forGetter(MergedConfiguration::features)
    ).apply(instance, MergedConfiguration::new));

}
