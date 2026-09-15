package com.aetherteam.aetherii.data.resources.registries;

import javax.annotation.Nullable;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.api.Mural;
import com.aetherteam.aetherii.api.registries.AetherIIRegistries;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class AetherIIMurals {
    public static final Registry<Mural> MURALS_REGISTRY = FabricRegistryBuilder.create(AetherIIRegistries.MURAL).attribute(RegistryAttribute.SYNCED).buildAndRegister();

    public static final Holder.Reference<Mural> TEST = register("test", 2, 2, "test_mural");
    public static final Holder.Reference<Mural> LARGE_TEST = register("large_test", 3, 2, "large_test_mural");
    public static final Holder.Reference<Mural> GIANT_TEST = register("giant_test", 4, 4, "giant_test_mural");

    private static Holder.Reference<Mural> register(String name, int width, int height, String assetId) {
        return register(name, width, height, assetId, Component.translatable(Identifier.fromNamespaceAndPath(AetherII.MODID, name).toLanguageKey("mural", "title")).withStyle(ChatFormatting.YELLOW));
    }

    private static Holder.Reference<Mural> register(String name, int width, int height, String assetId, @Nullable Component title) {
        var mural = new Mural(width, height, Identifier.fromNamespaceAndPath(AetherII.MODID, assetId), title);
        return Registry.registerForHolder(MURALS_REGISTRY, Identifier.fromNamespaceAndPath(AetherII.MODID, name), mural);
    }

    public static void init() {}
}
