package com.aetherteam.aetherii.data.generators.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import com.aetherteam.aetherii.data.providers.DatagenReferences;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootSubProvider;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import java.util.function.BiConsumer;
import java.util.Set;
import java.util.Optional;
import java.util.LinkedHashMap;
import java.util.HashSet;
import net.minecraft.core.registries.BuiltInRegistries;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.advancement.predicate.KirridPredicate;
import com.aetherteam.aetherii.advancement.predicate.SheepuffPredicate;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.entity.AetherIIEntityTypes;
import com.aetherteam.aetherii.entity.passive.Kirrid;
import com.aetherteam.aetherii.entity.passive.Sheepuff;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.loot.AetherIILoot;
import com.aetherteam.aetherii.loot.conditions.PlayerGrownCondition;
import com.aetherteam.aetherii.loot.functions.GelDropsFunction;
import com.aetherteam.aetherii.loot.functions.SugarDropsFunction;
import net.minecraft.advancements.predicates.entity.EntityFlagsPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;

import java.util.Iterator;
import java.util.Map;

public class AetherIIEntityLoot extends FabricEntityLootSubProvider {
    private final HolderLookup.RegistryLookup<LootTable> lootTables;

    public AetherIIEntityLoot(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
        this.lootTables = registries.join().lookupOrThrow(Registries.LOOT_TABLE);
    }

    /** Reference to another generated loot table (nested tables take holders since 26.3). */
    private static Holder<LootTable> lootTable(HolderLookup.RegistryLookup<LootTable> lootTables, ResourceKey<LootTable> key) {
        return DatagenReferences.reference(lootTables, key);
    }

    @Override
    public void generate() {
        this.add(AetherIIEntityTypes.FLYING_COW, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.BURRUKAI_RIB_CUT)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                        )
                )
        );
        this.add(AetherIIEntityTypes.PHYG, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.RAW_TAEGORE_MEAT)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                        )
                )
        );
        this.add(AetherIIEntityTypes.AERBUNNY, LootTable.lootTable());
        this.add(AetherIIEntityTypes.AERWHALE, LootTable.lootTable());

        Sheepuff.SheepuffColor.CLOUDWOOL_BY_SHEEPUFF_COLOR.forEach((color, itemLike) -> this.add(AetherIIEntityTypes.SHEEPUFF, AetherIILoot.ENTITIES_SHEEPUFF_WOOL_BY_DYE.get(color), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(itemLike)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().put(SheepuffPredicate.CODEC, SheepuffPredicate.isPuffed(false)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(2)).add(LootItem.lootTableItem(itemLike)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().put(SheepuffPredicate.CODEC, SheepuffPredicate.isPuffed(true))))))
        );

        this.add(AetherIIEntityTypes.SHEEPUFF, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.KIRRID_LOIN)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                        )
                )
                .withPool(createSheepuffDispatchPool(this.lootTables, AetherIILoot.ENTITIES_SHEEPUFF_WOOL_BY_DYE))
        );

        this.add(AetherIIEntityTypes.HIGHFIELDS_TAEGORE, this.createTaegoreTable());
        this.add(AetherIIEntityTypes.MAGNETIC_TAEGORE, this.createTaegoreTable());
        this.add(AetherIIEntityTypes.ARCTIC_TAEGORE, this.createTaegoreTable());

        this.add(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI, this.createBurrukaiTable());
        this.add(AetherIIEntityTypes.MAGNETIC_BURRUKAI, this.createBurrukaiTable());
        this.add(AetherIIEntityTypes.ARCTIC_BURRUKAI, this.createBurrukaiTable());

        Kirrid.KirridColor.CLOUDWOOL_BY_KIRRID_COLOR.forEach((color, lootTable) -> {
            this.add(AetherIIEntityTypes.HIGHFIELDS_KIRRID, AetherIILoot.ENTITIES_HIGHFIELDS_KIRRID_WOOL_BY_DYE.get(color), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(lootTable))));
            this.add(AetherIIEntityTypes.MAGNETIC_KIRRID, AetherIILoot.ENTITIES_MAGNETIC_KIRRID_WOOL_BY_DYE.get(color), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(lootTable))));
            this.add(AetherIIEntityTypes.ARCTIC_KIRRID, AetherIILoot.ENTITIES_ARCTIC_KIRRID_WOOL_BY_DYE.get(color), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(lootTable))));
        });

        this.add(AetherIIEntityTypes.HIGHFIELDS_KIRRID, AetherIILoot.ENTITIES_HIGHFIELDS_KIRRID_WOOL_UNDYED, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.CLOUDWOOL))));
        this.add(AetherIIEntityTypes.MAGNETIC_KIRRID, AetherIILoot.ENTITIES_MAGNETIC_KIRRID_WOOL_UNDYED, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.CLOUDWOOL))));
        this.add(AetherIIEntityTypes.ARCTIC_KIRRID, AetherIILoot.ENTITIES_ARCTIC_KIRRID_WOOL_UNDYED, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AetherIIBlocks.CLOUDWOOL))));

        this.add(AetherIIEntityTypes.HIGHFIELDS_KIRRID, this.createKirridTable(AetherIILoot.ENTITIES_HIGHFIELDS_KIRRID_WOOL_BY_DYE, AetherIILoot.ENTITIES_HIGHFIELDS_KIRRID_WOOL_UNDYED));
        this.add(AetherIIEntityTypes.MAGNETIC_KIRRID, this.createKirridTable(AetherIILoot.ENTITIES_MAGNETIC_KIRRID_WOOL_BY_DYE, AetherIILoot.ENTITIES_MAGNETIC_KIRRID_WOOL_UNDYED));
        this.add(AetherIIEntityTypes.ARCTIC_KIRRID, this.createKirridTable(AetherIILoot.ENTITIES_ARCTIC_KIRRID_WOOL_BY_DYE, AetherIILoot.ENTITIES_ARCTIC_KIRRID_WOOL_UNDYED));

        this.add(AetherIIEntityTypes.MOA, LootTable.lootTable());
        this.add(AetherIIEntityTypes.PRISMALLARD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.PRISMALLARD_LEG)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.PRISMALLARD_FEATHER)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                        )
                )
        );

        this.add(AetherIIEntityTypes.SKYROOT_LIZARD, LootTable.lootTable());

        this.add(AetherIIEntityTypes.GLITTERWING, LootTable.lootTable());
        this.add(AetherIIEntityTypes.SHROUDWING, LootTable.lootTable());

        this.add(AetherIIEntityTypes.AECHOR_PLANT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.AECHOR_PETAL)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.AECHOR_CUTTING)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(PlayerGrownCondition::new)
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.AECHOR_CUTTING)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.35F, 0.1F))
                                .when(InvertedLootItemCondition.invert(PlayerGrownCondition::new))
                        )
                )
        );
        this.add(AetherIIEntityTypes.CARRION_SPROUT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.WYNDBERRY)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.CARRION_CUTTING)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(PlayerGrownCondition::new)
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.CARRION_CUTTING)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.35F, 0.1F))
                                .when(InvertedLootItemCondition.invert(PlayerGrownCondition::new))
                        )
                )
        );

        this.add(AetherIIEntityTypes.ZEPHYR, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.COLD_AERCLOUD)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.BLUE_AERCLOUD)
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.1111F, 0.1111F))
                        )
                )
