package com.aetherteam.aetherii.network.packet.serverbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.advancement.trigger.AetherIIAdvancementTriggers;
import com.aetherteam.aetherii.attachment.AetherIIDataAttachments;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public record CurrencyAmountPacket(int amount) implements CustomPacketPayload {
    public static final Type<CurrencyAmountPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "currency_amount"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CurrencyAmountPacket> STREAM_CODEC = CustomPacketPayload.codec(
            CurrencyAmountPacket::write,
            CurrencyAmountPacket::decode);

    public void write(RegistryFriendlyByteBuf buf) {
        buf.writeInt(amount);
    }

    public static CurrencyAmountPacket decode(RegistryFriendlyByteBuf buf) {
        return new CurrencyAmountPacket(buf.readInt());
    }

    @Override
    public Type<CurrencyAmountPacket> type() {
        return TYPE;
    }

    public static void handleServer(CurrencyAmountPacket payload, ServerPlayer player) {
        ServerPlayer playerEntity = player;
        if (playerEntity != null && playerEntity.level().getServer() != null && playerEntity instanceof ServerPlayer serverPlayer) {
            serverPlayer.getAttachedOrCreate(AetherIIDataAttachments.CURRENCY).setAmount(payload.amount);
            serverPlayer.setAttached(AetherIIDataAttachments.CURRENCY, serverPlayer.getAttachedOrCreate(AetherIIDataAttachments.CURRENCY));
            AetherIIAdvancementTriggers.CURRENCY.trigger(serverPlayer, serverPlayer.getAttachedOrCreate(AetherIIDataAttachments.CURRENCY).getAmount());
        }
    }
}
