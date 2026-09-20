package com.aetherteam.aetherii.data.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.concurrent.CompletableFuture;

/**
 * Base for the mod's non-block, non-entity loot providers.
 */
public abstract class AetherIISimpleLootSubProvider extends SimpleFabricLootTableSubProvider {
    protected final HolderLookup.Provider registries;
    protected final HolderLookup.RegistryLookup<LootTable> lootTables;

    protected AetherIISimpleLootSubProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries, ContextKeySet paramSet) {
        super(output, registries, paramSet);
        this.registries = registries.join();
        this.lootTables = this.registries.lookupOrThrow(Registries.LOOT_TABLE);
    }

    /**
     * {@link net.minecraft.data.loot.LootTableSubProvider#run()} became abstract in 26.3 but Fabric's provider drives
     * {@link #generate} directly, so vanilla's entry point has nothing to do here.
     */
    @Override
    public void run() {
    }

    /**
     * Reference to another loot table (nested tables take holders since 26.3). Mirrors Fabric's own stand-alone
     * references for tables that are generated rather than looked up.
     */
    protected Holder<LootTable> lootTable(ResourceKey<LootTable> key) {
        return DatagenReferences.reference(this.lootTables, key);
    }
}
