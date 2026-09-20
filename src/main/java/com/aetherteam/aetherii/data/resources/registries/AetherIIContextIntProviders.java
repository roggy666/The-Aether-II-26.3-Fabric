package com.aetherteam.aetherii.data.resources.registries;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.DispatcherProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.NumberDispatcher;

import java.util.List;

/** Context int providers the mod needs beyond the vanilla ones (compost layer chances that vanilla has no key for). */
public class AetherIIContextIntProviders {
    /** 1.8% chance to add a compost layer, used by leaf piles. */
    public static final ResourceKey<ContextIntProvider> COMPOSTABLE_VERY_LOW = createKey("compostable/very_low");

    private static ResourceKey<ContextIntProvider> createKey(String name) {
        return ResourceKey.create(Registries.CONTEXT_INT_PROVIDER, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
    }

    public static void bootstrap(BootstrapContext<ContextIntProvider> context) {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        context.register(COMPOSTABLE_VERY_LOW, compostable(blocks, 18, 1000));
    }

    /** Same shape as {@link ContextIntProviders#compostable}, but with a finer-grained chance (successes out of total). */
    private static ContextIntProvider compostable(HolderGetter<Block> blocks, int successes, int total) {
        DispatcherProvider.Case<ContextIntProvider> emptyCase = new DispatcherProvider.Case<>(
                Holder.direct(MatchBlock.blockMatches(blocks, Blocks.COMPOSTER, StatePropertiesPredicate.Builder.properties().hasProperty(ComposterBlock.LEVEL, 0)).build()),
                ContextIntProviders.exactly(1)
        );
        WeightedList<Holder<ContextIntProvider>> cases = WeightedList.<Holder<ContextIntProvider>>builder()
                .add(ContextIntProviders.exactly(1), successes)
                .add(ContextIntProviders.exactly(0), total - successes)
                .build();
        return new NumberDispatcher(List.of(emptyCase), ContextIntProviders.weighted(cases));
    }
}
