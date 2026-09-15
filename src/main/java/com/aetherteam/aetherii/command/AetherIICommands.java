package com.aetherteam.aetherii.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class AetherIICommands {
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            DungeonBlockLockCommand.register(dispatcher, registryAccess);
        });
    }
}
