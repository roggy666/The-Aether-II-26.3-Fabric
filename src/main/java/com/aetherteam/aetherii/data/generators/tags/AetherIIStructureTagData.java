package com.aetherteam.aetherii.data.generators.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.core.registries.Registries;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.data.resources.registries.AetherIIStructures;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class AetherIIStructureTagData extends FabricTagsProvider<Structure> {
    public AetherIIStructureTagData(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.STRUCTURE, registries);
    }

    protected KeyAppender<Structure> tagOf(TagKey<Structure> key) {
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
        this.tagOf(AetherIITags.Structures.OUTPOSTS).add(
                AetherIIStructures.OUTPOST
        );
        this.tagOf(AetherIITags.Structures.CAMPS).add(
                AetherIIStructures.CAMP_HIGHFIELDS,
                AetherIIStructures.CAMP_MAGNETIC,
                AetherIIStructures.CAMP_ARCTIC
        );
        this.tagOf(AetherIITags.Structures.DUNGEONS).add(
                AetherIIStructures.SENTRY_RUINS,
                AetherIIStructures.INFECTED_GUARDIAN_TREE
        );
        this.tagOf(AetherIITags.Structures.WATCHTOWERS).add(
                AetherIIStructures.WATCHTOWER
        );
        this.tagOf(AetherIITags.Structures.SURFACE_RUINS).add(
                AetherIIStructures.VERADEXIAN_RUINS_TEMPERATE,
                AetherIIStructures.VERADEXIAN_RUINS_ARCTIC,
                AetherIIStructures.VERADEXIAN_LIBRARY_TEMPERATE
        );

        this.tagOf(AetherIITags.Structures.TREE_BLACKLIST_FILTER).addTags(
                AetherIITags.Structures.CAMPS
        );
        this.tagOf(AetherIITags.Structures.ALKAHEST_POOL_BLACKLIST_FILTER).addTags(
                AetherIITags.Structures.DUNGEONS
        );
        this.tagOf(AetherIITags.Structures.COAST_BLACKLIST_FILTER).add(
                AetherIIStructures.SENTRY_RUINS
        );
        this.tagOf(AetherIITags.Structures.FERROSITE_SPIKE_BLACKLIST_FILTER).addTags(
                AetherIITags.Structures.OUTPOSTS,
                AetherIITags.Structures.CAMPS,
                AetherIITags.Structures.WATCHTOWERS
        );
        this.tagOf(AetherIITags.Structures.ARCTIC_ICE_SPIKE_BLACKLIST_FILTER).addTags(
                AetherIITags.Structures.OUTPOSTS,
                AetherIITags.Structures.CAMPS,
                AetherIITags.Structures.WATCHTOWERS
        );
        this.tagOf(AetherIITags.Structures.AERCLOUD_BLACKLIST_FILTER).addTags(
                AetherIITags.Structures.DUNGEONS
        );
    }
}