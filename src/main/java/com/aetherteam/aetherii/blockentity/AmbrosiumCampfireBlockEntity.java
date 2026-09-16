package com.aetherteam.aetherii.blockentity;

import com.aetherteam.aetherii.block.AetherIIBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AmbrosiumCampfireBlockEntity extends CampfireBlockEntity {
    public AmbrosiumCampfireBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public boolean isValidBlockState(BlockState state) {
        return state.is(AetherIIBlocks.AMBROSIUM_CAMPFIRE);
    }

    @Override
    public BlockEntityType<AmbrosiumCampfireBlockEntity> getType() {
        return AetherIIBlockEntityTypes.AMBROSIUM_CAMPFIRE;
    }
}