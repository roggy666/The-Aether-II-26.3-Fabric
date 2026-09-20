package com.aetherteam.aetherii.client;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Collection;
import java.util.List;

/**
 * Client-side copy of the mod's synced recipes, grouped by type. Replaces the {@code RecipeMap} used before 26.3,
 * which can now only be built from a registry lookup.
 */
public record ClientRecipeCache(Multimap<RecipeType<?>, RecipeHolder<?>> byType) {
    public static final ClientRecipeCache EMPTY = new ClientRecipeCache(ImmutableMultimap.of());

    public static ClientRecipeCache of(List<RecipeHolder<?>> recipes) {
        ImmutableMultimap.Builder<RecipeType<?>, RecipeHolder<?>> builder = ImmutableMultimap.builder();
        for (RecipeHolder<?> holder : recipes) {
            builder.put(holder.value().getType(), holder);
        }
        return new ClientRecipeCache(builder.build());
    }

    @SuppressWarnings("unchecked")
    public <I extends RecipeInput, T extends Recipe<I>> Collection<RecipeHolder<T>> byType(RecipeType<T> type) {
        return (Collection<RecipeHolder<T>>) (Collection<?>) this.byType.get(type);
    }
}
