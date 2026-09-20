package com.aetherteam.aetherii.item;

import com.aetherteam.aetherii.data.resources.registries.AetherIIContextIntProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.item.component.Compostable;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.component.DataComponents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIBlocks;

/**
 * Compostable chances and furnace fuel values. Since 26.3 both are item data components
 * ({@code minecraft:compostable} / {@code minecraft:cooking_fuel}), applied here to the mod's default item components.
 */
public class AetherIIFuelsAndCompostables {
    public static void register() {
        DefaultItemComponentEvents.MODIFY.register(AetherIIFuelsAndCompostables::modifyComponents);
    }

    private static void modifyComponents(DefaultItemComponentEvents.ModifyContext context) {
        compostable(context, AetherIIBlocks.SKYROOT_LEAF_PILE, AetherIIContextIntProviders.COMPOSTABLE_VERY_LOW);
        compostable(context, AetherIIBlocks.SKYPLANE_LEAF_PILE, AetherIIContextIntProviders.COMPOSTABLE_VERY_LOW);
        compostable(context, AetherIIBlocks.SKYBIRCH_LEAF_PILE, AetherIIContextIntProviders.COMPOSTABLE_VERY_LOW);
        compostable(context, AetherIIBlocks.SKYPINE_LEAF_PILE, AetherIIContextIntProviders.COMPOSTABLE_VERY_LOW);
        compostable(context, AetherIIBlocks.WISPROOT_LEAF_PILE, AetherIIContextIntProviders.COMPOSTABLE_VERY_LOW);
        compostable(context, AetherIIBlocks.WISPTOP_LEAF_PILE, AetherIIContextIntProviders.COMPOSTABLE_VERY_LOW);
        compostable(context, AetherIIBlocks.GREATROOT_LEAF_PILE, AetherIIContextIntProviders.COMPOSTABLE_VERY_LOW);
        compostable(context, AetherIIBlocks.GREATOAK_LEAF_PILE, AetherIIContextIntProviders.COMPOSTABLE_VERY_LOW);
        compostable(context, AetherIIBlocks.GREATBOA_LEAF_PILE, AetherIIContextIntProviders.COMPOSTABLE_VERY_LOW);
        compostable(context, AetherIIBlocks.AMBEROOT_LEAF_PILE, AetherIIContextIntProviders.COMPOSTABLE_VERY_LOW);
        compostable(context, AetherIIBlocks.SKYROOT_LEAVES, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.SKYPLANE_LEAVES, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.SKYBIRCH_LEAVES, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.SKYPINE_LEAVES, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.WISPROOT_LEAVES, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.WISPTOP_LEAVES, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.GREATROOT_LEAVES, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.GREATOAK_LEAVES, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.GREATBOA_LEAVES, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.AMBEROOT_LEAVES, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.SKYROOT_SAPLING, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.SKYPLANE_SAPLING, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.SKYBIRCH_SAPLING, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.SKYPINE_SAPLING, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.WISPROOT_SAPLING, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.WISPTOP_SAPLING, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.GREATROOT_SAPLING, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.GREATOAK_SAPLING, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.GREATBOA_SAPLING, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.AMBEROOT_SAPLING, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.SHORT_AETHER_GRASS, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.MEDIUM_AETHER_GRASS, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.TALL_AETHER_GRASS, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.BRETTL_FLOWER, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIBlocks.AETHER_FERN, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIBlocks.SHIELD_FERN, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIBlocks.HESPEROSE, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIBlocks.TARABLOOM, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIBlocks.POASPROUT, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIBlocks.SATIVAL_SHOOT, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIBlocks.LILICHIME, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIBlocks.BLADE_POA, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIBlocks.AECHOR_CUTTING, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIBlocks.CARRION_CUTTING, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIBlocks.AETHER_BUSH, ContextIntProviders.COMPOSTABLE_LOW_MEDIUM);
        compostable(context, AetherIIBlocks.BLUEBERRY_BUSH, ContextIntProviders.COMPOSTABLE_LOW_MEDIUM);
        compostable(context, AetherIIBlocks.BLUEBERRY_BUSH_STEM, ContextIntProviders.COMPOSTABLE_LOW_MEDIUM);
        compostable(context, AetherIIBlocks.ORANGE_TREE, ContextIntProviders.COMPOSTABLE_LOW_MEDIUM);
        compostable(context, AetherIIBlocks.BRETTL_GRASS_BUNDLE, ContextIntProviders.COMPOSTABLE_MEDIUM_HIGH);
        compostable(context, AetherIIItems.BLUEBERRY, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIItems.ENCHANTED_BLUEBERRY, ContextIntProviders.COMPOSTABLE_LOW_MEDIUM);
        compostable(context, AetherIIItems.ORANGE, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIItems.ENCHANTED_ORANGE, ContextIntProviders.COMPOSTABLE_LOW_MEDIUM);
        compostable(context, AetherIIItems.WYNDBERRY, ContextIntProviders.COMPOSTABLE_LOW);
        compostable(context, AetherIIItems.ENCHANTED_WYNDBERRY, ContextIntProviders.COMPOSTABLE_LOW_MEDIUM);
        compostable(context, AetherIIItems.GOLDEN_WYNDBERRY, ContextIntProviders.COMPOSTABLE_MEDIUM);
        compostable(context, AetherIIItems.SATIVAL_BULB, ContextIntProviders.COMPOSTABLE_LOW);
        fuel(context, AetherIIItems.ARKENIUM_HESTVEIL_CANISTER, 5000);
        fuel(context, AetherIIBlocks.AMBROSIUM_BLOCK, 16000);
        fuel(context, AetherIIItems.IRRADIATED_DUST, 3500);
        fuel(context, AetherIIItems.AMBROSIUM_SHARD, 1600);
        fuel(context, AetherIIItems.SKYROOT_PINECONE, 400);
        fuel(context, AetherIIBlocks.AETHER_BUSH, 100);
        fuel(context, AetherIIBlocks.SKYROOT_PLANKS, 300);
        // members of AetherIITags.Items.SKYROOT_DECORATIVE_BLOCKS (tags are not loaded yet when default components are built)
        fuel(context, AetherIIBlocks.SKYROOT_FLOORBOARDS, 300);
        fuel(context, AetherIIBlocks.SKYROOT_HIGHLIGHT, 300);
        fuel(context, AetherIIBlocks.SKYROOT_SHINGLES, 300);
        fuel(context, AetherIIBlocks.SKYROOT_SMALL_SHINGLES, 300);
        fuel(context, AetherIIBlocks.SKYROOT_BASE_PLANKS, 300);
        fuel(context, AetherIIBlocks.SKYROOT_TOP_PLANKS, 300);
        fuel(context, AetherIIBlocks.SKYROOT_BASE_BEAM, 300);
        fuel(context, AetherIIBlocks.SKYROOT_TOP_BEAM, 300);
        fuel(context, AetherIIBlocks.SKYROOT_BEAM, 300);
        fuel(context, AetherIIBlocks.GREATROOT_PLANKS, 300);
        // members of AetherIITags.Items.GREATROOT_DECORATIVE_BLOCKS (tags are not loaded yet when default components are built)
        fuel(context, AetherIIBlocks.GREATROOT_FLOORBOARDS, 300);
        fuel(context, AetherIIBlocks.GREATROOT_HIGHLIGHT, 300);
        fuel(context, AetherIIBlocks.GREATROOT_SHINGLES, 300);
        fuel(context, AetherIIBlocks.GREATROOT_SMALL_SHINGLES, 300);
        fuel(context, AetherIIBlocks.GREATROOT_BASE_PLANKS, 300);
        fuel(context, AetherIIBlocks.GREATROOT_TOP_PLANKS, 300);
        fuel(context, AetherIIBlocks.GREATROOT_BASE_BEAM, 300);
        fuel(context, AetherIIBlocks.GREATROOT_TOP_BEAM, 300);
        fuel(context, AetherIIBlocks.GREATROOT_BEAM, 300);
        fuel(context, AetherIIBlocks.WISPROOT_PLANKS, 300);
        // members of AetherIITags.Items.WISPROOT_DECORATIVE_BLOCKS (tags are not loaded yet when default components are built)
        fuel(context, AetherIIBlocks.WISPROOT_FLOORBOARDS, 300);
        fuel(context, AetherIIBlocks.WISPROOT_HIGHLIGHT, 300);
        fuel(context, AetherIIBlocks.WISPROOT_SHINGLES, 300);
        fuel(context, AetherIIBlocks.WISPROOT_SMALL_SHINGLES, 300);
        fuel(context, AetherIIBlocks.WISPROOT_BASE_PLANKS, 300);
        fuel(context, AetherIIBlocks.WISPROOT_TOP_PLANKS, 300);
        fuel(context, AetherIIBlocks.WISPROOT_BASE_BEAM, 300);
        fuel(context, AetherIIBlocks.WISPROOT_TOP_BEAM, 300);
        fuel(context, AetherIIBlocks.WISPROOT_BEAM, 300);
        fuel(context, AetherIIBlocks.AMBEROOT_PLANKS, 300);
        // members of AetherIITags.Items.AMBEROOT_DECORATIVE_BLOCKS (tags are not loaded yet when default components are built)
        fuel(context, AetherIIBlocks.AMBEROOT_FLOORBOARDS, 300);
        fuel(context, AetherIIBlocks.AMBEROOT_HIGHLIGHT, 300);
        fuel(context, AetherIIBlocks.AMBEROOT_SHINGLES, 300);
        fuel(context, AetherIIBlocks.AMBEROOT_SMALL_SHINGLES, 300);
        fuel(context, AetherIIBlocks.AMBEROOT_BASE_PLANKS, 300);
        fuel(context, AetherIIBlocks.AMBEROOT_TOP_PLANKS, 300);
        fuel(context, AetherIIBlocks.AMBEROOT_BASE_BEAM, 300);
        fuel(context, AetherIIBlocks.AMBEROOT_TOP_BEAM, 300);
        fuel(context, AetherIIBlocks.AMBEROOT_BEAM, 300);
        fuel(context, AetherIIBlocks.SKYROOT_BOOKSHELF, 300);
        fuel(context, AetherIIBlocks.GREATROOT_BOOKSHELF, 300);
        fuel(context, AetherIIBlocks.WISPROOT_BOOKSHELF, 300);
        fuel(context, AetherIIBlocks.AMBEROOT_BOOKSHELF, 300);
        fuel(context, AetherIIItems.SKYROOT_SHORTSWORD, 200);
    }

    private static void compostable(DefaultItemComponentEvents.ModifyContext context, ItemLike item, ResourceKey<ContextIntProvider> layers) {
        context.modify(item.asItem(), builder -> builder.set(DataComponents.COMPOSTABLE, new Compostable(layers)));
    }

    private static void fuel(DefaultItemComponentEvents.ModifyContext context, ItemLike item, int burnTime) {
        context.modify(item.asItem(), builder -> builder.set(DataComponents.COOKING_FUEL,
                new CookingFuel(new ResolvableInt.Constant(burnTime), ResolvableFloat.fromKey(ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER))));
    }
}
