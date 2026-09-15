package com.aetherteam.aetherii.data.resources.builders.worldgen.holyisles;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.core.HolderGetter;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.data.resources.registries.AetherIINoises;
import com.aetherteam.aetherii.data.resources.registries.holyisles.HolyIslesBiomes;
import com.aetherteam.aetherii.world.surfacerule.NoisePalette3DPlacementRule;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class HolyIslesSurfaceBuilders {
    private static final SurfaceRules.RuleSource AETHER_GRASS_BLOCK = SurfaceRules.state(AetherIIBlocks.AETHER_GRASS_BLOCK.defaultBlockState());
    private static final SurfaceRules.RuleSource ENCHANTED_AETHER_GRASS_BLOCK = SurfaceRules.state(AetherIIBlocks.ENCHANTED_AETHER_GRASS_BLOCK.defaultBlockState());
    private static final SurfaceRules.RuleSource AETHER_DIRT = SurfaceRules.state(AetherIIBlocks.AETHER_DIRT.defaultBlockState());
    private static final SurfaceRules.RuleSource UNDERSHALE = SurfaceRules.state(AetherIIBlocks.UNDERSHALE.defaultBlockState());
    private static final SurfaceRules.RuleSource ARCTIC_SNOW_BLOCK = SurfaceRules.state(AetherIIBlocks.ARCTIC_SNOW_BLOCK.defaultBlockState());
    private static final SurfaceRules.RuleSource MOSSY_HOLYSTONE = new NoisePalette3DPlacementRule(AetherIIBlocks.MOSSY_HOLYSTONE.defaultBlockState(), 3, 10, 0.045);
    private static final SurfaceRules.RuleSource PACKED_ICE = new NoisePalette3DPlacementRule(AetherIIBlocks.ARCTIC_PACKED_ICE.defaultBlockState(), 3, 10, 0.075);
    private static final SurfaceRules.RuleSource FERROSITE = new NoisePalette3DPlacementRule(AetherIIBlocks.FERROSITE.defaultBlockState(), 9, 20, 0.05);
//    private static final SurfaceRules.RuleSource RUSTED_FERROSITE = new NoisePalette3DPlacementRule(AetherIIBlocks.RUSTED_FERROSITE.defaultBlockState(), 1, 9, 0.03);
    private static final SurfaceRules.RuleSource IRRADIATED_HOLYSTONE = new NoisePalette3DPlacementRule(AetherIIBlocks.IRRADIATED_HOLYSTONE.defaultBlockState(), 3, 10, 0.045);
    private static final SurfaceRules.RuleSource ICHORITE = new NoisePalette3DPlacementRule(AetherIIBlocks.ICHORITE.defaultBlockState(), 16, 12, 0.075);
    private static final SurfaceRules.RuleSource QUICKSOIL = SurfaceRules.state(AetherIIBlocks.QUICKSOIL.defaultBlockState());

    public static SurfaceRules.RuleSource surfaceRules(HolderGetter<Biome> biomes) {
        SurfaceRules.RuleSource surface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.BATTLEGROUND_WASTES), ENCHANTED_AETHER_GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.CONTAMINATED_JUNGLE), ENCHANTED_AETHER_GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), AETHER_GRASS_BLOCK),
                AETHER_DIRT);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.FRIGID_SIERRA),
                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(AetherIINoises.ARCTIC_SNOW, -0.5D, 0.35D),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ARCTIC_SNOW_BLOCK))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.FRIGID_SIERRA),
                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(AetherIINoises.ARCTIC_SNOW, -0.5D, 0.35D),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, ARCTIC_SNOW_BLOCK))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.BATTLEGROUND_WASTES, HolyIslesBiomes.CONTAMINATED_JUNGLE),
                        SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("aether_grass_block", VerticalAnchor.belowTop(276), VerticalAnchor.belowTop(272))),
                                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.noiseCondition2d(AetherIINoises.QUICKSOIL_IRRADIATED, -0.5D, 0.5D)),
                                        SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.steep()),
                                                SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 2, CaveSurface.FLOOR), QUICKSOIL))))),

                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("aether_grass_block", VerticalAnchor.belowTop(276), VerticalAnchor.belowTop(272))),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, surface)),

                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("aether_dirt", VerticalAnchor.belowTop(272), VerticalAnchor.belowTop(272))),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, AETHER_DIRT)),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.HESTVEIL_CAVERNS), ICHORITE),

                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("undershale", VerticalAnchor.absolute(89), VerticalAnchor.absolute(101)), UNDERSHALE),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.FLOURISHING_FIELD), MOSSY_HOLYSTONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.VERDANT_WOODS), MOSSY_HOLYSTONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.SHROUDED_FOREST), MOSSY_HOLYSTONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.SHIMMERING_BASIN), MOSSY_HOLYSTONE),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.FRIGID_SIERRA), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.ENDURING_WOODLAND), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.FROZEN_LAKES), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.SHEER_TUNDRA), PACKED_ICE),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.MAGNETIC_SCAR), FERROSITE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.TURQUOISE_FOREST), FERROSITE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.GLISTENING_SWAMP), FERROSITE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.VIOLET_HIGHWOODS), FERROSITE),

//                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HighlandsBiomes.MAGNETIC_SCAR), RUSTED_FERROSITE),
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HighlandsBiomes.TURQUOISE_FOREST), RUSTED_FERROSITE),
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HighlandsBiomes.GLISTENING_SWAMP), RUSTED_FERROSITE),
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HighlandsBiomes.VIOLET_HIGHWOODS), RUSTED_FERROSITE) //todo

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.CONTAMINATED_JUNGLE), IRRADIATED_HOLYSTONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, HolyIslesBiomes.BATTLEGROUND_WASTES), IRRADIATED_HOLYSTONE)
        );
    }
}