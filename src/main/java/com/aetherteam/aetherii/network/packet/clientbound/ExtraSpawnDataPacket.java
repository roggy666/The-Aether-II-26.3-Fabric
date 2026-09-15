package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.entity.ExtraSpawnData;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

/**
 * Carries {@link ExtraSpawnData} for an entity. The client may receive it before the entity exists, in which case it
 * is kept until the entity is added (see {@code AetherIIClientEventListeners}).
 */
public record ExtraSpawnDataPacket(int entityId, byte[] data) implements CustomPacketPayload {
    public static final Type<ExtraSpawnDataPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "extra_spawn_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ExtraSpawnDataPacket> STREAM_CODEC = CustomPacketPayload.codec(
            ExtraSpawnDataPacket::write,
            ExtraSpawnDataPacket::decode);

    @Environment(EnvType.CLIENT)
    private static final Int2ObjectMap<byte[]> PENDING = new Int2ObjectOpenHashMap<>();

    public static ExtraSpawnDataPacket create(Entity entity) {
        RegistryFriendlyByteBuf buf = new RegistryFriendlyByteBuf(Unpooled.buffer(), entity.registryAccess());
        ((ExtraSpawnData) entity).writeSpawnData(buf);
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        buf.release();
        return new ExtraSpawnDataPacket(entity.getId(), bytes);
    }

    public void write(RegistryFriendlyByteBuf buf) {
        buf.writeVarInt(this.entityId);
        buf.writeByteArray(this.data);
    }

    public static ExtraSpawnDataPacket decode(RegistryFriendlyByteBuf buf) {
        return new ExtraSpawnDataPacket(buf.readVarInt(), buf.readByteArray());
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(ExtraSpawnDataPacket packet, Player player) {
        if (Minecraft.getInstance().level == null) {
            return;
        }
        Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId);
        if (entity instanceof ExtraSpawnData) {
            apply(entity, packet.data);
        } else {
            PENDING.put(packet.entityId, packet.data);
        }
    }

    @Environment(EnvType.CLIENT)
    public static void onEntityAdded(Entity entity) {
        if (entity instanceof ExtraSpawnData) {
            byte[] data = PENDING.remove(entity.getId());
            if (data != null) {
                apply(entity, data);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static void clearPending() {
        PENDING.clear();
    }

    @Environment(EnvType.CLIENT)
    private static void apply(Entity entity, byte[] data) {
        RegistryFriendlyByteBuf buf = new RegistryFriendlyByteBuf(Unpooled.wrappedBuffer(data), entity.registryAccess());
        ((ExtraSpawnData) entity).readSpawnData(buf);
        buf.release();
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
