package com.aetherteam.aetherii.world.structure.processor;

import com.aetherteam.aetherii.world.density.DensitySampling;
import com.aetherteam.aetherii.block.dungeon.CopyBlock;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DensityFunctionProcessor implements StructureProcessor {
    private final BlockState inputState;
    private final BlockState outputState;
    public final DensityFunction density;
    public final boolean modifyCopyBlocks;

    public static final MapCodec<DensityFunctionProcessor> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockState.CODEC.fieldOf("input_state").forGetter(codec -> codec.inputState),
            BlockState.CODEC.fieldOf("output_state").forGetter(codec -> codec.outputState),
            DensityFunction.CODEC.fieldOf("density_function").forGetter(codec -> codec.density),
            Codec.BOOL.fieldOf("modify_copy_blocks").forGetter(codec -> codec.modifyCopyBlocks)
            ).apply(instance, DensityFunctionProcessor::new)
    );

    public DensityFunctionProcessor(BlockState inputState, BlockState outputState, DensityFunction density, boolean modifyCopyBlocks) {
        this.inputState = inputState;
        this.outputState = outputState;
        this.density = density;
        this.modifyCopyBlocks = modifyCopyBlocks;
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo processedBlockInfo, StructurePlaceSettings settings) {
        if (level instanceof WorldGenLevel worldGenLevel) {
            double noise = DensitySampling.sampler(worldGenLevel, this.density).sampleValue(processedBlockInfo.pos().getX(), processedBlockInfo.pos().getY(), processedBlockInfo.pos().getZ());
            BlockState state = processedBlockInfo.state();
            if (noise > 0) {
                if (modifyCopyBlocks) {
                    if (state.getBlock() instanceof CopyBlock copyBlock) {
                        CompoundTag tag = processedBlockInfo.nbt();
                        if (tag != null) {
                            Optional<BlockState> copyState = tag.read("copy_state", BlockState.CODEC);
                            if (copyState.isPresent() && copyState.equals(Optional.of(inputState))) {
                                tag.store("copy_state", BlockState.CODEC, outputState);
                                return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), copyBlock.defaultBlockState().setValue(CopyBlock.EMPTY, false), tag);
                            }
                        }
                    }
                }
                if (state == inputState) {
                    return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), outputState, processedBlockInfo.nbt());
                }
            }
        }
        return processedBlockInfo;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return AetherIIStructureProcessorTypes.DENSITY_FUNCTION;
    }
}