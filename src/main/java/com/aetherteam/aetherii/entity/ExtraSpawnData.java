package com.aetherteam.aetherii.entity;

import net.minecraft.network.RegistryFriendlyByteBuf;

/**
 * Entities that need extra data sent to clients when they start being tracked (NeoForge's {@code IEntityWithComplexSpawn}).
 * Sent through {@link com.aetherteam.aetherii.network.packet.clientbound.ExtraSpawnDataPacket} from
 * {@code EntityTrackingEvents.START_TRACKING}.
 */
public interface ExtraSpawnData {
    void writeSpawnData(RegistryFriendlyByteBuf buffer);

    void readSpawnData(RegistryFriendlyByteBuf buffer);
}
