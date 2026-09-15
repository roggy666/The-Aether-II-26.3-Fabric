package com.aetherteam.aetherii.world.tree.foliage;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.world.tree.foliage.amberoot.AmberootFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.amberoot.LargeAmberootFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.amberoot.SingularAmberootFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.greatroot.GreatboaFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.greatroot.GreatoakFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.greatroot.GreatrootFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.skyroot.*;
import com.aetherteam.aetherii.world.tree.foliage.wisproot.WisprootFoliagePlacer;
import com.aetherteam.aetherii.world.tree.foliage.wisproot.WisptopFoliagePlacer;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class AetherIIFoliagePlacerTypes {
    public static final FoliagePlacerType<LargeSkyrootFoliagePlacer> LARGE_SKYROOT_FOLIAGE_PLACER = register("large_skyroot_foliage_placer", LargeSkyrootFoliagePlacer.CODEC);
    public static final FoliagePlacerType<NestSkyrootFoliagePlacer> NEST_SKYROOT_FOLIAGE_PLACER = register("nest_skyroot_foliage_placer", NestSkyrootFoliagePlacer.CODEC);
    public static final FoliagePlacerType<SkyplaneFoliagePlacer> SKYPLANE_FOLIAGE_PLACER = register("skyplane_foliage_placer", SkyplaneFoliagePlacer.CODEC);
    public static final FoliagePlacerType<SkybirchFoliagePlacer> SKYBIRCH_FOLIAGE_PLACER = register("skybirch_foliage_placer", SkybirchFoliagePlacer.CODEC);
    public static final FoliagePlacerType<SkypineFoliagePlacer> SKYPINE_FOLIAGE_PLACER = register("skypine_foliage_placer", SkypineFoliagePlacer.CODEC);

    public static final FoliagePlacerType<WisprootFoliagePlacer> WISPROOT_FOLIAGE_PLACER = register("wisproot_foliage_placer", WisprootFoliagePlacer.CODEC);
    public static final FoliagePlacerType<WisptopFoliagePlacer> WISPTOP_FOLIAGE_PLACER = register("wisptop_foliage_placer", WisptopFoliagePlacer.CODEC);

    public static final FoliagePlacerType<GreatrootFoliagePlacer> GREATROOT_FOLIAGE_PLACER = register("greatroot_foliage_placer", GreatrootFoliagePlacer.CODEC);
    public static final FoliagePlacerType<GreatoakFoliagePlacer> GREATOAK_FOLIAGE_PLACER = register("greatoak_foliage_placer", GreatoakFoliagePlacer.CODEC);
    public static final FoliagePlacerType<GreatboaFoliagePlacer> GREATBOA_FOLIAGE_PLACER = register("greatboa_foliage_placer", GreatboaFoliagePlacer.CODEC);

    public static final FoliagePlacerType<AmberootFoliagePlacer> AMBEROOT_FOLIAGE_PLACER = register("amberoot_foliage_placer", AmberootFoliagePlacer.CODEC);
    public static final FoliagePlacerType<SingularAmberootFoliagePlacer> SINGULAR_AMBEROOT_FOLIAGE_PLACER = register("singular_amberoot_foliage_placer", SingularAmberootFoliagePlacer.CODEC);
    public static final FoliagePlacerType<LargeAmberootFoliagePlacer> LARGE_AMBEROOT_FOLIAGE_PLACER = register("large_amberoot_foliage_placer", LargeAmberootFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), new FoliagePlacerType<>(codec));
    }

    public static void init() {}
}