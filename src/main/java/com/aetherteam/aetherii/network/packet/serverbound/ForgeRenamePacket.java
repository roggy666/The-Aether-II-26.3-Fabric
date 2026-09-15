package com.aetherteam.aetherii.network.packet.serverbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.inventory.menu.ArkeniumForgeMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;

public record ForgeRenamePacket(String name) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ForgeRenamePacket> TYPE = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "forge_rename"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ForgeRenamePacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ForgeRenamePacket::name,
            ForgeRenamePacket::new);

    @Override
    public CustomPacketPayload.Type<ForgeRenamePacket> type() {
        return TYPE;
    }

    public static void handleServer(ForgeRenamePacket payload, ServerPlayer player) {
        ServerPlayer playerEntity = player;
        if (playerEntity.containerMenu instanceof ArkeniumForgeMenu menu) {
            if (menu.stillValid(playerEntity)) {
                menu.setItemName(payload.name());
            }
        }
    }
}
