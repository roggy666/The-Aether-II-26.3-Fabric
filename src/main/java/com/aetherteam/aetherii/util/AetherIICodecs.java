package com.aetherteam.aetherii.util;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Map;

public final class AetherIICodecs {
    private AetherIICodecs() {
    }

    /** Save-data migration: block entities written before 26.3 store the state as {@code Name}/{@code Properties}. */
    private static final Codec<BlockState> LEGACY_BLOCK_STATE = RecordCodecBuilder.create((instance) -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("Name").forGetter(BlockState::getBlock),
            Codec.unboundedMap(Codec.STRING, Codec.STRING).optionalFieldOf("Properties", Map.of()).forGetter((state) -> Map.of())
    ).apply(instance, AetherIICodecs::legacyBlockState));

    /**
     * Reads block states in the current ({@code id}/{@code properties} or plain id) format and falls back to the legacy
     * format; always writes the current one.
     */
    public static final Codec<BlockState> BLOCK_STATE = Codec.either(BlockState.CODEC, LEGACY_BLOCK_STATE)
            .xmap((either) -> either.map((state) -> state, (state) -> state), Either::left);

    private static BlockState legacyBlockState(Block block, Map<String, String> properties) {
        BlockState state = block.defaultBlockState();
        StateDefinition<Block, BlockState> definition = block.getStateDefinition();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            Property<?> property = definition.getProperty(entry.getKey());
            if (property != null) {
                state = setLegacyValue(state, property, entry.getValue());
            }
        }
        return state;
    }

    private static <T extends Comparable<T>> BlockState setLegacyValue(BlockState state, Property<T> property, String value) {
        return property.getValue(value).map((parsed) -> state.setValue(property, parsed)).orElse(state);
    }
}
