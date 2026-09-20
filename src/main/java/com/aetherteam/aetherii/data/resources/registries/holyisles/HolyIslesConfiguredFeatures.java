package com.aetherteam.aetherii.data.resources.registries.holyisles;

import net.minecraft.world.level.levelgen.feature.*;
import com.aetherteam.aetherii.world.feature.*;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIBlockStateProperties;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.*;
import com.aetherteam.aetherii.data.resources.registries.AetherIIDensityFunctions;
import com.aetherteam.aetherii.world.feature.configuration.*;
import com.aetherteam.aetherii.world.feature.modifier.predicate.MossyPredicate;
import com.aetherteam.aetherii.world.tree.decorator.*;
import com.aetherteam.aetherii.world.tree.foliage.amberoot.AmberootFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.amberoot.LargeAmberootFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.amberoot.SingularAmberootFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.greatroot.GreatboaFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.greatroot.GreatoakFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.greatroot.GreatrootFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.skyroot.*;
import com.aetherteam.aetherii.world.tree.foliage.wisproot.WisprootFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.wisproot.WisptopFoliagePlacer;
import com.aetherteam.aetherii.world.tree.trunk.MultiTreeTrunkPlacer;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.Fluids;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class HolyIslesConfiguredFeatures {
    public static final RuleTest HOLYSTONE_TEST = new TagMatchTest(AetherIITags.Blocks.HOLYSTONE);
    public static final RuleTest UNDERSHALE_TEST = new BlockMatchTest(AetherIIBlocks.UNDERSHALE);
    public static final RuleTest UNDERGROUND_TEST = new TagMatchTest(AetherIITags.Blocks.AETHER_UNDERGROUND_BLOCKS);

    // Surface
    public static final ResourceKey<Feature> SKYROOT_TWIGS = createKey("skyroot_twigs");
    public static final ResourceKey<Feature> HOLYSTONE_ROCKS = createKey("holystone_rocks");
    public static final ResourceKey<Feature> UNDERWATER_HOLYSTONE_ROCKS = createKey("underwater_holystone_rocks");
    public static final ResourceKey<Feature> MOSSY_HOLYSTONE_BOULDER = createKey("mossy_holystone_boulder");
    public static final ResourceKey<Feature> UNDERWATER_MOSSY_HOLYSTONE_BOULDER = createKey("underwater_mossy_holystone_boulder");
    public static final ResourceKey<Feature> ICESTONE_BOULDER = createKey("icestone_boulder");
    public static final ResourceKey<Feature> UNDERWATER_ARCTIC_HOLYSTONE_BOULDER = createKey("underwater_arctic_holystone_boulder");
    public static final ResourceKey<Feature> FALLEN_SKYROOT_LOG = createKey("fallen_skyroot_log");
    public static final ResourceKey<Feature> FALLEN_WISPROOT_LOG = createKey("fallen_wisproot_log");
    public static final ResourceKey<Feature> MOA_NEST = createKey("moa_nest");


    // Vegetation
    public static final ResourceKey<Feature> GRASS_FIELD = createKey("grass_field");
    public static final ResourceKey<Feature> SMALL_GRASS = createKey("small_grass");
    public static final ResourceKey<Feature> MEDIUM_GRASS = createKey("medium_grass");
    public static final ResourceKey<Feature> LARGE_GRASS = createKey("large_grass");
    public static final ResourceKey<Feature> IRRADIATED_GRASS = createKey("irradiated_grass");
    public static final ResourceKey<Feature> AETHER_FERN = createKey("aether_fern");
    public static final ResourceKey<Feature> VALKYRIE_SPROUT = createKey("valkyrie_sprout");
    public static final ResourceKey<Feature> AETHER_BUSH = createKey("aether_bush");
    public static final ResourceKey<Feature> BLUEBERRY_BUSH = createKey("blueberry_bush");
    public static final ResourceKey<Feature> ORANGE_TREE = createKey("orange_tree_patch");
    public static final ResourceKey<Feature> BRETTL_PLANT = createKey("brettl_plant");

    public static final ResourceKey<Feature> AETHER_BUSH_PATCH = createKey("aether_bush_patch");
    public static final ResourceKey<Feature> BLUEBERRY_BUSH_PATCH = createKey("blueberry_bush_patch");

    public static final ResourceKey<Feature> HOLY_ISLES_FLOWER_PATCH = createKey("holy_isles_flower_patch");
    public static final ResourceKey<Feature> HIGHFIELDS_FLOWER_PATCH = createKey("highfields_flower_patch");
    public static final ResourceKey<Feature> HIGHFIELDS_FLOWER_FIELD = createKey("highfields_flower_field");
    public static final ResourceKey<Feature> MAGNETIC_FLOWER_PATCH = createKey("magnetic_flower_patch");
    public static final ResourceKey<Feature> ARCTIC_FLOWER_PATCH = createKey("arctic_flower_patch");
    public static final ResourceKey<Feature> MAGNETIC_SHROOM_PATCH = createKey("magnetic_shroom_patch");
    public static final ResourceKey<Feature> BRYALINN_FLOWER_PATCH = createKey("bryalinn_flower_patch");

    public static final ResourceKey<Feature> SHORT_ARILUM = createKey("short_arilum");
    public static final ResourceKey<Feature> ARILUM = createKey("arilum");
    public static final ResourceKey<Feature> BLOOMING_ARILUM = createKey("blooming_arilum");
    public static final ResourceKey<Feature> MIXED_ARILUM = createKey("mixed_arilum");
    public static final ResourceKey<Feature> POND_ARILUM = createKey("pond_arilum");

    public static final ResourceKey<Feature> TREE_MOSS_COVER = createKey("tree_moss_cover");

    public static final ResourceKey<Feature> AETHER_GRASS_BONEMEAL = createKey("aether_grass_bonemeal");
    public static final ResourceKey<Feature> ARILUM_BONEMEAL = createKey("arilum_bonemeal");


    // Trees
    public static final ResourceKey<Feature> AMBEROOT = createKey("amberoot");
    public static final ResourceKey<Feature> LARGE_AMBEROOT = createKey("large_amberoot");
    public static final ResourceKey<Feature> SINGULAR_AMBEROOT = createKey("singular_amberoot");

    public static final ResourceKey<Feature> AMBEROOT_WITH_FERNS = createKey("amberoot_with_ferns");
    public static final ResourceKey<Feature> LARGE_AMBEROOT_WITH_FERNS = createKey("large_amberoot_with_ferns");
    public static final ResourceKey<Feature> SINGULAR_AMBEROOT_WITH_FERNS = createKey("singular_amberoot_with_ferns");

    public static final ResourceKey<Feature> AMBEROOT_SNOWY = createKey("amberoot_snowy");
    public static final ResourceKey<Feature> LARGE_AMBEROOT_SNOWY = createKey("large_amberoot_snowy");
    public static final ResourceKey<Feature> SINGULAR_AMBEROOT_SNOWY = createKey("singular_amberoot_snowy");

    public static final ResourceKey<Feature> TREES_AMBEROOT_FIELDS = createKey("trees_amberoot_fields");
    public static final ResourceKey<Feature> TREES_AMBEROOT_SPARSE = createKey("trees_amberoot_sparse");
    public static final ResourceKey<Feature> TREES_AMBEROOT_DENSE = createKey("trees_amberoot_dense");
    public static final ResourceKey<Feature> TREES_AMBEROOT_SNOWY = createKey("trees_amberoot_snowy");

    // Highfields
    public static final ResourceKey<Feature> SKYROOT = createKey("skyroot");
    public static final ResourceKey<Feature> SKYROOT_WITH_FERNS = createKey("skyroot_with_ferns");
    public static final ResourceKey<Feature> SKYROOT_WITH_LEAF_PILES = createKey("skyroot_with_leaf_piles");
    public static final ResourceKey<Feature> SHORT_SKYROOT = createKey("short_skyroot");
    public static final ResourceKey<Feature> SHORT_SKYROOT_WITH_FERNS = createKey("short_skyroot_with_ferns");
    public static final ResourceKey<Feature> LARGE_SKYROOT = createKey("large_skyroot");
    public static final ResourceKey<Feature> LARGE_SKYROOT_WITH_FERNS = createKey("large_skyroot_with_ferns");
    public static final ResourceKey<Feature> NEST_SKYROOT = createKey("nest_skyroot");
    public static final ResourceKey<Feature> NEST_SKYROOT_WITH_LEAF_PILES = createKey("nest_skyroot_with_leaf_piles");
    public static final ResourceKey<Feature> SKYPLANE = createKey("skyplane");
    public static final ResourceKey<Feature> SKYPLANE_PATCH = createKey("skyplane_patch");
    public static final ResourceKey<Feature> SHORT_SKYPLANE = createKey("short_skyplane");
    public static final ResourceKey<Feature> SHORT_SKYPLANE_WITH_FERNS = createKey("short_skyplane_with_ferns");
    public static final ResourceKey<Feature> WISPROOT = createKey("wisproot");
    public static final ResourceKey<Feature> WISPROOT_WITH_FERNS = createKey("wisproot_with_ferns");
    public static final ResourceKey<Feature> WISPROOT_WITH_LEAF_PILES = createKey("wisproot_with_leaf_piles");
    public static final ResourceKey<Feature> GREATOAK = createKey("greatoak");
    public static final ResourceKey<Feature> GREATOAK_WITH_LEAF_PILES = createKey("greatoak_with_leaf_piles");
    public static final ResourceKey<Feature> SHORT_GREATOAK = createKey("short_greatoak");
    public static final ResourceKey<Feature> SHORT_GREATOAK_WITH_FERNS = createKey("short_greatoak_with_ferns");

    public static final ResourceKey<Feature> TREES_BIOME_FLOURISHING_FIELD = createKey("trees_biome_flourishing_field");
    public static final ResourceKey<Feature> TREES_BIOME_VERDANT_WOODS = createKey("trees_biome_verdant_woods");
    public static final ResourceKey<Feature> TREES_BIOME_SHROUDED_FOREST = createKey("trees_biome_shrouded_forest");
    public static final ResourceKey<Feature> TREES_BIOME_SHIMMERING_BASIN = createKey("trees_biome_shimmering_basin");

    // Magnetic
    public static final ResourceKey<Feature> SKYBIRCH = createKey("skybirch");
    public static final ResourceKey<Feature> WISPTOP = createKey("wisptop");
    public static final ResourceKey<Feature> WISPTOP_WITH_LEAF_PILES = createKey("wisptop_with_leaf_piles");
    public static final ResourceKey<Feature> GREATROOT = createKey("greatroot");
    public static final ResourceKey<Feature> SWAMP_GREATROOT = createKey("swamp_greatroot");

    public static final ResourceKey<Feature> SMALL_MAGNETIC_SHROOM = createKey("small_magnetic_shroom");
    public static final ResourceKey<Feature> MEDIUM_MAGNETIC_SHROOM = createKey("medium_magnetic_shroom");
    public static final ResourceKey<Feature> HUGE_MAGNETIC_SHROOM = createKey("huge_magnetic_shroom");

    public static final ResourceKey<Feature> TREES_BIOME_MAGNETIC_SCAR = createKey("trees_biome_magnetic_scar");
    public static final ResourceKey<Feature> TREES_BIOME_TURQUOISE_FOREST = createKey("trees_biome_turquoise_forest");
    public static final ResourceKey<Feature> TREES_BIOME_GLISTENING_SWAMP = createKey("trees_glistening_swamp");
    public static final ResourceKey<Feature> TREES_BIOME_VIOLET_HIGHWOODS = createKey("trees_biome_violet_highwoods");

    public static final ResourceKey<Feature> MAGNETIC_SHROOMS_BIOME_GLISTENING_SWAMP = createKey("magnetic_shrooms_biome_glistening_swamp");

    // Arctic
    public static final ResourceKey<Feature> SKYPINE = createKey("skypine");
    public static final ResourceKey<Feature> SKYPINE_DECORATED = createKey("skypine_decorated");
    public static final ResourceKey<Feature> GREATBOA = createKey("greatboa");
    public static final ResourceKey<Feature> GREATBOA_DECORATED = createKey("greatboa_decorated");

    public static final ResourceKey<Feature> TREES_BIOME_FRIGID_SIERRA = createKey("trees_biome_frigid_sierra");
    public static final ResourceKey<Feature> TREES_BIOME_ENDURING_WOODLANDS = createKey("trees_biome_enduring_woodland");
    public static final ResourceKey<Feature> TREES_BIOME_FROZEN_LAKES = createKey("trees_biome_frozen_lakes");

    // Irradiated
    public static final ResourceKey<Feature> SKYROOT_IRRADIATED = createKey("skyroot_irradiated");
    public static final ResourceKey<Feature> LARGE_SKYROOT_IRRADIATED = createKey("large_skyroot_irradiated");
    public static final ResourceKey<Feature> SKYPLANE_IRRADIATED = createKey("skyplane_irradiated");
    public static final ResourceKey<Feature> SKYBIRCH_IRRADIATED = createKey("skybirch_irradiated");
    public static final ResourceKey<Feature> SKYPINE_IRRADIATED = createKey("skypine_irradiated");
    public static final ResourceKey<Feature> WISPROOT_IRRADIATED = createKey("wisproot_irradiated");
    public static final ResourceKey<Feature> WISPTOP_IRRADIATED = createKey("wisptop_irradiated");
    public static final ResourceKey<Feature> GREATROOT_IRRADIATED = createKey("greatroot_irradiated");
    public static final ResourceKey<Feature> GREATOAK_IRRADIATED = createKey("greatoak_irradiated");
    public static final ResourceKey<Feature> GREATBOA_IRRADIATED = createKey("greatboa_irradiated");

    public static final ResourceKey<Feature> TREES_IRRADIATED = createKey("trees_irradiated");

    public static final ResourceKey<Feature> HUGE_MAGNETIC_SHROOM_GROWN = createKey("huge_magnetic_shroom_grown");


    // Underground
    public static final ResourceKey<Feature> SKY_ROOTS = createKey("sky_roots");
    public static final ResourceKey<Feature> FROSTED_SKY_ROOTS = createKey("frosted_sky_roots");
    public static final ResourceKey<Feature> ICE = createKey("ice");
    public static final ResourceKey<Feature> ICE_CRYSTALS = createKey("ice_crystals");
    public static final ResourceKey<Feature> POINTED_HOLYSTONE = createKey("pointed_holystone");
    public static final ResourceKey<Feature> POINTED_ICHORITE = createKey("pointed_ichorite");
    public static final ResourceKey<Feature> GRASS_BLOCKS = createKey("grass_blocks");
    public static final ResourceKey<Feature> ENCHANTED_GRASS_BLOCKS = createKey("enchanted_grass_blocks");
    public static final ResourceKey<Feature> GRASS_AND_DIRT_FLOOR = createKey("grass_and_dirt_floor");
    public static final ResourceKey<Feature> ENCHANTED_GRASS_AND_DIRT_FLOOR = createKey("enchanted_grass_and_dirt_floor");
    public static final ResourceKey<Feature> SMALL_MYCELIUM_FLOOR = createKey("small_mycelium_floor");
    public static final ResourceKey<Feature> BIG_MYCELIUM_FLOOR = createKey("big_mycelium_floor");
    public static final ResourceKey<Feature> COARSE_AETHER_DIRT_FLOOR = createKey("coarse_aether_dirt_floor");
    public static final ResourceKey<Feature> COARSE_AETHER_DIRT_CEILING = createKey("coarse_aether_dirt_ceiling");
    public static final ResourceKey<Feature> COARSE_AETHER_DIRT_FROSTED_CEILING = createKey("coarse_aether_dirt_frosted_ceiling");
    public static final ResourceKey<Feature> ICE_CEILING = createKey("ice_ceiling");
    public static final ResourceKey<Feature> BRYALINN_MOSS_CARPET = createKey("bryalinn_moss_carpet");
    public static final ResourceKey<Feature> BRYALINN_MOSS_FLOWERS = createKey("bryalinn_moss_flowers");
    public static final ResourceKey<Feature> BRYALINN_MOSS_VINES = createKey("bryalinn_moss_vines");
    public static final ResourceKey<Feature> BRYALINN_MOSS_FLOOR = createKey("bryalinn_moss_floor");
    public static final ResourceKey<Feature> BRYALINN_MOSS_FLOOR_SWAMP = createKey("bryalinn_moss_floor_swamp");
    public static final ResourceKey<Feature> SHAYELINN_MOSS_CARPET = createKey("shayelinn_moss_carpet");
    public static final ResourceKey<Feature> SHAYELINN_MOSS_VINES = createKey("shayelinn_moss_vines");
    public static final ResourceKey<Feature> SHAYELINN_MOSS_FLOOR = createKey("shayelinn_moss_floor");
    public static final ResourceKey<Feature> AMBRELINN_MOSS_CARPET = createKey("ambrelinn_moss_carpet");
    public static final ResourceKey<Feature> AMBRELINN_MOSS_VINES = createKey("ambrelinn_moss_vines");
    public static final ResourceKey<Feature> AMBRELINN_MOSS_FLOOR = createKey("ambrelinn_moss_floor");

    public static final ResourceKey<Feature> UNSTABLE_HOLYSTONE = createKey("unstable_holystone");
    public static final ResourceKey<Feature> UNSTABLE_UNDERSHALE = createKey("unstable_undershale");

    public static final ResourceKey<Feature> ALKAHEST_POOL = createKey("alkahest_pool");

    public static final ResourceKey<Feature> ORE_SCATTERGLASS = createKey("ore_scatterglass");
    public static final ResourceKey<Feature> ORE_ICESTONE = createKey("ore_icestone");
    public static final ResourceKey<Feature> ORE_ICESTONE_SMALL = createKey("ore_icestone_small");
    public static final ResourceKey<Feature> ORE_AGIOSITE = createKey("ore_agiosite");
    public static final ResourceKey<Feature> ORE_AGIOSITE_SMALL = createKey("ore_agiosite_small");

    public static final ResourceKey<Feature> ORE_HOLYSTONE_QUARTZ = createKey("ore_holystone_quartz");
    public static final ResourceKey<Feature> ORE_AMBROSIUM = createKey("ore_ambrosium");
    public static final ResourceKey<Feature> ORE_ZANITE = createKey("ore_zanite");
    public static final ResourceKey<Feature> ORE_ZANITE_MOUNTAIN = createKey("ore_zanite_mountain");
    public static final ResourceKey<Feature> ORE_GLINT = createKey("ore_glint");
    public static final ResourceKey<Feature> ORE_ARKENIUM = createKey("ore_arkenium");
    public static final ResourceKey<Feature> ORE_GRAVITITE_BURIED = createKey("ore_gravitite_buried");
    public static final ResourceKey<Feature> ORE_GRAVITITE = createKey("ore_gravitite");
    public static final ResourceKey<Feature> ORE_CORROBONITE = createKey("ore_corrobonite");

    public static final ResourceKey<Feature> ORE_HESTVEIL_OPEN = createKey("ore_hestveil_open");
    public static final ResourceKey<Feature> ORE_HESTVEIL_BURIED = createKey("ore_hestveil_buried");


    // Worldgen
    public static final ResourceKey<Feature> COARSE_AETHER_DIRT_SURFACE = createKey("coarse_aether_dirt_surface");
    public static final ResourceKey<Feature> DISK_BRYALINN_MOSS = createKey("disk_bryalinn_moss");
    public static final ResourceKey<Feature> DISK_SHAYELINN_MOSS = createKey("disk_shayelinn_moss");

    public static final ResourceKey<Feature> COAST_QUICKSOIL = createKey("coast_quicksoil");
    public static final ResourceKey<Feature> COAST_FERROSITE_SAND = createKey("coast_ferrosite_sand");
    public static final ResourceKey<Feature> COAST_FERROSITE_PILLAR = createKey("coast_ferrosite_pillar");
    public static final ResourceKey<Feature> COAST_ARCTIC_PACKED_ICE = createKey("coast_arctic_packed_ice");

    public static final ResourceKey<Feature> WATER_POND = createKey("water_pond");
    public static final ResourceKey<Feature> WATER_POND_TUNDRA = createKey("water_pond_tundra");
    public static final ResourceKey<Feature> WATER_SPRING = createKey("water_spring");
    public static final ResourceKey<Feature> NOISE_LAKE = createKey("noise_lake");
    public static final ResourceKey<Feature> NOISE_LAKE_ARCTIC = createKey("noise_lake_arctic");
    public static final ResourceKey<Feature> NOISE_LAKE_SWAMP = createKey("noise_lake_swamp");

    public static final ResourceKey<Feature> FERROSITE_PILLAR = createKey("ferrosite_pillar");
    public static final ResourceKey<Feature> FERROSITE_PILLAR_TURF_TOP = createKey("ferrosite_pillar_turf_top");
    public static final ResourceKey<Feature> FERROSITE_PILLAR_TURF = createKey("ferrosite_pillar_turf");

    public static final ResourceKey<Feature> FERROSITE_SPIKE = createKey("ferrosite_spike");
    public static final ResourceKey<Feature> ARCTIC_ICE_SPIKE = createKey("arctic_ice_spike");
    public static final ResourceKey<Feature> MEGA_ARCTIC_ICE_SPIKE = createKey("mega_arctic_ice_spike");
    public static final ResourceKey<Feature> ARCTIC_ICE_SPIKE_VARIANTS = createKey("arctic_ice_spike_variants");

    public static final ResourceKey<Feature> FREEZE_TOP_LAYER_ARCTIC = createKey("freeze_top_layer_arctic");
    public static final ResourceKey<Feature> FREEZE_TOP_LAYER_TUNDRA = createKey("freeze_top_layer_tundra");

    public static final ResourceKey<Feature> CRATER = createKey("crater");

    public static final ResourceKey<Feature> CLOUDBED = createKey("cloudbed");


    // Dungeon
    public static final ResourceKey<Feature> BRYALINN_MOSS_STRUCTURE = createKey("bryalinn_moss_dungeon");
    public static final ResourceKey<Feature> SHAYELINN_MOSS_STRUCTURE = createKey("shayelinn_moss_dungeon");
    public static final ResourceKey<Feature> AMBRELINN_MOSS_STRUCTURE = createKey("ambrelinn_moss_dungeon");

    public static final ResourceKey<Feature> PILE_HOLYSTONE = createKey("pile_holystone");
    public static final ResourceKey<Feature> PILE_UNDERSHALE = createKey("pile_undershale");
    public static final ResourceKey<Feature> PILE_AGIOSITE = createKey("pile_agiosite");
    public static final ResourceKey<Feature> PILE_AMBROSIUM_ORE = createKey("pile_ambrosium_ore");
    public static final ResourceKey<Feature> PILE_FERROSITE = createKey("pile_ferrosite");
    public static final ResourceKey<Feature> PILE_ICESTONE = createKey("pile_icestone");
    public static final ResourceKey<Feature> PILE_ARCTIC_PACKED_ICE = createKey("pile_arctic_packed_ice");

    public static final ResourceKey<Feature> PILES_MATERIAL_DEPOSIT = createKey("piles_material_deposit");
    public static final ResourceKey<Feature> PILES_COLD_STORAGE = createKey("piles_cold_storage");

    public static final ResourceKey<Feature> LARGE_SHELF_ROTSHROOM = createKey("large_shelf_rotshroom");
    public static final ResourceKey<Feature> LARGE_SHELF_ROTSHROOM_UNDERGROUND = createKey("large_shelf_rotshroom_underground");
    public static final ResourceKey<Feature> ROTSHROOM_PATCH = createKey("rotshroom_patch");

    public static final ResourceKey<Feature> COARSE_AETHER_DIRT_DUNGEON = createKey("coarse_aether_dirt_dungeon");

    public static final ResourceKey<Feature> UNDERGROWTH_VINE = createKey("undergrowth_vine");
    public static final ResourceKey<Feature> UNDERGROWTH_PATCH = createKey("undergrowth_patch");

    public static final ResourceKey<Feature> INFECTED_GUARDIAN_TREE_ENTRANCE_COVER = createKey("infected_guardian_tree_entrance_cover");
    public static final ResourceKey<Feature> INFECTED_GUARDIAN_TREE_STAIRCASE_COVER = createKey("infected_guardian_tree_staircase_cover");
    public static final ResourceKey<Feature> INFECTED_GUARDIAN_TREE_LOBBY_COVER = createKey("infected_guardian_tree_lobby_cover");
    public static final ResourceKey<Feature> INFECTED_GUARDIAN_TREE_BOSS_ROOM_COVER = createKey("infected_guardian_tree_boss_room_cover");


    // Air
    public static final ResourceKey<Feature> COLD_AERCLOUD = createKey("cold_aercloud");
    public static final ResourceKey<Feature> GOLDEN_AERCLOUD = createKey("golden_aercloud");
    public static final ResourceKey<Feature> BLUE_AERCLOUD = createKey("blue_aercloud");
    public static final ResourceKey<Feature> GREEN_AERCLOUD = createKey("green_aercloud");
    public static final ResourceKey<Feature> PURPLE_AERCLOUD = createKey("purple_aercloud");
    public static final ResourceKey<Feature> PURPLE_AERCLOUD_SMALL = createKey("purple_aercloud_small");
    public static final ResourceKey<Feature> STORM_AERCLOUD = createKey("storm_aercloud");


    public static void bootstrap(BootstrapContext<Feature> context) {
        bootstrapSurface(context);
        bootstrapVegetation(context);
        bootstrapTrees(context);
        bootstrapUnderground(context);
        bootstrapWorldgen(context);
        bootstrapAir(context);
        bootstrapDungeon(context);
    }

    private static void bootstrapSurface(BootstrapContext<Feature> context) {
        HolderGetter<Feature> configuredFeatures = context.lookup(Registries.FEATURE);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        WeightedList.Builder<BlockState> twigs = new WeightedList.Builder<>();
        for (Direction facing : TwigBlock.FACING.getPossibleValues()) {
            for (int amount : TwigBlock.AMOUNT.getPossibleValues()) {
                twigs.add(AetherIIBlocks.SKYROOT_TWIG.defaultBlockState().setValue(TwigBlock.FACING, facing).setValue(TwigBlock.AMOUNT, amount), amount);
            }
        }

        WeightedList.Builder<BlockState> rocks = new WeightedList.Builder<>();
        for (Direction facing : RockBlock.FACING.getPossibleValues()) {
            for (int amount : RockBlock.AMOUNT.getPossibleValues()) {
                rocks.add(AetherIIBlocks.HOLYSTONE_ROCK.defaultBlockState().setValue(RockBlock.FACING, facing).setValue(RockBlock.AMOUNT, amount), amount);
            }
        }

        WeightedList.Builder<BlockState> underwaterRocks = new WeightedList.Builder<>();
        for (Direction facing : RockBlock.FACING.getPossibleValues()) {
            for (int amount : RockBlock.AMOUNT.getPossibleValues()) {
                underwaterRocks.add(AetherIIBlocks.HOLYSTONE_ROCK.defaultBlockState().setValue(RockBlock.FACING, facing).setValue(RockBlock.AMOUNT, amount).setValue(RockBlock.WATERLOGGED, true), amount);
            }
        }

        register(context, SKYROOT_TWIGS, (new SimpleBlockFeature(new WeightedStateProvider(twigs))));
        register(context, HOLYSTONE_ROCKS, (new SimpleBlockFeature(new WeightedStateProvider(rocks))));
        register(context, UNDERWATER_HOLYSTONE_ROCKS, (new SimpleBlockFeature(new WeightedStateProvider(underwaterRocks))));
        register(context, MOSSY_HOLYSTONE_BOULDER, new BoulderFeature(new BoulderConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(AetherIIBlocks.MOSSY_HOLYSTONE.defaultBlockState(), 4)
                        .add(AetherIIBlocks.HOLYSTONE.defaultBlockState(), 1)
                        .build()),
                0.5F,
                UniformFloat.of(0.0F, 1.0F),
                Optional.of(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(HOLYSTONE_ROCKS), CountPlacement.of(UniformInt.of(1, 6)))),
                1.0F)));
        register(context, UNDERWATER_MOSSY_HOLYSTONE_BOULDER, new BoulderFeature(new BoulderConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(AetherIIBlocks.MOSSY_HOLYSTONE.defaultBlockState(), 5)
                        .add(AetherIIBlocks.HOLYSTONE.defaultBlockState(), 1)
                        .build()),
                0.5F,
                UniformFloat.of(0.0F, 1.25F),
                Optional.of(PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                        List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(DISK_BRYALINN_MOSS)), 0.6F)),
                        placedFeatures.getOrThrow(HolyIslesPlacedFeatures.HOLYSTONE_ROCKS_UNDERWATER)
                ), CountPlacement.of(UniformInt.of(1, 3)))),
                1.0F)));
        register(context, ICESTONE_BOULDER, new BoulderFeature(new BoulderConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(AetherIIBlocks.ICESTONE.defaultBlockState(), 1)
                        .add(AetherIIBlocks.HOLYSTONE.defaultBlockState(), 3)
                        .build()),
                0.5F,
                UniformFloat.of(0.0F, 1.0F),
                Optional.empty(),
                0.0F)));
        register(context, UNDERWATER_ARCTIC_HOLYSTONE_BOULDER, new BoulderFeature(new BoulderConfiguration(
                BlockStateProvider.of(AetherIIBlocks.HOLYSTONE),
                0.5F,
                UniformFloat.of(0.0F, 1.25F),
                Optional.of(PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                        List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(DISK_SHAYELINN_MOSS)), 0.6F)),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(UNDERWATER_HOLYSTONE_ROCKS), CountPlacement.of(UniformInt.of(1, 4)))
                ), CountPlacement.of(UniformInt.of(1, 3)))),
                1.0F)));
        register(context, FALLEN_SKYROOT_LOG, new FallenLogFeature(new FallenLogConfiguration(
                BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG),
                UniformInt.of(2, 4),
                Optional.empty(),
                0.0F,
                AetherIITags.Blocks.SUPPORTS_FALLEN_LOG
        )));
        register(context, FALLEN_WISPROOT_LOG, new FallenLogFeature(new FallenLogConfiguration(
                BlockStateProvider.of(AetherIIBlocks.WISPROOT_LOG),
                UniformInt.of(3, 6),
                Optional.empty(),
                0.0F,
                AetherIITags.Blocks.SUPPORTS_FALLEN_LOG
        )));
        register(context, MOA_NEST, new MoaNestFeature(new MoaNestConfiguration(BlockStateProvider.of(AetherIIBlocks.WOVEN_SKYROOT_STICKS), 1.5F, 2, true)));
    }

    private static void bootstrapVegetation(BootstrapContext<Feature> context) {
        HolderGetter<Feature> configuredFeatures = context.lookup(Registries.FEATURE);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        WeightedList.Builder<BlockState> holpupea = new WeightedList.Builder<>();
        for (Direction facing : MossFlowersBlock.FACING.getPossibleValues()) {
            for (int amount : MossFlowersBlock.AMOUNT.getPossibleValues()) {
                holpupea.add(AetherIIBlocks.HOLPUPEA.defaultBlockState().setValue(MossFlowersBlock.AMOUNT, amount).setValue(MossFlowersBlock.FACING, facing), amount);
            }
        }

        WeightedList.Builder<BlockState> bryallinMossFlowers = WeightedList.builder();
        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                bryallinMossFlowers.add(AetherIIBlocks.BRYALINN_MOSS_FLOWERS.defaultBlockState().setValue(MossFlowersBlock.AMOUNT, i).setValue(MossFlowersBlock.FACING, direction), 1);
            }
        }

        register(context, GRASS_FIELD, new AetherGrassFeature(new SimpleBlockConfiguration(
                        new NoiseProvider(
                                2345L,
                                NormalNoise.createParity(0, 1.0),
                                0.02F,
                                List.of(
                                        AetherIIBlocks.TALL_AETHER_GRASS.defaultBlockState(),
                                        AetherIIBlocks.MEDIUM_AETHER_GRASS.defaultBlockState(),
                                        AetherIIBlocks.SHORT_AETHER_GRASS.defaultBlockState(),
                                        AetherIIBlocks.MEDIUM_AETHER_GRASS.defaultBlockState(),
                                        AetherIIBlocks.TALL_AETHER_GRASS.defaultBlockState()
                                )
                        )
                ))
        );
        register(context, SMALL_GRASS, new AetherGrassFeature(new SimpleBlockConfiguration(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>()
                                .add(AetherIIBlocks.SHORT_AETHER_GRASS.defaultBlockState(), 2)
                                .add(AetherIIBlocks.AETHER_FERN.defaultBlockState(), 1)
                                .build())
                ))
        );
        register(context, MEDIUM_GRASS, new AetherGrassFeature(new SimpleBlockConfiguration(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>()
                                .add(AetherIIBlocks.SHORT_AETHER_GRASS.defaultBlockState(), 2)
                                .add(AetherIIBlocks.MEDIUM_AETHER_GRASS.defaultBlockState(), 3)
                                .add(AetherIIBlocks.AETHER_FERN.defaultBlockState(), 1)
                                .build())
                ))
        );
        register(context, LARGE_GRASS, new AetherGrassFeature(new SimpleBlockConfiguration(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>()
                                .add(AetherIIBlocks.SHORT_AETHER_GRASS.defaultBlockState(), 2)
                                .add(AetherIIBlocks.MEDIUM_AETHER_GRASS.defaultBlockState(), 3)
                                .add(AetherIIBlocks.TALL_AETHER_GRASS.defaultBlockState(), 4)
                                .add(AetherIIBlocks.AETHER_FERN.defaultBlockState(), 1)
                                .build())
                ))
        );
        register(context, IRRADIATED_GRASS, new AetherGrassFeature(new SimpleBlockConfiguration(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>()
                                .add(AetherIIBlocks.SHORT_AETHER_GRASS.defaultBlockState(), 1)
                                .add(AetherIIBlocks.MEDIUM_AETHER_GRASS.defaultBlockState(), 2)
                                .add(AetherIIBlocks.TALL_AETHER_GRASS.defaultBlockState(), 1)
                                .add(AetherIIBlocks.AETHER_FERN.defaultBlockState(), 1)
                                .add(AetherIIBlocks.SHIELD_FERN.defaultBlockState(), 2)
                                .add(AetherIIBlocks.BLADE_POA.defaultBlockState(), 2)
                                .build())
                ))
        );
        register(context, AETHER_FERN, new AetherGrassFeature(new SimpleBlockConfiguration(BlockStateProvider.of(AetherIIBlocks.AETHER_FERN.defaultBlockState()))));
        register(context, VALKYRIE_SPROUT, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.VALKYRIE_SPROUT.defaultBlockState().setValue(ValkyrieSproutBlock.AGE, 2))));
        register(context, AETHER_BUSH, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.AETHER_BUSH.defaultBlockState())));
        register(context, BLUEBERRY_BUSH, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.BLUEBERRY_BUSH.defaultBlockState())));
        register(context, ORANGE_TREE, new OrangeTreeFeature(new SimpleBlockConfiguration(BlockStateProvider.of(AetherIIBlocks.ORANGE_TREE.defaultBlockState().setValue(OrangeTreeBlock.AGE, 4)))));
        register(context, BRETTL_PLANT, new BrettlPlantFeature());

        register(context, AETHER_BUSH_PATCH, new MergedFeature(new MergedConfiguration(List.of(
                placedFeatures.getOrThrow(HolyIslesPlacedFeatures.BUSH_FERNS_PATCH),
                placedFeatures.getOrThrow(HolyIslesPlacedFeatures.AETHER_BUSH_PATCH)
        ))));
        register(context, BLUEBERRY_BUSH_PATCH, new MergedFeature(new MergedConfiguration(List.of(
                placedFeatures.getOrThrow(HolyIslesPlacedFeatures.BUSH_FERNS_PATCH),
                placedFeatures.getOrThrow(HolyIslesPlacedFeatures.BLUEBERRY_BUSH_PATCH)
        ))));

        register(context, HOLY_ISLES_FLOWER_PATCH, new AetherFlowerFeature(new SimpleBlockConfiguration(new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(AetherIIBlocks.AECHOR_CUTTING.defaultBlockState(), 2)
                        .add(AetherIIBlocks.CARRION_CUTTING.defaultBlockState(), 1)
                        .build()
                )))
        );
        register(context, HIGHFIELDS_FLOWER_PATCH, new AetherFlowerFeature(new SimpleBlockConfiguration(
                        new DualNoiseProvider(
                                new InclusiveRange<>(1, 3),
                                NormalNoise.createParity(-5, 1.0),
                                1.0F,
                                2345L,
                                NormalNoise.createParity(-1, 1.0),
                                1.0F,
                                List.of(
                                        AetherIIBlocks.HESPEROSE.defaultBlockState(),
                                        AetherIIBlocks.TARABLOOM.defaultBlockState()
                                )
                        )
                ))
        );
        register(context, HIGHFIELDS_FLOWER_FIELD, new AetherGrassFeature(new SimpleBlockConfiguration(
                        new NoiseProvider(
                                5432L,
                                NormalNoise.createParity(0, 1.0),
                                0.1F,
                                List.of(
                                        Blocks.AIR.defaultBlockState(),
                                        AetherIIBlocks.HESPEROSE.defaultBlockState(),
                                        Blocks.AIR.defaultBlockState(),
                                        AetherIIBlocks.TARABLOOM.defaultBlockState(),
                                        Blocks.AIR.defaultBlockState()
                                )
                        )
                ))
        );
        register(context, MAGNETIC_FLOWER_PATCH, new AetherFlowerFeature(new SimpleBlockConfiguration(
                        new DualNoiseProvider(
                                new InclusiveRange<>(1, 3),
                                NormalNoise.createParity(-5, 1.0),
                                1.0F,
                                2345L,
                                NormalNoise.createParity(-1, 1.0),
                                1.0F,
                                List.of(
                                        AetherIIBlocks.POASPROUT.defaultBlockState(),
                                        AetherIIBlocks.LILICHIME.defaultBlockState(),
                                        AetherIIBlocks.PLURACIAN.defaultBlockState()
                                )
                        )
                ))
        );
        register(context,
                ARCTIC_FLOWER_PATCH,
                new RandomSelectorFeature(List.of(
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(new AetherFlowerFeature(new SimpleBlockConfiguration(new WeightedStateProvider(holpupea))),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.anyOf(
                                                BlockPredicate.matchesTag(Vec3i.ZERO.below(), AetherIITags.Blocks.SUPPORTS_AETHER_PLANT),
                                                new MossyPredicate(Vec3i.ZERO.below())), BlockPredicate.replaceable(), BlockPredicate.noFluid()))), 0.5F)

                ), PlacementUtils.inlinePlaced(new AetherFlowerFeature(new SimpleBlockConfiguration(
                                new DualNoiseProvider(
                                        new InclusiveRange<>(1, 3),
                                        NormalNoise.createParity(-5, 1.0),
                                        1.0F,
                                        2345L,
                                        NormalNoise.createParity(-1, 1.0),
                                        1.0F,
                                        List.of(
                                                AetherIIBlocks.SATIVAL_SHOOT.defaultBlockState()
                                        )
                                )
                        )), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.anyOf(BlockPredicate.matchesTag(Vec3i.ZERO.below(), AetherIITags.Blocks.SUPPORTS_AETHER_PLANT), new MossyPredicate(Vec3i.ZERO.below())), BlockPredicate.replaceable(), BlockPredicate.noFluid())))
                )
        );
        register(context, MAGNETIC_SHROOM_PATCH, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.MAGNETIC_SHROOM.defaultBlockState())));
        register(context, BRYALINN_FLOWER_PATCH, new SimpleBlockFeature(new WeightedStateProvider(bryallinMossFlowers)));

        register(context, SHORT_ARILUM, new ArilumFeature(new ArilumConfiguration(BlockStateProvider.of(AetherIIBlocks.ARILUM), BlockStateProvider.of(AetherIIBlocks.ARILUM_PLANT), UniformInt.of(0, 2), ConstantInt.of(0))));
        register(context, ARILUM, new ArilumFeature(new ArilumConfiguration(BlockStateProvider.of(AetherIIBlocks.ARILUM), BlockStateProvider.of(AetherIIBlocks.ARILUM_PLANT), UniformInt.of(1, 8), ConstantInt.of(0))));
        register(context, BLOOMING_ARILUM, new ArilumFeature(new ArilumConfiguration(BlockStateProvider.of(AetherIIBlocks.BLOOMING_ARILUM), BlockStateProvider.of(AetherIIBlocks.BLOOMING_ARILUM_PLANT), UniformInt.of(1, 3), UniformInt.of(4, 6))));
        register(context, MIXED_ARILUM, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(BLOOMING_ARILUM)), 0.6F),
        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SHORT_ARILUM)), 0.15F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ARILUM))));
        register(context, POND_ARILUM, new ArilumFeature(new ArilumConfiguration(BlockStateProvider.of(AetherIIBlocks.ARILUM), BlockStateProvider.of(AetherIIBlocks.ARILUM_PLANT), UniformInt.of(0, 3), ConstantInt.of(0))));

        register(context, TREE_MOSS_COVER, new TreeMossCoverFeature());

        register(context, AETHER_GRASS_BONEMEAL, new AetherGrassFeature(new SimpleBlockConfiguration(new WeightedStateProvider(WeightedList.<BlockState>builder()
                .add(AetherIIBlocks.SHORT_AETHER_GRASS.defaultBlockState(), 1)
                .add(AetherIIBlocks.MEDIUM_AETHER_GRASS.defaultBlockState(), 1)
                .add(AetherIIBlocks.TALL_AETHER_GRASS.defaultBlockState(), 1)
        ))));
        register(context, ARILUM_BONEMEAL, new RandomSelectorFeature(List.of(
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(
                                new ArilumFeature(new ArilumConfiguration(BlockStateProvider.of(AetherIIBlocks.ARILUM), BlockStateProvider.of(AetherIIBlocks.ARILUM_PLANT), UniformInt.of(1, 7), ConstantInt.of(0))),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(BlockPos.ZERO.below(), AetherIITags.Blocks.SUPPORTS_ARILUM), BlockPredicate.matchesBlocks(Blocks.WATER)))
                        ), 0.5F)),
                        PlacementUtils.inlinePlaced(
                                new ArilumFeature(new ArilumConfiguration(BlockStateProvider.of(AetherIIBlocks.BLOOMING_ARILUM), BlockStateProvider.of(AetherIIBlocks.BLOOMING_ARILUM_PLANT), UniformInt.of(1, 3), UniformInt.of(4, 6))),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(BlockPos.ZERO.below(), AetherIITags.Blocks.SUPPORTS_ARILUM), BlockPredicate.matchesBlocks(Blocks.WATER))))
                )
        );

    }

    private static void bootstrapTrees(BootstrapContext<Feature> context) {
        HolderGetter<Feature> configuredFeatures = context.lookup(Registries.FEATURE);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

        WeightedList.Builder<BlockState> twigs = new WeightedList.Builder<>();
        for (Direction facing : TwigBlock.FACING.getPossibleValues()) {
            for (int amount : TwigBlock.AMOUNT.getPossibleValues()) {
                twigs.add(AetherIIBlocks.SKYROOT_TWIG.defaultBlockState().setValue(TwigBlock.FACING, facing).setValue(TwigBlock.AMOUNT, amount), amount);
            }
        }

        WeightedList.Builder<BlockState> bryallinMossFlowers = WeightedList.builder();
        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                bryallinMossFlowers.add(AetherIIBlocks.BRYALINN_MOSS_FLOWERS.defaultBlockState().setValue(MossFlowersBlock.AMOUNT, i).setValue(MossFlowersBlock.FACING, direction), 1);
            }
        }

        WeightedList.Builder<BlockState> tarahespFlowers = WeightedList.builder();
        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                tarahespFlowers.add(AetherIIBlocks.TARAHESP_FLOWERS.defaultBlockState().setValue(MossFlowersBlock.AMOUNT, i).setValue(MossFlowersBlock.FACING, direction), 1);
            }
        }

        register(context, AMBEROOT, new TreeFeature.Builder(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.AMBEROOT_DEPOSIT.defaultBlockState(), 3).add(AetherIIBlocks.AMBEROOT_LOG.defaultBlockState(), 2).build()),
                        new StraightTrunkPlacer(5, 4, 0), BlockStateProvider.of(AetherIIBlocks.AMBEROOT_LEAVES.defaultBlockState()),
                        new AmberootFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.AMBEROOT_TRUNK.defaultBlockState()), 0.5F, 0.33F, 0.4F))).build());
        register(context, LARGE_AMBEROOT, new TreeFeature.Builder(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.AMBEROOT_DEPOSIT.defaultBlockState(), 3).add(AetherIIBlocks.AMBEROOT_LOG.defaultBlockState(), 2).build()),
                        new StraightTrunkPlacer(8, 5, 0), BlockStateProvider.of(AetherIIBlocks.AMBEROOT_LEAVES.defaultBlockState()),
                        new LargeAmberootFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(3)), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.AMBEROOT_TRUNK.defaultBlockState()), 0.75F, 0.5F, 0.7F))).build());
        register(context, SINGULAR_AMBEROOT, new TreeFeature.Builder(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.AMBEROOT_DEPOSIT.defaultBlockState(), 3).add(AetherIIBlocks.AMBEROOT_LOG.defaultBlockState(), 2).build()),
                        new StraightTrunkPlacer(6, 4, 0), BlockStateProvider.of(AetherIIBlocks.AMBEROOT_LEAVES.defaultBlockState()),
                        new SingularAmberootFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.AMBEROOT_TRUNK.defaultBlockState()), 0.2F, 0.1F, 0.15F))).build());

        register(context, AMBEROOT_WITH_FERNS, new TreeFeature.Builder(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.AMBEROOT_DEPOSIT.defaultBlockState(), 3).add(AetherIIBlocks.AMBEROOT_LOG.defaultBlockState(), 2).build()),
                        new StraightTrunkPlacer(5, 4, 0), BlockStateProvider.of(AetherIIBlocks.AMBEROOT_LEAVES.defaultBlockState()),
                        new AmberootFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.AMBEROOT_TRUNK.defaultBlockState()), 0.5F, 0.33F, 0.4F),
                                new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_FERN), 6)
                        )).build());
        register(context, LARGE_AMBEROOT_WITH_FERNS, new TreeFeature.Builder(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.AMBEROOT_DEPOSIT.defaultBlockState(), 3).add(AetherIIBlocks.AMBEROOT_LOG.defaultBlockState(), 2).build()),
                        new StraightTrunkPlacer(8, 5, 0), BlockStateProvider.of(AetherIIBlocks.AMBEROOT_LEAVES.defaultBlockState()),
                        new LargeAmberootFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(3)), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.AMBEROOT_TRUNK.defaultBlockState()), 0.75F, 0.5F, 0.7F),
                                new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_FERN), 6)
                        )).build());
        register(context, SINGULAR_AMBEROOT_WITH_FERNS, new TreeFeature.Builder(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.AMBEROOT_DEPOSIT.defaultBlockState(), 3).add(AetherIIBlocks.AMBEROOT_LOG.defaultBlockState(), 2).build()),
                        new StraightTrunkPlacer(6, 4, 0), BlockStateProvider.of(AetherIIBlocks.AMBEROOT_LEAVES.defaultBlockState()),
                        new SingularAmberootFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.AMBEROOT_TRUNK.defaultBlockState()), 0.2F, 0.1F, 0.15F),
                                new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_FERN), 6)
                        )).build());

        register(context, AMBEROOT_SNOWY, new TreeFeature.Builder(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.AMBEROOT_DEPOSIT.defaultBlockState(), 3).add(AetherIIBlocks.AMBEROOT_LOG.defaultBlockState(), 2).build()),
                        new StraightTrunkPlacer(5, 4, 0), BlockStateProvider.of(AetherIIBlocks.AMBEROOT_LEAVES.defaultBlockState()),
                        new AmberootFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new AlterGroundTagDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_GRASS_BLOCK), AetherIITags.Blocks.GRASS_SNOW_REPLACEABLE),
                                new SnowDecorator(),
                                new GroundFeatureDecorator(new WeightedStateProvider(twigs), 50),
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.AMBEROOT_TRUNK.defaultBlockState()), 0.5F, 0.33F, 0.4F))).build());
        register(context, LARGE_AMBEROOT_SNOWY, new TreeFeature.Builder(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.AMBEROOT_DEPOSIT.defaultBlockState(), 3).add(AetherIIBlocks.AMBEROOT_LOG.defaultBlockState(), 2).build()),
                        new StraightTrunkPlacer(8, 5, 0), BlockStateProvider.of(AetherIIBlocks.AMBEROOT_LEAVES.defaultBlockState()),
                        new LargeAmberootFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(3)), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new AlterGroundTagDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_GRASS_BLOCK), AetherIITags.Blocks.GRASS_SNOW_REPLACEABLE),
                                new SnowDecorator(),
                                new GroundFeatureDecorator(new WeightedStateProvider(twigs), 50),
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.AMBEROOT_TRUNK.defaultBlockState()), 0.75F, 0.5F, 0.7F))).build());
        register(context, SINGULAR_AMBEROOT_SNOWY, new TreeFeature.Builder(
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.AMBEROOT_DEPOSIT.defaultBlockState(), 3).add(AetherIIBlocks.AMBEROOT_LOG.defaultBlockState(), 2).build()),
                        new StraightTrunkPlacer(6, 4, 0), BlockStateProvider.of(AetherIIBlocks.AMBEROOT_LEAVES.defaultBlockState()),
                        new SingularAmberootFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new AlterGroundTagDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_GRASS_BLOCK), AetherIITags.Blocks.GRASS_SNOW_REPLACEABLE),
                                new SnowDecorator(),
                                new GroundFeatureDecorator(new WeightedStateProvider(twigs), 50),
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.AMBEROOT_TRUNK.defaultBlockState()), 0.2F, 0.1F, 0.15F))).build());

        register(context, TREES_AMBEROOT_FIELDS, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SINGULAR_AMBEROOT_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.3F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(LARGE_AMBEROOT_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.2F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AMBEROOT_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING))));
        register(context, TREES_AMBEROOT_SPARSE, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SINGULAR_AMBEROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.3F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(LARGE_AMBEROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.2F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AMBEROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING))));
        register(context, TREES_AMBEROOT_DENSE, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SINGULAR_AMBEROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.3F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AMBEROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.2F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(LARGE_AMBEROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING))));
        register(context, TREES_AMBEROOT_SNOWY, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SINGULAR_AMBEROOT_SNOWY), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.3F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(LARGE_AMBEROOT_SNOWY), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.2F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AMBEROOT_SNOWY), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING))));

        // Highfields
        register(context, SKYROOT, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(4, 2, 1),
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAVES.defaultBlockState()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT)).build());
        register(context, SKYROOT_WITH_FERNS, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(4, 2, 1),
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAVES.defaultBlockState()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_FERN), 6))).build());
        register(context, SKYROOT_WITH_LEAF_PILES, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(4, 2, 1),
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAVES.defaultBlockState()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAF_PILE.defaultBlockState().setValue(AetherLeafPileBlock.PERSISTENT, true)), 3))).build());
        register(context, SHORT_SKYROOT, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAVES.defaultBlockState()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT)).build());
        register(context, SHORT_SKYROOT_WITH_FERNS, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAVES.defaultBlockState()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_FERN), 6))).build());
        register(context, LARGE_SKYROOT, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(7, 6, 0), BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAVES.defaultBlockState()),
                        new LargeSkyrootFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(3)), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.25F, 0.5F, 0.35F))).build());
        register(context, LARGE_SKYROOT_WITH_FERNS, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(7, 6, 0), BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAVES.defaultBlockState()),
                        new LargeSkyrootFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(3)), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.25F, 0.5F, 0.35F),
                                new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_FERN), 6)
                        )).build());
        register(context, NEST_SKYROOT, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(12, 3, 0), BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAVES.defaultBlockState()),
                        new NestSkyrootFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.75F, 0.6F, 0.7F))).build());
        register(context, NEST_SKYROOT_WITH_LEAF_PILES, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(12, 3, 0), BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAVES.defaultBlockState()),
                        new NestSkyrootFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_LEAF_PILE.defaultBlockState().setValue(AetherLeafPileBlock.PERSISTENT, true)), 3), new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.75F, 0.5F, 0.7F))).build());
        register(context, SKYPLANE, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(10, 4, 2), BlockStateProvider.of(AetherIIBlocks.SKYPLANE_LEAVES.defaultBlockState()),
                        new SkyplaneFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 0, OptionalInt.empty()), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.35F, 0.2F, 0.3F))).build());
        register(context, SKYPLANE_PATCH, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new MultiTreeTrunkPlacer(10, 4, 2, UniformInt.of(3, 6), 10), BlockStateProvider.of(AetherIIBlocks.SKYPLANE_LEAVES.defaultBlockState()),
                        new SkyplaneFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 0, OptionalInt.empty()), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.SKYPLANE_LEAF_PILE.defaultBlockState().setValue(AetherLeafPileBlock.PERSISTENT, true)), 3),
                                new ShroudedCanopyDecorator(
                                        BlockStateProvider.of(AetherIIBlocks.WOVEN_SKYROOT_STICKS.defaultBlockState()),
                                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_WOOD.defaultBlockState()),
                                        BlockStateProvider.of(AetherIIBlocks.BRYALINN_MOSS_CARPET.defaultBlockState()),
                                        BlockStateProvider.of(AetherIIBlocks.BRYALINN_MOSS_VINES.defaultBlockState()),
                                        UniformInt.of(2, 5),
                                        UniformInt.of(4, 7),
                                        UniformInt.of(2, 4),
                                        0.025
                                ))).build());
        register(context, SHORT_SKYPLANE, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(10, 3, 0), BlockStateProvider.of(AetherIIBlocks.SKYPLANE_LEAVES.defaultBlockState()),
                        new SkyplaneFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 0, OptionalInt.empty()), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.2F, 0.15F, 0.15F))).build());
        register(context, SHORT_SKYPLANE_WITH_FERNS, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(10, 3, 0), BlockStateProvider.of(AetherIIBlocks.SKYPLANE_LEAVES.defaultBlockState()),
                        new SkyplaneFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 0, OptionalInt.empty()), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.2F, 0.15F, 0.15F),
                                new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_FERN), 6)
                        )).build());
        register(context, WISPROOT, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.WISPROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(11, 4, 0), BlockStateProvider.of(AetherIIBlocks.WISPROOT_LEAVES.defaultBlockState()),
                        new WisprootFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(2, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT)).decorators(List.of(new WisprootTreeDecorator(BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG.defaultBlockState()), BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE.defaultBlockState())))).build());
        register(context, WISPROOT_WITH_FERNS, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.WISPROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(11, 4, 0), BlockStateProvider.of(AetherIIBlocks.WISPROOT_LEAVES.defaultBlockState()),
                        new WisprootFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(2, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new WisprootTreeDecorator(BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG.defaultBlockState()), BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE.defaultBlockState())),
                                new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_FERN), 6)
                        )).build());
        register(context, WISPROOT_WITH_LEAF_PILES, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.WISPROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(11, 4, 0), BlockStateProvider.of(AetherIIBlocks.WISPROOT_LEAVES.defaultBlockState()),
                        new WisprootFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(2, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT)).decorators(List.of(
                                new WisprootTreeDecorator(BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG.defaultBlockState()), BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE.defaultBlockState())),
                                new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.WISPROOT_LEAF_PILE.defaultBlockState().setValue(AetherLeafPileBlock.PERSISTENT, true)), 3))).build());
        register(context, GREATOAK, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(16, 2, 1), BlockStateProvider.of(AetherIIBlocks.GREATOAK_LEAVES.defaultBlockState()),
                        new GreatoakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 1, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT)).build());
        register(context, GREATOAK_WITH_LEAF_PILES, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(20, 15, 4), BlockStateProvider.of(AetherIIBlocks.GREATOAK_LEAVES.defaultBlockState()),
                        new GreatoakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 1, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.GREATOAK_LEAF_PILE.defaultBlockState().setValue(AetherLeafPileBlock.PERSISTENT, true)), 3))).build());
        register(context, SHORT_GREATOAK, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(12, 2, 0), BlockStateProvider.of(AetherIIBlocks.GREATOAK_LEAVES.defaultBlockState()),
                        new GreatoakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT)).build());
        register(context, SHORT_GREATOAK_WITH_FERNS, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(12, 2, 0), BlockStateProvider.of(AetherIIBlocks.GREATOAK_LEAVES.defaultBlockState()),
                        new GreatoakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_FERN), 6)))
                        .build());

        register(context, TREES_BIOME_FLOURISHING_FIELD, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYROOT_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.1F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(LARGE_SKYROOT_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.3F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SHORT_SKYPLANE_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYPLANE_SAPLING)), 0.01F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPROOT_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPROOT_SAPLING)), 0.2F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SHORT_GREATOAK_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATOAK_SAPLING)), 0.005F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_FIELDS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.1F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SHORT_SKYROOT_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING))));
        register(context, TREES_BIOME_VERDANT_WOODS, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(LARGE_SKYROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.6F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(NEST_SKYROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.015F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYPLANE), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYPLANE_SAPLING)), 0.01F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPROOT_SAPLING)), 0.1F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SHORT_GREATOAK), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATOAK_SAPLING)), 0.005F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_DENSE), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.05F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING))));
        register(context, TREES_BIOME_SHROUDED_FOREST, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYPLANE_PATCH), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.1F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.025F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYROOT_WITH_LEAF_PILES), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.015F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(NEST_SKYROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.0015F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(NEST_SKYROOT_WITH_LEAF_PILES), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.0005F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPROOT_SAPLING)), 0.015F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPROOT_WITH_LEAF_PILES), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPROOT_SAPLING)), 0.005F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATOAK), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATOAK_SAPLING)), 0.015F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATOAK_WITH_LEAF_PILES), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATOAK_SAPLING)), 0.005F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_SPARSE), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.01F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYPLANE), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYPLANE_SAPLING))));
        register(context, TREES_BIOME_SHIMMERING_BASIN, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYROOT_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.6F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(LARGE_SKYROOT_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.3F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SHORT_SKYPLANE_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYPLANE_SAPLING)), 0.05F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SHORT_GREATOAK_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATOAK_SAPLING)), 0.025F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_FIELDS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.1F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SHORT_SKYROOT_WITH_FERNS), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING))));

        // Magnetic
        register(context, SKYBIRCH, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(8, 3, 0), BlockStateProvider.of(AetherIIBlocks.SKYBIRCH_LEAVES.defaultBlockState()),
                        new SkybirchFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 0, OptionalInt.empty()), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.25F, 0.1F, 0.2F))).build());
        register(context, WISPTOP, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.WISPROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(13, 6, 0), BlockStateProvider.of(AetherIIBlocks.WISPTOP_LEAVES.defaultBlockState()),
                        new WisptopFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(2, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT)).decorators(List.of(new WisprootTreeDecorator(BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG.defaultBlockState()), BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE.defaultBlockState())))).build());
        register(context, WISPTOP_WITH_LEAF_PILES, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.WISPROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(13, 6, 0), BlockStateProvider.of(AetherIIBlocks.WISPTOP_LEAVES.defaultBlockState()),
                        new WisptopFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(2, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT)).decorators(List.of(
                                new WisprootTreeDecorator(BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG.defaultBlockState()), BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE.defaultBlockState())),
                                new GroundFeatureDecorator(BlockStateProvider.of(AetherIIBlocks.WISPTOP_LEAF_PILE.defaultBlockState().setValue(AetherLeafPileBlock.PERSISTENT, true)), 3))).build());
        register(context, GREATROOT, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(6, 2, 5), BlockStateProvider.of(AetherIIBlocks.GREATROOT_LEAVES.defaultBlockState()),
                        new GreatrootFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT)).build());
        register(context, SWAMP_GREATROOT, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(7, 2, 6), BlockStateProvider.of(AetherIIBlocks.GREATROOT_LEAVES.defaultBlockState()),
                        new GreatrootFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(new MossDecorator(AetherIIBlockStateProperties.Mossy.BRYALINN, BlockStateProvider.of(AetherIIBlocks.BRYALINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.BRYALINN_MOSS_VINES), Optional.of(new WeightedStateProvider(bryallinMossFlowers))))).belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT.defaultBlockState())).build());

        register(context, SMALL_MAGNETIC_SHROOM, new SmallMagneticShroomFeature(new BigMagneticShroomConfiguration(
                new NoiseThresholdProvider(
                        2345L,
                        NormalNoise.createParity(0, 1.0),
                        1.0F,
                        -0.15F,
                        1.0F,
                        AetherIIBlocks.MAGNETIC_SHROOM_BLOCK.defaultBlockState(),
                        List.of(AetherIIBlocks.MAGNETIC_SHROOM_BLOCK.defaultBlockState()),
                        List.of(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK.defaultBlockState())),
                new NoiseThresholdProvider(
                        2345L,
                        NormalNoise.createParity(0, 1.0),
                        1.0F,
                        -0.15F,
                        1.0F,
                        AetherIIBlocks.MAGNETIC_SHROOM_BLOCK.defaultBlockState()
                                .setValue(HugeMushroomBlock.DOWN, false),
                        List.of(AetherIIBlocks.MAGNETIC_SHROOM_BLOCK.defaultBlockState()
                                .setValue(HugeMushroomBlock.DOWN, false)),
                        List.of(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK.defaultBlockState()
                                .setValue(HugeMushroomBlock.DOWN, false))),
                BlockStateProvider.of(AetherIIBlocks.MAGNETIC_SHROOM_STEM),
                Optional.empty(),
                new TwoLayersFeatureSize(1, 0, 1),
                false
        )));
        register(context, MEDIUM_MAGNETIC_SHROOM, new HugeMagneticShroomFeature(new BigMagneticShroomConfiguration(
                new NoiseThresholdProvider(
                        2345L,
                        NormalNoise.createParity(0, 1.0),
                        1.0F,
                        -0.15F,
                        1.0F,
                        AetherIIBlocks.MAGNETIC_SHROOM_BLOCK.defaultBlockState(),
                        List.of(AetherIIBlocks.MAGNETIC_SHROOM_BLOCK.defaultBlockState()),
                        List.of(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK.defaultBlockState())),
                BlockStateProvider.of(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK.defaultBlockState()
                        .setValue(HugeMushroomBlock.NORTH, false)
                        .setValue(HugeMushroomBlock.EAST, false)
                        .setValue(HugeMushroomBlock.SOUTH, false)
                        .setValue(HugeMushroomBlock.WEST, false)
                        .setValue(HugeMushroomBlock.UP, false)
                        .setValue(HugeMushroomBlock.DOWN, false)),
                BlockStateProvider.of(AetherIIBlocks.MAGNETIC_SHROOM_STEM),
                Optional.empty(),
                new TwoLayersFeatureSize(1, 0, 1),
                false
        )));
        register(context, HUGE_MAGNETIC_SHROOM, new HugeMagneticShroomFeature(new BigMagneticShroomConfiguration(
                new NoiseThresholdProvider(
                        2345L,
                        NormalNoise.createParity(0, 1.0),
                        1.0F,
                        -0.15F,
                        1.0F,
                        AetherIIBlocks.MAGNETIC_SHROOM_BLOCK.defaultBlockState(),
                        List.of(AetherIIBlocks.MAGNETIC_SHROOM_BLOCK.defaultBlockState()),
                        List.of(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK.defaultBlockState())),
                BlockStateProvider.of(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK.defaultBlockState()
                        .setValue(HugeMushroomBlock.NORTH, false)
                        .setValue(HugeMushroomBlock.EAST, false)
                        .setValue(HugeMushroomBlock.SOUTH, false)
                        .setValue(HugeMushroomBlock.WEST, false)
                        .setValue(HugeMushroomBlock.UP, false)
                        .setValue(HugeMushroomBlock.DOWN, false)),
                BlockStateProvider.of(AetherIIBlocks.MAGNETIC_SHROOM_STEM),
                Optional.empty(),
                new TwoLayersFeatureSize(1, 0, 1),
                true
        )));

        register(context, TREES_BIOME_MAGNETIC_SCAR, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPTOP), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPROOT_SAPLING)), 0.35F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATROOT_SAPLING)), 0.01F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_SPARSE), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.025F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYBIRCH), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYBIRCH_SAPLING))));
        register(context, TREES_BIOME_TURQUOISE_FOREST, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPTOP), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPROOT_SAPLING)), 0.0075F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATROOT_SAPLING)), 0.05F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_DENSE), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.005F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYBIRCH), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYBIRCH_SAPLING))));
        register(context, TREES_BIOME_GLISTENING_SWAMP, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPTOP), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPROOT_SAPLING)), 0.3F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_SPARSE), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.01F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYBIRCH), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYBIRCH_SAPLING))));
        register(context, TREES_BIOME_VIOLET_HIGHWOODS, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPTOP_WITH_LEAF_PILES), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPTOP_SAPLING)), 0.25F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYBIRCH), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYBIRCH_SAPLING)), 0.005F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATROOT_SAPLING)), 0.002F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_SPARSE), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.0025F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPTOP), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPTOP_SAPLING))));
        register(context, MAGNETIC_SHROOMS_BIOME_GLISTENING_SWAMP, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(HUGE_MAGNETIC_SHROOM), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.TALL_AETHER_GRASS)), 0.15F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(MEDIUM_MAGNETIC_SHROOM), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.TALL_AETHER_GRASS)), 0.35F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SMALL_MAGNETIC_SHROOM), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.TALL_AETHER_GRASS))));

        // Arctic
        register(context, SKYPINE, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(6, 4, 1), BlockStateProvider.of(AetherIIBlocks.SKYPINE_LEAVES.defaultBlockState()),
                        new SkypineFoliagePlacer(UniformInt.of(3, 5), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(2, 0, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.2F, 0.2F, 0.15F))).build());
        register(context, SKYPINE_DECORATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(6, 4, 1), BlockStateProvider.of(AetherIIBlocks.SKYPINE_LEAVES.defaultBlockState()),
                        new SkypineFoliagePlacer(UniformInt.of(3, 5), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(2, 0, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new AlterGroundTagDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_GRASS_BLOCK), AetherIITags.Blocks.GRASS_SNOW_REPLACEABLE),
                                new SnowDecorator(),
                                new GroundFeatureDecorator(new WeightedStateProvider(twigs), 50),
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.2F, 0.2F, 0.15F)))
                        .build());
        register(context, GREATBOA, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(15, 3, 8), BlockStateProvider.of(AetherIIBlocks.GREATBOA_LEAVES.defaultBlockState()),
                        new GreatboaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 2, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .build());
        register(context, GREATBOA_DECORATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(15, 3, 8), BlockStateProvider.of(AetherIIBlocks.GREATBOA_LEAVES.defaultBlockState()),
                        new GreatboaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 2, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(List.of(
                                new AlterGroundTagDecorator(BlockStateProvider.of(AetherIIBlocks.AETHER_GRASS_BLOCK), AetherIITags.Blocks.GRASS_SNOW_REPLACEABLE),
                                new SnowDecorator(),
                                new GroundFeatureDecorator(new WeightedStateProvider(twigs), 50)))
                        .build());

        register(context, TREES_BIOME_FRIGID_SIERRA, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYPINE_DECORATED), BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(BlockPos.ZERO.below(), AetherIITags.Blocks.SUPPORTS_ARCTIC_TREE))), 0.1F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_SNOWY), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.0025F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATBOA_DECORATED), BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(BlockPos.ZERO.below(), AetherIITags.Blocks.SUPPORTS_ARCTIC_TREE)))));
        register(context, TREES_BIOME_ENDURING_WOODLANDS, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATBOA_DECORATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATBOA_SAPLING)), 0.03F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_SNOWY), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.00375F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYPINE_DECORATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYPINE_SAPLING))));
        register(context, TREES_BIOME_FROZEN_LAKES, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATBOA_DECORATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATBOA_SAPLING)), 0.35F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TREES_AMBEROOT_SNOWY), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.0075F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYPINE_DECORATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYPINE_SAPLING))));

        // Irradiated
        register(context, SKYROOT_IRRADIATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(4, 2, 0), BlockStateProvider.of(AetherIIBlocks.IRRADIATED_SKYROOT_LEAVES.defaultBlockState()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(
                                new IrradiationTreeDecorator(),
                                new MossDecorator(AetherIIBlockStateProperties.Mossy.AMBRELINN, BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES), Optional.of(new WeightedStateProvider(tarahespFlowers)))))
                        .build());
        register(context, LARGE_SKYROOT_IRRADIATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(7, 6, 0), BlockStateProvider.of(AetherIIBlocks.IRRADIATED_SKYROOT_LEAVES.defaultBlockState()),
                        new LargeSkyrootFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(3)), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(
                                new IrradiationTreeDecorator(),
                                new MossDecorator(AetherIIBlockStateProperties.Mossy.AMBRELINN, BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES), Optional.of(new WeightedStateProvider(tarahespFlowers))),
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.25F, 0.5F, 0.35F)))
                        .build());
        register(context, SKYPLANE_IRRADIATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(10, 4, 0), BlockStateProvider.of(AetherIIBlocks.IRRADIATED_SKYPLANE_LEAVES.defaultBlockState()),
                        new SkyplaneFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 0, OptionalInt.empty()), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(
                                new IrradiationTreeDecorator(),
                                new MossDecorator(AetherIIBlockStateProperties.Mossy.AMBRELINN, BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES), Optional.of(new WeightedStateProvider(tarahespFlowers))),
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.35F, 0.2F, 0.3F)))
                        .build());
        register(context, SKYBIRCH_IRRADIATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(8, 3, 0), BlockStateProvider.of(AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAVES.defaultBlockState()),
                        new SkybirchFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 0, OptionalInt.empty()), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(
                                new IrradiationTreeDecorator(),
                                new MossDecorator(AetherIIBlockStateProperties.Mossy.AMBRELINN, BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES), Optional.of(new WeightedStateProvider(tarahespFlowers))),
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.25F, 0.1F, 0.2F)))
                        .build());
        register(context, SKYPINE_IRRADIATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.SKYROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(6, 4, 1), BlockStateProvider.of(AetherIIBlocks.IRRADIATED_SKYPINE_LEAVES.defaultBlockState()),
                        new SkypineFoliagePlacer(UniformInt.of(3, 5), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(2, 0, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(
                                new IrradiationTreeDecorator(),
                                new MossDecorator(AetherIIBlockStateProperties.Mossy.AMBRELINN, BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES), Optional.of(new WeightedStateProvider(tarahespFlowers))),
                                new SimpleTrunkTreeDecorator(BlockStateProvider.of(AetherIIBlocks.SKYROOT_TRUNK.defaultBlockState()), 0.2F, 0.2F, 0.15F)))
                        .build());
        register(context, WISPROOT_IRRADIATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.WISPROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(11, 4, 0), BlockStateProvider.of(AetherIIBlocks.IRRADIATED_WISPROOT_LEAVES.defaultBlockState()),
                        new WisprootFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(2, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(
                                new WisprootTreeDecorator(BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG.defaultBlockState()), BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE.defaultBlockState())),
                                new IrradiationTreeDecorator(),
                                new MossDecorator(AetherIIBlockStateProperties.Mossy.AMBRELINN, BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES), Optional.of(new WeightedStateProvider(tarahespFlowers)))))
                        .build());
        register(context, WISPTOP_IRRADIATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.WISPROOT_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(13, 6, 0), BlockStateProvider.of(AetherIIBlocks.IRRADIATED_WISPTOP_LEAVES.defaultBlockState()),
                        new WisptopFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(2, 0, 1), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(
                                new WisprootTreeDecorator(BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG.defaultBlockState()), BlockStateProvider.of(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE.defaultBlockState())),
                                new IrradiationTreeDecorator(),
                                new MossDecorator(AetherIIBlockStateProperties.Mossy.AMBRELINN, BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES), Optional.of(new WeightedStateProvider(tarahespFlowers)))))
                        .build());
        register(context, GREATROOT_IRRADIATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(6, 2, 5), BlockStateProvider.of(AetherIIBlocks.IRRADIATED_GREATROOT_LEAVES.defaultBlockState()),
                        new GreatrootFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(
                                new IrradiationTreeDecorator(),
                                new MossDecorator(AetherIIBlockStateProperties.Mossy.AMBRELINN, BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES), Optional.of(new WeightedStateProvider(tarahespFlowers)))))
                        .build());
        register(context, GREATOAK_IRRADIATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(12, 2, 0), BlockStateProvider.of(AetherIIBlocks.IRRADIATED_GREATOAK_LEAVES.defaultBlockState()),
                        new GreatoakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(
                                new IrradiationTreeDecorator(),
                                new MossDecorator(AetherIIBlockStateProperties.Mossy.AMBRELINN, BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES), Optional.of(new WeightedStateProvider(tarahespFlowers)))))
                        .build());
        register(context, GREATBOA_IRRADIATED, new TreeFeature.Builder(
                        BlockStateProvider.of(AetherIIBlocks.GREATROOT_LOG.defaultBlockState()),
                        new GiantTrunkPlacer(15, 3, 8), BlockStateProvider.of(AetherIIBlocks.IRRADIATED_GREATBOA_LEAVES.defaultBlockState()),
                        new GreatoakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 2, 2), BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .ignoreVines().belowTrunkProvider(BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT))
                        .decorators(ImmutableList.of(
                                new IrradiationTreeDecorator(),
                                new MossDecorator(AetherIIBlockStateProperties.Mossy.AMBRELINN, BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET), BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES), Optional.of(new WeightedStateProvider(tarahespFlowers)))))
                        .build());

        register(context, TREES_IRRADIATED, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(LARGE_SKYROOT_IRRADIATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING)), 0.4F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPROOT_IRRADIATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPROOT_SAPLING)), 0.025F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYPLANE_IRRADIATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYPLANE_SAPLING)), 0.05F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATOAK_IRRADIATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATOAK_SAPLING)), 0.002F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYBIRCH_IRRADIATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYBIRCH_SAPLING)), 0.075F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(WISPTOP_IRRADIATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.WISPTOP_SAPLING)), 0.005F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATROOT_IRRADIATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATROOT_SAPLING)), 0.0075F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYPINE_IRRADIATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYPINE_SAPLING)), 0.125F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GREATBOA_IRRADIATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.GREATBOA_SAPLING)), 0.001F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AMBEROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.025F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SINGULAR_AMBEROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.025F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(LARGE_AMBEROOT), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.AMBEROOT_SAPLING)), 0.065F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYROOT_IRRADIATED), PlacementUtils.filteredByBlockSurvival(AetherIIBlocks.SKYROOT_SAPLING))));

        register(context, HUGE_MAGNETIC_SHROOM_GROWN, new HugeMagneticShroomFeature(new BigMagneticShroomConfiguration(
                new NoiseThresholdProvider(
                        2345L,
                        NormalNoise.createParity(0, 1.0),
                        1.0F,
                        -0.15F,
                        1.0F,
                        AetherIIBlocks.MAGNETIC_SHROOM_BLOCK.defaultBlockState(),
                        List.of(AetherIIBlocks.MAGNETIC_SHROOM_BLOCK.defaultBlockState()),
                        List.of(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK.defaultBlockState())),
                BlockStateProvider.of(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK.defaultBlockState()
                        .setValue(HugeMushroomBlock.NORTH, false)
                        .setValue(HugeMushroomBlock.EAST, false)
                        .setValue(HugeMushroomBlock.SOUTH, false)
                        .setValue(HugeMushroomBlock.WEST, false)
                        .setValue(HugeMushroomBlock.UP, false)
                        .setValue(HugeMushroomBlock.DOWN, false)),
                BlockStateProvider.of(AetherIIBlocks.MAGNETIC_SHROOM_STEM),
                Optional.of(new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(), 10).add(AetherIIBlocks.MYCELIAL_AETHER_DIRT.defaultBlockState(), 15).build())),
                new TwoLayersFeatureSize(1, 0, 1),
                false
        )));
    }

    private static void bootstrapUnderground(BootstrapContext<Feature> context) {
        HolderGetter<Feature> configuredFeatures = context.lookup(Registries.FEATURE);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        List<BlockReplacement> quartz = List.of(
                BlockReplacement.replace(HOLYSTONE_TEST, AetherIIBlocks.HOLYSTONE_QUARTZ_ORE.defaultBlockState()));
        List<BlockReplacement> ambrosium = List.of(
                BlockReplacement.replace(HOLYSTONE_TEST, AetherIIBlocks.AMBROSIUM_ORE.defaultBlockState()),
                BlockReplacement.replace(UNDERSHALE_TEST, AetherIIBlocks.UNDERSHALE_AMBROSIUM_ORE.defaultBlockState()));
        List<BlockReplacement> zanite = List.of(
                BlockReplacement.replace(HOLYSTONE_TEST, AetherIIBlocks.ZANITE_ORE.defaultBlockState()),
                BlockReplacement.replace(UNDERSHALE_TEST, AetherIIBlocks.UNDERSHALE_ZANITE_ORE.defaultBlockState()));
        List<BlockReplacement> glint = List.of(
                BlockReplacement.replace(HOLYSTONE_TEST, AetherIIBlocks.GLINT_ORE.defaultBlockState()),
                BlockReplacement.replace(UNDERSHALE_TEST, AetherIIBlocks.UNDERSHALE_GLINT_ORE.defaultBlockState()));
        List<BlockReplacement> arkenium = List.of(
                BlockReplacement.replace(HOLYSTONE_TEST, AetherIIBlocks.ARKENIUM_ORE.defaultBlockState()),
                BlockReplacement.replace(UNDERSHALE_TEST, AetherIIBlocks.UNDERSHALE_ARKENIUM_ORE.defaultBlockState()));
        List<BlockReplacement> gravitite = List.of(
                BlockReplacement.replace(HOLYSTONE_TEST, AetherIIBlocks.GRAVITITE_ORE.defaultBlockState()),
                BlockReplacement.replace(UNDERSHALE_TEST, AetherIIBlocks.UNDERSHALE_GRAVITITE_ORE.defaultBlockState()));

        WeightedList.Builder<BlockState> bryalinnFlowers = WeightedList.builder();
        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                bryalinnFlowers.add(AetherIIBlocks.BRYALINN_MOSS_FLOWERS.defaultBlockState().setValue(MossFlowersBlock.AMOUNT, i).setValue(MossFlowersBlock.FACING, direction), 1);
            }
        }

        register(context, SKY_ROOTS, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.SKY_ROOTS.defaultBlockState())));
        register(context, FROSTED_SKY_ROOTS, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.SKY_ROOTS.defaultBlockState().setValue(AetherHangingRootsBlock.SNOWY, true))));
        register(context, ICE, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.ARCTIC_PACKED_ICE.defaultBlockState())));
        register(context, ICE_CRYSTALS, (new SimpleBlockFeature(
                        new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(AetherIIBlocks.SMALL_ARCTIC_ICE_CRYSTAL.defaultBlockState().setValue(IceCrystalBlock.FACING, Direction.DOWN), 1)
                        .add(AetherIIBlocks.MEDIUM_ARCTIC_ICE_CRYSTAL.defaultBlockState().setValue(IceCrystalBlock.FACING, Direction.DOWN), 1)
                        .add(AetherIIBlocks.LARGE_ARCTIC_ICE_CRYSTAL.defaultBlockState().setValue(IceCrystalBlock.FACING, Direction.DOWN), 1)
                        .build())
                )
        ));

        register(context, POINTED_HOLYSTONE, new SimpleRandomSelectorFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(
                                new PointedStoneFeature(new PointedStoneConfiguration(BlockStateProvider.of(AetherIIBlocks.HOLYSTONE), BlockStateProvider.of(AetherIIBlocks.POINTED_HOLYSTONE), 0.2F, 0.7F, 0.5F, 0.5F)),
                                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                                OffsetPlacement.vertical(ConstantInt.of(1))
                        ),
                        PlacementUtils.inlinePlaced(
                                new PointedStoneFeature(new PointedStoneConfiguration(BlockStateProvider.of(AetherIIBlocks.HOLYSTONE), BlockStateProvider.of(AetherIIBlocks.POINTED_HOLYSTONE), 0.2F, 0.7F, 0.5F, 0.5F)),
                                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                                OffsetPlacement.vertical(ConstantInt.of(-1))
                        ))));
        register(context, POINTED_ICHORITE, new SimpleRandomSelectorFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(
                                new PointedStoneFeature(new PointedStoneConfiguration(BlockStateProvider.of(AetherIIBlocks.ICHORITE), BlockStateProvider.of(AetherIIBlocks.POINTED_ICHORITE), 0.2F, 0.7F, 0.5F, 0.5F)),
                                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                                OffsetPlacement.vertical(ConstantInt.of(1))
                        ),
                        PlacementUtils.inlinePlaced(
                                new PointedStoneFeature(new PointedStoneConfiguration(BlockStateProvider.of(AetherIIBlocks.ICHORITE), BlockStateProvider.of(AetherIIBlocks.POINTED_ICHORITE), 0.2F, 0.7F, 0.5F, 0.5F)),
                                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                                OffsetPlacement.vertical(ConstantInt.of(-1))
                        ))));
        register(context, GRASS_BLOCKS, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.AETHER_GRASS_BLOCK.defaultBlockState())));
        register(context, ENCHANTED_GRASS_BLOCKS, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.ENCHANTED_AETHER_GRASS_BLOCK.defaultBlockState())));
        register(
                context,
                GRASS_AND_DIRT_FLOOR,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.GRASS_AND_DIRT_REPLACEABLE),
                        BlockStateProvider.holderOf(AetherIIBlocks.COARSE_AETHER_DIRT),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                                List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GRASS_BLOCKS),
                                        CountPlacement.of(20),
                                        OffsetPlacement.ofTriangle(4, 4),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(AetherIITags.Blocks.GRASS_AND_DIRT_REPLACEABLE), BlockPredicate.matchesBlocks(Vec3i.ZERO.above(), List.of(Blocks.AIR))))
                                ), 0.25F)),
                                placedFeatures.getOrThrow(HolyIslesPlacedFeatures.VEGETATION_GRASS_PATCH))),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0F,
                        3,
                        0.65F,
                        UniformInt.of(2, 5),
                        0.75F
                )
        );
        register(
                context,
                ENCHANTED_GRASS_AND_DIRT_FLOOR,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.GRASS_AND_DIRT_REPLACEABLE),
                        BlockStateProvider.holderOf(AetherIIBlocks.COARSE_AETHER_DIRT),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                                List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ENCHANTED_GRASS_BLOCKS),
                                        CountPlacement.of(20),
                                        OffsetPlacement.ofTriangle(4, 4),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(AetherIITags.Blocks.GRASS_AND_DIRT_REPLACEABLE), BlockPredicate.matchesBlocks(Vec3i.ZERO.above(), List.of(Blocks.AIR))))
                                ), 0.25F)),
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(IRRADIATED_GRASS)))),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0F,
                        3,
                        0.9F,
                        UniformInt.of(2, 5),
                        0.75F
                )
        );
        register(
                context,
                SMALL_MYCELIUM_FLOOR,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.COARSE_AETHER_DIRT_REPLACEABLE),
                        Holder.direct(new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(), 10).add(AetherIIBlocks.MYCELIAL_AETHER_DIRT.defaultBlockState(), 15).build())),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                                List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SMALL_MAGNETIC_SHROOM)), 0.2F)),
                                placedFeatures.getOrThrow(HolyIslesPlacedFeatures.MYCELIAL_MAGNETIC_SHROOM_PATCH))),
                        CaveSurface.FLOOR,
                        UniformInt.of(1, 3),
                        0.25F,
                        3,
                        0.25F,
                        UniformInt.of(2, 5),
                        0.75F
                )
        );
        register(
                context,
                BIG_MYCELIUM_FLOOR,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.MYCELIAL_AETHER_DIRT_REPLACEABLE),
                        Holder.direct(new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(), 10).add(AetherIIBlocks.MYCELIAL_AETHER_DIRT.defaultBlockState(), 15).build())),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(List.of(
                                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SMALL_MAGNETIC_SHROOM)), 0.2F),
                                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(MEDIUM_MAGNETIC_SHROOM)), 0.15F),
                                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(HUGE_MAGNETIC_SHROOM)), 0.1F)),
                                placedFeatures.getOrThrow(HolyIslesPlacedFeatures.MYCELIAL_MAGNETIC_SHROOM_PATCH))),
                        CaveSurface.FLOOR,
                        UniformInt.of(1, 3),
                        0.25F,
                        3,
                        0.1F,
                        UniformInt.of(2, 5),
                        0.75F
                )
        );
        register(
                context,
                COARSE_AETHER_DIRT_FLOOR,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.COARSE_AETHER_DIRT_REPLACEABLE),
                        BlockStateProvider.holderOf(AetherIIBlocks.COARSE_AETHER_DIRT),
                        placedFeatures.getOrThrow(HolyIslesPlacedFeatures.HOLYSTONE_ROCKS_FLOOR),
                        CaveSurface.FLOOR,
                        UniformInt.of(1, 2),
                        0.1F,
                        3,
                        0.035F,
                        UniformInt.of(1, 4),
                        0.75F
                )
        );
        register(
                context,
                COARSE_AETHER_DIRT_CEILING,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.COARSE_AETHER_DIRT_REPLACEABLE),
                        BlockStateProvider.holderOf(AetherIIBlocks.COARSE_AETHER_DIRT),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKY_ROOTS),
                                CountPlacement.of(20),
                                OffsetPlacement.ofTriangle(4, 4),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(Vec3i.ZERO.above(), AetherIITags.Blocks.SUPPORTS_SKY_ROOTS), BlockPredicate.ONLY_IN_AIR_PREDICATE))
                        ),
                        CaveSurface.CEILING,
                        UniformInt.of(1, 2),
                        0.1F,
                        3,
                        0.125F,
                        UniformInt.of(1, 4),
                        0.75F
                )
        );
        register(
                context,
                COARSE_AETHER_DIRT_FROSTED_CEILING,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.COARSE_AETHER_DIRT_REPLACEABLE),
                        BlockStateProvider.holderOf(AetherIIBlocks.COARSE_AETHER_DIRT),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(FROSTED_SKY_ROOTS),
                                CountPlacement.of(20),
                                OffsetPlacement.ofTriangle(4, 4),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(Vec3i.ZERO.above(), AetherIITags.Blocks.SUPPORTS_SKY_ROOTS), BlockPredicate.ONLY_IN_AIR_PREDICATE))
                        ),
                        CaveSurface.CEILING,
                        UniformInt.of(1, 2),
                        0.1F,
                        3,
                        0.125F,
                        UniformInt.of(1, 4),
                        0.75F
                )
        );
        register(
                context,
                ICE_CEILING,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.ARCTIC_ICE_REPLACEABLE),
                        BlockStateProvider.holderOf(AetherIIBlocks.ARCTIC_PACKED_ICE),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ICE_CRYSTALS),
                                CountPlacement.of(20),
                                OffsetPlacement.ofTriangle(4, 4),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(Vec3i.ZERO.above(), AetherIITags.Blocks.SUPPORTS_ICE_CRYSTAL), BlockPredicate.ONLY_IN_AIR_PREDICATE))
                        ),
                        CaveSurface.CEILING,
                        UniformInt.of(1, 2),
                        0.35F,
                        3,
                        0.35F,
                        UniformInt.of(1, 3),
                        0.75F
                )
        );
        register(context, BRYALINN_MOSS_CARPET, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.BRYALINN_MOSS_CARPET.defaultBlockState())));
        register(context, BRYALINN_MOSS_FLOWERS, new SimpleBlockFeature(new WeightedStateProvider(bryalinnFlowers)));
        register(context,
                BRYALINN_MOSS_VINES,
                new MossVinesFeature(new MossVinesConfiguration(BlockStateProvider.of(AetherIIBlocks.BRYALINN_MOSS_VINES)))
        );
        register(
                context,
                BRYALINN_MOSS_FLOOR,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.AETHER_UNDERGROUND_BLOCKS),
                        BlockStateProvider.holderOf(AetherIIBlocks.BRYALINN_MOSS_BLOCK),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                                List.of(
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.BRYALINN_MOSS_CARPET_PATCH), 0.2F),
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.BRYALINN_MOSS_FLOWER_PATCH), 0.3F),
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.VEGETATION_GRASS_PATCH), 0.1F)
                                ),
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(BRYALINN_MOSS_VINES), CountPlacement.of(16), OffsetPlacement.of(UniformInt.of(-1, 1), UniformInt.of(-1, 1))))),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.35F,
                        5,
                        0.925F,
                        UniformInt.of(1, 4),
                        0.5F
                )
        );
        register(
                context,
                BRYALINN_MOSS_FLOOR_SWAMP,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.AETHER_GROUND_BLOCKS),
                        BlockStateProvider.holderOf(AetherIIBlocks.BRYALINN_MOSS_BLOCK),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                                List.of(
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.BRYALINN_MOSS_CARPET_PATCH), 0.2F),
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.BRYALINN_MOSS_FLOWER_PATCH), 0.3F),
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.VEGETATION_GRASS_PATCH), 0.1F)
                                ),
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(BRYALINN_MOSS_VINES), CountPlacement.of(16), OffsetPlacement.of(UniformInt.of(-1, 1), UniformInt.of(-1, 1))))),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.35F,
                        3,
                        0.925F,
                        UniformInt.of(3, 5),
                        0.65F
                )
        );
        register(context, SHAYELINN_MOSS_CARPET, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.SHAYELINN_MOSS_CARPET.defaultBlockState())));
        register(context,
                SHAYELINN_MOSS_VINES,
                new MossVinesFeature(new MossVinesConfiguration(BlockStateProvider.of(AetherIIBlocks.SHAYELINN_MOSS_VINES)))
        );
        register(
                context,
                SHAYELINN_MOSS_FLOOR,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.AETHER_GROUND_BLOCKS),
                        BlockStateProvider.holderOf(AetherIIBlocks.SHAYELINN_MOSS_BLOCK),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                                List.of(
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.SHAYELINN_MOSS_CARPET_PATCH), 0.4F),
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.VEGETATION_GRASS_PATCH), 0.2F)
                                ),
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SHAYELINN_MOSS_VINES), CountPlacement.of(16), OffsetPlacement.of(UniformInt.of(-1, 1), UniformInt.of(-1, 1))))),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.35F,
                        5,
                        0.925F,
                        UniformInt.of(1, 4),
                        0.5F
                )
        );
        register(context, AMBRELINN_MOSS_CARPET, new SimpleBlockFeature(BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_CARPET.defaultBlockState())));
        register(context,
                AMBRELINN_MOSS_VINES,
                new MossVinesFeature(new MossVinesConfiguration(BlockStateProvider.of(AetherIIBlocks.AMBRELINN_MOSS_VINES)))
        );
        register(
                context,
                AMBRELINN_MOSS_FLOOR,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.AETHER_GROUND_BLOCKS),
                        BlockStateProvider.holderOf(AetherIIBlocks.AMBRELINN_MOSS_BLOCK),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                                List.of(
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.AMBRELINN_MOSS_CARPET_PATCH), 0.4F),
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.VEGETATION_GRASS_PATCH), 0.2F)
                                ),
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AMBRELINN_MOSS_VINES), CountPlacement.of(16), OffsetPlacement.of(UniformInt.of(-1, 1), UniformInt.of(-1, 1))))),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.35F,
                        5,
                        0.925F,
                        UniformInt.of(1, 4),
                        0.5F
                )
        );

        register(
                context,
                UNSTABLE_HOLYSTONE,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.HOLYSTONE),
                        BlockStateProvider.holderOf(AetherIIBlocks.UNSTABLE_HOLYSTONE),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(HOLYSTONE_ROCKS)),
                        CaveSurface.FLOOR,
                        UniformInt.of(6, 9),
                        0.5F,
                        4,
                        0.15F,
                        UniformInt.of(3, 6),
                        0.5F
                )
        );
        register(
                context,
                UNSTABLE_UNDERSHALE,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.UNDERSHALE),
                        BlockStateProvider.holderOf(AetherIIBlocks.UNSTABLE_UNDERSHALE),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(HOLYSTONE_ROCKS)),
                        CaveSurface.FLOOR,
                        UniformInt.of(6, 9),
                        0.5F,
                        4,
                        0.15F,
                        UniformInt.of(3, 6),
                        0.5F
                )
        );

        register(context, ALKAHEST_POOL, new AlkahestPoolFeature(new AlkahestPoolConfiguration(UniformInt.of(1, 4), UniformInt.of(4, 6), UniformInt.of(-4, 2))));

        register(context, ORE_SCATTERGLASS, new OreFeature(UNDERGROUND_TEST, AetherIIBlocks.CRUDE_SCATTERGLASS.defaultBlockState(), 24));
        register(context, ORE_ICESTONE, new OreFeature(HOLYSTONE_TEST, AetherIIBlocks.ICESTONE.defaultBlockState(), 32));
        register(context, ORE_ICESTONE_SMALL, new OreFeature(HOLYSTONE_TEST, AetherIIBlocks.ICESTONE.defaultBlockState(), 16));
        register(context, ORE_AGIOSITE, new OreFeature(UNDERSHALE_TEST, AetherIIBlocks.AGIOSITE.defaultBlockState(), 64));
        register(context, ORE_AGIOSITE_SMALL, new OreFeature(UNDERSHALE_TEST, AetherIIBlocks.AGIOSITE.defaultBlockState(), 32));

        register(context, ORE_HOLYSTONE_QUARTZ, new OreFeature(quartz, 15));
        register(context, ORE_AMBROSIUM, new OreFeature(ambrosium, 16));
        register(context, ORE_ZANITE, new OreFeature(zanite, 6, 0.15F));
        register(context, ORE_ZANITE_MOUNTAIN, new OreFeature(zanite, 4));
        register(context, ORE_GLINT, new OreFeature(glint, 4));
        register(context, ORE_ARKENIUM, new OreFeature(arkenium, 6, 0.25F));
        register(context, ORE_GRAVITITE_BURIED, new OreFeature(gravitite, 5, 0.5F));
        register(context, ORE_GRAVITITE, new OreFeature(gravitite, 5));
        register(context, ORE_CORROBONITE, new CorroboniteOreFeature(List.of(BlockReplacement.replace(UNDERSHALE_TEST, AetherIIBlocks.CORROBONITE_ORE.defaultBlockState())), 5, 0.0F));

        register(context, ORE_HESTVEIL_OPEN, new HestveilFeature());
        register(context, ORE_HESTVEIL_BURIED, new OreFeature(List.of(BlockReplacement.replace(UNDERGROUND_TEST, AetherIIBlocks.HESTVEIL.defaultBlockState())), 16, 1.0F));
    }

    @SuppressWarnings("deprecation")
    private static void bootstrapWorldgen(BootstrapContext<Feature> context) {
        HolderGetter<Feature> configuredFeatures = context.lookup(Registries.FEATURE);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<DensityFunction> function = context.lookup(Registries.DENSITY_FUNCTION);

        register(
                context,
                COARSE_AETHER_DIRT_SURFACE,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.SUPPORTS_AETHER_PLANT),
                        BlockStateProvider.holderOf(AetherIIBlocks.COARSE_AETHER_DIRT),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(HOLYSTONE_ROCKS)),
                        CaveSurface.FLOOR,
                        UniformInt.of(1, 2),
                        0.1F,
                        2,
                        0.0F,
                        UniformInt.of(1, 4),
                        0.75F
                )
        );
        register(context, DISK_BRYALINN_MOSS, new DiskFeature(
                BlockStateProvider.holderOf(AetherIIBlocks.BRYALINN_MOSS_BLOCK), BlockPredicate.matchesTag(AetherIITags.Blocks.BRYALINN_MOSS_REPLACEABLE), UniformInt.of(1, 2), 1
        ));
        register(context, DISK_SHAYELINN_MOSS, new DiskFeature(
                BlockStateProvider.holderOf(AetherIIBlocks.SHAYELINN_MOSS_BLOCK), BlockPredicate.matchesTag(AetherIITags.Blocks.SHAYELINN_MOSS_REPLACEABLE), UniformInt.of(1, 2), 1
        ));

        register(context, COAST_QUICKSOIL, new CoastFeature(new CoastConfiguration(
                BlockStateProvider.of(AetherIIBlocks.QUICKSOIL),
                16.35F,
                AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.COASTS_HIGHFIELDS),
                UniformInt.of(112, 156),
                Optional.of(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(HolyIslesConfiguredFeatures.BRETTL_PLANT),
                        OffsetPlacement.vertical(ConstantInt.of(1)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(Vec3i.ZERO.below(), AetherIITags.Blocks.SUPPORTS_BRETTL_PLANT), BlockPredicate.ONLY_IN_AIR_PREDICATE)))),
                0.01F,
                AetherIITags.Blocks.QUICKSOIL_COAST_GENERATES_ON
        )));
        register(context, COAST_FERROSITE_SAND, new CoastFeature(new CoastConfiguration(
                new NoiseProvider(
                        99L,
                        NormalNoise.createParity(-3, 1.0, 0.25, 0.0, 0.0),
                        1.0F,
                        List.of(
                                Blocks.AIR.defaultBlockState(),
                                AetherIIBlocks.FERROSITE_SAND.defaultBlockState(),
                                AetherIIBlocks.FERROSITE_SAND.defaultBlockState(),
                                Blocks.AIR.defaultBlockState(),
                                AetherIIBlocks.FERROSITE_SAND.defaultBlockState(),
                                AetherIIBlocks.FERROSITE_SAND.defaultBlockState(),
                                Blocks.AIR.defaultBlockState()
                        )
                ),
                16.35F,
                AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.COASTS_HIGHFIELDS),
                UniformInt.of(112, 156),
                Optional.empty(),
                0.0F,
                AetherIITags.Blocks.FERROSITE_COAST_GENERATES_ON
        )));
        register(context, COAST_FERROSITE_PILLAR, new CoastFeature(new CoastConfiguration(
                new NoiseProvider(
                        99L,
                        NormalNoise.createParity(-3, 1.0, 0.25, 0.0, 0.0),
                        1.0F,
                        List.of(
                                Blocks.AIR.defaultBlockState(),
                                AetherIIBlocks.FERROSITE_SAND.defaultBlockState(),
                                AetherIIBlocks.FERROSITE_SAND.defaultBlockState(),
                                Blocks.AIR.defaultBlockState(),
                                AetherIIBlocks.FERROSITE_SAND.defaultBlockState(),
                                AetherIIBlocks.FERROSITE_SAND.defaultBlockState(),
                                Blocks.AIR.defaultBlockState()
                        )
                ),
                16.35F,
                AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.COASTS_FERROSITE_PILLAR),
                UniformInt.of(112, 156),
                Optional.empty(),
                0.0F,
                AetherIITags.Blocks.FERROSITE_PILLAR_COAST_GENERATES_ON
        )));
        register(context, COAST_ARCTIC_PACKED_ICE, new CoastFeature(new CoastConfiguration(
                BlockStateProvider.of(AetherIIBlocks.ARCTIC_PACKED_ICE),
                16.35F,
                AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.COASTS_ARCTIC),
                UniformInt.of(120, 180),
                Optional.of(PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                        List.of(new WeightedPlacedFeature( PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ICE_CRYSTALS),
                                CountPlacement.of(20),
                                OffsetPlacement.ofTriangle(4, 4),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(Vec3i.ZERO.above(), AetherIITags.Blocks.SUPPORTS_ICE_CRYSTAL), BlockPredicate.ONLY_IN_AIR_PREDICATE))
                        ), 0.35F)),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ICE),
                                CountPlacement.of(20),
                                OffsetPlacement.ofTriangle(4, 4),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(Vec3i.ZERO.above(), List.of(AetherIIBlocks.ARCTIC_PACKED_ICE)), BlockPredicate.ONLY_IN_AIR_PREDICATE))
                        )))),
                0.25F,
                AetherIITags.Blocks.ARCTIC_COAST_GENERATES_ON
        )));

        register(context, WATER_POND, new AetherLakeFeature(new AetherLakeConfiguration(ConstantInt.of(2), BlockStateProvider.of(Blocks.WATER), new NoiseProvider(
                        2345L,
                        NormalNoise.createParity(0, 1.0),
                        0.25F,
                        List.of(
                                AetherIIBlocks.SHIMMERING_SILT.defaultBlockState(),
                                AetherIIBlocks.SHIMMERING_SILT.defaultBlockState(),
                                AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(),
                                AetherIIBlocks.SHIMMERING_SILT.defaultBlockState()
                        )
                ))));

        register(context, WATER_POND_TUNDRA, new AetherLakeFeature(new AetherLakeConfiguration(UniformInt.of(2, 5), BlockStateProvider.of(Blocks.WATER), BlockStateProvider.of(AetherIIBlocks.COARSE_AETHER_DIRT))));
        register(context, WATER_SPRING, new SpringFeature(Fluids.WATER.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, AetherIIBlocks.UNDERSHALE, AetherIIBlocks.HOLYSTONE, AetherIIBlocks.AETHER_DIRT)));

        register(context, NOISE_LAKE, new NoiseLakeFeature(new NoiseLakeConfiguration(
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_NOISE),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_FLOOR),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_BARRIER),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_WATERFALLS),
                        0.3,
                        ConstantInt.of(124),
                        new DualNoiseProvider(
                                new InclusiveRange<>(1, 4),
                                NormalNoise.createParity(-6, 1.0),
                                1.0F,
                                2345L,
                                NormalNoise.createParity(-2, 1.0),
                                1.0F,
                                List.of(
                                        AetherIIBlocks.SHIMMERING_SILT.defaultBlockState(),
                                        AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(),
                                        AetherIIBlocks.SHIMMERING_SILT.defaultBlockState(),
                                        AetherIIBlocks.AETHER_DIRT.defaultBlockState(),
                                        AetherIIBlocks.SHIMMERING_SILT.defaultBlockState()
                                )
                        ),
                        0.31,
                        BlockStateProvider.of(AetherIIBlocks.QUICKSOIL),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_SHORE),
                        BlockStateProvider.of(Blocks.AIR),
                        false
                )));
        register(context, NOISE_LAKE_ARCTIC, new NoiseLakeFeature(new NoiseLakeConfiguration(
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_NOISE),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_FLOOR),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_BARRIER),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_WATERFALLS),
                        0.3,
                        ConstantInt.of(124),
                        new DualNoiseProvider(
                                new InclusiveRange<>(1, 4),
                                NormalNoise.createParity(-6, 1.0),
                                1.0F,
                                2345L,
                                NormalNoise.createParity(-2, 1.0),
                                1.0F,
                                List.of(
                                        AetherIIBlocks.SHIMMERING_SILT.defaultBlockState(),
                                        AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(),
                                        AetherIIBlocks.HOLYSTONE.defaultBlockState(),
                                        AetherIIBlocks.AETHER_DIRT.defaultBlockState(),
                                        AetherIIBlocks.SHIMMERING_SILT.defaultBlockState()
                                )
                        ),
                        0.31,
                        new DualNoiseProvider(
                                new InclusiveRange<>(1, 4),
                                NormalNoise.createParity(-6, 1.0),
                                2.0F,
                                2345L,
                                NormalNoise.createParity(-2, 1.0),
                                2.0F,
                                List.of(
                                        AetherIIBlocks.ARCTIC_PACKED_ICE.defaultBlockState(),
                                        AetherIIBlocks.ARCTIC_PACKED_ICE.defaultBlockState(),
                                        AetherIIBlocks.ARCTIC_PACKED_ICE.defaultBlockState(),
                                        AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(),
                                        AetherIIBlocks.SHIMMERING_SILT.defaultBlockState(),
                                        AetherIIBlocks.SHIMMERING_SILT.defaultBlockState()
                                )
                        ),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_SHORE),
                        new NoiseProvider(
                                123L,
                                NormalNoise.createParity(-3, 1.25, 0.5, 0.0, 0.0, 0.0),
                                0.75F,
                                List.of(
                                        AetherIIBlocks.ARCTIC_ICE.defaultBlockState(),
                                        AetherIIBlocks.ARCTIC_ICE.defaultBlockState(),
                                        AetherIIBlocks.ARCTIC_ICE.defaultBlockState(),
                                        AetherIIBlocks.ARCTIC_ICE.defaultBlockState(),
                                        AetherIIBlocks.FRAGILE_ARCTIC_ICE.defaultBlockState(),
                                        AetherIIBlocks.FRAGILE_ARCTIC_ICE.defaultBlockState()
                                )
                        ),
                        true
                )));

        register(context, NOISE_LAKE_SWAMP, new NoiseLakeFeature(new NoiseLakeConfiguration(
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_NOISE_SWAMP),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_FLOOR),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_BARRIER),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_WATERFALLS),
                        0.3,
                        ConstantInt.of(124),
                        new DualNoiseProvider(
                                new InclusiveRange<>(1, 4),
                                NormalNoise.createParity(-6, 1.0),
                                1.0F,
                                2345L,
                                NormalNoise.createParity(-2, 1.0),
                                1.0F,
                                List.of(
                                        AetherIIBlocks.FERROSITE_MUD.defaultBlockState(),
                                        AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(),
                                        AetherIIBlocks.FERROSITE_MUD.defaultBlockState(),
                                        AetherIIBlocks.AETHER_DIRT.defaultBlockState(),
                                        AetherIIBlocks.FERROSITE_SAND.defaultBlockState()
                                )
                        ),
                        0.275,
                        new DualNoiseProvider(
                                new InclusiveRange<>(1, 4),
                                NormalNoise.createParity(-6, 1.0),
                                1.0F,
                                2345L,
                                NormalNoise.createParity(-2, 1.0),
                                1.0F,
                                List.of(
                                        AetherIIBlocks.FERROSITE_MUD.defaultBlockState(),
                                        AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(),
                                        AetherIIBlocks.FERROSITE_MUD.defaultBlockState(),
                                        AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(),
                                        AetherIIBlocks.BRYALINN_MOSS_BLOCK.defaultBlockState()
                                )
                        ),
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.LAKES_SHORE),
                        BlockStateProvider.of(Blocks.AIR),
                        false
                )));

        register(context, FERROSITE_PILLAR, new FerrositePillarFeature(new FerrositePillarConfiguration(
                new NoiseProvider(
                        300L,
                        NormalNoise.createParity(0, 1.0),
                        0.064F,
                        List.of(
                                AetherIIBlocks.FERROSITE.defaultBlockState(),
                                AetherIIBlocks.FERROSITE.defaultBlockState(),
                                AetherIIBlocks.RUSTED_FERROSITE.defaultBlockState()
                        )
                ),
                4.5F,
                6,
                40,
                24,
                AetherIITags.Blocks.FERROSITE_PILLAR_GENERATES_ON
        )));
        register(context, FERROSITE_PILLAR_TURF_TOP, new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.AETHER_GROUND_BLOCKS),
                        BlockStateProvider.holderOf(AetherIIBlocks.AETHER_GRASS_BLOCK),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AETHER_GRASS_BONEMEAL)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0F,
                        4,
                        0.0F,
                        UniformInt.of(24, 28),
                        0.3F
                )
        );
        register(context, FERROSITE_PILLAR_TURF, new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.FERROSITE),
                        BlockStateProvider.holderOf(AetherIIBlocks.AETHER_DIRT),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(FERROSITE_PILLAR_TURF_TOP)),
                        CaveSurface.FLOOR,
                        UniformInt.of(3, 4),
                        0.0F,
                        16,
                        1.0F,
                        UniformInt.of(24, 28),
                        0.3F
                )
        );

        register(context, FERROSITE_SPIKE, new FerrositeSpikeFeature(new FerrositeSpikeConfiguration(
                new NoiseProvider(
                        200L,
                        NormalNoise.createParity(0, 1.0),
                        0.12F,
                        List.of(
                                AetherIIBlocks.FERROSITE.defaultBlockState(),
                                AetherIIBlocks.FERROSITE.defaultBlockState(),
                                AetherIIBlocks.RUSTED_FERROSITE.defaultBlockState()
                        )
                ),
                2.5F,
                3,
                AetherIITags.Blocks.FERROSITE_SPIKE_GENERATES_ON
        )));
        register(context, ARCTIC_ICE_SPIKE, new ArcticIceSpikeFeature(new ArcticIceSpikeConfiguration(
                new NoiseProvider(
                        400L,
                        NormalNoise.createParity(0, 1.0),
                        0.1F,
                        List.of(
                                AetherIIBlocks.ARCTIC_PACKED_ICE.defaultBlockState(),
                                AetherIIBlocks.ARCTIC_ICE.defaultBlockState()
                        )
                ),
                4.25F,
                2,
                7.5F,
                5,
                AetherIITags.Blocks.ARCTIC_ICE_SPIKE_GENERATES_ON
        )));
        register(context, MEGA_ARCTIC_ICE_SPIKE, new ArcticIceSpikeFeature(new ArcticIceSpikeConfiguration(
                new NoiseProvider(
                        500L,
                        NormalNoise.createParity(0, 1.0),
                        0.1F,
                        List.of(
                                AetherIIBlocks.ARCTIC_PACKED_ICE.defaultBlockState(),
                                AetherIIBlocks.ARCTIC_ICE.defaultBlockState()
                        )
                ),
                6.25F,
                3,
                4.5F,
                2,
                AetherIITags.Blocks.ARCTIC_ICE_SPIKE_GENERATES_ON
        )));
        register(context, ARCTIC_ICE_SPIKE_VARIANTS, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(MEGA_ARCTIC_ICE_SPIKE)), 0.1F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ARCTIC_ICE_SPIKE))));

        register(context, FREEZE_TOP_LAYER_ARCTIC, new ArcticSnowAndFreezeFeature());
        register(context, FREEZE_TOP_LAYER_TUNDRA, new TundraSnowAndFreezeFeature());

        register(context, CRATER, new CraterFeature(new CraterConfiguration(
                UniformInt.of(4, 5),
                AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.ENVIRONMENTAL_CRATER),
                new WeightedStateProvider(new WeightedList.Builder<BlockState>().add(AetherIIBlocks.IRRADIATED_HOLYSTONE.defaultBlockState(), 1).add(AetherIIBlocks.COARSE_AETHER_DIRT.defaultBlockState(), 5).build()),
                BlockStateProvider.of(Blocks.WATER),
                BlockStateProvider.of(AetherIIBlocks.IRRADIATED_DUST_BLOCK)
        )));

        register(context, CLOUDBED, new CloudbedFeature(new CloudbedConfiguration(
                        new NoiseProvider(
                                2345L,
                                // 26.3 noise parameters reject negative octave amplitudes; the second octave used to be inverted (-0.25).
                                NormalNoise.createParity(-7, 1.25, 0.25, 1.0, 0.5, 1.25),
                                1.0F,
                                List.of(
                                        AetherIIBlocks.GREEN_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.COLD_AERCLOUD.defaultBlockState(),
                                        AetherIIBlocks.BLUE_AERCLOUD.defaultBlockState()
                                )
                        ),
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        96,
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.CLOUDBED_NOISE),
                        10D,
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.CLOUDBED_Y_OFFSET),
                        15D
                )));
    }

    private static void bootstrapAir(BootstrapContext<Feature> context) {
        WeightedList.Builder<BlockState> purpleAerclouds = new WeightedList.Builder<>();
        for (Direction direction : PurpleAercloudBlock.DIRECTIONS) {
            purpleAerclouds.add(AetherIIBlocks.PURPLE_AERCLOUD.defaultBlockState().setValue(PurpleAercloudBlock.FACING, direction), 1);
        }

        register(context, COLD_AERCLOUD, new AercloudFeature(new AercloudConfiguration(32, BlockStateProvider.of(AetherIIBlocks.COLD_AERCLOUD.defaultBlockState()))));
        register(context, GOLDEN_AERCLOUD, new AercloudFeature(new AercloudConfiguration(16, BlockStateProvider.of(AetherIIBlocks.GOLDEN_AERCLOUD.defaultBlockState()))));
        register(context, BLUE_AERCLOUD, new AercloudFeature(new AercloudConfiguration(24, BlockStateProvider.of(AetherIIBlocks.BLUE_AERCLOUD.defaultBlockState()))));
        register(context, GREEN_AERCLOUD, new AercloudFeature(new AercloudConfiguration(24, BlockStateProvider.of(AetherIIBlocks.GREEN_AERCLOUD.defaultBlockState()))));
        register(context, PURPLE_AERCLOUD, new AercloudFeature(new AercloudConfiguration(24, new WeightedStateProvider(purpleAerclouds))));
        register(context, PURPLE_AERCLOUD_SMALL, new AercloudFeature(new AercloudConfiguration(20, new WeightedStateProvider(purpleAerclouds))));
        register(context, STORM_AERCLOUD, new AercloudFeature(new AercloudConfiguration(28, BlockStateProvider.of(AetherIIBlocks.STORM_AERCLOUD.defaultBlockState()))));
    }

    private static void bootstrapDungeon(BootstrapContext<Feature> context) {
        HolderGetter<Feature> configuredFeatures = context.lookup(Registries.FEATURE);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<DensityFunction> function = context.lookup(Registries.DENSITY_FUNCTION);

        register(
                context,
                BRYALINN_MOSS_STRUCTURE,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.STRUCTURE_MOSS_REPLACEABLES),
                        BlockStateProvider.holderOf(AetherIIBlocks.BRYALINN_MOSS_BLOCK),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                                List.of(
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.BRYALINN_MOSS_CARPET_PATCH), 0.2F),
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.BRYALINN_MOSS_FLOWER_PATCH), 0.3F),
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.VEGETATION_GRASS_PATCH), 0.1F)
                                ),
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(BRYALINN_MOSS_VINES), CountPlacement.of(16), OffsetPlacement.of(UniformInt.of(-1, 1), UniformInt.of(-1, 1))))),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.35F,
                        5,
                        0.925F,
                        UniformInt.of(1, 4),
                        0.35F
                )
        );
        register(
                context,
                SHAYELINN_MOSS_STRUCTURE,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.STRUCTURE_MOSS_REPLACEABLES),
                        BlockStateProvider.holderOf(AetherIIBlocks.SHAYELINN_MOSS_BLOCK),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                                List.of(
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.SHAYELINN_MOSS_CARPET_PATCH), 0.4F),
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.VEGETATION_GRASS_PATCH), 0.2F)
                                ),
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SHAYELINN_MOSS_VINES), CountPlacement.of(16), OffsetPlacement.of(UniformInt.of(-1, 1), UniformInt.of(-1, 1))))),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.35F,
                        5,
                        0.925F,
                        UniformInt.of(1, 4),
                        0.35F
                )
        );
        register(
                context,
                AMBRELINN_MOSS_STRUCTURE,
                new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.STRUCTURE_MOSS_REPLACEABLES),
                        BlockStateProvider.holderOf(AetherIIBlocks.AMBRELINN_MOSS_BLOCK),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(
                                List.of(
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.AMBRELINN_MOSS_CARPET_PATCH), 0.4F),
                                        new WeightedPlacedFeature(placedFeatures.getOrThrow(HolyIslesPlacedFeatures.VEGETATION_GRASS_PATCH), 0.2F)
                                ),
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AMBRELINN_MOSS_VINES), CountPlacement.of(16), OffsetPlacement.of(UniformInt.of(-1, 1), UniformInt.of(-1, 1))))),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.35F,
                        5,
                        0.925F,
                        UniformInt.of(1, 4),
                        0.35F
                )
        );

        register(context, PILE_HOLYSTONE, new BlockPileFeature(BlockStateProvider.holderOf(AetherIIBlocks.HOLYSTONE)));
        register(context, PILE_UNDERSHALE, new BlockPileFeature(BlockStateProvider.holderOf(AetherIIBlocks.UNDERSHALE)));
        register(context, PILE_AGIOSITE, new BlockPileFeature(BlockStateProvider.holderOf(AetherIIBlocks.AGIOSITE)));
        register(context, PILE_AMBROSIUM_ORE, new BlockPileFeature(BlockStateProvider.holderOf(AetherIIBlocks.AMBROSIUM_ORE)));
        register(context, PILE_FERROSITE, new BlockPileFeature(BlockStateProvider.holderOf(AetherIIBlocks.FERROSITE)));
        register(context, PILE_ICESTONE, new BlockPileFeature(BlockStateProvider.holderOf(AetherIIBlocks.ICESTONE)));
        register(context, PILE_ARCTIC_PACKED_ICE, new BlockPileFeature(BlockStateProvider.holderOf(AetherIIBlocks.ARCTIC_PACKED_ICE)));

        register(context, PILES_MATERIAL_DEPOSIT, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PILE_UNDERSHALE)), 0.4F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PILE_AGIOSITE)), 0.2F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PILE_AMBROSIUM_ORE)), 0.1F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PILE_FERROSITE)), 0.05F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PILE_HOLYSTONE))));

        register(context, PILES_COLD_STORAGE, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PILE_ARCTIC_PACKED_ICE)), 0.25F)
        ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PILE_ICESTONE))));

        register(context, LARGE_SHELF_ROTSHROOM, new LargeShelfMushroom(new LargeShelfMushroomConfiguration(BlockStateProvider.of(AetherIIBlocks.SHELF_ROTSHROOM_SLAB), 1, 2, 96)));
        register(context, LARGE_SHELF_ROTSHROOM_UNDERGROUND, new LargeShelfMushroom(new LargeShelfMushroomConfiguration(BlockStateProvider.of(AetherIIBlocks.SHELF_ROTSHROOM_SLAB), 1, 2, 0)));
        register(context, ROTSHROOM_PATCH, (new SimpleBlockFeature(new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(AetherIIBlocks.ROTSHROOM_CLUSTER.defaultBlockState(), 3)
                        .add(AetherIIBlocks.ROTSHROOM_TOADSTOOL.defaultBlockState(), 1)
                        .build())
                )
        ));

        register(context, COARSE_AETHER_DIRT_DUNGEON, new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.AETHER_GROUND_BLOCKS),
                        BlockStateProvider.holderOf(AetherIIBlocks.COARSE_AETHER_DIRT),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ROTSHROOM_PATCH)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(2),
                        0.4F,
                        6,
                        0.65F,
                        UniformInt.of(2, 4),
                        0.375F));

        register(context, UNDERGROWTH_VINE, new BlockColumnFeature(
                        List.of(
                                BlockColumnFeature.layer(
                                        new WeightedListInt(
                                                WeightedList.<IntProvider>builder()
                                                        .add(UniformInt.of(1, 5), 1)
                                                        .add(UniformInt.of(0, 2), 3)
                                                        .build()
                                        ),
                                        BlockStateProvider.of(AetherIIBlocks.HANGING_UNDERGROWTH_PLANT)
                                ),
                                BlockColumnFeature.layer(ConstantInt.of(1), BlockStateProvider.of(AetherIIBlocks.HANGING_UNDERGROWTH))
                        ),
                        Direction.DOWN,
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        true));
        register(context, UNDERGROWTH_PATCH, new VegetationPatchFeature(
                        blocks.getOrThrow(AetherIITags.Blocks.UNDERGROWTH_PATCH_GENERATES_ON),
                        BlockStateProvider.holderOf(AetherIIBlocks.UNDERGROWTH_LEAVES.defaultBlockState()),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(UNDERGROWTH_VINE)),
                        CaveSurface.CEILING, ConstantInt.of(1),
                        0.6F,
                        2,
                        1.0F,
                        UniformInt.of(2, 3),
                        0.6F));

        register(context, INFECTED_GUARDIAN_TREE_ENTRANCE_COVER, new StructureCoverFeature(new StructureCoverConfiguration(
                        BlockStateProvider.of(AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        BlockStateProvider.of(AetherIIBlocks.UNDERSHALE.defaultBlockState()),
                        95,
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.DUNGEONS_STRUCTURE_COVER),
                        16.0F,
                        12,
                        0.0125F,
                        0.05F,
                        StructureCoverFeature.CalculationType.BOTTOM_TO_TOP
                )));
        register(context, INFECTED_GUARDIAN_TREE_STAIRCASE_COVER, new StructureCoverFeature(new StructureCoverConfiguration(
                        BlockStateProvider.of(AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        BlockStateProvider.of(AetherIIBlocks.UNDERSHALE.defaultBlockState()),
                        95,
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.DUNGEONS_STRUCTURE_COVER),
                        16.0F,
                        20,
                        0.0125F,
                        0.05F,
                        StructureCoverFeature.CalculationType.BOTTOM_TO_TOP
                )));
        register(context, INFECTED_GUARDIAN_TREE_LOBBY_COVER, new StructureCoverFeature(new StructureCoverConfiguration(
                        BlockStateProvider.of(AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        BlockStateProvider.of(AetherIIBlocks.UNDERSHALE.defaultBlockState()),
                        95,
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.DUNGEONS_STRUCTURE_COVER),
                        22.0F,
                        14,
                        0.0075F,
                        0.05F,
                        StructureCoverFeature.CalculationType.BOTTOM_TO_TOP
                )));
        register(context, INFECTED_GUARDIAN_TREE_BOSS_ROOM_COVER, new StructureCoverFeature(new StructureCoverConfiguration(
                        BlockStateProvider.of(AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        BlockStateProvider.of(AetherIIBlocks.UNDERSHALE.defaultBlockState()),
                        95,
                        AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.DUNGEONS_STRUCTURE_COVER),
                        24.0F,
                        28,
                        0.0075F,
                        0.05F,
                        StructureCoverFeature.CalculationType.BOTTOM_TO_TOP
                )));
    }

    private static ResourceKey<Feature> createKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
    }

    private static void register(BootstrapContext<Feature> context, ResourceKey<Feature> key, Feature feature) {
        context.register(key, feature);
    }
}