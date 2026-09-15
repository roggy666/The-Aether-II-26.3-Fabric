package com.aetherteam.aetherii.recipe.display;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

public class AetherIIRecipeDisplays {
    public static final RecipeDisplay.Type<AmberHourglassRecipeDisplay> AMBER_HOURGLASS = register("amber_hourglass", AmberHourglassRecipeDisplay.TYPE);
    public static final RecipeDisplay.Type<AltarRecipeDisplay> ALTAR = register("altar", AltarRecipeDisplay.TYPE);
    public static final RecipeDisplay.Type<AlkahestPurifierRecipeDisplay> ALKAHEST_PURIFIER = register("alkahest_purifier", AlkahestPurifierRecipeDisplay.TYPE);

    private static <T extends RecipeDisplay> RecipeDisplay.Type<T> register(String name, RecipeDisplay.Type<T> type) {
        return Registry.register(BuiltInRegistries.RECIPE_DISPLAY, Identifier.fromNamespaceAndPath(AetherII.MODID, name), type);
    }

    public static void init() {}
}