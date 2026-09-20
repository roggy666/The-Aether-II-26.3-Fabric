package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HestveilFeature implements Feature {
    public static final MapCodec<HestveilFeature> CODEC = MapCodec.unit(HestveilFeature::new);

    @Override
    public MapCodec<HestveilFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;

        List<BlockPos> positions = new ArrayList<>(List.of(pos));
        Set<BlockPos> visited = new HashSet<>();

        while (!positions.isEmpty()) {
            BlockPos storedPos = positions.removeLast();
            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = storedPos.relative(direction);
                if (Vector3i.distance(pos.getX(), 0, pos.getZ(), offsetPos.getX(), 0, offsetPos.getZ()) <= 4 + random.nextInt(5) && level.getBlockState(offsetPos).isAir() && !visited.contains(offsetPos)) {
                    boolean betweenCeiling = false;
                    boolean betweenFloor = false;
                    for (int y = 0; y < 8; y++) {
                        if (level.getBlockState(offsetPos.above(y)).is(AetherIITags.Blocks.AETHER_UNDERGROUND_BLOCKS)) {
                            betweenCeiling = true;
                        }
                        if (level.getBlockState(offsetPos.below(y)).is(AetherIITags.Blocks.AETHER_UNDERGROUND_BLOCKS)) {
                            betweenFloor = true;
                        }
                    }
                    if (betweenCeiling && betweenFloor) {
                        positions.add(offsetPos);
                    }
                }
            }
            if (level.getBlockState(storedPos).isAir()) {
                level.setBlock(storedPos, AetherIIBlocks.HESTVEIL.defaultBlockState(), 3);
            }
            visited.add(storedPos);
        }

        return true;
    }
}
