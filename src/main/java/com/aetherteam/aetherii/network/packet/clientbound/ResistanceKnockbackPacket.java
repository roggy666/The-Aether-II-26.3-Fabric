package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;

public record ResistanceKnockbackPacket(int sourceID, int targetID) implements CustomPacketPayload {
    public static final Type<ResistanceKnockbackPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "resistance_knockback"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ResistanceKnockbackPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            ResistanceKnockbackPacket::sourceID,
            ByteBufCodecs.INT,
            ResistanceKnockbackPacket::targetID,
            ResistanceKnockbackPacket::new);

    @Override
    public Type<ResistanceKnockbackPacket> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(ResistanceKnockbackPacket payload, Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            Level level = Minecraft.getInstance().player.level();
            if (level.getEntity(payload.sourceID()) instanceof LivingEntity source && level.getEntity(payload.targetID()) instanceof Entity entity) {
                source.knockback(0.75 / (1 + source.distanceTo(entity)), -Mth.sin(source.getYRot() * Mth.DEG_TO_RAD), Mth.cos(source.getYRot() * Mth.DEG_TO_RAD), entity.damageSources().generic(), 0.0F);
            }
        }
    }
}
