package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.attachment.AetherIIDataAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;

public record FlushGuidebookDataPacket() implements CustomPacketPayload {
    public static final Type<FlushGuidebookDataPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "flush_guidebook_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, FlushGuidebookDataPacket> STREAM_CODEC = CustomPacketPayload.codec(FlushGuidebookDataPacket::write, FlushGuidebookDataPacket::decode);

    public void write(RegistryFriendlyByteBuf buf) { }

    public static FlushGuidebookDataPacket decode(RegistryFriendlyByteBuf buf) {
        return new FlushGuidebookDataPacket();
    }

    @Override
    public Type<FlushGuidebookDataPacket> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(FlushGuidebookDataPacket payload, Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            Minecraft.getInstance().player.getAttachedOrCreate(AetherIIDataAttachments.GUIDEBOOK_DISCOVERY).clearEntries();
        }
    }
}
