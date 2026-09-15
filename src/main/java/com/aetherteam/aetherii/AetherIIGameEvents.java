package com.aetherteam.aetherii;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gameevent.GameEvent;

public class AetherIIGameEvents {
    public static final Holder.Reference<GameEvent> ICESTONE_FREEZABLE_UPDATE = Registry.registerForHolder(
            BuiltInRegistries.GAME_EVENT,
            Identifier.fromNamespaceAndPath(AetherII.MODID, "icestone_freezable_update"),
            new GameEvent(4)
    );

    public static void init() {}
}
