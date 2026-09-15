package com.aetherteam.aetherii.data.generators.tags;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import com.aetherteam.aetherii.data.providers.AetherTagAppender;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.material.Fluids;

import java.util.concurrent.CompletableFuture;

public class AetherIIFluidTagData extends FabricTagsProvider<Fluid> {
    public AetherIIFluidTagData(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.FLUID, registries);
    }

    protected AetherTagAppender<Fluid> tagOf(TagKey<Fluid> key) {
        return new AetherTagAppender<>(this.builder(key), BuiltInRegistries.FLUID);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tagOf(AetherIITags.Fluids.ALKAHEST).add(
                AetherIIFluids.ALKAHEST,
                AetherIIFluids.FLOWING_ALKAHEST
        );
        this.tagOf(AetherIITags.Fluids.ALLOWED_SKYROOT_BUCKET_PICKUP).add(
                Fluids.WATER,
                Fluids.FLOWING_WATER
        );
    }
}