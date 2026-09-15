package com.aetherteam.aetherii.client.renderer.item.color;

import net.minecraft.client.color.item.ItemTintSources;
import com.aetherteam.aetherii.AetherII;
import net.minecraft.resources.Identifier;

public class AetherIIItemTintSources {
    public static void registerTintSources() {
        ItemTintSources.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "aether_grass"), AetherGrassColorSource.MAP_CODEC);
        ItemTintSources.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "effect_buildup"), EffectBuildupColorSource.MAP_CODEC);
    }
}
