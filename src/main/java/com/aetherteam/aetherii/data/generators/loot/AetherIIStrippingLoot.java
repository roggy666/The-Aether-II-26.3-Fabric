package com.aetherteam.aetherii.data.generators.loot;

import com.aetherteam.aetherii.data.providers.AetherIISimpleLootSubProvider;
import com.aetherteam.aetherii.loot.AetherIILootContexts;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.loot.AetherIILoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;

import java.util.function.BiConsumer;

public class AetherIIStrippingLoot extends AetherIISimpleLootSubProvider {

    public AetherIIStrippingLoot(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AetherIILootContexts.STRIPPING);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        builder.accept(AetherIILoot.STRIP_MOSSY_WISPROOT_BASE, LootTable.lootTable()
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.BRYALINN_MOSS_VINES)
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));
        builder.accept(AetherIILoot.STRIP_MOSSY_WISPROOT, LootTable.lootTable()
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.BRYALINN_MOSS_VINES)
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(2)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));
        builder.accept(AetherIILoot.STRIP_AMBEROOT_DEPOSIT, LootTable.lootTable()
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIItems.GOLDEN_AMBER)
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));
    }
}