package com.aetherteam.aetherii.data.generators.tags;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.core.registries.Registries;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.data.resources.registries.holyisles.HolyIslesBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class AetherIIBiomeTagData extends FabricTagsProvider<Biome> {
    public AetherIIBiomeTagData(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.BIOME, registries);
    }

    protected KeyAppender<Biome> tagOf(TagKey<Biome> key) {
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
        this.tagOf(AetherIITags.Biomes.THE_AETHER).addTags(
                AetherIITags.Biomes.HOLY_ISLES
        );
        this.tagOf(AetherIITags.Biomes.HOLY_ISLES).addTags(
                AetherIITags.Biomes.HIGHFIELDS,
                AetherIITags.Biomes.MAGNETIC,
                AetherIITags.Biomes.ARCTIC,
                AetherIITags.Biomes.IRRADIATED,
                AetherIITags.Biomes.EXPANSE
        );
        this.tagOf(AetherIITags.Biomes.HIGHFIELDS).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.SHIMMERING_BASIN
        );
        this.tagOf(AetherIITags.Biomes.MAGNETIC).add(
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS
        );
        this.tagOf(AetherIITags.Biomes.MAGNETIC_FOG).add(
                HolyIslesBiomes.GLISTENING_SWAMP
        );
        this.tagOf(AetherIITags.Biomes.ARCTIC).add(
                HolyIslesBiomes.FRIGID_SIERRA,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.FROZEN_LAKES,
                HolyIslesBiomes.SHEER_TUNDRA
        );
        this.tagOf(AetherIITags.Biomes.IRRADIATED).add(
                HolyIslesBiomes.CONTAMINATED_JUNGLE,
                HolyIslesBiomes.BATTLEGROUND_WASTES
        );
        this.tagOf(AetherIITags.Biomes.EXPANSE).add(
                HolyIslesBiomes.EXPANSE
        );

        this.tagOf(AetherIITags.Biomes.LUSH).add(
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.VIOLET_HIGHWOODS,
                HolyIslesBiomes.ENDURING_WOODLAND
        );
        this.tagOf(AetherIITags.Biomes.WET).add(
                HolyIslesBiomes.SHIMMERING_BASIN,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.FROZEN_LAKES
        );

        this.tagOf(AetherIITags.Biomes.MYCELIUM_CONVERSION).add(
                Biomes.MUSHROOM_FIELDS
        );
        this.tagOf(AetherIITags.Biomes.PODZOL_CONVERSION).add(
                Biomes.OLD_GROWTH_PINE_TAIGA,
                Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                Biomes.BAMBOO_JUNGLE
        );
        this.tagOf(AetherIITags.Biomes.CRIMSON_NYLIUM_CONVERSION).add(
                Biomes.CRIMSON_FOREST
        );
        this.tagOf(AetherIITags.Biomes.WARPED_NYLIUM_CONVERSION).add(
                Biomes.WARPED_FOREST
        );

        this.tagOf(AetherIITags.Biomes.ARCTIC_ICE).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.SHIMMERING_BASIN,
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS,
                HolyIslesBiomes.FRIGID_SIERRA,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.FROZEN_LAKES,
                HolyIslesBiomes.SHEER_TUNDRA,
                HolyIslesBiomes.CONTAMINATED_JUNGLE,
                HolyIslesBiomes.BATTLEGROUND_WASTES,
                HolyIslesBiomes.EXPANSE,
                HolyIslesBiomes.HESTVEIL_CAVERNS
        );

        this.tagOf(AetherIITags.Biomes.AETHER_MUSIC).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.SHIMMERING_BASIN,
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS,
                HolyIslesBiomes.FRIGID_SIERRA,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.FROZEN_LAKES,
                HolyIslesBiomes.SHEER_TUNDRA,
                HolyIslesBiomes.CONTAMINATED_JUNGLE,
                HolyIslesBiomes.BATTLEGROUND_WASTES,
                HolyIslesBiomes.EXPANSE,
                HolyIslesBiomes.HESTVEIL_CAVERNS
        );

        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_OUTPOST).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS,
                HolyIslesBiomes.FRIGID_SIERRA,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.SHEER_TUNDRA
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_CAMP_HIGHFIELDS).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_CAMP_MAGNETIC).add(
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.VIOLET_HIGHWOODS
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_CAMP_ARCTIC).add(
                HolyIslesBiomes.FRIGID_SIERRA,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.SHEER_TUNDRA
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_WATCHTOWER).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS,
                HolyIslesBiomes.FRIGID_SIERRA,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.SHEER_TUNDRA
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_ANIMAL_DEN).add(
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.VIOLET_HIGHWOODS,
                HolyIslesBiomes.ENDURING_WOODLAND
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_VERADEXIAN_RUINS_TEMPERATE).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_VERADEXIAN_RUINS_ARCTIC).add(
                HolyIslesBiomes.FRIGID_SIERRA,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.SHEER_TUNDRA
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_VERADEXIAN_LIBRARY_TEMPERATE).add(
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_VERADEXIAN_LIBRARY_ARCTIC).add(
                HolyIslesBiomes.ENDURING_WOODLAND
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_VERADEXIAN_AQUEDUCT).add(
                HolyIslesBiomes.SHIMMERING_BASIN
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_BREXALLEN_RUINS).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.SHIMMERING_BASIN,
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS,
                HolyIslesBiomes.FRIGID_SIERRA,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.SHEER_TUNDRA,
                HolyIslesBiomes.FROZEN_LAKES,
                HolyIslesBiomes.HESTVEIL_CAVERNS
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_UNDERCLOUD_MINESHAFT).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.SHIMMERING_BASIN,
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS,
                HolyIslesBiomes.FRIGID_SIERRA,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.SHEER_TUNDRA,
                HolyIslesBiomes.FROZEN_LAKES
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_ANCIENT_HENGE).add(
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_IRRADIATED_REMNANTS).add(
                HolyIslesBiomes.CONTAMINATED_JUNGLE,
                HolyIslesBiomes.BATTLEGROUND_WASTES
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_SENTRY_RUINS).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.TURQUOISE_FOREST,
                HolyIslesBiomes.GLISTENING_SWAMP,
                HolyIslesBiomes.VIOLET_HIGHWOODS,
                HolyIslesBiomes.FRIGID_SIERRA,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.SHEER_TUNDRA,
                HolyIslesBiomes.CONTAMINATED_JUNGLE,
                HolyIslesBiomes.BATTLEGROUND_WASTES
        );
        this.tagOf(AetherIITags.Biomes.HAS_STRUCTURE_INFECTED_GUARDIAN_TREE).add(
                HolyIslesBiomes.FLOURISHING_FIELD,
                HolyIslesBiomes.VERDANT_WOODS,
                HolyIslesBiomes.SHROUDED_FOREST,
                HolyIslesBiomes.MAGNETIC_SCAR,
                HolyIslesBiomes.VIOLET_HIGHWOODS,
                HolyIslesBiomes.ENDURING_WOODLAND,
                HolyIslesBiomes.SHEER_TUNDRA
        );
    }
}