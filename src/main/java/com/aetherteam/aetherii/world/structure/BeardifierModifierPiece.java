package com.aetherteam.aetherii.world.structure;

import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;

/**
 * Structure pieces that override how the {@code Beardifier} treats them (NeoForge's {@code PieceBeardifierModifier}).
 * Applied through {@link com.aetherteam.aetherii.mixin.mixins.common.BeardifierMixin}.
 */
public interface BeardifierModifierPiece {
    BoundingBox getBeardifierBox();

    TerrainAdjustment getTerrainAdjustment();

    int getGroundLevelDelta();
}
