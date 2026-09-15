package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.phys.Vec3;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;

public record AlkahestFizzPacket(BlockPos pos, Direction face) implements CustomPacketPayload {
    public static final Type<AlkahestFizzPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "alkahest_fizz"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AlkahestFizzPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            AlkahestFizzPacket::pos,
            Direction.STREAM_CODEC,
            AlkahestFizzPacket::face,
            AlkahestFizzPacket::new);

    @Override
    public Type<AlkahestFizzPacket> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(AlkahestFizzPacket payload, Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            ParticleUtils.spawnParticlesOnBlockFace(player.level(), payload.pos().offset(payload.face().getOpposite().getUnitVec3i()), ParticleTypes.WHITE_SMOKE, UniformInt.of(10, 20), payload.face(), () -> Vec3.ZERO, 0.5);
        }
    }
}
