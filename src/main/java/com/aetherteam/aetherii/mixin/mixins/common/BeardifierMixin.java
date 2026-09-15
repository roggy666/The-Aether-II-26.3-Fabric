package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.world.structure.BeardifierModifierPiece;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.levelgen.Beardifier;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Lets {@link BeardifierModifierPiece}s override their terrain adaptation (NeoForge's {@code PieceBeardifierModifier}).
 */
@Mixin(Beardifier.class)
public abstract class BeardifierMixin {
    @WrapOperation(method = "forStructuresInChunk", at = @At(value = "NEW", target = "(Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/world/level/levelgen/structure/TerrainAdjustment;I)Lnet/minecraft/world/level/levelgen/Beardifier$Rigid;"))
    private static Beardifier.Rigid aether_ii$modifyRigid(BoundingBox box, TerrainAdjustment terrainAdjustment, int groundLevelDelta, Operation<Beardifier.Rigid> original, @Local StructurePiece piece) {
        if (piece instanceof BeardifierModifierPiece modifier) {
            return original.call(modifier.getBeardifierBox(), modifier.getTerrainAdjustment(), modifier.getGroundLevelDelta());
        }
        return original.call(box, terrainAdjustment, groundLevelDelta);
    }
}
