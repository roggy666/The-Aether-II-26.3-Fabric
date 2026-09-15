package com.aetherteam.aetherii.recipe.display.slot;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public class AetherIISlotDisplays {
    public static final SlotDisplay.Type<AmberFuel> AMBER_FUEL = register("amber_fuel", AmberFuel.TYPE);

    private static <T extends SlotDisplay> SlotDisplay.Type<T> register(String name, SlotDisplay.Type<T> type) {
        return Registry.register(BuiltInRegistries.SLOT_DISPLAY, Identifier.fromNamespaceAndPath(AetherII.MODID, name), type);
    }

    public static void init() {}
}
