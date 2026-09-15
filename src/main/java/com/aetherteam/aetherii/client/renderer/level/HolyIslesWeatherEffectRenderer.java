package com.aetherteam.aetherii.client.renderer.level;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.resources.Identifier;

public final class HolyIslesWeatherEffectRenderer {
    private static final Identifier RAIN = Identifier.fromNamespaceAndPath(AetherII.MODID, "textures/environment/rain.png");
    private static final Identifier RAIN_STORMY = Identifier.fromNamespaceAndPath(AetherII.MODID, "textures/environment/rain_stormy.png");
    private static final Identifier SNOW = Identifier.fromNamespaceAndPath(AetherII.MODID, "textures/environment/snow.png");
    private static final Identifier SNOW_STORMY = Identifier.fromNamespaceAndPath(AetherII.MODID, "textures/environment/snow_stormy.png");

    public static Identifier texture(Identifier vanilla, float thunder) {
        if (vanilla.equals(Identifier.withDefaultNamespace("textures/environment/rain.png"))) return thunder > 0 ? RAIN_STORMY : RAIN;
        if (vanilla.equals(Identifier.withDefaultNamespace("textures/environment/snow.png"))) return thunder > 0 ? SNOW_STORMY : SNOW;
        return vanilla;
    }
}
