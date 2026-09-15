package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.client.AetherIIKeyMappings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;

public record AerbunnyMessagePacket() implements CustomPacketPayload {
    public static final Type<AerbunnyMessagePacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "aerbunny_message"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AerbunnyMessagePacket> STREAM_CODEC = StreamCodec.unit(new AerbunnyMessagePacket());

    @Override
    public Type<AerbunnyMessagePacket> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(AerbunnyMessagePacket payload, Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            Component component = Component.translatable("aether_ii.message.passenger.onboard", AetherIIKeyMappings.ALLOW_DISMOUNTING_PASSENGER.getTranslatedKeyMessage(), Minecraft.getInstance().options.keyUse.getTranslatedKeyMessage());
            Minecraft.getInstance().gui.hud.setOverlayMessage(component, false);
            Minecraft.getInstance().getNarrator().saySystemNow(component);
        }
    }
}
