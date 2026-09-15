package com.aetherteam.aetherii.client.sprite;

import net.fabricmc.fabric.api.client.rendering.v1.SpriteSourceRegistry;
import com.aetherteam.aetherii.AetherII;
import net.minecraft.resources.Identifier;

public class AetherIISpriteSourceTypes {
    public static void registerSpriteSourceTypes() {
        SpriteSourceRegistry.register(Identifier.fromNamespaceAndPath(AetherII.MODID, "additive"), Additive.CODEC);
        SpriteSourceRegistry.register(Identifier.fromNamespaceAndPath(AetherII.MODID, "subtractive"), Subtractive.CODEC);
        SpriteSourceRegistry.register(Identifier.fromNamespaceAndPath(AetherII.MODID, "squares"), Squares.CODEC);
    }
}