package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;

public record DungeonEntranceConfiguration(Identifier path, int xOffset, int zOffset) {
    public static final MapCodec<DungeonEntranceConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Identifier.CODEC.fieldOf("path").forGetter(DungeonEntranceConfiguration::path),
            Codec.intRange(-64, 64).fieldOf("x_offset").forGetter(DungeonEntranceConfiguration::xOffset),
            Codec.intRange(-64, 64).fieldOf("z_offset").forGetter(DungeonEntranceConfiguration::zOffset)
    ).apply(instance, DungeonEntranceConfiguration::new));
}