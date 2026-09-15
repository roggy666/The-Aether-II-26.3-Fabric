package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.client.sound.AetherIISoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;

public record ForgeSoundPacket(BlockPos pos) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ForgeSoundPacket> TYPE = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "forge_sound"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ForgeSoundPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            ForgeSoundPacket::pos,
            ForgeSoundPacket::new);

    @Override
    public CustomPacketPayload.Type<ForgeSoundPacket> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(ForgeSoundPacket payload, Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            Minecraft.getInstance().level.playLocalSound(payload.pos(), AetherIISoundEvents.BLOCK_ARKENIUM_FORGE_USE, SoundSource.BLOCKS, 1.0F, Minecraft.getInstance().level.getRandom().nextFloat() * 0.1F + 0.9F, false);
        }
    }
}

