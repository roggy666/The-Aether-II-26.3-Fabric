package com.aetherteam.aetherii.mixin.mixins.common;

import net.minecraft.server.level.WorldGenRegion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldGenRegion.class)
public class WorldGenRegionMixin {
    @Inject(method = "warnIfReadOutsideWriteZone", at = @At("HEAD"), cancellable = true)
    private void aetherii$silenceUnsafeTerrainRead(int chunkX, int chunkZ, CallbackInfo ci) {
        ci.cancel();
    }
}