//                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)) // TODO WIP ALPHA THINGS
//                        .add(LootItem.lootTableItem(AetherIIItems.ZEPHYR_HUSK)
//                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.075F, 0.025F))
//                        )
//                )
        );

        this.add(AetherIIEntityTypes.TEMPEST, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.STORM_AERCLOUD)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                        )
                )
//                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)) // TODO WIP ALPHA THINGS
//                        .add(LootItem.lootTableItem(AetherIIItems.CHARGE_CATALYST)
//                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.075F, 0.025F))
//                        )
//                )
        );

        this.add(AetherIIEntityTypes.COCKATRICE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.COCKATRICE_FEATHER)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                        )
                )
        );
        this.add(AetherIIEntityTypes.BLUE_SWET, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.SWET_GEL)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        .apply(GelDropsFunction.extra(ContextIntProviders.exactly(1))
                                )
                        )
                ).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.SWET_SUGAR)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                .apply(SugarDropsFunction.extra(ContextIntProviders.exactly(1)))
                        )
                )
        );
        this.add(AetherIIEntityTypes.GOLDEN_SWET, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.SWET_GEL)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                .apply(GelDropsFunction.extra(ContextIntProviders.exactly(1)))
                        )
                ).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.SWET_SUGAR)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                .apply(SugarDropsFunction.extra(ContextIntProviders.exactly(2)))
                        )
                )
        );

        this.add(AetherIIEntityTypes.SKEPHID, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.CLOUDTWINE)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F))
                                )
                        )
                ));

        this.add(AetherIIEntityTypes.ARKENIUM_TALUTON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.HOLYSTONE)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                        )
                )
//                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)) // TODO WIP ALPHA THINGS
//                        .add(LootItem.lootTableItem(AetherIIItems.ARKENIUM_CORE)
//                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.075F, 0.025F))
//                        )
//                )
        );
        this.add(AetherIIEntityTypes.GRAVITITE_TALUTON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIBlocks.HOLYSTONE)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                        )
                )
