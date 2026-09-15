package com.aetherteam.aetherii.network.packet.serverbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.inventory.menu.ArkeniumForgeMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;

public record ForgeTriggerSoundPacket() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ForgeTriggerSoundPacket> TYPE = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "forge_trigger_sound"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ForgeTriggerSoundPacket> STREAM_CODEC = CustomPacketPayload.codec(
            ForgeTriggerSoundPacket::write,
            ForgeTriggerSoundPacket::decode);

    public void write(RegistryFriendlyByteBuf buf) {

    }

    public static ForgeTriggerSoundPacket decode(RegistryFriendlyByteBuf buf) {
        return new ForgeTriggerSoundPacket();
    }

    @Override
    public CustomPacketPayload.Type<ForgeTriggerSoundPacket> type() {
        return TYPE;
    }

    public static void handleServer(ForgeTriggerSoundPacket payload, ServerPlayer player) {
        ServerPlayer playerEntity = player;
        if (playerEntity.containerMenu instanceof ArkeniumForgeMenu menu) {
            if (menu.stillValid(playerEntity)) {
                menu.playSound();
            }
        }
    }
}

