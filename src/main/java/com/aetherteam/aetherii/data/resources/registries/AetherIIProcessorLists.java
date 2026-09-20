package com.aetherteam.aetherii.data.resources.registries;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.ValkyrieSproutBlock;
import com.aetherteam.aetherii.world.structure.piece.sentry.SentryRuinsPiece;
import com.aetherteam.aetherii.world.structure.processor.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.List;

public class AetherIIProcessorLists {
    public static final ResourceKey<StructureProcessorList> CAMP = createKey("camp");
    public static final ResourceKey<StructureProcessorList> VERADEXIAN_RUINS_TEMPERATE = createKey("veradexian_ruins_temperate");
    public static final ResourceKey<StructureProcessorList> VERADEXIAN_RUINS_ARCTIC = createKey("veradexian_ruins_arctic");
    public static final ResourceKey<StructureProcessorList> VERADEXIAN_LIBRARY_TEMPERATE = createKey("veradexian_library_temperate");
    public static final ResourceKey<StructureProcessorList> VERADEXIAN_LIBRARY_ARCTIC = createKey("veradexian_library_arctic");
    public static final ResourceKey<StructureProcessorList> VERADEXIAN_AQUEDUCT = createKey("veradexian_aqueduct");
    public static final ResourceKey<StructureProcessorList> BREXALLEN_RUINS = createKey("brexallen_ruins");
    public static final ResourceKey<StructureProcessorList> BREXALLEN_RUINS_CENTER = createKey("brexallen_ruins_center");
    public static final ResourceKey<StructureProcessorList> UNDERCLOUD_MINESHAFT = createKey("undercloud_mineshaft");
    public static final ResourceKey<StructureProcessorList> ANCIENT_HENGE = createKey("ancient_henge");
    public static final ResourceKey<StructureProcessorList> IRRADIATED_BUNKER_EXTERIOR = createKey("irradiated_bunker_exterior");
    public static final ResourceKey<StructureProcessorList> SENTRY_RUINS_ROOM = createKey("sentry_ruins_room");
    public static final ResourceKey<StructureProcessorList> SENTRY_RUINS_STAIRCASE = createKey("sentry_ruins_staircase");
    public static final ResourceKey<StructureProcessorList> SENTRY_RUINS_BOSS_ROOM = createKey("sentry_ruins_boss_room");
    public static final ResourceKey<StructureProcessorList> INFECTED_GUARDIAN_TREE = createKey("infected_guardian_tree");
    public static final ResourceKey<StructureProcessorList> INFECTED_GUARDIAN_TREE_DEBUG = createKey("infected_guardian_tree_debug");

