package com.aetherteam.aetherii.network.packet.serverbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.attachment.AetherIIDataAttachments;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;

public record OutpostRespawnPacket() implements CustomPacketPayload {
    public static final Type<OutpostRespawnPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "outpost_respawn"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OutpostRespawnPacket> STREAM_CODEC = CustomPacketPayload.codec(
            OutpostRespawnPacket::write,
            OutpostRespawnPacket::decode);

    public void write(RegistryFriendlyByteBuf buf) {

    }

    public static OutpostRespawnPacket decode(RegistryFriendlyByteBuf buf) {
        return new OutpostRespawnPacket();
    }

    @Override
    public Type<OutpostRespawnPacket> type() {
        return TYPE;
    }

    public static void handleServer(OutpostRespawnPacket payload, ServerPlayer player) {
        ServerPlayer playerEntity = player;
        if (playerEntity != null && playerEntity.level().getServer() != null) {
            playerEntity.getAttachedOrCreate(AetherIIDataAttachments.OUTPOST_TRACKER).setShouldRespawnAtOutpost(true);
        }
    }
}
