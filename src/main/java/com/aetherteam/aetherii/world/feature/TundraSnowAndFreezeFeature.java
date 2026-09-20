package com.aetherteam.aetherii.world.feature;

import net.minecraft.world.level.levelgen.densityfunction.DensitySampler;
import com.aetherteam.aetherii.world.density.DensitySampling;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.AetherGrassBlock;
import com.aetherteam.aetherii.block.natural.Snowable;
import com.aetherteam.aetherii.data.resources.registries.AetherIIDensityFunctions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.SnowyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;

public class TundraSnowAndFreezeFeature implements Feature {
    public static final MapCodec<TundraSnowAndFreezeFeature> CODEC = MapCodec.unit(TundraSnowAndFreezeFeature::new);

    @Override
    public MapCodec<TundraSnowAndFreezeFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {

        HolderGetter<DensityFunction> function = level.holderLookup(Registries.DENSITY_FUNCTION);
        DensitySampler.Bound noise = DensitySampling.sampler(level, AetherIIDensityFunctions.getFunction(function, AetherIIDensityFunctions.ENVIRONMENTAL_SNOW));

        BlockPos.MutableBlockPos posAbove = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos posBelow = new BlockPos.MutableBlockPos();

        int chunkX = origin.getX() - (origin.getX() % 16);
        int chunkZ = origin.getZ() - (origin.getZ() % 16);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int xCoord = chunkX + x;
                int zCoord = chunkZ + z;
                int yCoord = level.getHeight(Heightmap.Types.MOTION_BLOCKING, xCoord, zCoord);
                posAbove.set(xCoord, yCoord, zCoord);
                posBelow.set(posAbove).move(Direction.DOWN, 1);
                Biome biome = level.getBiome(posAbove).value();

                int fullSnowLimit = 196;
                double snowMagnitude = (fullSnowLimit - Math.min(fullSnowLimit, yCoord)) * 0.006F;
                double snowCalc = noise.sampleValue(xCoord, yCoord, zCoord);
                if (snowCalc >= snowMagnitude) {
                    BlockState state = level.getBlockState(posAbove);
                    BlockState ground = level.getBlockState(posBelow);
                    if (AetherGrassBlock.plantNotSnowed(state) && state.getBlock() instanceof Snowable snowable) {
                        level.setBlock(posAbove, snowable.setSnowy(state), 2);
                    } else {
                        level.setBlock(posAbove, AetherIIBlocks.ARCTIC_SNOW.defaultBlockState(), 2);
                    }
                    if (ground.hasProperty(SnowyBlock.SNOWY)) {
                        level.setBlock(posBelow, ground.setValue(SnowyBlock.SNOWY, Boolean.TRUE), 2);
                    }
                }
                if (biome.shouldFreeze(level, posBelow, false)) {
                    level.setBlock(posBelow, AetherIIBlocks.ARCTIC_ICE.defaultBlockState(), 2);
                }
            }
        }
        return true;
    }
}