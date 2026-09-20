package com.aetherteam.aetherii.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import com.aetherteam.aetherii.block.natural.FullAetherBushBlock;
import com.aetherteam.aetherii.block.natural.GelBlock;
import net.minecraft.world.level.pathfinder.PathType;
import net.fabricmc.fabric.api.registry.LandPathTypeRegistry;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.construction.*;
import com.aetherteam.aetherii.block.dungeon.*;
import com.aetherteam.aetherii.block.furniture.OutpostCampfireBlock;
import com.aetherteam.aetherii.block.furniture.VaseBlock;
import com.aetherteam.aetherii.block.miscellaneous.*;
import com.aetherteam.aetherii.block.natural.*;
import com.aetherteam.aetherii.block.portal.AetherPortalBlock;
import com.aetherteam.aetherii.block.utility.*;
import com.aetherteam.aetherii.blockentity.AetherIIBlockEntityTypes;
import com.aetherteam.aetherii.blockentity.MuralSection;
import com.aetherteam.aetherii.client.particle.AetherIIParticleTypes;
import com.aetherteam.aetherii.client.sound.AetherIISoundTypes;
import com.aetherteam.aetherii.data.resources.registries.holyisles.HolyIslesConfiguredFeatures;
import com.aetherteam.aetherii.entity.AetherIIEntityTypes;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.item.components.AetherIIDataComponents;
import com.aetherteam.aetherii.item.materials.RockItem;
import com.aetherteam.aetherii.item.miscellaneous.CopyBlockItem;
import com.aetherteam.aetherii.mixin.mixins.common.accessor.FireBlockAccessor;
import com.aetherteam.aetherii.world.tree.AetherIITreeGrowers;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.*;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class AetherIIBlocks extends AetherIIBlockBuilders {

    // Portal
    public static final AetherPortalBlock AETHER_PORTAL = registerWithoutItem("aether_portal", AetherPortalBlock::new, () -> Block.Properties.of().noCollision().randomTicks().strength(-1.0F).sound(SoundType.GLASS).lightLevel(AetherIIBlocks::lightLevel11).pushReaction(PushReaction.IMMOVEABLE).forceSolidOn().noLootTable());

    // Surface
    public static final Block AETHER_GRASS_BLOCK = register("aether_grass_block", AetherGrassBlock::new, () -> Block.Properties.of().mapColor(MapColor.WARPED_WART_BLOCK).randomTicks().strength(0.6F).sound(SoundType.GRASS));
    public static final Block ENCHANTED_AETHER_GRASS_BLOCK = register("enchanted_aether_grass_block", EnchantedAetherGrassBlock::new, () -> Block.Properties.of().mapColor(MapColor.GOLD).randomTicks().strength(0.6F).sound(SoundType.GRASS));
    public static final Block AETHER_DIRT = register("aether_dirt", () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).strength(0.5F).sound(SoundType.GRAVEL));
    public static final Block COARSE_AETHER_DIRT = register("coarse_aether_dirt", () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).strength(0.5F).sound(SoundType.GRAVEL));
    public static final Block MYCELIAL_AETHER_DIRT = register("mycelial_aether_dirt", () -> Block.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(0.5F).sound(SoundType.GRAVEL));
    public static final Block AETHER_DIRT_PATH = register("aether_dirt_path", AetherDirtPathBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).strength(0.65F).sound(SoundType.GRASS).isViewBlocking(AetherIIBlocks::always).isSuffocating(AetherIIBlocks::always));
    public static final Block AETHER_FARMLAND = register("aether_farmland", AetherFarmlandBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).isViewBlocking(AetherIIBlocks::always).isSuffocating(AetherIIBlocks::always));
    public static final Block SHIMMERING_SILT = register("shimmering_silt", (properties) -> new ColoredFallingBlock(new ColorRGBA(8360341), properties), () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).randomTicks().strength(0.5F).instrument(NoteBlockInstrument.SNARE).sound(SoundType.SAND).isViewBlocking(AetherIIBlocks::always).isSuffocating(AetherIIBlocks::always));

    // Underground
    public static final Block HOLYSTONE = register("holystone", () -> Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F, 6.0F).requiresCorrectToolForDrops());
    public static final Block UNSTABLE_HOLYSTONE = register("unstable_holystone", UnstableBlock::new, () -> Block.Properties.ofFullCopy(HOLYSTONE));
    public static final Block UNDERSHALE = register("undershale", () -> Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F, 6.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops());
    public static final Block UNSTABLE_UNDERSHALE = register("unstable_undershale", UnstableBlock::new, () -> Block.Properties.ofFullCopy(UNDERSHALE));
    public static final Block AGIOSITE = register("agiosite", () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F, 6.0F).requiresCorrectToolForDrops());
    public static final Block ICHORITE = register("ichorite", () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).strength(4.0F, 6.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops());
    public static final HalfTransparentBlock CRUDE_SCATTERGLASS = register("crude_scatterglass", CrudeScatterglassBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F, 6.0F).sound(SoundType.GLASS).requiresCorrectToolForDrops().noOcclusion().isViewBlocking(AetherIIBlocks::never));
    public static final Block SKY_ROOTS = register("sky_roots", AetherHangingRootsBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).replaceable().noCollision().instabreak().sound(SoundType.HANGING_ROOTS).offsetType(Block.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.POPPED));
    public static final LiquidBlock ALKAHEST = registerWithoutItem("alkahest", (properties) -> new AlkahestLiquidBlock(AetherIIFluids.ALKAHEST, properties), () -> Block.Properties.of().mapColor(MapColor.FIRE).replaceable().noCollision().randomTicks().strength(100.0F).lightLevel(AetherIIBlocks::lightLevel8).pushReaction(PushReaction.POPPED).noLootTable().liquid().sound(SoundType.EMPTY));
    public static final Block HESTVEIL = registerWithoutItem("hestveil", HestveilBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).strength(-1.0F, 0.0F).replaceable().noCollision().noOcclusion().noTerrainParticles().isValidSpawn(AetherIIBlockBuilders::never).isRedstoneConductor(AetherIIBlockBuilders::never).isSuffocating(AetherIIBlockBuilders::never).isViewBlocking(AetherIIBlockBuilders::never).noLootTable());
    public static final AbstractPointedStoneBlock POINTED_HOLYSTONE = register("pointed_holystone", PointedHolystoneBlock::new, () -> Block.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().sound(SoundType.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(Block.OffsetType.XZ).pushReaction(PushReaction.POPPED).isRedstoneConductor(AetherIIBlocks::never));
    public static final AbstractPointedStoneBlock POINTED_ICHORITE = register("pointed_ichorite", PointedIchoriteBlock::new, () -> Block.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().sound(SoundType.POINTED_DRIPSTONE).randomTicks().strength(4.0F, 3.0F).dynamicShape().offsetType(Block.OffsetType.XZ).pushReaction(PushReaction.POPPED).isRedstoneConductor(AetherIIBlocks::never));

    // Highfields
    public static final Block QUICKSOIL = register("quicksoil", QuicksoilBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.SNARE).strength(0.5F).friction(1.1F).sound(SoundType.SAND));
    public static final Block MOSSY_HOLYSTONE = register("mossy_holystone", () -> Block.Properties.ofFullCopy(HOLYSTONE));
    public static final Block BRYALINN_MOSS_BLOCK = register("bryalinn_moss_block", (properties) -> new AetherMossBlock(HolyIslesConfiguredFeatures.BRYALINN_MOSS_FLOOR, properties), () -> Block.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.POPPED));
    public static final Block BRYALINN_MOSS_CARPET = register("bryalinn_moss_carpet", CarpetBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.POPPED));
    public static final Block BRYALINN_MOSS_VINES = register("bryalinn_moss_vines", BottomedVineBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().randomTicks().strength(0.1F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.POPPED));
    public static final Block BRYALINN_MOSS_FLOWERS = register("bryalinn_moss_flowers", MossFlowersBlock::new, () -> Block.Properties.of().mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED));
    public static final Block TANGLED_BRANCHES = register("tangled_branches", TangledBranchBlock::new, () -> Block.Properties.of().noOcclusion().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.WOOD));

    // Magnetic
    public static final Block FERROSITE_SAND = register("ferrosite_sand", () -> Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND));
    public static final Block FERROSITE_MUD = register("ferrosite_mud", MudBlock::new, () -> Block.Properties.ofFullCopy(Blocks.MUD).mapColor(MapColor.COLOR_PURPLE));
    public static final Block FERROSITE = register("ferrosite", () -> Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F, 6.0F).sound(AetherIISoundTypes.FERROSITE).requiresCorrectToolForDrops());
    public static final Block RUSTED_FERROSITE = register("rusted_ferrosite", () -> Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F, 6.0F).sound(AetherIISoundTypes.FERROSITE).requiresCorrectToolForDrops());
    public static final Block MAGNETIC_SHROOM = register("magnetic_shroom", (properties) -> new MushroomBlock(HolyIslesConfiguredFeatures.HUGE_MAGNETIC_SHROOM_GROWN, properties), () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel(light -> 5).postProcess(AetherIIBlocks::postProcessSelf).pushReaction(PushReaction.POPPED));
    public static final Block MAGNETIC_SHROOM_BLOCK = register("magnetic_shroom_block", HugeMushroomBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava());
    public static final Block SPOTTED_MAGNETIC_SHROOM_BLOCK = register("spotted_magnetic_shroom_block", HugeMushroomBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava().lightLevel((state) -> 6));
    public static final Block MAGNETIC_SHROOM_STEM = register("magnetic_shroom_stem", HugeMushroomBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava());

    // Arctic
    public static final Block ARCTIC_SNOW_BLOCK = register("arctic_snow_block", () -> Block.Properties.of().mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(0.2F).sound(SoundType.SNOW));
    @SuppressWarnings("deprecation")
    public static final Block ARCTIC_SNOW = register("arctic_snow", SnowLayerBlock::new, () -> Block.Properties.of().mapColor(MapColor.SNOW).replaceable().forceSolidOff().randomTicks().strength(0.1F).sound(SoundType.SNOW).requiresCorrectToolForDrops().isViewBlocking((state, level, pos, nearPlaneBox) -> state.getValue(SnowLayerBlock.LAYERS) >= 8).pushReaction(PushReaction.POPPED).postProcess(AetherIIBlocks::postProcessSelf));
    public static final Block ARCTIC_ICE = register("arctic_ice", IceBlock::new, () -> Block.Properties.of().mapColor(MapColor.ICE).friction(0.98F).randomTicks().strength(0.5F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((state, level, pos, entityType) -> entityType.builtInRegistryHolder().is(AetherIITags.EntityTypes.SPAWNING_ICE)).isRedstoneConductor(AetherIIBlocks::never));
    public static final Block FRAGILE_ARCTIC_ICE = register("fragile_arctic_ice", FragileIceBlock::new, () -> Block.Properties.ofFullCopy(ARCTIC_ICE));
    public static final Block ARCTIC_PACKED_ICE = register("arctic_packed_ice", () -> Block.Properties.of().mapColor(MapColor.ICE).instrument(NoteBlockInstrument.CHIME).friction(0.98F).strength(0.5F).sound(SoundType.GLASS));
    public static final Block ICESTONE = register("icestone", IcestoneBlock::new, () -> Block.Properties.of().mapColor(MapColor.ICE).instrument(NoteBlockInstrument.CHIME).strength(0.5F).randomTicks().sound(SoundType.GLASS).requiresCorrectToolForDrops());
    public static final Block LARGE_ARCTIC_ICE_CRYSTAL = register("large_arctic_ice_crystal", (properties) -> new IceCrystalBlock(8.0F, 2.0F, properties), () -> Block.Properties.of().mapColor(MapColor.ICE).forceSolidOn().noOcclusion().sound(SoundType.GLASS).strength(0.5F).pushReaction(PushReaction.POPPED).randomTicks());
    @SuppressWarnings("deprecation")
    public static final Block MEDIUM_ARCTIC_ICE_CRYSTAL = register("medium_arctic_ice_crystal", (properties) -> new IceCrystalBlock(8.0F, 2.0F, properties), () -> Block.Properties.ofLegacyCopy(LARGE_ARCTIC_ICE_CRYSTAL));
    @SuppressWarnings("deprecation")
    public static final Block SMALL_ARCTIC_ICE_CRYSTAL = register("small_arctic_ice_crystal", (properties) -> new IceCrystalBlock(8.0F, 2.0F, properties), () -> Block.Properties.ofLegacyCopy(LARGE_ARCTIC_ICE_CRYSTAL));
    public static final Block SHAYELINN_MOSS_BLOCK = register("shayelinn_moss_block", (properties) -> new AetherMossBlock(HolyIslesConfiguredFeatures.SHAYELINN_MOSS_FLOOR, properties), () -> Block.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.POPPED));
    public static final Block SHAYELINN_MOSS_CARPET = register("shayelinn_moss_carpet", CarpetBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.POPPED));
    public static final Block SHAYELINN_MOSS_VINES = register("shayelinn_moss_vines", BottomedVineBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_CYAN).replaceable().noCollision().randomTicks().strength(0.1F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.POPPED));

    // Irradiated
    public static final Block IRRADIATED_HOLYSTONE = register("irradiated_holystone", IrradiatedBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.HOLYSTONE));
    public static final Block IRRADIATED_DUST_BLOCK = register("irradiated_dust_block", IrradiatedBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(3.0F, 30.0F).lightLevel((state) -> 5).requiresCorrectToolForDrops());
    public static final Block AMBRELINN_MOSS_BLOCK = register("ambrelinn_moss_block", (properties) -> new AetherMossBlock(HolyIslesConfiguredFeatures.AMBRELINN_MOSS_FLOOR, properties), () -> Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.POPPED));
    public static final Block AMBRELINN_MOSS_CARPET = register("ambrelinn_moss_carpet", CarpetBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.POPPED));
    public static final Block AMBRELINN_MOSS_VINES = register("ambrelinn_moss_vines", BottomedVineBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).replaceable().noCollision().randomTicks().strength(0.1F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.POPPED));
    public static final Block TARAHESP_FLOWERS = register("tarahesp_flowers", MossFlowersBlock::new, () -> Block.Properties.of().mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED));

    // Ores
    public static final Block HOLYSTONE_QUARTZ_ORE = register("holystone_quartz_ore", (properties) -> new DropExperienceBlock(UniformInt.of(2, 5), properties), () -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F, 3.0F).requiresCorrectToolForDrops());
    public static final Block AMBROSIUM_ORE = register("ambrosium_ore", (properties) -> new DropExperienceBlock(UniformInt.of(0, 2), properties), () -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F, 3.0F).requiresCorrectToolForDrops().lightLevel(AetherIIBlocks::lightLevel8));
    public static final Block ZANITE_ORE = register("zanite_ore", (properties) -> new DropExperienceBlock(UniformInt.of(3, 5), properties), () -> Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F, 3.0F).requiresCorrectToolForDrops());
    public static final Block ARKENIUM_ORE = register("arkenium_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F, 3.0F).requiresCorrectToolForDrops());
    public static final Block GRAVITITE_ORE = register("gravitite_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F, 3.0F).requiresCorrectToolForDrops());
    public static final Block GLINT_ORE = register("glint_ore", (properties) -> new DropExperienceBlock(UniformInt.of(3, 5), properties), () -> Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F, 3.0F).requiresCorrectToolForDrops());
    public static final Block UNDERSHALE_AMBROSIUM_ORE = register("undershale_ambrosium_ore", (properties) -> new DropExperienceBlock(UniformInt.of(0, 2), properties), () -> Block.Properties.ofFullCopy(AMBROSIUM_ORE).mapColor(MapColor.COLOR_LIGHT_GRAY).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE).lightLevel(AetherIIBlocks::lightLevel8));
    public static final Block UNDERSHALE_ZANITE_ORE = register("undershale_zanite_ore", (properties) -> new DropExperienceBlock(UniformInt.of(3, 5), properties), () -> Block.Properties.ofFullCopy(ZANITE_ORE).mapColor(MapColor.COLOR_LIGHT_GRAY).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE));
    public static final Block UNDERSHALE_ARKENIUM_ORE = register("undershale_arkenium_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> Block.Properties.ofFullCopy(ARKENIUM_ORE).mapColor(MapColor.COLOR_LIGHT_GRAY).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE));
    public static final Block UNDERSHALE_GRAVITITE_ORE = register("undershale_gravitite_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> Block.Properties.ofFullCopy(GRAVITITE_ORE).mapColor(MapColor.COLOR_LIGHT_GRAY).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE));
    public static final Block UNDERSHALE_GLINT_ORE = register("undershale_glint_ore", (properties) -> new DropExperienceBlock(UniformInt.of(3, 5), properties), () -> Block.Properties.ofFullCopy(GLINT_ORE).mapColor(MapColor.WOOL).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE));
    public static final Block CORROBONITE_ORE = register("corrobonite_ore", (properties) -> new CorroboniteOreBlock(ConstantInt.of(0), properties), () -> Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 3.0F).requiresCorrectToolForDrops());
    public static final Block CORROBONITE_CLUSTER = register("corrobonite_cluster", CorroboniteClusterBlock::new, () -> Block.Properties.of().mapColor(MapColor.WOOL).strength(3.0F, 3.0F).replaceable().noOcclusion().noCollision().instabreak());

    // Aerclouds
    public static final Block COLD_AERCLOUD = register("cold_aercloud", AercloudBlock::new, coldAercloudProperties(MapColor.SNOW));
    public static final Block GOLDEN_AERCLOUD = register("golden_aercloud", AercloudBlock::new, specialAercloudProperties(MapColor.COLOR_YELLOW));
    public static final Block BLUE_AERCLOUD = register("blue_aercloud", BlueAercloudBlock::new, specialAercloudProperties(MapColor.COLOR_LIGHT_BLUE));
    public static final Block GREEN_AERCLOUD = register("green_aercloud", GreenAercloudBlock::new, specialAercloudProperties(MapColor.COLOR_LIGHT_GREEN));
    public static final Block PURPLE_AERCLOUD = register("purple_aercloud", PurpleAercloudBlock::new, specialAercloudProperties(MapColor.COLOR_MAGENTA));
    public static final Block STORM_AERCLOUD = register("storm_aercloud", AercloudBlock::new, specialAercloudProperties(MapColor.DEEPSLATE));

    // Nest Blocks
    public static final Block WOVEN_SKYROOT_STICKS = register("woven_skyroot_sticks", WovenSticksBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.75F).sound(SoundType.GRASS));
    public static final Block ANIMAL_STASH = register("animal_stash", AnimalStashBlock::new, () -> Block.Properties.ofFullCopy(WOVEN_SKYROOT_STICKS));
    public static final Block MOA_EGG = registerWithoutItem("moa_egg", MoaEggBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.5F).sound(SoundType.METAL).noOcclusion());

    // Logs
    public static final RotatedPillarBlock SKYROOT_LOG = register("skyroot_log", RotatedPillarBlock::new, logProperties(MapColor.COLOR_BROWN, MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final RotatedPillarBlock SKYROOT_WOOD = register("skyroot_wood", RotatedPillarBlock::new, logProperties(MapColor.COLOR_BROWN, MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final RotatedPillarBlock STRIPPED_SKYROOT_LOG = register("stripped_skyroot_log", RotatedPillarBlock::new, logProperties(MapColor.TERRACOTTA_LIGHT_GRAY, MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final RotatedPillarBlock STRIPPED_SKYROOT_WOOD = register("stripped_skyroot_wood", RotatedPillarBlock::new, logProperties(MapColor.TERRACOTTA_LIGHT_GRAY, MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final RotatedPillarBlock GREATROOT_LOG = register("greatroot_log", RotatedPillarBlock::new, logProperties(MapColor.TERRACOTTA_BROWN, MapColor.COLOR_BROWN));
    public static final RotatedPillarBlock GREATROOT_WOOD = register("greatroot_wood", RotatedPillarBlock::new, logProperties(MapColor.TERRACOTTA_BROWN, MapColor.COLOR_BROWN));
    public static final RotatedPillarBlock STRIPPED_GREATROOT_LOG = register("stripped_greatroot_log", RotatedPillarBlock::new, logProperties(MapColor.TERRACOTTA_BROWN, MapColor.COLOR_BROWN));
    public static final RotatedPillarBlock STRIPPED_GREATROOT_WOOD = register("stripped_greatroot_wood", RotatedPillarBlock::new, logProperties(MapColor.TERRACOTTA_BROWN, MapColor.COLOR_BROWN));
    public static final RotatedPillarBlock WISPROOT_LOG = register("wisproot_log", RotatedPillarBlock::new, logProperties(MapColor.QUARTZ, MapColor.QUARTZ));
    public static final RotatedPillarBlock MOSSY_WISPROOT_LOG = register("mossy_wisproot_log", RotatedPillarBlock::new, logProperties(MapColor.QUARTZ, MapColor.QUARTZ));
    public static final FacingPillarBlock MOSSY_WISPROOT_LOG_BASE = register("mossy_wisproot_log_base", FacingPillarBlock::new, (() -> Block.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RotatedPillarBlock WISPROOT_WOOD = register("wisproot_wood", RotatedPillarBlock::new, logProperties(MapColor.QUARTZ, MapColor.QUARTZ));
    public static final RotatedPillarBlock MOSSY_WISPROOT_WOOD = register("mossy_wisproot_wood", RotatedPillarBlock::new, logProperties(MapColor.QUARTZ, MapColor.QUARTZ));
    public static final RotatedPillarBlock STRIPPED_WISPROOT_LOG = register("stripped_wisproot_log", RotatedPillarBlock::new, logProperties(MapColor.QUARTZ, MapColor.QUARTZ));
    public static final RotatedPillarBlock STRIPPED_WISPROOT_WOOD = register("stripped_wisproot_wood", RotatedPillarBlock::new, logProperties(MapColor.QUARTZ, MapColor.QUARTZ));
    public static final RotatedPillarBlock AMBEROOT_LOG = register("amberoot_log", RotatedPillarBlock::new, logProperties(MapColor.TERRACOTTA_BROWN, MapColor.WOOD));
    public static final RotatedPillarBlock AMBEROOT_DEPOSIT = register("amberoot_deposit", RotatedPillarBlock::new, logProperties(MapColor.COLOR_ORANGE, MapColor.WOOD));
    public static final RotatedPillarBlock AMBEROOT_WOOD = register("amberoot_wood", RotatedPillarBlock::new, logProperties(MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_BROWN));
    public static final RotatedPillarBlock STRIPPED_AMBEROOT_LOG = register("stripped_amberoot_log", RotatedPillarBlock::new, logProperties(MapColor.WOOD, MapColor.WOOD));
    public static final RotatedPillarBlock STRIPPED_AMBEROOT_WOOD = register("stripped_amberoot_wood", RotatedPillarBlock::new, logProperties(MapColor.WOOD, MapColor.WOOD));

    // Trunks
    public static final TrunkBlock SKYROOT_TRUNK = register("skyroot_trunk", TrunkBlock::new, trunkProperties(MapColor.COLOR_BROWN));
    public static final TrunkBlock STRIPPED_SKYROOT_TRUNK = register("stripped_skyroot_trunk", TrunkBlock::new, trunkProperties(MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final TrunkBlock GREATROOT_TRUNK = register("greatroot_trunk", TrunkBlock::new, trunkProperties(MapColor.TERRACOTTA_BROWN));
    public static final TrunkBlock STRIPPED_GREATROOT_TRUNK = register("stripped_greatroot_trunk", TrunkBlock::new, trunkProperties(MapColor.COLOR_BROWN));
    public static final TrunkBlock WISPROOT_TRUNK = register("wisproot_trunk", TrunkBlock::new, trunkProperties(MapColor.QUARTZ));
    public static final TrunkBlock MOSSY_WISPROOT_TRUNK = register("mossy_wisproot_trunk", TrunkBlock::new, trunkProperties(MapColor.QUARTZ));
    public static final TrunkBlock STRIPPED_WISPROOT_TRUNK = register("stripped_wisproot_trunk", TrunkBlock::new, trunkProperties(MapColor.QUARTZ));
    public static final TrunkBlock AMBEROOT_TRUNK = register("amberoot_trunk", TrunkBlock::new, trunkProperties(MapColor.COLOR_BROWN));
    public static final TrunkBlock STRIPPED_AMBEROOT_TRUNK = register("stripped_amberoot_trunk", TrunkBlock::new, trunkProperties(MapColor.COLOR_BROWN));

    // Leaf Pile
    public static final Block SKYROOT_LEAF_PILE = register("skyroot_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.GRASS));
    public static final Block SKYPLANE_LEAF_PILE = register("skyplane_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.COLOR_BLUE));
    public static final Block SKYBIRCH_LEAF_PILE = register("skybirch_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.COLOR_LIGHT_BLUE));
    public static final Block SKYPINE_LEAF_PILE = register("skypine_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.COLOR_MAGENTA));
    public static final Block WISPROOT_LEAF_PILE = register("wisproot_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.DIAMOND));
    public static final Block WISPTOP_LEAF_PILE = register("wisptop_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.COLOR_MAGENTA));
    public static final Block GREATROOT_LEAF_PILE = register("greatroot_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.TERRACOTTA_LIGHT_GREEN));
    public static final Block GREATOAK_LEAF_PILE = register("greatoak_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.COLOR_MAGENTA));
    public static final Block GREATBOA_LEAF_PILE = register("greatboa_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.COLOR_BLUE));
    public static final Block AMBEROOT_LEAF_PILE = register("amberoot_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.GOLD));
    public static final Block IRRADIATED_SKYROOT_LEAF_PILE = register("irradiated_skyroot_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_SKYPLANE_LEAF_PILE = register("irradiated_skyplane_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_SKYBIRCH_LEAF_PILE = register("irradiated_skybirch_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_SKYPINE_LEAF_PILE = register("irradiated_skypine_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_WISPROOT_LEAF_PILE = register("irradiated_wisproot_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_WISPTOP_LEAF_PILE = register("irradiated_wisptop_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_GREATROOT_LEAF_PILE = register("irradiated_greatroot_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_GREATOAK_LEAF_PILE = register("irradiated_greatoak_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_GREATBOA_LEAF_PILE = register("irradiated_greatboa_leaf_pile", AetherLeafPileBlock::new, leafPileProperties(MapColor.TERRACOTTA_YELLOW));

    // Leaves
    public static final Block SKYROOT_LEAVES = register("skyroot_leaves", (properties) -> new AetherLeavesBlock(properties, AetherIIParticleTypes.SKYROOT_LEAVES, () -> AetherIIBlocks.SKYROOT_LEAF_PILE), leavesProperties(MapColor.GRASS));
    public static final Block SKYPLANE_LEAVES = register("skyplane_leaves", (properties) -> new BlocksLightLeavesBlock(properties, AetherIIParticleTypes.SKYPLANE_LEAVES, () -> AetherIIBlocks.SKYPLANE_LEAF_PILE), leavesProperties(MapColor.COLOR_BLUE));
    public static final Block SKYBIRCH_LEAVES = register("skybirch_leaves", (properties) -> new AetherLeavesBlock(properties, AetherIIParticleTypes.SKYBIRCH_LEAVES, () -> AetherIIBlocks.SKYBIRCH_LEAF_PILE), leavesProperties(MapColor.COLOR_LIGHT_BLUE));
    public static final Block SKYPINE_LEAVES = register("skypine_leaves", (properties) -> new AetherLeavesBlock(properties, AetherIIParticleTypes.SKYPINE_LEAVES, () -> AetherIIBlocks.SKYPINE_LEAF_PILE), leavesProperties(MapColor.COLOR_MAGENTA));
    public static final Block WISPROOT_LEAVES = register("wisproot_leaves", (properties) -> new AetherLeavesBlock(properties, AetherIIParticleTypes.WISPROOT_LEAVES, () -> AetherIIBlocks.WISPROOT_LEAF_PILE), leavesProperties(MapColor.DIAMOND));
    public static final Block WISPTOP_LEAVES = register("wisptop_leaves", (properties) -> new AetherLeavesBlock(properties, AetherIIParticleTypes.WISPTOP_LEAVES, () -> AetherIIBlocks.WISPTOP_LEAF_PILE), leavesProperties(MapColor.COLOR_MAGENTA));
    public static final Block GREATROOT_LEAVES = register("greatroot_leaves", (properties) -> new AetherLeavesBlock(properties, AetherIIParticleTypes.GREATROOT_LEAVES, () -> AetherIIBlocks.GREATROOT_LEAF_PILE), leavesProperties(MapColor.TERRACOTTA_LIGHT_GREEN));
    public static final Block GREATOAK_LEAVES = register("greatoak_leaves", (properties) -> new AllowsLightLeavesBlock(properties, AetherIIParticleTypes.GREATOAK_LEAVES, () -> AetherIIBlocks.GREATOAK_LEAF_PILE), leavesProperties(MapColor.COLOR_MAGENTA));
    public static final Block GREATBOA_LEAVES = register("greatboa_leaves", (properties) -> new AetherLeavesBlock(properties, AetherIIParticleTypes.GREATBOA_LEAVES, () -> AetherIIBlocks.GREATBOA_LEAF_PILE), leavesProperties(MapColor.COLOR_BLUE));
    public static final Block AMBEROOT_LEAVES = register("amberoot_leaves", (properties) -> new AetherLeavesBlock(properties, AetherIIParticleTypes.AMBEROOT_LEAVES, () -> AetherIIBlocks.AMBEROOT_LEAF_PILE), leavesProperties(MapColor.GOLD));
    public static final Block IRRADIATED_SKYROOT_LEAVES = register("irradiated_skyroot_leaves", (properties) -> new IrradiatedLeavesBlock(properties, AetherIIParticleTypes.IRRADIATED_LEAVES, () -> AetherIIBlocks.IRRADIATED_SKYROOT_LEAF_PILE), leavesProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_SKYPLANE_LEAVES = register("irradiated_skyplane_leaves", (properties) -> new IrradiatedLeavesBlock(properties, AetherIIParticleTypes.IRRADIATED_LEAVES, () -> AetherIIBlocks.IRRADIATED_SKYPLANE_LEAF_PILE), leavesProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_SKYBIRCH_LEAVES = register("irradiated_skybirch_leaves", (properties) -> new IrradiatedLeavesBlock(properties, AetherIIParticleTypes.IRRADIATED_LEAVES, () -> AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAF_PILE), leavesProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_SKYPINE_LEAVES = register("irradiated_skypine_leaves", (properties) -> new IrradiatedLeavesBlock(properties, AetherIIParticleTypes.IRRADIATED_LEAVES, () -> AetherIIBlocks.IRRADIATED_SKYPINE_LEAF_PILE), leavesProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_WISPROOT_LEAVES = register("irradiated_wisproot_leaves", (properties) -> new IrradiatedLeavesBlock(properties, AetherIIParticleTypes.IRRADIATED_LEAVES, () -> AetherIIBlocks.IRRADIATED_WISPROOT_LEAF_PILE), leavesProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_WISPTOP_LEAVES = register("irradiated_wisptop_leaves", (properties) -> new IrradiatedLeavesBlock(properties, AetherIIParticleTypes.IRRADIATED_LEAVES, () -> AetherIIBlocks.IRRADIATED_WISPTOP_LEAF_PILE), leavesProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_GREATROOT_LEAVES = register("irradiated_greatroot_leaves", (properties) -> new IrradiatedLeavesBlock(properties, AetherIIParticleTypes.IRRADIATED_LEAVES, () -> AetherIIBlocks.IRRADIATED_GREATROOT_LEAF_PILE), leavesProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_GREATOAK_LEAVES = register("irradiated_greatoak_leaves", (properties) -> new IrradiatedLeavesBlock(properties, AetherIIParticleTypes.IRRADIATED_LEAVES, () -> AetherIIBlocks.IRRADIATED_GREATOAK_LEAF_PILE), leavesProperties(MapColor.TERRACOTTA_YELLOW));
    public static final Block IRRADIATED_GREATBOA_LEAVES = register("irradiated_greatboa_leaves", (properties) -> new IrradiatedLeavesBlock(properties, AetherIIParticleTypes.IRRADIATED_LEAVES, () -> AetherIIBlocks.IRRADIATED_GREATBOA_LEAF_PILE), leavesProperties(MapColor.TERRACOTTA_YELLOW));

    // Saplings
    public static final SaplingBlock SKYROOT_SAPLING = register("skyroot_sapling", (properties) -> new SaplingBlock(AetherIITreeGrowers.SKYROOT, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final SaplingBlock SKYPLANE_SAPLING = register("skyplane_sapling", (properties) -> new SaplingBlock(AetherIITreeGrowers.SKYPLANE, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final SaplingBlock SKYBIRCH_SAPLING = register("skybirch_sapling", (properties) -> new SaplingBlock(AetherIITreeGrowers.SKYBIRCH, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final SaplingBlock SKYPINE_SAPLING = register("skypine_sapling", (properties) -> new SaplingBlock(AetherIITreeGrowers.SKYPINE, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final SaplingBlock WISPROOT_SAPLING = register("wisproot_sapling", (properties) -> new SaplingBlock(AetherIITreeGrowers.WISPROOT, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final SaplingBlock WISPTOP_SAPLING = register("wisptop_sapling", (properties) -> new SaplingBlock(AetherIITreeGrowers.WISPTOP, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final SaplingBlock GREATROOT_SAPLING = register("greatroot_sapling", (properties) -> new SaplingBlock(AetherIITreeGrowers.GREATROOT, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final SaplingBlock GREATOAK_SAPLING = register("greatoak_sapling", (properties) -> new SaplingBlock(AetherIITreeGrowers.GREATOAK, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final SaplingBlock GREATBOA_SAPLING = register("greatboa_sapling", (properties) -> new SaplingBlock(AetherIITreeGrowers.GREATBOA, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final SaplingBlock AMBEROOT_SAPLING = register("amberoot_sapling", (properties) -> new SaplingBlock(AetherIITreeGrowers.AMBEROOT, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    // Potted Saplings
    public static final FlowerPotBlock POTTED_SKYROOT_SAPLING = registerWithoutItem("potted_skyroot_sapling", (properties) -> new FlowerPotBlock(SKYROOT_SAPLING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_SKYPLANE_SAPLING = registerWithoutItem("potted_skyplane_sapling", (properties) -> new FlowerPotBlock(SKYPLANE_SAPLING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_SKYBIRCH_SAPLING = registerWithoutItem("potted_skybirch_sapling", (properties) -> new FlowerPotBlock(SKYBIRCH_SAPLING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_SKYPINE_SAPLING = registerWithoutItem("potted_skypine_sapling", (properties) -> new FlowerPotBlock(SKYPINE_SAPLING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_WISPROOT_SAPLING = registerWithoutItem("potted_wisproot_sapling", (properties) -> new FlowerPotBlock(WISPROOT_SAPLING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_WISPTOP_SAPLING = registerWithoutItem("potted_wisptop_sapling", (properties) -> new FlowerPotBlock(WISPTOP_SAPLING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_GREATROOT_SAPLING = registerWithoutItem("potted_greatroot_sapling", (properties) -> new FlowerPotBlock(GREATROOT_SAPLING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_GREATOAK_SAPLING = registerWithoutItem("potted_greatoak_sapling", (properties) -> new FlowerPotBlock(GREATOAK_SAPLING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_GREATBOA_SAPLING = registerWithoutItem("potted_greatboa_sapling", (properties) -> new FlowerPotBlock(GREATBOA_SAPLING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_AMBEROOT_SAPLING = registerWithoutItem("potted_amberoot_sapling", (properties) -> new FlowerPotBlock(AMBEROOT_SAPLING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));

    // Grasses
    public static final Block SHORT_AETHER_GRASS = register("short_aether_grass", AetherTallGrassBlock::new, () -> Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS).offsetType(Block.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.POPPED).postProcess(AetherIIBlocks::postProcessSelf));
    public static final Block MEDIUM_AETHER_GRASS = register("medium_aether_grass", AetherTallGrassBlock::new, () -> Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS).offsetType(Block.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.POPPED).postProcess(AetherIIBlocks::postProcessSelf));
    public static final Block TALL_AETHER_GRASS = register("tall_aether_grass", AetherTallGrassBlock::new, () -> Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS).offsetType(Block.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.POPPED).postProcess(AetherIIBlocks::postProcessSelf));
    public static final Block AETHER_FERN = register("aether_fern", AetherTallGrassBlock::new, () -> Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS).offsetType(Block.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.POPPED).postProcess(AetherIIBlocks::postProcessSelf));
    public static final Block SHIELD_FERN = register("shield_fern", AetherTallGrassBlock::new, () -> Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS).offsetType(Block.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.POPPED).postProcess(AetherIIBlocks::postProcessSelf));

    // Flowers
    public static final Block HESPEROSE = register("hesperose", (properties) -> new AetherFlowerBlock(properties), () -> Block.Properties.ofFullCopy(Blocks.DANDELION));
    public static final Block TARABLOOM = register("tarabloom", (properties) -> new AetherFlowerBlock(properties), () -> Block.Properties.ofFullCopy(Blocks.DANDELION));
    public static final Block POASPROUT = register("poasprout", (properties) -> new AetherFlowerBlock(properties), () -> Block.Properties.ofFullCopy(Blocks.DANDELION));
    public static final Block LILICHIME = register("lilichime", (properties) -> new AetherFlowerBlock(properties), () -> Block.Properties.ofFullCopy(Blocks.DANDELION));
    public static final Block PLURACIAN = register("pluracian", (properties) -> new FacingFlowerBlock(properties), () -> Block.Properties.ofFullCopy(Blocks.DANDELION));
    public static final Block SATIVAL_SHOOT = register("satival_shoot", (properties) -> new AetherFlowerBlock(properties), () -> Block.Properties.ofFullCopy(Blocks.DANDELION));
    public static final Block HOLPUPEA = register("holpupea", MossFlowersBlock::new, () -> Block.Properties.of().mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED));
    public static final Block BLADE_POA = register("blade_poa", (properties) -> new AetherFlowerBlock(properties), () -> Block.Properties.ofFullCopy(Blocks.DANDELION));
    public static final Block AECHOR_CUTTING = register("aechor_cutting", (properties) -> new PlantMobCuttingBlock(() -> AetherIIEntityTypes.AECHOR_PLANT, properties), () -> Block.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollision().strength(0.65F).sound(SoundType.GRASS).offsetType(Block.OffsetType.XZ).pushReaction(PushReaction.POPPED).requiresCorrectToolForDrops());
    public static final Block CARRION_CUTTING = register("carrion_cutting", (properties) -> new PlantMobCuttingBlock(() -> AetherIIEntityTypes.CARRION_SPROUT, properties), () -> Block.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollision().strength(0.65F).sound(SoundType.GRASS).offsetType(Block.OffsetType.XZ).pushReaction(PushReaction.POPPED).requiresCorrectToolForDrops());

    // Potted Flowers
    public static final FlowerPotBlock POTTED_MAGNETIC_SHROOM = registerWithoutItem("potted_magnetic_shroom", (properties) -> new FlowerPotBlock(MAGNETIC_SHROOM, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_AETHER_FERN = registerWithoutItem("potted_aether_fern", (properties) -> new FlowerPotBlock(AETHER_FERN, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_SHIELD_FERN = registerWithoutItem("potted_shield_fern", (properties) -> new FlowerPotBlock(SHIELD_FERN, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_HESPEROSE = registerWithoutItem("potted_hesperose", (properties) -> new FlowerPotBlock(HESPEROSE, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_TARABLOOM = registerWithoutItem("potted_tarabloom", (properties) -> new FlowerPotBlock(TARABLOOM, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_POASPROUT = registerWithoutItem("potted_poasprout", (properties) -> new FlowerPotBlock(POASPROUT, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_PLURACIAN = registerWithoutItem("potted_pluracian", (properties) -> new FlowerPotBlock(PLURACIAN, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_SATIVAL_SHOOT = registerWithoutItem("potted_satival_shoot", (properties) -> new FlowerPotBlock(SATIVAL_SHOOT, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_LILICHIME = registerWithoutItem("potted_lilichime", (properties) -> new FlowerPotBlock(LILICHIME, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_BLADE_POA = registerWithoutItem("potted_blade_poa", (properties) -> new FlowerPotBlock(BLADE_POA, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_AECHOR_CUTTING = registerWithoutItem("potted_aechor_cutting", (properties) -> new FlowerPotBlock(AECHOR_CUTTING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_CARRION_CUTTING = registerWithoutItem("potted_carrion_cutting", (properties) -> new FlowerPotBlock(CARRION_CUTTING, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));

    // Bushes
    public static final Block AETHER_BUSH = register("aether_bush", AetherFullBushBlock::new, () -> Block.Properties.of().mapColor(MapColor.GRASS).pushReaction(PushReaction.POPPED).strength(0.65F).sound(SoundType.GRASS).noOcclusion().isValidSpawn(AetherIIBlocks::spawnOnLeaves).isRedstoneConductor(AetherIIBlocks::never).isSuffocating(AetherIIBlocks::never).isViewBlocking(AetherIIBlocks::never).requiresCorrectToolForDrops());
    public static final Block BLUEBERRY_BUSH = register("blueberry_bush", BlueberryBushBlock::new, () -> Block.Properties.of().mapColor(MapColor.GRASS).pushReaction(PushReaction.POPPED).strength(0.65F).sound(SoundType.GRASS).noOcclusion().isValidSpawn(AetherIIBlocks::spawnOnLeaves).isRedstoneConductor(AetherIIBlocks::never).isSuffocating(AetherIIBlocks::never).isViewBlocking(AetherIIBlocks::never).requiresCorrectToolForDrops());
    public static final Block BLUEBERRY_BUSH_STEM = register("blueberry_bush_stem", BlueberryBushStemBlock::new, () -> Block.Properties.of().mapColor(MapColor.GRASS).pushReaction(PushReaction.POPPED).strength(0.65F).sound(SoundType.GRASS).noCollision().requiresCorrectToolForDrops());

    // Potted Bushes
    public static final FlowerPotBlock POTTED_AETHER_BUSH = registerWithoutItem("potted_aether_bush", (properties) -> new FlowerPotBlock(AETHER_BUSH, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_BLUEBERRY_BUSH = registerWithoutItem("potted_blueberry_bush", (properties) -> new FlowerPotBlock(BLUEBERRY_BUSH, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final FlowerPotBlock POTTED_BLUEBERRY_BUSH_STEM = registerWithoutItem("potted_blueberry_bush_stem", (properties) -> new FlowerPotBlock(BLUEBERRY_BUSH_STEM, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));

    // Orange Tree
    public static final Block ORANGE_TREE = register("orange_tree", OrangeTreeBlock::new, () -> Block.Properties.of().mapColor(MapColor.GRASS).noCollision().strength(0.65F).sound(SoundType.GRASS).pushReaction(PushReaction.POPPED).requiresCorrectToolForDrops());

    // Potted Orange Tree
    public static final FlowerPotBlock POTTED_ORANGE_TREE = registerWithoutItem("potted_orange_tree", (properties) -> new FlowerPotBlock(ORANGE_TREE, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));

    // Valkyrie Sprout
    public static final Block VALKYRIE_SPROUT = register("valkyrie_sprout", ValkyrieSproutBlock::new, () -> Block.Properties.of().mapColor(MapColor.GRASS).pushReaction(PushReaction.POPPED).sound(SoundType.GRASS).noCollision().strength(0.65F).offsetType(Block.OffsetType.XZ).requiresCorrectToolForDrops());

    // Brettl
    public static final Block BRETTL_PLANT = registerWithoutItem("brettl_plant", BrettlPlantBlock::new, () -> Block.Properties.of().noCollision().strength(0.65F).randomTicks().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED).requiresCorrectToolForDrops());
    public static final Block BRETTL_PLANT_TIP = registerWithoutItem("brettl_plant_tip", BrettlPlantTipBlock::new, () -> Block.Properties.of().noCollision().strength(0.65F).randomTicks().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED).requiresCorrectToolForDrops());
    public static final Block BRETTL_FLOWER = register("brettl_flower", CactusFlowerBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CACTUS_FLOWER).mapColor(MapColor.DIAMOND));

    // Lake
    public static final Block ARILUM_SHOOT = registerWithoutItem("arilum_shoot", ArilumShootBlock::new, () -> Block.Properties.of().mapColor(MapColor.WATER).noCollision().randomTicks().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.POPPED));
    public static final Block ARILUM = register("arilum", ArilumBlock::new, () -> Block.Properties.of().mapColor(MapColor.WATER).noCollision().randomTicks().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.POPPED));
    public static final Block ARILUM_PLANT = register("arilum_plant", ArilumPlantBlock::new, () -> Block.Properties.of().mapColor(MapColor.WATER).noCollision().randomTicks().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.POPPED));
    public static final Block BLOOMING_ARILUM = register("blooming_arilum", BloomingArilumBlock::new, () -> Block.Properties.of().mapColor(MapColor.WATER).noCollision().randomTicks().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.POPPED).lightLevel((block) -> 5));
    public static final Block BLOOMING_ARILUM_PLANT = register("blooming_arilum_plant", BloomingArilumPlantBlock::new, () -> Block.Properties.of().mapColor(MapColor.WATER).noCollision().randomTicks().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.POPPED).lightLevel((block) -> 5));

    // Ground Decoration
    public static final Block SKYROOT_TWIG = register("skyroot_twig", TwigBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BROWN).noOcclusion().noCollision().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.POPPED));
    public static final Block HOLYSTONE_ROCK = register("holystone_rock", RockBlock::new, () -> Block.Properties.of().mapColor(MapColor.WOOL).noOcclusion().noCollision().instabreak().sound(SoundType.STONE).pushReaction(PushReaction.POPPED), RockItem::new);

    // Skyroot Planks
    public static final Block SKYROOT_PLANKS = register("skyroot_planks", () -> Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final StairBlock SKYROOT_STAIRS = register("skyroot_stairs", (properties) -> new StairBlock(SKYROOT_PLANKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.SKYROOT_PLANKS));
    public static final SlabBlock SKYROOT_SLAB = register("skyroot_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.SKYROOT_PLANKS).strength(2.0F, 3.0F));
    public static final FenceBlock SKYROOT_FENCE = register("skyroot_fence", FenceBlock::new, () -> Block.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final FenceGateBlock SKYROOT_FENCE_GATE = register("skyroot_fence_gate", (properties) -> new FenceGateBlock(AetherIIWoodTypes.SKYROOT, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DoorBlock SKYROOT_DOOR = register("skyroot_door", (properties) -> new DoorBlock(AetherIIWoodTypes.SKYROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final TrapDoorBlock SKYROOT_TRAPDOOR = register("skyroot_trapdoor", (properties) -> new TrapDoorBlock(AetherIIWoodTypes.SKYROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
    public static final ButtonBlock SKYROOT_BUTTON = register("skyroot_button", (properties) -> new ButtonBlock(AetherIIWoodTypes.SKYROOT_BLOCK_SET, 30, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_BUTTON));
    public static final PressurePlateBlock SKYROOT_PRESSURE_PLATE = register("skyroot_pressure_plate", (properties) -> new PressurePlateBlock(AetherIIWoodTypes.SKYROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));
    public static final ShelfBlock SKYROOT_SHELF = register("skyroot_shelf", ShelfBlock::new, () -> Block.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));

    // Skyroot Decorative Blocks
    public static final Block SKYROOT_FLOORBOARDS = register("skyroot_floorboards", () -> Block.Properties.ofFullCopy(SKYROOT_PLANKS).mapColor(MapColor.COLOR_BROWN));
    public static final Block SKYROOT_HIGHLIGHT = register("skyroot_highlight", () -> Block.Properties.ofFullCopy(SKYROOT_PLANKS));
    public static final Block SKYROOT_SHINGLES = register("skyroot_shingles", HorizontalFacingBlock::new, () -> Block.Properties.ofFullCopy(SKYROOT_PLANKS));
    public static final Block SKYROOT_SMALL_SHINGLES = register("skyroot_small_shingles", HorizontalFacingBlock::new, () -> Block.Properties.ofFullCopy(SKYROOT_PLANKS));
    public static final Block SKYROOT_BASE_PLANKS = register("skyroot_base_planks", () -> Block.Properties.ofFullCopy(SKYROOT_PLANKS));
    public static final Block SKYROOT_TOP_PLANKS = register("skyroot_top_planks", () -> Block.Properties.ofFullCopy(SKYROOT_PLANKS));
    public static final FacingPillarBlock SKYROOT_BASE_BEAM = register("skyroot_base_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(SKYROOT_PLANKS));
    public static final FacingPillarBlock SKYROOT_TOP_BEAM = register("skyroot_top_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(SKYROOT_PLANKS));
    public static final FacingPillarBlock SKYROOT_BEAM = register("skyroot_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(SKYROOT_PLANKS));
    public static final DoorBlock SECRET_SKYROOT_DOOR = register("secret_skyroot_door", (properties) -> new SecretDoorBlock(AetherIIWoodTypes.SKYROOT_BLOCK_SET, properties), () -> Block.Properties.of().mapColor(SKYROOT_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).isValidSpawn(AetherIIBlocks::never).ignitedByLava());
    public static final TrapDoorBlock SECRET_SKYROOT_TRAPDOOR = register("secret_skyroot_trapdoor", (properties) -> new SecretTrapDoorBlock(AetherIIWoodTypes.SKYROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    // Greatroot Planks
    public static final Block GREATROOT_PLANKS = register("greatroot_planks", () -> Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_BROWN));
    public static final StairBlock GREATROOT_STAIRS = register("greatroot_stairs", (properties) -> new StairBlock(GREATROOT_PLANKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.GREATROOT_PLANKS));
    public static final SlabBlock GREATROOT_SLAB = register("greatroot_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.GREATROOT_PLANKS).strength(2.0F, 3.0F));
    public static final FenceBlock GREATROOT_FENCE = register("greatroot_fence", FenceBlock::new, () -> Block.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final FenceGateBlock GREATROOT_FENCE_GATE = register("greatroot_fence_gate", (properties) -> new FenceGateBlock(AetherIIWoodTypes.GREATROOT, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DoorBlock GREATROOT_DOOR = register("greatroot_door", (properties) -> new DoorBlock(AetherIIWoodTypes.GREATROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final TrapDoorBlock GREATROOT_TRAPDOOR = register("greatroot_trapdoor", (properties) -> new TrapDoorBlock(AetherIIWoodTypes.GREATROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
    public static final ButtonBlock GREATROOT_BUTTON = register("greatroot_button", (properties) -> new ButtonBlock(AetherIIWoodTypes.GREATROOT_BLOCK_SET, 30, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_BUTTON));
    public static final PressurePlateBlock GREATROOT_PRESSURE_PLATE = register("greatroot_pressure_plate", (properties) -> new PressurePlateBlock(AetherIIWoodTypes.GREATROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));
    public static final ShelfBlock GREATROOT_SHELF = register("greatroot_shelf", ShelfBlock::new, () -> Block.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.COLOR_BROWN));

    // Greatroot Decorative Blocks
    public static final Block GREATROOT_FLOORBOARDS = register("greatroot_floorboards", () -> Block.Properties.ofFullCopy(GREATROOT_PLANKS));
    public static final Block GREATROOT_HIGHLIGHT = register("greatroot_highlight", () -> Block.Properties.ofFullCopy(GREATROOT_PLANKS));
    public static final Block GREATROOT_SHINGLES = register("greatroot_shingles", HorizontalFacingBlock::new, () -> Block.Properties.ofFullCopy(GREATROOT_PLANKS));
    public static final Block GREATROOT_SMALL_SHINGLES = register("greatroot_small_shingles", HorizontalFacingBlock::new, () -> Block.Properties.ofFullCopy(GREATROOT_PLANKS));
    public static final Block GREATROOT_BASE_PLANKS = register("greatroot_base_planks", () -> Block.Properties.ofFullCopy(GREATROOT_PLANKS));
    public static final Block GREATROOT_TOP_PLANKS = register("greatroot_top_planks", () -> Block.Properties.ofFullCopy(GREATROOT_PLANKS));
    public static final FacingPillarBlock GREATROOT_BASE_BEAM = register("greatroot_base_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(GREATROOT_PLANKS));
    public static final FacingPillarBlock GREATROOT_TOP_BEAM = register("greatroot_top_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(GREATROOT_PLANKS));
    public static final FacingPillarBlock GREATROOT_BEAM = register("greatroot_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(GREATROOT_PLANKS));
    public static final DoorBlock SECRET_GREATROOT_DOOR = register("secret_greatroot_door", (properties) -> new SecretDoorBlock(AetherIIWoodTypes.GREATROOT_BLOCK_SET, properties), () -> Block.Properties.of().mapColor(GREATROOT_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().pushReaction(PushReaction.POPPED));
    public static final TrapDoorBlock SECRET_GREATROOT_TRAPDOOR = register("secret_greatroot_trapdoor", (properties) -> new SecretTrapDoorBlock(AetherIIWoodTypes.GREATROOT_BLOCK_SET, properties), () -> Block.Properties.of().mapColor(GREATROOT_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).isValidSpawn(AetherIIBlocks::never).ignitedByLava());

    // Wisproot Planks
    public static final Block WISPROOT_PLANKS = register("wisproot_planks", () -> Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.QUARTZ));
    public static final StairBlock WISPROOT_STAIRS = register("wisproot_stairs", (properties) -> new StairBlock(WISPROOT_PLANKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.WISPROOT_PLANKS));
    public static final SlabBlock WISPROOT_SLAB = register("wisproot_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.WISPROOT_PLANKS).strength(2.0F, 3.0F));
    public static final FenceBlock WISPROOT_FENCE = register("wisproot_fence", FenceBlock::new, () -> Block.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final FenceGateBlock WISPROOT_FENCE_GATE = register("wisproot_fence_gate", (properties) -> new FenceGateBlock(AetherIIWoodTypes.WISPROOT, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DoorBlock WISPROOT_DOOR = register("wisproot_door", (properties) -> new DoorBlock(AetherIIWoodTypes.WISPROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final TrapDoorBlock WISPROOT_TRAPDOOR = register("wisproot_trapdoor", (properties) -> new TrapDoorBlock(AetherIIWoodTypes.WISPROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
    public static final ButtonBlock WISPROOT_BUTTON = register("wisproot_button", (properties) -> new ButtonBlock(AetherIIWoodTypes.WISPROOT_BLOCK_SET, 30, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_BUTTON));
    public static final PressurePlateBlock WISPROOT_PRESSURE_PLATE = register("wisproot_pressure_plate", (properties) -> new PressurePlateBlock(AetherIIWoodTypes.WISPROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));
    public static final ShelfBlock WISPROOT_SHELF = register("wisproot_shelf", ShelfBlock::new, () -> Block.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.QUARTZ));

    // Wisproot Decorative Blocks
    public static final Block WISPROOT_FLOORBOARDS = register("wisproot_floorboards", () -> Block.Properties.ofFullCopy(WISPROOT_PLANKS));
    public static final Block WISPROOT_HIGHLIGHT = register("wisproot_highlight", () -> Block.Properties.ofFullCopy(WISPROOT_PLANKS));
    public static final Block WISPROOT_SHINGLES = register("wisproot_shingles", HorizontalFacingBlock::new, () -> Block.Properties.ofFullCopy(WISPROOT_PLANKS));
    public static final Block WISPROOT_SMALL_SHINGLES = register("wisproot_small_shingles", HorizontalFacingBlock::new, () -> Block.Properties.ofFullCopy(WISPROOT_PLANKS));
    public static final Block WISPROOT_BASE_PLANKS = register("wisproot_base_planks", () -> Block.Properties.ofFullCopy(WISPROOT_PLANKS));
    public static final Block WISPROOT_TOP_PLANKS = register("wisproot_top_planks", () -> Block.Properties.ofFullCopy(WISPROOT_PLANKS));
    public static final FacingPillarBlock WISPROOT_BASE_BEAM = register("wisproot_base_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(WISPROOT_PLANKS));
    public static final FacingPillarBlock WISPROOT_TOP_BEAM = register("wisproot_top_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(WISPROOT_PLANKS));
    public static final FacingPillarBlock WISPROOT_BEAM = register("wisproot_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(WISPROOT_PLANKS));
    public static final DoorBlock SECRET_WISPROOT_DOOR = register("secret_wisproot_door", (properties) -> new SecretDoorBlock(AetherIIWoodTypes.WISPROOT_BLOCK_SET, properties), () -> Block.Properties.of().mapColor(WISPROOT_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().pushReaction(PushReaction.POPPED));
    public static final TrapDoorBlock SECRET_WISPROOT_TRAPDOOR = register("secret_wisproot_trapdoor", (properties) -> new SecretTrapDoorBlock(AetherIIWoodTypes.WISPROOT_BLOCK_SET, properties), () -> Block.Properties.of().mapColor(WISPROOT_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).isValidSpawn(AetherIIBlocks::never).ignitedByLava());

    // Amberoot Planks
    public static final Block AMBEROOT_PLANKS = register("amberoot_planks", () -> Block.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final StairBlock AMBEROOT_STAIRS = register("amberoot_stairs", (properties) -> new StairBlock(AMBEROOT_PLANKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.AMBEROOT_PLANKS));
    public static final SlabBlock AMBEROOT_SLAB = register("amberoot_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.AMBEROOT_PLANKS).strength(2.0F, 3.0F));
    public static final FenceBlock AMBEROOT_FENCE = register("amberoot_fence", FenceBlock::new, () -> Block.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final FenceGateBlock AMBEROOT_FENCE_GATE = register("amberoot_fence_gate", (properties) -> new FenceGateBlock(AetherIIWoodTypes.AMBEROOT, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DoorBlock AMBEROOT_DOOR = register("amberoot_door", (properties) -> new DoorBlock(AetherIIWoodTypes.AMBEROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final TrapDoorBlock AMBEROOT_TRAPDOOR = register("amberoot_trapdoor", (properties) -> new TrapDoorBlock(AetherIIWoodTypes.AMBEROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
    public static final ButtonBlock AMBEROOT_BUTTON = register("amberoot_button", (properties) -> new ButtonBlock(AetherIIWoodTypes.AMBEROOT_BLOCK_SET, 30, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_BUTTON));
    public static final PressurePlateBlock AMBEROOT_PRESSURE_PLATE = register("amberoot_pressure_plate", (properties) -> new PressurePlateBlock(AetherIIWoodTypes.AMBEROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));
    public static final ShelfBlock AMBEROOT_SHELF = register("amberoot_shelf", ShelfBlock::new, () -> Block.Properties.ofFullCopy(Blocks.OAK_SHELF));

    // Amberoot Decorative Blocks
    public static final Block AMBEROOT_FLOORBOARDS = register("amberoot_floorboards", () -> Block.Properties.ofFullCopy(AMBEROOT_PLANKS));
    public static final Block AMBEROOT_HIGHLIGHT = register("amberoot_highlight", () -> Block.Properties.ofFullCopy(AMBEROOT_PLANKS));
    public static final Block AMBEROOT_SHINGLES = register("amberoot_shingles", HorizontalFacingBlock::new, () -> Block.Properties.ofFullCopy(AMBEROOT_PLANKS));
    public static final Block AMBEROOT_SMALL_SHINGLES = register("amberoot_small_shingles", HorizontalFacingBlock::new, () -> Block.Properties.ofFullCopy(AMBEROOT_PLANKS));
    public static final Block AMBEROOT_BASE_PLANKS = register("amberoot_base_planks", () -> Block.Properties.ofFullCopy(AMBEROOT_PLANKS));
    public static final Block AMBEROOT_TOP_PLANKS = register("amberoot_top_planks", () -> Block.Properties.ofFullCopy(AMBEROOT_PLANKS));
    public static final FacingPillarBlock AMBEROOT_BASE_BEAM = register("amberoot_base_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(AMBEROOT_PLANKS));
    public static final FacingPillarBlock AMBEROOT_TOP_BEAM = register("amberoot_top_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(AMBEROOT_PLANKS));
    public static final FacingPillarBlock AMBEROOT_BEAM = register("amberoot_beam", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(AMBEROOT_PLANKS));
    public static final DoorBlock SECRET_AMBEROOT_DOOR = register("secret_amberoot_door", (properties) -> new SecretDoorBlock(AetherIIWoodTypes.AMBEROOT_BLOCK_SET, properties), () -> Block.Properties.of().mapColor(AMBEROOT_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).isValidSpawn(AetherIIBlocks::never).ignitedByLava());
    public static final TrapDoorBlock SECRET_AMBEROOT_TRAPDOOR = register("secret_amberoot_trapdoor", (properties) -> new SecretTrapDoorBlock(AetherIIWoodTypes.AMBEROOT_BLOCK_SET, properties), () -> Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    // Holystone
    public static final StairBlock HOLYSTONE_STAIRS = register("holystone_stairs", (properties) -> new StairBlock(HOLYSTONE.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.HOLYSTONE));
    public static final SlabBlock HOLYSTONE_SLAB = register("holystone_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.HOLYSTONE).strength(2.0F, 6.0F));
    public static final WallBlock HOLYSTONE_WALL = register("holystone_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.HOLYSTONE).forceSolidOn());
    public static final ButtonBlock HOLYSTONE_BUTTON = register("holystone_button", (properties) -> new ButtonBlock(BlockSetType.STONE, 20, properties), () -> Block.Properties.ofFullCopy(Blocks.STONE_BUTTON));
    public static final PressurePlateBlock HOLYSTONE_PRESSURE_PLATE = register("holystone_pressure_plate", (properties) -> new PressurePlateBlock(BlockSetType.STONE, properties), () -> Block.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollision().strength(0.5F));

    // Mossy Holystone
    public static final StairBlock MOSSY_HOLYSTONE_STAIRS = register("mossy_holystone_stairs", (properties) -> new StairBlock(MOSSY_HOLYSTONE.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.MOSSY_HOLYSTONE));
    public static final SlabBlock MOSSY_HOLYSTONE_SLAB = register("mossy_holystone_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.MOSSY_HOLYSTONE).strength(2.0F, 6.0F));
    public static final WallBlock MOSSY_HOLYSTONE_WALL = register("mossy_holystone_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.MOSSY_HOLYSTONE).forceSolidOn());

    // Irradiated Holystone
    public static final StairBlock IRRADIATED_HOLYSTONE_STAIRS = register("irradiated_holystone_stairs", (properties) -> new StairBlock(IRRADIATED_HOLYSTONE.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.IRRADIATED_HOLYSTONE));
    public static final SlabBlock IRRADIATED_HOLYSTONE_SLAB = register("irradiated_holystone_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.IRRADIATED_HOLYSTONE).strength(2.0F, 6.0F));
    public static final WallBlock IRRADIATED_HOLYSTONE_WALL = register("irradiated_holystone_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.IRRADIATED_HOLYSTONE).forceSolidOn());

    // Holystone Bricks
    public static final Block HOLYSTONE_BRICKS = register("holystone_bricks", () -> Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F, 6.0F).requiresCorrectToolForDrops());
    public static final StairBlock HOLYSTONE_BRICK_STAIRS = register("holystone_brick_stairs", (properties) -> new StairBlock(HOLYSTONE_BRICKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.HOLYSTONE_BRICKS));
    public static final SlabBlock HOLYSTONE_BRICK_SLAB = register("holystone_brick_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.HOLYSTONE_BRICKS).strength(2.0F, 6.0F));
    public static final WallBlock HOLYSTONE_BRICK_WALL = register("holystone_brick_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.HOLYSTONE_BRICKS).forceSolidOn());

    // Holystone Decorative Blocks
    public static final Block HOLYSTONE_FLAGSTONES = register("holystone_flagstones", () -> Block.Properties.ofFullCopy(HOLYSTONE_BRICKS));
    public static final Block HOLYSTONE_HEADSTONE = register("holystone_headstone", () -> Block.Properties.ofFullCopy(HOLYSTONE_BRICKS));
    public static final Block HOLYSTONE_KEYSTONE = register("holystone_keystone", () -> Block.Properties.ofFullCopy(HOLYSTONE_BRICKS));
    public static final Block HOLYSTONE_BASE_BRICKS = register("holystone_base_bricks", () -> Block.Properties.ofFullCopy(HOLYSTONE_BRICKS));
    public static final Block HOLYSTONE_CAPSTONE_BRICKS = register("holystone_capstone_bricks", () -> Block.Properties.ofFullCopy(HOLYSTONE_BRICKS));
    public static final FacingPillarBlock HOLYSTONE_BASE_PILLAR = register("holystone_base_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(HOLYSTONE_BRICKS));
    public static final FacingPillarBlock HOLYSTONE_CAPSTONE_PILLAR = register("holystone_capstone_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(HOLYSTONE_BRICKS));
    public static final FacingPillarBlock HOLYSTONE_PILLAR = register("holystone_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(HOLYSTONE_BRICKS));

    // Faded Holystone Bricks
    public static final Block FADED_HOLYSTONE_BRICKS = register("faded_holystone_bricks", () -> Block.Properties.ofFullCopy(HOLYSTONE_BRICKS));
    public static final StairBlock FADED_HOLYSTONE_BRICK_STAIRS = register("faded_holystone_brick_stairs", (properties) -> new StairBlock(FADED_HOLYSTONE_BRICKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.FADED_HOLYSTONE_BRICKS));
    public static final SlabBlock FADED_HOLYSTONE_BRICK_SLAB = register("faded_holystone_brick_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.FADED_HOLYSTONE_BRICKS).strength(2.0F, 6.0F));
    public static final WallBlock FADED_HOLYSTONE_BRICK_WALL = register("faded_holystone_brick_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.FADED_HOLYSTONE_BRICKS).forceSolidOn());

    // Faded Holystone Decorative Blocks
    public static final Block FADED_HOLYSTONE_FLAGSTONES = register("faded_holystone_flagstones", () -> Block.Properties.ofFullCopy(FADED_HOLYSTONE_BRICKS));
    public static final Block FADED_HOLYSTONE_HEADSTONE = register("faded_holystone_headstone", () -> Block.Properties.ofFullCopy(FADED_HOLYSTONE_BRICKS));
    public static final Block FADED_HOLYSTONE_KEYSTONE = register("faded_holystone_keystone", () -> Block.Properties.ofFullCopy(FADED_HOLYSTONE_BRICKS));
    public static final Block FADED_HOLYSTONE_BASE_BRICKS = register("faded_holystone_base_bricks", () -> Block.Properties.ofFullCopy(FADED_HOLYSTONE_BRICKS));
    public static final Block FADED_HOLYSTONE_CAPSTONE_BRICKS = register("faded_holystone_capstone_bricks", () -> Block.Properties.ofFullCopy(FADED_HOLYSTONE_BRICKS));
    public static final FacingPillarBlock FADED_HOLYSTONE_BASE_PILLAR = register("faded_holystone_base_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(FADED_HOLYSTONE_BRICKS));
    public static final FacingPillarBlock FADED_HOLYSTONE_CAPSTONE_PILLAR = register("faded_holystone_capstone_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(FADED_HOLYSTONE_BRICKS));
    public static final FacingPillarBlock FADED_HOLYSTONE_PILLAR = register("faded_holystone_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(FADED_HOLYSTONE_BRICKS));

    // Undershale
    public static final StairBlock UNDERSHALE_STAIRS = register("undershale_stairs", (properties) -> new StairBlock(UNDERSHALE.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.UNDERSHALE));
    public static final SlabBlock UNDERSHALE_SLAB = register("undershale_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.UNDERSHALE));
    public static final WallBlock UNDERSHALE_WALL = register("undershale_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.UNDERSHALE).forceSolidOn());

    // Undershale Bricks
    public static final Block UNDERSHALE_BRICKS = register("undershale_bricks", () -> Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(4.0F, 6.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops());
    public static final StairBlock UNDERSHALE_BRICK_STAIRS = register("undershale_brick_stairs", (properties) -> new StairBlock(UNDERSHALE_BRICKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.UNDERSHALE_BRICKS));
    public static final SlabBlock UNDERSHALE_BRICK_SLAB = register("undershale_brick_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.UNDERSHALE_BRICKS));
    public static final WallBlock UNDERSHALE_BRICK_WALL = register("undershale_brick_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.UNDERSHALE_BRICKS).forceSolidOn());
    public static final ButtonBlock UNDERSHALE_BRICK_BUTTON = register("undershale_brick_button", (properties) -> new ButtonBlock(BlockSetType.STONE, 20, properties), () -> Block.Properties.ofFullCopy(Blocks.STONE_BUTTON).sound(SoundType.DEEPSLATE));
    public static final PlayerPressurePlateBlock UNDERSHALE_BRICK_PRESSURE_PLATE = register("undershale_brick_pressure_plate", (properties) -> new PlayerPressurePlateBlock(BlockSetType.STONE, properties), () -> Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.DEEPSLATE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollision().strength(0.5F));

    // Undershale Decorative Blocks
    public static final Block UNDERSHALE_FLAGSTONES = register("undershale_flagstones", () -> Block.Properties.ofFullCopy(UNDERSHALE_BRICKS));
    public static final Block UNDERSHALE_TILE = register("undershale_tile", () -> Block.Properties.ofFullCopy(UNDERSHALE_BRICKS));
    public static final Block UNDERSHALE_BASE_BRICKS = register("undershale_base_bricks", () -> Block.Properties.ofFullCopy(UNDERSHALE_BRICKS));
    public static final Block UNDERSHALE_CAPSTONE_BRICKS = register("undershale_capstone_bricks", () -> Block.Properties.ofFullCopy(UNDERSHALE_BRICKS));
    public static final FacingPillarBlock UNDERSHALE_BASE_PILLAR = register("undershale_base_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(UNDERSHALE_BRICKS));
    public static final FacingPillarBlock UNDERSHALE_CAPSTONE_PILLAR = register("undershale_capstone_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(UNDERSHALE_BRICKS));
    public static final FacingPillarBlock UNDERSHALE_PILLAR = register("undershale_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(UNDERSHALE_BRICKS));

    // Sentry Bricks
    public static final Block SENTRY_BRICKS = register("sentry_bricks", SentryBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(4.0F, 6.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().isRedstoneConductor(AetherIIBlockBuilders::never).lightLevel(AetherIIBlockBuilders::lightLevel6));
    public static final StairBlock SENTRY_BRICK_STAIRS = register("sentry_brick_stairs", (properties) -> new SentryStairBlock(SENTRY_BRICKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.SENTRY_BRICKS));
    public static final SlabBlock SENTRY_BRICK_SLAB = register("sentry_brick_slab", SentrySlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.SENTRY_BRICKS));
    public static final WallBlock SENTRY_BRICK_WALL = register("sentry_brick_wall", SentryWallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.SENTRY_BRICKS).forceSolidOn());
    public static final ButtonBlock SENTRY_BUTTON = register("sentry_button", (properties) -> new ButtonBlock(BlockSetType.STONE, 20, properties), () -> Block.Properties.ofFullCopy(Blocks.STONE_BUTTON).sound(SoundType.DEEPSLATE));

    // Sentry Decorative Blocks
    public static final Block SENTRY_LIGHTSTONE = register("sentry_lightstone", SentryBlock::new, () -> Block.Properties.ofFullCopy(SENTRY_BRICKS).lightLevel(AetherIIBlockBuilders::lightLevel11));
    public static final Block SENTRY_FLAGSTONES = register("sentry_flagstones", SentryBlock::new, () -> Block.Properties.ofFullCopy(SENTRY_BRICKS));
    public static final Block SENTRY_TILE = register("sentry_tile", SentryBlock::new, () -> Block.Properties.ofFullCopy(SENTRY_BRICKS));
    public static final Block SENTRY_BASE_BRICKS = register("sentry_base_bricks", SentryBlock::new, () -> Block.Properties.ofFullCopy(SENTRY_BRICKS));
    public static final Block SENTRY_CAPSTONE_BRICKS = register("sentry_capstone_bricks", SentryBlock::new, () -> Block.Properties.ofFullCopy(SENTRY_BRICKS));
    public static final FacingPillarBlock SENTRY_BASE_PILLAR = register("sentry_base_pillar", SentryFacingPillarBlock::new, () -> Block.Properties.ofFullCopy(SENTRY_BRICKS));
    public static final FacingPillarBlock SENTRY_CAPSTONE_PILLAR = register("sentry_capstone_pillar", SentryFacingPillarBlock::new, () -> Block.Properties.ofFullCopy(SENTRY_BRICKS));
    public static final FacingPillarBlock SENTRY_PILLAR = register("sentry_pillar", SentryFacingPillarBlock::new, () -> Block.Properties.ofFullCopy(SENTRY_BRICKS));

    // Ichorite
    public static final StairBlock ICHORITE_STAIRS = register("ichorite_stairs", (properties) -> new StairBlock(ICHORITE.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICHORITE));
    public static final SlabBlock ICHORITE_SLAB = register("ichorite_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICHORITE).strength(2.0F, 6.0F));
    public static final WallBlock ICHORITE_WALL = register("ichorite_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICHORITE).forceSolidOn());

    // Smooth Ichorite
    public static final Block SMOOTH_ICHORITE = register("smooth_ichorite", () -> Block.Properties.ofFullCopy(ICHORITE));
    public static final StairBlock SMOOTH_ICHORITE_STAIRS = register("smooth_ichorite_stairs", (properties) -> new StairBlock(SMOOTH_ICHORITE.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.SMOOTH_ICHORITE));
    public static final SlabBlock SMOOTH_ICHORITE_SLAB = register("smooth_ichorite_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.SMOOTH_ICHORITE).strength(2.0F, 6.0F));
    public static final WallBlock SMOOTH_ICHORITE_WALL = register("smooth_ichorite_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.SMOOTH_ICHORITE).forceSolidOn());

    // Ichorite Bricks
    public static final Block ICHORITE_BRICKS = register("ichorite_bricks", () -> Block.Properties.ofFullCopy(SMOOTH_ICHORITE));
    public static final StairBlock ICHORITE_BRICK_STAIRS = register("ichorite_brick_stairs", (properties) -> new StairBlock(ICHORITE_BRICKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.SMOOTH_ICHORITE));
    public static final SlabBlock ICHORITE_BRICK_SLAB = register("ichorite_brick_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICHORITE_BRICKS).strength(2.0F, 6.0F));
    public static final WallBlock ICHORITE_BRICK_WALL = register("ichorite_brick_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICHORITE_BRICKS).forceSolidOn());

    // Marbled Ichorite Decorative Blocks
    public static final Block ICHORITE_FLAGSTONES = register("ichorite_flagstones", () -> Block.Properties.ofFullCopy(ICHORITE_BRICKS));
    public static final Block ICHORITE_RUNESTONE = register("ichorite_runestone", () -> Block.Properties.ofFullCopy(ICHORITE_BRICKS));
    public static final Block ICHORITE_KEYSTONE = register("ichorite_keystone", () -> Block.Properties.ofFullCopy(ICHORITE_BRICKS));
    public static final Block ICHORITE_BASE_BRICKS = register("ichorite_base_bricks", () -> Block.Properties.ofFullCopy(ICHORITE_BRICKS));
    public static final Block ICHORITE_CAPSTONE_BRICKS = register("ichorite_capstone_bricks", () -> Block.Properties.ofFullCopy(ICHORITE_BRICKS));
    public static final FacingPillarBlock ICHORITE_BASE_PILLAR = register("ichorite_base_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(ICHORITE_BRICKS));
    public static final FacingPillarBlock ICHORITE_CAPSTONE_PILLAR = register("ichorite_capstone_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(ICHORITE_BRICKS));
    public static final FacingPillarBlock ICHORITE_PILLAR = register("ichorite_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(ICHORITE_BRICKS));

    // Marbled Ichorite
    public static final Block MARBLED_ICHORITE = register("marbled_ichorite", () -> Block.Properties.ofFullCopy(ICHORITE));
    public static final StairBlock MARBLED_ICHORITE_STAIRS = register("marbled_ichorite_stairs", (properties) -> new StairBlock(MARBLED_ICHORITE.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.MARBLED_ICHORITE));
    public static final SlabBlock MARBLED_ICHORITE_SLAB = register("marbled_ichorite_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.MARBLED_ICHORITE).strength(2.0F, 6.0F));
    public static final WallBlock MARBLED_ICHORITE_WALL = register("marbled_ichorite_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.MARBLED_ICHORITE).forceSolidOn());

    // Marbled Bricks
    public static final Block MARBLED_BRICKS = register("marbled_bricks", () -> Block.Properties.ofFullCopy(MARBLED_ICHORITE));
    public static final StairBlock MARBLED_BRICK_STAIRS = register("marbled_brick_stairs", (properties) -> new StairBlock(MARBLED_BRICKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.MARBLED_BRICKS));
    public static final SlabBlock MARBLED_BRICK_SLAB = register("marbled_brick_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.MARBLED_BRICKS).strength(2.0F, 6.0F));
    public static final WallBlock MARBLED_BRICK_WALL = register("marbled_brick_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.MARBLED_BRICKS).forceSolidOn());

    // Marbled Ichorite Decorative Blocks
    public static final Block MARBLED_FLAGSTONES = register("marbled_flagstones", () -> Block.Properties.ofFullCopy(MARBLED_BRICKS));
    public static final Block MARBLED_KEYSTONE = register("marbled_keystone", () -> Block.Properties.ofFullCopy(MARBLED_BRICKS));
    public static final Block MARBLED_BASE_BRICKS = register("marbled_base_bricks", () -> Block.Properties.ofFullCopy(MARBLED_BRICKS));
    public static final Block MARBLED_CAPSTONE_BRICKS = register("marbled_capstone_bricks", () -> Block.Properties.ofFullCopy(MARBLED_BRICKS));
    public static final FacingPillarBlock MARBLED_BASE_PILLAR = register("marbled_base_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(MARBLED_BRICKS));
    public static final FacingPillarBlock MARBLED_CAPSTONE_PILLAR = register("marbled_capstone_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(MARBLED_BRICKS));
    public static final FacingPillarBlock MARBLED_PILLAR = register("marbled_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(MARBLED_BRICKS));

    // Agiosite
    public static final StairBlock AGIOSITE_STAIRS = register("agiosite_stairs", (properties) -> new StairBlock(AGIOSITE.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.AGIOSITE));
    public static final SlabBlock AGIOSITE_SLAB = register("agiosite_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.AGIOSITE).strength(2.0F, 6.0F));
    public static final WallBlock AGIOSITE_WALL = register("agiosite_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.AGIOSITE).forceSolidOn());

    // Agiosite Bricks
    public static final Block AGIOSITE_BRICKS = register("agiosite_bricks", () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F, 6.0F).requiresCorrectToolForDrops());
    public static final StairBlock AGIOSITE_BRICK_STAIRS = register("agiosite_brick_stairs", (properties) -> new StairBlock(AGIOSITE_BRICKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.AGIOSITE_BRICKS));
    public static final SlabBlock AGIOSITE_BRICK_SLAB = register("agiosite_brick_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.AGIOSITE_BRICKS).strength(2.0F, 6.0F));
    public static final WallBlock AGIOSITE_BRICK_WALL = register("agiosite_brick_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.AGIOSITE_BRICKS).forceSolidOn());

    // Agiosite Decorative Blocks
    public static final Block AGIOSITE_FLAGSTONES = register("agiosite_flagstones", () -> Block.Properties.ofFullCopy(AGIOSITE_BRICKS));
    public static final Block AGIOSITE_KEYSTONE = register("agiosite_keystone", () -> Block.Properties.ofFullCopy(AGIOSITE_BRICKS));
    public static final Block AGIOSITE_BASE_BRICKS = register("agiosite_base_bricks", () -> Block.Properties.ofFullCopy(AGIOSITE_BRICKS));
    public static final Block AGIOSITE_CAPSTONE_BRICKS = register("agiosite_capstone_bricks", () -> Block.Properties.ofFullCopy(AGIOSITE_BRICKS));
    public static final FacingPillarBlock AGIOSITE_BASE_PILLAR = register("agiosite_base_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(AGIOSITE_BRICKS));
    public static final FacingPillarBlock AGIOSITE_CAPSTONE_PILLAR = register("agiosite_capstone_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(AGIOSITE_BRICKS));
    public static final FacingPillarBlock AGIOSITE_PILLAR = register("agiosite_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(AGIOSITE_BRICKS));

    // Icestone
    public static final StairBlock ICESTONE_STAIRS = register("icestone_stairs", (properties) -> new IcestoneStairsBlock(ICESTONE.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICESTONE));
    public static final SlabBlock ICESTONE_SLAB = register("icestone_slab", IcestoneSlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICESTONE).strength(0.5F, 6.0F));
    public static final WallBlock ICESTONE_WALL = register("icestone_wall", IcestoneWallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICESTONE).forceSolidOn());

    // Icestone Bricks
    public static final Block ICESTONE_BRICKS = register("icestone_bricks", () -> Block.Properties.of().mapColor(MapColor.ICE).sound(SoundType.GLASS).instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F, 6.0F).requiresCorrectToolForDrops());
    public static final StairBlock ICESTONE_BRICK_STAIRS = register("icestone_bricks_stairs", (properties) -> new StairBlock(ICESTONE_BRICKS.defaultBlockState(), properties), () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICESTONE_BRICKS));
    public static final SlabBlock ICESTONE_BRICK_SLAB = register("icestone_bricks_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICESTONE_BRICKS).strength(2.0F, 6.0F));
    public static final WallBlock ICESTONE_BRICK_WALL = register("icestone_bricks_wall", WallBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ICESTONE_BRICKS).forceSolidOn());

    // Icestone Decorative Blocks
    public static final Block ICESTONE_FLAGSTONES = register("icestone_flagstones", () -> Block.Properties.ofFullCopy(ICESTONE_BRICKS));
    public static final Block ICESTONE_KEYSTONE = register("icestone_keystone", () -> Block.Properties.ofFullCopy(ICESTONE_BRICKS));
    public static final Block ICESTONE_BASE_BRICKS = register("icestone_base_bricks", () -> Block.Properties.ofFullCopy(ICESTONE_BRICKS));
    public static final Block ICESTONE_CAPSTONE_BRICKS = register("icestone_capstone_bricks", () -> Block.Properties.ofFullCopy(ICESTONE_BRICKS));
    public static final FacingPillarBlock ICESTONE_BASE_PILLAR = register("icestone_base_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(ICESTONE_BRICKS));
    public static final FacingPillarBlock ICESTONE_CAPSTONE_PILLAR = register("icestone_capstone_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(ICESTONE_BRICKS));
    public static final FacingPillarBlock ICESTONE_PILLAR = register("icestone_pillar", FacingPillarBlock::new, () -> Block.Properties.ofFullCopy(ICESTONE_BRICKS));

    // Glass
    public static final TransparentBlock QUICKSOIL_GLASS = register("quicksoil_glass", QuicksoilGlassBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.HAT).strength(0.2F).friction(1.1F).lightLevel(AetherIIBlocks::lightLevel11).sound(SoundType.GLASS).noOcclusion().isValidSpawn(AetherIIBlocks::never).isRedstoneConductor(AetherIIBlocks::never).isSuffocating(AetherIIBlocks::never).isViewBlocking(AetherIIBlocks::never));
    public static final TransparentBlock TILED_QUICKSOIL_GLASS = register("tiled_quicksoil_glass", QuicksoilGlassBlock::new, () -> Block.Properties.ofFullCopy(QUICKSOIL_GLASS));
    public static final TransparentBlock GRIDDED_QUICKSOIL_GLASS = register("gridded_quicksoil_glass", QuicksoilGlassBlock::new, () -> Block.Properties.ofFullCopy(QUICKSOIL_GLASS));
    public static final HalfTransparentBlock SKYROOT_FRAMED_CRUDE_SCATTERGLASS = register("skyroot_framed_crude_scatterglass", CrudeScatterglassBlock::new, () -> Block.Properties.ofFullCopy(CRUDE_SCATTERGLASS));
    public static final HalfTransparentBlock ARKENIUM_FRAMED_CRUDE_SCATTERGLASS = register("arkenium_framed_crude_scatterglass", CrudeScatterglassBlock::new, () -> Block.Properties.ofFullCopy(CRUDE_SCATTERGLASS));
    public static final TransparentBlock SCATTERGLASS = register("scatterglass", ScatterglassBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().strength(0.2F).sound(SoundType.GLASS).requiresCorrectToolForDrops().isValidSpawn(AetherIIBlocks::never).isRedstoneConductor(AetherIIBlocks::never).isSuffocating(AetherIIBlocks::never).isViewBlocking(AetherIIBlocks::never));
    public static final TransparentBlock SKYROOT_FRAMED_SCATTERGLASS = register("skyroot_framed_scatterglass", ScatterglassBlock::new, () -> Block.Properties.ofFullCopy(SCATTERGLASS));
    public static final TransparentBlock ARKENIUM_FRAMED_SCATTERGLASS = register("arkenium_framed_scatterglass", ScatterglassBlock::new, () -> Block.Properties.ofFullCopy(SCATTERGLASS));

    // Glass Panes
    public static final IronBarsBlock QUICKSOIL_GLASS_PANE = register("quicksoil_glass_pane", QuicksoilGlassPaneBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.HAT).strength(0.2F).friction(1.1F).lightLevel(AetherIIBlocks::lightLevel11).sound(SoundType.GLASS).noOcclusion());
    public static final IronBarsBlock TILED_QUICKSOIL_GLASS_PANE = register("tiled_quicksoil_glass_pane", QuicksoilGlassPaneBlock::new, () -> Block.Properties.ofFullCopy(QUICKSOIL_GLASS_PANE));
    public static final IronBarsBlock GRIDDED_QUICKSOIL_GLASS_PANE = register("gridded_quicksoil_glass_pane", QuicksoilGlassPaneBlock::new, () -> Block.Properties.ofFullCopy(QUICKSOIL_GLASS_PANE));
    public static final IronBarsBlock CRUDE_SCATTERGLASS_PANE = register("crude_scatterglass_pane", CrudeScatterglassPaneBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F, 6.0F).sound(SoundType.GLASS).requiresCorrectToolForDrops().noOcclusion().isViewBlocking(AetherIIBlocks::never));
    public static final IronBarsBlock SKYROOT_FRAMED_CRUDE_SCATTERGLASS_PANE = register("skyroot_framed_crude_scatterglass_pane", CrudeScatterglassPaneBlock::new, () -> Block.Properties.ofFullCopy(CRUDE_SCATTERGLASS_PANE));
    public static final IronBarsBlock ARKENIUM_FRAMED_CRUDE_SCATTERGLASS_PANE = register("arkenium_framed_crude_scatterglass_pane", CrudeScatterglassPaneBlock::new, () -> Block.Properties.ofFullCopy(CRUDE_SCATTERGLASS_PANE));
    public static final IronBarsBlock SCATTERGLASS_PANE = register("scatterglass_pane", ScatterglassPaneBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().strength(0.2F).sound(SoundType.GLASS).requiresCorrectToolForDrops());
    public static final IronBarsBlock SKYROOT_FRAMED_SCATTERGLASS_PANE = register("skyroot_framed_scatterglass_pane", ScatterglassPaneBlock::new, () -> Block.Properties.ofFullCopy(SCATTERGLASS_PANE));
    public static final IronBarsBlock ARKENIUM_FRAMED_SCATTERGLASS_PANE = register("arkenium_framed_scatterglass_pane", ScatterglassPaneBlock::new, () -> Block.Properties.ofFullCopy(SCATTERGLASS_PANE));

    // Wool
    public static final Block CLOUDWOOL = register("cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.white()));
    public static final Block WHITE_CLOUDWOOL = register("white_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.white()));
    public static final Block ORANGE_CLOUDWOOL = register("orange_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.orange()));
    public static final Block MAGENTA_CLOUDWOOL = register("magenta_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.magenta()));
    public static final Block LIGHT_BLUE_CLOUDWOOL = register("light_blue_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.lightBlue()));
    public static final Block YELLOW_CLOUDWOOL = register("yellow_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.yellow()));
    public static final Block LIME_CLOUDWOOL = register("lime_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.lime()));
    public static final Block PINK_CLOUDWOOL = register("pink_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.pink()));
    public static final Block GRAY_CLOUDWOOL = register("gray_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.gray()));
    public static final Block LIGHT_GRAY_CLOUDWOOL = register("light_gray_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.lightGray()));
    public static final Block CYAN_CLOUDWOOL = register("cyan_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.cyan()));
    public static final Block PURPLE_CLOUDWOOL = register("purple_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.purple()));
    public static final Block BLUE_CLOUDWOOL = register("blue_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.blue()));
    public static final Block BROWN_CLOUDWOOL = register("brown_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.brown()));
    public static final Block GREEN_CLOUDWOOL = register("green_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.green()));
    public static final Block RED_CLOUDWOOL = register("red_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.red()));
    public static final Block BLACK_CLOUDWOOL = register("black_cloudwool", () -> Block.Properties.ofFullCopy(Blocks.WOOL.black()));

    // Carpet
    public static final CarpetBlock CLOUDWOOL_CARPET = register("cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.white()));
    public static final CarpetBlock WHITE_CLOUDWOOL_CARPET = register("white_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.white()));
    public static final CarpetBlock ORANGE_CLOUDWOOL_CARPET = register("orange_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.orange()));
    public static final CarpetBlock MAGENTA_CLOUDWOOL_CARPET = register("magenta_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.magenta()));
    public static final CarpetBlock LIGHT_BLUE_CLOUDWOOL_CARPET = register("light_blue_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.lightBlue()));
    public static final CarpetBlock YELLOW_CLOUDWOOL_CARPET = register("yellow_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.yellow()));
    public static final CarpetBlock LIME_CLOUDWOOL_CARPET = register("lime_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.lime()));
    public static final CarpetBlock PINK_CLOUDWOOL_CARPET = register("pink_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.pink()));
    public static final CarpetBlock GRAY_CLOUDWOOL_CARPET = register("gray_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.gray()));
    public static final CarpetBlock LIGHT_GRAY_CLOUDWOOL_CARPET = register("light_gray_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.lightGray()));
    public static final CarpetBlock CYAN_CLOUDWOOL_CARPET = register("cyan_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.cyan()));
    public static final CarpetBlock PURPLE_CLOUDWOOL_CARPET = register("purple_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.purple()));
    public static final CarpetBlock BLUE_CLOUDWOOL_CARPET = register("blue_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.blue()));
    public static final CarpetBlock BROWN_CLOUDWOOL_CARPET = register("brown_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.brown()));
    public static final CarpetBlock GREEN_CLOUDWOOL_CARPET = register("green_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.green()));
    public static final CarpetBlock RED_CLOUDWOOL_CARPET = register("red_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.red()));
    public static final CarpetBlock BLACK_CLOUDWOOL_CARPET = register("black_cloudwool_carpet", CarpetBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CARPET.black()));

    // Roofing
    public static final Block CLOUDWOOL_ROOFING = register("cloudwool_roofing", () -> BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).instrument(NoteBlockInstrument.GUITAR).strength(1.5F).sound(SoundType.WOOL).ignitedByLava());

    // Arkenium Blocks
    public static final DoorBlock ARKENIUM_DOOR = register("arkenium_door", (properties) -> new DoorBlock(BlockSetType.IRON, properties), () -> Block.Properties.ofFullCopy(Blocks.IRON_DOOR));
    public static final TrapDoorBlock ARKENIUM_TRAPDOOR = register("arkenium_trapdoor", (properties) -> new TrapDoorBlock(BlockSetType.IRON, properties), () -> Block.Properties.ofFullCopy(Blocks.IRON_TRAPDOOR));
    public static final IronBarsBlock ARKENIUM_BARS = register("arkenium_bars", IronBarsBlock::new, () -> Block.Properties.ofFullCopy(Blocks.IRON_BARS));
    public static final IronBarsBlock FLORAL_ARKENIUM_BARS = register("floral_arkenium_bars", IronBarsBlock::new, () -> Block.Properties.ofFullCopy(Blocks.IRON_BARS));
    public static final IronBarsBlock PATTERNED_ARKENIUM_BARS = register("patterned_arkenium_bars", IronBarsBlock::new, () -> Block.Properties.ofFullCopy(Blocks.IRON_BARS));
    public static final IronBarsBlock CURVED_ARKENIUM_BARS = register("curved_arkenium_bars", IronBarsBlock::new, () -> Block.Properties.ofFullCopy(Blocks.IRON_BARS));

    // Rustic Arkenium Blocks
    public static final IronBarsBlock RUSTIC_ARKENIUM_BARS = register("rustic_arkenium_bars", IronBarsBlock::new, () -> Block.Properties.ofFullCopy(Blocks.IRON_BARS));
    public static final IronBarsBlock RUSTIC_FLORAL_ARKENIUM_BARS = register("rustic_floral_arkenium_bars", IronBarsBlock::new, () -> Block.Properties.ofFullCopy(Blocks.IRON_BARS));
    public static final IronBarsBlock RUSTIC_PATTERNED_ARKENIUM_BARS = register("rustic_patterned_arkenium_bars", IronBarsBlock::new, () -> Block.Properties.ofFullCopy(Blocks.IRON_BARS));
    public static final IronBarsBlock RUSTIC_CURVED_ARKENIUM_BARS = register("rustic_curved_arkenium_bars", IronBarsBlock::new, () -> Block.Properties.ofFullCopy(Blocks.IRON_BARS));

    // Inert Mineral Blocks
    public static final Block INERT_ARKENIUM_BLOCK = register("inert_arkenium_block", () -> Block.Properties.of().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(5.0F, 6.0F).requiresCorrectToolForDrops());
    public static final Block INERT_GRAVITITE_BLOCK = register("inert_gravitite_block", () -> Block.Properties.of().mapColor(MapColor.COLOR_PINK).instrument(NoteBlockInstrument.BASEDRUM).strength(5.0F, 6.0F).requiresCorrectToolForDrops());

    // Mineral Blocks
    public static final Block AMBROSIUM_BLOCK = register("ambrosium_block", () -> Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel(AetherIIBlocks::lightLevel11));
    public static final Block ZANITE_BLOCK = register("zanite_block", () -> Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BIT).strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL));
    public static final Block ARKENIUM_BLOCK = register("arkenium_block", () -> Block.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.PLING).strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL));
    public static final Block GRAVITITE_BLOCK = register("gravitite_block", () -> Block.Properties.of().mapColor(MapColor.COLOR_PINK).instrument(NoteBlockInstrument.PLING).strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL));
    public static final Block GLINT_BLOCK = register("glint_block", () -> Block.Properties.of().mapColor(MapColor.COLOR_MAGENTA).instrument(NoteBlockInstrument.PLING).strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL));
    public static final Block CORROBONITE_BLOCK = register("corrobonite_block", () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BIT).strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL));
    public static final Block GOLDEN_AMBER_BLOCK = register("golden_amber_block", () -> Block.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BIT).strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL));

    // Storage Blocks
    public static final Block BRETTL_GRASS_BUNDLE = register("brettl_grass_bundle", HayBlock::new, () -> Block.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN));
    public static final Block GEL_BLOCK = register("gel_block", GelBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_CYAN).speedFactor(0.4F).jumpFactor(0.5F).noOcclusion().sound(SoundType.HONEY_BLOCK));

    // Arilum Lantern
    public static final Block WHITE_ARILUM_LANTERN = register("white_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.SNOW));
    public static final Block ORANGE_ARILUM_LANTERN = register("orange_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_ORANGE));
    public static final Block MAGENTA_ARILUM_LANTERN = register("magenta_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_MAGENTA));
    public static final Block LIGHT_BLUE_ARILUM_LANTERN = register("light_blue_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_LIGHT_BLUE));

    public static final Block YELLOW_ARILUM_LANTERN = register("yellow_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_YELLOW));
    public static final Block LIME_ARILUM_LANTERN = register("lime_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_LIGHT_GREEN));
    public static final Block PINK_ARILUM_LANTERN = register("pink_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_PINK));
    public static final Block GRAY_ARILUM_LANTERN = register("gray_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_GRAY));
    public static final Block LIGHT_GRAY_ARILUM_LANTERN = register("light_gray_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_LIGHT_GRAY));
    public static final Block CYAN_ARILUM_LANTERN = register("cyan_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_CYAN));
    public static final Block PURPLE_ARILUM_LANTERN = register("purple_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_PURPLE));
    public static final Block BLUE_ARILUM_LANTERN = register("blue_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_BLUE));
    public static final Block BROWN_ARILUM_LANTERN = register("brown_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_BROWN));
    public static final Block GREEN_ARILUM_LANTERN = register("green_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_GREEN));
    public static final Block RED_ARILUM_LANTERN = register("red_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_RED));
    public static final Block BLACK_ARILUM_LANTERN = register("black_arilum_lantern", HalfTransparentBlock::new, arilumLanternProperties(MapColor.COLOR_BLACK));


    // Utility
    public static final Block AMBROSIUM_TORCH = registerWithoutItem("ambrosium_torch", AmbrosiumTorchBlock::new, () -> Block.Properties.ofFullCopy(Blocks.TORCH));
    public static final Block AMBROSIUM_WALL_TORCH = registerWithoutItem("ambrosium_wall_torch", AmbrosiumWallTorchBlock::new, () -> Block.Properties.ofFullCopy(Blocks.WALL_TORCH));

    static {
        // Registration is eager (no DeferredRegister): the torch item needs the wall torch, which needs the torch's loot table
        registerItem("ambrosium_torch", AMBROSIUM_TORCH, torchItem(() -> AMBROSIUM_WALL_TORCH));
    }
    public static final Block ARKENIUM_LANTERN = register("arkenium_lantern", ArkeniumLanternBlock::new, () -> Block.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Block RUSTIC_ARKENIUM_LANTERN = register("rustic_arkenium_lantern", RusticArkeniumLanternBlock::new, () -> Block.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Block ARKENIUM_CHAIN = register("arkenium_chain", ChainBlock::new, () -> Block.Properties.ofFullCopy(Blocks.IRON_CHAIN));
    public static final Block SKYROOT_CRAFTING_TABLE = register("skyroot_crafting_table", SkyrootCraftingTableBlock::new, () -> Block.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
    public static final Block HOLYSTONE_FURNACE = register("holystone_furnace", HolystoneFurnaceBlock::new, () -> Block.Properties.ofFullCopy(Blocks.FURNACE));
    public static final Block HOLYSTONE_SMOKER = register("holystone_smoker", HolystoneSmokerBlock::new, () -> Block.Properties.ofFullCopy(Blocks.SMOKER));
    public static final Block AMBER_HOURGLASS = register("amber_hourglass", AmberHourglassBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.SKYROOT_PLANKS).noOcclusion());
    public static final Block ALTAR = register("altar", AltarBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.HOLYSTONE).noOcclusion());
    public static final Block ARTISANS_BENCH = register("artisans_bench", ArtisansBenchBlock::new, () -> Block.Properties.ofFullCopy(Blocks.STONECUTTER).noOcclusion());
    public static final Block ARKENIUM_FORGE = register("arkenium_forge", ArkeniumForgeBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ARKENIUM_BLOCK).noOcclusion().lightLevel(state -> state.getValue(ArkeniumForgeBlock.CHARGED) ? 8 : 0));
    public static final Block ALKAHEST_PURIFIER = register("alkahest_purifier", AlkahestPurifierBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ARKENIUM_BLOCK).noOcclusion());
    public static final Block MUSIC_BLOCK = register("music_block", MusicBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.SKYROOT_PLANKS));
    public static final CampfireBlock AMBROSIUM_CAMPFIRE = register("ambrosium_campfire", (properties) -> new AmbrosiumCampfireBlock(false, 1, properties), () -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).lightLevel((state) -> state.getValue(BlockStateProperties.LIT) ? 15 : 0).noOcclusion().ignitedByLava());
    public static final Block SKYROOT_CHEST = register("skyroot_chest", (properties) -> new SkyrootChestBlock(properties, () -> AetherIIBlockEntityTypes.SKYROOT_CHEST), () -> Block.Properties.ofFullCopy(Blocks.CHEST));
    public static final Block SKYROOT_BARREL = register("skyroot_barrel", BarrelBlock::new, () -> Block.Properties.ofFullCopy(Blocks.BARREL));
    public static final LadderBlock SKYROOT_LADDER = register("skyroot_ladder", LadderBlock::new, () -> Block.Properties.ofFullCopy(Blocks.LADDER).strength(0.4F).sound(SoundType.LADDER).noOcclusion());
    public static final BedrollBlock CLOUDWOOL_BEDROLL = register("cloudwool_bedroll", BedrollBlock::new, () -> Block.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.WOOL).strength(0.2F).noOcclusion().ignitedByLava().pushReaction(PushReaction.POPPED), AetherIIBlocks.bedrollBlockItem());

    public static final BedBlock SKYROOT_BED = register("skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.WHITE, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.white()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock WHITE_SKYROOT_BED = register("white_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.WHITE, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.white()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock ORANGE_SKYROOT_BED = register("orange_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.ORANGE, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.orange()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock MAGENTA_SKYROOT_BED = register("magenta_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.MAGENTA, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.magenta()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock LIGHT_BLUE_SKYROOT_BED = register("light_blue_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.LIGHT_BLUE, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.lightBlue()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock YELLOW_SKYROOT_BED = register("yellow_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.YELLOW, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.yellow()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock LIME_SKYROOT_BED = register("lime_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.LIME, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.lime()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock PINK_SKYROOT_BED = register("pink_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.PINK, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.pink()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock GRAY_SKYROOT_BED = register("gray_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.GRAY, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.gray()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock LIGHT_GRAY_SKYROOT_BED = register("light_gray_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.LIGHT_GRAY, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.lightGray()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock CYAN_SKYROOT_BED = register("cyan_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.CYAN, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.cyan()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock PURPLE_SKYROOT_BED = register("purple_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.PURPLE, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.purple()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock BLUE_SKYROOT_BED = register("blue_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.BLUE, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.blue()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock BROWN_SKYROOT_BED = register("brown_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.BROWN, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.brown()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock GREEN_SKYROOT_BED = register("green_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.GREEN, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.green()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock RED_SKYROOT_BED = register("red_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.RED, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.red()), AetherIIBlocks.bedBlockItem());
    public static final BedBlock BLACK_SKYROOT_BED = register("black_skyroot_bed", (properties) -> new SkyrootBedBlock(DyeColor.BLACK, properties), () -> Block.Properties.ofFullCopy(Blocks.BED.black()), AetherIIBlocks.bedBlockItem());

    private static Block.Properties skyrootSignProperties() {
        return Block.Properties.of().mapColor(MapColor.SAND).forceSolidOn().ignitedByLava().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).sound(SoundType.WOOD);
    }
    public static final WallSignBlock SKYROOT_WALL_SIGN = registerWithoutItem("skyroot_wall_sign", (properties) -> new WallSignBlock(AetherIIWoodTypes.SKYROOT, properties), AetherIIBlocks::skyrootSignProperties);
    public static final StandingSignBlock SKYROOT_SIGN = register("skyroot_sign", (properties) -> new StandingSignBlock(AetherIIWoodTypes.SKYROOT, properties), AetherIIBlocks::skyrootSignProperties, signItem(() -> AetherIIBlocks.SKYROOT_WALL_SIGN));

    private static Block.Properties skyrootHangingSignProperties() {
        return Block.Properties.of().mapColor(Blocks.OAK_LOG.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava();
    }
    public static final WallHangingSignBlock SKYROOT_WALL_HANGING_SIGN = registerWithoutItem("skyroot_wall_hanging_sign", (properties) -> new WallHangingSignBlock(AetherIIWoodTypes.SKYROOT, properties), AetherIIBlocks::skyrootHangingSignProperties);
    public static final CeilingHangingSignBlock SKYROOT_HANGING_SIGN = register("skyroot_hanging_sign", (properties) -> new CeilingHangingSignBlock(AetherIIWoodTypes.SKYROOT, properties), AetherIIBlocks::skyrootHangingSignProperties, hangingSignItem(() -> AetherIIBlocks.SKYROOT_WALL_HANGING_SIGN));

    private static Block.Properties greatrootSignProperties() { return skyrootSignProperties(); }
    public static final WallSignBlock GREATROOT_WALL_SIGN = registerWithoutItem("greatroot_wall_sign", (properties) -> new WallSignBlock(AetherIIWoodTypes.GREATROOT, properties), AetherIIBlocks::greatrootSignProperties);
    public static final StandingSignBlock GREATROOT_SIGN = register("greatroot_sign", (properties) -> new StandingSignBlock(AetherIIWoodTypes.GREATROOT, properties), AetherIIBlocks::greatrootSignProperties, signItem(() -> AetherIIBlocks.GREATROOT_WALL_SIGN));

    private static Block.Properties greatrootHangingSignProperties() { return skyrootHangingSignProperties(); }
    public static final WallHangingSignBlock GREATROOT_WALL_HANGING_SIGN = registerWithoutItem("greatroot_wall_hanging_sign", (properties) -> new WallHangingSignBlock(AetherIIWoodTypes.GREATROOT, properties), AetherIIBlocks::greatrootHangingSignProperties);
    public static final CeilingHangingSignBlock GREATROOT_HANGING_SIGN = register("greatroot_hanging_sign", (properties) -> new CeilingHangingSignBlock(AetherIIWoodTypes.GREATROOT, properties), AetherIIBlocks::greatrootHangingSignProperties, hangingSignItem(() -> AetherIIBlocks.GREATROOT_WALL_HANGING_SIGN));

    private static Block.Properties wisprootSignProperties() { return skyrootSignProperties(); }
    public static final WallSignBlock WISPROOT_WALL_SIGN = registerWithoutItem("wisproot_wall_sign", (properties) -> new WallSignBlock(AetherIIWoodTypes.WISPROOT, properties), AetherIIBlocks::wisprootSignProperties);
    public static final StandingSignBlock WISPROOT_SIGN = register("wisproot_sign", (properties) -> new StandingSignBlock(AetherIIWoodTypes.WISPROOT, properties), AetherIIBlocks::wisprootSignProperties, signItem(() -> AetherIIBlocks.WISPROOT_WALL_SIGN));

    private static Block.Properties wisprootHangingSignProperties() { return skyrootHangingSignProperties(); }
    public static final WallHangingSignBlock WISPROOT_WALL_HANGING_SIGN = registerWithoutItem("wisproot_wall_hanging_sign", (properties) -> new WallHangingSignBlock(AetherIIWoodTypes.WISPROOT, properties), AetherIIBlocks::wisprootHangingSignProperties);
    public static final CeilingHangingSignBlock WISPROOT_HANGING_SIGN = register("wisproot_hanging_sign", (properties) -> new CeilingHangingSignBlock(AetherIIWoodTypes.WISPROOT, properties), AetherIIBlocks::wisprootHangingSignProperties, hangingSignItem(() -> AetherIIBlocks.WISPROOT_WALL_HANGING_SIGN));

    private static Block.Properties amberootSignProperties() { return skyrootSignProperties(); }
    public static final WallSignBlock AMBEROOT_WALL_SIGN = registerWithoutItem("amberoot_wall_sign", (properties) -> new WallSignBlock(AetherIIWoodTypes.AMBEROOT, properties), AetherIIBlocks::wisprootSignProperties);
    public static final StandingSignBlock AMBEROOT_SIGN = register("amberoot_sign", (properties) -> new StandingSignBlock(AetherIIWoodTypes.AMBEROOT, properties), AetherIIBlocks::wisprootSignProperties, signItem(() -> AetherIIBlocks.AMBEROOT_WALL_SIGN));

    private static Block.Properties amberootHangingSignProperties() { return skyrootHangingSignProperties(); }
    public static final WallHangingSignBlock AMBEROOT_WALL_HANGING_SIGN = registerWithoutItem("amberoot_wall_hanging_sign", (properties) -> new WallHangingSignBlock(AetherIIWoodTypes.AMBEROOT, properties), AetherIIBlocks::wisprootHangingSignProperties);
    public static final CeilingHangingSignBlock AMBEROOT_HANGING_SIGN = register("amberoot_hanging_sign", (properties) -> new CeilingHangingSignBlock(AetherIIWoodTypes.AMBEROOT, properties), AetherIIBlocks::wisprootHangingSignProperties, hangingSignItem(() -> AetherIIBlocks.AMBEROOT_WALL_HANGING_SIGN));

    public static final Block HOLYSTONE_LEVER = register("holystone_lever", LeverBlock::new, () -> Block.Properties.ofFullCopy(Blocks.LEVER));

    public static final Block HOLYSTONE_VASE = register("holystone_vase", VaseBlock::new, () -> Block.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(MapColor.WOOL));
    public static final Block VERADEXIAN_VASE = register("veradexian_vase", VaseBlock::new, () -> Block.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(MapColor.QUARTZ));
    public static final Block BREXALLEN_VASE = register("brexallen_vase", VaseBlock::new, () -> Block.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(MapColor.COLOR_PURPLE));

    public static final Block SENTRY_CRATE = register("sentry_crate", SentryCrateBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F).sound(SoundType.STONE).lightLevel(state -> state.getValue(SentryCrateBlock.OPEN) ? 4 : 0).requiresCorrectToolForDrops());
    public static final Block SENTRY_SPAWNER = register("sentry_spawner", SentrySpawnerBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(5.0F).sound(SoundType.STONE).lightLevel(state -> state.getValue(SentrySpawnerBlock.SENTRY_SPAWNER_STATE) != AetherIIBlockStateProperties.SentrySpawnerState.INACTIVE ? 6 : 0));
    public static final Block SENTRY_TRAP = register("sentry_trap", SentryTrapBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(5.0F).sound(SoundType.STONE).lightLevel(state -> state.getValue(GroundTrapBlock.TRAP_STATE) == AetherIIBlockStateProperties.TrapState.SPAWNED ? 6 : 0));

    public static final Block LOCKED_BLOCK = register("locked_block", LockedBlock::new, () -> BlockBehaviour.Properties.of().strength(-1.0F, 3600000.8F).noLootTable().isValidSpawn(Blocks::always).pushReaction(PushReaction.IMMOVEABLE).lightLevel(CopyBlock::lightEmission), CopyBlockItem::new);
    public static final Block BOSS_DOORWAY_BLOCK = register("boss_doorway_block", BossDoorwayBlock::new, () -> BlockBehaviour.Properties.of().strength(-1.0F, 3600000.8F).noLootTable().isValidSpawn(Blocks::always).pushReaction(PushReaction.IMMOVEABLE).lightLevel(CopyBlock::lightEmission), CopyBlockItem::new);
    public static final Block TREASURE_DOORWAY_BLOCK = register("treasure_doorway_block", TreasureDoorwayBlock::new, () -> BlockBehaviour.Properties.of().strength(-1.0F, 3600000.8F).noLootTable().isValidSpawn(Blocks::always).pushReaction(PushReaction.IMMOVEABLE).lightLevel(CopyBlock::lightEmission), CopyBlockItem::new);

    // Bookshelves
    public static final Block SKYROOT_BOOKSHELF = register("skyroot_bookshelf", BookshelfBlock::new, () -> Block.Properties.ofFullCopy(SKYROOT_PLANKS));
    public static final Block GREATROOT_BOOKSHELF = register("greatroot_bookshelf", BookshelfBlock::new, () -> Block.Properties.ofFullCopy(GREATROOT_PLANKS));
    public static final Block WISPROOT_BOOKSHELF = register("wisproot_bookshelf", BookshelfBlock::new, () -> Block.Properties.ofFullCopy(WISPROOT_PLANKS));
    public static final Block AMBEROOT_BOOKSHELF = register("amberoot_bookshelf", BookshelfBlock::new, () -> Block.Properties.ofFullCopy(AMBEROOT_PLANKS));
    public static final Block HOLYSTONE_BOOKSHELF = register("holystone_bookshelf", BookshelfBlock::new, () -> Block.Properties.ofFullCopy(HOLYSTONE_BRICKS));

    // Furniture
    public static final OutpostCampfireBlock OUTPOST_CAMPFIRE = register("outpost_campfire", OutpostCampfireBlock::new, () -> Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(15.0F, 1200.0F).lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0).noOcclusion().pushReaction(PushReaction.IMMOVEABLE));
    public static final Block MURAL = register("mural", MuralBlock::new, () -> Block.Properties.ofFullCopy(Blocks.STONE), (block, properties) -> new BlockItem(block, properties.component(AetherIIDataComponents.MURAL_SECTION, MuralSection.DEFAULT)));

    // Melting Blocks
    public static final Block FROSTED_ICE = registerWithoutItem("frosted_ice", AetherFrostedIceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.ICE).friction(0.98F).randomTicks().strength(0.5F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((state, level, pos, entityType) -> entityType.builtInRegistryHolder().is(AetherIITags.EntityTypes.SPAWNING_ICE)).isRedstoneConductor(AetherIIBlockBuilders::never).noLootTable());
    public static final Block FROSTED_ARCTIC_ICE = registerWithoutItem("frosted_arctic_ice", AetherFrostedIceBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.ICE).friction(0.98F).randomTicks().strength(0.5F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((state, level, pos, entityType) -> entityType.builtInRegistryHolder().is(AetherIITags.EntityTypes.SPAWNING_ICE)).isRedstoneConductor(AetherIIBlockBuilders::never).noLootTable());
    public static final Block UNSTABLE_OBSIDIAN = registerWithoutItem("unstable_obsidian", UnstableObsidianBlock::new, () ->  BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).randomTicks().requiresCorrectToolForDrops().strength(50.0F, 1200.0F).noLootTable().pushReaction(PushReaction.PUSH_PULL));

    // Infected Guardian Tree
    // Guardian Wood
    public static final RotatedPillarBlock GUARDIAN_LOG = register("guardian_log", RotatedPillarBlock::new, logProperties(MapColor.COLOR_BROWN, MapColor.WOOD));
    public static final RotatedPillarBlock GUARDIAN_WOOD = register("guardian_wood", RotatedPillarBlock::new, logProperties(MapColor.COLOR_BROWN, MapColor.WOOD));
    public static final RotatedPillarBlock STRIPPED_GUARDIAN_LOG = register("stripped_guardian_log", RotatedPillarBlock::new, logProperties(MapColor.WOOD, MapColor.WOOD));
    public static final RotatedPillarBlock STRIPPED_GUARDIAN_WOOD = register("stripped_guardian_wood", RotatedPillarBlock::new, logProperties(MapColor.WOOD, MapColor.WOOD));

    // Infected Wood
    public static final RotatedPillarBlock INFECTED_LOG = register("infected_log", RotatedPillarBlock::new, logProperties(MapColor.TERRACOTTA_LIGHT_GRAY, MapColor.SAND));
    public static final RotatedPillarBlock INFECTED_WOOD = register("infected_wood", RotatedPillarBlock::new, logProperties(MapColor.TERRACOTTA_LIGHT_GRAY, MapColor.SAND));
    public static final RotatedPillarBlock STRIPPED_INFECTED_LOG = register("stripped_infected_log", RotatedPillarBlock::new, logProperties(MapColor.SAND, MapColor.SAND));
    public static final RotatedPillarBlock STRIPPED_INFECTED_WOOD = register("stripped_infected_wood", RotatedPillarBlock::new, logProperties(MapColor.SAND, MapColor.SAND));

    // Guardian Slabs
    public static final SlabBlock GUARDIAN_LOG_SLAB = register("guardian_log_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.GUARDIAN_LOG).mapColor(MapColor.WOOD));
    public static final SlabBlock GUARDIAN_WOOD_SLAB = register("guardian_wood_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.GUARDIAN_WOOD).mapColor(MapColor.COLOR_BROWN));
    public static final SlabBlock STRIPPED_GUARDIAN_LOG_SLAB = register("stripped_guardian_log_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.STRIPPED_GUARDIAN_LOG).mapColor(MapColor.WOOD));
    public static final SlabBlock STRIPPED_GUARDIAN_WOOD_SLAB = register("stripped_guardian_wood_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.STRIPPED_GUARDIAN_WOOD).mapColor(MapColor.WOOD));
    public static final SlabBlock INFECTED_LOG_SLAB = register("infected_log_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.GUARDIAN_LOG).mapColor(MapColor.SAND));
    public static final SlabBlock INFECTED_WOOD_SLAB = register("infected_wood_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.GUARDIAN_WOOD).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final SlabBlock STRIPPED_INFECTED_LOG_SLAB = register("stripped_infected_log_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.STRIPPED_GUARDIAN_LOG).mapColor(MapColor.SAND));
    public static final SlabBlock STRIPPED_INFECTED_WOOD_SLAB = register("stripped_infected_wood_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.STRIPPED_GUARDIAN_WOOD).mapColor(MapColor.SAND));

    // Guardian Trunks
    public static final TrunkBlock GUARDIAN_TRUNK = register("guardian_trunk", TrunkBlock::new, trunkProperties(MapColor.COLOR_BROWN));
    public static final TrunkBlock STRIPPED_GUARDIAN_TRUNK = register("stripped_guardian_trunk", TrunkBlock::new, trunkProperties(MapColor.WOOD));
    public static final TrunkBlock INFECTED_TRUNK = register("infected_trunk", TrunkBlock::new, trunkProperties(MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final TrunkBlock STRIPPED_INFECTED_TRUNK = register("stripped_infected_trunk", TrunkBlock::new, trunkProperties(MapColor.SAND));

    // Guardian Root Blocks
    public static final Block GUARDIAN_ROOTS = register("guardian_roots", Block::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.ROOTS));
    public static final Block UNSTABLE_GUARDIAN_ROOTS = register("unstable_guardian_roots", UnstableBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.ROOTS).ignitedByLava());
    public static final Block LUCENT_GUARDIAN_ROOTS = register("lucent_guardian_roots", Block::new, () -> Block.Properties.ofFullCopy(GUARDIAN_ROOTS).lightLevel((state) -> 7));
    public static final Block GUARDIAN_LAMP = register("guardian_lamp", Block::new, () -> Block.Properties.ofFullCopy(GUARDIAN_ROOTS).lightLevel((state) -> 10));

    // Undergrowth Blocks
    public static final Block UNDERGROWTH_LEAVES = register("undergrowth_leaves", () -> Block.Properties.of().strength(0.2F).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).sound(SoundType.AZALEA_LEAVES).noOcclusion().isSuffocating(AetherIIBlockBuilders::never).isViewBlocking(AetherIIBlockBuilders::never).isRedstoneConductor(AetherIIBlockBuilders::never).pushReaction(PushReaction.POPPED));
    public static final Block UNDERGROWTH_VINES = register("undergrowth_vines", BottomedVineBlock::new, () -> Block.Properties.ofFullCopy(Blocks.VINE).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN));
    public static final Block HANGING_UNDERGROWTH = register("hanging_undergrowth", HangingUndergrowthBlock::new, () -> Block.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).randomTicks().noCollision().instabreak().sound(SoundType.CAVE_VINES).pushReaction(PushReaction.POPPED));
    public static final Block HANGING_UNDERGROWTH_PLANT = registerWithoutItem("hanging_undergrowth_plant", HangingUndergrowthPlantBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.HANGING_UNDERGROWTH));

    // Rotshroom Blocks
    public static final Block ROTSHROOM_BLOCK = register("rotshroom_block", Block::new, () -> Block.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD));
    public static final SlabBlock ROTSHROOM_SLAB = register("rotshroom_slab", SlabBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ROTSHROOM_BLOCK).mapColor(MapColor.WOOD));
    public static final RotatedPillarBlock ROTSHROOM_STEM = register("rotshroom_stem", RotatedPillarBlock::new, () -> Block.Properties.ofFullCopy(AetherIIBlocks.ROTSHROOM_BLOCK).mapColor(MapColor.WOOL));
    public static final Block SHELF_ROTSHROOM_SLAB = register("shelf_rotshroom_slab", LargeShelfRotshroomBlock::new, () -> Block.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava());
    public static final Block ROTSHROOM = register("rotshroom", RotshroomBlock::new, () -> Block.Properties.of().mapColor(MapColor.DIRT).noCollision().noOcclusion().randomTicks().instabreak().offsetType(BlockBehaviour.OffsetType.XZ).dynamicShape().sound(SoundType.FUNGUS).postProcess(AetherIIBlocks::postProcessSelf).pushReaction(PushReaction.POPPED));
    public static final FlowerPotBlock POTTED_ROTSHROOM = registerWithoutItem("potted_rotshroom", (properties) -> new FlowerPotBlock(ROTSHROOM, properties), () -> Block.Properties.ofFullCopy(Blocks.FLOWER_POT));
    public static final Block ROTSHROOM_CLUSTER = register("rotshroom_cluster", RotshroomClusterBlock::new, () -> Block.Properties.of().mapColor(MapColor.DIRT).strength(0.1F).noOcclusion().randomTicks().instabreak().offsetType(BlockBehaviour.OffsetType.XZ).dynamicShape().sound(SoundType.FUNGUS).postProcess(AetherIIBlocks::postProcessSelf).pushReaction(PushReaction.POPPED));
    public static final Block ROTSHROOM_TOADSTOOL = register("rotshroom_toadstool", RotshroomToadstoolBlock::new, () -> Block.Properties.of().mapColor(MapColor.DIRT).strength(0.1F).offsetType(BlockBehaviour.OffsetType.XYZ).dynamicShape().noOcclusion().randomTicks().sound(SoundType.FUNGUS).postProcess(AetherIIBlocks::postProcessSelf).pushReaction(PushReaction.POPPED));
    public static final Block SHELF_ROTSHROOM = register("shelf_rotshroom", ShelfRotshroomBlock::new, () -> Block.Properties.of().mapColor(MapColor.DIRT).noCollision().randomTicks().instabreak().sound(SoundType.FUNGUS).postProcess(AetherIIBlocks::postProcessSelf).pushReaction(PushReaction.POPPED));
    public static final Block ROTGROWTH_VINES = register("rotgrowth_vines", BottomedVineBlock::new, () -> Block.Properties.ofFullCopy(Blocks.VINE).sound(SoundType.HANGING_ROOTS).mapColor(MapColor.DIRT).strength(-1.0F, 3600000.0F).noLootTable());

    // Dungeon Furniture
    public static final Block PRAYER_CANDLE = register("prayer_candle", PrayerCandleBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD).lightLevel((state) -> state.getValue(PrayerCandleBlock.LIT) ? 12 : 0));
    public static final Block GUARDIAN_PEW = register("guardian_pew", GuardianPewBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD));
    public static final Block GUARDIAN_DONATION_BOX = register("guardian_donation_box", GuardianDonationBoxBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD));
    public static final Block ABANDONED_BAG = register("abandoned_bag", AbandonedBagBlock::new, () -> Block.Properties.ofFullCopy(LIGHT_BLUE_CLOUDWOOL));
    public static final Block FUNGAL_CACHE = register("fungal_cache", FungalCacheBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD));
    public static final Block SAGE_CHEST = register("sage_chest", SageChestBlock::new, () -> Block.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.5F).sound(SoundType.WOOD));

    private static Block registerWithoutItem(String name, Supplier<Block.Properties> properties) {
        return registerWithoutItem(name, Block::new, properties);
    }

    private static <T extends Block> T registerWithoutItem(String name, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties) {
        var key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
        return Registry.register(BuiltInRegistries.BLOCK, key, builder.apply(properties.get().setId(key)));
    }

    private static Block register(String name, Supplier<Block.Properties> properties) {
        return register(name, Block::new, properties);
    }

    private static <T extends Block> T register(String name, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties) {
        return register(name, builder, properties, BlockItem::new);
    }

    private static <T extends Block> T register(String name, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties, BiFunction<? super T, Item.Properties, ? extends Item> itemCreator) {
        var key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
        T block = Registry.register(BuiltInRegistries.BLOCK, key, builder.apply(properties.get().setId(key)));
        var itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
        Registry.register(BuiltInRegistries.ITEM, itemKey, itemCreator.apply(
            block,
            new Item.Properties()
                .setId(itemKey)
                .useBlockDescriptionPrefix()
        ));
        return block;
    }

    private static <T extends Block> void registerItem(String name, T block, BiFunction<? super T, Item.Properties, ? extends Item> itemCreator) {
        var itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
        Registry.register(BuiltInRegistries.ITEM, itemKey, itemCreator.apply(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix()));
    }

    private static BiFunction<Block, Item.Properties, StandingAndWallBlockItem> torchItem(Supplier<? extends Block> wallTorch) {
        return standingAndWallBlockItem(wallTorch, Direction.DOWN);
    }

    private static BiFunction<Block, Item.Properties, StandingAndWallBlockItem> standingAndWallBlockItem(Supplier<? extends Block> wallBlock, Direction attachmentDirection) {
        Objects.requireNonNull(wallBlock);
        return (standingBlock, properties) -> new StandingAndWallBlockItem(standingBlock, Objects.requireNonNull(wallBlock.get()), attachmentDirection, properties);
    }

    private static BiFunction<BedrollBlock, Item.Properties, BlockItem> bedrollBlockItem() {
        return (bedrollBlock, properties) -> new BlockItem(bedrollBlock, properties.stacksTo(4));
    }

    private static BiFunction<BedBlock, Item.Properties, BlockItem> bedBlockItem() {
        return (bedBlock, properties) -> new BlockItem(bedBlock, properties.stacksTo(1));
    }

    private static BiFunction<StandingSignBlock, Item.Properties, StandingAndWallBlockItem> signItem(Supplier<? extends WallSignBlock> wallSignBlock) {
        Objects.requireNonNull(wallSignBlock);
        return (standingSignBlock, properties) -> new StandingAndWallBlockItem(standingSignBlock, Objects.requireNonNull(wallSignBlock.get()), Direction.DOWN, properties.stacksTo(16).signText());
    }

    private static BiFunction<CeilingHangingSignBlock, Item.Properties, HangingSignItem> hangingSignItem(Supplier<? extends WallHangingSignBlock> wallHangingSignBlock) {
        Objects.requireNonNull(wallHangingSignBlock);
        return (ceilingHangingSignBlock, properties) -> new HangingSignItem(ceilingHangingSignBlock, Objects.requireNonNull(wallHangingSignBlock.get()), properties.stacksTo(16));
    }

    private static ResourceKey<Block> createKey(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
    }

    public static void registerFlammability() {
        FireBlockAccessor fireBlockAccessor = (FireBlockAccessor) Blocks.FIRE;
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_LOG, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_WOOD, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_SKYROOT_LOG, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_SKYROOT_WOOD, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_TRUNK, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_SKYROOT_TRUNK, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_LOG, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_WOOD, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_GREATROOT_LOG, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_GREATROOT_WOOD, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_TRUNK, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_GREATROOT_TRUNK, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_LOG, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_WOOD, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.MOSSY_WISPROOT_LOG, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.MOSSY_WISPROOT_WOOD, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_WISPROOT_LOG, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_WISPROOT_WOOD, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_TRUNK, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_WISPROOT_TRUNK, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_LOG, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_DEPOSIT, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_WOOD, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_TRUNK, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_AMBEROOT_LOG, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_AMBEROOT_WOOD, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.STRIPPED_AMBEROOT_TRUNK, 5, 5);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_LEAF_PILE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYPLANE_LEAF_PILE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYBIRCH_LEAF_PILE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYPINE_LEAF_PILE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_LEAF_PILE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPTOP_LEAF_PILE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_LEAF_PILE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATOAK_LEAF_PILE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATBOA_LEAF_PILE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_LEAF_PILE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_LEAVES, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYPLANE_LEAVES, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYBIRCH_LEAVES, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYPINE_LEAVES, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_LEAVES, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPTOP_LEAVES, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_LEAVES, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATOAK_LEAVES, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATBOA_LEAVES, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_LEAVES, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SHORT_AETHER_GRASS, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.MEDIUM_AETHER_GRASS, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.TALL_AETHER_GRASS, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AETHER_FERN, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AETHER_BUSH, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.BLUEBERRY_BUSH, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.BLUEBERRY_BUSH_STEM, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.ORANGE_TREE, 60, 100);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_STAIRS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_SLAB, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_FENCE, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_FENCE_GATE, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_FLOORBOARDS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_HIGHLIGHT, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_SHINGLES, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_SMALL_SHINGLES, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_BASE_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_TOP_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_BASE_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_TOP_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_STAIRS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_SLAB, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_FENCE, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_FENCE_GATE, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_FLOORBOARDS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_HIGHLIGHT, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_SHINGLES, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_SMALL_SHINGLES, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_BASE_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_TOP_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_BASE_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_TOP_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_STAIRS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_SLAB, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_FENCE, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_FENCE_GATE, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_FLOORBOARDS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_HIGHLIGHT, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_SHINGLES, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_SMALL_SHINGLES, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_BASE_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_TOP_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_BASE_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_TOP_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_STAIRS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_SLAB, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_FENCE, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_FENCE_GATE, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_FLOORBOARDS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_HIGHLIGHT, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_SHINGLES, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_SMALL_SHINGLES, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_BASE_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_TOP_PLANKS, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_BASE_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_TOP_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_BEAM, 5, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.CLOUDWOOL, 30, 60);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.CLOUDWOOL_CARPET, 60, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_BOOKSHELF, 30, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_BOOKSHELF, 30, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_BOOKSHELF, 30, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_BOOKSHELF, 30, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.SKYROOT_SHELF, 30, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.GREATROOT_SHELF, 30, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.WISPROOT_SHELF, 30, 20);
        fireBlockAccessor.callSetFlammable(AetherIIBlocks.AMBEROOT_SHELF, 30, 20);
    }

    public static void registerWoodTypes() {
        // Classload AetherIIWoodTypes to ensure static registration via WoodTypeBuilder
        if (AetherIIWoodTypes.SKYROOT == null) {
            throw new IllegalStateException("AetherIIWoodTypes failed to initialize");
        }
    }

    /**
     * Path types that NeoForge exposed through block overrides ({@code getBlockPathType}/{@code getAdjacentBlockPathType}).
     */
    public static void registerPathTypes() {
        for (Block block : BuiltInRegistries.BLOCK) {
            if (block instanceof GelBlock) {
                LandPathTypeRegistry.register(block, PathType.STICKY_HONEY, null);
            } else if (block instanceof FullAetherBushBlock) {
                LandPathTypeRegistry.register(block, null, PathType.DAMAGING);
            }
        }
        // NeoForge FluidType#pathType(DAMAGE_CAUTIOUS) for alkahest
        LandPathTypeRegistry.register(ALKAHEST, PathType.DAMAGE_CAUTIOUS, null);
    }

    public static void registerStrippables() {
        BlockTransformerHelper.registerStripping(AetherIIBlocks.SKYROOT_LOG, AetherIIBlocks.STRIPPED_SKYROOT_LOG);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.SKYROOT_WOOD, AetherIIBlocks.STRIPPED_SKYROOT_WOOD);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.SKYROOT_TRUNK, AetherIIBlocks.STRIPPED_SKYROOT_TRUNK);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.GREATROOT_LOG, AetherIIBlocks.STRIPPED_GREATROOT_LOG);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.GREATROOT_WOOD, AetherIIBlocks.STRIPPED_GREATROOT_WOOD);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.GREATROOT_TRUNK, AetherIIBlocks.STRIPPED_GREATROOT_TRUNK);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.WISPROOT_LOG, AetherIIBlocks.STRIPPED_WISPROOT_LOG);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.WISPROOT_WOOD, AetherIIBlocks.STRIPPED_WISPROOT_WOOD);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.WISPROOT_TRUNK, AetherIIBlocks.STRIPPED_WISPROOT_TRUNK);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.MOSSY_WISPROOT_LOG, AetherIIBlocks.WISPROOT_LOG);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.MOSSY_WISPROOT_WOOD, AetherIIBlocks.WISPROOT_WOOD);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.MOSSY_WISPROOT_TRUNK, AetherIIBlocks.WISPROOT_TRUNK);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE, AetherIIBlocks.WISPROOT_LOG);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.AMBEROOT_LOG, AetherIIBlocks.STRIPPED_AMBEROOT_LOG);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.AMBEROOT_DEPOSIT, AetherIIBlocks.STRIPPED_AMBEROOT_LOG);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.AMBEROOT_WOOD, AetherIIBlocks.STRIPPED_AMBEROOT_WOOD);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.AMBEROOT_TRUNK, AetherIIBlocks.STRIPPED_AMBEROOT_TRUNK);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.GUARDIAN_LOG, AetherIIBlocks.STRIPPED_GUARDIAN_LOG);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.GUARDIAN_LOG_SLAB, AetherIIBlocks.STRIPPED_GUARDIAN_LOG_SLAB);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.GUARDIAN_WOOD, AetherIIBlocks.STRIPPED_GUARDIAN_WOOD);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.GUARDIAN_WOOD_SLAB, AetherIIBlocks.STRIPPED_GUARDIAN_WOOD_SLAB);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.GUARDIAN_TRUNK, AetherIIBlocks.STRIPPED_GUARDIAN_TRUNK);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.INFECTED_LOG, AetherIIBlocks.STRIPPED_INFECTED_LOG);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.INFECTED_LOG_SLAB, AetherIIBlocks.STRIPPED_INFECTED_LOG_SLAB);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.INFECTED_WOOD, AetherIIBlocks.STRIPPED_INFECTED_WOOD);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.INFECTED_WOOD_SLAB, AetherIIBlocks.STRIPPED_INFECTED_WOOD_SLAB);
        BlockTransformerHelper.registerStripping(AetherIIBlocks.INFECTED_TRUNK, AetherIIBlocks.STRIPPED_INFECTED_TRUNK);
    }

    public static void registerFlattenables() {
        BlockTransformerHelper.registerFlattening(AetherIIBlocks.AETHER_GRASS_BLOCK, AetherIIBlocks.AETHER_DIRT_PATH.defaultBlockState());
        BlockTransformerHelper.registerFlattening(AetherIIBlocks.AETHER_DIRT, AetherIIBlocks.AETHER_DIRT_PATH.defaultBlockState());
        BlockTransformerHelper.registerFlattening(AetherIIBlocks.COARSE_AETHER_DIRT, AetherIIBlocks.AETHER_DIRT_PATH.defaultBlockState());
    }

    public static void registerTillables() {
        BlockTransformerHelper.registerTilling(BlockPredicate.allOf(BlockPredicate.matchesBlocks(AetherIIBlocks.AETHER_DIRT), BlockPredicate.matchesTag(Vec3i.ZERO.above(), BlockTags.AIR)), AetherIIBlocks.AETHER_FARMLAND.defaultBlockState());
        BlockTransformerHelper.registerTilling(BlockPredicate.allOf(BlockPredicate.matchesBlocks(AetherIIBlocks.AETHER_GRASS_BLOCK), BlockPredicate.matchesTag(Vec3i.ZERO.above(), BlockTags.AIR)), AetherIIBlocks.AETHER_FARMLAND.defaultBlockState());
        BlockTransformerHelper.registerTilling(BlockPredicate.allOf(BlockPredicate.matchesBlocks(AetherIIBlocks.AETHER_DIRT_PATH), BlockPredicate.matchesTag(Vec3i.ZERO.above(), BlockTags.AIR)), AetherIIBlocks.AETHER_FARMLAND.defaultBlockState());
        BlockTransformerHelper.registerTilling(BlockPredicate.allOf(BlockPredicate.matchesBlocks(AetherIIBlocks.COARSE_AETHER_DIRT), BlockPredicate.matchesTag(Vec3i.ZERO.above(), BlockTags.AIR)), AetherIIBlocks.AETHER_DIRT.defaultBlockState());
        BlockTransformerHelper.registerTilling(BlockPredicate.allOf(BlockPredicate.matchesBlocks(AetherIIBlocks.MYCELIAL_AETHER_DIRT), BlockPredicate.matchesTag(Vec3i.ZERO.above(), BlockTags.AIR)), AetherIIBlocks.AETHER_DIRT.defaultBlockState());
    }

    public static void init() {
    }
}