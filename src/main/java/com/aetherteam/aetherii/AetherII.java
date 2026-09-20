package com.aetherteam.aetherii;

import com.aetherteam.aetherii.data.resources.registries.AetherIIDataMaps;
import com.aetherteam.aetherii.item.AetherIIFuelsAndCompostables;
import com.aetherteam.aetherii.advancement.AetherIIAdvancementSoundOverrides;
import com.aetherteam.aetherii.advancement.predicate.AetherIIEntitySubPredicates;
import com.aetherteam.aetherii.advancement.trigger.AetherIIAdvancementTriggers;
import com.aetherteam.aetherii.api.ItemReinforcement;
import com.aetherteam.aetherii.api.guidebook.BestiaryEntry;
import com.aetherteam.aetherii.api.guidebook.EffectsEntry;
import com.aetherteam.aetherii.api.guidebook.ExplorationEntry;
import com.aetherteam.aetherii.api.guidebook.RewardWrapper;
import com.aetherteam.aetherii.api.registries.AetherIIRegistries;
import com.aetherteam.aetherii.api.styles.StyleDesign;
import com.aetherteam.aetherii.api.styles.StyleMaterial;
import com.aetherteam.aetherii.attachment.AetherIIDataAttachments;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.AetherIICauldronInteractions;
import com.aetherteam.aetherii.block.AetherIIDispenseBehaviors;
import com.aetherteam.aetherii.block.AetherIIFluids;
import com.aetherteam.aetherii.blockentity.AetherIIBlockEntityTypes;
import com.aetherteam.aetherii.client.particle.AetherIIParticleTypes;
import com.aetherteam.aetherii.client.sound.AetherIISoundEvents;
import com.aetherteam.aetherii.data.resources.registries.AetherIIMurals;
import com.aetherteam.aetherii.effect.AetherIIMobEffects;
import com.aetherteam.aetherii.entity.AetherIIDataSerializers;
import com.aetherteam.aetherii.entity.AetherIIEntityTypes;
import com.aetherteam.aetherii.entity.ai.brain.memory.AetherIIMemoryModuleTypes;
import com.aetherteam.aetherii.entity.ai.brain.sensor.AetherIISensorTypes;
import com.aetherteam.aetherii.entity.attributes.AetherIIAttributes;
import com.aetherteam.aetherii.entity.variant.GlitterwingVariant;
import com.aetherteam.aetherii.entity.variant.ShroudwingVariant;
import com.aetherteam.aetherii.entity.variant.SkyrootLizardVariant;
import com.aetherteam.aetherii.entity.variant.spawning.AetherIISpawnConditions;
import com.aetherteam.aetherii.inventory.menu.AetherIIMenuTypes;
import com.aetherteam.aetherii.item.AetherIICreativeTabs;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.item.components.AetherIIDataComponents;
import com.aetherteam.aetherii.item.consumeeffect.AetherIIConsumeEffectTypes;
import com.aetherteam.aetherii.loot.conditions.AetherIILootConditions;
import com.aetherteam.aetherii.loot.functions.AetherIILootFunctions;
import com.aetherteam.aetherii.loot.modifiers.AetherIILootModifiers;
import com.aetherteam.aetherii.network.AetherIIPackets;
import com.aetherteam.aetherii.recipe.AetherIIRecipeSerializers;
import com.aetherteam.aetherii.recipe.book.AetherIIRecipeBookCategories;
import com.aetherteam.aetherii.recipe.display.AetherIIRecipeDisplays;
import com.aetherteam.aetherii.recipe.display.slot.AetherIISlotDisplays;
import com.aetherteam.aetherii.recipe.recipes.AetherIIRecipeTypes;
import com.aetherteam.aetherii.recipe.set.AetherIIRecipePropertySets;
import com.aetherteam.aetherii.world.AetherIIPoi;
import com.aetherteam.aetherii.world.density.AetherIIDensityFunctionTypes;
import com.aetherteam.aetherii.world.feature.AetherIIFeatures;
import com.aetherteam.aetherii.loot.AetherIILootContexts;
import com.aetherteam.aetherii.world.feature.modifier.filter.AetherIIPlacementModifierTypes;
import com.aetherteam.aetherii.world.feature.modifier.predicate.AetherIIBlockPredicateTypes;
import com.aetherteam.aetherii.world.structure.piece.AetherIIStructurePieceTypes;
import com.aetherteam.aetherii.world.structure.pool.AetherIIPoolElementTypes;
import com.aetherteam.aetherii.world.structure.processor.AetherIIStructureProcessorTypes;
import com.aetherteam.aetherii.world.structure.type.AetherIIStructureTypes;
import com.aetherteam.aetherii.world.surfacerule.AetherIISurfaceRules;
import com.aetherteam.aetherii.world.tree.decorator.AetherIITreeDecoratorTypes;
import com.aetherteam.aetherii.world.tree.foliage.AetherIIFoliagePlacerTypes;
import com.aetherteam.aetherii.world.tree.trunk.AetherIITrunkPlacerTypes;
import com.mojang.logging.LogUtils;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

