package com.aetherteam.aetherii.block.natural;

import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.fluid.AlkahestFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;

public class AlkahestLiquidBlock extends VolatileLiquidBlock {
    public AlkahestLiquidBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
        this.createHestveil(level, pos);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        FluidState fluidState = level.getFluidState(pos);
        if (level.getBlockState(pos.above()).isAir() && fluidState.isSource()) {
            level.scheduleTick(pos, state.getBlock(), 25);
        }
    }

    public void createHestveil(Level level, BlockPos pos) {
        BlockPos above = pos.above();
        if (level.getBlockState(above).isAir()) {
            level.setBlock(above, AetherIIBlocks.HESTVEIL.defaultBlockState(), 3);
        }
    }

    /**
     * Alkahest touching water turns into a gel block (NeoForge's {@code FluidInteractionRegistry} entry for alkahest/water),
     * checked like lava's {@code shouldSpreadLiquid}: when placed/flowing and when a neighbor changes.
     */
    private boolean gelify(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (level.getFluidState(pos.relative(direction)).is(FluidTags.WATER)) {
                level.setBlockAndUpdate(pos, AetherIIBlocks.GEL_BLOCK.defaultBlockState());
                level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.8F);
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.WHITE_SMOKE,
                            pos.getX() + level.getRandom().nextDouble(),
                            pos.getY() + 1.2,
                            pos.getZ() + level.getRandom().nextDouble(),
                            8, 0.0, 0.0, 0.0, 0.0
                    );
                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, orientation, movedByPiston);
        this.gelify(level, pos);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!this.gelify(level, pos)) {
            level.scheduleTick(pos, state.getFluidState().getType(), this.fluid.getTickDelay(level));
        }
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean p_451772_) {
        if (this.fluid instanceof AlkahestFluid alkahestFluid && level instanceof ServerLevel serverLevel) {
            alkahestFluid.entityInside(state, serverLevel, pos, entity);
        }
        super.entityInside(state, level, pos, entity, effectApplier, p_451772_);
    }
}
