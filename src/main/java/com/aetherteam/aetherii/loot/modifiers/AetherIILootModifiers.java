package com.aetherteam.aetherii.loot.modifiers;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.nitrogen.loot.modifiers.NitrogenLootModifiers;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public class AetherIILootModifiers {
    public static final MapCodec<DoubleDropsModifier> DOUBLE_DROPS = Registry.register(
            NitrogenLootModifiers.GLOBAL_LOOT_MODIFIERS,
            Identifier.fromNamespaceAndPath(AetherII.MODID, "double_drops"),
            DoubleDropsModifier.CODEC
    );

    public static void init() {}
}
