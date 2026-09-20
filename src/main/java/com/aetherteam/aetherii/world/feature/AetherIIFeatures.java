package com.aetherteam.aetherii.world.feature;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;

public class AetherIIFeatures {
    public static final MapCodec<MergedFeature> MERGED = register("merged", MergedFeature.CODEC);
    public static final MapCodec<AetherGrassFeature> AETHER_GRASS = register("aether_grass", AetherGrassFeature.CODEC);
    public static final MapCodec<AetherFlowerFeature> AETHER_FLOWER = register("aether_flower", AetherFlowerFeature.CODEC);
    public static final MapCodec<CoastFeature> COAST = register("coast", CoastFeature.CODEC);
    public static final MapCodec<AetherLakeFeature> LAKE = register("lake", AetherLakeFeature.CODEC);
    public static final MapCodec<NoiseLakeFeature> NOISE_LAKE = register("noise_lake", NoiseLakeFeature.CODEC);
    public static final MapCodec<FerrositeSpikeFeature> FERROSITE_SPIKE = register("ferrosite_spike", FerrositeSpikeFeature.CODEC);
    public static final MapCodec<FerrositePillarFeature> FERROSITE_PILLAR = register("ferrosite_pillar", FerrositePillarFeature.CODEC);
    public static final MapCodec<ArcticIceSpikeFeature> ARCTIC_ICE_SPIKE = register("arctic_ice_spike", ArcticIceSpikeFeature.CODEC);
    public static final MapCodec<MoaNestFeature> MOA_NEST = register("moa_nest", MoaNestFeature.CODEC);
    public static final MapCodec<OrangeTreeFeature> ORANGE_TREE = register("orange_tree", OrangeTreeFeature.CODEC);
    public static final MapCodec<BrettlPlantFeature> BRETTL_PLANT = register("brettl_plant", BrettlPlantFeature.CODEC);
    public static final MapCodec<AercloudFeature> AERCLOUD = register("aercloud", AercloudFeature.CODEC);
    public static final MapCodec<ArcticSnowAndFreezeFeature> FREEZE_TOP_LAYER_ARCTIC = register("freeze_top_layer_arctic", ArcticSnowAndFreezeFeature.CODEC);
    public static final MapCodec<TundraSnowAndFreezeFeature> FREEZE_TOP_LAYER_TUNDRA = register("freeze_top_layer_tundra", TundraSnowAndFreezeFeature.CODEC);
    public static final MapCodec<CloudbedFeature> CLOUDBED = register("cloudbed", CloudbedFeature.CODEC);
    public static final MapCodec<CorroboniteOreFeature> CORROBONITE_ORE = register("corrobonite_ore", CorroboniteOreFeature.CODEC);
    public static final MapCodec<BoulderFeature> BOULDER = register("boulder", BoulderFeature.CODEC);
    public static final MapCodec<FallenLogFeature> FALLEN_LOG = register("fallen_log", FallenLogFeature.CODEC);
    public static final MapCodec<MossVinesFeature> MOSS_VINES = register("moss_vines", MossVinesFeature.CODEC);
    public static final MapCodec<ArilumFeature> ARILUM = register("arilum", ArilumFeature.CODEC);
    public static final MapCodec<AlkahestPoolFeature> ALKAHEST_POOL = register("alkahest_pool", AlkahestPoolFeature.CODEC);
    public static final MapCodec<HestveilFeature> HESTVEIL = register("hestveil", HestveilFeature.CODEC);
    public static final MapCodec<PointedStoneFeature> POINTED_STONE = register("pointed_stone", PointedStoneFeature.CODEC);
    public static final MapCodec<CraterFeature> CRATER = register("crater", CraterFeature.CODEC);
    public static final MapCodec<TreeMossCoverFeature> TREE_MOSS_COVER = register("tree_moss_cover", TreeMossCoverFeature.CODEC);
    public static final MapCodec<SmallMagneticShroomFeature> SMALL_MAGNETIC_SHROOM = register("small_magnetic_shroom", SmallMagneticShroomFeature.CODEC);
    public static final MapCodec<HugeMagneticShroomFeature> HUGE_MAGNETIC_SHROOM = register("huge_magnetic_shroom", HugeMagneticShroomFeature.CODEC);
    public static final MapCodec<LargeShelfMushroom> LARGE_SHELF_MUSHROOM = register("large_shelf_mushroom", LargeShelfMushroom.CODEC);
    public static final MapCodec<StructureCoverFeature> STRUCTURE_COVER = register("structure_cover", StructureCoverFeature.CODEC);

    private static <F extends Feature> MapCodec<F> register(String name, MapCodec<F> codec) {
        return Registry.register(BuiltInRegistries.FEATURE_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), codec);
    }

    public static void init() {}
}
