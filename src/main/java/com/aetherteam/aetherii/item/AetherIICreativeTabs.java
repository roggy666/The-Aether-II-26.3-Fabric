package com.aetherteam.aetherii.item;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIIConfig;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.data.resources.registries.AetherIIEntityIds;
import com.aetherteam.aetherii.effect.buildup.EffectBuildupPresets;
import com.aetherteam.aetherii.entity.passive.Moa;
import com.aetherteam.aetherii.item.components.AetherIIDataComponents;
import com.aetherteam.aetherii.item.components.BuildupContents;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.world.item.component.ItemLore;

import java.util.List;

public class AetherIICreativeTabs {

    public static final CreativeModeTab AETHER_II_BUILDING_BLOCKS = register("building_blocks", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(AetherIIBlocks.HOLYSTONE_BRICKS))
            .title(Component.translatable("itemGroup." + AetherII.MODID + ".building_blocks"))
            .displayItems((features, output) -> {
                output.accept(AetherIIBlocks.SKYROOT_LOG);
                output.accept(AetherIIBlocks.SKYROOT_WOOD);
                output.accept(AetherIIBlocks.SKYROOT_TRUNK);
                output.accept(AetherIIBlocks.STRIPPED_SKYROOT_LOG);
                output.accept(AetherIIBlocks.STRIPPED_SKYROOT_WOOD);
                output.accept(AetherIIBlocks.STRIPPED_SKYROOT_TRUNK);
                output.accept(AetherIIBlocks.SKYROOT_PLANKS);
                output.accept(AetherIIBlocks.SKYROOT_STAIRS);
                output.accept(AetherIIBlocks.SKYROOT_SLAB);
                output.accept(AetherIIBlocks.SKYROOT_FENCE);
                output.accept(AetherIIBlocks.SKYROOT_FENCE_GATE);
                output.accept(AetherIIBlocks.SKYROOT_DOOR);
                output.accept(AetherIIBlocks.SKYROOT_TRAPDOOR);
                output.accept(AetherIIBlocks.SECRET_SKYROOT_DOOR);
                output.accept(AetherIIBlocks.SECRET_SKYROOT_TRAPDOOR);
                output.accept(AetherIIBlocks.SKYROOT_PRESSURE_PLATE);
                output.accept(AetherIIBlocks.SKYROOT_BUTTON);
                output.accept(AetherIIBlocks.SKYROOT_FLOORBOARDS);
                output.accept(AetherIIBlocks.SKYROOT_HIGHLIGHT);
                output.accept(AetherIIBlocks.SKYROOT_SHINGLES);
                output.accept(AetherIIBlocks.SKYROOT_SMALL_SHINGLES);
                output.accept(AetherIIBlocks.SKYROOT_BASE_PLANKS);
                output.accept(AetherIIBlocks.SKYROOT_TOP_PLANKS);
                output.accept(AetherIIBlocks.SKYROOT_BASE_BEAM);
                output.accept(AetherIIBlocks.SKYROOT_TOP_BEAM);
                output.accept(AetherIIBlocks.SKYROOT_BEAM);
                output.accept(AetherIIBlocks.GREATROOT_LOG);
                output.accept(AetherIIBlocks.GREATROOT_WOOD);
                output.accept(AetherIIBlocks.GREATROOT_TRUNK);
                output.accept(AetherIIBlocks.STRIPPED_GREATROOT_LOG);
                output.accept(AetherIIBlocks.STRIPPED_GREATROOT_WOOD);
                output.accept(AetherIIBlocks.STRIPPED_GREATROOT_TRUNK);
                output.accept(AetherIIBlocks.GREATROOT_PLANKS);
                output.accept(AetherIIBlocks.GREATROOT_STAIRS);
                output.accept(AetherIIBlocks.GREATROOT_SLAB);
                output.accept(AetherIIBlocks.GREATROOT_FENCE);
                output.accept(AetherIIBlocks.GREATROOT_FENCE_GATE);
                output.accept(AetherIIBlocks.GREATROOT_DOOR);
                output.accept(AetherIIBlocks.GREATROOT_TRAPDOOR);
                output.accept(AetherIIBlocks.SECRET_GREATROOT_DOOR);
                output.accept(AetherIIBlocks.SECRET_GREATROOT_TRAPDOOR);
                output.accept(AetherIIBlocks.GREATROOT_PRESSURE_PLATE);
                output.accept(AetherIIBlocks.GREATROOT_BUTTON);
                output.accept(AetherIIBlocks.GREATROOT_FLOORBOARDS);
                output.accept(AetherIIBlocks.GREATROOT_HIGHLIGHT);
                output.accept(AetherIIBlocks.GREATROOT_SHINGLES);
                output.accept(AetherIIBlocks.GREATROOT_SMALL_SHINGLES);
                output.accept(AetherIIBlocks.GREATROOT_BASE_PLANKS);
                output.accept(AetherIIBlocks.GREATROOT_TOP_PLANKS);
                output.accept(AetherIIBlocks.GREATROOT_BASE_BEAM);
                output.accept(AetherIIBlocks.GREATROOT_TOP_BEAM);
                output.accept(AetherIIBlocks.GREATROOT_BEAM);
                output.accept(AetherIIBlocks.WISPROOT_LOG);
                output.accept(AetherIIBlocks.MOSSY_WISPROOT_LOG);
                output.accept(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE);
                output.accept(AetherIIBlocks.WISPROOT_WOOD);
                output.accept(AetherIIBlocks.WISPROOT_TRUNK);
                output.accept(AetherIIBlocks.MOSSY_WISPROOT_WOOD);
                output.accept(AetherIIBlocks.MOSSY_WISPROOT_TRUNK);
                output.accept(AetherIIBlocks.STRIPPED_WISPROOT_LOG);
                output.accept(AetherIIBlocks.STRIPPED_WISPROOT_WOOD);
                output.accept(AetherIIBlocks.STRIPPED_WISPROOT_TRUNK);
                output.accept(AetherIIBlocks.WISPROOT_PLANKS);
                output.accept(AetherIIBlocks.WISPROOT_STAIRS);
                output.accept(AetherIIBlocks.WISPROOT_SLAB);
                output.accept(AetherIIBlocks.WISPROOT_FENCE);
                output.accept(AetherIIBlocks.WISPROOT_FENCE_GATE);
                output.accept(AetherIIBlocks.WISPROOT_DOOR);
                output.accept(AetherIIBlocks.WISPROOT_TRAPDOOR);
                output.accept(AetherIIBlocks.SECRET_WISPROOT_DOOR);
                output.accept(AetherIIBlocks.SECRET_WISPROOT_TRAPDOOR);
                output.accept(AetherIIBlocks.WISPROOT_PRESSURE_PLATE);
                output.accept(AetherIIBlocks.WISPROOT_BUTTON);
                output.accept(AetherIIBlocks.WISPROOT_FLOORBOARDS);
                output.accept(AetherIIBlocks.WISPROOT_HIGHLIGHT);
                output.accept(AetherIIBlocks.WISPROOT_SHINGLES);
                output.accept(AetherIIBlocks.WISPROOT_SMALL_SHINGLES);
                output.accept(AetherIIBlocks.WISPROOT_BASE_PLANKS);
                output.accept(AetherIIBlocks.WISPROOT_TOP_PLANKS);
                output.accept(AetherIIBlocks.WISPROOT_BASE_BEAM);
                output.accept(AetherIIBlocks.WISPROOT_TOP_BEAM);
                output.accept(AetherIIBlocks.WISPROOT_BEAM);
                output.accept(AetherIIBlocks.AMBEROOT_LOG);
                output.accept(AetherIIBlocks.AMBEROOT_DEPOSIT);
                output.accept(AetherIIBlocks.AMBEROOT_WOOD);
                output.accept(AetherIIBlocks.AMBEROOT_TRUNK);
                output.accept(AetherIIBlocks.STRIPPED_AMBEROOT_LOG);
                output.accept(AetherIIBlocks.STRIPPED_AMBEROOT_WOOD);
                output.accept(AetherIIBlocks.STRIPPED_AMBEROOT_TRUNK);
                output.accept(AetherIIBlocks.AMBEROOT_PLANKS);
                output.accept(AetherIIBlocks.AMBEROOT_STAIRS);
                output.accept(AetherIIBlocks.AMBEROOT_SLAB);
                output.accept(AetherIIBlocks.AMBEROOT_FENCE);
                output.accept(AetherIIBlocks.AMBEROOT_FENCE_GATE);
                output.accept(AetherIIBlocks.AMBEROOT_DOOR);
                output.accept(AetherIIBlocks.AMBEROOT_TRAPDOOR);
                output.accept(AetherIIBlocks.SECRET_AMBEROOT_DOOR);
                output.accept(AetherIIBlocks.SECRET_AMBEROOT_TRAPDOOR);
                output.accept(AetherIIBlocks.AMBEROOT_PRESSURE_PLATE);
                output.accept(AetherIIBlocks.AMBEROOT_BUTTON);
                output.accept(AetherIIBlocks.AMBEROOT_FLOORBOARDS);
                output.accept(AetherIIBlocks.AMBEROOT_HIGHLIGHT);
                output.accept(AetherIIBlocks.AMBEROOT_SHINGLES);
                output.accept(AetherIIBlocks.AMBEROOT_SMALL_SHINGLES);
                output.accept(AetherIIBlocks.AMBEROOT_BASE_PLANKS);
                output.accept(AetherIIBlocks.AMBEROOT_TOP_PLANKS);
                output.accept(AetherIIBlocks.AMBEROOT_BASE_BEAM);
                output.accept(AetherIIBlocks.AMBEROOT_TOP_BEAM);
                output.accept(AetherIIBlocks.AMBEROOT_BEAM);
                output.accept(AetherIIBlocks.CLOUDWOOL);
                output.accept(AetherIIBlocks.CLOUDWOOL_ROOFING);
                output.accept(AetherIIBlocks.HOLYSTONE);
                output.accept(AetherIIBlocks.HOLYSTONE_STAIRS);
                output.accept(AetherIIBlocks.HOLYSTONE_SLAB);
                output.accept(AetherIIBlocks.HOLYSTONE_WALL);
                output.accept(AetherIIBlocks.HOLYSTONE_PRESSURE_PLATE);
                output.accept(AetherIIBlocks.HOLYSTONE_BUTTON);
                output.accept(AetherIIBlocks.MOSSY_HOLYSTONE);
                output.accept(AetherIIBlocks.MOSSY_HOLYSTONE_STAIRS);
                output.accept(AetherIIBlocks.MOSSY_HOLYSTONE_SLAB);
                output.accept(AetherIIBlocks.MOSSY_HOLYSTONE_WALL);
                output.accept(AetherIIBlocks.IRRADIATED_HOLYSTONE);
                output.accept(AetherIIBlocks.IRRADIATED_HOLYSTONE_STAIRS);
                output.accept(AetherIIBlocks.IRRADIATED_HOLYSTONE_SLAB);
                output.accept(AetherIIBlocks.IRRADIATED_HOLYSTONE_WALL);
                output.accept(AetherIIBlocks.HOLYSTONE_BRICKS);
                output.accept(AetherIIBlocks.HOLYSTONE_BRICK_STAIRS);
                output.accept(AetherIIBlocks.HOLYSTONE_BRICK_SLAB);
                output.accept(AetherIIBlocks.HOLYSTONE_BRICK_WALL);
                output.accept(AetherIIBlocks.HOLYSTONE_FLAGSTONES);
                output.accept(AetherIIBlocks.HOLYSTONE_HEADSTONE);
                output.accept(AetherIIBlocks.HOLYSTONE_KEYSTONE);
                output.accept(AetherIIBlocks.HOLYSTONE_BASE_BRICKS);
                output.accept(AetherIIBlocks.HOLYSTONE_CAPSTONE_BRICKS);
                output.accept(AetherIIBlocks.HOLYSTONE_BASE_PILLAR);
                output.accept(AetherIIBlocks.HOLYSTONE_CAPSTONE_PILLAR);
                output.accept(AetherIIBlocks.HOLYSTONE_PILLAR);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_BRICKS);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_BRICK_STAIRS);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_BRICK_SLAB);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_BRICK_WALL);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_FLAGSTONES);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_HEADSTONE);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_BASE_BRICKS);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_BRICKS);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_BASE_PILLAR);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_PILLAR);
                output.accept(AetherIIBlocks.FADED_HOLYSTONE_PILLAR);
                output.accept(AetherIIBlocks.UNDERSHALE);
                output.accept(AetherIIBlocks.UNDERSHALE_STAIRS);
                output.accept(AetherIIBlocks.UNDERSHALE_SLAB);
                output.accept(AetherIIBlocks.UNDERSHALE_WALL);
                output.accept(AetherIIBlocks.UNDERSHALE_BRICKS);
                output.accept(AetherIIBlocks.UNDERSHALE_BRICK_STAIRS);
                output.accept(AetherIIBlocks.UNDERSHALE_BRICK_SLAB);
                output.accept(AetherIIBlocks.UNDERSHALE_BRICK_WALL);
                output.accept(AetherIIBlocks.UNDERSHALE_BRICK_PRESSURE_PLATE);
                output.accept(AetherIIBlocks.UNDERSHALE_BRICK_BUTTON);
                output.accept(AetherIIBlocks.UNDERSHALE_FLAGSTONES);
                output.accept(AetherIIBlocks.UNDERSHALE_TILE);
                output.accept(AetherIIBlocks.UNDERSHALE_BASE_BRICKS);
                output.accept(AetherIIBlocks.UNDERSHALE_CAPSTONE_BRICKS);
                output.accept(AetherIIBlocks.UNDERSHALE_BASE_PILLAR);
                output.accept(AetherIIBlocks.UNDERSHALE_CAPSTONE_PILLAR);
                output.accept(AetherIIBlocks.UNDERSHALE_PILLAR);
                output.accept(AetherIIBlocks.SENTRY_BRICKS);
                output.accept(AetherIIBlocks.SENTRY_BRICK_STAIRS);
                output.accept(AetherIIBlocks.SENTRY_BRICK_SLAB);
                output.accept(AetherIIBlocks.SENTRY_BRICK_WALL);
                output.accept(AetherIIBlocks.SENTRY_BUTTON);
                output.accept(AetherIIBlocks.SENTRY_LIGHTSTONE);
                output.accept(AetherIIBlocks.SENTRY_FLAGSTONES);
                output.accept(AetherIIBlocks.SENTRY_TILE);
                output.accept(AetherIIBlocks.SENTRY_BASE_BRICKS);
                output.accept(AetherIIBlocks.SENTRY_CAPSTONE_BRICKS);
                output.accept(AetherIIBlocks.SENTRY_BASE_PILLAR);
                output.accept(AetherIIBlocks.SENTRY_CAPSTONE_PILLAR);
                output.accept(AetherIIBlocks.SENTRY_PILLAR);
                output.accept(AetherIIBlocks.ICHORITE);
                output.accept(AetherIIBlocks.ICHORITE_STAIRS);
                output.accept(AetherIIBlocks.ICHORITE_SLAB);
                output.accept(AetherIIBlocks.ICHORITE_WALL);
                output.accept(AetherIIBlocks.SMOOTH_ICHORITE);
                output.accept(AetherIIBlocks.SMOOTH_ICHORITE_STAIRS);
                output.accept(AetherIIBlocks.SMOOTH_ICHORITE_SLAB);
                output.accept(AetherIIBlocks.SMOOTH_ICHORITE_WALL);
                output.accept(AetherIIBlocks.ICHORITE_BRICKS);
                output.accept(AetherIIBlocks.ICHORITE_BRICK_STAIRS);
                output.accept(AetherIIBlocks.ICHORITE_BRICK_SLAB);
                output.accept(AetherIIBlocks.ICHORITE_BRICK_WALL);
                output.accept(AetherIIBlocks.ICHORITE_FLAGSTONES);
                output.accept(AetherIIBlocks.ICHORITE_RUNESTONE);
                output.accept(AetherIIBlocks.ICHORITE_KEYSTONE);
                output.accept(AetherIIBlocks.ICHORITE_BASE_BRICKS);
                output.accept(AetherIIBlocks.ICHORITE_CAPSTONE_BRICKS);
                output.accept(AetherIIBlocks.ICHORITE_BASE_PILLAR);
                output.accept(AetherIIBlocks.ICHORITE_CAPSTONE_PILLAR);
                output.accept(AetherIIBlocks.ICHORITE_PILLAR);
                output.accept(AetherIIBlocks.MARBLED_ICHORITE);
                output.accept(AetherIIBlocks.MARBLED_ICHORITE_STAIRS);
                output.accept(AetherIIBlocks.MARBLED_ICHORITE_SLAB);
                output.accept(AetherIIBlocks.MARBLED_ICHORITE_WALL);
                output.accept(AetherIIBlocks.MARBLED_BRICKS);
                output.accept(AetherIIBlocks.MARBLED_BRICK_STAIRS);
                output.accept(AetherIIBlocks.MARBLED_BRICK_SLAB);
                output.accept(AetherIIBlocks.MARBLED_BRICK_WALL);
                output.accept(AetherIIBlocks.MARBLED_FLAGSTONES);
                output.accept(AetherIIBlocks.MARBLED_KEYSTONE);
                output.accept(AetherIIBlocks.MARBLED_BASE_BRICKS);
                output.accept(AetherIIBlocks.MARBLED_CAPSTONE_BRICKS);
                output.accept(AetherIIBlocks.MARBLED_BASE_PILLAR);
                output.accept(AetherIIBlocks.MARBLED_CAPSTONE_PILLAR);
                output.accept(AetherIIBlocks.MARBLED_PILLAR);
                output.accept(AetherIIBlocks.AGIOSITE);
                output.accept(AetherIIBlocks.AGIOSITE_STAIRS);
                output.accept(AetherIIBlocks.AGIOSITE_SLAB);
                output.accept(AetherIIBlocks.AGIOSITE_WALL);
                output.accept(AetherIIBlocks.AGIOSITE_BRICKS);
                output.accept(AetherIIBlocks.AGIOSITE_BRICK_STAIRS);
                output.accept(AetherIIBlocks.AGIOSITE_BRICK_SLAB);
                output.accept(AetherIIBlocks.AGIOSITE_BRICK_WALL);
                output.accept(AetherIIBlocks.AGIOSITE_FLAGSTONES);
                output.accept(AetherIIBlocks.AGIOSITE_KEYSTONE);
                output.accept(AetherIIBlocks.AGIOSITE_BASE_BRICKS);
                output.accept(AetherIIBlocks.AGIOSITE_CAPSTONE_BRICKS);
                output.accept(AetherIIBlocks.AGIOSITE_BASE_PILLAR);
                output.accept(AetherIIBlocks.AGIOSITE_CAPSTONE_PILLAR);
                output.accept(AetherIIBlocks.AGIOSITE_PILLAR);
                output.accept(AetherIIBlocks.ICESTONE);
                output.accept(AetherIIBlocks.ICESTONE_STAIRS);
                output.accept(AetherIIBlocks.ICESTONE_SLAB);
                output.accept(AetherIIBlocks.ICESTONE_WALL);
                output.accept(AetherIIBlocks.ICESTONE_BRICKS);
                output.accept(AetherIIBlocks.ICESTONE_BRICK_STAIRS);
                output.accept(AetherIIBlocks.ICESTONE_BRICK_SLAB);
                output.accept(AetherIIBlocks.ICESTONE_BRICK_WALL);
                output.accept(AetherIIBlocks.ICESTONE_FLAGSTONES);
                output.accept(AetherIIBlocks.ICESTONE_KEYSTONE);
                output.accept(AetherIIBlocks.ICESTONE_BASE_BRICKS);
                output.accept(AetherIIBlocks.ICESTONE_CAPSTONE_BRICKS);
                output.accept(AetherIIBlocks.ICESTONE_BASE_PILLAR);
                output.accept(AetherIIBlocks.ICESTONE_CAPSTONE_PILLAR);
                output.accept(AetherIIBlocks.ICESTONE_PILLAR);
                output.accept(AetherIIBlocks.QUICKSOIL_GLASS);
                output.accept(AetherIIBlocks.TILED_QUICKSOIL_GLASS);
                output.accept(AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS);
                output.accept(AetherIIBlocks.QUICKSOIL_GLASS_PANE);
                output.accept(AetherIIBlocks.TILED_QUICKSOIL_GLASS_PANE);
                output.accept(AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS_PANE);
                output.accept(AetherIIBlocks.CRUDE_SCATTERGLASS);
                output.accept(AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS);
                output.accept(AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS);
                output.accept(AetherIIBlocks.CRUDE_SCATTERGLASS_PANE);
                output.accept(AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS_PANE);
                output.accept(AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS_PANE);
                output.accept(AetherIIBlocks.SCATTERGLASS);
                output.accept(AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS);
                output.accept(AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS);
                output.accept(AetherIIBlocks.SCATTERGLASS_PANE);
                output.accept(AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS_PANE);
                output.accept(AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS_PANE);
                output.accept(AetherIIBlocks.ARKENIUM_DOOR);
                output.accept(AetherIIBlocks.ARKENIUM_TRAPDOOR);
                output.accept(AetherIIBlocks.ARKENIUM_BARS);
                output.accept(AetherIIBlocks.FLORAL_ARKENIUM_BARS);
                output.accept(AetherIIBlocks.PATTERNED_ARKENIUM_BARS);
                output.accept(AetherIIBlocks.CURVED_ARKENIUM_BARS);
                output.accept(AetherIIBlocks.RUSTIC_ARKENIUM_BARS);
                output.accept(AetherIIBlocks.RUSTIC_FLORAL_ARKENIUM_BARS);
                output.accept(AetherIIBlocks.RUSTIC_PATTERNED_ARKENIUM_BARS);
                output.accept(AetherIIBlocks.RUSTIC_CURVED_ARKENIUM_BARS);
                output.accept(AetherIIBlocks.AMBROSIUM_BLOCK);
                output.accept(AetherIIBlocks.ZANITE_BLOCK);
                output.accept(AetherIIBlocks.ARKENIUM_BLOCK);
                output.accept(AetherIIBlocks.GRAVITITE_BLOCK);
                output.accept(AetherIIBlocks.GLINT_BLOCK);
                output.accept(AetherIIBlocks.CORROBONITE_BLOCK);
                output.accept(AetherIIBlocks.GOLDEN_AMBER_BLOCK);
            }));

    public static final CreativeModeTab AETHER_II_COLORED_BLOCKS = register("colored_blocks", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(AetherIIBlocks.PURPLE_CLOUDWOOL))
            .title(Component.translatable("itemGroup." + AetherII.MODID + ".colored_blocks"))
            .displayItems((features, output) -> {
                output.accept(AetherIIBlocks.CLOUDWOOL);
                output.accept(AetherIIBlocks.WHITE_CLOUDWOOL);
                output.accept(AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL);
                output.accept(AetherIIBlocks.GRAY_CLOUDWOOL);
                output.accept(AetherIIBlocks.BLACK_CLOUDWOOL);
                output.accept(AetherIIBlocks.BROWN_CLOUDWOOL);
                output.accept(AetherIIBlocks.RED_CLOUDWOOL);
                output.accept(AetherIIBlocks.ORANGE_CLOUDWOOL);
                output.accept(AetherIIBlocks.YELLOW_CLOUDWOOL);
                output.accept(AetherIIBlocks.LIME_CLOUDWOOL);
                output.accept(AetherIIBlocks.GREEN_CLOUDWOOL);
                output.accept(AetherIIBlocks.CYAN_CLOUDWOOL);
                output.accept(AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL);
                output.accept(AetherIIBlocks.BLUE_CLOUDWOOL);
                output.accept(AetherIIBlocks.PURPLE_CLOUDWOOL);
                output.accept(AetherIIBlocks.MAGENTA_CLOUDWOOL);
                output.accept(AetherIIBlocks.PINK_CLOUDWOOL);
                output.accept(AetherIIBlocks.CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.WHITE_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.GRAY_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.BLACK_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.BROWN_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.RED_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.ORANGE_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.YELLOW_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.LIME_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.GREEN_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.CYAN_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.BLUE_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.PURPLE_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.MAGENTA_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.PINK_CLOUDWOOL_CARPET);
                output.accept(AetherIIBlocks.WHITE_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.LIGHT_GRAY_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.GRAY_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.BLACK_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.BROWN_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.RED_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.ORANGE_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.YELLOW_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.LIME_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.GREEN_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.CYAN_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.LIGHT_BLUE_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.BLUE_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.PURPLE_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.MAGENTA_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.PINK_ARILUM_LANTERN);
                output.accept(AetherIIBlocks.SKYROOT_BED);
                output.accept(AetherIIBlocks.WHITE_SKYROOT_BED);
                output.accept(AetherIIBlocks.LIGHT_GRAY_SKYROOT_BED);
                output.accept(AetherIIBlocks.GRAY_SKYROOT_BED);
                output.accept(AetherIIBlocks.BLACK_SKYROOT_BED);
                output.accept(AetherIIBlocks.BROWN_SKYROOT_BED);
                output.accept(AetherIIBlocks.RED_SKYROOT_BED);
                output.accept(AetherIIBlocks.ORANGE_SKYROOT_BED);
                output.accept(AetherIIBlocks.YELLOW_SKYROOT_BED);
                output.accept(AetherIIBlocks.LIME_SKYROOT_BED);
                output.accept(AetherIIBlocks.GREEN_SKYROOT_BED);
                output.accept(AetherIIBlocks.CYAN_SKYROOT_BED);
                output.accept(AetherIIBlocks.LIGHT_BLUE_SKYROOT_BED);
                output.accept(AetherIIBlocks.BLUE_SKYROOT_BED);
                output.accept(AetherIIBlocks.PURPLE_SKYROOT_BED);
                output.accept(AetherIIBlocks.MAGENTA_SKYROOT_BED);
                output.accept(AetherIIBlocks.PINK_SKYROOT_BED);
            }));

    public static final CreativeModeTab AETHER_II_NATURAL_BLOCKS = register("natural_blocks", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(AetherIIBlocks.AETHER_GRASS_BLOCK))
            .title(Component.translatable("itemGroup." + AetherII.MODID + ".natural_blocks"))
            .displayItems((features, output) -> {
                output.accept(AetherIIBlocks.AETHER_GRASS_BLOCK);
                output.accept(AetherIIBlocks.ENCHANTED_AETHER_GRASS_BLOCK);
                output.accept(AetherIIBlocks.AETHER_DIRT_PATH);
                output.accept(AetherIIBlocks.AETHER_DIRT);
                output.accept(AetherIIBlocks.COARSE_AETHER_DIRT);
                output.accept(AetherIIBlocks.MYCELIAL_AETHER_DIRT);
                output.accept(AetherIIBlocks.AETHER_FARMLAND);
                output.accept(AetherIIBlocks.SHIMMERING_SILT);
                output.accept(AetherIIBlocks.QUICKSOIL);
                output.accept(AetherIIBlocks.FERROSITE_SAND);
                output.accept(AetherIIBlocks.FERROSITE_MUD);
                output.accept(AetherIIBlocks.ARCTIC_ICE);
                output.accept(AetherIIBlocks.FRAGILE_ARCTIC_ICE);
                output.accept(AetherIIBlocks.ARCTIC_PACKED_ICE);
                output.accept(AetherIIBlocks.ARCTIC_SNOW_BLOCK);
                output.accept(AetherIIBlocks.ARCTIC_SNOW);
                output.accept(AetherIIBlocks.IRRADIATED_DUST_BLOCK);
                output.accept(AetherIIBlocks.HOLYSTONE);
                output.accept(AetherIIBlocks.UNSTABLE_HOLYSTONE);
                output.accept(AetherIIBlocks.MOSSY_HOLYSTONE);
                output.accept(AetherIIBlocks.IRRADIATED_HOLYSTONE);
                output.accept(AetherIIBlocks.UNDERSHALE);
                output.accept(AetherIIBlocks.UNSTABLE_UNDERSHALE);
                output.accept(AetherIIBlocks.ICHORITE);
                output.accept(AetherIIBlocks.AGIOSITE);
                output.accept(AetherIIBlocks.FERROSITE);
                output.accept(AetherIIBlocks.RUSTED_FERROSITE);
                output.accept(AetherIIBlocks.ICESTONE);
                output.accept(AetherIIBlocks.CRUDE_SCATTERGLASS);
                output.accept(AetherIIBlocks.HOLYSTONE_QUARTZ_ORE);
                output.accept(AetherIIBlocks.AMBROSIUM_ORE);
                output.accept(AetherIIBlocks.ZANITE_ORE);
                output.accept(AetherIIBlocks.ARKENIUM_ORE);
                output.accept(AetherIIBlocks.GRAVITITE_ORE);
                output.accept(AetherIIBlocks.GLINT_ORE);
                output.accept(AetherIIBlocks.UNDERSHALE_AMBROSIUM_ORE);
                output.accept(AetherIIBlocks.UNDERSHALE_ZANITE_ORE);
                output.accept(AetherIIBlocks.UNDERSHALE_ARKENIUM_ORE);
                output.accept(AetherIIBlocks.UNDERSHALE_GRAVITITE_ORE);
                output.accept(AetherIIBlocks.UNDERSHALE_GLINT_ORE);
                output.accept(AetherIIBlocks.CORROBONITE_ORE);
                output.accept(AetherIIBlocks.CORROBONITE_CLUSTER);
                output.accept(AetherIIBlocks.INERT_ARKENIUM_BLOCK);
                output.accept(AetherIIBlocks.INERT_GRAVITITE_BLOCK);
                output.accept(AetherIIBlocks.COLD_AERCLOUD);
                output.accept(AetherIIBlocks.BLUE_AERCLOUD);
                output.accept(AetherIIBlocks.GREEN_AERCLOUD);
                output.accept(AetherIIBlocks.PURPLE_AERCLOUD);
                output.accept(AetherIIBlocks.GOLDEN_AERCLOUD);
                output.accept(AetherIIBlocks.STORM_AERCLOUD);
                output.accept(AetherIIBlocks.WOVEN_SKYROOT_STICKS);
                output.accept(AetherIIBlocks.ANIMAL_STASH);
                output.accept(AetherIIBlocks.TANGLED_BRANCHES);
                output.accept(AetherIIBlocks.SKYROOT_LOG);
                output.accept(AetherIIBlocks.SKYROOT_TRUNK);
                output.accept(AetherIIBlocks.GREATROOT_LOG);
                output.accept(AetherIIBlocks.GREATROOT_TRUNK);
                output.accept(AetherIIBlocks.WISPROOT_LOG);
                output.accept(AetherIIBlocks.WISPROOT_TRUNK);
                output.accept(AetherIIBlocks.MOSSY_WISPROOT_LOG);
                output.accept(AetherIIBlocks.MOSSY_WISPROOT_TRUNK);
                output.accept(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE);
                output.accept(AetherIIBlocks.AMBEROOT_LOG);
                output.accept(AetherIIBlocks.AMBEROOT_DEPOSIT);
                output.accept(AetherIIBlocks.AMBEROOT_TRUNK);
                output.accept(AetherIIBlocks.SKYROOT_LEAVES);
                output.accept(AetherIIBlocks.SKYPLANE_LEAVES);
                output.accept(AetherIIBlocks.SKYBIRCH_LEAVES);
                output.accept(AetherIIBlocks.SKYPINE_LEAVES);
                output.accept(AetherIIBlocks.WISPROOT_LEAVES);
                output.accept(AetherIIBlocks.WISPTOP_LEAVES);
                output.accept(AetherIIBlocks.GREATROOT_LEAVES);
                output.accept(AetherIIBlocks.GREATOAK_LEAVES);
                output.accept(AetherIIBlocks.GREATBOA_LEAVES);
                output.accept(AetherIIBlocks.AMBEROOT_LEAVES);
                output.accept(AetherIIBlocks.IRRADIATED_SKYROOT_LEAVES);
                output.accept(AetherIIBlocks.IRRADIATED_SKYPLANE_LEAVES);
                output.accept(AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAVES);
                output.accept(AetherIIBlocks.IRRADIATED_SKYPINE_LEAVES);
                output.accept(AetherIIBlocks.IRRADIATED_WISPROOT_LEAVES);
                output.accept(AetherIIBlocks.IRRADIATED_WISPTOP_LEAVES);
                output.accept(AetherIIBlocks.IRRADIATED_GREATROOT_LEAVES);
                output.accept(AetherIIBlocks.IRRADIATED_GREATOAK_LEAVES);
                output.accept(AetherIIBlocks.IRRADIATED_GREATBOA_LEAVES);
                output.accept(AetherIIBlocks.SKYROOT_LEAF_PILE);
                output.accept(AetherIIBlocks.SKYPLANE_LEAF_PILE);
                output.accept(AetherIIBlocks.SKYBIRCH_LEAF_PILE);
                output.accept(AetherIIBlocks.SKYPINE_LEAF_PILE);
                output.accept(AetherIIBlocks.WISPROOT_LEAF_PILE);
                output.accept(AetherIIBlocks.WISPTOP_LEAF_PILE);
                output.accept(AetherIIBlocks.GREATROOT_LEAF_PILE);
                output.accept(AetherIIBlocks.GREATOAK_LEAF_PILE);
                output.accept(AetherIIBlocks.GREATBOA_LEAF_PILE);
                output.accept(AetherIIBlocks.AMBEROOT_LEAF_PILE);
                output.accept(AetherIIBlocks.IRRADIATED_SKYROOT_LEAF_PILE);
                output.accept(AetherIIBlocks.IRRADIATED_SKYPLANE_LEAF_PILE);
                output.accept(AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAF_PILE);
                output.accept(AetherIIBlocks.IRRADIATED_SKYPINE_LEAF_PILE);
                output.accept(AetherIIBlocks.IRRADIATED_WISPROOT_LEAF_PILE);
                output.accept(AetherIIBlocks.IRRADIATED_WISPTOP_LEAF_PILE);
                output.accept(AetherIIBlocks.IRRADIATED_GREATROOT_LEAF_PILE);
                output.accept(AetherIIBlocks.IRRADIATED_GREATOAK_LEAF_PILE);
                output.accept(AetherIIBlocks.IRRADIATED_GREATBOA_LEAF_PILE);
                output.accept(AetherIIBlocks.SKYROOT_SAPLING);
                output.accept(AetherIIBlocks.SKYPLANE_SAPLING);
                output.accept(AetherIIBlocks.SKYBIRCH_SAPLING);
                output.accept(AetherIIBlocks.SKYPINE_SAPLING);
                output.accept(AetherIIBlocks.WISPROOT_SAPLING);
                output.accept(AetherIIBlocks.WISPTOP_SAPLING);
                output.accept(AetherIIBlocks.GREATROOT_SAPLING);
                output.accept(AetherIIBlocks.GREATOAK_SAPLING);
                output.accept(AetherIIBlocks.GREATBOA_SAPLING);
                output.accept(AetherIIBlocks.AMBEROOT_SAPLING);
                output.accept(AetherIIBlocks.SHORT_AETHER_GRASS);
                output.accept(AetherIIBlocks.MEDIUM_AETHER_GRASS);
                output.accept(AetherIIBlocks.TALL_AETHER_GRASS);
                output.accept(AetherIIBlocks.AETHER_FERN);
                output.accept(AetherIIBlocks.SHIELD_FERN);
                output.accept(AetherIIBlocks.BLADE_POA);
                output.accept(AetherIIBlocks.TARABLOOM);
                output.accept(AetherIIBlocks.HESPEROSE);
                output.accept(AetherIIBlocks.POASPROUT);
                output.accept(AetherIIBlocks.LILICHIME);
                output.accept(AetherIIBlocks.PLURACIAN);
                output.accept(AetherIIBlocks.SATIVAL_SHOOT);
                output.accept(AetherIIBlocks.BRETTL_FLOWER);
                output.accept(AetherIIBlocks.HOLPUPEA);
                output.accept(AetherIIBlocks.AECHOR_CUTTING);
                output.accept(AetherIIBlocks.CARRION_CUTTING);
                output.accept(AetherIIBlocks.AETHER_BUSH);
                output.accept(AetherIIBlocks.BLUEBERRY_BUSH_STEM);
                output.accept(AetherIIBlocks.BLUEBERRY_BUSH);
                output.accept(AetherIIBlocks.ORANGE_TREE);
                output.accept(AetherIIBlocks.VALKYRIE_SPROUT);
                output.accept(AetherIIItems.ARILUM_BULBS);
                output.accept(AetherIIBlocks.ARILUM);
                output.accept(AetherIIBlocks.BLOOMING_ARILUM);
                output.accept(AetherIIBlocks.MAGNETIC_SHROOM_BLOCK);
                output.accept(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK);
                output.accept(AetherIIBlocks.MAGNETIC_SHROOM_STEM);
                output.accept(AetherIIBlocks.MAGNETIC_SHROOM);
                output.accept(AetherIIItems.BRETTL_CANE);
                output.accept(AetherIIBlocks.BRETTL_GRASS_BUNDLE);
                output.accept(AetherIIBlocks.GEL_BLOCK);
                output.accept(AetherIIBlocks.BRYALINN_MOSS_BLOCK);
                output.accept(AetherIIBlocks.BRYALINN_MOSS_CARPET);
                output.accept(AetherIIBlocks.BRYALINN_MOSS_VINES);
                output.accept(AetherIIBlocks.BRYALINN_MOSS_FLOWERS);
                output.accept(AetherIIBlocks.SHAYELINN_MOSS_BLOCK);
                output.accept(AetherIIBlocks.SHAYELINN_MOSS_CARPET);
                output.accept(AetherIIBlocks.SHAYELINN_MOSS_VINES);
                output.accept(AetherIIBlocks.AMBRELINN_MOSS_BLOCK);
                output.accept(AetherIIBlocks.AMBRELINN_MOSS_CARPET);
                output.accept(AetherIIBlocks.AMBRELINN_MOSS_VINES);
                output.accept(AetherIIBlocks.TARAHESP_FLOWERS);
                output.accept(AetherIIBlocks.SKY_ROOTS);
                output.accept(AetherIIBlocks.SKYROOT_TWIG);
                output.accept(AetherIIBlocks.HOLYSTONE_ROCK);
                output.accept(AetherIIBlocks.POINTED_HOLYSTONE);
                output.accept(AetherIIBlocks.POINTED_ICHORITE);
                output.accept(AetherIIBlocks.SMALL_ARCTIC_ICE_CRYSTAL);
                output.accept(AetherIIBlocks.MEDIUM_ARCTIC_ICE_CRYSTAL);
                output.accept(AetherIIBlocks.LARGE_ARCTIC_ICE_CRYSTAL);
            }));

    public static final CreativeModeTab AETHER_II_FUNCTIONAL_BLOCKS = register("functional_blocks", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(AetherIIBlocks.ARTISANS_BENCH))
            .title(Component.translatable("itemGroup." + AetherII.MODID + ".functional_blocks"))
            .displayItems((features, output) -> {
                output.accept(AetherIIBlocks.AMBROSIUM_TORCH);
                output.accept(AetherIIBlocks.ARKENIUM_LANTERN);
                output.accept(AetherIIBlocks.RUSTIC_ARKENIUM_LANTERN);
                output.accept(AetherIIBlocks.ARKENIUM_CHAIN);
                output.accept(AetherIIBlocks.HOLYSTONE_LEVER);
                output.accept(AetherIIBlocks.SKYROOT_CRAFTING_TABLE);
                output.accept(AetherIIBlocks.HOLYSTONE_FURNACE);
                output.accept(AetherIIBlocks.HOLYSTONE_SMOKER);
                output.accept(AetherIIBlocks.AMBER_HOURGLASS);
                output.accept(AetherIIBlocks.ALTAR);
                output.accept(AetherIIBlocks.ARTISANS_BENCH);
                output.accept(AetherIIBlocks.ARKENIUM_FORGE);
                output.accept(AetherIIBlocks.ALKAHEST_PURIFIER);
                output.accept(AetherIIBlocks.AMBROSIUM_CAMPFIRE);
                output.accept(AetherIIBlocks.SKYROOT_LADDER);
                output.accept(AetherIIBlocks.SKYROOT_BOOKSHELF);
                output.accept(AetherIIBlocks.GREATROOT_BOOKSHELF);
                output.accept(AetherIIBlocks.WISPROOT_BOOKSHELF);
                output.accept(AetherIIBlocks.AMBEROOT_BOOKSHELF);
                output.accept(AetherIIBlocks.HOLYSTONE_BOOKSHELF);
                output.accept(AetherIIBlocks.SKYROOT_SHELF);
                output.accept(AetherIIBlocks.GREATROOT_SHELF);
                output.accept(AetherIIBlocks.WISPROOT_SHELF);
                output.accept(AetherIIBlocks.AMBEROOT_SHELF);
                output.accept(AetherIIBlocks.SKYROOT_CHEST);
                output.accept(AetherIIBlocks.SKYROOT_BARREL);
                output.accept(AetherIIBlocks.SENTRY_CRATE);
                output.accept(AetherIIBlocks.SENTRY_SPAWNER);
                output.accept(AetherIIBlocks.SENTRY_TRAP);
                output.accept(AetherIIBlocks.HOLYSTONE_VASE);
                output.accept(AetherIIBlocks.VERADEXIAN_VASE);
                output.accept(AetherIIBlocks.BREXALLEN_VASE);
                output.accept(AetherIIBlocks.SKYROOT_SIGN);
                output.accept(AetherIIBlocks.SKYROOT_HANGING_SIGN);
                output.accept(AetherIIBlocks.GREATROOT_SIGN);
                output.accept(AetherIIBlocks.GREATROOT_HANGING_SIGN);
                output.accept(AetherIIBlocks.WISPROOT_SIGN);
                output.accept(AetherIIBlocks.WISPROOT_HANGING_SIGN);
                output.accept(AetherIIBlocks.AMBEROOT_SIGN);
                output.accept(AetherIIBlocks.AMBEROOT_HANGING_SIGN);
                output.accept(AetherIIBlocks.CLOUDWOOL_BEDROLL);
                output.accept(AetherIIBlocks.SKYROOT_BED);
                output.accept(AetherIIBlocks.OUTPOST_CAMPFIRE);
                output.accept(AetherIIBlocks.UNSTABLE_HOLYSTONE);
                output.accept(AetherIIBlocks.UNSTABLE_UNDERSHALE);
                output.accept(AetherIIItems.AETHER_PORTAL_FRAME);
            }));

    public static final CreativeModeTab AETHER_II_DUNGEON_BLOCKS = register("dungeon_blocks", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(AetherIIBlocks.GUARDIAN_LAMP))
            .title(Component.translatable("itemGroup." + AetherII.MODID + ".dungeon_blocks"))
            .displayItems((features, output) -> {
                if (AetherIIConfig.COMMON.experimental_dungeon_content.get()) {
                    output.accept(AetherIIBlocks.GUARDIAN_LOG);
                    output.accept(AetherIIBlocks.GUARDIAN_LOG_SLAB);
                    output.accept(AetherIIBlocks.GUARDIAN_WOOD);
                    output.accept(AetherIIBlocks.GUARDIAN_WOOD_SLAB);
                    output.accept(AetherIIBlocks.GUARDIAN_TRUNK);
                    output.accept(AetherIIBlocks.STRIPPED_GUARDIAN_LOG);
                    output.accept(AetherIIBlocks.STRIPPED_GUARDIAN_LOG_SLAB);
                    output.accept(AetherIIBlocks.STRIPPED_GUARDIAN_WOOD);
                    output.accept(AetherIIBlocks.STRIPPED_GUARDIAN_WOOD_SLAB);
                    output.accept(AetherIIBlocks.STRIPPED_GUARDIAN_TRUNK);
                    output.accept(AetherIIBlocks.INFECTED_LOG);
                    output.accept(AetherIIBlocks.INFECTED_LOG_SLAB);
                    output.accept(AetherIIBlocks.INFECTED_WOOD);
                    output.accept(AetherIIBlocks.INFECTED_WOOD_SLAB);
                    output.accept(AetherIIBlocks.INFECTED_TRUNK);
                    output.accept(AetherIIBlocks.STRIPPED_INFECTED_LOG);
                    output.accept(AetherIIBlocks.STRIPPED_INFECTED_LOG_SLAB);
                    output.accept(AetherIIBlocks.STRIPPED_INFECTED_WOOD);
                    output.accept(AetherIIBlocks.STRIPPED_INFECTED_WOOD_SLAB);
                    output.accept(AetherIIBlocks.STRIPPED_INFECTED_TRUNK);
                    output.accept(AetherIIBlocks.GUARDIAN_ROOTS);
                    output.accept(AetherIIBlocks.UNSTABLE_GUARDIAN_ROOTS);
                    output.accept(AetherIIBlocks.LUCENT_GUARDIAN_ROOTS);
                    output.accept(AetherIIBlocks.GUARDIAN_LAMP);
                    output.accept(AetherIIBlocks.UNDERGROWTH_LEAVES);
                    output.accept(AetherIIBlocks.UNDERGROWTH_VINES);
                    output.accept(AetherIIBlocks.HANGING_UNDERGROWTH);
                    output.accept(AetherIIBlocks.ROTSHROOM_BLOCK);
                    output.accept(AetherIIBlocks.ROTSHROOM_SLAB);
                    output.accept(AetherIIBlocks.ROTSHROOM_STEM);
                    output.accept(AetherIIBlocks.SHELF_ROTSHROOM_SLAB);
                    output.accept(AetherIIBlocks.ROTSHROOM);
                    output.accept(AetherIIBlocks.ROTSHROOM_CLUSTER);
                    output.accept(AetherIIBlocks.ROTSHROOM_TOADSTOOL);
                    output.accept(AetherIIBlocks.SHELF_ROTSHROOM);
                    output.accept(AetherIIBlocks.ROTGROWTH_VINES);
                    output.accept(AetherIIBlocks.PRAYER_CANDLE);
                    output.accept(AetherIIBlocks.GUARDIAN_PEW);
                    output.accept(AetherIIBlocks.GUARDIAN_DONATION_BOX);
                    output.accept(AetherIIBlocks.ABANDONED_BAG);
                    output.accept(AetherIIBlocks.FUNGAL_CACHE);
                    output.accept(AetherIIBlocks.SAGE_CHEST);
                }
            }));

    public static final CreativeModeTab AETHER_II_TOOLS_AND_UTILITIES = register("tools_and_utilities", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(AetherIIItems.GRAVITITE_PICKAXE))
            .title(Component.translatable("itemGroup." + AetherII.MODID + ".tools_and_utilities"))
            .displayItems((features, output) -> {
                output.accept(AetherIIItems.SKYROOT_SHOVEL);
                output.accept(AetherIIItems.SKYROOT_PICKAXE);
                output.accept(AetherIIItems.SKYROOT_AXE);
                output.accept(AetherIIItems.SKYROOT_TROWEL);
                output.accept(AetherIIItems.HOLYSTONE_SHOVEL);
                output.accept(AetherIIItems.HOLYSTONE_PICKAXE);
                output.accept(AetherIIItems.HOLYSTONE_AXE);
                output.accept(AetherIIItems.HOLYSTONE_TROWEL);
                output.accept(AetherIIItems.ZANITE_SHOVEL);
                output.accept(AetherIIItems.ZANITE_PICKAXE);
                output.accept(AetherIIItems.ZANITE_AXE);
                output.accept(AetherIIItems.ZANITE_TROWEL);
                output.accept(AetherIIItems.ARKENIUM_SHOVEL);
                output.accept(AetherIIItems.ARKENIUM_PICKAXE);
                output.accept(AetherIIItems.ARKENIUM_AXE);
                output.accept(AetherIIItems.ARKENIUM_TROWEL);
                output.accept(AetherIIItems.GRAVITITE_SHOVEL);
                output.accept(AetherIIItems.GRAVITITE_PICKAXE);
                output.accept(AetherIIItems.GRAVITITE_AXE);
                output.accept(AetherIIItems.GRAVITITE_TROWEL);
                output.accept(AetherIIItems.ZANITE_SHEARS);
                output.accept(AetherIIItems.SKYROOT_BUCKET);
                output.accept(AetherIIItems.SKYROOT_WATER_BUCKET);
                output.accept(AetherIIItems.SKYROOT_MILK_BUCKET);
                output.accept(AetherIIItems.ARKENIUM_CANISTER);
                output.accept(AetherIIItems.ARKENIUM_ALKAHEST_CANISTER);
                output.accept(AetherIIItems.ARKENIUM_HESTVEIL_CANISTER);
                output.accept(AetherIIItems.COLD_AERCLOUD_GLIDER);
                output.accept(AetherIIItems.GOLDEN_AERCLOUD_GLIDER);
                output.accept(AetherIIItems.BLUE_AERCLOUD_GLIDER);
                output.accept(AetherIIItems.PURPLE_AERCLOUD_GLIDER);
                output.accept(AetherIIItems.SHIFTING_GLASS);
                output.accept(AetherIIItems.AERBUNNY_BELL);
                output.accept(AetherIIItems.BEAST_PELT_BUNDLE);
                output.accept(AetherIIItems.BRETTL_LASSO);
                output.accept(AetherIIItems.MOA_SADDLE);
                output.accept(AetherIIItems.MOA_SADDLEBAG);
                output.accept(AetherIIItems.LARGE_MOA_SADDLEBAG);
                output.accept(AetherIIItems.CLOUD_SKIFF);
                output.accept(AetherIIItems.MUSIC_PLAYER);
                output.accept(AetherIIItems.ENGRAVED_DISC_ASCENDING_DAWN);
                output.accept(AetherIIItems.ENGRAVED_DISC_AERWHALE);
                output.accept(AetherIIItems.ENGRAVED_DISC_APPROACHES);
                output.accept(AetherIIItems.ENGRAVED_DISC_DEMISE);
                output.accept(AetherIIItems.ENGRAVED_DISC_CHINCHILLA);
                output.accept(AetherIIItems.ENGRAVED_DISC_HIGH);
                output.accept(AetherIIItems.ENGRAVED_DISC_REVOLUTIONS);
                output.accept(AetherIIItems.GLINT_COIN);
                output.accept(AetherIIItems.GUIDEBOOK_PAGE);
            }));

    public static final CreativeModeTab AETHER_II_COMBAT_AND_EQUIPMENT = register("combat_and_equipment", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(AetherIIItems.ARKENIUM_SHORTSWORD))
            .title(Component.translatable("itemGroup." + AetherII.MODID + ".combat_and_equipment"))
            .displayItems((features, output) -> {
                output.accept(AetherIIItems.SKYROOT_SHORTSWORD);
                output.accept(AetherIIItems.HOLYSTONE_SHORTSWORD);
                output.accept(AetherIIItems.ZANITE_SHORTSWORD);
                output.accept(AetherIIItems.ARKENIUM_SHORTSWORD);
                output.accept(AetherIIItems.GRAVITITE_SHORTSWORD);
                output.accept(AetherIIItems.SKYROOT_PIKE);
                output.accept(AetherIIItems.HOLYSTONE_PIKE);
                output.accept(AetherIIItems.ZANITE_PIKE);
                output.accept(AetherIIItems.ARKENIUM_PIKE);
                output.accept(AetherIIItems.GRAVITITE_PIKE);
                output.accept(AetherIIItems.SKYROOT_HAMMER);
                output.accept(AetherIIItems.HOLYSTONE_HAMMER);
                output.accept(AetherIIItems.ZANITE_HAMMER);
                output.accept(AetherIIItems.ARKENIUM_HAMMER);
                output.accept(AetherIIItems.GRAVITITE_HAMMER);
                output.accept(AetherIIItems.SKYROOT_SHIELD);
                output.accept(AetherIIItems.BURRUKAI_PLATE_SHIELD);
                output.accept(AetherIIItems.ZANITE_SHIELD);
                output.accept(AetherIIItems.ARKENIUM_SHIELD);
                output.accept(AetherIIItems.GRAVITITE_SHIELD);
                output.accept(AetherIIItems.SKYROOT_CROSSBOW);
                output.accept(AetherIIItems.HOLYSTONE_CROSSBOW);
                output.accept(AetherIIItems.ZANITE_CROSSBOW);
                output.accept(AetherIIItems.ARKENIUM_CROSSBOW);
                output.accept(AetherIIItems.GRAVITITE_CROSSBOW);
                output.accept(AetherIIItems.SCATTERGLASS_BOLT);
                output.accept(AetherIIItems.DART_SHOOTER);
                output.accept(new ItemStack(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.AMBER_DARTS), 1, DataComponentPatch.builder().set(AetherIIDataComponents.BUILDUP_CONTENTS, new BuildupContents(EffectBuildupPresets.VULNERABILITY)).build()));
                output.accept(new ItemStack(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.AMBER_DARTS), 1, DataComponentPatch.builder().set(AetherIIDataComponents.BUILDUP_CONTENTS, new BuildupContents(EffectBuildupPresets.TOXIN)).build()));
                output.accept(new ItemStack(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.AMBER_DARTS), 1, DataComponentPatch.builder().set(AetherIIDataComponents.BUILDUP_CONTENTS, new BuildupContents(EffectBuildupPresets.VENOM)).build()));
                output.accept(new ItemStack(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.AMBER_DARTS), 1, DataComponentPatch.builder().set(AetherIIDataComponents.BUILDUP_CONTENTS, new BuildupContents(EffectBuildupPresets.AMBROSIUM_POISONING)).build()));
                output.accept(AetherIIItems.BEAST_PELT_HELMET);
                output.accept(AetherIIItems.BEAST_PELT_CHESTPLATE);
                output.accept(AetherIIItems.BEAST_PELT_LEGGINGS);
                output.accept(AetherIIItems.BEAST_PELT_BOOTS);
                output.accept(AetherIIItems.BEAST_PELT_GLOVES);
                output.accept(AetherIIItems.BURRUKAI_PLATE_HELMET);
                output.accept(AetherIIItems.BURRUKAI_PLATE_CHESTPLATE);
                output.accept(AetherIIItems.BURRUKAI_PLATE_LEGGINGS);
                output.accept(AetherIIItems.BURRUKAI_PLATE_BOOTS);
                output.accept(AetherIIItems.BURRUKAI_PLATE_GLOVES);
                output.accept(AetherIIItems.ZANITE_HELMET);
                output.accept(AetherIIItems.ZANITE_CHESTPLATE);
                output.accept(AetherIIItems.ZANITE_LEGGINGS);
                output.accept(AetherIIItems.ZANITE_BOOTS);
                output.accept(AetherIIItems.ZANITE_GLOVES);
                output.accept(AetherIIItems.ARKENIUM_HELMET);
                output.accept(AetherIIItems.ARKENIUM_CHESTPLATE);
                output.accept(AetherIIItems.ARKENIUM_LEGGINGS);
                output.accept(AetherIIItems.ARKENIUM_BOOTS);
                output.accept(AetherIIItems.ARKENIUM_GLOVES);
                output.accept(AetherIIItems.GRAVITITE_HELMET);
                output.accept(AetherIIItems.GRAVITITE_CHESTPLATE);
                output.accept(AetherIIItems.GRAVITITE_LEGGINGS);
                output.accept(AetherIIItems.GRAVITITE_BOOTS);
                output.accept(AetherIIItems.GRAVITITE_GLOVES);
                output.accept(AetherIIItems.HAMMER_OF_DEMOLITION);
                output.accept(AetherIIItems.NEPTUNE_HELMET);
                output.accept(AetherIIItems.NEPTUNE_CHESTPLATE);
                output.accept(AetherIIItems.NEPTUNE_LEGGINGS);
                output.accept(AetherIIItems.NEPTUNE_BOOTS);
                output.accept(AetherIIItems.NEPTUNE_GLOVES);
                output.accept(AetherIIItems.SENTRY_BOOTS);
                output.accept(AetherIIItems.KINETIC_THRUSTERS);
                output.accept(AetherIIItems.ZANITE_PENDANT);
                output.accept(AetherIIItems.ICESTONE_PENDANT);
                output.accept(AetherIIItems.CHARM_OF_DAMAGE_I);
                output.accept(AetherIIItems.CHARM_OF_DEXTERITY_I);
                output.accept(AetherIIItems.CHARM_OF_KNOCKBACK_I);
                output.accept(AetherIIItems.CHARM_OF_HEALTH_I);
                output.accept(AetherIIItems.CHARM_OF_DEFENSE_I);
                output.accept(AetherIIItems.CHARM_OF_TOUGHNESS_I);
                output.accept(AetherIIItems.CHARM_OF_RESISTANCE_I);
                output.accept(AetherIIItems.CHARM_OF_AGILITY_I);
                output.accept(AetherIIItems.CHARM_OF_EFFICIENCY_I);
                output.accept(AetherIIItems.CHARM_OF_REACH_I);
            }));

    public static final CreativeModeTab AETHER_II_CONSUMABLES = register("consumables", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(AetherIIItems.ORANGE))
            .title(Component.translatable("itemGroup." + AetherII.MODID + ".consumables"))
            .displayItems((features, output) -> {
                output.accept(AetherIIItems.BLUEBERRY);
                output.accept(AetherIIItems.ENCHANTED_BLUEBERRY);
                output.accept(AetherIIItems.ORANGE);
                output.accept(AetherIIItems.ENCHANTED_ORANGE);
                output.accept(AetherIIItems.WYNDBERRY);
                output.accept(AetherIIItems.ENCHANTED_WYNDBERRY);
                output.accept(AetherIIItems.GOLDEN_WYNDBERRY);
                output.accept(AetherIIItems.SATIVAL_BULB);
                output.accept(AetherIIItems.SWET_JELLY);
                output.accept(AetherIIItems.ENCHANTED_SWET_JELLY);
                output.accept(AetherIIItems.FRIED_PRISMALLARD_EGG);
                output.accept(AetherIIItems.PRISMALLARD_LEG);
                output.accept(AetherIIItems.PRISMALLARD_ROAST);
                output.accept(AetherIIItems.BURRUKAI_RIB_CUT);
                output.accept(AetherIIItems.BURRUKAI_RIBS);
                output.accept(AetherIIItems.KIRRID_LOIN);
                output.accept(AetherIIItems.KIRRID_CUTLET);
                output.accept(AetherIIItems.RAW_TAEGORE_MEAT);
                output.accept(AetherIIItems.TAEGORE_STEAK);
                output.accept(AetherIIItems.SKYROOT_LIZARD_ON_A_STICK);
                output.accept(AetherIIItems.ROASTED_SKYROOT_LIZARD_ON_A_STICK);
                output.accept(AetherIIItems.WATER_VIAL);
                output.accept(AetherIIItems.BANDAGE);
                output.accept(AetherIIItems.SPLINT);
                output.accept(AetherIIItems.ANTITOXIN_VIAL);
                output.accept(AetherIIItems.ANTIVENOM_VIAL);
                output.accept(AetherIIItems.VALKYRIE_TEA);
                output.accept(AetherIIItems.HEALING_STONE);
                output.accept(new ItemStack(BuiltInRegistries.ITEM.wrapAsHolder(AetherIIItems.HEALING_STONE), 1, DataComponentPatch.builder().set(AetherIIDataComponents.HEALING_STONE_CHARGES, 5).build()));
            }));

    public static final CreativeModeTab AETHER_II_INGREDIENTS = register("ingredients", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(AetherIIItems.AMBROSIUM_SHARD))
            .title(Component.translatable("itemGroup." + AetherII.MODID + ".ingredients"))
            .displayItems((features, output) -> {
                output.accept(AetherIIItems.AMBROSIUM_SHARD);
                output.accept(AetherIIItems.FOSSILIZED_ZANITE);
                output.accept(AetherIIItems.INERT_ARKENIUM);
                output.accept(AetherIIItems.INERT_GRAVITITE);
                output.accept(AetherIIItems.FOSSILIZED_GLINT);
                output.accept(AetherIIItems.FOSSILIZED_CORROBONITE);
                output.accept(AetherIIItems.GOLDEN_AMBER);
                output.accept(AetherIIItems.ZANITE_GEMSTONE);
                output.accept(AetherIIItems.ARKENIUM_CHIP);
                output.accept(AetherIIItems.ARKENIUM_PLATE);
                output.accept(AetherIIItems.GRAVITITE_PLATE);
                output.accept(AetherIIItems.GLINT_GEMSTONE);
                output.accept(AetherIIItems.CORROBONITE_CRYSTAL);
                output.accept(AetherIIItems.NEPTUNE_SCALE);
                output.accept(AetherIIItems.SENTRY_SERVO);
                output.accept(AetherIIItems.RESONANT_STONE);
                output.accept(AetherIIItems.SKYROOT_STICK);
                output.accept(AetherIIItems.SCATTERGLASS_SHARD);
                output.accept(AetherIIItems.ARCTIC_SNOWBALL);
                output.accept(AetherIIItems.VALKYRIE_WINGS);
                output.accept(AetherIIItems.BRETTL_GRASS);
                output.accept(AetherIIItems.BRETTL_ROPE);
                output.accept(AetherIIItems.CLOUDTWINE);
                output.accept(AetherIIItems.BEAST_PELT);
                output.accept(AetherIIItems.BURRUKAI_PLATE);
//                output.accept(AetherIIItems.KIRRID_PLATE);
                output.accept(AetherIIItems.PRISMALLARD_FEATHER);
                output.accept(AetherIIItems.MOA_FEATHER);
                output.accept(AetherIIItems.COCKATRICE_FEATHER);
                output.accept(AetherIIItems.SWET_GEL);
                output.accept(AetherIIItems.SWET_SUGAR);
                output.accept(AetherIIItems.PRISMALLARD_EGG);
                output.accept(AetherIIBlocks.MOA_EGG);
                output.accept(AetherIIItems.AECHOR_PETAL);
                output.accept(AetherIIItems.SKYROOT_PINECONE);
                output.accept(AetherIIItems.MOA_FEED);
                output.accept(AetherIIItems.BLUEBERRY_MOA_FEED);
                output.accept(AetherIIItems.ENCHANTED_MOA_FEED);
                output.accept(AetherIIItems.SCATTERGLASS_VIAL);
                output.accept(AetherIIItems.IRRADIATED_ARMOR);
                output.accept(AetherIIItems.IRRADIATED_WEAPON);
                output.accept(AetherIIItems.IRRADIATED_TOOL);
                output.accept(AetherIIItems.IRRADIATED_CHUNK);
                output.accept(AetherIIItems.IRRADIATED_DUST);
//                output.accept(AetherIIItems.ZEPHYR_HUSK); // TODO WIP ALPHA THINGS
//                output.accept(AetherIIItems.CHARGE_CATALYST);
//                output.accept(AetherIIItems.ARKENIUM_CORE);
//                output.accept(AetherIIItems.GRAVITITE_CORE);
//                output.accept(AetherIIItems.EYE_OF_THE_MIMIC);
            }));

    public static final CreativeModeTab AETHER_II_SPAWN_EGGS = register("spawn_eggs", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(AetherIIItems.AERBUNNY_SPAWN_EGG))
            .title(Component.translatable("itemGroup." + AetherII.MODID + ".spawn_eggs"))
            .displayItems((features, output) -> {
                output.accept(AetherIIItems.HIGHFIELDS_TAEGORE_SPAWN_EGG);
                output.accept(AetherIIItems.MAGNETIC_TAEGORE_SPAWN_EGG);
                output.accept(AetherIIItems.ARCTIC_TAEGORE_SPAWN_EGG);
                output.accept(AetherIIItems.HIGHFIELDS_KIRRID_SPAWN_EGG);
                output.accept(AetherIIItems.MAGNETIC_KIRRID_SPAWN_EGG);
                output.accept(AetherIIItems.ARCTIC_KIRRID_SPAWN_EGG);
                output.accept(AetherIIItems.HIGHFIELDS_BURRUKAI_SPAWN_EGG);
                output.accept(AetherIIItems.MAGNETIC_BURRUKAI_SPAWN_EGG);
                output.accept(AetherIIItems.ARCTIC_BURRUKAI_SPAWN_EGG);
                output.accept(AetherIIItems.PHYG_SPAWN_EGG);
                output.accept(AetherIIItems.SHEEPUFF_SPAWN_EGG);
                output.accept(AetherIIItems.FLYING_COW_SPAWN_EGG);
                output.accept(AetherIIItems.AERBUNNY_SPAWN_EGG);
                output.accept(AetherIIItems.PRISMALLARD_SPAWN_EGG);
                output.accept(AetherIIItems.SKYROOT_LIZARD_SPAWN_EGG);
                output.accept(AetherIIItems.GLITTERWING_SPAWN_EGG);
                output.accept(AetherIIItems.SHROUDWING_SPAWN_EGG);
                output.accept(AetherIIItems.MOA_SPAWN_EGG);
                output.accept(AetherIIItems.AERWHALE_SPAWN_EGG);
                output.accept(AetherIIItems.BLUE_SWET_SPAWN_EGG);
                output.accept(AetherIIItems.GOLDEN_SWET_SPAWN_EGG);
                output.accept(AetherIIItems.AECHOR_PLANT_SPAWN_EGG);
                output.accept(AetherIIItems.CARRION_SPROUT_SPAWN_EGG);
                output.accept(AetherIIItems.SKEPHID_SPAWN_EGG);
                output.accept(AetherIIItems.ZEPHYR_SPAWN_EGG);
                output.accept(AetherIIItems.TEMPEST_SPAWN_EGG);
                output.accept(AetherIIItems.COCKATRICE_SPAWN_EGG);
                output.accept(AetherIIItems.ARKENIUM_TALUTON_SPAWN_EGG);
                output.accept(AetherIIItems.GRAVITITE_TALUTON_SPAWN_EGG);
                output.accept(AetherIIItems.SENTRY_CRATE_MIMIC_SPAWN_EGG);
                output.accept(AetherIIItems.DETONATION_SENTRY_SPAWN_EGG);
                output.accept(AetherIIItems.SENTRY_GOLEM_SPAWN_EGG);
            }));

    public static ItemStack getMoaBook() {
        final String selector = "entity @e[type=" + AetherIIEntityIds.MOA.identifier() + ",limit=1,sort=nearest] ";
        final String dataMerge = "data merge " + selector;
        final String dataRemove = "data remove " + selector;

        try {
            ItemStack book = new UtilityBookBuilder()
                    .author("Aether II")
                    .title("Moa Book")
                    .generation(3)
                    .section("Set Feather Color")
                        .entries(Moa.FeatherColor.values(),
                                (featherColor, section) -> section.translatableEntry(AetherII.MODID + ".tooltip.item.moa_egg.feather_color." + featherColor.getSerializedName())
                                        .withNameStyle((featherColor == Moa.FeatherColor.WHITE ? Style.EMPTY : featherColor == Moa.FeatherColor.YELLOW ? Style.EMPTY.withColor(ChatFormatting.GOLD) : Style.EMPTY.withColor(featherColor.dyeColor.getTextColor())).withItalic(featherColor.isSpecialColor))
                                        .command(dataMerge + "{FeatherColor:" + featherColor.getSerializedName() + "}"))
                    .section("Set Keratin Color")
                        .entries(Moa.KeratinColor.values(),
                                (keratinColor, section) -> section.translatableEntry(AetherII.MODID + ".tooltip.item.moa_egg.keratin_color." + keratinColor.getSerializedName())
                                        .withNameStyle(Style.EMPTY.withItalic(keratinColor.isSpecialColor))
                                        .command(dataMerge + "{KeratinColor:" + keratinColor.getSerializedName() + "}"))
                    .section("Set Eye Color")
                        .entries(Moa.EyeColor.values(),
                                (eyeColor, section) -> section.translatableEntry(AetherII.MODID + ".tooltip.item.moa_egg.eye_color." + eyeColor.getSerializedName())
                                        .withNameStyle(Style.EMPTY.withItalic(eyeColor.isSpecialColor))
                                        .command(dataMerge + "{EyeColor:" + eyeColor.getSerializedName() + "}"))
                    .section("Set Feather Shape")
                        .entries(Moa.FeatherShape.values(),
                                (featherShape, section) -> section.translatableEntry(AetherII.MODID + ".tooltip.item.moa_egg.feather_shape." + featherShape.getSerializedName())
                                        .withNameStyle(Style.EMPTY.withItalic(featherShape.isSpecialShape))
                                        .command(dataMerge + "{FeatherShape:" + featherShape.getSerializedName() + "}"))
                    .section("Set Special Variant")
                        .entry("Remove MoaVariant").command(dataRemove + "MoaVariant")
                        .entry("Remove CustomName").command(dataRemove + "CustomName")
                        .entries(Moa.SpecialVariant.values(),
                                (variant, section) -> section.entry(variant.getSerializedName())
                                        .command(dataMerge + "{MoaVariant:" + variant.id() + "}"))
                    .build();
            book.set(DataComponents.CUSTOM_NAME, Component.literal("Moa Book").withStyle(Rarity.EPIC.color()).withStyle(style -> style.withItalic(false)));
            book.set(DataComponents.LORE, new ItemLore(List.of(
                Component.literal("Contains buttons to modify"),
                Component.literal("the nearest Moa's features")
            )));
            book.set(DataComponents.ITEM_MODEL, Identifier.fromNamespaceAndPath("minecraft", "knowledge_book"));
            book.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
            return book;
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

        private static CreativeModeTab register(String name, CreativeModeTab.Builder builder) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(AetherII.MODID, name), builder.build());
    }

    public static void init() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.OP_BLOCKS).register(output -> {
            if (output.shouldShowOpRestrictedItems()) {
                output.accept(getMoaBook());
                output.accept(AetherIIBlocks.LOCKED_BLOCK);
                output.accept(AetherIIBlocks.BOSS_DOORWAY_BLOCK);
                output.accept(AetherIIBlocks.TREASURE_DOORWAY_BLOCK);
            }
        });
    }
}