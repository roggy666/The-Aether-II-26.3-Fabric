package com.aetherteam.aetherii.data.resources.builders.worldgen.holyisles;

import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.core.HolderGetter;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.data.resources.registries.AetherIINoises;
import com.aetherteam.aetherii.data.resources.registries.holyisles.HolyIslesBiomes;
import com.aetherteam.aetherii.world.surfacerule.NoisePalette3DPlacementRule;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class HolyIslesSurfaceBuilders {
    private static final MaterialRule AETHER_GRASS_BLOCK = MaterialRules.state(AetherIIBlocks.AETHER_GRASS_BLOCK.defaultBlockState());
    private static final MaterialRule ENCHANTED_AETHER_GRASS_BLOCK = MaterialRules.state(AetherIIBlocks.ENCHANTED_AETHER_GRASS_BLOCK.defaultBlockState());
    private static final MaterialRule AETHER_DIRT = MaterialRules.state(AetherIIBlocks.AETHER_DIRT.defaultBlockState());
    private static final MaterialRule UNDERSHALE = MaterialRules.state(AetherIIBlocks.UNDERSHALE.defaultBlockState());
    private static final MaterialRule ARCTIC_SNOW_BLOCK = MaterialRules.state(AetherIIBlocks.ARCTIC_SNOW_BLOCK.defaultBlockState());
    private static final MaterialRule MOSSY_HOLYSTONE = new NoisePalette3DPlacementRule(AetherIIBlocks.MOSSY_HOLYSTONE.defaultBlockState(), 3, 10, 0.045);
    private static final MaterialRule PACKED_ICE = new NoisePalette3DPlacementRule(AetherIIBlocks.ARCTIC_PACKED_ICE.defaultBlockState(), 3, 10, 0.075);
    private static final MaterialRule FERROSITE = new NoisePalette3DPlacementRule(AetherIIBlocks.FERROSITE.defaultBlockState(), 9, 20, 0.05);
//    private static final MaterialRule RUSTED_FERROSITE = new NoisePalette3DPlacementRule(AetherIIBlocks.RUSTED_FERROSITE.defaultBlockState(), 1, 9, 0.03);
    private static final MaterialRule IRRADIATED_HOLYSTONE = new NoisePalette3DPlacementRule(AetherIIBlocks.IRRADIATED_HOLYSTONE.defaultBlockState(), 3, 10, 0.045);
    private static final MaterialRule ICHORITE = new NoisePalette3DPlacementRule(AetherIIBlocks.ICHORITE.defaultBlockState(), 16, 12, 0.075);
    private static final MaterialRule QUICKSOIL = MaterialRules.state(AetherIIBlocks.QUICKSOIL.defaultBlockState());

    public static MaterialRule surfaceRules(HolderGetter<Biome> biomes) {
        MaterialRule surface = MaterialRules.sequence(
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.BATTLEGROUND_WASTES), ENCHANTED_AETHER_GRASS_BLOCK),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.CONTAMINATED_JUNGLE), ENCHANTED_AETHER_GRASS_BLOCK),
                MaterialRules.ifTrue(MaterialRules.waterBlockCheck(-1, 0), AETHER_GRASS_BLOCK),
                AETHER_DIRT);
        return MaterialRules.sequence(
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.FRIGID_SIERRA),
                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(AetherIINoises.ARCTIC_SNOW, -0.5D, 0.35D),
                                MaterialRules.ifTrue(MaterialRules.stoneDepthCheck(0, false, CaveSurface.FLOOR), ARCTIC_SNOW_BLOCK))),

                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.FRIGID_SIERRA),
                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(AetherIINoises.ARCTIC_SNOW, -0.5D, 0.35D),
                                MaterialRules.ifTrue(MaterialRules.stoneDepthCheck(0, true, CaveSurface.FLOOR), ARCTIC_SNOW_BLOCK))),

                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.BATTLEGROUND_WASTES, HolyIslesBiomes.CONTAMINATED_JUNGLE),
                        MaterialRules.ifTrue(MaterialRules.not(MaterialRules.verticalGradient("aether_grass_block", VerticalAnchor.belowTop(276), VerticalAnchor.belowTop(272))),
                                MaterialRules.ifTrue(MaterialRules.not(MaterialRules.noiseCondition2d(AetherIINoises.QUICKSOIL_IRRADIATED, -0.5D, 0.5D)),
                                        MaterialRules.ifTrue(MaterialRules.not(MaterialRules.steep()),
                                                MaterialRules.ifTrue(MaterialRules.stoneDepthCheck(0, false, 2, CaveSurface.FLOOR), QUICKSOIL))))),

                MaterialRules.ifTrue(MaterialRules.not(MaterialRules.verticalGradient("aether_grass_block", VerticalAnchor.belowTop(276), VerticalAnchor.belowTop(272))),
                        MaterialRules.ifTrue(MaterialRules.stoneDepthCheck(0, false, CaveSurface.FLOOR), surface)),

                MaterialRules.ifTrue(MaterialRules.not(MaterialRules.verticalGradient("aether_dirt", VerticalAnchor.belowTop(272), VerticalAnchor.belowTop(272))),
                        MaterialRules.ifTrue(MaterialRules.stoneDepthCheck(0, true, CaveSurface.FLOOR), AETHER_DIRT)),

                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.HESTVEIL_CAVERNS), ICHORITE),

                MaterialRules.ifTrue(MaterialRules.verticalGradient("undershale", VerticalAnchor.absolute(89), VerticalAnchor.absolute(101)), UNDERSHALE),

                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.FLOURISHING_FIELD), MOSSY_HOLYSTONE),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.VERDANT_WOODS), MOSSY_HOLYSTONE),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.SHROUDED_FOREST), MOSSY_HOLYSTONE),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.SHIMMERING_BASIN), MOSSY_HOLYSTONE),

                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.FRIGID_SIERRA), PACKED_ICE),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.ENDURING_WOODLAND), PACKED_ICE),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.FROZEN_LAKES), PACKED_ICE),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.SHEER_TUNDRA), PACKED_ICE),

                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.MAGNETIC_SCAR), FERROSITE),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.TURQUOISE_FOREST), FERROSITE),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.GLISTENING_SWAMP), FERROSITE),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.VIOLET_HIGHWOODS), FERROSITE),

//                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HighlandsBiomes.MAGNETIC_SCAR), RUSTED_FERROSITE),
//                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HighlandsBiomes.TURQUOISE_FOREST), RUSTED_FERROSITE),
//                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HighlandsBiomes.GLISTENING_SWAMP), RUSTED_FERROSITE),
//                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HighlandsBiomes.VIOLET_HIGHWOODS), RUSTED_FERROSITE) //todo

                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.CONTAMINATED_JUNGLE), IRRADIATED_HOLYSTONE),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, HolyIslesBiomes.BATTLEGROUND_WASTES), IRRADIATED_HOLYSTONE)
        );
    }
}