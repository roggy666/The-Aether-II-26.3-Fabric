package com.aetherteam.aetherii.loot;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.function.Consumer;

public class AetherIILootContexts {
    public static final ContextKeySet STRIPPING = register("stripping", (builder) -> builder.required(LootContextParams.TOOL));

    /** Forces class initialization during mod setup, while the built-in registries are still open. */
    public static void init() {}

    private static ContextKeySet register(String name, Consumer<ContextKeySet.Builder> consumer) {
        ContextKeySet.Builder builder = new ContextKeySet.Builder();
        consumer.accept(builder);
        return Registry.register(BuiltInRegistries.CONTEXT_KEY_SET, Identifier.fromNamespaceAndPath(AetherII.MODID, name), builder.build());
    }
}
