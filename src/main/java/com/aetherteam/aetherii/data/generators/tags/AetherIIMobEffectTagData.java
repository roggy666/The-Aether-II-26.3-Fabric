package com.aetherteam.aetherii.data.generators.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.BuiltInRegistries;
import com.aetherteam.aetherii.data.providers.AetherTagAppender;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.effect.AetherIIMobEffects;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

import java.util.concurrent.CompletableFuture;

public class AetherIIMobEffectTagData extends FabricTagsProvider<MobEffect> {
    public AetherIIMobEffectTagData(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.MOB_EFFECT, registries);
    }

    protected AetherTagAppender<MobEffect> tagOf(TagKey<MobEffect> key) {
        return new AetherTagAppender<>(this.builder(key), BuiltInRegistries.MOB_EFFECT);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tagOf(AetherIITags.MobEffects.DART_EFFECTS).addKeys(
                AetherIIMobEffects.VULNERABILITY.key(),
                AetherIIMobEffects.TOXIN.key(),
                AetherIIMobEffects.VENOM.key()
        );
        this.tagOf(AetherIITags.MobEffects.MILK_DOESNT_CLEAR).addKeys(
                AetherIIMobEffects.VULNERABILITY.key(),
                AetherIIMobEffects.WOUND.key(),
                AetherIIMobEffects.STUN.key(),
                AetherIIMobEffects.FRACTURE.key(),
                AetherIIMobEffects.AMBROSIUM_POISONING.key(),
                AetherIIMobEffects.CHARGED.key(),
                AetherIIMobEffects.WEBBED.key(),
                AetherIIMobEffects.IMMOLATION.key(),
                AetherIIMobEffects.FROSTBITE.key(),
                AetherIIMobEffects.FUNGAL_ROT.key(),
                AetherIIMobEffects.CRYSTALLIZED.key(),
                AetherIIMobEffects.NATURAL_CAMOUFLAGE.key(),
                AetherIIMobEffects.HEALING_OVERFLOW.key(),
                AetherIIMobEffects.ELECTRIC_SHOCK.key(),
                AetherIIMobEffects.CARRION_TRAP.key(),
                AetherIIMobEffects.GRAVITATIONAL_PULL.key()
        );
    }
}
