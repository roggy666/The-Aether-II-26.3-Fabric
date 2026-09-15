package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.aetherteam.aetherii.block.AetherIIFluids;
import com.aetherteam.aetherii.client.event.listeners.DimensionClientListener;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @Inject(method = "setupFog", at = @At("RETURN"))
    private void aether_ii$fog(Camera camera, int renderDistance, DeltaTracker delta, float darken, ClientLevel level, CallbackInfoReturnable<FogData> cir) {
        FogData fog = cir.getReturnValue();
        var fluid = level.getFluidState(camera.blockPosition());
        if (fluid.getType().isSame(AetherIIFluids.ALKAHEST) && camera.position().y < camera.blockPosition().getY() + fluid.getHeight(level, camera.blockPosition())) {
            fog.color.set(170 / 255.0F, 226 / 255.0F, 149 / 255.0F, 1.0F);
            fog.environmentalStart = 0;
            fog.environmentalEnd = 12;
        } else {
            DimensionClientListener.adjustFog(camera, fog);
            DimensionClientListener.adjustFogColor(camera, level, renderDistance, delta.getGameTimeDeltaPartialTick(false), fog);
        }
    }
}
