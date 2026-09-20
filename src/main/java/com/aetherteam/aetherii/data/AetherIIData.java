package com.aetherteam.aetherii.data;

import com.aetherteam.aetherii.data.generators.loot.*;
import com.aetherteam.aetherii.data.generators.*;
import com.aetherteam.aetherii.data.generators.tags.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;

import java.util.concurrent.CompletableFuture;

public class AetherIIData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Client Data
        pack.addProvider((FabricPackOutput output) -> new AetherIIModelData(output));
        pack.addProvider((FabricPackOutput output) -> new AetherIIParticleData(output));
        pack.addProvider(AetherIILanguageData::new);
        pack.addProvider((FabricPackOutput output) -> new AetherIISoundData(output));
        pack.addProvider((FabricPackOutput output) -> new AetherIIEquipmentAssetData(output));

        // Server Data
        pack.addProvider(AetherIIRegistrySets::new);
        pack.addProvider(AetherIIRecipeData.Runner::new);
        pack.addProvider(AetherIIBlockLoot::new);
        pack.addProvider(AetherIIEntityLoot::new);
        pack.addProvider(AetherIIChestLoot::new);
        pack.addProvider(AetherIIShearingLoot::new);
        pack.addProvider(AetherIIGiftLoot::new);
        pack.addProvider(AetherIIStrippingLoot::new);
        pack.addProvider(AetherIILootModifierData::new);
        pack.addProvider(AetherIIAdvancementData::new);
        pack.addProvider(AetherIIDataMapData::new);

        // Tags
        pack.addProvider(AetherIIBlockTagData::new);
        pack.addProvider(AetherIIItemTagData::new);
        pack.addProvider(AetherIIEntityTypeTagData::new);
        pack.addProvider(AetherIIFluidTagData::new);
        pack.addProvider(AetherIIBiomeTagData::new);
        pack.addProvider(AetherIIFeatureTagData::new);
        pack.addProvider(AetherIIStructureTagData::new);
        pack.addProvider(AetherIIDamageTypeTagData::new);
        pack.addProvider(AetherIIMobEffectTagData::new);
        pack.addProvider(AetherIISoundEventTagData::new);

        // pack.mcmeta
        pack.addProvider((FabricPackOutput output) -> new PackMetadataGenerator(output).add(PackMetadataSection.CLIENT_TYPE, new PackMetadataSection(
                Component.translatable("pack.aether_ii.mod.description"),
                new InclusiveRange<>(DetectedVersion.BUILT_IN.packVersion(PackType.CLIENT_RESOURCES)))));
    }

    @Override
    public void buildRegistry(RegistrySetBuilder builder) {
        AetherIIRegistrySets.build(builder);
    }

    @Override
    public void buildReloadableRegistry(RegistrySetBuilder builder) {
        AetherIIRegistrySets.buildReloadable(builder);
    }
}
