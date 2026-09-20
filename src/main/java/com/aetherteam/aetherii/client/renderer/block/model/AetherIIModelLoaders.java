package com.aetherteam.aetherii.client.renderer.block.model;

import com.aetherteam.aetherii.AetherII;
import com.google.gson.*;
import net.fabricmc.fabric.api.client.model.loading.v1.*;
import net.fabricmc.fabric.api.client.model.loading.v1.wrapper.WrapperUnbakedModel;
import net.minecraft.client.resources.model.*;
import net.minecraft.client.resources.model.cuboid.CuboidModel;
import net.minecraft.client.resources.model.geometry.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public final class AetherIIModelLoaders {
    public static final ThreadLocal<Boolean> BAKING_ITEM = ThreadLocal.withInitial(() -> false);
    private static final Identifier COMPOSITE = Identifier.fromNamespaceAndPath(AetherII.MODID, "composite");
    private static final Identifier FACE_DATA = Identifier.fromNamespaceAndPath(AetherII.MODID, "face_data");

    public static void register() {
        UnbakedModelDeserializer.register(COMPOSITE, (json, context) -> new Composite(json));
        UnbakedModelDeserializer.register(FACE_DATA, (json, context) -> new FaceData(json));
        PreparableModelLoadingPlugin.<Set<Identifier>>register((reload, executor) -> CompletableFuture.supplyAsync(() -> {
            Set<Identifier> children = new HashSet<>();
            reload.resourceManager().listResources("models", id -> id.getPath().endsWith(".json")).forEach((id, resource) -> {
                try (var reader = resource.openAsReader()) {
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    if (json.has("fabric:type") && json.get("fabric:type").getAsString().equals(COMPOSITE.toString())) {
                        json.getAsJsonObject("children").entrySet().forEach(entry -> children.add(Identifier.parse(entry.getValue().getAsString())));
                    }
                } catch (Exception exception) {
                    throw new IllegalArgumentException("Cannot read model " + id, exception);
                }
            });
            return children;
        }, executor), (children, plugin) -> children.forEach(id -> plugin.addModel(ExtraModelKey.create(() -> id.toString()), SimpleUnbakedExtraModel.blockStateModel(id))));
    }

    private static CuboidModel vanilla(JsonObject json) {
        JsonObject copy = json.deepCopy();
        copy.remove("fabric:type");
        return CuboidModel.fromStream(new StringReader(copy.toString()));
    }

    private static final class Composite extends WrapperUnbakedModel {
        private final LinkedHashMap<String, Identifier> children = new LinkedHashMap<>();
        private final List<String> itemOrder = new ArrayList<>();
        private Composite(JsonObject json) {
            super(vanilla(json));
            json.getAsJsonObject("children").entrySet().forEach(entry -> this.children.put(entry.getKey(), Identifier.parse(entry.getValue().getAsString())));
            if (json.has("item_render_order")) json.getAsJsonArray("item_render_order").forEach(value -> this.itemOrder.add(value.getAsString()));
            if (this.itemOrder.isEmpty()) this.itemOrder.addAll(this.children.keySet());
            for (String name : this.itemOrder) if (!this.children.containsKey(name)) throw new JsonParseException("Unknown composite child " + name);
        }
        @Override public UnbakedGeometry geometry() {
            return (textures, baker, state, debugName) -> {
                QuadCollection.Builder result = new QuadCollection.Builder();
                Collection<String> order = BAKING_ITEM.get() ? this.itemOrder : this.children.keySet();
                for (String child : order) {
                    ResolvedModel model = baker.getModel(this.children.get(child));
                    result.addAll(model.bakeTopGeometry(model.getTopTextureSlots(), baker, state));
                }
                return result.build();
            };
        }
    }

    private static final class FaceData extends WrapperUnbakedModel {
        private final Map<String, Integer> light = new LinkedHashMap<>();
        private FaceData(JsonObject json) {
            super(vanilla(json));
            json.getAsJsonObject("face_data").entrySet().forEach(entry -> {
                JsonObject data = entry.getValue().getAsJsonObject();
                int emission = data.has("light_emission") ? data.get("light_emission").getAsInt() : 0;
                if (emission < 0 || emission > 15) throw new JsonParseException("Invalid light emission " + emission);
                this.light.put(entry.getKey(), emission);
            });
        }
        @Override public UnbakedGeometry geometry() {
            return (textures, baker, state, debugName) -> {
                UnbakedGeometry base = this.wrapped.geometry();
                if (base == null && this.parent() != null) base = baker.getModel(this.parent()).getTopGeometry();
                if (base == null) return QuadCollection.EMPTY;
                QuadCollection quads = base.bake(textures, baker, state, debugName);
                Map<Object, Integer> sprites = new IdentityHashMap<>();
                this.light.forEach((slot, emission) -> {
                    var material = textures.getMaterial(slot);
                    if (material != null) sprites.put(baker.materials().get(material, debugName).sprite(), emission);
                });
                QuadCollection.Builder result = new QuadCollection.Builder();
                for (int side = 0; side <= 6; side++) {
                    Direction direction = side == 6 ? null : Direction.values()[side];
                    for (BakedQuad quad : quads.getQuads(direction)) {
                        Integer emission = sprites.get(quad.materialInfo().sprite());
                        if (emission != null) {
                            var old = quad.materialInfo();
                            var material = new BakedQuad.MaterialInfo(old.sprite(), old.layer(), old.itemRenderType(), old.itemGlintRenderType(), old.itemGlintSpecialRenderType(), old.tintIndex(), old.shadeDirectionOverride(), emission);
                            quad = new BakedQuad(quad.position0(), quad.position1(), quad.position2(), quad.position3(), quad.packedUV0(), quad.packedUV1(), quad.packedUV2(), quad.packedUV3(), quad.direction(), material);
                        }
                        if (direction == null) result.addUnculledFace(quad); else result.addCulledFace(direction, quad);
                    }
                }
                return result.build();
            };
        }
    }
}
