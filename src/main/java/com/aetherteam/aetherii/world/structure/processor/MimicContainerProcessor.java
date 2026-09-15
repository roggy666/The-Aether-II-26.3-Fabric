package com.aetherteam.aetherii.world.structure.processor;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.item.components.AetherIIDataComponents;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

public class MimicContainerProcessor implements StructureProcessor {
    public static final MimicContainerProcessor INSTANCE = new MimicContainerProcessor();

    public static final MapCodec<MimicContainerProcessor> CODEC = MapCodec.unit(MimicContainerProcessor.INSTANCE);

    @SuppressWarnings("deprecation")
    @Override
    public @Nullable StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo processedBlockInfo, StructurePlaceSettings settings) {
        RandomSource random = RandomSource.create(Mth.getSeed(templateRelativePos));
        if (processedBlockInfo.state().is(AetherIITags.Blocks.MIMIC_CONTAINERS)) {
            if (random.nextDouble() <= 0.3) {
                CompoundTag tag = processedBlockInfo.nbt();
                if (tag != null) {
                    DataComponentMap oldMap = tag.read("components", DataComponentMap.CODEC).orElse(DataComponentMap.EMPTY);
                    DataComponentMap newMap = DataComponentMap.builder().addAll(oldMap).set(AetherIIDataComponents.MIMIC, true).build();
                    tag.store("components", DataComponentMap.CODEC, newMap);
                }
            }
        }
        return processedBlockInfo;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return AetherIIStructureProcessorTypes.MIMIC_CONTAINER;
    }
}