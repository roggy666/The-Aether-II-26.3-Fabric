package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.aetherteam.aetherii.client.particle.AetherIIParticleTypes;
import com.aetherteam.aetherii.client.renderer.AetherIIDimensionRenderers;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
@Mixin(ClientLevel.class)
public class ClientWeatherMixin {
    @ModifyExpressionValue(method = "tickWeatherEffects", at = @At(value = "FIELD", target = "Lnet/minecraft/core/particles/ParticleTypes;RAIN:Lnet/minecraft/core/particles/SimpleParticleType;"))
    private SimpleParticleType aether_ii$rainSplash(SimpleParticleType original) {
        return AetherIIDimensionRenderers.isHolyIsles((ClientLevel) (Object) this) ? AetherIIParticleTypes.RAIN : original;
    }
    @ModifyExpressionValue(method = "tickWeatherEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getRainLevel(F)F"))
    private float aether_ii$stormIntensity(float rain) {
        ClientLevel level = (ClientLevel) (Object) this;
        return AetherIIDimensionRenderers.isHolyIsles(level) && level.getThunderLevel(1.0F) > 0 ? rain * 2.0F : rain;
    }
}
