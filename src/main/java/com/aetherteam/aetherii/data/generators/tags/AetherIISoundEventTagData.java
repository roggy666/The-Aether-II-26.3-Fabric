package com.aetherteam.aetherii.data.generators.tags;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.BuiltInRegistries;
import com.aetherteam.aetherii.data.providers.AetherTagAppender;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.client.sound.AetherIISoundEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

import java.util.concurrent.CompletableFuture;

public class AetherIISoundEventTagData extends FabricTagsProvider<SoundEvent> {
    public AetherIISoundEventTagData(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.SOUND_EVENT, registries);
    }

    protected AetherTagAppender<SoundEvent> tagOf(TagKey<SoundEvent> key) {
        return new AetherTagAppender<>(this.builder(key), BuiltInRegistries.SOUND_EVENT);
    }

    private static ResourceKey<SoundEvent> key(SoundEvent sound) {
        return ResourceKey.create(Registries.SOUND_EVENT, sound.location());
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tagOf(AetherIITags.SoundEvents.PORTAL_SOUNDS).addKeys(
                key(AetherIISoundEvents.BLOCK_AETHER_PORTAL_AMBIENT),
                key(AetherIISoundEvents.BLOCK_AETHER_PORTAL_TRIGGER),
                key(AetherIISoundEvents.BLOCK_AETHER_PORTAL_TRAVEL)
        );
        this.tagOf(AetherIITags.SoundEvents.AMBIENT_PORTAL_SOUNDS).addKeys(
                key(AetherIISoundEvents.BLOCK_AETHER_PORTAL_AMBIENT)
        );
        this.tagOf(AetherIITags.SoundEvents.ACTIVATED_PORTAL_SOUNDS).addKeys(
                key(AetherIISoundEvents.BLOCK_AETHER_PORTAL_TRIGGER),
                key(AetherIISoundEvents.BLOCK_AETHER_PORTAL_TRAVEL)
        );
        this.tagOf(AetherIITags.SoundEvents.ACHIEVEMENT_SOUNDS);
        this.tagOf(AetherIITags.SoundEvents.MUSIC).addKeys(
                key(AetherIISoundEvents.MUSIC_AETHER),
                key(AetherIISoundEvents.MUSIC_AETHER_CAVES),
                key(AetherIISoundEvents.MUSIC_AETHER_NIGHT),
                key(AetherIISoundEvents.MUSIC_AETHER_SUNRISE),
                key(AetherIISoundEvents.MUSIC_AETHER_SUNSET)
        ).addTag(
                AetherIITags.SoundEvents.BOSS_MUSIC
        );
        this.tagOf(AetherIITags.SoundEvents.BOSS_MUSIC).addKeys(
                key(AetherIISoundEvents.MUSIC_BOSS_SLIDER)
        );
    }
}
