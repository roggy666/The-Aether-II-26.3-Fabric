package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;

public record AlkahestItemSmokePacket(Vec3 pos) implements CustomPacketPayload {
    public static final Type<AlkahestItemSmokePacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "alkahest_item_smoke"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AlkahestItemSmokePacket> STREAM_CODEC = StreamCodec.composite(
            Vec3.STREAM_CODEC,
            AlkahestItemSmokePacket::pos,
            AlkahestItemSmokePacket::new);

    @Override
    public Type<AlkahestItemSmokePacket> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(AlkahestItemSmokePacket payload, Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            for (int i = 0; i < 2; ++i) {
                double d0 = Minecraft.getInstance().level.getRandom().nextGaussian() * 0.02;
                double d1 = Minecraft.getInstance().level.getRandom().nextGaussian() * 0.02;
                double d2 = Minecraft.getInstance().level.getRandom().nextGaussian() * 0.02;
                Minecraft.getInstance().level.addParticle(ParticleTypes.WHITE_SMOKE, payload.pos().x(), payload.pos().y(), payload.pos().z(), d0, d1, d2);
            }
        }
    }
}
