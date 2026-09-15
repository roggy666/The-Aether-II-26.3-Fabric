package com.aetherteam.aetherii.data.generators.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import com.aetherteam.aetherii.data.providers.AetherTagAppender;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.entity.AetherIIEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class AetherIIEntityTypeTagData extends FabricTagsProvider<EntityType<?>> {
    public AetherIIEntityTypeTagData(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.ENTITY_TYPE, registries);
    }

    protected AetherTagAppender<EntityType<?>> tagOf(TagKey<EntityType<?>> key) {
        return new AetherTagAppender<>(this.builder(key), BuiltInRegistries.ENTITY_TYPE);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        // Aether II
        this.tagOf(AetherIITags.EntityTypes.AETHER_MOBS).add(
                AetherIIEntityTypes.FLYING_COW,
                AetherIIEntityTypes.SHEEPUFF,
                AetherIIEntityTypes.PHYG,
                AetherIIEntityTypes.AERBUNNY,
                AetherIIEntityTypes.HIGHFIELDS_TAEGORE,
                AetherIIEntityTypes.MAGNETIC_TAEGORE,
                AetherIIEntityTypes.ARCTIC_TAEGORE,
                AetherIIEntityTypes.HIGHFIELDS_BURRUKAI,
                AetherIIEntityTypes.MAGNETIC_BURRUKAI,
                AetherIIEntityTypes.ARCTIC_BURRUKAI,
                AetherIIEntityTypes.HIGHFIELDS_KIRRID,
                AetherIIEntityTypes.MAGNETIC_KIRRID,
                AetherIIEntityTypes.ARCTIC_KIRRID,
                AetherIIEntityTypes.MOA,
                AetherIIEntityTypes.PRISMALLARD,
                AetherIIEntityTypes.SKYROOT_LIZARD,
                AetherIIEntityTypes.GLITTERWING,
                AetherIIEntityTypes.SHROUDWING,
                AetherIIEntityTypes.AECHOR_PLANT,
                AetherIIEntityTypes.CARRION_SPROUT,
                AetherIIEntityTypes.ZEPHYR,
                AetherIIEntityTypes.SKEPHID,
                AetherIIEntityTypes.TEMPEST,
                AetherIIEntityTypes.COCKATRICE,
                AetherIIEntityTypes.ARKENIUM_TALUTON,
                AetherIIEntityTypes.GRAVITITE_TALUTON,
                AetherIIEntityTypes.MIMIC,
                AetherIIEntityTypes.DETONATION_SENTRY,
                AetherIIEntityTypes.SENTRY_GOLEM,
                AetherIIEntityTypes.SLIDER,
                AetherIIEntityTypes.DEMOLITION_PROJECTILE,
                AetherIIEntityTypes.BLADESHROOM_HUNTER,
                AetherIIEntityTypes.HOLYSTONE_ROCK,
                AetherIIEntityTypes.ARCTIC_SNOWBALL,
                AetherIIEntityTypes.SKYROOT_PINECONE,
                AetherIIEntityTypes.PRISMALLARD_EGG,
                AetherIIEntityTypes.LASSO_LOOP,
                AetherIIEntityTypes.SCATTERGLASS_BOLT,
                AetherIIEntityTypes.AMBER_DART,
                AetherIIEntityTypes.TOXIC_DART,
                AetherIIEntityTypes.ZEPHYR_WEBBING_BALL,
                AetherIIEntityTypes.TEMPEST_THUNDERBALL
        );
        this.tagOf(AetherIITags.EntityTypes.TAEGORE).add(
                AetherIIEntityTypes.HIGHFIELDS_TAEGORE,
                AetherIIEntityTypes.MAGNETIC_TAEGORE,
                AetherIIEntityTypes.ARCTIC_TAEGORE
        );
        this.tagOf(AetherIITags.EntityTypes.BURRUKAI).add(
                AetherIIEntityTypes.HIGHFIELDS_BURRUKAI,
                AetherIIEntityTypes.MAGNETIC_BURRUKAI,
                AetherIIEntityTypes.ARCTIC_BURRUKAI
        );
        this.tagOf(AetherIITags.EntityTypes.KIRRID).add(
                AetherIIEntityTypes.HIGHFIELDS_KIRRID,
                AetherIIEntityTypes.MAGNETIC_KIRRID,
                AetherIIEntityTypes.ARCTIC_KIRRID
        );
        this.tagOf(AetherIITags.EntityTypes.SWETS).add(
                AetherIIEntityTypes.BLUE_SWET,
                AetherIIEntityTypes.GOLDEN_SWET
        );
        this.tagOf(AetherIITags.EntityTypes.TALUTONS).add(
                AetherIIEntityTypes.ARKENIUM_TALUTON,
                AetherIIEntityTypes.GRAVITITE_TALUTON
        );
        this.tagOf(AetherIITags.EntityTypes.PLANT_MOBS).add(
                AetherIIEntityTypes.CARRION_SPROUT,
                AetherIIEntityTypes.AECHOR_PLANT
        );
        this.tagOf(AetherIITags.EntityTypes.SENTRY_RUINS_MOBS).add(
                AetherIIEntityTypes.MIMIC,
                AetherIIEntityTypes.DETONATION_SENTRY,
                AetherIIEntityTypes.SENTRY_GOLEM,
                AetherIIEntityTypes.SLIDER
        );
        this.tagOf(AetherIITags.EntityTypes.DUNGEON_MOBS).addTag(
                AetherIITags.EntityTypes.SENTRY_RUINS_MOBS
        );

        this.tagOf(AetherIITags.EntityTypes.NO_DOUBLE_DROPS).add(
                EntityTypes.PLAYER
        ).addTag(
                ConventionalEntityTypeTags.BOSSES
        );
        this.tagOf(AetherIITags.EntityTypes.NO_AMBROSIUM_DROPS).add(
                EntityTypes.PLAYER
        );
        this.tagOf(AetherIITags.EntityTypes.ZEPHYR_BLOW_BLACKLIST).addTag(
                AetherIITags.EntityTypes.PLANT_MOBS
        );

        this.tagOf(AetherIITags.EntityTypes.PLANT_DAMAGING_PROJECTILES);
        this.tagOf(AetherIITags.EntityTypes.SLIDER_DAMAGING_PROJECTILES);
        this.tagOf(AetherIITags.EntityTypes.STICKABLE_PROJECTILES).add(
                AetherIIEntityTypes.SCATTERGLASS_BOLT,
                AetherIIEntityTypes.AMBER_DART,
                AetherIIEntityTypes.TOXIC_DART,
                AetherIIEntityTypes.VENOMOUS_DART
        );
        this.tagOf(AetherIITags.EntityTypes.STICKABLE_PROJECTILES_EMISSIVE).add(
                AetherIIEntityTypes.VENOMOUS_DART
        );

        this.tagOf(AetherIITags.EntityTypes.SPAWNING_ICE).add(
                EntityTypes.POLAR_BEAR
        );
        this.tagOf(AetherIITags.EntityTypes.SPAWNING_AERCLOUDS).add(
                AetherIIEntityTypes.TEMPEST,
                AetherIIEntityTypes.ZEPHYR
        );
        this.tagOf(AetherIITags.EntityTypes.SPAWNING_LEAVES).add(
                EntityTypes.OCELOT,
                EntityTypes.PARROT
        );

        // Vanilla
        this.tagOf(EntityTypeTags.ARROWS).add(
                AetherIIEntityTypes.SCATTERGLASS_BOLT,
                AetherIIEntityTypes.AMBER_DART
        );
        this.tagOf(EntityTypeTags.IMPACT_PROJECTILES).add(
                AetherIIEntityTypes.HOLYSTONE_ROCK,
                AetherIIEntityTypes.ARCTIC_SNOWBALL,
                AetherIIEntityTypes.SKYROOT_PINECONE,
                AetherIIEntityTypes.TOXIC_DART,
                AetherIIEntityTypes.VENOMOUS_DART,
                AetherIIEntityTypes.TEMPEST_THUNDERBALL,
                AetherIIEntityTypes.GRAVITITE_DEBRIS_SHOT
        );
        this.tagOf(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(
                AetherIIEntityTypes.PHYG,
                AetherIIEntityTypes.SHEEPUFF,
                AetherIIEntityTypes.FLYING_COW,
                AetherIIEntityTypes.AERBUNNY,
                AetherIIEntityTypes.MOA,
                AetherIIEntityTypes.PRISMALLARD
        ).addTag(
                AetherIITags.EntityTypes.KIRRID
        );
        this.tagOf(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(
                AetherIIEntityTypes.DETONATION_SENTRY,
                AetherIIEntityTypes.SENTRY_GOLEM,
                AetherIIEntityTypes.SLIDER
        ).addTag(
                AetherIITags.EntityTypes.TALUTONS
        );
        this.tagOf(EntityTypeTags.FROG_FOOD).addTag(
                AetherIITags.EntityTypes.SWETS
        );
        this.tagOf(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
                AetherIIEntityTypes.PHYG,
                AetherIIEntityTypes.FLYING_COW,
                AetherIIEntityTypes.AERBUNNY,
                AetherIIEntityTypes.MOA,
                AetherIIEntityTypes.PRISMALLARD,
                AetherIIEntityTypes.AERWHALE,
                AetherIIEntityTypes.SKYROOT_LIZARD,
                AetherIIEntityTypes.ZEPHYR,
                AetherIIEntityTypes.TEMPEST,
                AetherIIEntityTypes.SKEPHID,
                AetherIIEntityTypes.GRAVITITE_TALUTON,
                AetherIIEntityTypes.GLITTERWING,
                AetherIIEntityTypes.SHROUDWING
        ).addTag(
                AetherIITags.EntityTypes.KIRRID
        );
        this.tagOf(EntityTypeTags.DISMOUNTS_UNDERWATER).add(
                AetherIIEntityTypes.PHYG,
                AetherIIEntityTypes.FLYING_COW,
                AetherIIEntityTypes.MOA
        );
        this.tagOf(EntityTypeTags.NON_CONTROLLING_RIDER).add(
                AetherIIEntityTypes.AERBUNNY
        );
        this.tagOf(EntityTypeTags.ARTHROPOD).add(
                AetherIIEntityTypes.SKEPHID,
                AetherIIEntityTypes.ZEPHYR,
                AetherIIEntityTypes.TEMPEST
        );
        this.tagOf(EntityTypeTags.REDIRECTABLE_PROJECTILE).add(
                AetherIIEntityTypes.ZEPHYR_WEBBING_BALL,
                AetherIIEntityTypes.TEMPEST_THUNDERBALL
        );
        this.tagOf(EntityTypeTags.BOAT).add(
                AetherIIEntityTypes.CLOUD_SKIFF
        );
        this.tagOf(EntityTypeTags.FOLLOWABLE_FRIENDLY_MOBS).add(
                AetherIIEntityTypes.PHYG,
                AetherIIEntityTypes.SHEEPUFF,
                AetherIIEntityTypes.FLYING_COW,
                AetherIIEntityTypes.AERBUNNY,
                AetherIIEntityTypes.PRISMALLARD
        ).addTags(
                AetherIITags.EntityTypes.TAEGORE,
                AetherIITags.EntityTypes.KIRRID,
                AetherIITags.EntityTypes.BURRUKAI
        );
        this.tagOf(EntityTypeTags.CANNOT_BE_PUSHED_ONTO_BOATS).add(
                AetherIIEntityTypes.SLIDER
        );
        this.tagOf(EntityTypeTags.CAN_FLOAT_WHILE_RIDDEN).add(
                AetherIIEntityTypes.PHYG,
                AetherIIEntityTypes.FLYING_COW,
                AetherIIEntityTypes.MOA
        );

        // NeoForge
        this.tagOf(ConventionalEntityTypeTags.BOSSES).add(
                AetherIIEntityTypes.SLIDER
        );
        this.tagOf(ConventionalEntityTypeTags.BOATS).add(
                AetherIIEntityTypes.CLOUD_SKIFF
        );
        this.tagOf(ConventionalEntityTypeTags.CAPTURING_NOT_SUPPORTED).add(
                AetherIIEntityTypes.SLIDER
        );
        this.tagOf(ConventionalEntityTypeTags.TELEPORTING_NOT_SUPPORTED).add(
                AetherIIEntityTypes.SLIDER
        );
    }
}
