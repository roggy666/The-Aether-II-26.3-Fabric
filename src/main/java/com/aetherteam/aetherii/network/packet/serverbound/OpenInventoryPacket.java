package com.aetherteam.aetherii.network.packet.serverbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.network.packet.clientbound.ClientGrabItemPacket;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public record OpenInventoryPacket(ItemStack carryStack) implements CustomPacketPayload {
    public static final Type<OpenInventoryPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "open_inventory"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OpenInventoryPacket> STREAM_CODEC = StreamCodec.composite(
            ItemStack.OPTIONAL_STREAM_CODEC,
            OpenInventoryPacket::carryStack,
            OpenInventoryPacket::new);

    @Override
    public Type<OpenInventoryPacket> type() {
        return TYPE;
    }

    public static void handleServer(OpenInventoryPacket payload, ServerPlayer player) {
        ServerPlayer playerEntity = player;
        if (playerEntity != null && playerEntity.level().getServer() != null && playerEntity instanceof ServerPlayer serverPlayer) {
            ItemStack itemStack = serverPlayer.isCreative() ? payload.carryStack() : serverPlayer.containerMenu.getCarried();
            serverPlayer.containerMenu.setCarried(ItemStack.EMPTY);
            serverPlayer.doCloseContainer();
            if (!itemStack.isEmpty()) {
                if (!serverPlayer.isCreative()) {
                    serverPlayer.containerMenu.setCarried(itemStack);
                    ServerPlayNetworking.send(serverPlayer, new ClientGrabItemPacket(itemStack));
                }
            }
        }
    }
}
