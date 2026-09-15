package com.aetherteam.aetherii.network.packet.serverbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.entity.passive.Aerbunny;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;

/**
 * Sets the {@link Aerbunny#DATA_PUFFINESS_ID} value to 11. This is needed in a packet for precise animation syncing.
 */
public record AerbunnyPuffPacket(int entityID) implements CustomPacketPayload {
    public static final Type<AerbunnyPuffPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "aerbunny_puff"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AerbunnyPuffPacket> STREAM_CODEC = CustomPacketPayload.codec(
            AerbunnyPuffPacket::write,
            AerbunnyPuffPacket::decode);

    public void write(RegistryFriendlyByteBuf buf) {
        buf.writeInt(this.entityID());
    }

    public static AerbunnyPuffPacket decode(RegistryFriendlyByteBuf buf) {
        int entityID = buf.readInt();
        return new AerbunnyPuffPacket(entityID);
    }

    @Override
    public Type<AerbunnyPuffPacket> type() {
        return TYPE;
    }

    public static void handleServer(AerbunnyPuffPacket payload, ServerPlayer player) {
        ServerPlayer playerEntity = player;
        if (playerEntity.level().getServer() != null && playerEntity.level().getEntity(payload.entityID()) instanceof Aerbunny aerbunny) {
            aerbunny.puff();
            aerbunny.level().broadcastEntityEvent(aerbunny, (byte) Aerbunny.PUFF_PARTICLE_EVENT);
        }
    }
}
