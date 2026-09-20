package com.aetherteam.aetherii.world.tree;

import com.aetherteam.aetherii.data.resources.registries.holyisles.HolyIslesConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.Map;

public class AetherIITreeGrowers {
    // Skyroot saplings grow the short tree 4 out of 5 times and the large one otherwise (the old 0.2 secondary chance).
    public static final TreeGrower SKYROOT = new TreeGrower(
            "skyroot",
            WeightedList.of(new Weighted<>(HolyIslesConfiguredFeatures.SHORT_SKYROOT, 4), new Weighted<>(HolyIslesConfiguredFeatures.LARGE_SKYROOT, 1)),
            WeightedList.of(),
            WeightedList.of(),
            HolyIslesConfiguredFeatures.SHORT_SKYROOT
    );
    public static final TreeGrower IRRADIATED_SKYROOT = new TreeGrower(
            "irradiated_skyroot",
            WeightedList.of(new Weighted<>(HolyIslesConfiguredFeatures.SKYROOT_IRRADIATED, 4), new Weighted<>(HolyIslesConfiguredFeatures.LARGE_SKYROOT_IRRADIATED, 1)),
            WeightedList.of(),
            WeightedList.of(),
            HolyIslesConfiguredFeatures.SKYROOT_IRRADIATED
    );
    public static final TreeGrower SKYPLANE = tree("skyplane", HolyIslesConfiguredFeatures.SKYPLANE);
    public static final TreeGrower IRRADIATED_SKYPLANE = tree("irradiated_skyplane", HolyIslesConfiguredFeatures.SKYPLANE_IRRADIATED);
    public static final TreeGrower SKYBIRCH = tree("skybirch", HolyIslesConfiguredFeatures.SKYBIRCH);
    public static final TreeGrower IRRADIATED_SKYBIRCH = tree("irradiated_skybirch", HolyIslesConfiguredFeatures.SKYBIRCH_IRRADIATED);
    public static final TreeGrower SKYPINE = tree("skypine", HolyIslesConfiguredFeatures.SKYPINE);
    public static final TreeGrower IRRADIATED_SKYPINE = tree("irradiated_skypine", HolyIslesConfiguredFeatures.SKYPINE_IRRADIATED);
    public static final TreeGrower WISPROOT = tree("wisproot", HolyIslesConfiguredFeatures.WISPROOT);
    public static final TreeGrower IRRADIATED_WISPROOT = tree("irradiated_wisproot", HolyIslesConfiguredFeatures.WISPROOT_IRRADIATED);
    public static final TreeGrower WISPTOP = tree("wisptop", HolyIslesConfiguredFeatures.WISPTOP);
    public static final TreeGrower IRRADIATED_WISPTOP = tree("irradiated_wisptop", HolyIslesConfiguredFeatures.WISPTOP_IRRADIATED);
    public static final TreeGrower GREATROOT = megaTree("greatroot", HolyIslesConfiguredFeatures.GREATROOT);
    public static final TreeGrower IRRADIATED_GREATROOT = megaTree("irradiated_greatroot", HolyIslesConfiguredFeatures.GREATROOT_IRRADIATED);
    public static final TreeGrower GREATOAK = megaTree("greatoak", HolyIslesConfiguredFeatures.GREATOAK);
    public static final TreeGrower IRRADIATED_GREATOAK = megaTree("irradiated_greatoak", HolyIslesConfiguredFeatures.GREATOAK_IRRADIATED);
    public static final TreeGrower GREATBOA = megaTree("greatboa", HolyIslesConfiguredFeatures.GREATBOA);
    public static final TreeGrower IRRADIATED_GREATBOA = megaTree("irradiated_greatboa", HolyIslesConfiguredFeatures.GREATBOA_IRRADIATED);
    public static final TreeGrower AMBEROOT = tree("amberoot", HolyIslesConfiguredFeatures.TREES_AMBEROOT_SPARSE);

    public static final Map<TreeGrower, TreeGrower> NORMAL_TO_IRRADIATED = Map.ofEntries(
            Map.entry(AetherIITreeGrowers.SKYROOT, AetherIITreeGrowers.IRRADIATED_SKYROOT),
            Map.entry(AetherIITreeGrowers.SKYPLANE, AetherIITreeGrowers.IRRADIATED_SKYPLANE),
            Map.entry(AetherIITreeGrowers.SKYBIRCH, AetherIITreeGrowers.IRRADIATED_SKYBIRCH),
            Map.entry(AetherIITreeGrowers.SKYPINE, AetherIITreeGrowers.IRRADIATED_SKYPINE),
            Map.entry(AetherIITreeGrowers.WISPROOT, AetherIITreeGrowers.IRRADIATED_WISPROOT),
            Map.entry(AetherIITreeGrowers.WISPTOP, AetherIITreeGrowers.IRRADIATED_WISPTOP),
            Map.entry(AetherIITreeGrowers.GREATROOT, AetherIITreeGrowers.IRRADIATED_GREATROOT),
            Map.entry(AetherIITreeGrowers.GREATOAK, AetherIITreeGrowers.IRRADIATED_GREATOAK),
            Map.entry(AetherIITreeGrowers.GREATBOA, AetherIITreeGrowers.IRRADIATED_GREATBOA)
    );

    /** A grower with a single 1x1 tree. */
    private static TreeGrower tree(String name, ResourceKey<Feature> tree) {
        return new TreeGrower(name, WeightedList.of(tree), WeightedList.of(), WeightedList.of(), tree);
    }

    /** A grower that only grows from a 2x2 sapling arrangement. */
    private static TreeGrower megaTree(String name, ResourceKey<Feature> megaTree) {
        return new TreeGrower(name, WeightedList.of(), WeightedList.of(megaTree), WeightedList.of(), null);
    }
}
