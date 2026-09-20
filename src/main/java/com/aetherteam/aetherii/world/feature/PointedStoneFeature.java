package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.world.feature.configuration.PointedStoneConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SpeleothemThickness;
import net.minecraft.world.level.levelgen.feature.SpeleothemUtils;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.Optional;
import java.util.function.Consumer;

public class PointedStoneFeature implements Feature {
    public static final MapCodec<PointedStoneFeature> CODEC = PointedStoneConfiguration.CODEC.xmap(PointedStoneFeature::new, PointedStoneFeature::config);
    private final PointedStoneConfiguration config;

    public PointedStoneFeature(PointedStoneConfiguration config) {
        this.config = config;
    }

    public PointedStoneConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<PointedStoneFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;
        PointedStoneConfiguration config = this.config;
        Optional<Direction> optional = getTipDirection(level, pos, random, config);
        if (optional.isEmpty()) {
            return false;
        } else {
            BlockPos relativePos = pos.relative(optional.get().getOpposite());
            createPatchOfDripstoneBlocks(level, random, relativePos, config);
            int i = 1 + (random.nextFloat() < config.chanceOfTallerDripstone() && SpeleothemUtils.isEmptyOrWater(level.getBlockState(pos.relative(optional.get()))) ? random.nextInt(5) : random.nextInt(3));
            growPointedDripstone(level, pos, optional.get(), i, false, random, config);
            return true;
        }
    }

    private static Optional<Direction> getTipDirection(WorldGenLevel level, BlockPos pos, RandomSource random, PointedStoneConfiguration config) {
        boolean isAboveBase = isDripstoneBase(level, level.getBlockState(pos.above()), random, pos, config);
        boolean isBelowBase = isDripstoneBase(level, level.getBlockState(pos.below()), random, pos, config);
        if (isAboveBase && isBelowBase) {
            return Optional.of(random.nextBoolean() ? Direction.DOWN : Direction.UP);
        } else if (isAboveBase) {
            return Optional.of(Direction.DOWN);
        } else {
            return isBelowBase ? Optional.of(Direction.UP) : Optional.empty();
        }
    }

    private static void createPatchOfDripstoneBlocks(WorldGenLevel level, RandomSource random, BlockPos pos, PointedStoneConfiguration config) {
        placeDripstoneBlockIfPossible(level, random, pos, config);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!(random.nextFloat() > config.chanceOfDirectionalSpread())) {
                BlockPos firstRelativePos = pos.relative(direction);
                placeDripstoneBlockIfPossible(level, random, firstRelativePos, config);
                if (!(random.nextFloat() > config.chanceOfSpreadRadius2())) {
                    BlockPos secondRelativePos = firstRelativePos.relative(Direction.getRandom(random));
                    placeDripstoneBlockIfPossible(level, random, secondRelativePos, config);
                    if (!(random.nextFloat() > config.chanceOfSpreadRadius3())) {
                        BlockPos thirdRelativePos = secondRelativePos.relative(Direction.getRandom(random));
                        placeDripstoneBlockIfPossible(level, random, thirdRelativePos, config);
                    }
                }
            }
        }
    }

    protected static boolean placeDripstoneBlockIfPossible(WorldGenLevel level, RandomSource random, BlockPos pos, PointedStoneConfiguration config) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.is(AetherIITags.Blocks.AETHER_UNDERGROUND_BLOCKS)) {
            level.setBlock(pos, config.stoneBlock().getState(level, random, pos), 2);
            return true;
        } else {
            return false;
        }
    }

    protected static void growPointedDripstone(WorldGenLevel level, BlockPos pos, Direction direction, int height, boolean mergeTip, RandomSource random, PointedStoneConfiguration config) {
        if (isDripstoneBase(level, level.getBlockState(pos.relative(direction.getOpposite())), random, pos, config)) {
            BlockPos.MutableBlockPos mutablePos = pos.mutable();
            buildBaseToTipColumn(level, direction, height, mergeTip, (state) -> {
                BlockState stoneBlock = config.stoneBlock().getState(level, random, mutablePos);
                if (state.is(stoneBlock.getBlock())) {
                    state = state.setValue(PointedDripstoneBlock.WATERLOGGED, level.isWaterAt(mutablePos));
                }
                level.setBlock(mutablePos, state, 2);
                mutablePos.move(direction);
            }, random, mutablePos, config);
        }
    }

    public static boolean isDripstoneBase(WorldGenLevel level, BlockState state, RandomSource random, BlockPos pos, PointedStoneConfiguration config) {
        BlockState stoneBlock = config.stoneBlock().getState(level, random, pos);
        return state.is(stoneBlock.getBlock());
    }

    protected static void buildBaseToTipColumn(WorldGenLevel level, Direction direction, int height, boolean mergeTip, Consumer<BlockState> blockSetter, RandomSource random, BlockPos pos, PointedStoneConfiguration config) {
        if (height >= 3) {
            blockSetter.accept(createPointedDripstone(level, direction, SpeleothemThickness.BASE, random, pos, config));

            for (int i = 0; i < height - 3; ++i) {
                blockSetter.accept(createPointedDripstone(level, direction, SpeleothemThickness.MIDDLE, random, pos, config));
            }
        }

        if (height >= 2) {
            blockSetter.accept(createPointedDripstone(level, direction, SpeleothemThickness.FRUSTUM, random, pos, config));
        }

        if (height >= 1) {
            blockSetter.accept(createPointedDripstone(level, direction, mergeTip ? SpeleothemThickness.TIP_MERGE : SpeleothemThickness.TIP, random, pos, config));
        }
    }

    private static BlockState createPointedDripstone(WorldGenLevel level, Direction direction, SpeleothemThickness dripstoneThickness, RandomSource random, BlockPos pos, PointedStoneConfiguration config) {
        return config.pointedStoneBlock().getState(level, random, pos).setValue(PointedDripstoneBlock.TIP_DIRECTION, direction).setValue(PointedDripstoneBlock.THICKNESS, dripstoneThickness);
    }
}
