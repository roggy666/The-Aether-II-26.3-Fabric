package com.aetherteam.aetherii.data.generators;

import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.api.datamap.DataMapManager;
import com.aetherteam.aetherii.api.datamap.DataMapType;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.data.resources.maps.AmberHourglassFuel;
import com.aetherteam.aetherii.data.resources.maps.BlockInfection;
import com.aetherteam.aetherii.data.resources.maps.BucketReplacement;
import com.aetherteam.aetherii.data.resources.registries.AetherIIDataMaps;
import com.aetherteam.aetherii.data.resources.registries.holyisles.HolyIslesBiomes;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Writes the mod's {@link DataMapType} files ({@code data/aether_ii/data_maps/...}). Compostables and furnace fuels
 * live in {@link com.aetherteam.aetherii.item.AetherIIFuelsAndCompostables} on Fabric.
 */
public class AetherIIDataMapData implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> provider;
    private final Map<DataMapType<?, ?>, Builder<?, ?>> builders = new LinkedHashMap<>();

    public AetherIIDataMapData(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        this.output = output;
        this.provider = provider;
    }

    protected void gather(HolderLookup.Provider provider) {
        var amberHourglassFuels = this.builder(AetherIIDataMaps.AMBER_HOURGLASS_FUELS);
        amberHourglassFuels.add(AetherIIItems.GOLDEN_AMBER, new AmberHourglassFuel(400));
        amberHourglassFuels.add(AetherIIBlocks.GOLDEN_AMBER_BLOCK, new AmberHourglassFuel(4000));

        var buckets = this.builder(AetherIIDataMaps.BUCKET_REPLACEMENT);
        buckets.add(Items.WATER_BUCKET, new BucketReplacement(itemKey(AetherIIItems.SKYROOT_WATER_BUCKET)));
        buckets.add(Items.POWDER_SNOW_BUCKET, new BucketReplacement(itemKey(AetherIIItems.SKYROOT_POWDER_SNOW_BUCKET)));
        buckets.add(Items.COD_BUCKET, new BucketReplacement(itemKey(AetherIIItems.SKYROOT_COD_BUCKET)));
        buckets.add(Items.SALMON_BUCKET, new BucketReplacement(itemKey(AetherIIItems.SKYROOT_SALMON_BUCKET)));
        buckets.add(Items.PUFFERFISH_BUCKET, new BucketReplacement(itemKey(AetherIIItems.SKYROOT_PUFFERFISH_BUCKET)));
        buckets.add(Items.TROPICAL_FISH_BUCKET, new BucketReplacement(itemKey(AetherIIItems.SKYROOT_TROPICAL_FISH_BUCKET)));
        buckets.add(Items.AXOLOTL_BUCKET, new BucketReplacement(itemKey(AetherIIItems.SKYROOT_AXOLOTL_BUCKET)));
        buckets.add(Items.TADPOLE_BUCKET, new BucketReplacement(itemKey(AetherIIItems.SKYROOT_TADPOLE_BUCKET)));

        var blocks = this.builder(AetherIIDataMaps.INFECTED_BLOCKS);
        blocks.add(BuiltInRegistries.BLOCK.getKey(AetherIIBlocks.GUARDIAN_LOG), new BlockInfection(blockKey(AetherIIBlocks.INFECTED_LOG)));
        blocks.add(BuiltInRegistries.BLOCK.getKey(AetherIIBlocks.GUARDIAN_WOOD), new BlockInfection(blockKey(AetherIIBlocks.INFECTED_WOOD)));
        blocks.add(BuiltInRegistries.BLOCK.getKey(AetherIIBlocks.STRIPPED_GUARDIAN_LOG), new BlockInfection(blockKey(AetherIIBlocks.STRIPPED_INFECTED_LOG)));
        blocks.add(BuiltInRegistries.BLOCK.getKey(AetherIIBlocks.STRIPPED_GUARDIAN_WOOD), new BlockInfection(blockKey(AetherIIBlocks.STRIPPED_INFECTED_WOOD)));

        var colors = this.builder(AetherIIDataMaps.AETHER_GRASS_COLORS);
        colors.add(AetherIITags.Biomes.HIGHFIELDS, 0xb5ffd0);
        colors.add(AetherIITags.Biomes.MAGNETIC, 0xc9ffd1);
        colors.add(AetherIITags.Biomes.ARCTIC, 0xbdf9ff);
        colors.add(AetherIITags.Biomes.IRRADIATED, 0xffdd99);
        colors.add(HolyIslesBiomes.EXPANSE.identifier(), 0xb5ffd0);
    }

    private static ResourceKey<net.minecraft.world.item.Item> itemKey(ItemLike item) {
        return BuiltInRegistries.ITEM.getResourceKey(item.asItem()).orElseThrow();
    }

    private static ResourceKey<net.minecraft.world.level.block.Block> blockKey(net.minecraft.world.level.block.Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();
    }

    @SuppressWarnings("unchecked")
    protected <R, T> Builder<R, T> builder(DataMapType<R, T> type) {
        return (Builder<R, T>) this.builders.computeIfAbsent(type, t -> new Builder<>(type));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return this.provider.thenCompose(registries -> {
            this.gather(registries);
            RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, registries);
            List<CompletableFuture<?>> futures = new ArrayList<>();
            for (Builder<?, ?> builder : this.builders.values()) {
                Identifier location = DataMapManager.fileLocation(builder.type);
                Path path = this.output.getOutputFolder(PackOutput.Target.DATA_PACK).resolve(location.getNamespace()).resolve(location.getPath());
                futures.add(DataProvider.saveStable(cache, builder.toJson(ops), path));
            }
            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return "Data Maps";
    }

    public static final class Builder<R, T> {
        private final DataMapType<R, T> type;
        private final Map<String, T> values = new LinkedHashMap<>();

        private Builder(DataMapType<R, T> type) {
            this.type = type;
        }

        public Builder<R, T> add(Identifier key, T value) {
            this.values.put(key.toString(), value);
            return this;
        }

        public Builder<R, T> add(ItemLike item, T value) {
            return this.add(BuiltInRegistries.ITEM.getKey(item.asItem()), value);
        }

        public Builder<R, T> add(ResourceKey<R> key, T value) {
            return this.add(key.identifier(), value);
        }

        public Builder<R, T> add(TagKey<R> tag, T value) {
            this.values.put("#" + tag.location(), value);
            return this;
        }

        JsonElement toJson(RegistryOps<JsonElement> ops) {
            JsonObject values = new JsonObject();
            this.values.forEach((key, value) -> values.add(key, this.type.codec().encodeStart(ops, value).getOrThrow()));
            JsonObject root = new JsonObject();
            root.add("values", values);
            return root;
        }
    }
}
