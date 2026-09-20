package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;

public record AlkahestPoolConfiguration(IntProvider count, IntProvider radius, IntProvider offset) {
    public static final MapCodec<AlkahestPoolConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            IntProviders.CODEC.fieldOf("count").forGetter(AlkahestPoolConfiguration::count),
            IntProviders.CODEC.fieldOf("radius").forGetter(AlkahestPoolConfiguration::radius),
            IntProviders.CODEC.fieldOf("offset").forGetter(AlkahestPoolConfiguration::offset)
    ).apply(instance, AlkahestPoolConfiguration::new));
}
