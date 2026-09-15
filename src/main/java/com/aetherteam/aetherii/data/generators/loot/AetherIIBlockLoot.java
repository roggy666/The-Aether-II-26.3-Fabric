package com.aetherteam.aetherii.data.generators.loot;

import java.util.function.BiConsumer;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.BuiltInRegistries;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.data.providers.AetherIIBlockLootSubProvider;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.item.components.AetherIIDataComponents;
import com.aetherteam.aetherii.mixin.mixins.common.accessor.BlockLootAccessor;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class AetherIIBlockLoot extends AetherIIBlockLootSubProvider {
    protected static final float[] AMBEROOT_LEAVES_SAPLING_CHANCES = new float[]{0.0375F, 0.042F, 0.048F, 0.0615F, 0.1F};
    private static final Set<Item> EXPLOSION_RESISTANT = Set.of();

    public AetherIIBlockLoot(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        HolderGetter<Item> getter = this.registries.lookupOrThrow(Registries.ITEM);

        // Dirt
        this.add(AetherIIBlocks.AETHER_GRASS_BLOCK, block -> this.createSingleItemTableWithSilkTouch(block, AetherIIBlocks.AETHER_DIRT));
        this.add(AetherIIBlocks.ENCHANTED_AETHER_GRASS_BLOCK, block -> this.createSingleItemTableWithSilkTouch(block, AetherIIBlocks.AETHER_DIRT));
        this.dropOther(AetherIIBlocks.AETHER_DIRT_PATH, AetherIIBlocks.AETHER_DIRT);
        this.dropSelf(AetherIIBlocks.AETHER_DIRT);
        this.dropSelf(AetherIIBlocks.COARSE_AETHER_DIRT);
        this.dropSelf(AetherIIBlocks.MYCELIAL_AETHER_DIRT);
        this.dropOther(AetherIIBlocks.AETHER_FARMLAND, AetherIIBlocks.AETHER_DIRT);
        this.dropSelf(AetherIIBlocks.SHIMMERING_SILT);

        // Underground
        this.dropSelf(AetherIIBlocks.HOLYSTONE);
        this.dropWhenSilkTouch(AetherIIBlocks.UNSTABLE_HOLYSTONE);
        this.dropSelf(AetherIIBlocks.UNDERSHALE);
        this.dropWhenSilkTouch(AetherIIBlocks.UNSTABLE_UNDERSHALE);
        this.dropSelf(AetherIIBlocks.AGIOSITE);
        this.add(AetherIIBlocks.CRUDE_SCATTERGLASS, block -> this.createSingleItemTableWithSilkTouch(block, AetherIIItems.SCATTERGLASS_SHARD, UniformGenerator.between(1.0F, 2.0F)));
        this.add(AetherIIBlocks.SKY_ROOTS, this::createSkyRootsDrops);
        this.dropSelf(AetherIIBlocks.POINTED_HOLYSTONE);
        this.dropSelf(AetherIIBlocks.POINTED_ICHORITE);

        // Highfields
        this.dropSelf(AetherIIBlocks.QUICKSOIL);
        this.dropSelf(AetherIIBlocks.MOSSY_HOLYSTONE);
        this.dropSelf(AetherIIBlocks.BRYALINN_MOSS_BLOCK);
        this.dropSelf(AetherIIBlocks.BRYALINN_MOSS_CARPET);
        this.add(AetherIIBlocks.BRYALINN_MOSS_VINES, this::createShearsOnlyDrop);
        this.add(AetherIIBlocks.BRYALINN_MOSS_FLOWERS, this.createSegmentedBlockDrops(AetherIIBlocks.BRYALINN_MOSS_FLOWERS));
        this.dropSelf(AetherIIBlocks.TANGLED_BRANCHES);

        // Magnetic
        this.dropSelf(AetherIIBlocks.FERROSITE_SAND);
        this.dropSelf(AetherIIBlocks.FERROSITE_MUD);
        this.dropSelf(AetherIIBlocks.FERROSITE);
        this.dropSelf(AetherIIBlocks.RUSTED_FERROSITE);
        this.dropSelf(AetherIIBlocks.MAGNETIC_SHROOM);
        this.add(AetherIIBlocks.MAGNETIC_SHROOM_BLOCK, block -> this.createMushroomBlockDrop(block, AetherIIBlocks.MAGNETIC_SHROOM));
        this.add(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK, block -> this.createMushroomBlockDrop(block, AetherIIBlocks.MAGNETIC_SHROOM));
        this.add(AetherIIBlocks.MAGNETIC_SHROOM_STEM, block -> this.createMushroomBlockDrop(block, AetherIIBlocks.MAGNETIC_SHROOM));

        // Arctic
        this.add(AetherIIBlocks.ARCTIC_SNOW_BLOCK, block -> this.createSingleItemTableWithSilkTouch(block, AetherIIItems.ARCTIC_SNOWBALL, ConstantValue.exactly(4.0F)));
        this.add(AetherIIBlocks.ARCTIC_SNOW, this::droppingSnowLayer);
        this.dropWhenSilkTouch(AetherIIBlocks.ARCTIC_ICE);
        this.dropWhenSilkTouch(AetherIIBlocks.FRAGILE_ARCTIC_ICE);
        this.dropWhenSilkTouch(AetherIIBlocks.ARCTIC_PACKED_ICE);
        this.dropSelf(AetherIIBlocks.ICESTONE);
        this.dropWhenSilkTouch(AetherIIBlocks.LARGE_ARCTIC_ICE_CRYSTAL);
        this.dropWhenSilkTouch(AetherIIBlocks.MEDIUM_ARCTIC_ICE_CRYSTAL);
        this.dropWhenSilkTouch(AetherIIBlocks.SMALL_ARCTIC_ICE_CRYSTAL);
        this.dropSelf(AetherIIBlocks.SHAYELINN_MOSS_BLOCK);
        this.dropSelf(AetherIIBlocks.SHAYELINN_MOSS_CARPET);
        this.add(AetherIIBlocks.SHAYELINN_MOSS_VINES, this::createShearsOnlyDrop);

        // Irradiated
        this.dropSelf(AetherIIBlocks.IRRADIATED_HOLYSTONE);
        this.add(AetherIIBlocks.IRRADIATED_DUST_BLOCK, this::droppingIrradiatedDustLoot);
        this.dropSelf(AetherIIBlocks.AMBRELINN_MOSS_BLOCK);
        this.dropSelf(AetherIIBlocks.AMBRELINN_MOSS_CARPET);
        this.add(AetherIIBlocks.AMBRELINN_MOSS_VINES, this::createShearsOnlyDrop);
        this.add(AetherIIBlocks.TARAHESP_FLOWERS, this.createSegmentedBlockDrops(AetherIIBlocks.TARAHESP_FLOWERS));

        // Ores
        this.add(AetherIIBlocks.HOLYSTONE_QUARTZ_ORE, this::createQuartzOreDrops);
        this.dropWithFortune(AetherIIBlocks.AMBROSIUM_ORE, AetherIIItems.AMBROSIUM_SHARD);
        this.dropWithFortune(AetherIIBlocks.ZANITE_ORE, AetherIIItems.FOSSILIZED_ZANITE);
        this.dropWithFortune(AetherIIBlocks.GLINT_ORE, AetherIIItems.FOSSILIZED_GLINT);
        this.dropWithFortune(AetherIIBlocks.ARKENIUM_ORE, AetherIIItems.INERT_ARKENIUM);
        this.dropWithFortune(AetherIIBlocks.GRAVITITE_ORE, AetherIIItems.INERT_GRAVITITE);
        this.dropWithFortune(AetherIIBlocks.UNDERSHALE_AMBROSIUM_ORE, AetherIIItems.AMBROSIUM_SHARD);
        this.dropWithFortune(AetherIIBlocks.UNDERSHALE_ZANITE_ORE, AetherIIItems.FOSSILIZED_ZANITE);
        this.dropWithFortune(AetherIIBlocks.UNDERSHALE_GLINT_ORE, AetherIIItems.FOSSILIZED_GLINT);
        this.dropWithFortune(AetherIIBlocks.UNDERSHALE_ARKENIUM_ORE, AetherIIItems.INERT_ARKENIUM);
        this.dropWithFortune(AetherIIBlocks.UNDERSHALE_GRAVITITE_ORE, AetherIIItems.INERT_GRAVITITE);
        this.dropWithFortune(AetherIIBlocks.CORROBONITE_ORE, AetherIIItems.FOSSILIZED_CORROBONITE);
        this.dropNone(AetherIIBlocks.CORROBONITE_CLUSTER);

        // Aerclouds
        this.dropSelf(AetherIIBlocks.COLD_AERCLOUD);
        this.dropSelf(AetherIIBlocks.BLUE_AERCLOUD);
        this.dropSelf(AetherIIBlocks.GOLDEN_AERCLOUD);
        this.dropSelf(AetherIIBlocks.GREEN_AERCLOUD);
        this.dropSelf(AetherIIBlocks.PURPLE_AERCLOUD);
        this.dropSelf(AetherIIBlocks.STORM_AERCLOUD);

        // Nest Blocks
        this.dropSelf(AetherIIBlocks.WOVEN_SKYROOT_STICKS);
        this.dropSelf(AetherIIBlocks.ANIMAL_STASH);
        this.add(AetherIIBlocks.MOA_EGG, this::droppingMoaEgg);

        // Logs
        this.dropSelf(AetherIIBlocks.SKYROOT_LOG);
        this.dropSelf(AetherIIBlocks.STRIPPED_SKYROOT_LOG);
        this.dropSelf(AetherIIBlocks.GREATROOT_LOG);
        this.dropSelf(AetherIIBlocks.STRIPPED_GREATROOT_LOG);
        this.dropSelf(AetherIIBlocks.WISPROOT_LOG);
        this.dropSelf(AetherIIBlocks.STRIPPED_WISPROOT_LOG);
        this.add(AetherIIBlocks.MOSSY_WISPROOT_LOG, block -> this.createSingleItemTableWithSilkTouch(block, AetherIIBlocks.WISPROOT_LOG));
        this.add(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE, block -> this.createSingleItemTableWithSilkTouch(block, AetherIIBlocks.WISPROOT_LOG));
        this.add(AetherIIBlocks.MOSSY_WISPROOT_WOOD, block -> this.createSingleItemTableWithSilkTouch(block, AetherIIBlocks.WISPROOT_WOOD));
        this.dropSelf(AetherIIBlocks.AMBEROOT_LOG);
        this.dropSelf(AetherIIBlocks.STRIPPED_AMBEROOT_LOG);
        this.add(AetherIIBlocks.AMBEROOT_DEPOSIT, (wood) -> this.droppingAmberoot(getter, wood, AetherIIBlocks.AMBEROOT_LOG, AetherIIItems.GOLDEN_AMBER));
        this.dropSelf(AetherIIBlocks.SKYROOT_WOOD);
        this.dropSelf(AetherIIBlocks.STRIPPED_SKYROOT_WOOD);
        this.dropSelf(AetherIIBlocks.GREATROOT_WOOD);
        this.dropSelf(AetherIIBlocks.STRIPPED_GREATROOT_WOOD);
        this.dropSelf(AetherIIBlocks.WISPROOT_WOOD);
        this.dropSelf(AetherIIBlocks.STRIPPED_WISPROOT_WOOD);
        this.dropSelf(AetherIIBlocks.AMBEROOT_WOOD);
        this.dropSelf(AetherIIBlocks.STRIPPED_AMBEROOT_WOOD);

        // Trunks
        this.dropSelf(AetherIIBlocks.SKYROOT_TRUNK);
        this.dropSelf(AetherIIBlocks.STRIPPED_SKYROOT_TRUNK);
        this.dropSelf(AetherIIBlocks.GREATROOT_TRUNK);
        this.dropSelf(AetherIIBlocks.STRIPPED_GREATROOT_TRUNK);
        this.dropSelf(AetherIIBlocks.WISPROOT_TRUNK);
        this.add(AetherIIBlocks.MOSSY_WISPROOT_TRUNK, block -> this.createSingleItemTableWithSilkTouch(block, AetherIIBlocks.WISPROOT_TRUNK));
        this.dropSelf(AetherIIBlocks.STRIPPED_WISPROOT_TRUNK);
        this.dropSelf(AetherIIBlocks.AMBEROOT_TRUNK);
        this.dropSelf(AetherIIBlocks.STRIPPED_AMBEROOT_TRUNK);

        // Leaf Pile
        this.add(AetherIIBlocks.SKYROOT_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.SKYROOT_LEAVES));
        this.add(AetherIIBlocks.SKYPLANE_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.SKYPLANE_LEAVES));
        this.add(AetherIIBlocks.SKYBIRCH_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.SKYBIRCH_LEAVES));
        this.add(AetherIIBlocks.SKYPINE_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.SKYPINE_LEAVES));
        this.add(AetherIIBlocks.WISPROOT_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.WISPROOT_LEAVES));
        this.add(AetherIIBlocks.WISPTOP_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.WISPTOP_LEAVES));
        this.add(AetherIIBlocks.GREATROOT_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.GREATROOT_LEAVES));
        this.add(AetherIIBlocks.GREATOAK_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.GREATOAK_LEAVES));
        this.add(AetherIIBlocks.GREATBOA_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.GREATBOA_LEAVES));
        this.add(AetherIIBlocks.AMBEROOT_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.AMBEROOT_LEAVES));
        this.add(AetherIIBlocks.IRRADIATED_SKYROOT_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.IRRADIATED_SKYROOT_LEAVES));
        this.add(AetherIIBlocks.IRRADIATED_SKYPLANE_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.IRRADIATED_SKYPLANE_LEAVES));
        this.add(AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAVES));
        this.add(AetherIIBlocks.IRRADIATED_SKYPINE_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.IRRADIATED_SKYPINE_LEAVES));
        this.add(AetherIIBlocks.IRRADIATED_WISPROOT_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.IRRADIATED_WISPROOT_LEAVES));
        this.add(AetherIIBlocks.IRRADIATED_WISPTOP_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.IRRADIATED_WISPTOP_LEAVES));
        this.add(AetherIIBlocks.IRRADIATED_GREATROOT_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.IRRADIATED_GREATROOT_LEAVES));
        this.add(AetherIIBlocks.IRRADIATED_GREATOAK_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.IRRADIATED_GREATOAK_LEAVES));
        this.add(AetherIIBlocks.IRRADIATED_GREATBOA_LEAF_PILE, (block) -> this.droppingLeafPile(block, AetherIIBlocks.IRRADIATED_GREATBOA_LEAVES));

        // Leaves
        this.add(AetherIIBlocks.SKYROOT_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticksWithLizard(leaves, AetherIIBlocks.SKYROOT_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.SKYPLANE_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticksWithLizard(leaves, AetherIIBlocks.SKYPLANE_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.SKYBIRCH_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticksWithLizard(leaves, AetherIIBlocks.SKYBIRCH_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.SKYPINE_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticksWithLizard(leaves, AetherIIBlocks.SKYPINE_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.WISPROOT_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticksWithLizard(leaves, AetherIIBlocks.WISPROOT_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.WISPTOP_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticksWithLizard(leaves, AetherIIBlocks.WISPTOP_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.GREATROOT_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticksWithLizard(leaves, AetherIIBlocks.GREATROOT_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.GREATOAK_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticksWithLizard(leaves, AetherIIBlocks.GREATOAK_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.GREATBOA_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticksWithLizard(leaves, AetherIIBlocks.GREATBOA_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.AMBEROOT_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticksWithLizard(leaves, AetherIIBlocks.AMBEROOT_SAPLING, AMBEROOT_LEAVES_SAPLING_CHANCES));
        this.add(AetherIIBlocks.IRRADIATED_SKYROOT_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticks(leaves, AetherIIBlocks.SKYROOT_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances())); //TODO
        this.add(AetherIIBlocks.IRRADIATED_SKYPLANE_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticks(leaves, AetherIIBlocks.SKYPLANE_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticks(leaves, AetherIIBlocks.SKYBIRCH_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.IRRADIATED_SKYPINE_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticks(leaves, AetherIIBlocks.SKYPINE_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.IRRADIATED_WISPROOT_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticks(leaves, AetherIIBlocks.WISPROOT_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.IRRADIATED_WISPTOP_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticks(leaves, AetherIIBlocks.WISPTOP_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.IRRADIATED_GREATROOT_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticks(leaves, AetherIIBlocks.GREATROOT_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.IRRADIATED_GREATOAK_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticks(leaves, AetherIIBlocks.GREATOAK_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));
        this.add(AetherIIBlocks.IRRADIATED_GREATBOA_LEAVES, (leaves) -> this.droppingWithChancesAndSkyrootSticks(leaves, AetherIIBlocks.GREATBOA_SAPLING, BlockLootAccessor.aether_ii$getNormalLeavesSaplingChances()));

        // Saplings
        this.dropSelf(AetherIIBlocks.SKYROOT_SAPLING);
        this.dropSelf(AetherIIBlocks.SKYPLANE_SAPLING);
        this.dropSelf(AetherIIBlocks.SKYBIRCH_SAPLING);
        this.dropSelf(AetherIIBlocks.SKYPINE_SAPLING);
        this.dropSelf(AetherIIBlocks.WISPROOT_SAPLING);
        this.dropSelf(AetherIIBlocks.WISPTOP_SAPLING);
        this.dropSelf(AetherIIBlocks.GREATROOT_SAPLING);
        this.dropSelf(AetherIIBlocks.GREATOAK_SAPLING);
        this.dropSelf(AetherIIBlocks.GREATBOA_SAPLING);
        this.dropSelf(AetherIIBlocks.AMBEROOT_SAPLING);

        // Potted Saplings
        this.dropPottedContents(AetherIIBlocks.POTTED_SKYROOT_SAPLING);
        this.dropPottedContents(AetherIIBlocks.POTTED_SKYPLANE_SAPLING);
        this.dropPottedContents(AetherIIBlocks.POTTED_SKYBIRCH_SAPLING);
        this.dropPottedContents(AetherIIBlocks.POTTED_SKYPINE_SAPLING);
        this.dropPottedContents(AetherIIBlocks.POTTED_WISPROOT_SAPLING);
        this.dropPottedContents(AetherIIBlocks.POTTED_WISPTOP_SAPLING);
        this.dropPottedContents(AetherIIBlocks.POTTED_GREATROOT_SAPLING);
        this.dropPottedContents(AetherIIBlocks.POTTED_GREATOAK_SAPLING);
        this.dropPottedContents(AetherIIBlocks.POTTED_GREATBOA_SAPLING);
        this.dropPottedContents(AetherIIBlocks.POTTED_AMBEROOT_SAPLING);

        // Grasses
        this.add(AetherIIBlocks.SHORT_AETHER_GRASS, this::createShearsOnlyDrop);
        this.add(AetherIIBlocks.MEDIUM_AETHER_GRASS, this::createShearsOnlyDrop);
        this.add(AetherIIBlocks.TALL_AETHER_GRASS, this::createShearsOnlyDrop);
        this.add(AetherIIBlocks.AETHER_FERN, this::createShearsOnlyDrop);
        this.add(AetherIIBlocks.SHIELD_FERN, this::createShearsOnlyDrop);

        // Flowers
        this.dropSelf(AetherIIBlocks.HESPEROSE);
        this.dropSelf(AetherIIBlocks.TARABLOOM);
        this.dropSelf(AetherIIBlocks.POASPROUT);
        this.dropSelf(AetherIIBlocks.LILICHIME);
        this.dropSelf(AetherIIBlocks.PLURACIAN);
        this.add(AetherIIBlocks.SATIVAL_SHOOT, (shoot) -> this.droppingSativalShoot(getter, shoot, AetherIIItems.SATIVAL_BULB));
        this.add(AetherIIBlocks.HOLPUPEA, this.createSegmentedBlockDrops(AetherIIBlocks.HOLPUPEA));
        this.add(AetherIIBlocks.BLADE_POA, this::createShearsOnlyDrop);
        this.dropSelf(AetherIIBlocks.AECHOR_CUTTING);
        this.dropSelf(AetherIIBlocks.CARRION_CUTTING);

        // Potted Flowers
        this.dropPottedContents(AetherIIBlocks.POTTED_MAGNETIC_SHROOM);
        this.dropPottedContents(AetherIIBlocks.POTTED_AETHER_FERN);
        this.dropPottedContents(AetherIIBlocks.POTTED_SHIELD_FERN);
        this.dropPottedContents(AetherIIBlocks.POTTED_HESPEROSE);
        this.dropPottedContents(AetherIIBlocks.POTTED_TARABLOOM);
        this.dropPottedContents(AetherIIBlocks.POTTED_POASPROUT);
        this.dropPottedContents(AetherIIBlocks.POTTED_SATIVAL_SHOOT);
        this.dropPottedContents(AetherIIBlocks.POTTED_LILICHIME);
        this.dropPottedContents(AetherIIBlocks.POTTED_PLURACIAN);
        this.dropPottedContents(AetherIIBlocks.POTTED_BLADE_POA);
        this.dropPottedContents(AetherIIBlocks.POTTED_AECHOR_CUTTING);
        this.dropPottedContents(AetherIIBlocks.POTTED_CARRION_CUTTING);

        // Bushes
        this.dropSelf(AetherIIBlocks.AETHER_BUSH);
        this.add(AetherIIBlocks.BLUEBERRY_BUSH, (bush) -> this.droppingBerryBush(bush, AetherIIItems.BLUEBERRY));
        this.dropSelf(AetherIIBlocks.BLUEBERRY_BUSH_STEM);

        // Potted Bushes
        this.dropPottedContents(AetherIIBlocks.POTTED_AETHER_BUSH);
        this.dropPottedContents(AetherIIBlocks.POTTED_BLUEBERRY_BUSH);
        this.dropPottedContents(AetherIIBlocks.POTTED_BLUEBERRY_BUSH_STEM);

        // Orange Tree
        this.add(AetherIIBlocks.ORANGE_TREE, (tree) -> this.droppingOrangeTree(getter, tree, AetherIIItems.ORANGE));

        // Potted Orange Tree
        this.dropPottedContents(AetherIIBlocks.POTTED_ORANGE_TREE);

        // Valkyrie Sprout
        this.add(AetherIIBlocks.VALKYRIE_SPROUT, (sprout) -> this.droppingValkyrieSprout(sprout, AetherIIItems.VALKYRIE_WINGS));

        // Brettl
        this.add(AetherIIBlocks.BRETTL_PLANT, (brettl) -> this.droppingBrettlPlant(getter, brettl, AetherIIItems.BRETTL_CANE, AetherIIItems.BRETTL_GRASS));
        this.add(AetherIIBlocks.BRETTL_PLANT_TIP, (brettl) -> this.droppingBrettlPlantTip(getter, brettl, AetherIIItems.BRETTL_CANE, AetherIIBlocks.BRETTL_FLOWER));
        this.dropSelf(AetherIIBlocks.BRETTL_FLOWER);

        // Lake
        this.dropOther(AetherIIBlocks.ARILUM_SHOOT, AetherIIItems.ARILUM_BULBS);
        this.add(AetherIIBlocks.ARILUM, this::createSilkTouchOrShearsTable);
        this.add(AetherIIBlocks.ARILUM_PLANT, (plant) -> this.createSilkTouchOrShearsTable(AetherIIBlocks.ARILUM));
        this.add(AetherIIBlocks.BLOOMING_ARILUM, (plant) -> this.droppingArilumBulbs(getter, plant, AetherIIItems.ARILUM_BULBS));
        this.add(AetherIIBlocks.BLOOMING_ARILUM_PLANT, (plant) -> this.droppingArilumBulbs(getter, AetherIIBlocks.BLOOMING_ARILUM, AetherIIItems.ARILUM_BULBS));

        // Ground Decoration
        this.add(AetherIIBlocks.SKYROOT_TWIG, this::dropTwigs);
        this.add(AetherIIBlocks.HOLYSTONE_ROCK, this::dropRocks);

        // Skyroot Planks
        this.dropSelf(AetherIIBlocks.SKYROOT_PLANKS);
        this.dropSelf(AetherIIBlocks.SKYROOT_STAIRS);
        this.add(AetherIIBlocks.SKYROOT_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.SKYROOT_DOOR, createDoorTable(AetherIIBlocks.SKYROOT_DOOR));
        this.dropSelf(AetherIIBlocks.SKYROOT_TRAPDOOR);
        this.dropSelf(AetherIIBlocks.SKYROOT_FENCE);
        this.dropSelf(AetherIIBlocks.SKYROOT_FENCE_GATE);
        this.dropSelf(AetherIIBlocks.SKYROOT_BUTTON);
        this.dropSelf(AetherIIBlocks.SKYROOT_PRESSURE_PLATE);
        this.dropSelf(AetherIIBlocks.SKYROOT_SHELF);

        // Skyroot Decorative Blocks
        this.dropSelf(AetherIIBlocks.SKYROOT_FLOORBOARDS);
        this.dropSelf(AetherIIBlocks.SKYROOT_HIGHLIGHT);
        this.dropSelf(AetherIIBlocks.SKYROOT_SHINGLES);
        this.dropSelf(AetherIIBlocks.SKYROOT_SMALL_SHINGLES);
        this.dropSelf(AetherIIBlocks.SKYROOT_BASE_PLANKS);
        this.dropSelf(AetherIIBlocks.SKYROOT_TOP_PLANKS);
        this.dropSelf(AetherIIBlocks.SKYROOT_BASE_BEAM);
        this.dropSelf(AetherIIBlocks.SKYROOT_TOP_BEAM);
        this.dropSelf(AetherIIBlocks.SKYROOT_BEAM);
        this.add(AetherIIBlocks.SECRET_SKYROOT_DOOR, createDoorTable(AetherIIBlocks.SECRET_SKYROOT_DOOR));
        this.dropSelf(AetherIIBlocks.SECRET_SKYROOT_TRAPDOOR);

        // Greatroot Planks
        this.dropSelf(AetherIIBlocks.GREATROOT_PLANKS);
        this.dropSelf(AetherIIBlocks.GREATROOT_STAIRS);
        this.add(AetherIIBlocks.GREATROOT_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.GREATROOT_DOOR, createDoorTable(AetherIIBlocks.GREATROOT_DOOR));
        this.dropSelf(AetherIIBlocks.GREATROOT_TRAPDOOR);
        this.dropSelf(AetherIIBlocks.GREATROOT_FENCE);
        this.dropSelf(AetherIIBlocks.GREATROOT_FENCE_GATE);
        this.dropSelf(AetherIIBlocks.GREATROOT_BUTTON);
        this.dropSelf(AetherIIBlocks.GREATROOT_PRESSURE_PLATE);
        this.dropSelf(AetherIIBlocks.GREATROOT_SHELF);

        // Greatroot Decorative Blocks
        this.dropSelf(AetherIIBlocks.GREATROOT_FLOORBOARDS);
        this.dropSelf(AetherIIBlocks.GREATROOT_HIGHLIGHT);
        this.dropSelf(AetherIIBlocks.GREATROOT_SHINGLES);
        this.dropSelf(AetherIIBlocks.GREATROOT_SMALL_SHINGLES);
        this.dropSelf(AetherIIBlocks.GREATROOT_BASE_PLANKS);
        this.dropSelf(AetherIIBlocks.GREATROOT_TOP_PLANKS);
        this.dropSelf(AetherIIBlocks.GREATROOT_BASE_BEAM);
        this.dropSelf(AetherIIBlocks.GREATROOT_TOP_BEAM);
        this.dropSelf(AetherIIBlocks.GREATROOT_BEAM);
        this.add(AetherIIBlocks.SECRET_GREATROOT_DOOR, createDoorTable(AetherIIBlocks.SECRET_GREATROOT_DOOR));
        this.dropSelf(AetherIIBlocks.SECRET_GREATROOT_TRAPDOOR);

        // Wisproot Planks
        this.dropSelf(AetherIIBlocks.WISPROOT_PLANKS);
        this.dropSelf(AetherIIBlocks.WISPROOT_STAIRS);
        this.add(AetherIIBlocks.WISPROOT_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.WISPROOT_DOOR, createDoorTable(AetherIIBlocks.WISPROOT_DOOR));
        this.dropSelf(AetherIIBlocks.WISPROOT_TRAPDOOR);
        this.dropSelf(AetherIIBlocks.WISPROOT_FENCE);
        this.dropSelf(AetherIIBlocks.WISPROOT_FENCE_GATE);
        this.dropSelf(AetherIIBlocks.WISPROOT_BUTTON);
        this.dropSelf(AetherIIBlocks.WISPROOT_PRESSURE_PLATE);
        this.dropSelf(AetherIIBlocks.WISPROOT_SHELF);

        // Wisproot Decorative Blocks
        this.dropSelf(AetherIIBlocks.WISPROOT_FLOORBOARDS);
        this.dropSelf(AetherIIBlocks.WISPROOT_HIGHLIGHT);
        this.dropSelf(AetherIIBlocks.WISPROOT_SHINGLES);
        this.dropSelf(AetherIIBlocks.WISPROOT_SMALL_SHINGLES);
        this.dropSelf(AetherIIBlocks.WISPROOT_BASE_PLANKS);
        this.dropSelf(AetherIIBlocks.WISPROOT_TOP_PLANKS);
        this.dropSelf(AetherIIBlocks.WISPROOT_BASE_BEAM);
        this.dropSelf(AetherIIBlocks.WISPROOT_TOP_BEAM);
        this.dropSelf(AetherIIBlocks.WISPROOT_BEAM);
        this.add(AetherIIBlocks.SECRET_WISPROOT_DOOR, createDoorTable(AetherIIBlocks.SECRET_WISPROOT_DOOR));
        this.dropSelf(AetherIIBlocks.SECRET_WISPROOT_TRAPDOOR);

        // Amberoot Planks
        this.dropSelf(AetherIIBlocks.AMBEROOT_PLANKS);
        this.dropSelf(AetherIIBlocks.AMBEROOT_STAIRS);
        this.add(AetherIIBlocks.AMBEROOT_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.AMBEROOT_DOOR, createDoorTable(AetherIIBlocks.AMBEROOT_DOOR));
        this.dropSelf(AetherIIBlocks.AMBEROOT_TRAPDOOR);
        this.dropSelf(AetherIIBlocks.AMBEROOT_FENCE);
        this.dropSelf(AetherIIBlocks.AMBEROOT_FENCE_GATE);
        this.dropSelf(AetherIIBlocks.AMBEROOT_BUTTON);
        this.dropSelf(AetherIIBlocks.AMBEROOT_PRESSURE_PLATE);
        this.dropSelf(AetherIIBlocks.AMBEROOT_SHELF);

        // Amberoot Decorative Blocks
        this.dropSelf(AetherIIBlocks.AMBEROOT_FLOORBOARDS);
        this.dropSelf(AetherIIBlocks.AMBEROOT_HIGHLIGHT);
        this.dropSelf(AetherIIBlocks.AMBEROOT_SHINGLES);
        this.dropSelf(AetherIIBlocks.AMBEROOT_SMALL_SHINGLES);
        this.dropSelf(AetherIIBlocks.AMBEROOT_BASE_PLANKS);
        this.dropSelf(AetherIIBlocks.AMBEROOT_TOP_PLANKS);
        this.dropSelf(AetherIIBlocks.AMBEROOT_BASE_BEAM);
        this.dropSelf(AetherIIBlocks.AMBEROOT_TOP_BEAM);
        this.dropSelf(AetherIIBlocks.AMBEROOT_BEAM);
        this.add(AetherIIBlocks.SECRET_AMBEROOT_DOOR, createDoorTable(AetherIIBlocks.SECRET_AMBEROOT_DOOR));
        this.dropSelf(AetherIIBlocks.SECRET_AMBEROOT_TRAPDOOR);

        // Holystone
        this.dropSelf(AetherIIBlocks.HOLYSTONE_STAIRS);
        this.add(AetherIIBlocks.HOLYSTONE_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_WALL);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_BUTTON);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_PRESSURE_PLATE);

        // Mossy Holystone
        this.dropSelf(AetherIIBlocks.MOSSY_HOLYSTONE_STAIRS);
        this.add(AetherIIBlocks.MOSSY_HOLYSTONE_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.MOSSY_HOLYSTONE_WALL);

        // Irradiated Holystone
        this.dropSelf(AetherIIBlocks.IRRADIATED_HOLYSTONE_STAIRS);
        this.add(AetherIIBlocks.IRRADIATED_HOLYSTONE_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.IRRADIATED_HOLYSTONE_WALL);

        // Holystone Bricks
        this.dropSelf(AetherIIBlocks.HOLYSTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_BRICK_STAIRS);
        this.add(AetherIIBlocks.HOLYSTONE_BRICK_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_BRICK_WALL);

        // Holystone Decorative Blocks
        this.dropSelf(AetherIIBlocks.HOLYSTONE_FLAGSTONES);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_HEADSTONE);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_KEYSTONE);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_BASE_BRICKS);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_CAPSTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_BASE_PILLAR);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_CAPSTONE_PILLAR);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_PILLAR);

        // Faded Holystone Bricks
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_BRICK_STAIRS);
        this.add(AetherIIBlocks.FADED_HOLYSTONE_BRICK_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_BRICK_WALL);

        // Faded Holystone Decorative Blocks
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_FLAGSTONES);
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_HEADSTONE);
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE);
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_BASE_BRICKS);
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_BASE_PILLAR);
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_PILLAR);
        this.dropSelf(AetherIIBlocks.FADED_HOLYSTONE_PILLAR);

        // Undershale
        this.dropSelf(AetherIIBlocks.UNDERSHALE_STAIRS);
        this.add(AetherIIBlocks.UNDERSHALE_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_WALL);

        // Undershale Bricks
        this.dropSelf(AetherIIBlocks.UNDERSHALE_BRICKS);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_BRICK_STAIRS);
        this.add(AetherIIBlocks.UNDERSHALE_BRICK_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_BRICK_WALL);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_BRICK_BUTTON);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_BRICK_PRESSURE_PLATE);

        // Undershale Decorative Blocks
        this.dropSelf(AetherIIBlocks.UNDERSHALE_FLAGSTONES);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_TILE);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_BASE_BRICKS);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_CAPSTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_BASE_PILLAR);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_CAPSTONE_PILLAR);
        this.dropSelf(AetherIIBlocks.UNDERSHALE_PILLAR);

        // Sentry Bricks
        this.dropSelf(AetherIIBlocks.SENTRY_BRICKS);
        this.dropSelf(AetherIIBlocks.SENTRY_BRICK_STAIRS);
        this.add(AetherIIBlocks.SENTRY_BRICK_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.SENTRY_BRICK_WALL);
        this.dropSelf(AetherIIBlocks.SENTRY_BUTTON);

        // Sentry Decorative Blocks
        this.dropSelf(AetherIIBlocks.SENTRY_LIGHTSTONE);
        this.dropSelf(AetherIIBlocks.SENTRY_FLAGSTONES);
        this.dropSelf(AetherIIBlocks.SENTRY_TILE);
        this.dropSelf(AetherIIBlocks.SENTRY_BASE_BRICKS);
        this.dropSelf(AetherIIBlocks.SENTRY_CAPSTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.SENTRY_BASE_PILLAR);
        this.dropSelf(AetherIIBlocks.SENTRY_CAPSTONE_PILLAR);
        this.dropSelf(AetherIIBlocks.SENTRY_PILLAR);

        // Ichorite
        this.dropSelf(AetherIIBlocks.ICHORITE);
        this.dropSelf(AetherIIBlocks.ICHORITE_STAIRS);
        this.dropSelf(AetherIIBlocks.ICHORITE_SLAB);
        this.dropSelf(AetherIIBlocks.ICHORITE_WALL);

        // Smooth Ichorite
        this.dropSelf(AetherIIBlocks.SMOOTH_ICHORITE);
        this.dropSelf(AetherIIBlocks.SMOOTH_ICHORITE_STAIRS);
        this.dropSelf(AetherIIBlocks.SMOOTH_ICHORITE_SLAB);
        this.dropSelf(AetherIIBlocks.SMOOTH_ICHORITE_WALL);

        // Ichorite Bricks
        this.dropSelf(AetherIIBlocks.ICHORITE_BRICKS);
        this.dropSelf(AetherIIBlocks.ICHORITE_BRICK_STAIRS);
        this.dropSelf(AetherIIBlocks.ICHORITE_BRICK_SLAB);
        this.dropSelf(AetherIIBlocks.ICHORITE_BRICK_WALL);

        // Ichorite Decorative Blocks
        this.dropSelf(AetherIIBlocks.ICHORITE_FLAGSTONES);
        this.dropSelf(AetherIIBlocks.ICHORITE_RUNESTONE);
        this.dropSelf(AetherIIBlocks.ICHORITE_KEYSTONE);
        this.dropSelf(AetherIIBlocks.ICHORITE_BASE_BRICKS);
        this.dropSelf(AetherIIBlocks.ICHORITE_CAPSTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.ICHORITE_BASE_PILLAR);
        this.dropSelf(AetherIIBlocks.ICHORITE_CAPSTONE_PILLAR);
        this.dropSelf(AetherIIBlocks.ICHORITE_PILLAR);

        // Marbled Ichorite
        this.dropSelf(AetherIIBlocks.MARBLED_ICHORITE);
        this.dropSelf(AetherIIBlocks.MARBLED_ICHORITE_STAIRS);
        this.dropSelf(AetherIIBlocks.MARBLED_ICHORITE_SLAB);
        this.dropSelf(AetherIIBlocks.MARBLED_ICHORITE_WALL);

        // Marbled Bricks
        this.dropSelf(AetherIIBlocks.MARBLED_BRICKS);
        this.dropSelf(AetherIIBlocks.MARBLED_BRICK_STAIRS);
        this.dropSelf(AetherIIBlocks.MARBLED_BRICK_SLAB);
        this.dropSelf(AetherIIBlocks.MARBLED_BRICK_WALL);

        // Marbled Ichorite Decorative Blocks
        this.dropSelf(AetherIIBlocks.MARBLED_FLAGSTONES);
        this.dropSelf(AetherIIBlocks.MARBLED_KEYSTONE);
        this.dropSelf(AetherIIBlocks.MARBLED_BASE_BRICKS);
        this.dropSelf(AetherIIBlocks.MARBLED_CAPSTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.MARBLED_BASE_PILLAR);
        this.dropSelf(AetherIIBlocks.MARBLED_CAPSTONE_PILLAR);
        this.dropSelf(AetherIIBlocks.MARBLED_PILLAR);

        // Agiosite
        this.dropSelf(AetherIIBlocks.AGIOSITE_STAIRS);
        this.add(AetherIIBlocks.AGIOSITE_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.AGIOSITE_WALL);

        // Agiosite Bricks
        this.dropSelf(AetherIIBlocks.AGIOSITE_BRICKS);
        this.dropSelf(AetherIIBlocks.AGIOSITE_BRICK_STAIRS);
        this.add(AetherIIBlocks.AGIOSITE_BRICK_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.AGIOSITE_BRICK_WALL);

        // Agiosite Decorative Blocks
        this.dropSelf(AetherIIBlocks.AGIOSITE_FLAGSTONES);
        this.dropSelf(AetherIIBlocks.AGIOSITE_KEYSTONE);
        this.dropSelf(AetherIIBlocks.AGIOSITE_BASE_BRICKS);
        this.dropSelf(AetherIIBlocks.AGIOSITE_CAPSTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.AGIOSITE_BASE_PILLAR);
        this.dropSelf(AetherIIBlocks.AGIOSITE_CAPSTONE_PILLAR);
        this.dropSelf(AetherIIBlocks.AGIOSITE_PILLAR);

        // Icestone
        this.dropSelf(AetherIIBlocks.ICESTONE_STAIRS);
        this.add(AetherIIBlocks.ICESTONE_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.ICESTONE_WALL);

        // Icestone Bricks
        this.dropSelf(AetherIIBlocks.ICESTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.ICESTONE_BRICK_STAIRS);
        this.add(AetherIIBlocks.ICESTONE_BRICK_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.ICESTONE_BRICK_WALL);

        // Icestone Decorative Blocks
        this.dropSelf(AetherIIBlocks.ICESTONE_FLAGSTONES);
        this.dropSelf(AetherIIBlocks.ICESTONE_KEYSTONE);
        this.dropSelf(AetherIIBlocks.ICESTONE_BASE_BRICKS);
        this.dropSelf(AetherIIBlocks.ICESTONE_CAPSTONE_BRICKS);
        this.dropSelf(AetherIIBlocks.ICESTONE_BASE_PILLAR);
        this.dropSelf(AetherIIBlocks.ICESTONE_CAPSTONE_PILLAR);
        this.dropSelf(AetherIIBlocks.ICESTONE_PILLAR);

        // Glass
        this.dropWhenSilkTouch(AetherIIBlocks.QUICKSOIL_GLASS);
        this.dropWhenSilkTouch(AetherIIBlocks.TILED_QUICKSOIL_GLASS);
        this.dropWhenSilkTouch(AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS);
        this.dropWhenSilkTouch(AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS);
        this.dropWhenSilkTouch(AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS);
        this.dropSelf(AetherIIBlocks.SCATTERGLASS);
        this.dropWhenSilkTouch(AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS);
        this.dropWhenSilkTouch(AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS);

        // Glass Panes
        this.dropWhenSilkTouch(AetherIIBlocks.QUICKSOIL_GLASS_PANE);
        this.dropWhenSilkTouch(AetherIIBlocks.TILED_QUICKSOIL_GLASS_PANE);
        this.dropWhenSilkTouch(AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS_PANE);
        this.dropSelf(AetherIIBlocks.CRUDE_SCATTERGLASS_PANE);
        this.dropWhenSilkTouch(AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS_PANE);
        this.dropWhenSilkTouch(AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS_PANE);
        this.dropSelf(AetherIIBlocks.SCATTERGLASS_PANE);
        this.dropWhenSilkTouch(AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS_PANE);
        this.dropWhenSilkTouch(AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS_PANE);

        // Wool
        this.dropSelf(AetherIIBlocks.CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.WHITE_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.ORANGE_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.MAGENTA_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.YELLOW_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.LIME_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.PINK_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.GRAY_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.CYAN_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.PURPLE_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.BLUE_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.BROWN_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.GREEN_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.RED_CLOUDWOOL);
        this.dropSelf(AetherIIBlocks.BLACK_CLOUDWOOL);

        // Carpet
        this.dropSelf(AetherIIBlocks.CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.WHITE_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.ORANGE_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.MAGENTA_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.YELLOW_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.LIME_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.PINK_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.GRAY_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.CYAN_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.PURPLE_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.BLUE_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.BROWN_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.GREEN_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.RED_CLOUDWOOL_CARPET);
        this.dropSelf(AetherIIBlocks.BLACK_CLOUDWOOL_CARPET);

        // Roofing
        this.dropSelf(AetherIIBlocks.CLOUDWOOL_ROOFING);

        // Arkenium Blocks
        this.add(AetherIIBlocks.ARKENIUM_DOOR, createDoorTable(AetherIIBlocks.ARKENIUM_DOOR));
        this.dropSelf(AetherIIBlocks.ARKENIUM_TRAPDOOR);
        this.dropSelf(AetherIIBlocks.ARKENIUM_BARS);
        this.dropSelf(AetherIIBlocks.FLORAL_ARKENIUM_BARS);
        this.dropSelf(AetherIIBlocks.PATTERNED_ARKENIUM_BARS);
        this.dropSelf(AetherIIBlocks.CURVED_ARKENIUM_BARS);

        // Rustic Arkenium Blocks
        this.dropSelf(AetherIIBlocks.RUSTIC_ARKENIUM_BARS);
        this.dropSelf(AetherIIBlocks.RUSTIC_FLORAL_ARKENIUM_BARS);
        this.dropSelf(AetherIIBlocks.RUSTIC_PATTERNED_ARKENIUM_BARS);
        this.dropSelf(AetherIIBlocks.RUSTIC_CURVED_ARKENIUM_BARS);

        // Inert Blocks
        this.dropSelf(AetherIIBlocks.INERT_ARKENIUM_BLOCK);
        this.dropSelf(AetherIIBlocks.INERT_GRAVITITE_BLOCK);

        // Mineral Blocks
        this.dropSelf(AetherIIBlocks.AMBROSIUM_BLOCK);
        this.dropSelf(AetherIIBlocks.ZANITE_BLOCK);
        this.dropSelf(AetherIIBlocks.ARKENIUM_BLOCK);
        this.dropSelf(AetherIIBlocks.GRAVITITE_BLOCK);
        this.dropSelf(AetherIIBlocks.GLINT_BLOCK);
        this.dropSelf(AetherIIBlocks.CORROBONITE_BLOCK);
        this.dropSelf(AetherIIBlocks.GOLDEN_AMBER_BLOCK);

        // Storage Blocks
        this.dropSelf(AetherIIBlocks.BRETTL_GRASS_BUNDLE);
        this.dropSelf(AetherIIBlocks.GEL_BLOCK);

        // Arilum Lantern
        this.dropSelf(AetherIIBlocks.WHITE_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.ORANGE_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.MAGENTA_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.LIGHT_BLUE_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.YELLOW_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.LIME_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.PINK_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.GRAY_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.LIGHT_GRAY_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.CYAN_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.PURPLE_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.BLUE_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.BROWN_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.GREEN_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.RED_ARILUM_LANTERN);
        this.dropSelf(AetherIIBlocks.BLACK_ARILUM_LANTERN);

        // Utility
        this.dropSelf(AetherIIBlocks.AMBROSIUM_TORCH);
        this.dropSelf(AetherIIBlocks.ARKENIUM_LANTERN);
        this.dropSelf(AetherIIBlocks.RUSTIC_ARKENIUM_LANTERN);
        this.dropSelf(AetherIIBlocks.ARKENIUM_CHAIN);
        this.dropSelf(AetherIIBlocks.SKYROOT_CRAFTING_TABLE);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_FURNACE);
        this.dropSelf(AetherIIBlocks.HOLYSTONE_SMOKER);
        this.dropSelf(AetherIIBlocks.AMBER_HOURGLASS);
        this.dropSelf(AetherIIBlocks.ALTAR);
        this.dropSelf(AetherIIBlocks.ARKENIUM_FORGE);
        this.dropSelf(AetherIIBlocks.ARTISANS_BENCH);
        this.dropSelf(AetherIIBlocks.ALKAHEST_PURIFIER);
        this.dropSelf(AetherIIBlocks.MUSIC_BLOCK);
        this.add(AetherIIBlocks.AMBROSIUM_CAMPFIRE, (block) -> this.createSilkTouchDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(AetherIIItems.AMBROSIUM_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))));
        this.dropSelf(AetherIIBlocks.SKYROOT_CHEST);
        this.dropSelf(AetherIIBlocks.SKYROOT_BARREL);
        this.dropSelf(AetherIIBlocks.SKYROOT_LADDER);
        this.add(AetherIIBlocks.CLOUDWOOL_BEDROLL, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));

        this.add(AetherIIBlocks.SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.WHITE_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.ORANGE_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.MAGENTA_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.LIGHT_BLUE_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.YELLOW_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.LIME_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.PINK_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.GRAY_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.LIGHT_GRAY_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.CYAN_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.PURPLE_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.BLUE_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.BROWN_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.GREEN_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.RED_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
        this.add(AetherIIBlocks.BLACK_SKYROOT_BED, (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));

        this.dropWhenSilkTouch(AetherIIBlocks.HOLYSTONE_VASE);
        this.dropWhenSilkTouch(AetherIIBlocks.VERADEXIAN_VASE);
        this.dropWhenSilkTouch(AetherIIBlocks.BREXALLEN_VASE);

        this.dropSelf(AetherIIBlocks.SENTRY_CRATE);
        this.dropNone(AetherIIBlocks.SENTRY_SPAWNER);
        this.dropNone(AetherIIBlocks.SENTRY_TRAP);

        this.dropOther(AetherIIBlocks.SKYROOT_WALL_SIGN, AetherIIBlocks.SKYROOT_SIGN);
        this.dropSelf(AetherIIBlocks.SKYROOT_SIGN);

        this.dropOther(AetherIIBlocks.SKYROOT_WALL_HANGING_SIGN, AetherIIBlocks.SKYROOT_HANGING_SIGN);
        this.dropSelf(AetherIIBlocks.SKYROOT_HANGING_SIGN);

        this.dropOther(AetherIIBlocks.GREATROOT_WALL_SIGN, AetherIIBlocks.GREATROOT_SIGN);
        this.dropSelf(AetherIIBlocks.GREATROOT_SIGN);

        this.dropOther(AetherIIBlocks.GREATROOT_WALL_HANGING_SIGN, AetherIIBlocks.GREATROOT_HANGING_SIGN);
        this.dropSelf(AetherIIBlocks.GREATROOT_HANGING_SIGN);

        this.dropOther(AetherIIBlocks.WISPROOT_WALL_SIGN, AetherIIBlocks.WISPROOT_SIGN);
        this.dropSelf(AetherIIBlocks.WISPROOT_SIGN);

        this.dropOther(AetherIIBlocks.WISPROOT_WALL_HANGING_SIGN, AetherIIBlocks.WISPROOT_HANGING_SIGN);
        this.dropSelf(AetherIIBlocks.WISPROOT_HANGING_SIGN);

        this.dropOther(AetherIIBlocks.AMBEROOT_WALL_SIGN, AetherIIBlocks.AMBEROOT_SIGN);
        this.dropSelf(AetherIIBlocks.AMBEROOT_SIGN);

        this.dropOther(AetherIIBlocks.AMBEROOT_WALL_HANGING_SIGN, AetherIIBlocks.AMBEROOT_HANGING_SIGN);
        this.dropSelf(AetherIIBlocks.AMBEROOT_HANGING_SIGN);

        this.dropSelf(AetherIIBlocks.HOLYSTONE_LEVER);

        // Bookshelves
        this.add(AetherIIBlocks.SKYROOT_BOOKSHELF, (bookshelf) -> this.createSingleItemTableWithSilkTouch(bookshelf, Items.BOOK, ConstantValue.exactly(3)));
        this.add(AetherIIBlocks.GREATROOT_BOOKSHELF, (bookshelf) -> this.createSingleItemTableWithSilkTouch(bookshelf, Items.BOOK, ConstantValue.exactly(3)));
        this.add(AetherIIBlocks.WISPROOT_BOOKSHELF, (bookshelf) -> this.createSingleItemTableWithSilkTouch(bookshelf, Items.BOOK, ConstantValue.exactly(3)));
        this.add(AetherIIBlocks.AMBEROOT_BOOKSHELF, (bookshelf) -> this.createSingleItemTableWithSilkTouch(bookshelf, Items.BOOK, ConstantValue.exactly(3)));
        this.add(AetherIIBlocks.HOLYSTONE_BOOKSHELF, (bookshelf) -> this.createSingleItemTableWithSilkTouch(bookshelf, Items.BOOK, ConstantValue.exactly(3)));

        // Furniture
        this.dropNone(AetherIIBlocks.OUTPOST_CAMPFIRE);
        this.add(AetherIIBlocks.MURAL, (mural) -> LootTable.lootTable()
            .withPool(this.applyExplosionCondition(mural, LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(mural)))
                .apply(CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                    .include(AetherIIDataComponents.MURAL_SECTION)))
        );

        // Infected Guardian Tree
        // Guardian Wood
        this.dropSelf(AetherIIBlocks.GUARDIAN_LOG);
        this.dropSelf(AetherIIBlocks.GUARDIAN_WOOD);
        this.dropSelf(AetherIIBlocks.STRIPPED_GUARDIAN_LOG);
        this.dropSelf(AetherIIBlocks.STRIPPED_GUARDIAN_WOOD);

        // Infected Wood
        this.dropSelf(AetherIIBlocks.INFECTED_LOG);
        this.dropSelf(AetherIIBlocks.INFECTED_WOOD);
        this.dropSelf(AetherIIBlocks.STRIPPED_INFECTED_LOG);
        this.dropSelf(AetherIIBlocks.STRIPPED_INFECTED_WOOD);

        // Guardian Slabs
        this.add(AetherIIBlocks.GUARDIAN_LOG_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.GUARDIAN_WOOD_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.STRIPPED_GUARDIAN_LOG_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.STRIPPED_GUARDIAN_WOOD_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.INFECTED_LOG_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.INFECTED_WOOD_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.STRIPPED_INFECTED_LOG_SLAB, this::createSlabItemTable);
        this.add(AetherIIBlocks.STRIPPED_INFECTED_WOOD_SLAB, this::createSlabItemTable);

        // Guardian Trunks
        this.dropSelf(AetherIIBlocks.GUARDIAN_TRUNK);
        this.dropSelf(AetherIIBlocks.STRIPPED_GUARDIAN_TRUNK);
        this.dropSelf(AetherIIBlocks.INFECTED_TRUNK);
        this.dropSelf(AetherIIBlocks.STRIPPED_INFECTED_TRUNK);

        // Guardian Root Blocks
        this.dropSelf(AetherIIBlocks.GUARDIAN_ROOTS);
        this.dropWhenSilkTouch(AetherIIBlocks.UNSTABLE_GUARDIAN_ROOTS);
        this.dropSelf(AetherIIBlocks.LUCENT_GUARDIAN_ROOTS);
        this.dropSelf(AetherIIBlocks.GUARDIAN_LAMP);

        // Undergrowth Blocks
        this.dropWhenSilkTouch(AetherIIBlocks.UNDERGROWTH_LEAVES);
        this.dropWhenSilkTouch(AetherIIBlocks.UNDERGROWTH_VINES);
        this.dropWhenSilkTouch(AetherIIBlocks.HANGING_UNDERGROWTH);
        this.otherWhenSilkTouch(AetherIIBlocks.HANGING_UNDERGROWTH_PLANT, AetherIIBlocks.HANGING_UNDERGROWTH);

        // Rotshroom Blocks
        this.dropSelf(AetherIIBlocks.ROTSHROOM_BLOCK);
        this.add(AetherIIBlocks.ROTSHROOM_SLAB, this::createSlabItemTable);
        this.dropSelf(AetherIIBlocks.ROTSHROOM_STEM);
        this.dropSelf(AetherIIBlocks.SHELF_ROTSHROOM_SLAB);
        this.dropSelf(AetherIIBlocks.ROTSHROOM);
        this.dropPottedContents(AetherIIBlocks.POTTED_ROTSHROOM);
        this.dropSelf(AetherIIBlocks.ROTSHROOM_CLUSTER);
        this.dropSelf(AetherIIBlocks.ROTSHROOM_TOADSTOOL);
        this.dropSelf(AetherIIBlocks.SHELF_ROTSHROOM);

        // Dungeon Furniture
        this.dropSelf(AetherIIBlocks.PRAYER_CANDLE);
        this.dropSelf(AetherIIBlocks.GUARDIAN_PEW);
        this.dropSelf(AetherIIBlocks.GUARDIAN_DONATION_BOX);
        this.dropSelf(AetherIIBlocks.ABANDONED_BAG);
        this.dropSelf(AetherIIBlocks.FUNGAL_CACHE);
        this.dropSelf(AetherIIBlocks.SAGE_CHEST);
    }

    /**
     * Vanilla's {@code generate(BiConsumer)} walks every registered block and demands a table for each, so only the
     * tables added here are emitted (NeoForge scoped this through {@code getKnownBlocks}).
     */
    private final Map<ResourceKey<LootTable>, LootTable.Builder> tables = new LinkedHashMap<>();

    @Override
    public void add(Block block, LootTable.Builder builder) {
        ResourceKey<LootTable> key = block.getLootTable().orElseThrow(() -> new IllegalStateException("Block " + block + " does not have loot table"));
        if (this.tables.put(key, builder) != null) {
            throw new IllegalStateException("Duplicate loot table " + key.identifier() + " for " + BuiltInRegistries.BLOCK.getKey(block));
        }
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        this.generate();
        Set<ResourceKey<LootTable>> seen = new HashSet<>();
        for (Block block : BuiltInRegistries.BLOCK) {
            if (!BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(AetherII.MODID)) {
                continue;
            }
            block.getLootTable().ifPresent(table -> {
                if (seen.add(table)) {
                    LootTable.Builder builder = this.tables.remove(table);
                    if (builder == null) {
                        throw new IllegalStateException("Missing loottable " + table.identifier() + " for " + BuiltInRegistries.BLOCK.getKey(block));
                    }
                    output.accept(table, builder);
                }
            });
        }
        if (!this.tables.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.tables.keySet());
        }
    }
}