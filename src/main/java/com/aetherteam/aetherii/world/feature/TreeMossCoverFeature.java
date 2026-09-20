package com.aetherteam.aetherii.world.feature;

import com.aetherteam.aetherii.block.AetherIIBlockStateProperties;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.AetherLeavesBlock;
import com.aetherteam.aetherii.block.natural.BottomedVineBlock;
import com.aetherteam.aetherii.data.resources.registries.AetherIIDensityFunctions;
import com.aetherteam.aetherii.data.resources.registries.holyisles.HolyIslesBiomes;
import com.aetherteam.aetherii.world.density.DensitySampling;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensitySampler;
import net.minecraft.world.level.levelgen.feature.Feature;

public class TreeMossCoverFeature implements Feature {
    public static final MapCodec<TreeMossCoverFeature> CODEC = MapCodec.unit(TreeMossCoverFeature::new);

    @Override
    public MapCodec<TreeMossCoverFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        int chunkX = origin.getX() - (origin.getX() % 16);
        int chunkZ = origin.getZ() - (origin.getZ() % 16);
        HolderGetter<DensityFunction> function = level.holderLookup(Registries.DENSITY_FUNCTION);
        DensitySampler.Bound noise = DensitySampling.sampler(level, AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.ENVIRONMENTAL_TREE_MOSS));
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int xCoord = chunkX + x;
                int zCoord = chunkZ + z;
                int yCoord = level.getHeight(Heightmap.Types.MOTION_BLOCKING, xCoord, zCoord);
                BlockPos groundPos = new BlockPos(xCoord, yCoord, zCoord).below();
                if (level.getBiome(groundPos).is(HolyIslesBiomes.SHROUDED_FOREST) && level.getBlockState(groundPos.above()).isAir()) {
                    double calc = noise.sampleValue(groundPos.getX(), groundPos.getY(), groundPos.getZ());
                    if (calc >= 0.05F) {
                        if ((level.getBlockState(groundPos).is(AetherIIBlocks.SKYPLANE_LEAVES)) || level.getBlockState(groundPos).is(AetherIIBlocks.WOVEN_SKYROOT_STICKS)) {
                            level.setBlock(groundPos, level.getBlockState(groundPos).setValue(AetherLeavesBlock.MOSSY, AetherIIBlockStateProperties.Mossy.BRYALINN), 2);
                            for (Direction direction : Direction.Plane.HORIZONTAL) {
                                if (level.getRandom().nextBoolean()) {
                                    BlockPos offsetPos = groundPos.relative(direction);
                                    if (level.getBlockState(offsetPos).isAir()) {
                                        BlockState blockState = AetherIIBlocks.BRYALINN_MOSS_VINES.defaultBlockState().setValue(VineBlock.getPropertyForFace(direction.getOpposite()), true).setValue(BottomedVineBlock.AGE, 25 - level.getRandom().nextInt(2));
                                        addHangingVine(level, offsetPos, blockState);
                                    } else if (level.getBlockState(offsetPos).is(AetherIIBlocks.BRYALINN_MOSS_VINES)) {
                                        BlockState blockState = level.getBlockState(offsetPos).setValue(VineBlock.getPropertyForFace(direction.getOpposite()), true).setValue(BottomedVineBlock.AGE, 25 - level.getRandom().nextInt(2));
                                        addHangingVine(level, offsetPos, blockState);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void addHangingVine(WorldGenLevel level, BlockPos pos, BlockState blockState) {
        level.setBlock(pos, blockState, 3);
        int i = 10;
        for (BlockPos blockpos = pos.below(); level.getBlockState(blockpos).isAir() && i > 0; i--) {
            if (blockState.getValue(BottomedVineBlock.AGE) + 1 <= 25) {
                blockState = blockState.setValue(BottomedVineBlock.AGE, blockState.getValue(BottomedVineBlock.AGE) + 1);
                level.setBlock(blockpos, blockState, 3);
                blockpos = blockpos.below();
            } else {
                break;
            }
        }
    }
}
