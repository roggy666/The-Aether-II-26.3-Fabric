package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.aetherteam.aetherii.client.renderer.AetherIIDimensionRenderers;
import com.aetherteam.aetherii.client.renderer.level.HolyIslesWeatherEffectRenderer;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WeatherEffectRenderer;
import net.minecraft.resources.Identifier;
@Mixin(WeatherEffectRenderer.class)
public class WeatherEffectRendererMixin {
    @ModifyExpressionValue(method = "render", at = {@At(value = "FIELD", target = "Lnet/minecraft/client/renderer/WeatherEffectRenderer;RAIN_LOCATION:Lnet/minecraft/resources/Identifier;"), @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/WeatherEffectRenderer;SNOW_LOCATION:Lnet/minecraft/resources/Identifier;")})
    private Identifier aether_ii$weatherTexture(Identifier original) {
        var state = Minecraft.getInstance().gameRenderer.gameRenderState().levelRenderState;
        return state.getDataOrDefault(AetherIIDimensionRenderers.IS_HOLY_ISLES, false)
                ? HolyIslesWeatherEffectRenderer.texture(original, state.getDataOrDefault(AetherIIDimensionRenderers.DATA_THUNDER_KEY, 0.0F)) : original;
    }
}
