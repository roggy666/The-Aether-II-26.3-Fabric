package com.aetherteam.aetherii.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

/**
 * FreezeEvent is fired when an event for a freezing recipe occurs.
 */
public abstract class FreezeEvent {
    @FunctionalInterface
    public interface FreezeFromBlockCallback {
        boolean onFreezeFromBlock(FreezeFromBlock event);
    }

    @FunctionalInterface
    public interface FreezeFromItemCallback {
        boolean onFreezeFromItem(FreezeFromItem event);
    }

    public static final Event<FreezeFromBlockCallback> FREEZE_FROM_BLOCK = EventFactory.createArrayBacked(FreezeFromBlockCallback.class, callbacks -> event -> {
        for (FreezeFromBlockCallback callback : callbacks) {
            if (callback.onFreezeFromBlock(event)) {
                return true;
            }
        }
        return false;
    });

    public static final Event<FreezeFromItemCallback> FREEZE_FROM_ITEM = EventFactory.createArrayBacked(FreezeFromItemCallback.class, callbacks -> event -> {
        for (FreezeFromItemCallback callback : callbacks) {
            if (callback.onFreezeFromItem(event)) {
                return true;
            }
        }
        return false;
    });

    private final LevelAccessor level;
    private final BlockPos pos;
    private final BlockState priorBlock;
    private BlockState frozenBlock;
    private boolean canceled = false;

    public FreezeEvent(LevelAccessor level, BlockPos pos, BlockState priorBlock, BlockState frozenBlock) {
        this.level = level;
        this.pos = pos;
        this.priorBlock = priorBlock;
        this.frozenBlock = frozenBlock;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public LevelAccessor getLevel() {
        return this.level;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public BlockState getPriorBlock() {
        return this.priorBlock;
    }

    public BlockState getFrozenBlock() {
        return this.frozenBlock;
    }

    public void setFrozenBlock(BlockState frozenBlock) {
        this.frozenBlock = frozenBlock;
    }

    public static class FreezeFromBlock extends FreezeEvent {
        private final BlockPos sourcePos;
        private final BlockState sourceBlock;

        public FreezeFromBlock(LevelAccessor level, BlockPos pos, BlockPos sourcePos, BlockState priorBlock, BlockState frozenBlock, BlockState sourceBlock) {
            super(level, pos, priorBlock, frozenBlock);
            this.sourcePos = sourcePos;
            this.sourceBlock = sourceBlock;
        }

        public BlockState getSourceBlock() {
            return this.sourceBlock;
        }

        public BlockPos getSourcePos() {
            return this.sourcePos;
        }
    }

    public static class FreezeFromItem extends FreezeEvent {
        private final ItemStack sourceStack;

        public FreezeFromItem(LevelAccessor level, BlockPos pos, BlockState priorBlock, BlockState frozenBlock, ItemStack sourceStack) {
            super(level, pos, priorBlock, frozenBlock);
            this.sourceStack = sourceStack;
        }

        public ItemStack getSourceStack() {
            return this.sourceStack;
        }
    }
}
