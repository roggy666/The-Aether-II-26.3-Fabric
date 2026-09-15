package com.aetherteam.aetherii.recipe.book;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeBookCategory;

public class AetherIIRecipeBookCategories {
    public static final RecipeBookCategory AMBER_HOURGLASS_RESTORATION = register("amber_hourglass_restoration", new RecipeBookCategory());
    public static final RecipeBookCategory AMBER_HOURGLASS_UNCRAFTING = register("amber_hourglass_uncrafting", new RecipeBookCategory());

    public static final RecipeBookCategory ALTAR_FOOD = register("altar_food", new RecipeBookCategory());
    public static final RecipeBookCategory ALTAR_BLOCKS = register("altar_blocks", new RecipeBookCategory());
    public static final RecipeBookCategory ALTAR_REPAIRING = register("altar_repairing", new RecipeBookCategory());
    public static final RecipeBookCategory ALTAR_MISC = register("altar_misc", new RecipeBookCategory());

    public static final RecipeBookCategory ALKAHEST_PURIFIER_ITEMS = register("alkahest_purifier_items", new RecipeBookCategory());
    public static final RecipeBookCategory ALKAHEST_PURIFIER_BLOCKS = register("alkahest_purifier_blocks", new RecipeBookCategory());

    public static final RecipeBookCategory AMBER_HOURGLASS_SEARCH = new RecipeBookCategory();
    public static final RecipeBookCategory ALTAR_SEARCH = new RecipeBookCategory();
    public static final RecipeBookCategory ALKAHEST_PURIFIER_SEARCH = new RecipeBookCategory();

    private static RecipeBookCategory register(String name, RecipeBookCategory category) {
        return Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, Identifier.fromNamespaceAndPath(AetherII.MODID, name), category);
    }

    public static void init() {}
}
