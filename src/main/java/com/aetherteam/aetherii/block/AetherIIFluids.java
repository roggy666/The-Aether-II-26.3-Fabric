package com.aetherteam.aetherii.block;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.block.fluid.AlkahestFluid;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.FlowingFluid;

public class AetherIIFluids {
    public static final FlowingFluid FLOWING_ALKAHEST = register("flowing_alkahest", new AlkahestFluid.Flowing());
    public static final FlowingFluid ALKAHEST = register("alkahest", new AlkahestFluid.Source());

    private static <T extends FlowingFluid> T register(String name, T fluid) {
        return Registry.register(BuiltInRegistries.FLUID, Identifier.fromNamespaceAndPath(AetherII.MODID, name), fluid);
    }

    public static void init() {}
}
