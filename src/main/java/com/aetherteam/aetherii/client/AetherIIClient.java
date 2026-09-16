package com.aetherteam.aetherii.client;

import net.minecraft.core.registries.BuiltInRegistries;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.gui.screens.MenuScreens;
import com.aetherteam.aetherii.client.gui.screen.guidebook.GuidebookEquipmentScreen;
import com.aetherteam.aetherii.client.gui.screen.inventory.*;
import com.aetherteam.aetherii.api.AetherIIMenus;
import com.aetherteam.aetherii.client.event.listeners.DimensionClientListener;
import com.aetherteam.aetherii.client.gui.screen.HolyIslesReceivingLevelScreen;
import com.aetherteam.aetherii.client.particle.AetherIIParticleFactories;
import com.aetherteam.aetherii.client.renderer.AetherIIDimensionRenderers;
import com.aetherteam.aetherii.client.renderer.AetherIIOverlays;
import com.aetherteam.aetherii.client.renderer.AetherIIRenderTypes;
import com.aetherteam.aetherii.client.renderer.AetherIIRenderers;
import com.aetherteam.aetherii.client.renderer.item.color.AetherIIItemTintSources;
import com.aetherteam.aetherii.client.renderer.item.properties.AetherIIItemModelProperties;
import com.aetherteam.aetherii.client.sprite.AetherIISpriteSourceTypes;
import com.aetherteam.aetherii.data.resources.registries.AetherIIDimensions;
import com.aetherteam.aetherii.inventory.menu.AetherIIMenuTypes;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.item.components.AetherIIDataComponents;
import com.aetherteam.aetherii.item.equipment.EquipmentUtil;
import com.aetherteam.aetherii.recipe.book.AetherIIRecipeBookCategories;
import com.aetherteam.nitrogen.event.listeners.TooltipListeners;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.CubeMapTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.client.network.AetherIIClientPackets;
import net.fabricmc.api.ClientModInitializer;

public class AetherIIClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        com.aetherteam.aetherii.client.animation.AetherIIAnimations.register();
        com.aetherteam.aetherii.client.renderer.block.model.AetherIIModelLoaders.register();
        AetherIIClientPackets.init();
        AetherIIClientEventListeners.listen();
        registerMenuScreens();
        registerTooltipOverrides();
        AetherIIColorResolvers.registerColorResolvers();
        AetherIIColorResolvers.registerBlockColor();
        AetherIIParticleFactories.registerParticleFactories();
        AetherIIOverlays.registerOverlays();
        AetherIIRenderers.registerAddLayer();
        AetherIIRenderers.registerEntityRenderers();
        AetherIIRenderers.registerLayerDefinition();
        AetherIIRenderers.registerItemModels();
        AetherIIRenderers.registerBlockStateModels();
        AetherIIRenderers.registerFluidModels();
        AetherIIRenderers.registerBakedModels();
        AetherIIRenderers.registerSpecialModelRenderers();
        AetherIIDimensionRenderers.registerDimensionEffect();
        AetherIIRenderPipelines.registerShaders();
        AetherIIItemDecorators.registerItemDecorators();
        AetherIIClientTooltips.registerClientTooltipComponents();
        AetherIIItemModelProperties.registerConditionalProperties();
        AetherIIItemModelProperties.registerSelectProperties();
        AetherIIItemModelProperties.registerRangeSelectProperties();
        AetherIIAtlases.registerAtlases();
        AetherIISpriteSourceTypes.registerSpriteSourceTypes();
        AetherIIItemTintSources.registerTintSources();
        AetherIIClientCaches.registerReloadListeners();
        AetherIIKeyMappings.registerKeyMappings();
        LevelRenderEvents.COLLECT_SUBMITS.register(AetherIIRenderers::submitCustomGeometryRendering);
    }

    private static void registerMenuScreens() {
        MenuScreens.register(AetherIIMenuTypes.GUIDEBOOK, GuidebookEquipmentScreen::new);
        MenuScreens.register(AetherIIMenuTypes.SKYROOT_CRAFTING_TABLE, SkyrootCraftingScreen::new);
        MenuScreens.register(AetherIIMenuTypes.HOLYSTONE_FURNACE, HolystoneFurnaceScreen::new);
        MenuScreens.register(AetherIIMenuTypes.HOLYSTONE_SMOKER, HolystoneSmokerScreen::new);
        MenuScreens.register(AetherIIMenuTypes.AMBER_HOURGLASS, AmberHourglassScreen::new);
        MenuScreens.register(AetherIIMenuTypes.ALTAR, AltarScreen::new);
        MenuScreens.register(AetherIIMenuTypes.ARTISANS_BENCH, ArtisansBenchScreen::new);
        MenuScreens.register(AetherIIMenuTypes.ARKENIUM_FORGE, ArkeniumForgeScreen::new);
        MenuScreens.register(AetherIIMenuTypes.ALKAHEST_PURIFIER, AlkahestPurifierScreen::new);
    }

    public static void registerTooltipOverrides() {
        TooltipListeners.TooltipPredicate setBonusPredicate = (player, itemStack, components, context, component) -> {
            if (player != null && component.getString().contains("%s")) {
                TagKey<Item> armorSet = itemStack.get(AetherIIDataComponents.ARMOR_SET);
                if (armorSet != null) {
                    int currentEquipmentCount = EquipmentUtil.getArmorCount(player, armorSet);
                    Component finalComponent;
                    if (currentEquipmentCount >= 3) {
                        finalComponent = Component.literal("3/3").withStyle(ChatFormatting.WHITE);
                    } else {
                        finalComponent = Component.literal(currentEquipmentCount + "/3").withStyle(ChatFormatting.GRAY);
                    }
                    return Component.translatable(component.getString(), finalComponent);
                }
            }
            return component;
        };

        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.BEAST_PELT_HELMET), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.BEAST_PELT_CHESTPLATE), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.BEAST_PELT_LEGGINGS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.BEAST_PELT_BOOTS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.BEAST_PELT_GLOVES), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.BURRUKAI_PLATE_HELMET), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.BURRUKAI_PLATE_CHESTPLATE), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.BURRUKAI_PLATE_LEGGINGS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.BURRUKAI_PLATE_BOOTS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.BURRUKAI_PLATE_GLOVES), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.ZANITE_HELMET), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.ZANITE_CHESTPLATE), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.ZANITE_LEGGINGS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.ZANITE_BOOTS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.ZANITE_GLOVES), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.ARKENIUM_HELMET), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.ARKENIUM_CHESTPLATE), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.ARKENIUM_LEGGINGS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.ARKENIUM_BOOTS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.ARKENIUM_GLOVES), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.GRAVITITE_HELMET), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.GRAVITITE_CHESTPLATE), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.GRAVITITE_LEGGINGS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.GRAVITITE_BOOTS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.GRAVITITE_GLOVES), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.NEPTUNE_HELMET), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.NEPTUNE_CHESTPLATE), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.NEPTUNE_LEGGINGS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.NEPTUNE_BOOTS), setBonusPredicate);
        TooltipListeners.PREDICATES.put(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.NEPTUNE_GLOVES), setBonusPredicate);
    }
}