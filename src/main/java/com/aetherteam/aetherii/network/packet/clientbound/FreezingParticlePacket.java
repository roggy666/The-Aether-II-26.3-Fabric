package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;

public record FreezingParticlePacket(Block block, BlockPos pos) implements CustomPacketPayload {
    public static final Type<FreezingParticlePacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "freezing_particles"));

    public static final StreamCodec<RegistryFriendlyByteBuf, FreezingParticlePacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.registry(Registries.BLOCK), FreezingParticlePacket::block,
            BlockPos.STREAM_CODEC, FreezingParticlePacket::pos,
            FreezingParticlePacket::new);

    @Override
    public Type<FreezingParticlePacket> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(FreezingParticlePacket payload, Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            for (Direction direction : Direction.values()) {
                for (int i = 0; i < 25; i++) {
                    ParticleUtils.spawnParticleOnFace(player.level(), payload.pos(), direction, new BlockParticleOption(ParticleTypes.BLOCK, payload.block().defaultBlockState()), Vec3.ZERO, 0.5F);
                }
            }
        }
    }
}
