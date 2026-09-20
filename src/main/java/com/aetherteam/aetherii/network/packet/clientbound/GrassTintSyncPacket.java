package com.aetherteam.aetherii.network.packet.clientbound;

import java.util.HashMap;
import net.minecraft.network.codec.ByteBufCodecs;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.client.event.hooks.BiomeHooks;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;

import java.util.Map;

public record GrassTintSyncPacket(Map<ResourceKey<Biome>, Integer> types) implements CustomPacketPayload {

    public static final Type<GrassTintSyncPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "sync_grass_tint"));

    public static final StreamCodec<RegistryFriendlyByteBuf, GrassTintSyncPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.map(HashMap::new, ResourceKey.streamCodec(Registries.BIOME), ByteBufCodecs.INT), GrassTintSyncPacket::types,
            GrassTintSyncPacket::new);




    @Environment(EnvType.CLIENT)
    public static void handleClient(GrassTintSyncPacket packet, Player player) {
        if (Minecraft.getInstance().level != null) {
            BiomeHooks.acceptColors(Minecraft.getInstance().level.registryAccess().lookupOrThrow(Registries.BIOME), packet.types);
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
