package com.aetherteam.aetherii.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

/**
 * Entity-aware block friction (NeoForge's {@code IBlockExtension#getFriction(BlockState, LevelReader, BlockPos, Entity)}).
 * Hooked into the vanilla friction lookups by the LivingEntity, ItemEntity and AbstractBoat mixins.
 */
public interface EntityFrictionBlock {
    float getFriction(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity);

    static float friction(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        return state.getBlock() instanceof EntityFrictionBlock block ? block.getFriction(state, level, pos, entity) : state.getBlock().getFriction();
    }
}
