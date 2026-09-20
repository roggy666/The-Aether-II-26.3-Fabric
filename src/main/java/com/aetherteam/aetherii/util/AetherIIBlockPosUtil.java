package com.aetherteam.aetherii.util;

import net.minecraft.core.BlockPos;

import java.util.Optional;
import java.util.function.Predicate;

public final class AetherIIBlockPosUtil {
    private AetherIIBlockPosUtil() {}

    /** {@code BlockPos#findClosestMatch} as it existed before 26.3: nearest position by Manhattan distance inside the given box. */
    public static Optional<BlockPos> findClosestMatch(BlockPos start, int horizontalSearchRadius, int verticalSearchRadius, Predicate<BlockPos> predicate) {
        for (BlockPos pos : BlockPos.withinBoxByManhattanDistance(start, horizontalSearchRadius, verticalSearchRadius, horizontalSearchRadius)) {
            if (predicate.test(pos)) {
                return Optional.of(pos);
            }
        }
        return Optional.empty();
    }
}