//                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)) // TODO WIP ALPHA THINGS
//                        .add(LootItem.lootTableItem(AetherIIItems.GRAVITITE_CORE)
//                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.075F, 0.025F))
//                        )
//                )
        );

        this.add(AetherIIEntityTypes.BLADESHROOM_HUNTER, LootTable.lootTable());

        this.add(AetherIIEntityTypes.MIMIC, LootTable.lootTable()
//                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)) // TODO WIP ALPHA THINGS
//                        .add(LootItem.lootTableItem(AetherIIItems.EYE_OF_THE_MIMIC)
//                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.075F, 0.025F))
//                        )
//                )
        );

        this.add(AetherIIEntityTypes.DETONATION_SENTRY, LootTable.lootTable());
        this.add(AetherIIEntityTypes.SENTRY_GOLEM, LootTable.lootTable());
        this.add(AetherIIEntityTypes.SLIDER, LootTable.lootTable());

        this.add(AetherIIEntityTypes.EDWARD, LootTable.lootTable());
    }

    protected LootTable.Builder createTaegoreTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.RAW_TAEGORE_MEAT)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                        )
                ).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.BEAST_PELT)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                        )
                );
    }

    protected LootTable.Builder createBurrukaiTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.BURRUKAI_RIB_CUT)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                        )
                ).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.BEAST_PELT)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                        )
                ).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.BURRUKAI_PLATE)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                        )
                );
    }

    protected LootTable.Builder createKirridTable(Map<Kirrid.KirridColor, ResourceKey<LootTable>> wool, ResourceKey<LootTable> undyed) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(AetherIIItems.KIRRID_LOIN)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                        )
                )
                .withPool(createKirridDispatchPool(this.lootTables, wool, undyed));
    }

    public static LootPool.Builder createKirridDispatchPool(HolderLookup.RegistryLookup<LootTable> lootTables, Map<Kirrid.KirridColor, ResourceKey<LootTable>> map, ResourceKey<LootTable> undyed) {
        AlternativesEntry.Builder builder = AlternativesEntry.alternatives();
        Map.Entry<Kirrid.KirridColor, ResourceKey<LootTable>> entry;
        for (Iterator<Map.Entry<Kirrid.KirridColor, ResourceKey<LootTable>>> var2 = map.entrySet().iterator(); var2.hasNext(); builder = builder.otherwise(NestedLootTable.lootTableReference(lootTable(lootTables, entry.getValue())).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().put(KirridPredicate.CODEC, KirridPredicate.hasWool(entry.getKey())))))) {
            entry = var2.next();
        }
        builder = builder.otherwise(NestedLootTable.lootTableReference(lootTable(lootTables, undyed)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().put(KirridPredicate.CODEC, KirridPredicate.hasWool()))));
        return LootPool.lootPool().add(builder);
    }

    public static LootPool.Builder createSheepuffDispatchPool(HolderLookup.RegistryLookup<LootTable> lootTables, Map<Sheepuff.SheepuffColor, ResourceKey<LootTable>> map) {
        AlternativesEntry.Builder builder = AlternativesEntry.alternatives();
        Map.Entry<Sheepuff.SheepuffColor, ResourceKey<LootTable>> entry;
        for (Iterator<Map.Entry<Sheepuff.SheepuffColor, ResourceKey<LootTable>>> var2 = map.entrySet().iterator(); var2.hasNext(); builder = builder.otherwise(NestedLootTable.lootTableReference(lootTable(lootTables, entry.getValue())).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().put(SheepuffPredicate.CODEC, SheepuffPredicate.hasWool(entry.getKey())))))) {
            entry = var2.next();
        }
        return LootPool.lootPool().add(builder);
    }

    /**
     * Vanilla's {@code generate(BiConsumer)} walks every registered entity type and demands a table for each, so only
     * the tables added here are emitted (NeoForge scoped this through {@code getKnownEntityTypes}).
     */
    private final Map<EntityType<?>, Map<ResourceKey<LootTable>, LootTable.Builder>> tables = new LinkedHashMap<>();

    @Override
    public void add(EntityType<?> type, ResourceKey<LootTable> lootTable, LootTable.Builder builder) {
        this.tables.computeIfAbsent(type, k -> new LinkedHashMap<>()).put(lootTable, builder);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        this.generate();
        Set<ResourceKey<LootTable>> seen = new HashSet<>();
        BuiltInRegistries.ENTITY_TYPE.listElements().filter(holder -> holder.key().identifier().getNamespace().equals(AetherII.MODID)).forEach(holder -> {
            EntityType<?> type = holder.value();
            Optional<ResourceKey<LootTable>> defaultLootTable = type.getDefaultLootTable();
            Map<ResourceKey<LootTable>, LootTable.Builder> builders = this.tables.remove(type);
            if (defaultLootTable.isPresent()) {
                if (builders == null || !builders.containsKey(defaultLootTable.get())) {
                    throw new IllegalStateException("Missing loottable " + defaultLootTable.get().identifier() + " for " + holder.key().identifier());
                }
                builders.forEach((id, builder) -> {
                    if (!seen.add(id)) {
                        throw new IllegalStateException("Duplicate loottable " + id.identifier() + " for " + holder.key().identifier());
                    }
                    output.accept(id, builder);
                });
            } else if (builders != null) {
                throw new IllegalStateException("Weird loottables " + builders.keySet() + " for " + holder.key().identifier() + ", not a LivingEntity so should not have loot");
            }
        });
        if (!this.tables.isEmpty()) {
            throw new IllegalStateException("Created loot tables for entities not supported by datapack: " + this.tables.keySet());
        }
    }
}