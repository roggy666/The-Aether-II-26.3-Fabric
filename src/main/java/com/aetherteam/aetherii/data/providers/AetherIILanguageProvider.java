package com.aetherteam.aetherii.data.providers;

import com.aetherteam.aetherii.entity.passive.Moa;
import com.aetherteam.nitrogen.data.providers.NitrogenLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public abstract class AetherIILanguageProvider extends NitrogenLanguageProvider {
    public AetherIILanguageProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries, String id) {
        super(output, registries, id);
    }

    // Direct-registry overloads (Nitrogen's helpers still take Suppliers from the NeoForge days)
    public void addBlock(Block block, String name) {
        this.add(block, name);
    }

    public void addItem(Item item, String name) {
        this.add(item, name);
    }

    public void addEffect(Holder<MobEffect> effect, String name) {
        this.add(effect.value(), name);
    }

    public void addEffectDesc(Holder<MobEffect> effect, String name) {
        this.add(effect.value().getDescriptionId() + ".desc", name);
    }

    public void addEntityType(EntityType<?> entityType, String name) {
        this.add(entityType, name);
    }

    public void addContainerType(MenuType<?> menuType, String name) {
        this.add("menu." + BuiltInRegistries.MENU.getKey(menuType).getNamespace() + "." + BuiltInRegistries.MENU.getKey(menuType).getPath(), name);
    }

    public void addDiscDesc(Item item, String name) {
        this.add(item.getDescriptionId() + ".desc", name);
    }

    public void addEffectDarts(Item key, String effect, String name) {
        this.add(key.getDescriptionId() + ".effect." + effect, name);
    }

    public void addKeratinColor(Moa.KeratinColor color, String name) {
        this.addItemTooltip("moa_egg.keratin_color." + color.getSerializedName(), name);
    }

    public void addEyeColor(Moa.EyeColor color, String name) {
        this.addItemTooltip("moa_egg.eye_color." + color.getSerializedName(), name);
    }

    public void addFeatherColor(Moa.FeatherColor color, String name) {
        this.addItemTooltip("moa_egg.feather_color." + color.getSerializedName(), name);
    }

    public void addFeatherShape(Moa.FeatherShape shape, String name) {
        this.addItemTooltip("moa_egg.feather_shape." + shape.getSerializedName(), name);
    }

    public void addEffectDarts(Supplier<? extends Item> key, String effect, String name) {
        this.add(key.get().getDescriptionId() + ".effect." + effect, name);
    }

    public void addDamageTypeTooltip(String path, String name) {
        this.addItemTooltip("damage." + path, name);
    }

    public void addItemTooltip(String path, String name) {
        this.addTooltip("item." + path, name);
    }

    public void addTooltip(String path, String name) {
        this.add(this.id + ".tooltip." + path, name);
    }

    public void addAccessorySlot(String path, String name) {
        this.add("accessories.slot." + this.id + "." + path, name);
    }

    public void addAttribute(Attribute attribute, String name) {
        this.add(attribute.getDescriptionId(), name);
    }

    public void addMusic(String songName, String name) {
        this.add(this.id + ".music." + songName, name);
    }

    public void addJukeboxSong(String songName, String name) {
        this.add("jukebox_song." + this.id + "." + songName, name);
    }

    public void addBestiaryName(EntityType<?> entityType, String description) {
        this.add(this.id + ".guidebook_bestiary.name." + entityType.getDescriptionId(), description);
    }

    public void addBestiarySlotName(EntityType<?> entityType, String description) {
        this.add(this.id + ".guidebook_bestiary.slot_name." + entityType.getDescriptionId(), description);
    }

    public void addBestiarySlotSubtitle(EntityType<?> entityType, String description) {
        this.add(this.id + ".guidebook_bestiary.slot_subtitle." + entityType.getDescriptionId(), description);
    }

    public void addBestiaryDescription(EntityType<?> entityType, String description) {
        this.add(this.id + ".guidebook_bestiary.description." + entityType.getDescriptionId(), description);
    }

    public void addEffectsDescription(MobEffect effect, String description) {
        this.add(this.id + ".guidebook_effects.description." + effect.getDescriptionId(), description);
    }
}