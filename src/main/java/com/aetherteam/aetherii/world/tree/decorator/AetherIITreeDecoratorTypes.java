package com.aetherteam.aetherii.world.tree.decorator;

import com.aetherteam.aetherii.AetherII;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class AetherIITreeDecoratorTypes {
    public static final TreeDecoratorType<GroundFeatureDecorator> GROUND_FEATURE = register("ground_feature", GroundFeatureDecorator.CODEC);
    public static final TreeDecoratorType<SnowDecorator> SNOW = register("snow", SnowDecorator.CODEC);
    public static final TreeDecoratorType<WisprootTreeDecorator> WISPROOT = register("wisproot", WisprootTreeDecorator.CODEC);
    public static final TreeDecoratorType<SimpleTrunkTreeDecorator> SIMPLE_TRUNK = register("simple_trunk", SimpleTrunkTreeDecorator.CODEC);
    public static final TreeDecoratorType<IrradiationTreeDecorator> IRRADIATION = register("irradiation", IrradiationTreeDecorator.CODEC);
    public static final TreeDecoratorType<AlterGroundTagDecorator> ALTER_GROUND_TAG = register("alter_ground_tag", AlterGroundTagDecorator.CODEC);
    public static final TreeDecoratorType<MossDecorator> MOSS = register("moss", MossDecorator.CODEC);
    public static final TreeDecoratorType<ShroudedCanopyDecorator> SHROUDED_CANOPY = register("shrouded_canopy", ShroudedCanopyDecorator.CODEC);

    private static <P extends TreeDecorator> TreeDecoratorType<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), new TreeDecoratorType<>(codec));
    }

    public static void init() {}
}