    public static void bootstrap(BootstrapContext<StructureProcessorList> context) {
        HolderGetter<DensityFunction> density = context.lookup(Registries.DENSITY_FUNCTION);

        register(context, CAMP, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.COARSE_AETHER_DIRT, 0.2F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.AETHER_GRASS_BLOCK.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.HOLYSTONE, 0.3F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.MOSSY_HOLYSTONE.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.HOLYSTONE, 0.1F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.AETHER_GRASS_BLOCK.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.BLUEBERRY_BUSH, 0.2F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.BLUEBERRY_BUSH, 0.4F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.BLUEBERRY_BUSH_STEM.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.VALKYRIE_SPROUT, 0.2F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.VALKYRIE_SPROUT, 0.25F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.VALKYRIE_SPROUT.defaultBlockState().setValue(ValkyrieSproutBlock.AGE, 0)),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.VALKYRIE_SPROUT, 0.15F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.VALKYRIE_SPROUT.defaultBlockState().setValue(ValkyrieSproutBlock.AGE, 1))
                ))
        ));

        register(context, VERADEXIAN_RUINS_TEMPERATE, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.VERADEXIAN_VASE, 0.65F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState())
                ))
        ));
        register(context, VERADEXIAN_RUINS_ARCTIC, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.VERADEXIAN_VASE, 0.65F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState())
                )),
                new ShayelinnMossProcessor()
        ));
        register(context, VERADEXIAN_LIBRARY_TEMPERATE, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.VERADEXIAN_VASE, 0.65F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.RUSTIC_ARKENIUM_LANTERN, 0.5F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.AETHER_BUSH, 0.4F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.SKYROOT_CHEST, 0.25F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState())
                ))
        ));
        register(context, VERADEXIAN_LIBRARY_ARCTIC, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.VERADEXIAN_VASE, 0.65F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.RUSTIC_ARKENIUM_LANTERN, 0.5F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.AETHER_BUSH, 0.4F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.SKYROOT_CHEST, 0.25F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState())
                )),
                new ShayelinnMossProcessor()
        ));
        register(context, VERADEXIAN_AQUEDUCT, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.VERADEXIAN_VASE, 0.2F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.COLD_AERCLOUD), new TagMatchTest(AetherIITags.Blocks.AETHER_CARVER_REPLACEABLES), AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.COLD_AERCLOUD), new BlockMatchTest(Blocks.WATER), Blocks.WATER.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.COLD_AERCLOUD, 0.5F), new BlockMatchTest(Blocks.AIR), Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.MOSSY_HOLYSTONE, 0.5F), new BlockMatchTest(Blocks.AIR), Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.MOSSY_HOLYSTONE, 0.5F), new BlockMatchTest(Blocks.WATER), Blocks.WATER.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.MOSSY_HOLYSTONE, 0.3F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.HOLYSTONE.defaultBlockState())
                )),
                new ShayelinnMossProcessor()
        ));

        register(context, BREXALLEN_RUINS, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.BREXALLEN_VASE, 0.65F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.GREATROOT_PLANKS, 0.4F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.UNSTABLE_UNDERSHALE.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_BRICKS, 0.3F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.UNDERSHALE.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_FLAGSTONES, 0.3F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.UNDERSHALE.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_TILE, 0.3F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.UNDERSHALE.defaultBlockState())
                ))
        ));
        register(context, BREXALLEN_RUINS_CENTER, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_FLAGSTONES, 0.8F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.UNDERSHALE.defaultBlockState())
                ))
        ));

        register(context, UNDERCLOUD_MINESHAFT, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.HOLYSTONE_VASE, 0.25F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.SKYROOT_CHEST, 0.75F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.AMBROSIUM_WALL_TORCH, 0.95F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.SKYROOT_PLANKS), new BlockMatchTest(AetherIIBlocks.HOLYSTONE), AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.SKYROOT_PLANKS), new BlockMatchTest(AetherIIBlocks.UNDERSHALE), AetherIIBlocks.UNDERSHALE.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.SKYROOT_LOG), new BlockMatchTest(AetherIIBlocks.HOLYSTONE), AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.SKYROOT_LOG), new BlockMatchTest(AetherIIBlocks.UNDERSHALE), AetherIIBlocks.UNDERSHALE.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.SKYROOT_TRUNK), new BlockMatchTest(AetherIIBlocks.HOLYSTONE), AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.SKYROOT_TRUNK), new BlockMatchTest(AetherIIBlocks.UNDERSHALE), AetherIIBlocks.UNDERSHALE.defaultBlockState())
                )),
                new RemoveInAirProcessor()
        ));

        register(context, ANCIENT_HENGE, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.HOLYSTONE_VASE, 0.6F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.RED_CLOUDWOOL, 0.5F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.FERROSITE.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.BLUE_CLOUDWOOL, 0.5F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.YELLOW_CLOUDWOOL, 0.5F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.RED_CLOUDWOOL), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.BLUE_CLOUDWOOL), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new BlockMatchTest(AetherIIBlocks.YELLOW_CLOUDWOOL), AlwaysTrueTest.INSTANCE, AetherIIBlocks.FERROSITE.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.AETHER_GRASS_BLOCK, 0.2F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.HOLYSTONE.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.AETHER_GRASS_BLOCK, 0.15F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.MOSSY_HOLYSTONE.defaultBlockState())
                ))
        ));


        register(context, IRRADIATED_BUNKER_EXTERIOR, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.FADED_HOLYSTONE_BRICKS, 0.25F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.AMBRELINN_MOSS_BLOCK.defaultBlockState())
                ))
        ));

        register(context, SENTRY_RUINS_ROOM, List.<StructureProcessor>of(
                SentryRuinsPiece.CAVE_REPLACEABLE,
                SentryRuinsPiece.SENTRY_STONE,
                SentryRuinsPiece.ROOM_DECORATION_RANDOMIZATION,
                MimicContainerProcessor.INSTANCE
        ));
        register(context, SENTRY_RUINS_STAIRCASE, List.<StructureProcessor>of(
                SentryRuinsPiece.CAVE_REPLACEABLE,
                SentryRuinsPiece.STAIRCASE_EXPOSED,
                SentryRuinsPiece.SENTRY_STONE_REDUCED,
                MimicContainerProcessor.INSTANCE
        ));
        register(context, SENTRY_RUINS_BOSS_ROOM, List.<StructureProcessor>of(
                SentryRuinsPiece.SENTRY_STONE_REDUCED,
                new CopyRuleProcessor(SentryRuinsPiece.SENTRY_STONE_LIST_REDUCED),
                BossRoomProcessor.INSTANCE
        ));


        register(context, INFECTED_GUARDIAN_TREE, ImmutableList.<StructureProcessor>of(
                new DensityFunctionProcessor(AetherIIBlocks.GUARDIAN_WOOD.defaultBlockState(), AetherIIBlocks.INFECTED_WOOD.defaultBlockState(), AetherIIDensityFunctions.getFunction(density, AetherIIDensityFunctions.DUNGEONS_INFECTED_BLOCKS), true),
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.GUARDIAN_ROOTS, 0.025F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.LUCENT_GUARDIAN_ROOTS.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.GUARDIAN_ROOTS, 0.01F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.GUARDIAN_LAMP.defaultBlockState())
                ))
        ));
        register(context, INFECTED_GUARDIAN_TREE_DEBUG, ImmutableList.<StructureProcessor>of(
                new RuleProcessor(ImmutableList.of(
                        new ProcessorRule(new TagMatchTest(AetherIITags.Blocks.CLOUDWOOL), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState())
                ))
        ));
    }

    private static ResourceKey<StructureProcessorList> createKey(String name) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
    }

    private static void register(BootstrapContext<StructureProcessorList> context, ResourceKey<StructureProcessorList> resourceKey, List<StructureProcessor> list) {
        context.register(resourceKey, new StructureProcessorList(list));
    }
}