package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;

public record ClientGrabItemPacket(ItemStack carryStack) implements CustomPacketPayload {
    public static final Type<ClientGrabItemPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "grab_from_inventory"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ClientGrabItemPacket> STREAM_CODEC = StreamCodec.composite(
            ItemStack.OPTIONAL_STREAM_CODEC,
            ClientGrabItemPacket::carryStack,
            ClientGrabItemPacket::new);

    @Override
    public Type<ClientGrabItemPacket> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(ClientGrabItemPacket payload, Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            Minecraft.getInstance().player.containerMenu.setCarried(payload.carryStack());
        }
    }
}
