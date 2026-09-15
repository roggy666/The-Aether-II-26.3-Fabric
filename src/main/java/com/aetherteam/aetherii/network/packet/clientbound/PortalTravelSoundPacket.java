package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.block.portal.PortalClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;

public record PortalTravelSoundPacket() implements CustomPacketPayload {
    public static final Type<PortalTravelSoundPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "play_portal_travel_sound"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PortalTravelSoundPacket> STREAM_CODEC = StreamCodec.unit(new PortalTravelSoundPacket());

    @Override
    public Type<PortalTravelSoundPacket> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(PortalTravelSoundPacket payload, Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            PortalClientUtil.playTravelSound();
        }
    }
}