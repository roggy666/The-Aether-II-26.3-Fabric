package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.HestveilBlock;
import com.aetherteam.aetherii.world.feature.configuration.AlkahestPoolConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SpeleothemThickness;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.function.Consumer;

public class AlkahestPoolFeature implements Feature {
    public static final MapCodec<AlkahestPoolFeature> CODEC = AlkahestPoolConfiguration.CODEC.xmap(AlkahestPoolFeature::new, AlkahestPoolFeature::config);
    private final AlkahestPoolConfiguration config;

    public AlkahestPoolFeature(AlkahestPoolConfiguration config) {
        this.config = config;
    }

    public AlkahestPoolConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<AlkahestPoolFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;
        AlkahestPoolConfiguration config = this.config;

        int count = config.count().sample(random);
        for (int i = 0; i < count; i++) {
            this.placePool(pos.offset(config.offset().sample(random), 0, config.offset().sample(random)), level, random, config);
        }

        return true;
    }

    private void placePool(BlockPos pos, WorldGenLevel level, RandomSource random, AlkahestPoolConfiguration config) {
        int radius = config.radius().sample(random);
        for (int x = -radius; x < radius; x++) {
            for (int z = -radius; z < radius; z++) {
                for (int y = -radius; y < radius; y++) {
                    int volume = x * x + y * y + z * z;
                    int radiusSquared = (radius - 1) * (radius - 1);
                    int radiusOutlineSquared = radius * radius;
                    if (volume <= radiusOutlineSquared) {
                        BlockPos offsetPos = pos.offset(x, y, z);
                        if (y < 0) {
                            if (volume >= radiusSquared) {
                                if (!level.getBlockState(offsetPos).is(AetherIIBlocks.ALKAHEST)) {
                                    level.setBlock(offsetPos, AetherIIBlocks.ICHORITE.defaultBlockState(), 3);
                                    int limit = 1 + random.nextInt(2) + random.nextInt(5);
                                    for (int i = 1; i < limit; i++) {
                                        level.setBlock(offsetPos.below(i), AetherIIBlocks.ICHORITE.defaultBlockState(), 3);
                                        if (i == limit - 1 && random.nextBoolean()) {
                                            if (!level.getBlockState(offsetPos.below(i + 1)).isSolid()) {
                                                growPointedIchorite(level, offsetPos.below(i + 1), Direction.DOWN, 1 + random.nextInt(2), false);
                                            }
                                        }
                                    }
                                }
                            } else {
                                level.setBlock(offsetPos, AetherIIBlocks.ALKAHEST.defaultBlockState(), 3);
                            }
                        } else {
                            level.setBlock(offsetPos, Blocks.CAVE_AIR.defaultBlockState(), 3);
                        }
                    }
                }
                for (int y = 0; y < HestveilBlock.MAX_VERTICAL_DISTANCE; y++) {
                    BlockPos offsetPos = pos.offset(x, y, z);
                    if (level.getBlockState(offsetPos).isAir()) {
                        BlockState hestveilState = HestveilBlock.updateDistance(AetherIIBlocks.HESTVEIL.defaultBlockState(), level, offsetPos);
                        if (hestveilState.getValue(HestveilBlock.HORIZONTAL_DISTANCE) < HestveilBlock.MAX_HORIZONTAL_DISTANCE && hestveilState.getValue(HestveilBlock.VERTICAL_DISTANCE) < HestveilBlock.MAX_VERTICAL_DISTANCE) {
                            level.setBlock(offsetPos, HestveilBlock.updateDistance(hestveilState, level, offsetPos), 3);
                        }
                        if (!level.getBlockState(offsetPos.above()).isAir()) {
                            level.scheduleTick(offsetPos, AetherIIBlocks.HESTVEIL, 1);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    protected static void growPointedIchorite(LevelAccessor level, BlockPos pos, Direction direction, int height, boolean mergeTip) {
        if (isDripstoneBase(level.getBlockState(pos.relative(direction.getOpposite())))) {
            BlockPos.MutableBlockPos mutablePos = pos.mutable();
            buildBaseToTipColumn(direction, height, mergeTip, state -> {
                if (state.is(AetherIIBlocks.POINTED_ICHORITE)) {
                    state = state.setValue(PointedDripstoneBlock.WATERLOGGED, level.isWaterAt(mutablePos));
                }
                level.setBlock(mutablePos, state, 2);
                mutablePos.move(direction);
            });
        }
    }

    protected static void buildBaseToTipColumn(Direction direction, int height, boolean mergeTip, Consumer<BlockState> blockSetter) {
        if (height >= 3) {
            blockSetter.accept(createPointedIchorite(direction, SpeleothemThickness.BASE));

            for (int i = 0; i < height - 3; i++) {
                blockSetter.accept(createPointedIchorite(direction, SpeleothemThickness.MIDDLE));
            }
        }

        if (height >= 2) {
            blockSetter.accept(createPointedIchorite(direction, SpeleothemThickness.FRUSTUM));
        }

        if (height >= 1) {
            blockSetter.accept(createPointedIchorite(direction, mergeTip ? SpeleothemThickness.TIP_MERGE : SpeleothemThickness.TIP));
        }
    }

    private static BlockState createPointedIchorite(Direction direction, SpeleothemThickness dripstoneThickness) {
        return AetherIIBlocks.POINTED_ICHORITE.defaultBlockState().setValue(PointedDripstoneBlock.TIP_DIRECTION, direction).setValue(PointedDripstoneBlock.THICKNESS, dripstoneThickness);
    }

    public static boolean isDripstoneBase(BlockState state) {
        return state.is(AetherIIBlocks.ICHORITE);
    }
}
