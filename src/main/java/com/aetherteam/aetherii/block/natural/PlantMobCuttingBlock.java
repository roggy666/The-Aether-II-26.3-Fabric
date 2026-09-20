package com.aetherteam.aetherii.block.natural;

import net.minecraft.world.level.block.BonemealSource;
import com.aetherteam.aetherii.entity.PlantCuttingMob;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class PlantMobCuttingBlock extends AetherFlowerBlock implements BonemealableBlock {
    private final Supplier<EntityType<?>> spawnableEntityTypeSupplier;

    public PlantMobCuttingBlock(Supplier<EntityType<?>> spawnableEntityTypeSupplier, Properties properties) {
        super(properties);
        this.spawnableEntityTypeSupplier = spawnableEntityTypeSupplier;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if ((random.nextInt(20) == 0)) {
            this.spawnPlantMob(level, pos);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        return random.nextFloat() <= 0.25;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        this.spawnPlantMob(level, pos);
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
    }

    private void spawnPlantMob(ServerLevel level, BlockPos pos) {
        this.spawnableEntityTypeSupplier.get().spawn(level, (entity) -> {
            if (entity instanceof Mob mob) {
                mob.setPersistenceRequired();
            }
            if (entity instanceof PlantCuttingMob plantCuttingMob) {
                plantCuttingMob.setPlayerGrown(true);
            }
        }, BlockPos.containing(Vec3.atBottomCenterOf(pos)), EntitySpawnReason.NATURAL, false, false);
    }
}