public class AetherII implements ModInitializer {
    public static final String MODID = "aether_ii";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final boolean DEBUG_MODE = false;

    @Override
    public void onInitialize() {
        // Configs
        ConfigRegistry.INSTANCE.register(MODID, ModConfig.Type.SERVER, AetherIIConfig.SERVER_SPEC);
        ConfigRegistry.INSTANCE.register(MODID, ModConfig.Type.COMMON, AetherIIConfig.COMMON_SPEC);

        // Initialize Registries in order
        AetherIIDataAttachments.init();
        AetherIIMurals.init();
        AetherIIAdvancementSoundOverrides.init();
        AetherIIAdvancementTriggers.init();
        AetherIIEntitySubPredicates.init();
        AetherIISoundEvents.init();
        AetherIIParticleTypes.init();
        AetherIIMobEffects.init();
        AetherIIDataComponents.init();
        AetherIIConsumeEffectTypes.init();
        AetherIIAttributes.init();
        AetherIIDataSerializers.init();
        AetherIIMemoryModuleTypes.init();
        AetherIISensorTypes.init();
        AetherIISpawnConditions.init();
        AetherIIGameEvents.init();
        AetherIIFluids.init();
        AetherIIBlocks.init();
        AetherIIBlocks.registerWoodTypes(); // Registered this early to avoid bugs with WoodTypes and signs.
        AetherIIItems.init();
        this.registerItemAliases();
        AetherIIBlockEntityTypes.init();
        AetherIIEntityTypes.init();
        AetherIICreativeTabs.init();
        AetherIIMenuTypes.init();
        AetherIIRecipeTypes.init();
        AetherIIRecipeSerializers.init();
        AetherIIRecipeDisplays.init();
        AetherIISlotDisplays.init();
        AetherIIRecipeBookCategories.init();
        AetherIILootConditions.init();
        AetherIILootFunctions.init();
        AetherIILootModifiers.init();

        // Worldgen Registries
        AetherIIPoi.init();
        AetherIIDensityFunctionTypes.init();
        AetherIIFeatures.init();
        AetherIILootContexts.init();
        AetherIIPlacementModifierTypes.init();
        AetherIIBlockPredicateTypes.init();
        AetherIITreeDecoratorTypes.init();
        AetherIIFoliagePlacerTypes.init();
        AetherIITrunkPlacerTypes.init();
        AetherIIStructurePieceTypes.init();
        AetherIIPoolElementTypes.init();
        AetherIIStructureProcessorTypes.init();
        AetherIIStructureTypes.init();
        AetherIISurfaceRules.init();

        // Datapack Registries
        registerDataPackRegistries();

        // Post-setup
        AetherIIBlocks.registerFlammability();
        AetherIIBlocks.registerStrippables();
        AetherIIBlocks.registerPathTypes();
        AetherIIBlocks.registerFlattenables();
        AetherIIBlocks.registerTillables();

        AetherIIRecipePropertySets.addToMap();

        this.registerDispenserBehaviors();
        this.registerCauldronInteractions();

        AetherIIBlockEntityTypes.registerValidBlockEntityTypes();
        AetherIIEntityTypes.registerSpawnPlacements();
        AetherIIEntityTypes.registerEntityAttributes();
        AetherIIAttributes.registerEntityAttributes();

        AetherIIPackets.init();
        AetherIIDataMaps.registerDataMaps();
        AetherIIFuelsAndCompostables.register();
        com.aetherteam.aetherii.command.AetherIICommands.init();
        com.aetherteam.aetherii.data.ReloadListeners.registerReloadListeners();
        AetherIIEventListeners.register();
        AetherIIItems.registerEquipmentAbilities();
        AetherIIMobEffects.registerUniqueBehaviors();
    }

