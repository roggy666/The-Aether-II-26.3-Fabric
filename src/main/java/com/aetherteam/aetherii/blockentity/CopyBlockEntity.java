package com.aetherteam.aetherii.blockentity;

import org.jetbrains.annotations.Nullable;
import net.fabricmc.fabric.api.blockgetter.v2.RenderDataBlockEntity;
import com.aetherteam.aetherii.client.AetherIIClientProxy;
import com.aetherteam.aetherii.item.components.AetherIIDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.SectionPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import com.aetherteam.aetherii.block.dungeon.CopyBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public abstract class CopyBlockEntity extends BlockEntity {
    protected BlockState copyState;

    public CopyBlockEntity(BlockEntityType<? extends CopyBlockEntity> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public BlockState getCopyState() {
        return this.copyState;
    }

    public void setCopyState(BlockState copyState) {
        this.copyState = copyState;
        this.syncLight();
    }

    /**
     * Mirrors the copied block's light emission into {@link CopyBlock#LIGHT} (NeoForge's {@code AuxiliaryLightManager}).
     */
    protected void syncLight() {
        if (this.level != null && !this.level.isClientSide()) {
            BlockState state = this.getBlockState();
            if (state.hasProperty(CopyBlock.LIGHT)) {
                int light = this.copyState != null && !state.getValue(CopyBlock.EMPTY) ? this.copyState.getLightEmission() : 0;
                if (state.getValue(CopyBlock.LIGHT) != light) {
                    this.level.setBlock(this.getBlockPos(), state.setValue(CopyBlock.LIGHT, light), Block.UPDATE_ALL);
                }
            }
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (this.copyState != null) {
            output.store("copy_state", BlockState.CODEC, this.copyState);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.copyState = input.read("copy_state", BlockState.CODEC).orElse(null);
        this.syncLight();
        if (this.level != null && this.level.isClientSide()) {
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
            AetherIIClientProxy.setSectionDirty(SectionPos.of(this.getBlockPos()));
        }
    }

    @Override
    public void setLevel(Level level) {
        boolean added = this.level == null && level != null;
        super.setLevel(level);
        if (added) { // NeoForge's onLoad
            this.setChanged();
            level.blockEvent(this.getBlockPos(), this.getBlockState().getBlock(), 1, 0);
            this.syncLight();
        }
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (this.level != null) {
            BlockPos pos = this.getBlockPos();
            this.level.getLightEngine().checkBlock(pos);
            if (this.level.isClientSide()) {
                AetherIIClientProxy.setSectionDirty(SectionPos.of(pos));
            }
        }
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.setChanged();
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter getter) {
        super.applyImplicitComponents(getter);
        this.copyState = getter.getOrDefault(AetherIIDataComponents.BLOCK_STATE, null);
        this.syncLight();
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);
        builder.set(AetherIIDataComponents.BLOCK_STATE, this.copyState);
    }

    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        output.discard("copy_state");
    }


    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveCustomOnly(registries);
    }

    @Override
    public @Nullable Object getRenderData() {
        if (this.copyState != null) {
            return new CopyData(this.copyState);
        }
        return null;
    }

    public BlockState open(Level level, BlockPos pos) {
        return null;
    }

    public BlockState close(Level level, BlockPos pos) {
        return null;
    }

    public BlockState destroy(Level level, BlockPos pos) {
        return null;
    }

    public abstract ItemStack getItem();

    public record CopyData(BlockState state) {
    }
}
