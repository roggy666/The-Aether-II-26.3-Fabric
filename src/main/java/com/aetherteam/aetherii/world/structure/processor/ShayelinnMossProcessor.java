package com.aetherteam.aetherii.world.structure.processor;

import net.minecraft.world.level.block.state.BlockState;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.BottomedVineBlock;
import com.aetherteam.aetherii.block.natural.MossFlowersBlock;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nullable;

public class ShayelinnMossProcessor implements StructureProcessor {
    public static final ShayelinnMossProcessor INSTANCE = new ShayelinnMossProcessor();

    public static final MapCodec<ShayelinnMossProcessor> CODEC = MapCodec.unit(ShayelinnMossProcessor.INSTANCE);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo processedBlockInfo, StructurePlaceSettings settings) {
        BlockState state = processedBlockInfo.state();
        if (state.is(AetherIIBlocks.BRYALINN_MOSS_BLOCK)) {
            return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), AetherIIBlocks.SHAYELINN_MOSS_BLOCK.defaultBlockState(), processedBlockInfo.nbt());
        }
        if (state.is(AetherIIBlocks.BRYALINN_MOSS_CARPET)) {
            return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), AetherIIBlocks.SHAYELINN_MOSS_CARPET.defaultBlockState(), processedBlockInfo.nbt());
        }
        if (state.is(AetherIIBlocks.AETHER_BUSH)) {
            return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), AetherIIBlocks.GREATBOA_LEAVES.defaultBlockState(), processedBlockInfo.nbt());
        }
        if (state.is(AetherIIBlocks.BRYALINN_MOSS_VINES)) {
            return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), AetherIIBlocks.SHAYELINN_MOSS_VINES.defaultBlockState()
                    .setValue(BottomedVineBlock.UP, state.getValue(BottomedVineBlock.UP))
                    .setValue(BottomedVineBlock.NORTH, state.getValue(BottomedVineBlock.NORTH))
                    .setValue(BottomedVineBlock.EAST, state.getValue(BottomedVineBlock.EAST))
                    .setValue(BottomedVineBlock.SOUTH, state.getValue(BottomedVineBlock.SOUTH))
                    .setValue(BottomedVineBlock.WEST, state.getValue(BottomedVineBlock.WEST))
                    .setValue(BottomedVineBlock.AGE, state.getValue(BottomedVineBlock.AGE)), processedBlockInfo.nbt());
        }
        if (state.is(AetherIIBlocks.BRYALINN_MOSS_FLOWERS)) {
            return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), AetherIIBlocks.HOLPUPEA.defaultBlockState()
                    .setValue(MossFlowersBlock.FACING, state.getValue(MossFlowersBlock.FACING))
                    .setValue(MossFlowersBlock.AMOUNT, state.getValue(MossFlowersBlock.AMOUNT)), processedBlockInfo.nbt());
        }
        return processedBlockInfo;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return AetherIIStructureProcessorTypes.SHAYELINN_MOSS;
    }
}