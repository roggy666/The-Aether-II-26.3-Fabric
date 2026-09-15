package com.aetherteam.aetherii.api.datamap;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Registry-attached data loaded from {@code data/<namespace>/data_maps/<registry>/<name>.json}, the same file
 * layout as NeoForge data maps so existing datapacks keep working. Loading is done by {@link DataMapManager};
 * synced maps are sent to clients with {@link com.aetherteam.aetherii.network.packet.clientbound.DataMapSyncPacket}.
 */
public final class DataMapType<R, T> {
    private final Identifier id;
    private final ResourceKey<Registry<R>> registryKey;
    private final Codec<T> codec;
    @Nullable
    private final Codec<T> networkCodec;

    private volatile Map<ResourceKey<R>, T> values = Map.of();
    private volatile Map<TagKey<R>, T> tagValues = Map.of();

    private DataMapType(Identifier id, ResourceKey<Registry<R>> registryKey, Codec<T> codec, @Nullable Codec<T> networkCodec) {
        this.id = id;
        this.registryKey = registryKey;
        this.codec = codec;
        this.networkCodec = networkCodec;
    }

    public static <R, T> Builder<R, T> builder(Identifier id, ResourceKey<Registry<R>> registryKey, Codec<T> codec) {
        return new Builder<>(id, registryKey, codec);
    }

    public Identifier id() {
        return this.id;
    }

    public ResourceKey<Registry<R>> registryKey() {
        return this.registryKey;
    }

    public Codec<T> codec() {
        return this.codec;
    }

    @Nullable
    public Codec<T> networkCodec() {
        return this.networkCodec;
    }

    public boolean isSynced() {
        return this.networkCodec != null;
    }

    /**
     * @return The value attached to the holder, checking direct entries first and then tag entries.
     */
    @Nullable
    public T get(Holder<R> holder) {
        T direct = holder.unwrapKey().map(this.values::get).orElse(null);
        if (direct != null) {
            return direct;
        }
        for (Map.Entry<TagKey<R>, T> entry : this.tagValues.entrySet()) {
            if (holder.is(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Nullable
    public T get(ResourceKey<R> key) {
        return this.values.get(key);
    }

    /**
     * @return Every entry of this map with tag entries expanded through the given registry. Direct entries win over tag entries.
     */
    public Map<ResourceKey<R>, T> getAll(Registry<R> registry) {
        Map<ResourceKey<R>, T> result = new LinkedHashMap<>();
        for (Map.Entry<TagKey<R>, T> entry : this.tagValues.entrySet()) {
            for (Holder<R> holder : registry.getTagOrEmpty(entry.getKey())) {
                holder.unwrapKey().ifPresent(key -> result.put(key, entry.getValue()));
            }
        }
        result.putAll(this.values);
        return Collections.unmodifiableMap(result);
    }

    public Map<ResourceKey<R>, T> directValues() {
        return this.values;
    }

    public Map<TagKey<R>, T> tagValues() {
        return this.tagValues;
    }

    void set(Map<ResourceKey<R>, T> values, Map<TagKey<R>, T> tagValues) {
        this.values = Collections.unmodifiableMap(new LinkedHashMap<>(values));
        this.tagValues = Collections.unmodifiableMap(new LinkedHashMap<>(tagValues));
    }

    /**
     * Flattened entries (used for network sync).
     */
    public List<Entry<R, T>> entries() {
        List<Entry<R, T>> list = new ArrayList<>();
        this.values.forEach((key, value) -> list.add(new Entry<>(key.identifier(), false, value)));
        this.tagValues.forEach((tag, value) -> list.add(new Entry<>(tag.location(), true, value)));
        return list;
    }

    public void setFromEntries(List<Entry<R, T>> entries) {
        Map<ResourceKey<R>, T> direct = new LinkedHashMap<>();
        Map<TagKey<R>, T> tags = new LinkedHashMap<>();
        for (Entry<R, T> entry : entries) {
            if (entry.tag()) {
                tags.put(TagKey.create(this.registryKey, entry.key()), entry.value());
            } else {
                direct.put(ResourceKey.create(this.registryKey, entry.key()), entry.value());
            }
        }
        this.set(direct, tags);
    }

    @Override
    public String toString() {
        return "DataMapType[" + this.id + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DataMapType<?, ?> other && other.id.equals(this.id) && other.registryKey.equals(this.registryKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.registryKey);
    }

    public record Entry<R, T>(Identifier key, boolean tag, T value) {
    }

    public static final class Builder<R, T> {
        private final Identifier id;
        private final ResourceKey<Registry<R>> registryKey;
        private final Codec<T> codec;
        @Nullable
        private Codec<T> networkCodec;

        private Builder(Identifier id, ResourceKey<Registry<R>> registryKey, Codec<T> codec) {
            this.id = id;
            this.registryKey = registryKey;
            this.codec = codec;
        }

        /**
         * @param networkCodec The codec used to send this map to clients.
         * @param mandatory    Kept for parity with the original API; every map is registered by this mod so it is always present.
         */
        public Builder<R, T> synced(Codec<T> networkCodec, boolean mandatory) {
            this.networkCodec = networkCodec;
            return this;
        }

        public DataMapType<R, T> build() {
            return new DataMapType<>(this.id, this.registryKey, this.codec, this.networkCodec);
        }
    }
}
