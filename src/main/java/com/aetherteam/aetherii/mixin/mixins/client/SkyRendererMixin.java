package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.client.renderer.AetherIIDimensionRenderers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.renderpearl.api.commands.RenderPass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.world.level.MoonPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkyRenderer.class)
public class SkyRendererMixin {
    /** The cloud cover is drawn into the sky pass itself; 26.3 does not allow a nested render pass here. */
    @Inject(method = "renderSunMoonAndStars", at = @At("TAIL"))
    private void aether_ii$cloudCover(RenderPass renderPass, PoseStack poseStack, float sunAngle, float moonAngle, float starAngle, MoonPhase moonPhase, float rainBrightness, float starBrightness, CallbackInfo ci) {
        var state = Minecraft.getInstance().gameRenderer.gameRenderState().levelRenderState;
        if (state.getDataOrDefault(AetherIIDimensionRenderers.IS_HOLY_ISLES, false)) {
            AetherIIDimensionRenderers.SKY.renderCloudCover(state, renderPass);
        }
    }
}
