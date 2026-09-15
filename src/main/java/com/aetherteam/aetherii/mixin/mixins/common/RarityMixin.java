package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.mixin.mixins.common.invoker.RarityInvoker;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

/**
 * Adds the Aether II rarities to the vanilla enum. The mixin static initializer runs after vanilla's, so the
 * codecs (which snapshot {@code values()}) have to be rebuilt here as well. Ids equal ordinals, like vanilla.
 */
@Mixin(Rarity.class)
public abstract class RarityMixin {
    @Shadow
    @Final
    @Mutable
    private static Rarity[] $VALUES;

    @Shadow
    @Final
    @Mutable
    public static Codec<Rarity> CODEC;

    @Shadow
    @Final
    @Mutable
    public static IntFunction<Rarity> BY_ID;

    @Shadow
    @Final
    @Mutable
    public static StreamCodec<ByteBuf, Rarity> STREAM_CODEC;

    static {
        List<Rarity> rarities = new ArrayList<>(Arrays.asList($VALUES));
        rarities.add(RarityInvoker.aether_ii$create("AETHER_II_CURRENCY", rarities.size(), rarities.size(), "aether_ii:currency", ChatFormatting.WHITE));
        rarities.add(RarityInvoker.aether_ii$create("AETHER_II_TREASURE", rarities.size(), rarities.size(), "aether_ii:treasure", ChatFormatting.GOLD));
        rarities.add(RarityInvoker.aether_ii$create("AETHER_II_UPGRADED", rarities.size(), rarities.size(), "aether_ii:upgraded", ChatFormatting.AQUA));
        $VALUES = rarities.toArray(new Rarity[0]);

        Rarity[] values = $VALUES;
        CODEC = StringRepresentable.fromValues(() -> values);
        BY_ID = ByIdMap.continuous(Rarity::ordinal, values, ByIdMap.OutOfBoundsStrategy.ZERO);
        STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Rarity::ordinal);
    }
}
