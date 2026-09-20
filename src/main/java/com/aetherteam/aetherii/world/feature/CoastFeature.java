package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.levelgen.densityfunction.DensitySampler;
import com.aetherteam.aetherii.world.density.DensitySampling;
import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.world.feature.configuration.CoastConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.HashSet;
import java.util.Set;

public class CoastFeature implements Feature {
    public static final MapCodec<CoastFeature> CODEC = CoastConfiguration.CODEC.xmap(CoastFeature::new, CoastFeature::config);
    private final CoastConfiguration config;

    public CoastFeature(CoastConfiguration config) {
        this.config = config;
    }

    public CoastConfiguration config() {
        return this.config;
    }

    @Override
    public MapCodec<CoastFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockPos pos = origin;
        CoastConfiguration config = this.config;
        Set<BlockPos> set = new HashSet<>();

        DensitySampler.Bound distanceNoise = DensitySampling.sampler(level, config.distanceNoise());

        for (int x = pos.getX(); x < pos.getX() + 16; ++x) {
            for (int z = pos.getZ(); z < pos.getZ() + 16; ++z) {
                for (int y = config.yRange().minInclusive(); y < config.yRange().maxInclusive(); ++y) {
                    BlockPos placementPos = new BlockPos(x, y, z);
                    int distance = (int) distanceNoise.sampleValue(x, y, z);

                        if (level.getBlockState(placementPos).isAir()
                                && level.getBlockState(placementPos.below(2)).isAir()
                                && level.getBlockState(placementPos.below(4)).isAir()
                                && level.getBlockState(placementPos.below(8)).isAir()
                                && level.getBlockState(placementPos.below(16)).isAir()
                                && level.getBlockState(placementPos.above()).is(config.validBlocks()) && level.getBlockState(placementPos.above(2)).isAir()) {
                            placeCoast(level, config.block(), placementPos, config.size(), random, distance, set);
                            placeCoast(level, config.block(), placementPos.below(), config.size(), random, (int) (distance / 1.75F), set);
                            break;
                        }
                }
            }
        }
        this.distributeVegetation(level, chunkGenerator, config, random, origin, set);
        return true;
    }

    public static void placeCoast(WorldGenLevel level, BlockStateProvider blockProvider, BlockPos center, float radius, RandomSource random, int distance, Set<BlockPos> set) {
        float radiusSq = radius * radius;
        placeCoastBlock(level, blockProvider, center, random, distance, set);
        for (int z = 0; z < radius; z++) {
            for (int x = 0; x < radius; x++) {
                if (x * x + z * z > radiusSq) continue;
                placeCoastBlock(level, blockProvider, center.offset(x, 0, z), random, distance, set);
                placeCoastBlock(level, blockProvider, center.offset(-x, 0, -z), random, distance, set);
                placeCoastBlock(level, blockProvider, center.offset(-z, 0, x), random, distance, set);
                placeCoastBlock(level, blockProvider, center.offset(z, 0, -x), random, distance, set);
            }
        }
    }

    @SuppressWarnings({"UnusedReturnValue", "deprecation"})
    public static boolean placeCoastBlock(WorldGenLevel level, BlockStateProvider provider, BlockPos pos, RandomSource random, int distance, Set<BlockPos> set) {
        if (!level.ensureCanWrite(pos)) {
            return false;
        }
        if (level.getBlockState(pos).canBeReplaced() && !level.getBlockState(pos).liquid()
                && (canReadAndShapesCoasts(level, pos.north(distance))
                || canReadAndShapesCoasts(level, pos.east(distance))
                || canReadAndShapesCoasts(level, pos.south(distance))
                || canReadAndShapesCoasts(level, pos.west(distance))
        )) {
            BlockState state = provider.getState(level, random, pos);
            if (level.setBlock(pos, state, 2)) {
                set.add(pos);
                return true;
            }
        }
        return false;
    }

    private static boolean canReadAndShapesCoasts(WorldGenLevel level, BlockPos pos) {
        if (level instanceof WorldGenRegion region && !region.isWithinWriteZone(pos)) {
            return false;
        }
        int cx = SectionPos.blockToSectionCoord(pos.getX());
        int cz = SectionPos.blockToSectionCoord(pos.getZ());
        if (!level.hasChunk(cx, cz)) {
            return false;
        }
        return level.getBlockState(pos).is(AetherIITags.Blocks.SHAPES_COASTS);
    }

    protected void distributeVegetation(WorldGenLevel level, ChunkGenerator chunkGenerator, CoastConfiguration config, RandomSource random, BlockPos origin, Set<BlockPos> set) {
        ChunkPos centerPos = level instanceof WorldGenRegion region ? region.getCenter() : ChunkPos.containing(origin);
        for (BlockPos blockPos : set) {
            if (SectionPos.blockToSectionCoord(blockPos.getX()) != centerPos.x() || SectionPos.blockToSectionCoord(blockPos.getZ()) != centerPos.z()) {
                continue;
            }
            if (config.vegetationChance() > 0.0F && random.nextFloat() < config.vegetationChance()) {
                if (level.ensureCanWrite(blockPos)) {
                    config.vegetationFeature().ifPresent(placedFeatureHolder -> placedFeatureHolder.value().place(level, chunkGenerator, random, blockPos));
                }
            }
        }
    }
}