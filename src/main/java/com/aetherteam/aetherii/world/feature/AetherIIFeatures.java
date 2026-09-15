package com.aetherteam.aetherii.world.feature;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.world.feature.configuration.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class AetherIIFeatures {
    public static final Feature<MergedConfiguration> MERGED = register("merged", new MergedFeature(MergedConfiguration.CODEC));
    public static final Feature<SimpleBlockConfiguration> AETHER_GRASS = register("aether_grass", new AetherGrassFeature(SimpleBlockConfiguration.CODEC));
    public static final Feature<SimpleBlockConfiguration> AETHER_FLOWER = register("aether_flower", new AetherFlowerFeature(SimpleBlockConfiguration.CODEC));
    public static final Feature<CoastConfiguration> COAST = register("coast", new CoastFeature(CoastConfiguration.CODEC));
    public static final Feature<AetherLakeConfiguration> LAKE = register("lake", new AetherLakeFeature(AetherLakeConfiguration.CODEC));
    public static final Feature<NoiseLakeConfiguration> NOISE_LAKE = register("noise_lake", new NoiseLakeFeature(NoiseLakeConfiguration.CODEC));
    public static final Feature<FerrositeSpikeConfiguration> FERROSITE_SPIKE = register("ferrosite_spike", new FerrositeSpikeFeature(FerrositeSpikeConfiguration.CODEC));
    public static final Feature<FerrositePillarConfiguration> FERROSITE_PILLAR = register("ferrosite_pillar", new FerrositePillarFeature(FerrositePillarConfiguration.CODEC));
    public static final Feature<ArcticIceSpikeConfiguration> ARCTIC_ICE_SPIKE = register("arctic_ice_spike", new ArcticIceSpikeFeature(ArcticIceSpikeConfiguration.CODEC));
    public static final Feature<MoaNestConfiguration> MOA_NEST = register("moa_nest", new MoaNestFeature(MoaNestConfiguration.CODEC));
    public static final Feature<SimpleBlockConfiguration> ORANGE_TREE = register("orange_tree", new OrangeTreeFeature(SimpleBlockConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> BRETTL_PLANT = register("brettl_plant", new BrettlPlantFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<AercloudConfiguration> AERCLOUD = register("aercloud", new AercloudFeature(AercloudConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> FREEZE_TOP_LAYER_ARCTIC = register("freeze_top_layer_arctic", new ArcticSnowAndFreezeFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> FREEZE_TOP_LAYER_TUNDRA = register("freeze_top_layer_tundra", new TundraSnowAndFreezeFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<CloudbedConfiguration> CLOUDBED = register("cloudbed", new CloudbedFeature(CloudbedConfiguration.CODEC));
    public static final Feature<OreConfiguration> CORROBONITE_ORE = register("corrobonite_ore", new CorroboniteOreFeature(OreConfiguration.CODEC));
    public static final Feature<BoulderConfiguration> BOULDER = register("boulder", new BoulderFeature(BoulderConfiguration.CODEC));
    public static final Feature<FallenLogConfiguration> FALLEN_LOG = register("fallen_log", new FallenLogFeature(FallenLogConfiguration.CODEC));
    public static final Feature<MossVinesConfiguration> MOSS_VINES = register("moss_vines", new MossVinesFeature(MossVinesConfiguration.CODEC));
    public static final Feature<ArilumConfiguration> ARILUM = register("arilum", new ArilumFeature(ArilumConfiguration.CODEC));
    public static final Feature<AlkahestPoolConfiguration> ALKAHEST_POOL = register("alkahest_pool", new AlkahestPoolFeature(AlkahestPoolConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> HESTVEIL = register("hestveil", new HestveilFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<PointedStoneConfiguration> POINTED_STONE = register("pointed_stone", new PointedStoneFeature(PointedStoneConfiguration.CODEC));
    public static final Feature<CraterConfiguration> CRATER = register("crater", new CraterFeature(CraterConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> TREE_MOSS_COVER = register("tree_moss_cover", new TreeMossCoverFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<BigMagneticShroomConfiguration> SMALL_MAGNETIC_SHROOM = register("small_magnetic_shroom", new SmallMagneticShroomFeature(BigMagneticShroomConfiguration.CODEC));
    public static final Feature<BigMagneticShroomConfiguration> HUGE_MAGNETIC_SHROOM = register("huge_magnetic_shroom", new HugeMagneticShroomFeature(BigMagneticShroomConfiguration.CODEC));
    public static final Feature<LargeShelfMushroomConfiguration> LARGE_SHELF_MUSHROOM = register("large_shelf_mushroom", new LargeShelfMushroom(LargeShelfMushroomConfiguration.CODEC));
    public static final Feature<StructureCoverConfiguration> STRUCTURE_COVER = register("structure_cover", new StructureCoverFeature(StructureCoverConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), feature);
    }

    public static void init() {}
}