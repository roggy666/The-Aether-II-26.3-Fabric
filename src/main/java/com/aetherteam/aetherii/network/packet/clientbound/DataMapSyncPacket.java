package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.api.datamap.DataMapManager;
import com.aetherteam.aetherii.api.datamap.DataMapType;
import com.mojang.serialization.Codec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Sends every synced {@link DataMapType} to the client (on join and after {@code /reload}).
 */
public record DataMapSyncPacket(List<MapData<?, ?>> maps) implements CustomPacketPayload {
    public static final Type<DataMapSyncPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "sync_data_maps"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DataMapSyncPacket> STREAM_CODEC = CustomPacketPayload.codec(
            DataMapSyncPacket::write,
            DataMapSyncPacket::decode);

    public static DataMapSyncPacket create() {
        List<MapData<?, ?>> maps = new ArrayList<>();
        for (DataMapType<?, ?> type : DataMapManager.types()) {
            if (type.isSynced()) {
                maps.add(MapData.of(type));
            }
        }
        return new DataMapSyncPacket(maps);
    }

    public void write(RegistryFriendlyByteBuf buf) {
        buf.writeVarInt(this.maps.size());
        for (MapData<?, ?> map : this.maps) {
            map.write(buf);
        }
    }

    public static DataMapSyncPacket decode(RegistryFriendlyByteBuf buf) {
        int count = buf.readVarInt();
        List<MapData<?, ?>> maps = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Identifier id = buf.readIdentifier();
            Identifier registry = buf.readIdentifier();
            DataMapType<?, ?> type = find(id, registry);
            int entries = buf.readVarInt();
            if (type == null) {
                throw new IllegalStateException("Received unknown data map " + id + " for registry " + registry);
            }
            maps.add(MapData.read(type, buf, entries));
        }
        return new DataMapSyncPacket(maps);
    }

    private static DataMapType<?, ?> find(Identifier id, Identifier registry) {
        for (DataMapType<?, ?> type : DataMapManager.types()) {
            if (type.id().equals(id) && type.registryKey().identifier().equals(registry)) {
                return type;
            }
        }
        return null;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(DataMapSyncPacket packet, Player player) {
        for (MapData<?, ?> map : packet.maps) {
            map.apply();
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public record MapData<R, T>(DataMapType<R, T> type, List<DataMapType.Entry<R, T>> entries) {
        static <R, T> MapData<R, T> of(DataMapType<R, T> type) {
            return new MapData<>(type, type.entries());
        }

        static <R, T> MapData<R, T> read(DataMapType<R, T> type, RegistryFriendlyByteBuf buf, int count) {
            StreamCodec<RegistryFriendlyByteBuf, T> valueCodec = ByteBufCodecs.fromCodecWithRegistries(codec(type));
            List<DataMapType.Entry<R, T>> entries = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                Identifier key = buf.readIdentifier();
                boolean tag = buf.readBoolean();
                T value = valueCodec.decode(buf);
                entries.add(new DataMapType.Entry<>(key, tag, value));
            }
            return new MapData<>(type, entries);
        }

        void write(RegistryFriendlyByteBuf buf) {
            StreamCodec<RegistryFriendlyByteBuf, T> valueCodec = ByteBufCodecs.fromCodecWithRegistries(codec(this.type));
            buf.writeIdentifier(this.type.id());
            buf.writeIdentifier(this.type.registryKey().identifier());
            buf.writeVarInt(this.entries.size());
            for (DataMapType.Entry<R, T> entry : this.entries) {
                buf.writeIdentifier(entry.key());
                buf.writeBoolean(entry.tag());
                valueCodec.encode(buf, entry.value());
            }
        }

        void apply() {
            this.type.setFromEntries(this.entries);
        }

        private static <T> Codec<T> codec(DataMapType<?, T> type) {
            Codec<T> network = type.networkCodec();
            return network != null ? network : type.codec();
        }
    }
}
