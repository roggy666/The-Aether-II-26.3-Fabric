package com.aetherteam.aetherii.world.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * Structure processors that also want to touch entities placed by a template (NeoForge's
 * {@code StructureProcessor#processEntity}). Invoked from {@code StructureTemplateMixin} right before each
 * entity is created; the info's {@code nbt} may be modified in place.
 */
public interface EntityStructureProcessor extends StructureProcessor {
    void processEntity(ServerLevelAccessor level, BlockPos seedPos, StructureTemplate.StructureEntityInfo entityInfo, StructurePlaceSettings placementSettings, StructureTemplate template);
}
