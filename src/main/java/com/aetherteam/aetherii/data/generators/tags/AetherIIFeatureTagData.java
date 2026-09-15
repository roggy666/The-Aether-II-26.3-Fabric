package com.aetherteam.aetherii.data.generators.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.tags.TagAppender;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.data.resources.registries.holyisles.HolyIslesConfiguredFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.FeatureTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.concurrent.CompletableFuture;

public class AetherIIFeatureTagData extends FabricTagsProvider<ConfiguredFeature<?, ?>> {
    public AetherIIFeatureTagData(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.CONFIGURED_FEATURE, registries);
    }

    protected KeyAppender<ConfiguredFeature<?, ?>> tagOf(TagKey<ConfiguredFeature<?, ?>> key) {
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

    @Override
    public void addTags(HolderLookup.Provider provider) {
        // Vanilla
        this.tagOf(FeatureTags.CAN_SPAWN_FROM_BONE_MEAL).add(
                HolyIslesConfiguredFeatures.VALKYRIE_SPROUT,
                HolyIslesConfiguredFeatures.AETHER_BUSH,
                HolyIslesConfiguredFeatures.BLUEBERRY_BUSH,
                HolyIslesConfiguredFeatures.ORANGE_TREE,
                HolyIslesConfiguredFeatures.HOLY_ISLES_FLOWER_PATCH,
                HolyIslesConfiguredFeatures.HIGHFIELDS_FLOWER_PATCH,
                HolyIslesConfiguredFeatures.MAGNETIC_FLOWER_PATCH,
                HolyIslesConfiguredFeatures.ARCTIC_FLOWER_PATCH
        );
    }
}
