package com.aetherteam.aetherii.data.generators.models;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.client.renderer.item.model.VaseSpecialRenderer;
import com.aetherteam.aetherii.data.providers.AetherIIBlockModelSubProvider;
import com.aetherteam.aetherii.data.resources.builders.models.AetherIIBlockFamilies;
import com.aetherteam.aetherii.data.resources.builders.models.AetherIIModelTemplates;
import com.aetherteam.aetherii.data.resources.builders.models.AetherIITextureMappings;
import com.aetherteam.aetherii.data.resources.builders.models.AetherIITexturedModels;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class AetherIIBlockModels extends AetherIIBlockModelSubProvider {
    public AetherIIBlockModels(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        AetherIIBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateModel).forEach(this::generateFamily);

        // Portal
        this.createAetherPortalBlock();

        // Surface
        this.createAetherGrassBlocks();
        this.createTrivialCube(AetherIIBlocks.AETHER_DIRT);
        this.createTrivialCube(AetherIIBlocks.COARSE_AETHER_DIRT);
        this.createTrivialCube(AetherIIBlocks.MYCELIAL_AETHER_DIRT);
        this.createAetherFarmland();
        this.createTrivialCube(AetherIIBlocks.SHIMMERING_SILT);

        // Underground
        this.createTrivialCube(AetherIIBlocks.UNSTABLE_HOLYSTONE);
        this.createTrivialCube(AetherIIBlocks.UNSTABLE_UNDERSHALE);
        this.createSnowyCross(AetherIIBlocks.SKY_ROOTS);
        this.createTranslucentCubeInnerFaces(AetherIIBlocks.HESTVEIL);
        this.createPointedStone(AetherIIBlocks.POINTED_HOLYSTONE);
        this.createPointedStone(AetherIIBlocks.POINTED_ICHORITE);

        // Highfields
        this.createTrivialCube(AetherIIBlocks.QUICKSOIL);
        this.createFullAndCarpetBlocks(AetherIIBlocks.BRYALINN_MOSS_BLOCK, AetherIIBlocks.BRYALINN_MOSS_CARPET);
        this.createVine(AetherIIBlocks.BRYALINN_MOSS_VINES, AetherIIModelTemplates.MOSS_VINE);
        this.createCustomFlowerBed(AetherIIBlocks.BRYALINN_MOSS_FLOWERS,
                AetherIITexturedModels.BRYALINN_MOSS_FLOWERS_1.create(AetherIIBlocks.BRYALINN_MOSS_FLOWERS, this.modelOutput),
                AetherIITexturedModels.BRYALINN_MOSS_FLOWERS_2.create(AetherIIBlocks.BRYALINN_MOSS_FLOWERS, this.modelOutput),
                AetherIITexturedModels.BRYALINN_MOSS_FLOWERS_3.create(AetherIIBlocks.BRYALINN_MOSS_FLOWERS, this.modelOutput),
                AetherIITexturedModels.BRYALINN_MOSS_FLOWERS_4.create(AetherIIBlocks.BRYALINN_MOSS_FLOWERS, this.modelOutput));
        this.createCutoutMippedCube(AetherIIBlocks.TANGLED_BRANCHES);

        // Magnetic
        this.createTrivialCube(AetherIIBlocks.FERROSITE_SAND);
        this.createTrivialCube(AetherIIBlocks.FERROSITE_MUD);
        this.createTrivialCube(AetherIIBlocks.FERROSITE);
        this.createTrivialCube(AetherIIBlocks.RUSTED_FERROSITE);
        this.createMagneticShroom(AetherIIBlocks.MAGNETIC_SHROOM, AetherIIBlocks.POTTED_MAGNETIC_SHROOM);
        this.createMagneticShroomBlock(AetherIIBlocks.MAGNETIC_SHROOM_BLOCK);
        this.createMagneticShroomBlockEmissive(AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK);
        this.createMagneticShroomBlock(AetherIIBlocks.MAGNETIC_SHROOM_STEM);
        this.createMagneticShroomBlocksInside();

        // Arctic
        this.createArcticSnowBlocks();
        this.createTranslucentCube(AetherIIBlocks.ARCTIC_ICE);
        this.createTranslucentCube(AetherIIBlocks.FRAGILE_ARCTIC_ICE);
        this.createTrivialCube(AetherIIBlocks.ARCTIC_PACKED_ICE);
        this.createCrystal(AetherIIBlocks.LARGE_ARCTIC_ICE_CRYSTAL, AetherIIModelTemplates.FULL_CRYSTAL);
        this.createCrystal(AetherIIBlocks.MEDIUM_ARCTIC_ICE_CRYSTAL, AetherIIModelTemplates.FULL_CRYSTAL);
        this.createCrystal(AetherIIBlocks.SMALL_ARCTIC_ICE_CRYSTAL, AetherIIModelTemplates.LARGE_CRYSTAL);
        this.createFullAndCarpetBlocks(AetherIIBlocks.SHAYELINN_MOSS_BLOCK, AetherIIBlocks.SHAYELINN_MOSS_CARPET);
        this.createVine(AetherIIBlocks.SHAYELINN_MOSS_VINES, AetherIIModelTemplates.MOSS_VINE);
        this.createCustomFlowerBed(AetherIIBlocks.HOLPUPEA,
                AetherIITexturedModels.HOLPUPEA_1.create(AetherIIBlocks.HOLPUPEA, this.modelOutput),
                AetherIITexturedModels.HOLPUPEA_2.create(AetherIIBlocks.HOLPUPEA, this.modelOutput),
                AetherIITexturedModels.HOLPUPEA_3.create(AetherIIBlocks.HOLPUPEA, this.modelOutput),
                AetherIITexturedModels.HOLPUPEA_4.create(AetherIIBlocks.HOLPUPEA, this.modelOutput));

        // Irradiated
        this.createTrivialCube(AetherIIBlocks.IRRADIATED_DUST_BLOCK);
        this.createFullAndCarpetBlocks(AetherIIBlocks.AMBRELINN_MOSS_BLOCK, AetherIIBlocks.AMBRELINN_MOSS_CARPET);
        this.createVine(AetherIIBlocks.AMBRELINN_MOSS_VINES, AetherIIModelTemplates.AMBRELINN_MOSS_VINE);
        this.createCustomFlowerBed(AetherIIBlocks.TARAHESP_FLOWERS,
                AetherIITexturedModels.TARAHESP_FLOWERS_1.create(AetherIIBlocks.TARAHESP_FLOWERS, this.modelOutput),
                AetherIITexturedModels.TARAHESP_FLOWERS_2.create(AetherIIBlocks.TARAHESP_FLOWERS, this.modelOutput),
                AetherIITexturedModels.TARAHESP_FLOWERS_3.create(AetherIIBlocks.TARAHESP_FLOWERS, this.modelOutput),
                AetherIITexturedModels.TARAHESP_FLOWERS_4.create(AetherIIBlocks.TARAHESP_FLOWERS, this.modelOutput));

        // Ores
        this.createTrivialCube(AetherIIBlocks.HOLYSTONE_QUARTZ_ORE);
        this.createTrivialCube(AetherIIBlocks.AMBROSIUM_ORE);
        this.createTrivialCube(AetherIIBlocks.ZANITE_ORE);
        this.createTrivialCube(AetherIIBlocks.ARKENIUM_ORE);
        this.createTrivialCube(AetherIIBlocks.GRAVITITE_ORE);
        this.createTrivialCube(AetherIIBlocks.GLINT_ORE);
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_AMBROSIUM_ORE);
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_ZANITE_ORE);
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_ARKENIUM_ORE);
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_GRAVITITE_ORE);
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_GLINT_ORE);
        this.createTrivialCube(AetherIIBlocks.CORROBONITE_ORE);
        this.createCorroboniteCluster(AetherIIBlocks.CORROBONITE_CLUSTER, AetherIIModelTemplates.MEDIUM_CRYSTAL);

        // Aerclouds
        this.createAercloud(AetherIIBlocks.COLD_AERCLOUD);
        this.createAercloud(AetherIIBlocks.BLUE_AERCLOUD);
        this.createAercloud(AetherIIBlocks.GOLDEN_AERCLOUD);
        this.createAercloud(AetherIIBlocks.GREEN_AERCLOUD);
        this.createPurpleAercloud(AetherIIBlocks.PURPLE_AERCLOUD);
        this.createAercloud(AetherIIBlocks.STORM_AERCLOUD);

        // Nest Blocks
        this.createWovenSticks(AetherIIBlocks.WOVEN_SKYROOT_STICKS);
        this.createAnimalStash(AetherIIBlocks.ANIMAL_STASH, AetherIIBlocks.WOVEN_SKYROOT_STICKS);
        this.createMoaEgg(AetherIIBlocks.MOA_EGG);

        // Logs
        this.woodProvider(AetherIIBlocks.SKYROOT_LOG).logWithHorizontal(AetherIIBlocks.SKYROOT_LOG).wood(AetherIIBlocks.SKYROOT_WOOD);
        this.woodProvider(AetherIIBlocks.STRIPPED_SKYROOT_LOG).logWithHorizontal(AetherIIBlocks.STRIPPED_SKYROOT_LOG).wood(AetherIIBlocks.STRIPPED_SKYROOT_WOOD);
        this.woodProvider(AetherIIBlocks.GREATROOT_LOG).logWithHorizontal(AetherIIBlocks.GREATROOT_LOG).wood(AetherIIBlocks.GREATROOT_WOOD);
        this.woodProvider(AetherIIBlocks.STRIPPED_GREATROOT_LOG).logWithHorizontal(AetherIIBlocks.STRIPPED_GREATROOT_LOG).wood(AetherIIBlocks.STRIPPED_GREATROOT_WOOD);
        this.woodProvider(AetherIIBlocks.WISPROOT_LOG).logWithHorizontal(AetherIIBlocks.WISPROOT_LOG).wood(AetherIIBlocks.WISPROOT_WOOD);
        this.woodProvider(AetherIIBlocks.STRIPPED_WISPROOT_LOG).logWithHorizontal(AetherIIBlocks.STRIPPED_WISPROOT_LOG).wood(AetherIIBlocks.STRIPPED_WISPROOT_WOOD);
        this.woodProvider(AetherIIBlocks.MOSSY_WISPROOT_LOG).logWithHorizontal(AetherIIBlocks.MOSSY_WISPROOT_LOG).wood(AetherIIBlocks.MOSSY_WISPROOT_WOOD);
        this.createFacingTopBottomColumnWithHorizontalVariant(AetherIIBlocks.MOSSY_WISPROOT_LOG_BASE, AetherIIBlocks.WISPROOT_LOG, AetherIIBlocks.MOSSY_WISPROOT_LOG);
        this.woodProvider(AetherIIBlocks.AMBEROOT_LOG).logWithHorizontal(AetherIIBlocks.AMBEROOT_LOG).wood(AetherIIBlocks.AMBEROOT_WOOD);
        this.woodProvider(AetherIIBlocks.STRIPPED_AMBEROOT_LOG).logWithHorizontal(AetherIIBlocks.STRIPPED_AMBEROOT_LOG).wood(AetherIIBlocks.STRIPPED_AMBEROOT_WOOD);
        this.woodProviderColumn(AetherIIBlocks.AMBEROOT_DEPOSIT, AetherIIBlocks.AMBEROOT_LOG).logWithHorizontal(AetherIIBlocks.AMBEROOT_DEPOSIT);

        // Trunks
        this.createTrunk(AetherIIBlocks.SKYROOT_TRUNK, AetherIIBlocks.SKYROOT_LOG);
        this.createTrunk(AetherIIBlocks.STRIPPED_SKYROOT_TRUNK, AetherIIBlocks.STRIPPED_SKYROOT_LOG);
        this.createTrunk(AetherIIBlocks.GREATROOT_TRUNK, AetherIIBlocks.GREATROOT_LOG);
        this.createTrunk(AetherIIBlocks.STRIPPED_GREATROOT_TRUNK, AetherIIBlocks.STRIPPED_GREATROOT_LOG);
        this.createTrunk(AetherIIBlocks.WISPROOT_TRUNK, AetherIIBlocks.WISPROOT_LOG);
        this.createTrunk(AetherIIBlocks.MOSSY_WISPROOT_TRUNK, AetherIIBlocks.MOSSY_WISPROOT_LOG);
        this.createTrunk(AetherIIBlocks.STRIPPED_WISPROOT_TRUNK, AetherIIBlocks.STRIPPED_WISPROOT_LOG);
        this.createTrunk(AetherIIBlocks.AMBEROOT_TRUNK, AetherIIBlocks.AMBEROOT_LOG);
        this.createTrunk(AetherIIBlocks.STRIPPED_AMBEROOT_TRUNK, AetherIIBlocks.STRIPPED_AMBEROOT_LOG);

        // Leaves
        this.createLeavesWithPiles(AetherIIBlocks.SKYROOT_LEAVES, AetherIIBlocks.SKYROOT_LEAF_PILE, AetherIITexturedModels.LEAVES, AetherIIModelTemplates.OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.SKYPLANE_LEAVES, AetherIIBlocks.SKYPLANE_LEAF_PILE, AetherIITexturedModels.LEAVES, AetherIIModelTemplates.OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.SKYBIRCH_LEAVES, AetherIIBlocks.SKYBIRCH_LEAF_PILE, AetherIITexturedModels.LEAVES, AetherIIModelTemplates.OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.SKYPINE_LEAVES, AetherIIBlocks.SKYPINE_LEAF_PILE, AetherIITexturedModels.LEAVES, AetherIIModelTemplates.OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.WISPROOT_LEAVES, AetherIIBlocks.WISPROOT_LEAF_PILE, AetherIITexturedModels.LEAVES, AetherIIModelTemplates.OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.WISPTOP_LEAVES, AetherIIBlocks.WISPTOP_LEAF_PILE, AetherIITexturedModels.LEAVES, AetherIIModelTemplates.OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.GREATROOT_LEAVES, AetherIIBlocks.GREATROOT_LEAF_PILE, AetherIITexturedModels.LEAVES, AetherIIModelTemplates.OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.GREATOAK_LEAVES, AetherIIBlocks.GREATOAK_LEAF_PILE, AetherIITexturedModels.LEAVES, AetherIIModelTemplates.OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.GREATBOA_LEAVES, AetherIIBlocks.GREATBOA_LEAF_PILE, AetherIITexturedModels.LEAVES, AetherIIModelTemplates.OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.AMBEROOT_LEAVES, AetherIIBlocks.AMBEROOT_LEAF_PILE, AetherIITexturedModels.LEAVES, AetherIIModelTemplates.OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.IRRADIATED_SKYROOT_LEAVES, AetherIIBlocks.IRRADIATED_SKYROOT_LEAF_PILE, AetherIITexturedModels.TINTED_LEAVES, AetherIIModelTemplates.TINTED_OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.IRRADIATED_SKYPLANE_LEAVES, AetherIIBlocks.IRRADIATED_SKYPLANE_LEAF_PILE, AetherIITexturedModels.TINTED_LEAVES, AetherIIModelTemplates.TINTED_OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAVES, AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAF_PILE, AetherIITexturedModels.TINTED_LEAVES, AetherIIModelTemplates.TINTED_OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.IRRADIATED_SKYPINE_LEAVES, AetherIIBlocks.IRRADIATED_SKYPINE_LEAF_PILE, AetherIITexturedModels.TINTED_LEAVES, AetherIIModelTemplates.TINTED_OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.IRRADIATED_WISPROOT_LEAVES, AetherIIBlocks.IRRADIATED_WISPROOT_LEAF_PILE, AetherIITexturedModels.TINTED_LEAVES, AetherIIModelTemplates.TINTED_OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.IRRADIATED_WISPTOP_LEAVES, AetherIIBlocks.IRRADIATED_WISPTOP_LEAF_PILE, AetherIITexturedModels.TINTED_LEAVES, AetherIIModelTemplates.TINTED_OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.IRRADIATED_GREATROOT_LEAVES, AetherIIBlocks.IRRADIATED_GREATROOT_LEAF_PILE, AetherIITexturedModels.TINTED_LEAVES, AetherIIModelTemplates.TINTED_OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.IRRADIATED_GREATOAK_LEAVES, AetherIIBlocks.IRRADIATED_GREATOAK_LEAF_PILE, AetherIITexturedModels.TINTED_LEAVES, AetherIIModelTemplates.TINTED_OVERLAID_LEAVES);
        this.createLeavesWithPiles(AetherIIBlocks.IRRADIATED_GREATBOA_LEAVES, AetherIIBlocks.IRRADIATED_GREATBOA_LEAF_PILE, AetherIITexturedModels.TINTED_LEAVES, AetherIIModelTemplates.TINTED_OVERLAID_LEAVES);

        // Saplings
        this.createPlantWithDefaultItem(AetherIIBlocks.SKYROOT_SAPLING, AetherIIBlocks.POTTED_SKYROOT_SAPLING, PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.SKYPLANE_SAPLING, AetherIIBlocks.POTTED_SKYPLANE_SAPLING, PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.SKYBIRCH_SAPLING, AetherIIBlocks.POTTED_SKYBIRCH_SAPLING, PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.SKYPINE_SAPLING, AetherIIBlocks.POTTED_SKYPINE_SAPLING, PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.WISPROOT_SAPLING, AetherIIBlocks.POTTED_WISPROOT_SAPLING, PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.WISPTOP_SAPLING, AetherIIBlocks.POTTED_WISPTOP_SAPLING, PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.GREATROOT_SAPLING, AetherIIBlocks.POTTED_GREATROOT_SAPLING, PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.GREATOAK_SAPLING, AetherIIBlocks.POTTED_GREATOAK_SAPLING, PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.GREATBOA_SAPLING, AetherIIBlocks.POTTED_GREATBOA_SAPLING, PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.AMBEROOT_SAPLING, AetherIIBlocks.POTTED_AMBEROOT_SAPLING, PlantType.NOT_TINTED);

        // Grasses
        this.createTintedTallGrass(AetherIIBlocks.SHORT_AETHER_GRASS);
        this.createTintedTallGrass(AetherIIBlocks.MEDIUM_AETHER_GRASS);
        this.createTintedTallGrass(AetherIIBlocks.TALL_AETHER_GRASS);

        // Flowers
        this.createAetherFern();
        this.createPlantWithDefaultItem(AetherIIBlocks.SHIELD_FERN, AetherIIBlocks.POTTED_SHIELD_FERN, PlantType.NOT_TINTED);
        this.createSnowyPlantWithDefaultItem(AetherIIBlocks.HESPEROSE, AetherIIBlocks.POTTED_HESPEROSE);
        this.createSnowyPlantWithDefaultItem(AetherIIBlocks.TARABLOOM, AetherIIBlocks.POTTED_TARABLOOM);
        this.createSnowyPlantWithDefaultItem(AetherIIBlocks.POASPROUT, AetherIIBlocks.POTTED_POASPROUT);
        this.createAsymmetricalPlantWithDefaultItem(AetherIIBlocks.SATIVAL_SHOOT, AetherIITexturedModels.ASYMMETRICAL_CROSS_EVEN, AetherIITexturedModels.ASYMMETRICAL_CROSS_EVEN_MIRRORED,
                AetherIIBlocks.POTTED_SATIVAL_SHOOT, AetherIIModelTemplates.POTTED_ASYMMETRICAL_CROSS_EVEN);
        this.createUniquePlantWithDefaultItem(AetherIIBlocks.LILICHIME, AetherIITexturedModels.LILICHIME, AetherIIBlocks.POTTED_LILICHIME, AetherIIModelTemplates.POTTED_LILICHIME, AetherIITextureMappings::pottedLilichime);
        this.createFacingPlantWithDefaultItem(AetherIIBlocks.PLURACIAN, AetherIITexturedModels.PLURACIAN, AetherIIBlocks.POTTED_PLURACIAN, AetherIIModelTemplates.POTTED_PLURACIAN, AetherIITextureMappings::pluracian);
        this.createAsymmetricalPlantWithDefaultItem(AetherIIBlocks.BLADE_POA, AetherIITexturedModels.ASYMMETRICAL_CROSS_ODD, AetherIITexturedModels.ASYMMETRICAL_CROSS_ODD_MIRRORED,
                AetherIIBlocks.POTTED_BLADE_POA, AetherIIModelTemplates.POTTED_ASYMMETRICAL_CROSS_ODD);
        this.createPlantWithDefaultItem(AetherIIBlocks.AECHOR_CUTTING, AetherIIBlocks.POTTED_AECHOR_CUTTING, PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.CARRION_CUTTING, AetherIIBlocks.POTTED_CARRION_CUTTING, PlantType.NOT_TINTED);

        // Bushes
        this.createBush(AetherIIBlocks.AETHER_BUSH, AetherIIBlocks.POTTED_AETHER_BUSH);
        this.createPlantWithDefaultItem(AetherIIBlocks.BLUEBERRY_BUSH_STEM, AetherIIBlocks.POTTED_BLUEBERRY_BUSH_STEM, PlantType.NOT_TINTED);
        this.createBush(AetherIIBlocks.BLUEBERRY_BUSH, AetherIIBlocks.POTTED_BLUEBERRY_BUSH);

        // Orange Tree
        this.createOrangeTree(AetherIIBlocks.ORANGE_TREE, AetherIIBlocks.POTTED_ORANGE_TREE);

        // Surface Vegetation
        this.createValkyrieSprout();
        this.createBrettlPlant(AetherIIBlocks.BRETTL_PLANT);
        this.createBrettlPlant(AetherIIBlocks.BRETTL_PLANT_TIP);
        this.createCrossWithDefaultItem(AetherIIBlocks.BRETTL_FLOWER, PlantType.NOT_TINTED);

        // Lake
        this.createCrossBlock(AetherIIBlocks.ARILUM_SHOOT, PlantType.NOT_TINTED);
        this.createCrossWithDefaultItem(AetherIIBlocks.ARILUM, PlantType.NOT_TINTED);
        this.createCrossWithDefaultItem(AetherIIBlocks.ARILUM_PLANT, PlantType.NOT_TINTED);
        this.createCrossWithDefaultItem(AetherIIBlocks.BLOOMING_ARILUM, PlantType.NOT_TINTED);
        this.createCrossWithDefaultItem(AetherIIBlocks.BLOOMING_ARILUM_PLANT, PlantType.NOT_TINTED);

        // Ground Decoration
        this.createTwig(AetherIIBlocks.SKYROOT_TWIG, AetherIIBlocks.SKYROOT_LOG);
        this.createRock(AetherIIBlocks.HOLYSTONE_ROCK, AetherIIBlocks.HOLYSTONE);

        // Skyroot Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.SKYROOT_FLOORBOARDS);
        this.createTrivialCube(AetherIIBlocks.SKYROOT_HIGHLIGHT);
        this.createHorizontallyRotatedBlock(AetherIIBlocks.SKYROOT_SHINGLES, TexturedModel.CUBE);
        this.createHorizontallyRotatedBlock(AetherIIBlocks.SKYROOT_SMALL_SHINGLES, TexturedModel.CUBE);
        this.createCubeColumn(AetherIIBlocks.SKYROOT_BASE_PLANKS, AetherIIBlocks.SKYROOT_HIGHLIGHT);
        this.createCubeColumn(AetherIIBlocks.SKYROOT_TOP_PLANKS, AetherIIBlocks.SKYROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.SKYROOT_BASE_BEAM, AetherIIBlocks.SKYROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.SKYROOT_TOP_BEAM, AetherIIBlocks.SKYROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.SKYROOT_BEAM, AetherIIBlocks.SKYROOT_HIGHLIGHT);
        this.createSecretDoor(AetherIIBlocks.SECRET_SKYROOT_DOOR, AetherIIBlocks.SKYROOT_PLANKS);
        this.createOrientableSecretTrapdoor(AetherIIBlocks.SECRET_SKYROOT_TRAPDOOR, AetherIIBlocks.SKYROOT_PLANKS);

        // Greatroot Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.GREATROOT_FLOORBOARDS);
        this.createTrivialCube(AetherIIBlocks.GREATROOT_HIGHLIGHT);
        this.createHorizontallyRotatedBlock(AetherIIBlocks.GREATROOT_SHINGLES, TexturedModel.CUBE);
        this.createHorizontallyRotatedBlock(AetherIIBlocks.GREATROOT_SMALL_SHINGLES, TexturedModel.CUBE);
        this.createCubeColumn(AetherIIBlocks.GREATROOT_BASE_PLANKS, AetherIIBlocks.GREATROOT_HIGHLIGHT);
        this.createCubeColumn(AetherIIBlocks.GREATROOT_TOP_PLANKS, AetherIIBlocks.GREATROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.GREATROOT_BASE_BEAM, AetherIIBlocks.GREATROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.GREATROOT_TOP_BEAM, AetherIIBlocks.GREATROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.GREATROOT_BEAM, AetherIIBlocks.GREATROOT_HIGHLIGHT);
        this.createSecretDoor(AetherIIBlocks.SECRET_GREATROOT_DOOR, AetherIIBlocks.GREATROOT_PLANKS);
        this.createOrientableSecretTrapdoor(AetherIIBlocks.SECRET_GREATROOT_TRAPDOOR, AetherIIBlocks.GREATROOT_PLANKS);

        // Wisproot Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.WISPROOT_FLOORBOARDS);
        this.createTrivialCube(AetherIIBlocks.WISPROOT_HIGHLIGHT);
        this.createHorizontallyRotatedBlock(AetherIIBlocks.WISPROOT_SHINGLES, TexturedModel.CUBE);
        this.createHorizontallyRotatedBlock(AetherIIBlocks.WISPROOT_SMALL_SHINGLES, TexturedModel.CUBE);
        this.createCubeColumn(AetherIIBlocks.WISPROOT_BASE_PLANKS, AetherIIBlocks.WISPROOT_HIGHLIGHT);
        this.createCubeColumn(AetherIIBlocks.WISPROOT_TOP_PLANKS, AetherIIBlocks.WISPROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.WISPROOT_BASE_BEAM, AetherIIBlocks.WISPROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.WISPROOT_TOP_BEAM, AetherIIBlocks.WISPROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.WISPROOT_BEAM, AetherIIBlocks.WISPROOT_HIGHLIGHT);
        this.createSecretDoor(AetherIIBlocks.SECRET_WISPROOT_DOOR, AetherIIBlocks.WISPROOT_PLANKS);
        this.createOrientableSecretTrapdoor(AetherIIBlocks.SECRET_WISPROOT_TRAPDOOR, AetherIIBlocks.WISPROOT_PLANKS);

        // Amberoot Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.AMBEROOT_FLOORBOARDS);
        this.createTrivialCube(AetherIIBlocks.AMBEROOT_HIGHLIGHT);
        this.createHorizontallyRotatedBlock(AetherIIBlocks.AMBEROOT_SHINGLES, TexturedModel.CUBE);
        this.createHorizontallyRotatedBlock(AetherIIBlocks.AMBEROOT_SMALL_SHINGLES, TexturedModel.CUBE);
        this.createCubeColumn(AetherIIBlocks.AMBEROOT_BASE_PLANKS, AetherIIBlocks.AMBEROOT_HIGHLIGHT);
        this.createCubeColumn(AetherIIBlocks.AMBEROOT_TOP_PLANKS, AetherIIBlocks.AMBEROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.AMBEROOT_BASE_BEAM, AetherIIBlocks.AMBEROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.AMBEROOT_TOP_BEAM, AetherIIBlocks.AMBEROOT_HIGHLIGHT);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.AMBEROOT_BEAM, AetherIIBlocks.AMBEROOT_HIGHLIGHT);
        this.createSecretDoor(AetherIIBlocks.SECRET_AMBEROOT_DOOR, AetherIIBlocks.AMBEROOT_PLANKS);
        this.createOrientableSecretTrapdoor(AetherIIBlocks.SECRET_AMBEROOT_TRAPDOOR, AetherIIBlocks.AMBEROOT_PLANKS);

        // Holystone Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.HOLYSTONE_FLAGSTONES);
        this.createTrivialCube(AetherIIBlocks.HOLYSTONE_HEADSTONE);
        this.createTrivialCube(AetherIIBlocks.HOLYSTONE_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.HOLYSTONE_BASE_BRICKS, AetherIIBlocks.HOLYSTONE_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.HOLYSTONE_CAPSTONE_BRICKS, AetherIIBlocks.HOLYSTONE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.HOLYSTONE_BASE_PILLAR, AetherIIBlocks.HOLYSTONE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.HOLYSTONE_CAPSTONE_PILLAR, AetherIIBlocks.HOLYSTONE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.HOLYSTONE_PILLAR, AetherIIBlocks.HOLYSTONE_KEYSTONE);

        // Faded Holystone Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.FADED_HOLYSTONE_FLAGSTONES);
        this.createTrivialCube(AetherIIBlocks.FADED_HOLYSTONE_HEADSTONE);
        this.createTrivialCube(AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.FADED_HOLYSTONE_BASE_BRICKS, AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_BRICKS, AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.FADED_HOLYSTONE_BASE_PILLAR, AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_PILLAR, AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.FADED_HOLYSTONE_PILLAR, AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE);

        // Undershale Bricks
        this.pressurePlate(AetherIIBlocks.UNDERSHALE_BRICK_PRESSURE_PLATE);
        this.button(AetherIIBlocks.UNDERSHALE_BRICK_BUTTON);

        // Undershale Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_FLAGSTONES);
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_TILE);
        this.createCubeColumn(AetherIIBlocks.UNDERSHALE_BASE_BRICKS, AetherIIBlocks.UNDERSHALE_TILE);
        this.createCubeColumn(AetherIIBlocks.UNDERSHALE_CAPSTONE_BRICKS, AetherIIBlocks.UNDERSHALE_TILE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.UNDERSHALE_BASE_PILLAR, AetherIIBlocks.UNDERSHALE_TILE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.UNDERSHALE_CAPSTONE_PILLAR, AetherIIBlocks.UNDERSHALE_TILE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.UNDERSHALE_PILLAR, AetherIIBlocks.UNDERSHALE_TILE);

        // Sentry Bricks
        this.createLitBlock(AetherIIBlocks.SENTRY_BRICKS);
        this.createLitStairs(AetherIIBlocks.SENTRY_BRICK_STAIRS, AetherIIBlocks.SENTRY_BRICKS);
        this.createLitSlab(AetherIIBlocks.SENTRY_BRICK_SLAB, AetherIIBlocks.SENTRY_BRICKS);
        this.createLitWall(AetherIIBlocks.SENTRY_BRICK_WALL, AetherIIBlocks.SENTRY_BRICKS, AetherIIBlocks.UNDERSHALE_BRICKS);
        this.litButton(AetherIIBlocks.SENTRY_BUTTON);

        // Sentry Decorative Blocks
        this.createLitBlock(AetherIIBlocks.SENTRY_LIGHTSTONE);
        this.createLitBlock(AetherIIBlocks.SENTRY_FLAGSTONES);
        this.createLitBlock(AetherIIBlocks.SENTRY_TILE);
        this.createLitCubeColumn(AetherIIBlocks.SENTRY_BASE_BRICKS, AetherIIBlocks.UNDERSHALE_TILE);
        this.createLitCubeColumn(AetherIIBlocks.SENTRY_CAPSTONE_BRICKS, AetherIIBlocks.UNDERSHALE_TILE);
        this.createLitFacingColumnWithHorizontalVariant(AetherIIBlocks.SENTRY_BASE_PILLAR, AetherIIBlocks.UNDERSHALE_TILE);
        this.createLitFacingColumnWithHorizontalVariant(AetherIIBlocks.SENTRY_CAPSTONE_PILLAR, AetherIIBlocks.UNDERSHALE_TILE);
        this.createLitFacingColumnWithHorizontalVariant(AetherIIBlocks.SENTRY_PILLAR, AetherIIBlocks.UNDERSHALE_TILE);

        // Agiosite Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.AGIOSITE_FLAGSTONES);
        this.createTrivialCube(AetherIIBlocks.AGIOSITE_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.AGIOSITE_BASE_BRICKS, AetherIIBlocks.AGIOSITE_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.AGIOSITE_CAPSTONE_BRICKS, AetherIIBlocks.AGIOSITE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.AGIOSITE_BASE_PILLAR, AetherIIBlocks.AGIOSITE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.AGIOSITE_CAPSTONE_PILLAR, AetherIIBlocks.AGIOSITE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.AGIOSITE_PILLAR, AetherIIBlocks.AGIOSITE_KEYSTONE);

        // Ichorite Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.ICHORITE_FLAGSTONES);
        this.createTrivialCube(AetherIIBlocks.ICHORITE_RUNESTONE);
        this.createTrivialCube(AetherIIBlocks.ICHORITE_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.ICHORITE_BASE_BRICKS, AetherIIBlocks.ICHORITE_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.ICHORITE_CAPSTONE_BRICKS, AetherIIBlocks.ICHORITE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.ICHORITE_BASE_PILLAR, AetherIIBlocks.ICHORITE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.ICHORITE_CAPSTONE_PILLAR, AetherIIBlocks.ICHORITE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.ICHORITE_PILLAR, AetherIIBlocks.ICHORITE_KEYSTONE);

        // Marbled Ichorite Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.MARBLED_FLAGSTONES);
        this.createTrivialCube(AetherIIBlocks.MARBLED_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.MARBLED_BASE_BRICKS, AetherIIBlocks.MARBLED_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.MARBLED_CAPSTONE_BRICKS, AetherIIBlocks.MARBLED_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.MARBLED_BASE_PILLAR, AetherIIBlocks.MARBLED_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.MARBLED_CAPSTONE_PILLAR, AetherIIBlocks.MARBLED_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.MARBLED_PILLAR, AetherIIBlocks.MARBLED_KEYSTONE);

        // Icestone Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.ICESTONE_FLAGSTONES);
        this.createTrivialCube(AetherIIBlocks.ICESTONE_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.ICESTONE_BASE_BRICKS, AetherIIBlocks.ICESTONE_KEYSTONE);
        this.createCubeColumn(AetherIIBlocks.ICESTONE_CAPSTONE_BRICKS, AetherIIBlocks.ICESTONE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.ICESTONE_BASE_PILLAR, AetherIIBlocks.ICESTONE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.ICESTONE_CAPSTONE_PILLAR, AetherIIBlocks.ICESTONE_KEYSTONE);
        this.createFacingColumnWithHorizontalVariant(AetherIIBlocks.ICESTONE_PILLAR, AetherIIBlocks.ICESTONE_KEYSTONE);

        // Glass
        this.createAetherGlassBlocks(AetherIIBlocks.QUICKSOIL_GLASS, AetherIIBlocks.QUICKSOIL_GLASS_PANE);
        this.createAetherGlassBlocks(AetherIIBlocks.TILED_QUICKSOIL_GLASS, AetherIIBlocks.TILED_QUICKSOIL_GLASS_PANE);
        this.createAetherGlassBlocks(AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS, AetherIIBlocks.GRIDDED_QUICKSOIL_GLASS_PANE);
        this.createAetherGlassBlocks(AetherIIBlocks.CRUDE_SCATTERGLASS, AetherIIBlocks.CRUDE_SCATTERGLASS_PANE);
        this.createAetherGlassBlocks(AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS, AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS_PANE);
        this.createAetherGlassBlocks(AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS, AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS_PANE);
        this.createAetherGlassBlocks(AetherIIBlocks.SCATTERGLASS, AetherIIBlocks.SCATTERGLASS_PANE);
        this.createAetherGlassBlocks(AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS, AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS_PANE);
        this.createAetherGlassBlocks(AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS, AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS_PANE);

        // Wool
        this.createFullAndCarpetBlocks(AetherIIBlocks.CLOUDWOOL, AetherIIBlocks.CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.WHITE_CLOUDWOOL, AetherIIBlocks.WHITE_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.ORANGE_CLOUDWOOL, AetherIIBlocks.ORANGE_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.MAGENTA_CLOUDWOOL, AetherIIBlocks.MAGENTA_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL, AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.YELLOW_CLOUDWOOL, AetherIIBlocks.YELLOW_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.LIME_CLOUDWOOL, AetherIIBlocks.LIME_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.PINK_CLOUDWOOL, AetherIIBlocks.PINK_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.GRAY_CLOUDWOOL, AetherIIBlocks.GRAY_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL, AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.CYAN_CLOUDWOOL, AetherIIBlocks.CYAN_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.PURPLE_CLOUDWOOL, AetherIIBlocks.PURPLE_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.BLUE_CLOUDWOOL, AetherIIBlocks.BLUE_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.BROWN_CLOUDWOOL, AetherIIBlocks.BROWN_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.GREEN_CLOUDWOOL, AetherIIBlocks.GREEN_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.RED_CLOUDWOOL, AetherIIBlocks.RED_CLOUDWOOL_CARPET);
        this.createFullAndCarpetBlocks(AetherIIBlocks.BLACK_CLOUDWOOL, AetherIIBlocks.BLACK_CLOUDWOOL_CARPET);

        // Roofing
        this.createRoofing(AetherIIBlocks.CLOUDWOOL_ROOFING);

        // Arkenium Blocks
        this.createAetherDoor(AetherIIBlocks.ARKENIUM_DOOR);
        this.createAetherOrientableTrapdoor(AetherIIBlocks.ARKENIUM_TRAPDOOR);
        this.createBarsWithDifferentEdge(AetherIIBlocks.ARKENIUM_BARS, AetherIIBlocks.ARKENIUM_BARS, "");
        this.createBarsWithDifferentEdge(AetherIIBlocks.FLORAL_ARKENIUM_BARS, AetherIIBlocks.ARKENIUM_BARS, "_edge");
        this.createBarsWithDifferentEdge(AetherIIBlocks.PATTERNED_ARKENIUM_BARS, AetherIIBlocks.ARKENIUM_BARS, "_edge");
        this.createBarsWithDifferentEdge(AetherIIBlocks.CURVED_ARKENIUM_BARS, AetherIIBlocks.ARKENIUM_BARS, "_edge");

        // Rustic Arkenium Blocks
        this.createBarsWithDifferentEdge(AetherIIBlocks.RUSTIC_ARKENIUM_BARS, AetherIIBlocks.RUSTIC_ARKENIUM_BARS, "");
        this.createBarsWithDifferentEdge(AetherIIBlocks.RUSTIC_FLORAL_ARKENIUM_BARS, AetherIIBlocks.RUSTIC_ARKENIUM_BARS, "_edge");
        this.createBarsWithDifferentEdge(AetherIIBlocks.RUSTIC_PATTERNED_ARKENIUM_BARS, AetherIIBlocks.RUSTIC_ARKENIUM_BARS, "_edge");
        this.createBarsWithDifferentEdge(AetherIIBlocks.RUSTIC_CURVED_ARKENIUM_BARS, AetherIIBlocks.RUSTIC_ARKENIUM_BARS, "_edge");

        // Inert Mineral Blocks
        this.createTrivialCube(AetherIIBlocks.INERT_ARKENIUM_BLOCK);
        this.createTrivialCube(AetherIIBlocks.INERT_GRAVITITE_BLOCK);

        // Mineral Blocks
        this.createTrivialCube(AetherIIBlocks.AMBROSIUM_BLOCK);
        this.createTrivialCube(AetherIIBlocks.ZANITE_BLOCK);
        this.createTrivialCube(AetherIIBlocks.ARKENIUM_BLOCK);
        this.createTrivialCube(AetherIIBlocks.GRAVITITE_BLOCK);
        this.createTrivialCube(AetherIIBlocks.GLINT_BLOCK);
        this.createTrivialCube(AetherIIBlocks.CORROBONITE_BLOCK);
        this.createTrivialCube(AetherIIBlocks.GOLDEN_AMBER_BLOCK);

        // Storage Blocks
        this.createRotatedPillarWithHorizontalVariant(AetherIIBlocks.BRETTL_GRASS_BUNDLE, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        this.createTrivialCube(AetherIIBlocks.GEL_BLOCK);

        // Arilum Lantern
        this.createArilumLantern(AetherIIBlocks.WHITE_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.ORANGE_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.MAGENTA_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.LIGHT_BLUE_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.YELLOW_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.LIME_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.PINK_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.GRAY_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.LIGHT_GRAY_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.CYAN_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.PURPLE_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.BLUE_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.BROWN_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.GREEN_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.RED_ARILUM_LANTERN);
        this.createArilumLantern(AetherIIBlocks.BLACK_ARILUM_LANTERN);

        // Utility
        this.createAmbrosiumTorch();
        this.createArkeniumLantern();
        this.createRusticArkeniumLantern();
        this.createAxisAlignedPillarBlockCustomModel(AetherIIBlocks.ARKENIUM_CHAIN, plainVariant(ModelLocationUtils.getModelLocation(AetherIIBlocks.ARKENIUM_CHAIN)));
        this.createCraftingTableLike(AetherIIBlocks.SKYROOT_CRAFTING_TABLE, AetherIIBlocks.SKYROOT_PLANKS, TextureMapping::craftingTable);
        this.createFurnace(AetherIIBlocks.HOLYSTONE_FURNACE, TexturedModel.ORIENTABLE_ONLY_TOP);
        this.createFurnace(AetherIIBlocks.HOLYSTONE_SMOKER, TexturedModel.ORIENTABLE);
        this.createAmberHourglass(AetherIIBlocks.AMBER_HOURGLASS);
        this.createAltar(AetherIIBlocks.ALTAR, AetherIIBlocks.HOLYSTONE);
        this.createArtisansBench(AetherIIBlocks.ARTISANS_BENCH, AetherIIBlocks.HOLYSTONE_BRICKS);
        this.createArkeniumForge(AetherIIBlocks.ARKENIUM_FORGE, AetherIIBlocks.ARKENIUM_BLOCK);
        this.createAlkahestPurifier(AetherIIBlocks.ALKAHEST_PURIFIER, AetherIIBlocks.ARKENIUM_BLOCK);
        this.createTrivialCube(AetherIIBlocks.MUSIC_BLOCK);
        this.createCampfire(AetherIIBlocks.AMBROSIUM_CAMPFIRE);
        this.createChest(AetherIIBlocks.SKYROOT_CHEST, AetherIIBlocks.SKYROOT_PLANKS, Identifier.fromNamespaceAndPath(AetherII.MODID, "skyroot_chest"), true);
        this.createBarrel(AetherIIBlocks.SKYROOT_BARREL);
        this.createLadder(AetherIIBlocks.SKYROOT_LADDER);
        this.createBedroll(AetherIIBlocks.CLOUDWOOL_BEDROLL);

        this.createBed(AetherIIBlocks.SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "undyed");
        this.createBed(AetherIIBlocks.WHITE_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "white");
        this.createBed(AetherIIBlocks.ORANGE_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "orange");
        this.createBed(AetherIIBlocks.MAGENTA_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "magenta");
        this.createBed(AetherIIBlocks.LIGHT_BLUE_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "light_blue");
        this.createBed(AetherIIBlocks.YELLOW_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "yellow");
        this.createBed(AetherIIBlocks.LIME_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "lime");
        this.createBed(AetherIIBlocks.PINK_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "pink");
        this.createBed(AetherIIBlocks.GRAY_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "gray");
        this.createBed(AetherIIBlocks.LIGHT_GRAY_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "light_gray");
        this.createBed(AetherIIBlocks.CYAN_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "cyan");
        this.createBed(AetherIIBlocks.PURPLE_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "purple");
        this.createBed(AetherIIBlocks.BLUE_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "blue");
        this.createBed(AetherIIBlocks.BROWN_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "brown");
        this.createBed(AetherIIBlocks.GREEN_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "green");
        this.createBed(AetherIIBlocks.RED_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "red");
        this.createBed(AetherIIBlocks.BLACK_SKYROOT_BED, AetherIIBlocks.SKYROOT_PLANKS, "black");

        this.createVase(AetherIIBlocks.HOLYSTONE_VASE, AetherIIBlocks.FADED_HOLYSTONE_BRICKS);
        this.createVase(AetherIIBlocks.VERADEXIAN_VASE, AetherIIBlocks.ICHORITE_BRICKS);
        this.createVase(AetherIIBlocks.BREXALLEN_VASE, AetherIIBlocks.AGIOSITE);

        this.createSentryCrate(AetherIIBlocks.SENTRY_CRATE, AetherIIBlocks.UNDERSHALE_BRICKS);
        this.createSentrySpawner(AetherIIBlocks.SENTRY_SPAWNER, AetherIIBlocks.UNDERSHALE_BRICKS);
        this.createSentryTrap(AetherIIBlocks.SENTRY_TRAP, AetherIIBlocks.UNDERSHALE_TILE);

        this.createCopyBlock(AetherIIBlocks.LOCKED_BLOCK.builtInRegistryHolder(), "dungeon_lock");
        this.createCopyBlock(AetherIIBlocks.BOSS_DOORWAY_BLOCK.builtInRegistryHolder(), "dungeon_doorway");
        this.createCopyBlock(AetherIIBlocks.TREASURE_DOORWAY_BLOCK.builtInRegistryHolder(), "dungeon_treasure");

        this.createHangingSign(AetherIIBlocks.STRIPPED_SKYROOT_LOG, AetherIIBlocks.SKYROOT_HANGING_SIGN, AetherIIBlocks.SKYROOT_WALL_HANGING_SIGN);
        this.createHangingSign(AetherIIBlocks.STRIPPED_GREATROOT_LOG, AetherIIBlocks.GREATROOT_HANGING_SIGN, AetherIIBlocks.GREATROOT_WALL_HANGING_SIGN);
        this.createHangingSign(AetherIIBlocks.STRIPPED_WISPROOT_LOG, AetherIIBlocks.WISPROOT_HANGING_SIGN, AetherIIBlocks.WISPROOT_WALL_HANGING_SIGN);
        this.createHangingSign(AetherIIBlocks.STRIPPED_AMBEROOT_LOG, AetherIIBlocks.AMBEROOT_HANGING_SIGN, AetherIIBlocks.AMBEROOT_WALL_HANGING_SIGN);

        this.createShelf(AetherIIBlocks.SKYROOT_SHELF, AetherIIBlocks.STRIPPED_SKYROOT_LOG);
        this.createShelf(AetherIIBlocks.GREATROOT_SHELF, AetherIIBlocks.STRIPPED_GREATROOT_LOG);
        this.createShelf(AetherIIBlocks.WISPROOT_SHELF, AetherIIBlocks.STRIPPED_WISPROOT_LOG);
        this.createShelf(AetherIIBlocks.AMBEROOT_SHELF, AetherIIBlocks.STRIPPED_AMBEROOT_LOG);

        this.createLever(AetherIIBlocks.HOLYSTONE_LEVER);

        // Bookshelves
        this.createCubeColumn(AetherIIBlocks.SKYROOT_BOOKSHELF, AetherIIBlocks.SKYROOT_PLANKS);
        this.createCubeColumn(AetherIIBlocks.GREATROOT_BOOKSHELF, AetherIIBlocks.GREATROOT_PLANKS);
        this.createCubeColumn(AetherIIBlocks.WISPROOT_BOOKSHELF, AetherIIBlocks.WISPROOT_PLANKS);
        this.createCubeColumn(AetherIIBlocks.AMBEROOT_BOOKSHELF, AetherIIBlocks.AMBEROOT_PLANKS);
        this.createCubeColumn(AetherIIBlocks.HOLYSTONE_BOOKSHELF, AetherIIBlocks.HOLYSTONE_BRICKS);

        // Furniture
        this.createOutpostCampfire();
        this.createMural();
        this.createTrivialCube(AetherIIBlocks.MURAL);

        this.createMeltingBlock(AetherIIBlocks.FROSTED_ICE, Blocks.FROSTED_ICE, ModelTemplates.CUBE_ALL);
        this.createMeltingBlock(AetherIIBlocks.FROSTED_ARCTIC_ICE, AetherIIBlocks.FROSTED_ARCTIC_ICE, ModelTemplates.CUBE_ALL);
        this.createMeltingBlock(AetherIIBlocks.UNSTABLE_OBSIDIAN, AetherIIBlocks.UNSTABLE_OBSIDIAN, ModelTemplates.CUBE_ALL);

        // Infected Guardian Tree
        // Guardian Wood
        this.woodProvider(AetherIIBlocks.GUARDIAN_LOG).logWithHorizontal(AetherIIBlocks.GUARDIAN_LOG).wood(AetherIIBlocks.GUARDIAN_WOOD);
        this.woodProvider(AetherIIBlocks.STRIPPED_GUARDIAN_LOG).logWithHorizontal(AetherIIBlocks.STRIPPED_GUARDIAN_LOG).wood(AetherIIBlocks.STRIPPED_GUARDIAN_WOOD);

        // Infected Wood
        this.woodProvider(AetherIIBlocks.INFECTED_LOG).logWithHorizontal(AetherIIBlocks.INFECTED_LOG).wood(AetherIIBlocks.INFECTED_WOOD);
        this.woodProvider(AetherIIBlocks.STRIPPED_INFECTED_LOG).logWithHorizontal(AetherIIBlocks.STRIPPED_INFECTED_LOG).wood(AetherIIBlocks.STRIPPED_INFECTED_WOOD);

        // Guardian Slabs
        this.createLogSlab(AetherIIBlocks.GUARDIAN_LOG_SLAB, AetherIIBlocks.GUARDIAN_LOG);
        this.createWoodSlab(AetherIIBlocks.GUARDIAN_WOOD_SLAB, AetherIIBlocks.GUARDIAN_WOOD, AetherIIBlocks.GUARDIAN_LOG);
        this.createLogSlab(AetherIIBlocks.STRIPPED_GUARDIAN_LOG_SLAB, AetherIIBlocks.STRIPPED_GUARDIAN_LOG);
        this.createWoodSlab(AetherIIBlocks.STRIPPED_GUARDIAN_WOOD_SLAB, AetherIIBlocks.STRIPPED_GUARDIAN_WOOD, AetherIIBlocks.STRIPPED_GUARDIAN_LOG);
        this.createLogSlab(AetherIIBlocks.INFECTED_LOG_SLAB, AetherIIBlocks.INFECTED_LOG);
        this.createWoodSlab(AetherIIBlocks.INFECTED_WOOD_SLAB, AetherIIBlocks.INFECTED_WOOD, AetherIIBlocks.INFECTED_LOG);
        this.createLogSlab(AetherIIBlocks.STRIPPED_INFECTED_LOG_SLAB, AetherIIBlocks.STRIPPED_INFECTED_LOG);
        this.createWoodSlab(AetherIIBlocks.STRIPPED_INFECTED_WOOD_SLAB, AetherIIBlocks.STRIPPED_INFECTED_WOOD, AetherIIBlocks.STRIPPED_INFECTED_LOG);

        // Guardian Trunks
        this.createTrunk(AetherIIBlocks.GUARDIAN_TRUNK, AetherIIBlocks.GUARDIAN_LOG);
        this.createTrunk(AetherIIBlocks.STRIPPED_GUARDIAN_TRUNK, AetherIIBlocks.STRIPPED_GUARDIAN_LOG);
        this.createTrunk(AetherIIBlocks.INFECTED_TRUNK, AetherIIBlocks.INFECTED_LOG);
        this.createTrunk(AetherIIBlocks.STRIPPED_INFECTED_TRUNK, AetherIIBlocks.STRIPPED_INFECTED_LOG);

        // Guardian Root Blocks
        this.createTrivialCube(AetherIIBlocks.GUARDIAN_ROOTS);
        this.createTrivialCube(AetherIIBlocks.UNSTABLE_GUARDIAN_ROOTS);
        this.createTrivialCube(AetherIIBlocks.LUCENT_GUARDIAN_ROOTS);
        this.createTrivialCube(AetherIIBlocks.GUARDIAN_LAMP);

        // Undergrowth Blocks
        this.createCutoutMippedCube(AetherIIBlocks.UNDERGROWTH_LEAVES);
        this.createVine(AetherIIBlocks.UNDERGROWTH_VINES, AetherIIModelTemplates.MOSS_VINE);
        this.createHangingUndergrowth(AetherIIBlocks.HANGING_UNDERGROWTH);
        this.createHangingUndergrowth(AetherIIBlocks.HANGING_UNDERGROWTH_PLANT);
        this.registerSimpleFlatItemModel(AetherIIBlocks.HANGING_UNDERGROWTH, "_plant");

        // Rotshroom Blocks
        this.createCubeBottom(AetherIIBlocks.ROTSHROOM_BLOCK);
        this.createMushroomSlab(AetherIIBlocks.ROTSHROOM_SLAB, AetherIIBlocks.ROTSHROOM_BLOCK);
        this.createRotatedPillarWithHorizontalVariant(AetherIIBlocks.ROTSHROOM_STEM, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        this.createShelfRotshroomSlab(AetherIIBlocks.SHELF_ROTSHROOM_SLAB);
        this.createPlantWithDefaultItem(AetherIIBlocks.ROTSHROOM, AetherIIBlocks.POTTED_ROTSHROOM, PlantType.NOT_TINTED);
        this.createRotshroomCluster(AetherIIBlocks.ROTSHROOM_CLUSTER);
        this.createRotshroomToadstool(AetherIIBlocks.ROTSHROOM_TOADSTOOL);
        this.createShelfRotshroom(AetherIIBlocks.SHELF_ROTSHROOM);
        this.createVine(AetherIIBlocks.ROTGROWTH_VINES, AetherIIModelTemplates.MOSS_VINE);

        // Dungeon Furniture
        this.createPrayerCandle(AetherIIBlocks.PRAYER_CANDLE, AetherIIBlocks.GUARDIAN_LOG);
        this.createGuardianPew(AetherIIBlocks.GUARDIAN_PEW, AetherIIBlocks.GUARDIAN_LOG);
        this.createGuardianDonationBox(AetherIIBlocks.GUARDIAN_DONATION_BOX, AetherIIBlocks.GUARDIAN_LOG);
        this.createAbandonedBag(AetherIIBlocks.ABANDONED_BAG, AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL);
        this.createFungalCache(AetherIIBlocks.FUNGAL_CACHE, AetherIIBlocks.ROTSHROOM_BLOCK);
        this.createSageChest(AetherIIBlocks.SAGE_CHEST, AetherIIBlocks.GUARDIAN_LOG);
    }
}