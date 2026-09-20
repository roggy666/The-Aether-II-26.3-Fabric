package com.aetherteam.aetherii.data.providers;

import net.minecraft.core.Holder;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIBlockStateProperties;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.*;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.item.components.AetherIIDataComponents;
import com.aetherteam.aetherii.loot.conditions.TierCompare;
import com.aetherteam.aetherii.loot.functions.SpawnSkyrootLizard;
import com.aetherteam.nitrogen.data.providers.NitrogenBlockLootSubProvider;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;

import java.util.stream.IntStream;

public abstract class AetherIIBlockLootSubProvider extends NitrogenBlockLootSubProvider {
    public AetherIIBlockLootSubProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public static final BooleanProperty GROWN = AetherIIBlockStateProperties.BRETTL_GROWN;

    protected LootTable.Builder droppingIrradiatedDustLoot(Block block) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).when(this.hasSilkTouch()).add(LootItem.lootTableItem(block)))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).when(this.doesNotHaveSilkTouch())
                        .add(LootItem.lootTableItem(AetherIIItems.IRRADIATED_WEAPON))
                        .add(LootItem.lootTableItem(AetherIIItems.IRRADIATED_TOOL))
                        .add(LootItem.lootTableItem(AetherIIItems.IRRADIATED_ARMOR))
                        .add(LootItem.lootTableItem(AetherIIItems.IRRADIATED_CHUNK))
                );
    }

    protected LootTable.Builder createSkyRootsDrops(Block block) {
        return this.createSilkTouchOrShearsDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(AetherIIItems.SKYROOT_STICK).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))))
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .when(this.doesNotHaveShearsOrSilkTouch())
                                .add(this.applyExplosionDecay(block, LootItem.lootTableItem(AetherIIItems.ARCTIC_SNOWBALL)
                                        .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(AetherHangingRootsBlock.SNOWY, true)))
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                )));
    }

    public LootTable.Builder droppingSnowLayer(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemEntityPropertyCondition.entityPresent(LootContext.EntityTarget.THIS))
                        .add(AlternativesEntry.alternatives(
                                AlternativesEntry.alternatives(
                                        SnowLayerBlock.LAYERS.getPossibleValues(),
                                        layer -> ((LootPoolEntryContainer.Builder<?>) LootItem.lootTableItem(AetherIIItems.ARCTIC_SNOWBALL)
                                                .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(SnowLayerBlock.LAYERS, layer))))
                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(layer)))).when(this.doesNotHaveSilkTouch()),
                                AlternativesEntry.alternatives(
                                        SnowLayerBlock.LAYERS.getPossibleValues(),
                                        layer -> layer == 8 ? LootItem.lootTableItem(AetherIIBlocks.ARCTIC_SNOW_BLOCK) : LootItem.lootTableItem(AetherIIBlocks.ARCTIC_SNOW)
                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(layer)))
                                                .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(SnowLayerBlock.LAYERS, layer))))
                                )
                        )
        );
    }

    protected LootTable.Builder createQuartzOreDrops(Block block) {
        HolderGetter<Enchantment> registrylookup = this.enchantments;
        return this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block,
                        LootItem.lootTableItem(Items.QUARTZ)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 5)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                .apply(SetComponentsFunction.setComponent(DataComponents.ITEM_NAME, Component.translatable("item.aether_ii.aether_quartz")))
                )
        );
    }

    public LootTable.Builder droppingAmberoot(HolderGetter<Item> holderGetter, Block original, Block block, Item item) {
        HolderGetter<Enchantment> registrylookup = this.enchantments;
        return LootTable.lootTable()
                .withPool(this.applyExplosionDecay(block, LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(original)
                        .when(this.hasSilkTouch()))))
                .withPool(this.applyExplosionDecay(block, LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(block)
                        .when(this.doesNotHaveSilkTouch()))))
                .withPool(this.applyExplosionDecay(item, LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(item)
                        .when(() -> new TierCompare(holderGetter.getOrThrow(AetherIITags.Items.GOLDEN_AMBER_HARVESTERS)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, ItemTags.AXES)))
                        .when(this.doesNotHaveSilkTouch())
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));
    }

    public LootTable.Builder createShearsOnlyDrop(ItemLike p_250684_) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).when(this.hasShears()).add(LootItem.lootTableItem(p_250684_)));
    }

    public LootTable.Builder droppingLeafPile(Block block, Block leaves) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .when(this.hasShears())
                .when(LootItemEntityPropertyCondition.entityPresent(LootContext.EntityTarget.THIS))
                .add(AlternativesEntry.alternatives(
                        AetherLeafPileBlock.PILES.getPossibleValues(),
                        piles -> piles == 16 ? LootItem.lootTableItem(leaves) : LootItem.lootTableItem(block)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(piles)))
                                .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(AetherLeafPileBlock.PILES, piles))))
                )
        );
    }

    public LootTable.Builder droppingWithChancesAndSkyrootSticksWithLizard(Block block, Block sapling, float... chances) {
        HolderGetter<Enchantment> enchantmentLookup = this.enchantments;
        HolderGetter<Item> itemLookup = this.items;
        return createForgeSilkTouchOrShearsDispatchTable(itemLookup, block, this.applyExplosionCondition( block, LootItem.lootTableItem(sapling)).when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentLookup.getOrThrow(Enchantments.FORTUNE), chances)))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).when(this.doesNotHaveShearsOrSilkTouch())
                        .add(this.applyExplosionDecay(block,
                                        LootItem.lootTableItem(AetherIIItems.SKYROOT_STICK).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))).apply(SpawnSkyrootLizard.builder(block.builtInRegistryHolder()))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentLookup.getOrThrow(Enchantments.FORTUNE), 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).when(this.doesNotHaveShearsOrSilkTouch())
                        .add(this.applyExplosionCondition(block, LootItem.lootTableItem(AetherIIItems.SKYROOT_PINECONE))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentLookup.getOrThrow(Enchantments.FORTUNE), 0.01F, 0.011111112F, 0.0125F, 0.0111111125F, 0.05F))));
    }

    public LootTable.Builder droppingWithChancesAndSkyrootSticks(Block block, Block sapling, float... chances) {
        HolderGetter<Enchantment> enchantmentLookup = this.enchantments;
        HolderGetter<Item> itemLookup = this.items;
        return createForgeSilkTouchOrShearsDispatchTable(itemLookup, block, this.applyExplosionCondition( block, LootItem.lootTableItem(sapling)).when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentLookup.getOrThrow(Enchantments.FORTUNE), chances)))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).when(this.doesNotHaveShearsOrSilkTouch())
                        .add(this.applyExplosionDecay(block,
                                        LootItem.lootTableItem(AetherIIItems.SKYROOT_STICK).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentLookup.getOrThrow(Enchantments.FORTUNE), 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).when(this.doesNotHaveShearsOrSilkTouch())
                                .add(this.applyExplosionCondition(block, LootItem.lootTableItem(AetherIIItems.SKYROOT_PINECONE))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentLookup.getOrThrow(Enchantments.FORTUNE), 0.01F, 0.011111112F, 0.0125F, 0.0111111125F, 0.05F))));
    }

    protected LootTable.Builder createSilkTouchOrShearsTable(ItemLike item) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(this.hasShearsOrSilkTouch()).setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(item)));
    }

    public LootTable.Builder droppingArilumBulbs(HolderGetter<Item> holderGetter, Block block, Item drop) {
        HolderGetter<Enchantment> registrylookup = this.enchantments;
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(this.applyExplosionDecay(block, LootItem.lootTableItem(drop))
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, AetherIITags.Items.TOOLS_TROWELS)).invert()))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                .add(this.applyExplosionDecay(block, LootItem.lootTableItem(drop))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, AetherIITags.Items.TOOLS_TROWELS))))
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(block)).when(this.hasShearsOrSilkTouch()));
    }

    public LootTable.Builder droppingSativalShoot(HolderGetter<Item> holderGetter, Block block, Item drop) {
        HolderGetter<Enchantment> registrylookup = this.enchantments;
        HolderGetter<Item> itemLookup = this.items;
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                .add(this.applyExplosionDecay(block, LootItem.lootTableItem(drop))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))
                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, AetherIITags.Items.TOOLS_TROWELS)))
        ).withPool(LootPool.lootPool().add(LootItem.lootTableItem(block)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(itemLookup, AetherIITags.Items.TOOLS_TROWELS)).invert()));
    }

    public LootTable.Builder droppingBerryBush(Block block, Item drop) {
        HolderGetter<Enchantment> registryLookup = this.enchantments;
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(this.applyExplosionDecay(block, LootItem.lootTableItem(drop)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                                .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))
                        .when(this.doesNotHaveSilkTouch())
                ).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(block))
                        .when(this.hasSilkTouch())
                );
    }

    public LootTable.Builder droppingOrangeTree(HolderGetter<Item> holderGetter, Block block, Item drop) {
        HolderGetter<Enchantment> registrylookup = this.enchantments;
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                .add(this.applyExplosionDecay(block, LootItem.lootTableItem(drop))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))
                .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(OrangeTreeBlock.AGE, 4)))
                .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, AetherIITags.Items.TOOLS_TROWELS)))
        ).withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(block))
                .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(OrangeTreeBlock.AGE, 4)).invert())
                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, AetherIITags.Items.TOOLS_TROWELS)))
        );
    }

    public LootTable.Builder droppingValkyrieSprout(Block block, Item drop) {
        HolderGetter<Enchantment> registrylookup = this.enchantments;
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                .add(this.applyExplosionDecay(block, LootItem.lootTableItem(drop))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))
                .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(ValkyrieSproutBlock.AGE, 2)))
        ).withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(block))
        );
    }

    protected LootTable.Builder droppingBrettlPlant(HolderGetter<Item> holderGetter, Block block, ItemLike drop, ItemLike dropGrown) {
        HolderGetter<Enchantment> registryLookup = this.enchantments;
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(this.applyExplosionDecay(block, LootItem.lootTableItem(drop)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))
                                .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))
                        .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(GROWN, false)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, AetherIITags.Items.TOOLS_TROWELS)))
                )
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(this.applyExplosionDecay(block, LootItem.lootTableItem(dropGrown)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                            .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))
                        .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(GROWN, true)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, AetherIITags.Items.TOOLS_TROWELS)))
                );
    }

    protected LootTable.Builder droppingBrettlPlantTip(HolderGetter<Item> holderGetter, Block block, ItemLike drop, ItemLike dropGrown) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(this.applyExplosionDecay(block, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))))
                        .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(GROWN, false)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, AetherIITags.Items.TOOLS_TROWELS)))
                )
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(this.applyExplosionDecay(block, LootItem.lootTableItem(dropGrown).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))))
                        .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(GROWN, true)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, AetherIITags.Items.TOOLS_TROWELS)))
                );
    }

    protected LootTable.Builder dropTwigs(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(this.applyExplosionDecay(block, LootItem.lootTableItem(block)
                                                .apply(IntStream.rangeClosed(1, 4).boxed().toList(), count -> SetItemCountFunction.setCount(ContextIntProviders.exactly(count))
                                                        .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(TwigBlock.AMOUNT, count))))
                                )
                        )
        );
    }

    protected LootTable.Builder dropRocks(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                .add(this.applyExplosionDecay(block, LootItem.lootTableItem(block)
                                .apply(IntStream.rangeClosed(1, 4).boxed().toList(), count -> SetItemCountFunction.setCount(ContextIntProviders.exactly(count))
                                        .when(MatchBlock.blockMatches(this.blocks, block, StatePropertiesPredicate.Builder.properties().hasProperty(RockBlock.AMOUNT, count))))
                        )
                )
        );
    }

    protected LootTable.Builder droppingMoaEgg(Block block) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                .add(LootItem.lootTableItem(block)
                        .apply(CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY).include(AetherIIDataComponents.MOA_EGG_TYPE))))
        );
    }

    @Override
    public Holder<LootItemCondition> hasShears() {
        return Holder.direct(new AnyOfCondition.Builder().or(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.items, ConventionalItemTags.SHEAR_TOOLS))).or(super.hasShears()).build());
    }
}