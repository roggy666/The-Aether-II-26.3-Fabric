package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.aetherteam.aetherii.client.renderer.AetherIIDimensionRenderers;
import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SkyRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(SkyRenderer.class)
public class SkyRendererMixin {
    @Shadow @Final private RenderTarget renderTarget;
    @Inject(method = "renderSunMoonAndStars", at = @At("TAIL"))
    private void aether_ii$cloudCover(CallbackInfo ci) {
        var state = Minecraft.getInstance().gameRenderer.gameRenderState().levelRenderState;
        if (state.getDataOrDefault(AetherIIDimensionRenderers.IS_HOLY_ISLES, false)) AetherIIDimensionRenderers.SKY.renderCloudCover(state, this.renderTarget);
    }
}
