package com.aetherteam.aetherii.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

/**
 * ItemUseConvertEvent is fired after an item that can convert blocks is used, but before the block is converted by the recipe.
 */
public class ItemUseConvertEvent {
    @FunctionalInterface
    public interface Callback {
        boolean onConvert(ItemUseConvertEvent event);
    }

    public static final Event<Callback> EVENT = EventFactory.createArrayBacked(Callback.class, callbacks -> event -> {
        for (Callback callback : callbacks) {
            if (callback.onConvert(event)) {
                return true;
            }
        }
        return false;
    });

    @Nullable
    private final Player player;
    private final LevelAccessor level;
    private final BlockPos pos;
    @Nullable
    private final ItemStack itemStack;
    private final RecipeType<?> recipeType;
    private final BlockState oldBlockState;
    private BlockState newBlockState;
    private boolean canceled = false;

    public ItemUseConvertEvent(@Nullable Player player, LevelAccessor level, BlockPos pos, @Nullable ItemStack itemStack, BlockState oldBlockState, BlockState newBlockState, RecipeType<?> recipe) {
        this.player = player;
        this.level = level;
        this.pos = pos;
        this.itemStack = itemStack;
        this.oldBlockState = oldBlockState;
        this.newBlockState = newBlockState;
        this.recipeType = recipe;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Nullable
    public Player getPlayer() {
        return this.player;
    }

    public LevelAccessor getLevel() {
        return this.level;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    @Nullable
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public RecipeType<?> getRecipeType() {
        return this.recipeType;
    }

    public BlockState getOldBlockState() {
        return this.oldBlockState;
    }

    public BlockState getNewBlockState() {
        return this.newBlockState;
    }

    public void setNewBlockState(BlockState newBlockState) {
        this.newBlockState = newBlockState;
    }
}
