package com.aetherteam.aetherii.item.components;

import java.util.List;
import java.util.UUID;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.api.ItemReinforcement;
import com.aetherteam.aetherii.api.Mural;
import com.aetherteam.aetherii.api.registries.AetherIIRegistries;
import com.aetherteam.aetherii.blockentity.MuralSection;
import com.aetherteam.aetherii.entity.passive.Moa;
import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import java.util.function.UnaryOperator;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class AetherIIDataComponents {
        private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), builder.apply(DataComponentType.builder()).build());
    }

    public static void init() {}

    public static final DataComponentType<Moa.FeatherColor> FEATHER_COLOR = register("feather_color", b -> b.persistent(Moa.FeatherColor.CODEC).networkSynchronized(Moa.FeatherColor.STREAM_CODEC));
    public static final DataComponentType<MoaEggType> MOA_EGG_TYPE = register("moa_egg_type", b -> b.persistent(MoaEggType.CODEC).networkSynchronized(MoaEggType.STREAM_CODEC));
    public static final DataComponentType<MoaVariant> MOA_VARIANT = register("moa/variant", b -> b.persistent(MoaVariant.CODEC).networkSynchronized(MoaVariant.STREAM_CODEC));
    public static final DataComponentType<Integer> HEALING_STONE_CHARGES = register("healing_stone_charges", b -> b.persistent(ExtraCodecs.intRange(0, 5)).networkSynchronized(ByteBufCodecs.VAR_INT));
    public static final DataComponentType<ArmorStyle> ARMOR_STYLE = register("armor_style", b -> b.persistent(ArmorStyle.CODEC).networkSynchronized(ArmorStyle.STREAM_CODEC));
    public static final DataComponentType<TagKey<Item>> ARMOR_SET = register("armor_set", b -> b.persistent(TagKey.codec(Registries.ITEM)).networkSynchronized(TagKey.streamCodec(Registries.ITEM)));
    public static final DataComponentType<Integer> DARTS_LOADED = register("darts_loaded", b -> b.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));
    public static final DataComponentType<BuildupContents> BUILDUP_CONTENTS = register("buildup_contents", b -> b.persistent(BuildupContents.CODEC).networkSynchronized(BuildupContents.STREAM_CODEC));
    public static final DataComponentType<ResourceKey<ItemReinforcement>> ITEM_REINFORCEMENTS = register("item_reinforcement", b -> b.persistent(ResourceKey.codec(AetherIIRegistries.ITEM_REINFORCEMENT)).networkSynchronized(ResourceKey.streamCodec(AetherIIRegistries.ITEM_REINFORCEMENT)).cacheEncoding());
    public static final DataComponentType<ReinforcementTier> REINFORCEMENT_TIER = register("reinforcement_tier", b -> b.persistent(ReinforcementTier.CODEC).networkSynchronized(ReinforcementTier.STREAM_CODEC).cacheEncoding());
    public static final DataComponentType<Charms> CHARMS = register("charms", b -> b.persistent(Charms.CODEC).networkSynchronized(Charms.STREAM_CODEC).cacheEncoding());
    public static final DataComponentType<List<GuidebookEntryData>> GUIDEBOOK_ENTRY_DATA = register("guidebook_entry_data", b -> b.persistent(GuidebookEntryData.CODEC.listOf()).networkSynchronized(GuidebookEntryData.STREAM_CODEC.apply(ByteBufCodecs.list())).cacheEncoding());
    public static final DataComponentType<MuralSection> MURAL_SECTION = register("mural_section", b -> b.persistent(MuralSection.CODEC).networkSynchronized(MuralSection.STREAM_CODEC));
    public static final DataComponentType<Holder<Mural>> MURAL = register("mural", b -> b.persistent(Mural.CODEC).networkSynchronized(Mural.STREAM_CODEC));
    public static final DataComponentType<BlockState> BLOCK_STATE = register("block_state", b -> b.persistent(BlockState.CODEC));
    public static final DataComponentType<UUID> COMPANION_UUID = register("companion_uuid", b -> b.persistent(UUIDUtil.CODEC).networkSynchronized(UUIDUtil.STREAM_CODEC));
    public static final DataComponentType<CompoundTag> COMPANION_NBT = register("companion_tag", b -> b.persistent(CompoundTag.CODEC).networkSynchronized(ByteBufCodecs.COMPOUND_TAG));
    public static final DataComponentType<Boolean> MIMIC = register("mimic", b -> b.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));
    public static final DataComponentType<EngravedDisc> ENGRAVED_DISC = register("engraved_disc", b -> b.persistent(EngravedDisc.CODEC).networkSynchronized(EngravedDisc.STREAM_CODEC));
    public static final DataComponentType<StoredMusic> STORED_MUSIC = register("stored_music", b -> b.persistent(StoredMusic.CODEC).networkSynchronized(StoredMusic.STREAM_CODEC));
    public static final DataComponentType<BrokenStack> BROKEN_STACK = register("broken_stack", b -> b.persistent(BrokenStack.CODEC).networkSynchronized(BrokenStack.STREAM_CODEC));
}