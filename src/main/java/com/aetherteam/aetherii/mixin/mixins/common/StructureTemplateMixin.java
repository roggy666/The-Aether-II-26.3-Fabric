package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.world.structure.EntityStructureProcessor;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Lets {@link EntityStructureProcessor}s modify entities placed by a template (NeoForge's {@code processEntity} hook).
 * {@code placeEntities} doesn't receive the placement settings, so they are remembered from {@code placeInWorld}.
 */
@Mixin(StructureTemplate.class)
public abstract class StructureTemplateMixin {
    @Unique
    private final ThreadLocal<StructurePlaceSettings> aether_ii$currentSettings = new ThreadLocal<>();

    @Inject(method = "placeInWorld", at = @At("HEAD"))
    private void aether_ii$rememberSettings(ServerLevelAccessor level, BlockPos position, BlockPos referencePos, StructurePlaceSettings settings, RandomSource random, int updateMode, CallbackInfoReturnable<Boolean> cir) {
        this.aether_ii$currentSettings.set(settings);
    }

    @Inject(method = "placeInWorld", at = @At("RETURN"))
    private void aether_ii$forgetSettings(ServerLevelAccessor level, BlockPos position, BlockPos referencePos, StructurePlaceSettings settings, RandomSource random, int updateMode, CallbackInfoReturnable<Boolean> cir) {
        this.aether_ii$currentSettings.remove();
    }

    @Inject(method = "placeEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate;createEntityIgnoreException(Lnet/minecraft/util/ProblemReporter;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/nbt/CompoundTag;)Ljava/util/Optional;"))
    private void aether_ii$processEntity(ServerLevelAccessor level, BlockPos position, Mirror mirror, Rotation rotation, BlockPos pivot, BoundingBox boundingBox, boolean finalizeEntities, net.minecraft.util.ProblemReporter problemReporter, CallbackInfo ci, @Local(ordinal = 2) BlockPos blockPos, @Local CompoundTag tag, @Local(ordinal = 1) Vec3 pos) {
        StructurePlaceSettings settings = this.aether_ii$currentSettings.get();
        if (settings == null) {
            return;
        }
        for (StructureProcessor processor : settings.getProcessors()) {
            if (processor instanceof EntityStructureProcessor entityProcessor) {
                entityProcessor.processEntity(level, position, new StructureTemplate.StructureEntityInfo(pos, blockPos, tag), settings, (StructureTemplate) (Object) this);
            }
        }
    }
}
