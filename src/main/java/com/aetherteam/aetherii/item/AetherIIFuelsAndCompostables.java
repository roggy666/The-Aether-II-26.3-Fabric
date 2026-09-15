package com.aetherteam.aetherii.item;

import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;

/**
 * Compostable chances and furnace fuel values. NeoForge shipped these as the {@code compostables} and
 * {@code furnace_fuels} data maps; on Fabric they are registered in code through the content registries.
 */
public class AetherIIFuelsAndCompostables {
    public static void register() {
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYROOT_LEAF_PILE, 0.018F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYPLANE_LEAF_PILE, 0.018F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYBIRCH_LEAF_PILE, 0.018F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYPINE_LEAF_PILE, 0.018F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.WISPROOT_LEAF_PILE, 0.018F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.WISPTOP_LEAF_PILE, 0.018F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.GREATROOT_LEAF_PILE, 0.018F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.GREATOAK_LEAF_PILE, 0.018F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.GREATBOA_LEAF_PILE, 0.018F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.AMBEROOT_LEAF_PILE, 0.018F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYROOT_LEAVES, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYPLANE_LEAVES, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYBIRCH_LEAVES, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYPINE_LEAVES, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.WISPROOT_LEAVES, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.WISPTOP_LEAVES, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.GREATROOT_LEAVES, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.GREATOAK_LEAVES, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.GREATBOA_LEAVES, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.AMBEROOT_LEAVES, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYROOT_SAPLING, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYPLANE_SAPLING, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYBIRCH_SAPLING, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SKYPINE_SAPLING, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.WISPROOT_SAPLING, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.WISPTOP_SAPLING, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.GREATROOT_SAPLING, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.GREATOAK_SAPLING, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.GREATBOA_SAPLING, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.AMBEROOT_SAPLING, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SHORT_AETHER_GRASS, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.MEDIUM_AETHER_GRASS, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.TALL_AETHER_GRASS, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.BRETTL_FLOWER, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.AETHER_FERN, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SHIELD_FERN, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.HESPEROSE, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.TARABLOOM, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.POASPROUT, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.SATIVAL_SHOOT, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.LILICHIME, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.BLADE_POA, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.AECHOR_CUTTING, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.CARRION_CUTTING, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.AETHER_BUSH, 0.5F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.BLUEBERRY_BUSH, 0.5F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.BLUEBERRY_BUSH_STEM, 0.5F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.ORANGE_TREE, 0.5F);
        CompostableRegistry.INSTANCE.add(AetherIIBlocks.BRETTL_GRASS_BUNDLE, 0.85F);
        CompostableRegistry.INSTANCE.add(AetherIIItems.BLUEBERRY, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIItems.ENCHANTED_BLUEBERRY, 0.5F);
        CompostableRegistry.INSTANCE.add(AetherIIItems.ORANGE, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIItems.ENCHANTED_ORANGE, 0.5F);
        CompostableRegistry.INSTANCE.add(AetherIIItems.WYNDBERRY, 0.3F);
        CompostableRegistry.INSTANCE.add(AetherIIItems.ENCHANTED_WYNDBERRY, 0.5F);
        CompostableRegistry.INSTANCE.add(AetherIIItems.GOLDEN_WYNDBERRY, 0.65F);
        CompostableRegistry.INSTANCE.add(AetherIIItems.SATIVAL_BULB, 0.3F);

        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(AetherIIItems.ARKENIUM_HESTVEIL_CANISTER, 5000);
            builder.add(AetherIIBlocks.AMBROSIUM_BLOCK, 16000);
            builder.add(AetherIIItems.IRRADIATED_DUST, 3500);
            builder.add(AetherIIItems.AMBROSIUM_SHARD, 1600);
            builder.add(AetherIIItems.SKYROOT_PINECONE, 400);
            builder.add(AetherIIBlocks.AETHER_BUSH, 100);
            builder.add(AetherIIBlocks.SKYROOT_PLANKS, 300);
            builder.add(AetherIITags.Items.SKYROOT_DECORATIVE_BLOCKS, 300);
            builder.add(AetherIIBlocks.GREATROOT_PLANKS, 300);
            builder.add(AetherIITags.Items.GREATROOT_DECORATIVE_BLOCKS, 300);
            builder.add(AetherIIBlocks.WISPROOT_PLANKS, 300);
            builder.add(AetherIITags.Items.WISPROOT_DECORATIVE_BLOCKS, 300);
            builder.add(AetherIIBlocks.AMBEROOT_PLANKS, 300);
            builder.add(AetherIITags.Items.AMBEROOT_DECORATIVE_BLOCKS, 300);
            builder.add(AetherIIBlocks.SKYROOT_BOOKSHELF, 300);
            builder.add(AetherIIBlocks.GREATROOT_BOOKSHELF, 300);
            builder.add(AetherIIBlocks.WISPROOT_BOOKSHELF, 300);
            builder.add(AetherIIBlocks.AMBEROOT_BOOKSHELF, 300);
            builder.add(AetherIIItems.SKYROOT_SHORTSWORD, 200);
        });
    }
}
