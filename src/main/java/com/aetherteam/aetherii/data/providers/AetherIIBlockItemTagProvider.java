package com.aetherteam.aetherii.data.providers;

import com.aetherteam.aetherii.data.providers.AetherTagAppender;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public abstract class AetherIIBlockItemTagProvider {
    public AetherIIBlockItemTagProvider() {
    }

    public void run() {
        // Aether II
        this.tagOf(AetherIITags.Blocks.AETHER_GRASS_BLOCKS, AetherIITags.Items.AETHER_GRASS_BLOCKS).add(
                AetherIIBlocks.AETHER_GRASS_BLOCK,
                AetherIIBlocks.ENCHANTED_AETHER_GRASS_BLOCK
        );
        this.tagOf(AetherIITags.Blocks.AETHER_DIRT, AetherIITags.Items.AETHER_DIRT).add(
                AetherIIBlocks.AETHER_DIRT,
                AetherIIBlocks.COARSE_AETHER_DIRT,
                AetherIIBlocks.MYCELIAL_AETHER_DIRT
        );
        this.tagOf(AetherIITags.Blocks.AETHER_MOSS_BLOCKS, AetherIITags.Items.AETHER_MOSS_BLOCKS).add(
                AetherIIBlocks.BRYALINN_MOSS_BLOCK,
                AetherIIBlocks.SHAYELINN_MOSS_BLOCK,
                AetherIIBlocks.AMBRELINN_MOSS_BLOCK
        );
        this.tagOf(AetherIITags.Blocks.AETHER_MOSS_VINES, AetherIITags.Items.AETHER_MOSS_VINES).add(
                AetherIIBlocks.BRYALINN_MOSS_VINES,
                AetherIIBlocks.SHAYELINN_MOSS_VINES,
                AetherIIBlocks.AMBRELINN_MOSS_VINES
        );
        this.tagOf(AetherIITags.Blocks.AETHER_MOSS_CARPETS, AetherIITags.Items.AETHER_MOSS_CARPETS).add(
                AetherIIBlocks.BRYALINN_MOSS_CARPET,
                AetherIIBlocks.SHAYELINN_MOSS_CARPET,
                AetherIIBlocks.AMBRELINN_MOSS_CARPET
        );
        this.tagOf(AetherIITags.Blocks.HOLYSTONE, AetherIITags.Items.HOLYSTONE).add(
                AetherIIBlocks.HOLYSTONE,
                AetherIIBlocks.MOSSY_HOLYSTONE,
                AetherIIBlocks.IRRADIATED_HOLYSTONE
        );
        this.tagOf(AetherIITags.Blocks.UNDERSHALE, AetherIITags.Items.UNDERSHALE).add(
                AetherIIBlocks.UNDERSHALE
        );
        this.tagOf(AetherIITags.Blocks.ARCTIC_ICE, AetherIITags.Items.ARCTIC_ICE).add(
                AetherIIBlocks.ARCTIC_ICE,
                AetherIIBlocks.FRAGILE_ARCTIC_ICE,
                AetherIIBlocks.ARCTIC_PACKED_ICE
        );
        this.tagOf(AetherIITags.Blocks.FERROSITE, AetherIITags.Items.FERROSITE).add(
                AetherIIBlocks.FERROSITE,
                AetherIIBlocks.RUSTED_FERROSITE
        );
        this.tagOf(AetherIITags.Blocks.AETHER_SURFACE_STONES, AetherIITags.Items.AETHER_SURFACE_STONES).add(
                AetherIIBlocks.HOLYSTONE,
                AetherIIBlocks.UNSTABLE_HOLYSTONE,
                AetherIIBlocks.MOSSY_HOLYSTONE,
                AetherIIBlocks.IRRADIATED_HOLYSTONE,
                AetherIIBlocks.ICESTONE,
                AetherIIBlocks.FERROSITE,
                AetherIIBlocks.RUSTED_FERROSITE
        );
        this.tagOf(AetherIITags.Blocks.AETHER_UNDERCLOUD_STONES, AetherIITags.Items.AETHER_UNDERCLOUD_STONES).add(
                AetherIIBlocks.UNDERSHALE,
                AetherIIBlocks.UNSTABLE_UNDERSHALE,
                AetherIIBlocks.ICESTONE,
                AetherIIBlocks.AGIOSITE,
                AetherIIBlocks.ICHORITE
        );
        this.tagOf(AetherIITags.Blocks.AERCLOUDS, AetherIITags.Items.AERCLOUDS).add(
                AetherIIBlocks.COLD_AERCLOUD,
                AetherIIBlocks.BLUE_AERCLOUD,
                AetherIIBlocks.GOLDEN_AERCLOUD,
                AetherIIBlocks.GREEN_AERCLOUD,
                AetherIIBlocks.PURPLE_AERCLOUD,
                AetherIIBlocks.STORM_AERCLOUD
        );
        this.tagOf(AetherIITags.Blocks.CLOUDWOOL, AetherIITags.Items.CLOUDWOOL).add(
                AetherIIBlocks.CLOUDWOOL,
                AetherIIBlocks.WHITE_CLOUDWOOL,
                AetherIIBlocks.ORANGE_CLOUDWOOL,
                AetherIIBlocks.MAGENTA_CLOUDWOOL,
                AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL,
                AetherIIBlocks.YELLOW_CLOUDWOOL,
                AetherIIBlocks.LIME_CLOUDWOOL,
                AetherIIBlocks.PINK_CLOUDWOOL,
                AetherIIBlocks.GRAY_CLOUDWOOL,
                AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL,
                AetherIIBlocks.CYAN_CLOUDWOOL,
                AetherIIBlocks.PURPLE_CLOUDWOOL,
                AetherIIBlocks.BLUE_CLOUDWOOL,
                AetherIIBlocks.BROWN_CLOUDWOOL,
                AetherIIBlocks.GREEN_CLOUDWOOL,
                AetherIIBlocks.RED_CLOUDWOOL,
                AetherIIBlocks.BLACK_CLOUDWOOL
        );
        this.tagOf(AetherIITags.Blocks.SKYROOT_LOGS, AetherIITags.Items.SKYROOT_LOGS).add(
                AetherIIBlocks.SKYROOT_LOG,
                AetherIIBlocks.SKYROOT_WOOD,
                AetherIIBlocks.SKYROOT_TRUNK,
                AetherIIBlocks.STRIPPED_SKYROOT_LOG,
                AetherIIBlocks.STRIPPED_SKYROOT_WOOD,
                AetherIIBlocks.STRIPPED_SKYROOT_TRUNK
        );
        this.tagOf(AetherIITags.Blocks.GREATROOT_LOGS, AetherIITags.Items.GREATROOT_LOGS).add(
                AetherIIBlocks.GREATROOT_LOG,
                AetherIIBlocks.GREATROOT_WOOD,
                AetherIIBlocks.GREATROOT_TRUNK,
                AetherIIBlocks.STRIPPED_GREATROOT_LOG,
                AetherIIBlocks.STRIPPED_GREATROOT_WOOD,
                AetherIIBlocks.STRIPPED_GREATROOT_TRUNK
        );
        this.tagOf(AetherIITags.Blocks.WISPROOT_LOGS, AetherIITags.Items.WISPROOT_LOGS).add(
                AetherIIBlocks.WISPROOT_LOG,
                AetherIIBlocks.MOSSY_WISPROOT_LOG,
                AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE,
                AetherIIBlocks.WISPROOT_WOOD,
                AetherIIBlocks.WISPROOT_TRUNK,
                AetherIIBlocks.MOSSY_WISPROOT_WOOD,
                AetherIIBlocks.MOSSY_WISPROOT_TRUNK,
                AetherIIBlocks.STRIPPED_WISPROOT_LOG,
                AetherIIBlocks.STRIPPED_WISPROOT_WOOD,
                AetherIIBlocks.STRIPPED_WISPROOT_TRUNK
        );
        this.tagOf(AetherIITags.Blocks.AMBEROOT_LOGS, AetherIITags.Items.AMBEROOT_LOGS).add(
                AetherIIBlocks.AMBEROOT_LOG,
                AetherIIBlocks.AMBEROOT_WOOD,
                AetherIIBlocks.AMBEROOT_TRUNK,
                AetherIIBlocks.AMBEROOT_DEPOSIT,
                AetherIIBlocks.STRIPPED_AMBEROOT_LOG,
                AetherIIBlocks.STRIPPED_AMBEROOT_WOOD,
                AetherIIBlocks.STRIPPED_AMBEROOT_TRUNK
        );
        this.tagOf(AetherIITags.Blocks.GUARDIAN_LOGS, AetherIITags.Items.GUARDIAN_LOGS).add(
                AetherIIBlocks.GUARDIAN_LOG,
                AetherIIBlocks.GUARDIAN_WOOD,
                AetherIIBlocks.STRIPPED_GUARDIAN_LOG,
                AetherIIBlocks.STRIPPED_GUARDIAN_WOOD,
                AetherIIBlocks.INFECTED_LOG,
                AetherIIBlocks.INFECTED_WOOD,
                AetherIIBlocks.STRIPPED_INFECTED_LOG,
                AetherIIBlocks.STRIPPED_INFECTED_WOOD
        );
        this.tagOf(AetherIITags.Blocks.AETHER_NATURAL_LOGS, AetherIITags.Items.AETHER_NATURAL_LOGS).add(
                AetherIIBlocks.SKYROOT_LOG,
                AetherIIBlocks.GREATROOT_LOG,
                AetherIIBlocks.WISPROOT_LOG,
                AetherIIBlocks.MOSSY_WISPROOT_LOG,
                AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE,
                AetherIIBlocks.AMBEROOT_LOG,
                AetherIIBlocks.AMBEROOT_DEPOSIT
        );
        this.tagOf(AetherIITags.Blocks.TRUNKS, AetherIITags.Items.TRUNKS).add(
                AetherIIBlocks.SKYROOT_TRUNK,
                AetherIIBlocks.STRIPPED_SKYROOT_TRUNK,
                AetherIIBlocks.GREATROOT_TRUNK,
                AetherIIBlocks.STRIPPED_GREATROOT_TRUNK,
                AetherIIBlocks.WISPROOT_TRUNK,
                AetherIIBlocks.MOSSY_WISPROOT_TRUNK,
                AetherIIBlocks.STRIPPED_WISPROOT_TRUNK,
                AetherIIBlocks.AMBEROOT_TRUNK,
                AetherIIBlocks.STRIPPED_AMBEROOT_TRUNK,
                AetherIIBlocks.GUARDIAN_TRUNK,
                AetherIIBlocks.STRIPPED_GUARDIAN_TRUNK,
                AetherIIBlocks.INFECTED_TRUNK,
                AetherIIBlocks.STRIPPED_INFECTED_TRUNK
        );
        this.tagOf(AetherIITags.Blocks.LEAVES, AetherIITags.Items.LEAVES).add(
                AetherIIBlocks.SKYROOT_LEAVES,
                AetherIIBlocks.SKYPLANE_LEAVES,
                AetherIIBlocks.SKYBIRCH_LEAVES,
                AetherIIBlocks.SKYPINE_LEAVES,
                AetherIIBlocks.WISPROOT_LEAVES,
                AetherIIBlocks.WISPTOP_LEAVES,
                AetherIIBlocks.GREATROOT_LEAVES,
                AetherIIBlocks.GREATOAK_LEAVES,
                AetherIIBlocks.GREATBOA_LEAVES,
                AetherIIBlocks.AMBEROOT_LEAVES,
                AetherIIBlocks.IRRADIATED_SKYROOT_LEAVES,
                AetherIIBlocks.IRRADIATED_SKYPLANE_LEAVES,
                AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAVES,
                AetherIIBlocks.IRRADIATED_SKYPINE_LEAVES,
                AetherIIBlocks.IRRADIATED_WISPROOT_LEAVES,
                AetherIIBlocks.IRRADIATED_WISPTOP_LEAVES,
                AetherIIBlocks.IRRADIATED_GREATROOT_LEAVES,
                AetherIIBlocks.IRRADIATED_GREATOAK_LEAVES,
                AetherIIBlocks.IRRADIATED_GREATBOA_LEAVES
        );
        this.tagOf(AetherIITags.Blocks.LEAF_PILES, AetherIITags.Items.LEAF_PILES).add(
                AetherIIBlocks.SKYROOT_LEAF_PILE,
                AetherIIBlocks.SKYPLANE_LEAF_PILE,
                AetherIIBlocks.SKYBIRCH_LEAF_PILE,
                AetherIIBlocks.SKYPINE_LEAF_PILE,
                AetherIIBlocks.WISPROOT_LEAF_PILE,
                AetherIIBlocks.WISPTOP_LEAF_PILE,
                AetherIIBlocks.GREATROOT_LEAF_PILE,
                AetherIIBlocks.GREATOAK_LEAF_PILE,
                AetherIIBlocks.GREATBOA_LEAF_PILE,
                AetherIIBlocks.AMBEROOT_LEAF_PILE,
                AetherIIBlocks.IRRADIATED_SKYROOT_LEAF_PILE,
                AetherIIBlocks.IRRADIATED_SKYPLANE_LEAF_PILE,
                AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAF_PILE,
                AetherIIBlocks.IRRADIATED_SKYPINE_LEAF_PILE,
                AetherIIBlocks.IRRADIATED_WISPROOT_LEAF_PILE,
                AetherIIBlocks.IRRADIATED_WISPTOP_LEAF_PILE,
                AetherIIBlocks.IRRADIATED_GREATROOT_LEAF_PILE,
                AetherIIBlocks.IRRADIATED_GREATOAK_LEAF_PILE,
                AetherIIBlocks.IRRADIATED_GREATBOA_LEAF_PILE
        );
        this.tagOf(AetherIITags.Blocks.SKYROOT_DECORATIVE_BLOCKS, AetherIITags.Items.SKYROOT_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SKYROOT_FLOORBOARDS,
                AetherIIBlocks.SKYROOT_HIGHLIGHT,
                AetherIIBlocks.SKYROOT_SHINGLES,
                AetherIIBlocks.SKYROOT_SMALL_SHINGLES,
                AetherIIBlocks.SKYROOT_BASE_PLANKS,
                AetherIIBlocks.SKYROOT_TOP_PLANKS,
                AetherIIBlocks.SKYROOT_BASE_BEAM,
                AetherIIBlocks.SKYROOT_TOP_BEAM,
                AetherIIBlocks.SKYROOT_BEAM
        );
        this.tagOf(AetherIITags.Blocks.GREATROOT_DECORATIVE_BLOCKS, AetherIITags.Items.GREATROOT_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.GREATROOT_FLOORBOARDS,
                AetherIIBlocks.GREATROOT_HIGHLIGHT,
                AetherIIBlocks.GREATROOT_SHINGLES,
                AetherIIBlocks.GREATROOT_SMALL_SHINGLES,
                AetherIIBlocks.GREATROOT_BASE_PLANKS,
                AetherIIBlocks.GREATROOT_TOP_PLANKS,
                AetherIIBlocks.GREATROOT_BASE_BEAM,
                AetherIIBlocks.GREATROOT_TOP_BEAM,
                AetherIIBlocks.GREATROOT_BEAM
        );
        this.tagOf(AetherIITags.Blocks.WISPROOT_DECORATIVE_BLOCKS, AetherIITags.Items.WISPROOT_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.WISPROOT_FLOORBOARDS,
                AetherIIBlocks.WISPROOT_HIGHLIGHT,
                AetherIIBlocks.WISPROOT_SHINGLES,
                AetherIIBlocks.WISPROOT_SMALL_SHINGLES,
                AetherIIBlocks.WISPROOT_BASE_PLANKS,
                AetherIIBlocks.WISPROOT_TOP_PLANKS,
                AetherIIBlocks.WISPROOT_BASE_BEAM,
                AetherIIBlocks.WISPROOT_TOP_BEAM,
                AetherIIBlocks.WISPROOT_BEAM
        );
        this.tagOf(AetherIITags.Blocks.AMBEROOT_DECORATIVE_BLOCKS, AetherIITags.Items.AMBEROOT_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.AMBEROOT_FLOORBOARDS,
                AetherIIBlocks.AMBEROOT_HIGHLIGHT,
                AetherIIBlocks.AMBEROOT_SHINGLES,
                AetherIIBlocks.AMBEROOT_SMALL_SHINGLES,
                AetherIIBlocks.AMBEROOT_BASE_PLANKS,
                AetherIIBlocks.AMBEROOT_TOP_PLANKS,
                AetherIIBlocks.AMBEROOT_BASE_BEAM,
                AetherIIBlocks.AMBEROOT_TOP_BEAM,
                AetherIIBlocks.AMBEROOT_BEAM
        );
        this.tagOf(AetherIITags.Blocks.HOLYSTONE_DECORATIVE_BLOCKS, AetherIITags.Items.HOLYSTONE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.HOLYSTONE_FLAGSTONES,
                AetherIIBlocks.HOLYSTONE_HEADSTONE,
                AetherIIBlocks.HOLYSTONE_KEYSTONE,
                AetherIIBlocks.HOLYSTONE_BASE_BRICKS,
                AetherIIBlocks.HOLYSTONE_CAPSTONE_BRICKS,
                AetherIIBlocks.HOLYSTONE_BASE_PILLAR,
                AetherIIBlocks.HOLYSTONE_CAPSTONE_PILLAR,
                AetherIIBlocks.HOLYSTONE_PILLAR
        );
        this.tagOf(AetherIITags.Blocks.FADED_HOLYSTONE_DECORATIVE_BLOCKS, AetherIITags.Items.FADED_HOLYSTONE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.FADED_HOLYSTONE_FLAGSTONES,
                AetherIIBlocks.FADED_HOLYSTONE_HEADSTONE,
                AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE,
                AetherIIBlocks.FADED_HOLYSTONE_BASE_BRICKS,
                AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_BRICKS,
                AetherIIBlocks.FADED_HOLYSTONE_BASE_PILLAR,
                AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_PILLAR,
                AetherIIBlocks.FADED_HOLYSTONE_PILLAR
        );
        this.tagOf(AetherIITags.Blocks.UNDERSHALE_DECORATIVE_BLOCKS, AetherIITags.Items.UNDERSHALE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.UNDERSHALE_FLAGSTONES,
                AetherIIBlocks.UNDERSHALE_TILE,
                AetherIIBlocks.UNDERSHALE_BASE_BRICKS,
                AetherIIBlocks.UNDERSHALE_CAPSTONE_BRICKS,
                AetherIIBlocks.UNDERSHALE_BASE_PILLAR,
                AetherIIBlocks.UNDERSHALE_CAPSTONE_PILLAR,
                AetherIIBlocks.UNDERSHALE_PILLAR
        );
        this.tagOf(AetherIITags.Blocks.SENTRY_DECORATIVE_BLOCKS, AetherIITags.Items.SENTRY_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SENTRY_LIGHTSTONE,
                AetherIIBlocks.SENTRY_FLAGSTONES,
                AetherIIBlocks.SENTRY_TILE,
                AetherIIBlocks.SENTRY_BASE_BRICKS,
                AetherIIBlocks.SENTRY_CAPSTONE_BRICKS,
                AetherIIBlocks.SENTRY_BASE_PILLAR,
                AetherIIBlocks.SENTRY_CAPSTONE_PILLAR,
                AetherIIBlocks.SENTRY_PILLAR
        );
        this.tagOf(AetherIITags.Blocks.ICHORITE_DECORATIVE_BLOCKS, AetherIITags.Items.ICHORITE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.ICHORITE_FLAGSTONES,
                AetherIIBlocks.ICHORITE_RUNESTONE,
                AetherIIBlocks.ICHORITE_KEYSTONE,
                AetherIIBlocks.ICHORITE_BASE_BRICKS,
                AetherIIBlocks.ICHORITE_CAPSTONE_BRICKS,
                AetherIIBlocks.ICHORITE_BASE_PILLAR,
                AetherIIBlocks.ICHORITE_CAPSTONE_PILLAR,
                AetherIIBlocks.ICHORITE_PILLAR
        );
        this.tagOf(AetherIITags.Blocks.MARBLED_ICHORITE_DECORATIVE_BLOCKS, AetherIITags.Items.MARBLED_ICHORITE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.MARBLED_FLAGSTONES,
                AetherIIBlocks.MARBLED_KEYSTONE,
                AetherIIBlocks.MARBLED_BASE_BRICKS,
                AetherIIBlocks.MARBLED_CAPSTONE_BRICKS,
                AetherIIBlocks.MARBLED_BASE_PILLAR,
                AetherIIBlocks.MARBLED_CAPSTONE_PILLAR,
                AetherIIBlocks.MARBLED_PILLAR
        );
        this.tagOf(AetherIITags.Blocks.AGIOSITE_DECORATIVE_BLOCKS, AetherIITags.Items.AGIOSITE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.AGIOSITE_FLAGSTONES,
                AetherIIBlocks.AGIOSITE_KEYSTONE,
                AetherIIBlocks.AGIOSITE_BASE_BRICKS,
                AetherIIBlocks.AGIOSITE_CAPSTONE_BRICKS,
                AetherIIBlocks.AGIOSITE_BASE_PILLAR,
                AetherIIBlocks.AGIOSITE_CAPSTONE_PILLAR,
                AetherIIBlocks.AGIOSITE_PILLAR
        );
        this.tagOf(AetherIITags.Blocks.ICESTONE_DECORATIVE_BLOCKS, AetherIITags.Items.ICESTONE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.ICESTONE_FLAGSTONES,
                AetherIIBlocks.ICESTONE_KEYSTONE,
                AetherIIBlocks.ICESTONE_BASE_BRICKS,
                AetherIIBlocks.ICESTONE_CAPSTONE_BRICKS,
                AetherIIBlocks.ICESTONE_BASE_PILLAR,
                AetherIIBlocks.ICESTONE_CAPSTONE_PILLAR,
                AetherIIBlocks.ICESTONE_PILLAR
        );
        this.tagOf(AetherIITags.Blocks.QUICKSOIL_GLASS_DECORATIVE_BLOCKS, AetherIITags.Items.QUICKSOIL_GLASS_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.TILED_QUICKSOIL_GLASS,
                AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS
        );
        this.tagOf(AetherIITags.Blocks.QUICKSOIL_GLASS_PANE_DECORATIVE_BLOCKS, AetherIITags.Items.QUICKSOIL_GLASS_PANE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.TILED_QUICKSOIL_GLASS_PANE,
                AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS_PANE
        );
        this.tagOf(AetherIITags.Blocks.CRUDE_SCATTERGLASS_DECORATIVE_BLOCKS, AetherIITags.Items.CRUDE_SCATTERGLASS_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS,
                AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS
        );
        this.tagOf(AetherIITags.Blocks.CRUDE_SCATTERGLASS_PANE_DECORATIVE_BLOCKS, AetherIITags.Items.CRUDE_SCATTERGLASS_PANE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS_PANE,
                AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS_PANE
        );
        this.tagOf(AetherIITags.Blocks.SCATTERGLASS_DECORATIVE_BLOCKS, AetherIITags.Items.SCATTERGLASS_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS,
                AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS
        );
        this.tagOf(AetherIITags.Blocks.SCATTERGLASS_PANE_DECORATIVE_BLOCKS, AetherIITags.Items.SCATTERGLASS_PANE_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS_PANE,
                AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS_PANE
        );
        this.tagOf(AetherIITags.Blocks.ARKENIUM_BARS_DECORATIVE_BLOCKS, AetherIITags.Items.ARKENIUM_BARS_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.FLORAL_ARKENIUM_BARS,
                AetherIIBlocks.PATTERNED_ARKENIUM_BARS,
                AetherIIBlocks.CURVED_ARKENIUM_BARS,
                AetherIIBlocks.RUSTIC_ARKENIUM_BARS
        );
        this.tagOf(AetherIITags.Blocks.RUSTIC_ARKENIUM_BARS_DECORATIVE_BLOCKS, AetherIITags.Items.RUSTIC_ARKENIUM_BARS_DECORATIVE_BLOCKS).add(
                AetherIIBlocks.RUSTIC_FLORAL_ARKENIUM_BARS,
                AetherIIBlocks.RUSTIC_PATTERNED_ARKENIUM_BARS,
                AetherIIBlocks.RUSTIC_CURVED_ARKENIUM_BARS
        );
        this.tagOf(AetherIITags.Blocks.QUICKSOIL_GLASS, AetherIITags.Items.QUICKSOIL_GLASS).add(
                AetherIIBlocks.QUICKSOIL_GLASS,
                AetherIIBlocks.TILED_QUICKSOIL_GLASS,
                AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS
        );
        this.tagOf(AetherIITags.Blocks.CRUDE_SCATTERGLASS, AetherIITags.Items.CRUDE_SCATTERGLASS).add(
                AetherIIBlocks.CRUDE_SCATTERGLASS,
                AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS,
                AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS
        );
        this.tagOf(AetherIITags.Blocks.SCATTERGLASS, AetherIITags.Items.SCATTERGLASS).add(
                AetherIIBlocks.SCATTERGLASS,
                AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS,
                AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS
        );
        this.tagOf(AetherIITags.Blocks.QUICKSOIL_GLASS_PANE, AetherIITags.Items.QUICKSOIL_GLASS_PANE).add(
                AetherIIBlocks.QUICKSOIL_GLASS_PANE,
                AetherIIBlocks.TILED_QUICKSOIL_GLASS_PANE,
                AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS_PANE
        );
        this.tagOf(AetherIITags.Blocks.CRUDE_SCATTERGLASS_PANE, AetherIITags.Items.CRUDE_SCATTERGLASS_PANE).add(
                AetherIIBlocks.CRUDE_SCATTERGLASS_PANE,
                AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS_PANE,
                AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS_PANE
        );
        this.tagOf(AetherIITags.Blocks.SCATTERGLASS_PANE, AetherIITags.Items.SCATTERGLASS_PANE).add(
                AetherIIBlocks.SCATTERGLASS_PANE,
                AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS_PANE,
                AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS_PANE
        );
        this.tagOf(AetherIITags.Blocks.ARKENIUM_BARS, AetherIITags.Items.ARKENIUM_BARS).add(
                AetherIIBlocks.ARKENIUM_BARS,
                AetherIIBlocks.FLORAL_ARKENIUM_BARS,
                AetherIIBlocks.PATTERNED_ARKENIUM_BARS,
                AetherIIBlocks.CURVED_ARKENIUM_BARS
        );
        this.tagOf(AetherIITags.Blocks.ARILUM_LANTERN, AetherIITags.Items.ARILUM_LANTERN).add(
                AetherIIBlocks.WHITE_ARILUM_LANTERN,
                AetherIIBlocks.ORANGE_ARILUM_LANTERN,
                AetherIIBlocks.MAGENTA_ARILUM_LANTERN,
                AetherIIBlocks.LIGHT_BLUE_ARILUM_LANTERN,
                AetherIIBlocks.YELLOW_ARILUM_LANTERN,
                AetherIIBlocks.LIME_ARILUM_LANTERN,
                AetherIIBlocks.PINK_CLOUDWOOL,
                AetherIIBlocks.GRAY_ARILUM_LANTERN,
                AetherIIBlocks.LIGHT_GRAY_ARILUM_LANTERN,
                AetherIIBlocks.CYAN_ARILUM_LANTERN,
                AetherIIBlocks.PURPLE_ARILUM_LANTERN,
                AetherIIBlocks.BLUE_ARILUM_LANTERN,
                AetherIIBlocks.BROWN_ARILUM_LANTERN,
                AetherIIBlocks.GREEN_ARILUM_LANTERN,
                AetherIIBlocks.RED_ARILUM_LANTERN,
                AetherIIBlocks.BLACK_ARILUM_LANTERN
        );

        // Vanilla
        this.tagOf(BlockTags.WOOL, ItemTags.WOOL).add(
                AetherIIBlocks.CLOUDWOOL,
                AetherIIBlocks.WHITE_CLOUDWOOL,
                AetherIIBlocks.ORANGE_CLOUDWOOL,
                AetherIIBlocks.MAGENTA_CLOUDWOOL,
                AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL,
                AetherIIBlocks.YELLOW_CLOUDWOOL,
                AetherIIBlocks.LIME_CLOUDWOOL,
                AetherIIBlocks.PINK_CLOUDWOOL,
                AetherIIBlocks.GRAY_CLOUDWOOL,
                AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL,
                AetherIIBlocks.CYAN_CLOUDWOOL,
                AetherIIBlocks.PURPLE_CLOUDWOOL,
                AetherIIBlocks.BLUE_CLOUDWOOL,
                AetherIIBlocks.BROWN_CLOUDWOOL,
                AetherIIBlocks.GREEN_CLOUDWOOL,
                AetherIIBlocks.RED_CLOUDWOOL,
                AetherIIBlocks.BLACK_CLOUDWOOL
        );
        this.tagOf(BlockTags.PLANKS, ItemTags.PLANKS).add(
                AetherIIBlocks.SKYROOT_PLANKS,
                AetherIIBlocks.GREATROOT_PLANKS,
                AetherIIBlocks.WISPROOT_PLANKS
        );
        this.tagOf(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS).add(
                AetherIIBlocks.SKYROOT_BUTTON,
                AetherIIBlocks.GREATROOT_BUTTON,
                AetherIIBlocks.WISPROOT_BUTTON,
                AetherIIBlocks.AMBEROOT_BUTTON
        );
        this.tagOf(BlockItemTags.STONE_BUTTONS.block(), BlockItemTags.STONE_BUTTONS.item()).add(
                AetherIIBlocks.HOLYSTONE_BUTTON,
                AetherIIBlocks.UNDERSHALE_BRICK_BUTTON,
                AetherIIBlocks.SENTRY_BUTTON
        );
        this.tagOf(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS).add(
                AetherIIBlocks.CLOUDWOOL_CARPET,
                AetherIIBlocks.WHITE_CLOUDWOOL_CARPET,
                AetherIIBlocks.ORANGE_CLOUDWOOL_CARPET,
                AetherIIBlocks.MAGENTA_CLOUDWOOL_CARPET,
                AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL_CARPET,
                AetherIIBlocks.YELLOW_CLOUDWOOL_CARPET,
                AetherIIBlocks.LIME_CLOUDWOOL_CARPET,
                AetherIIBlocks.PINK_CLOUDWOOL_CARPET,
                AetherIIBlocks.GRAY_CLOUDWOOL_CARPET,
                AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL_CARPET,
                AetherIIBlocks.CYAN_CLOUDWOOL_CARPET,
                AetherIIBlocks.PURPLE_CLOUDWOOL_CARPET,
                AetherIIBlocks.BLUE_CLOUDWOOL_CARPET,
                AetherIIBlocks.BROWN_CLOUDWOOL_CARPET,
                AetherIIBlocks.GREEN_CLOUDWOOL_CARPET,
                AetherIIBlocks.RED_CLOUDWOOL_CARPET,
                AetherIIBlocks.BLACK_CLOUDWOOL_CARPET
        );
        this.tagOf(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS).add(
                AetherIIBlocks.SKYROOT_DOOR,
                AetherIIBlocks.GREATROOT_DOOR,
                AetherIIBlocks.WISPROOT_DOOR,
                AetherIIBlocks.SECRET_SKYROOT_DOOR,
                AetherIIBlocks.SECRET_GREATROOT_DOOR,
                AetherIIBlocks.SECRET_WISPROOT_DOOR,
                AetherIIBlocks.SECRET_AMBEROOT_DOOR
        );
        this.tagOf(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS).add(
                AetherIIBlocks.SKYROOT_STAIRS,
                AetherIIBlocks.GREATROOT_STAIRS,
                AetherIIBlocks.WISPROOT_STAIRS,
                AetherIIBlocks.AMBEROOT_STAIRS
        );
        this.tagOf(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS).add(
                AetherIIBlocks.SKYROOT_SLAB,
                AetherIIBlocks.GREATROOT_SLAB,
                AetherIIBlocks.WISPROOT_SLAB,
                AetherIIBlocks.WISPROOT_SLAB
        );
        this.tagOf(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES).add(
                AetherIIBlocks.SKYROOT_FENCE,
                AetherIIBlocks.GREATROOT_FENCE,
                AetherIIBlocks.WISPROOT_FENCE,
                AetherIIBlocks.AMBEROOT_FENCE
        );
        this.tagOf(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES).add(
                AetherIIBlocks.SKYROOT_FENCE_GATE,
                AetherIIBlocks.GREATROOT_FENCE_GATE,
                AetherIIBlocks.WISPROOT_FENCE_GATE,
                AetherIIBlocks.AMBEROOT_FENCE_GATE
        );
        this.tagOf(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES).add(
                AetherIIBlocks.SKYROOT_PRESSURE_PLATE,
                AetherIIBlocks.GREATROOT_PRESSURE_PLATE,
                AetherIIBlocks.WISPROOT_PRESSURE_PLATE,
                AetherIIBlocks.AMBEROOT_PRESSURE_PLATE
        );
        this.tagOf(BlockTags.WOODEN_SHELVES, ItemTags.WOODEN_SHELVES).add(
                AetherIIBlocks.SKYROOT_SHELF,
                AetherIIBlocks.GREATROOT_SHELF,
                AetherIIBlocks.WISPROOT_SHELF,
                AetherIIBlocks.AMBEROOT_SHELF
        );
        this.tagOf(BlockItemTags.DOORS.block(), BlockItemTags.DOORS.item()).add(
                AetherIIBlocks.ARKENIUM_DOOR
        );
        this.tagOf(BlockItemTags.SAPLINGS.block(), BlockItemTags.SAPLINGS.item()).add(
                AetherIIBlocks.SKYROOT_SAPLING,
                AetherIIBlocks.SKYPLANE_SAPLING,
                AetherIIBlocks.SKYBIRCH_SAPLING,
                AetherIIBlocks.SKYPINE_SAPLING,
                AetherIIBlocks.WISPROOT_SAPLING,
                AetherIIBlocks.WISPTOP_SAPLING,
                AetherIIBlocks.GREATROOT_SAPLING,
                AetherIIBlocks.GREATOAK_SAPLING,
                AetherIIBlocks.GREATBOA_SAPLING,
                AetherIIBlocks.AMBEROOT_SAPLING
        );
        this.tagOf(BlockTags.SAND, ItemTags.SAND).add(
                AetherIIBlocks.QUICKSOIL,
                AetherIIBlocks.SHIMMERING_SILT,
                AetherIIBlocks.FERROSITE_SAND
        );
        this.tagOf(BlockItemTags.SLABS.block(), BlockItemTags.SLABS.item()).add(
                AetherIIBlocks.HOLYSTONE_SLAB,
                AetherIIBlocks.MOSSY_HOLYSTONE_SLAB,
                AetherIIBlocks.IRRADIATED_HOLYSTONE_SLAB,
                AetherIIBlocks.HOLYSTONE_BRICK_SLAB,
                AetherIIBlocks.FADED_HOLYSTONE_BRICK_SLAB,
                AetherIIBlocks.UNDERSHALE_SLAB,
                AetherIIBlocks.UNDERSHALE_BRICK_SLAB,
                AetherIIBlocks.SENTRY_BRICK_SLAB,
                AetherIIBlocks.AGIOSITE_SLAB,
                AetherIIBlocks.AGIOSITE_BRICK_SLAB,
                AetherIIBlocks.ICESTONE_SLAB,
                AetherIIBlocks.ICESTONE_BRICK_SLAB,
                AetherIIBlocks.ICHORITE_SLAB,
                AetherIIBlocks.SMOOTH_ICHORITE_SLAB,
                AetherIIBlocks.ICHORITE_BRICK_SLAB,
                AetherIIBlocks.MARBLED_ICHORITE_SLAB,
                AetherIIBlocks.MARBLED_BRICK_SLAB
        );
        this.tagOf(BlockTags.WALLS, ItemTags.WALLS).add(
                AetherIIBlocks.HOLYSTONE_WALL,
                AetherIIBlocks.MOSSY_HOLYSTONE_WALL,
                AetherIIBlocks.IRRADIATED_HOLYSTONE_WALL,
                AetherIIBlocks.HOLYSTONE_BRICK_WALL,
                AetherIIBlocks.FADED_HOLYSTONE_BRICK_WALL,
                AetherIIBlocks.UNDERSHALE_WALL,
                AetherIIBlocks.UNDERSHALE_BRICK_WALL,
                AetherIIBlocks.SENTRY_BRICK_WALL,
                AetherIIBlocks.AGIOSITE_WALL,
                AetherIIBlocks.AGIOSITE_BRICK_WALL,
                AetherIIBlocks.ICESTONE_WALL,
                AetherIIBlocks.ICESTONE_BRICK_WALL,
                AetherIIBlocks.ICHORITE_WALL,
                AetherIIBlocks.SMOOTH_ICHORITE_WALL,
                AetherIIBlocks.ICHORITE_BRICK_WALL,
                AetherIIBlocks.MARBLED_ICHORITE_WALL,
                AetherIIBlocks.MARBLED_BRICK_WALL
        );
        this.tagOf(BlockItemTags.STAIRS.block(), BlockItemTags.STAIRS.item()).add(
                AetherIIBlocks.HOLYSTONE_STAIRS,
                AetherIIBlocks.MOSSY_HOLYSTONE_STAIRS,
                AetherIIBlocks.IRRADIATED_HOLYSTONE_STAIRS,
                AetherIIBlocks.HOLYSTONE_BRICK_STAIRS,
                AetherIIBlocks.FADED_HOLYSTONE_BRICK_STAIRS,
                AetherIIBlocks.UNDERSHALE_STAIRS,
                AetherIIBlocks.UNDERSHALE_BRICK_STAIRS,
                AetherIIBlocks.SENTRY_BRICK_STAIRS,
                AetherIIBlocks.AGIOSITE_STAIRS,
                AetherIIBlocks.AGIOSITE_BRICK_STAIRS,
                AetherIIBlocks.ICESTONE_STAIRS,
                AetherIIBlocks.ICESTONE_BRICK_STAIRS,
                AetherIIBlocks.ICHORITE_STAIRS,
                AetherIIBlocks.SMOOTH_ICHORITE_STAIRS,
                AetherIIBlocks.ICHORITE_BRICK_STAIRS,
                AetherIIBlocks.MARBLED_ICHORITE_STAIRS,
                AetherIIBlocks.MARBLED_BRICK_STAIRS
        );
        this.tagOf(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS).add(
                AetherIIBlocks.SKYROOT_TRAPDOOR,
                AetherIIBlocks.GREATROOT_TRAPDOOR,
                AetherIIBlocks.WISPROOT_TRAPDOOR,
                AetherIIBlocks.AMBEROOT_TRAPDOOR,
                AetherIIBlocks.SECRET_SKYROOT_TRAPDOOR,
                AetherIIBlocks.SECRET_GREATROOT_TRAPDOOR,
                AetherIIBlocks.SECRET_WISPROOT_TRAPDOOR,
                AetherIIBlocks.SECRET_AMBEROOT_TRAPDOOR
        );
        this.tagOf(BlockItemTags.TRAPDOORS.block(), BlockItemTags.TRAPDOORS.item()).add(
                AetherIIBlocks.ARKENIUM_TRAPDOOR
        );
        this.tagOf(BlockItemTags.SMALL_FLOWERS.block(), BlockItemTags.SMALL_FLOWERS.item()).add(
                AetherIIBlocks.BLADE_POA,
                AetherIIBlocks.HESPEROSE,
                AetherIIBlocks.TARABLOOM,
                AetherIIBlocks.POASPROUT,
                AetherIIBlocks.LILICHIME,
                AetherIIBlocks.PLURACIAN,
                AetherIIBlocks.SATIVAL_SHOOT,
                AetherIIBlocks.AECHOR_CUTTING,
                AetherIIBlocks.CARRION_CUTTING
        );
        this.tagOf(BlockItemTags.FLOWERS.block(), BlockItemTags.FLOWERS.item()).add(
                AetherIIBlocks.BRETTL_FLOWER,
                AetherIIBlocks.HOLPUPEA,
                AetherIIBlocks.BRYALINN_MOSS_FLOWERS,
                AetherIIBlocks.TARAHESP_FLOWERS
        );
        this.tagOf(BlockTags.BEDS, ItemTags.BEDS).add(
                AetherIIBlocks.CLOUDWOOL_BEDROLL,
                AetherIIBlocks.SKYROOT_BED,
                AetherIIBlocks.WHITE_SKYROOT_BED,
                AetherIIBlocks.ORANGE_SKYROOT_BED,
                AetherIIBlocks.MAGENTA_SKYROOT_BED,
                AetherIIBlocks.LIGHT_BLUE_SKYROOT_BED,
                AetherIIBlocks.YELLOW_SKYROOT_BED,
                AetherIIBlocks.LIME_SKYROOT_BED,
                AetherIIBlocks.PINK_SKYROOT_BED,
                AetherIIBlocks.GRAY_SKYROOT_BED,
                AetherIIBlocks.LIGHT_GRAY_SKYROOT_BED,
                AetherIIBlocks.CYAN_SKYROOT_BED,
                AetherIIBlocks.PURPLE_SKYROOT_BED,
                AetherIIBlocks.BLUE_SKYROOT_BED,
                AetherIIBlocks.BROWN_SKYROOT_BED,
                AetherIIBlocks.GREEN_SKYROOT_BED,
                AetherIIBlocks.RED_SKYROOT_BED,
                AetherIIBlocks.BLACK_SKYROOT_BED
        );
        this.tagOf(BlockTags.MUD, ItemTags.MUD).add(
                AetherIIBlocks.FERROSITE_MUD
        );
        this.tagOf(BlockItemTags.CHAINS.block(), BlockItemTags.CHAINS.item()).add(
                AetherIIBlocks.ARKENIUM_CHAIN
        );
        this.tagOf(BlockItemTags.LANTERNS.block(), BlockItemTags.LANTERNS.item()).add(
                AetherIIBlocks.ARKENIUM_LANTERN,
                AetherIIBlocks.RUSTIC_ARKENIUM_LANTERN
        );
        this.tagOf(BlockTags.SIGNS, ItemTags.SIGNS).add(
                AetherIIBlocks.SKYROOT_SIGN,
                AetherIIBlocks.GREATROOT_SIGN,
                AetherIIBlocks.WISPROOT_SIGN,
                AetherIIBlocks.AMBEROOT_SIGN
        );

        // NeoForge
        this.tagOf(ConventionalBlockTags.WOODEN_BARRELS, ConventionalItemTags.WOODEN_BARRELS).add(
                AetherIIBlocks.SKYROOT_BARREL
        );
        this.tagOf(ConventionalBlockTags.BOOKSHELVES, ConventionalItemTags.BOOKSHELVES).add(
                AetherIIBlocks.SKYROOT_BOOKSHELF,
                AetherIIBlocks.GREATROOT_BOOKSHELF,
                AetherIIBlocks.WISPROOT_BOOKSHELF,
                AetherIIBlocks.AMBEROOT_BOOKSHELF,
                AetherIIBlocks.HOLYSTONE_BOOKSHELF
        );
        this.tagOf(ConventionalBlockTags.CHAINS, ConventionalItemTags.CHAINS).add(
                AetherIIBlocks.ARKENIUM_CHAIN
        );
        this.tagOf(ConventionalBlockTags.CHESTS, ConventionalItemTags.CHESTS).add(
                AetherIIBlocks.SENTRY_CRATE
        );
        this.tagOf(ConventionalBlockTags.WOODEN_CHESTS, ConventionalItemTags.WOODEN_CHESTS).add(
                AetherIIBlocks.SKYROOT_CHEST,
                AetherIIBlocks.SAGE_CHEST
        );
        this.tagOf(ConventionalBlockTags.BLACK_DYED, ConventionalItemTags.BLACK_DYED).add(
                AetherIIBlocks.BLACK_CLOUDWOOL,
                AetherIIBlocks.BLACK_CLOUDWOOL_CARPET,
                AetherIIBlocks.BLACK_ARILUM_LANTERN,
                AetherIIBlocks.BLACK_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.BLUE_DYED, ConventionalItemTags.BLUE_DYED).add(
                AetherIIBlocks.BLUE_CLOUDWOOL,
                AetherIIBlocks.BLUE_CLOUDWOOL_CARPET,
                AetherIIBlocks.BLUE_ARILUM_LANTERN,
                AetherIIBlocks.BLUE_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.BROWN_DYED, ConventionalItemTags.BROWN_DYED).add(
                AetherIIBlocks.BROWN_CLOUDWOOL,
                AetherIIBlocks.BROWN_CLOUDWOOL_CARPET,
                AetherIIBlocks.BROWN_ARILUM_LANTERN,
                AetherIIBlocks.BROWN_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.CYAN_DYED, ConventionalItemTags.CYAN_DYED).add(
                AetherIIBlocks.CYAN_CLOUDWOOL,
                AetherIIBlocks.CYAN_CLOUDWOOL_CARPET,
                AetherIIBlocks.CYAN_ARILUM_LANTERN,
                AetherIIBlocks.CYAN_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.GRAY_DYED, ConventionalItemTags.GRAY_DYED).add(
                AetherIIBlocks.GRAY_CLOUDWOOL,
                AetherIIBlocks.GRAY_CLOUDWOOL_CARPET,
                AetherIIBlocks.GRAY_ARILUM_LANTERN,
                AetherIIBlocks.GRAY_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.GREEN_DYED, ConventionalItemTags.GREEN_DYED).add(
                AetherIIBlocks.GREEN_CLOUDWOOL,
                AetherIIBlocks.GREEN_CLOUDWOOL_CARPET,
                AetherIIBlocks.GREEN_ARILUM_LANTERN,
                AetherIIBlocks.GREEN_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.LIGHT_BLUE_DYED, ConventionalItemTags.LIGHT_BLUE_DYED).add(
                AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL,
                AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL_CARPET,
                AetherIIBlocks.LIGHT_BLUE_ARILUM_LANTERN,
                AetherIIBlocks.LIGHT_BLUE_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.LIGHT_GRAY_DYED, ConventionalItemTags.LIGHT_GRAY_DYED).add(
                AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL,
                AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL_CARPET,
                AetherIIBlocks.LIGHT_GRAY_ARILUM_LANTERN,
                AetherIIBlocks.LIGHT_GRAY_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.LIME_DYED, ConventionalItemTags.LIME_DYED).add(
                AetherIIBlocks.LIME_CLOUDWOOL,
                AetherIIBlocks.LIME_CLOUDWOOL_CARPET,
                AetherIIBlocks.LIME_ARILUM_LANTERN,
                AetherIIBlocks.LIME_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.MAGENTA_DYED, ConventionalItemTags.MAGENTA_DYED).add(
                AetherIIBlocks.MAGENTA_CLOUDWOOL,
                AetherIIBlocks.MAGENTA_CLOUDWOOL_CARPET,
                AetherIIBlocks.MAGENTA_ARILUM_LANTERN,
                AetherIIBlocks.MAGENTA_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.ORANGE_DYED, ConventionalItemTags.ORANGE_DYED).add(
                AetherIIBlocks.ORANGE_CLOUDWOOL,
                AetherIIBlocks.ORANGE_CLOUDWOOL_CARPET,
                AetherIIBlocks.ORANGE_ARILUM_LANTERN,
                AetherIIBlocks.ORANGE_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.PINK_DYED, ConventionalItemTags.PINK_DYED).add(
                AetherIIBlocks.PINK_CLOUDWOOL,
                AetherIIBlocks.PINK_CLOUDWOOL_CARPET,
                AetherIIBlocks.PINK_ARILUM_LANTERN,
                AetherIIBlocks.PINK_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.PURPLE_DYED, ConventionalItemTags.PURPLE_DYED).add(
                AetherIIBlocks.PURPLE_CLOUDWOOL,
                AetherIIBlocks.PURPLE_CLOUDWOOL_CARPET,
                AetherIIBlocks.PURPLE_ARILUM_LANTERN,
                AetherIIBlocks.PURPLE_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.RED_DYED, ConventionalItemTags.RED_DYED).add(
                AetherIIBlocks.RED_CLOUDWOOL,
                AetherIIBlocks.RED_CLOUDWOOL_CARPET,
                AetherIIBlocks.RED_ARILUM_LANTERN,
                AetherIIBlocks.RED_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.WHITE_DYED, ConventionalItemTags.WHITE_DYED).add(
                AetherIIBlocks.WHITE_CLOUDWOOL,
                AetherIIBlocks.WHITE_CLOUDWOOL_CARPET,
                AetherIIBlocks.WHITE_ARILUM_LANTERN,
                AetherIIBlocks.WHITE_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.YELLOW_DYED, ConventionalItemTags.YELLOW_DYED).add(
                AetherIIBlocks.YELLOW_CLOUDWOOL,
                AetherIIBlocks.YELLOW_CLOUDWOOL_CARPET,
                AetherIIBlocks.YELLOW_ARILUM_LANTERN,
                AetherIIBlocks.YELLOW_SKYROOT_BED
        );
        this.tagOf(ConventionalBlockTags.WOODEN_FENCE_GATES, ConventionalItemTags.WOODEN_FENCE_GATES).add(
                AetherIIBlocks.SKYROOT_FENCE_GATE,
                AetherIIBlocks.GREATROOT_FENCE_GATE,
                AetherIIBlocks.WISPROOT_FENCE_GATE,
                AetherIIBlocks.AMBEROOT_FENCE_GATE
        );
        this.tagOf(ConventionalBlockTags.WOODEN_FENCES, ConventionalItemTags.WOODEN_FENCES).add(
                AetherIIBlocks.SKYROOT_FENCE,
                AetherIIBlocks.GREATROOT_FENCE,
                AetherIIBlocks.WISPROOT_FENCE,
                AetherIIBlocks.AMBEROOT_FENCE
        );
        this.tagOf(ConventionalBlockTags.GLASS_BLOCKS_COLORLESS, ConventionalItemTags.GLASS_BLOCKS_COLORLESS).add(
                AetherIIBlocks.QUICKSOIL_GLASS,
                AetherIIBlocks.TILED_QUICKSOIL_GLASS,
                AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS,
                AetherIIBlocks.SCATTERGLASS,
                AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS,
                AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS
        );
        this.tagOf(ConventionalBlockTags.GLASS_PANES_COLORLESS, ConventionalItemTags.GLASS_PANES_COLORLESS).add(
                AetherIIBlocks.QUICKSOIL_GLASS_PANE,
                AetherIIBlocks.TILED_QUICKSOIL_GLASS_PANE,
                AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS_PANE,
                AetherIIBlocks.SCATTERGLASS_PANE,
                AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS_PANE,
                AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS_PANE
        );
        this.tagOf(ConventionalBlockTags.ORE_RATES_SINGULAR, ConventionalItemTags.ORE_RATES_SINGULAR).add(
                AetherIIBlocks.HOLYSTONE_QUARTZ_ORE,
                AetherIIBlocks.AMBROSIUM_ORE,
                AetherIIBlocks.ZANITE_ORE,
                AetherIIBlocks.ARKENIUM_ORE,
                AetherIIBlocks.GRAVITITE_ORE,
                AetherIIBlocks.GLINT_ORE,
                AetherIIBlocks.UNDERSHALE_AMBROSIUM_ORE,
                AetherIIBlocks.UNDERSHALE_ZANITE_ORE,
                AetherIIBlocks.UNDERSHALE_ARKENIUM_ORE,
                AetherIIBlocks.UNDERSHALE_GRAVITITE_ORE,
                AetherIIBlocks.UNDERSHALE_GLINT_ORE,
                AetherIIBlocks.CORROBONITE_ORE
        );
        this.tagOf(ConventionalBlockTags.ORES, ConventionalItemTags.ORES).add(
                AetherIIBlocks.HOLYSTONE_QUARTZ_ORE,
                AetherIIBlocks.AMBROSIUM_ORE,
                AetherIIBlocks.ZANITE_ORE,
                AetherIIBlocks.ARKENIUM_ORE,
                AetherIIBlocks.GRAVITITE_ORE,
                AetherIIBlocks.GLINT_ORE,
                AetherIIBlocks.UNDERSHALE_AMBROSIUM_ORE,
                AetherIIBlocks.UNDERSHALE_ZANITE_ORE,
                AetherIIBlocks.UNDERSHALE_ARKENIUM_ORE,
                AetherIIBlocks.UNDERSHALE_GRAVITITE_ORE,
                AetherIIBlocks.UNDERSHALE_GLINT_ORE,
                AetherIIBlocks.CORROBONITE_ORE
        );
        this.tagOf(ConventionalBlockTags.STONES, ConventionalItemTags.STONES).addTags(
                AetherIITags.Blocks.HOLYSTONE,
                AetherIITags.Blocks.UNDERSHALE
        );
        this.tagOf(ConventionalBlockTags.STORAGE_BLOCKS, ConventionalItemTags.STORAGE_BLOCKS).add(
                AetherIIBlocks.INERT_ARKENIUM_BLOCK,
                AetherIIBlocks.INERT_GRAVITITE_BLOCK,
                AetherIIBlocks.AMBROSIUM_BLOCK,
                AetherIIBlocks.ZANITE_BLOCK,
                AetherIIBlocks.ARKENIUM_BLOCK,
                AetherIIBlocks.GRAVITITE_BLOCK,
                AetherIIBlocks.GLINT_BLOCK,
                AetherIIBlocks.CORROBONITE_BLOCK,
                AetherIIBlocks.GOLDEN_AMBER_BLOCK,
                AetherIIBlocks.BRETTL_GRASS_BUNDLE
        );
        this.tagOf(ConventionalBlockTags.NATURAL_WOODS, ConventionalItemTags.NATURAL_WOODS).add(
                AetherIIBlocks.SKYROOT_WOOD,
                AetherIIBlocks.GREATROOT_WOOD,
                AetherIIBlocks.WISPROOT_WOOD,
                AetherIIBlocks.MOSSY_WISPROOT_WOOD,
                AetherIIBlocks.AMBEROOT_WOOD
        );
        this.tagOf(ConventionalBlockTags.STRIPPED_LOGS, ConventionalItemTags.STRIPPED_LOGS).add(
                AetherIIBlocks.STRIPPED_SKYROOT_LOG,
                AetherIIBlocks.STRIPPED_GREATROOT_LOG,
                AetherIIBlocks.STRIPPED_WISPROOT_LOG,
                AetherIIBlocks.STRIPPED_AMBEROOT_LOG
        );
        this.tagOf(ConventionalBlockTags.STRIPPED_WOODS, ConventionalItemTags.STRIPPED_WOODS).add(
                AetherIIBlocks.STRIPPED_SKYROOT_WOOD,
                AetherIIBlocks.STRIPPED_GREATROOT_WOOD,
                AetherIIBlocks.STRIPPED_WISPROOT_WOOD,
                AetherIIBlocks.STRIPPED_AMBEROOT_WOOD
        );
    }

    protected abstract AetherTagAppender<Block> tagOf(TagKey<Block> blockKey, TagKey<Item> itemKey);
}