    private static void registerDataPackRegistries() {
        DynamicRegistries.registerSynced(AetherIIRegistries.BESTIARY_ENTRY, BestiaryEntry.DIRECT_CODEC);
        DynamicRegistries.registerSynced(AetherIIRegistries.EFFECTS_ENTRY, EffectsEntry.DIRECT_CODEC);
        DynamicRegistries.registerSynced(AetherIIRegistries.EXPLORATION_ENTRY, ExplorationEntry.DIRECT_CODEC);
        DynamicRegistries.registerSynced(AetherIIRegistries.STYLE_DESIGN, StyleDesign.DIRECT_CODEC);
        DynamicRegistries.registerSynced(AetherIIRegistries.STYLE_MATERIAL, StyleMaterial.DIRECT_CODEC);
        DynamicRegistries.registerSynced(AetherIIRegistries.ITEM_REINFORCEMENT, ItemReinforcement.DIRECT_CODEC);
        DynamicRegistries.registerSynced(AetherIIRegistries.SKYROOT_LIZARD_VARIANT, SkyrootLizardVariant.DIRECT_CODEC);
        DynamicRegistries.registerSynced(AetherIIRegistries.GLITTERWING_VARIANT, GlitterwingVariant.DIRECT_CODEC);
        DynamicRegistries.registerSynced(AetherIIRegistries.SHROUDWING_VARIANT, ShroudwingVariant.DIRECT_CODEC);
        DynamicRegistries.registerSynced(AetherIIRegistries.REWARD_WRAPPER, RewardWrapper.DIRECT_CODEC);
    }

    private void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(AetherIIItems.SKYROOT_WATER_BUCKET, AetherIIDispenseBehaviors.SKYROOT_BUCKET_DISPENSE_BEHAVIOR);
        DispenserBlock.registerBehavior(AetherIIItems.SKYROOT_BUCKET, AetherIIDispenseBehaviors.SKYROOT_BUCKET_PICKUP_BEHAVIOR);
    }

    private void registerCauldronInteractions() {
        CauldronInteractions.EMPTY.put(AetherIIItems.SKYROOT_WATER_BUCKET, AetherIICauldronInteractions.FILL_WATER);
        CauldronInteractions.WATER.put(AetherIIItems.SKYROOT_WATER_BUCKET, AetherIICauldronInteractions.FILL_WATER);
        CauldronInteractions.LAVA.put(AetherIIItems.SKYROOT_WATER_BUCKET, AetherIICauldronInteractions.FILL_WATER);
        CauldronInteractions.POWDER_SNOW.put(AetherIIItems.SKYROOT_WATER_BUCKET, AetherIICauldronInteractions.FILL_WATER);
        CauldronInteractions.EMPTY.put(AetherIIItems.SKYROOT_POWDER_SNOW_BUCKET, AetherIICauldronInteractions.FILL_POWDER_SNOW);
        CauldronInteractions.WATER.put(AetherIIItems.SKYROOT_POWDER_SNOW_BUCKET, AetherIICauldronInteractions.FILL_POWDER_SNOW);
        CauldronInteractions.LAVA.put(AetherIIItems.SKYROOT_POWDER_SNOW_BUCKET, AetherIICauldronInteractions.FILL_POWDER_SNOW);
        CauldronInteractions.POWDER_SNOW.put(AetherIIItems.SKYROOT_POWDER_SNOW_BUCKET, AetherIICauldronInteractions.FILL_POWDER_SNOW);
        CauldronInteractions.WATER.put(AetherIIItems.SKYROOT_BUCKET, AetherIICauldronInteractions.EMPTY_WATER);
        CauldronInteractions.POWDER_SNOW.put(AetherIIItems.SKYROOT_BUCKET, AetherIICauldronInteractions.EMPTY_POWDER_SNOW);
    }

    private void registerItemAliases() {
        BuiltInRegistries.ITEM.addAlias(Identifier.fromNamespaceAndPath(MODID, "skyroot_spear"), Identifier.fromNamespaceAndPath(MODID, "skyroot_pike"));
        BuiltInRegistries.ITEM.addAlias(Identifier.fromNamespaceAndPath(MODID, "holystone_spear"), Identifier.fromNamespaceAndPath(MODID, "holystone_pike"));
        BuiltInRegistries.ITEM.addAlias(Identifier.fromNamespaceAndPath(MODID, "zanite_spear"), Identifier.fromNamespaceAndPath(MODID, "zanite_pike"));
        BuiltInRegistries.ITEM.addAlias(Identifier.fromNamespaceAndPath(MODID, "arkenium_spear"), Identifier.fromNamespaceAndPath(MODID, "arkenium_pike"));
        BuiltInRegistries.ITEM.addAlias(Identifier.fromNamespaceAndPath(MODID, "gravitite_spear"), Identifier.fromNamespaceAndPath(MODID, "gravitite_pike"));
        BuiltInRegistries.ITEM.addAlias(Identifier.fromNamespaceAndPath(MODID, "ice_pendant"), Identifier.fromNamespaceAndPath(MODID, "icestone_pendant"));
    }
}
