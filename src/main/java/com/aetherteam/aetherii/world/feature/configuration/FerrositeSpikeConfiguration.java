package com.aetherteam.aetherii.world.feature.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record FerrositeSpikeConfiguration(BlockStateProvider block, float baseRadius, int additionalRadius, TagKey<Block> validBlocks) {
    public static final MapCodec<FerrositeSpikeConfiguration> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("block").forGetter(FerrositeSpikeConfiguration::block),
            Codec.FLOAT.fieldOf("base_radius").forGetter(FerrositeSpikeConfiguration::baseRadius),
            Codec.INT.fieldOf("additional_radius").forGetter(FerrositeSpikeConfiguration::additionalRadius),
            TagKey.codec(Registries.BLOCK).fieldOf("valid_blocks").forGetter(FerrositeSpikeConfiguration::validBlocks)
    ).apply(instance, FerrositeSpikeConfiguration::new));
}