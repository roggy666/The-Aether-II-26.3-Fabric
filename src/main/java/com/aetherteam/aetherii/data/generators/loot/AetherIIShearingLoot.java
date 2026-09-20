package com.aetherteam.aetherii.data.generators.loot;

import com.aetherteam.aetherii.data.providers.AetherIISimpleLootSubProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import com.aetherteam.aetherii.advancement.predicate.SheepuffPredicate;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.entity.passive.Kirrid;
import com.aetherteam.aetherii.entity.passive.Sheepuff;
import com.aetherteam.aetherii.loot.AetherIILoot;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

import java.util.function.BiConsumer;

public class AetherIIShearingLoot extends AetherIISimpleLootSubProvider {

    public AetherIIShearingLoot(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, LootContextParamSets.SHEARING);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder) {
        Kirrid.KirridColor.CLOUDWOOL_BY_KIRRID_COLOR.forEach((color, wool) -> {
            builder.accept(AetherIILoot.SHEARING_HIGHFIELDS_KIRRID_WOOL_BY_DYE.get(color), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(1, 3)).add(LootItem.lootTableItem(wool))));
            builder.accept(AetherIILoot.SHEARING_MAGNETIC_KIRRID_WOOL_BY_DYE.get(color), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(1, 3)).add(LootItem.lootTableItem(wool))));
            builder.accept(AetherIILoot.SHEARING_ARCTIC_KIRRID_WOOL_BY_DYE.get(color), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(1, 3)).add(LootItem.lootTableItem(wool))));
        });

        builder.accept(AetherIILoot.SHEARING_HIGHFIELDS_KIRRID_WOOL_UNDYED, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.CLOUDWOOL))));
        builder.accept(AetherIILoot.SHEARING_MAGNETIC_KIRRID_WOOL_UNDYED, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.CLOUDWOOL))));
        builder.accept(AetherIILoot.SHEARING_ARCTIC_KIRRID_WOOL_UNDYED, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.CLOUDWOOL))));

        builder.accept(AetherIILoot.SHEARING_HIGHFIELDS_KIRRID, LootTable.lootTable().withPool(AetherIIEntityLoot.createKirridDispatchPool(this.lootTables, AetherIILoot.SHEARING_HIGHFIELDS_KIRRID_WOOL_BY_DYE, AetherIILoot.SHEARING_HIGHFIELDS_KIRRID_WOOL_UNDYED)));
        builder.accept(AetherIILoot.SHEARING_MAGNETIC_KIRRID, LootTable.lootTable().withPool(AetherIIEntityLoot.createKirridDispatchPool(this.lootTables, AetherIILoot.SHEARING_MAGNETIC_KIRRID_WOOL_BY_DYE, AetherIILoot.SHEARING_MAGNETIC_KIRRID_WOOL_UNDYED)));
        builder.accept(AetherIILoot.SHEARING_ARCTIC_KIRRID, LootTable.lootTable().withPool(AetherIIEntityLoot.createKirridDispatchPool(this.lootTables, AetherIILoot.SHEARING_ARCTIC_KIRRID_WOOL_BY_DYE, AetherIILoot.SHEARING_ARCTIC_KIRRID_WOOL_UNDYED)));

        Sheepuff.SheepuffColor.CLOUDWOOL_BY_SHEEPUFF_COLOR.forEach(
                (color, wool) -> builder.accept(AetherIILoot.SHEARING_SHEEPUFF_WOOL_BY_DYE.get(color),
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(1, 3)).add(LootItem.lootTableItem(wool)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().put(SheepuffPredicate.CODEC, SheepuffPredicate.isPuffed(false)))))
                                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(2, 4)).add(LootItem.lootTableItem(wool)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().put(SheepuffPredicate.CODEC, SheepuffPredicate.isPuffed(true)))))
                ));
        builder.accept(AetherIILoot.SHEARING_SHEEPUFF, LootTable.lootTable().withPool(AetherIIEntityLoot.createSheepuffDispatchPool(this.lootTables, AetherIILoot.SHEARING_SHEEPUFF_WOOL_BY_DYE)));
    }
}
