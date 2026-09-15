package com.aetherteam.aetherii.world.structure.processor;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nullable;

public class RemoveInAirProcessor implements StructureProcessor {
    public static final RemoveInAirProcessor INSTANCE = new RemoveInAirProcessor();

    public static final MapCodec<RemoveInAirProcessor> CODEC = MapCodec.unit(RemoveInAirProcessor.INSTANCE);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo processedBlockInfo, StructurePlaceSettings settings) {
        if (level.getBlockState(targetPosition).is(Blocks.AIR)) {
            return null;
        }
        return processedBlockInfo;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return AetherIIStructureProcessorTypes.REMOVE_IN_AIR;
    }
}