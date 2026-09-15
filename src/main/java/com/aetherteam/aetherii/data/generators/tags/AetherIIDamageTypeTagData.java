package com.aetherteam.aetherii.data.generators.tags;

import net.minecraft.resources.ResourceKey;
import net.minecraft.data.tags.TagAppender;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.data.resources.registries.AetherIIDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.concurrent.CompletableFuture;

public class AetherIIDamageTypeTagData extends FabricTagsProvider<DamageType> {
    public AetherIIDamageTypeTagData(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.DAMAGE_TYPE, registries);
    }

    protected KeyAppender<DamageType> tagOf(TagKey<DamageType> key) {
        return new KeyAppender<>(this.builder(key));
    }

    /** Key-based appender with the varargs helpers the NeoForge {@code KeyTagProvider} offered. */
    protected record KeyAppender<T>(TagAppender<T> appender) {
        @SafeVarargs
        public final KeyAppender<T> add(ResourceKey<T>... keys) {
            for (ResourceKey<T> key : keys) this.appender.add(key);
            return this;
        }

        @SafeVarargs
        public final KeyAppender<T> addTags(TagKey<T>... tags) {
            for (TagKey<T> tag : tags) this.appender.addTag(tag);
            return this;
        }

        public KeyAppender<T> addTag(TagKey<T> tag) {
            this.appender.addTag(tag);
            return this;
        }

        public KeyAppender<T> addOptional(ResourceKey<T> key) {
            this.appender.addOptional(key);
            return this;
        }

        public KeyAppender<T> addOptionalTag(TagKey<T> tag) {
            this.appender.addOptionalTag(tag);
            return this;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addTags(HolderLookup.Provider provider) {
        // Aether II
        this.tagOf(AetherIITags.DamageTypes.TYPED).add(
                DamageTypes.PLAYER_ATTACK,
                DamageTypes.ARROW,
                DamageTypes.TRIDENT,
                DamageTypes.THROWN
        );

        // Vanilla
        this.tagOf(DamageTypeTags.BYPASSES_ARMOR).add(
                AetherIIDamageTypes.WOUND,
                AetherIIDamageTypes.FRACTURE,
                AetherIIDamageTypes.TOXIN,
                AetherIIDamageTypes.VENOM,
                AetherIIDamageTypes.IMMOLATION
        );
        this.tagOf(DamageTypeTags.BYPASSES_SHIELD).add(
                AetherIIDamageTypes.ALKAHEST,
                AetherIIDamageTypes.CARRION_SPROUT
        );
        this.tagOf(DamageTypeTags.IS_FIRE).add(
                AetherIIDamageTypes.IMMOLATION
        );
        this.tagOf(DamageTypeTags.IS_LIGHTNING).add(
                AetherIIDamageTypes.CHARGED,
                AetherIIDamageTypes.SHOCK
        );
        this.tagOf(DamageTypeTags.IGNITES_ARMOR_STANDS).add(
                AetherIIDamageTypes.IMMOLATION
        );
        this.tagOf(DamageTypeTags.NO_KNOCKBACK).add(
                AetherIIDamageTypes.PLAYER_AOE_NO_KNOCKBACK,
                AetherIIDamageTypes.WOUND,
                AetherIIDamageTypes.FRACTURE,
                AetherIIDamageTypes.TOXIN,
                AetherIIDamageTypes.VENOM,
                AetherIIDamageTypes.IMMOLATION,
                AetherIIDamageTypes.ALKAHEST,
                AetherIIDamageTypes.CARRION_SPROUT
        );
        this.tagOf(DamageTypeTags.IS_PLAYER_ATTACK).add(
                AetherIIDamageTypes.PLAYER_AOE,
                AetherIIDamageTypes.PLAYER_AOE_NO_KNOCKBACK
        );
        this.tagOf(DamageTypeTags.PANIC_CAUSES).add(
                AetherIIDamageTypes.WOUND,
                AetherIIDamageTypes.TOXIN,
                AetherIIDamageTypes.VENOM,
                AetherIIDamageTypes.CHARGED,
                AetherIIDamageTypes.IMMOLATION,
                AetherIIDamageTypes.SHOCK,
                AetherIIDamageTypes.CRUSH
        );
        this.tagOf(DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES).add(
                AetherIIDamageTypes.ALKAHEST
        );

        // NeoForge
        this.tagOf(TagKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("c", "is_poison"))).add(
                AetherIIDamageTypes.TOXIN,
                AetherIIDamageTypes.VENOM
        );
        this.tagOf(TagKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("c", "is_environment"))).add(
                AetherIIDamageTypes.ALKAHEST
        );
        this.tagOf(TagKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("c", "is_physical"))).add(
                AetherIIDamageTypes.WOUND,
                AetherIIDamageTypes.FRACTURE,
                AetherIIDamageTypes.CARRION_SPROUT,
                AetherIIDamageTypes.CRUSH
        );
    }
}