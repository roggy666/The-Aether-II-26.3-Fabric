package com.aetherteam.aetherii.recipe.recipes;

import net.minecraft.world.item.crafting.RecipeInput;
import java.util.List;
import java.util.ArrayList;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.server.level.ServerPlayer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import com.aetherteam.aetherii.network.packet.clientbound.RecipeSyncPacket;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.recipe.recipes.block.*;
import com.aetherteam.aetherii.recipe.recipes.item.AlkahestPurificationRecipe;
import com.aetherteam.aetherii.recipe.recipes.item.AltarEnchantingRecipe;
import com.aetherteam.aetherii.recipe.recipes.item.HourglassRestoringRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class AetherIIRecipeTypes {
    private static <T extends Recipe<?>> RecipeType<T> register(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(AetherII.MODID, name);
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, id, new RecipeType<T>() {
            @Override
            public String toString() {
                return id.toString();
            }
        });
    }

    public static final RecipeType<AmbrosiumRecipe> AMBROSIUM_ENCHANTING = register("ambrosium_enchanting");
    public static final RecipeType<IrradiationRecipe> DUST_IRRADIATION = register("dust_irradiation");
    public static final RecipeType<AlkahestCorrosionRecipe> ALKAHEST_CORROSION = register("alkahest_corrosion");
    public static final RecipeType<SwetGelRecipe> SWET_GEL_CONVERSION = register("swet_gel_conversion");
    public static final RecipeType<IcestoneFreezableRecipe> ICESTONE_FREEZABLE = register("icestone_freezable");
    public static final RecipeType<AccessoryFreezableRecipe> ACCESSORY_FREEZABLE = register("accessory_freezable");

    public static final RecipeType<HourglassRestoringRecipe> HOURGLASS_RESTORING = register("hourglass_restoring");
    public static final RecipeType<AltarEnchantingRecipe> ALTAR_ENCHANTING = register("altar_enchanting");
    public static final RecipeType<AlkahestPurificationRecipe> ALKAHEST_PURIFICATION = register("alkahest_purification");

    public static void init() {}

    /**
     * Sends the recipes of the mod's custom types to a player (NeoForge's {@code OnDatapackSyncEvent#sendRecipes}).
     */
    public static void syncRecipes(ServerPlayer player) {
        if (player.level().getServer() == null || !ServerPlayNetworking.canSend(player, RecipeSyncPacket.TYPE)) {
            return;
        }
        RecipeManager recipeManager = player.level().getServer().getRecipeManager();
        List<RecipeHolder<?>> recipes = new ArrayList<>();
        addRecipes(recipes, recipeManager, ALKAHEST_PURIFICATION);
        addRecipes(recipes, recipeManager, ALKAHEST_CORROSION);
        addRecipes(recipes, recipeManager, ALTAR_ENCHANTING);
        addRecipes(recipes, recipeManager, AMBROSIUM_ENCHANTING);
        addRecipes(recipes, recipeManager, DUST_IRRADIATION);
        addRecipes(recipes, recipeManager, HOURGLASS_RESTORING);
        addRecipes(recipes, recipeManager, ICESTONE_FREEZABLE);
        addRecipes(recipes, recipeManager, SWET_GEL_CONVERSION);
        ServerPlayNetworking.send(player, new RecipeSyncPacket(recipes));
    }

    private static <I extends RecipeInput, T extends Recipe<I>> void addRecipes(List<RecipeHolder<?>> target, RecipeManager manager, RecipeType<T> type) {
        target.addAll(manager.getAllOfType(type));
    }
}
