package com.aetherteam.aetherii.data.resources.builders.models;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.math.Quadrant;
import com.mojang.serialization.JsonOps;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A {@link ModelTemplate} that can carry the rest of the block model format: elements, display transforms, ambient
 * occlusion, GUI light and a custom loader. Replaces NeoForge's {@code ExtendedModelTemplate}/{@code ExtendedModelTemplateBuilder}
 * and writes the same JSON (custom loaders use Fabric's {@code fabric:type} dispatch instead of NeoForge's {@code loader}).
 */
public class ExtendedModelTemplate extends ModelTemplate {
    private final Optional<Identifier> model;
    private final Optional<String> suffix;
    private final Set<TextureSlot> requiredSlots;
    private final List<ElementBuilder> elements;
    private final Map<ItemDisplayContext, TransformVecBuilder> transforms;
    @Nullable
    private final Boolean ambientOcclusion;
    @Nullable
    private final GuiLight guiLight;
    @Nullable
    private final CustomLoaderBuilder customLoader;
    private final Map<String, ExtraFaceData> itemLayerFaceData;

    public ExtendedModelTemplate(Optional<Identifier> model, Optional<String> suffix, TextureSlot... requiredSlots) {
        this(model, suffix, ImmutableSet.copyOf(requiredSlots), List.of(), Map.of(), null, null, null, Map.of());
    }

    private ExtendedModelTemplate(Optional<Identifier> model, Optional<String> suffix, Set<TextureSlot> requiredSlots, List<ElementBuilder> elements, Map<ItemDisplayContext, TransformVecBuilder> transforms, @Nullable Boolean ambientOcclusion, @Nullable GuiLight guiLight, @Nullable CustomLoaderBuilder customLoader, Map<String, ExtraFaceData> itemLayerFaceData) {
        super(model, suffix, requiredSlots.toArray(TextureSlot[]::new));
        this.model = model;
        this.suffix = suffix;
        this.requiredSlots = requiredSlots;
        this.elements = List.copyOf(elements);
        this.transforms = new LinkedHashMap<>(transforms);
        this.ambientOcclusion = ambientOcclusion;
        this.guiLight = guiLight;
        this.customLoader = customLoader;
        this.itemLayerFaceData = new LinkedHashMap<>(itemLayerFaceData);
    }

    public static ExtendedModelTemplate of(ModelTemplate template) {
        if (template instanceof ExtendedModelTemplate extended) {
            return extended;
        }
        throw new IllegalArgumentException("Only templates created through AetherIIModelTemplates can be extended: " + template);
    }

    /**
     * Starts a builder pre-filled with this template's parent, suffix, texture slots and extensions.
     */
    public Builder extend() {
        return new Builder(this);
    }

    @Override
    public Identifier create(Identifier target, TextureMapping textures, BiConsumer<Identifier, ModelInstance> output) {
        Map<TextureSlot, Material> slots = new LinkedHashMap<>();
        Stream.concat(this.requiredSlots.stream(), textures.getForced()).distinct().forEach(slot -> slots.put(slot, textures.get(slot)));
        output.accept(target, () -> {
            JsonObject result = new JsonObject();
            this.model.ifPresent(m -> result.addProperty("parent", m.toString()));
            if (this.customLoader != null) {
                this.customLoader.toJson(result);
            }
            if (this.ambientOcclusion != null) {
                result.addProperty("ambientocclusion", this.ambientOcclusion);
            }
            if (this.guiLight != null) {
                result.addProperty("gui_light", this.guiLight.serializedName);
            }
            if (!this.transforms.isEmpty()) {
                JsonObject display = new JsonObject();
                this.transforms.forEach((context, transform) -> display.add(context.getSerializedName(), transform.toJson()));
                result.add("display", display);
            }
            if (!slots.isEmpty()) {
                JsonObject textureObj = new JsonObject();
                slots.forEach((slot, value) -> textureObj.add(slot.getId(), Material.CODEC.encodeStart(JsonOps.INSTANCE, value).getOrThrow()));
                result.add("textures", textureObj);
            }
            if (!this.elements.isEmpty()) {
                JsonArray elements = new JsonArray();
                this.elements.forEach(element -> elements.add(element.toJson()));
                result.add("elements", elements);
            }
            if (!this.itemLayerFaceData.isEmpty()) {
                result.addProperty("fabric:type", AetherIIModelTemplates.FACE_DATA_LOADER.toString());
                JsonObject faceData = new JsonObject();
                this.itemLayerFaceData.forEach((layer, data) -> faceData.add(layer, data.toJson()));
                result.add("face_data", faceData);
            }
            return result;
        });
        return target;
    }

    public enum GuiLight {
        FRONT("front"),
        SIDE("side");

        private final String serializedName;

        GuiLight(String serializedName) {
            this.serializedName = serializedName;
        }
    }

    public static class Builder {
        private final Optional<Identifier> model;
        private final Optional<String> suffix;
        private final Set<TextureSlot> requiredSlots;
        private final List<ElementBuilder> elements;
        private final Map<ItemDisplayContext, TransformVecBuilder> transforms;
        @Nullable
        private Boolean ambientOcclusion;
        @Nullable
        private GuiLight guiLight;
        @Nullable
        private CustomLoaderBuilder customLoader;
        private final Map<String, ExtraFaceData> itemLayerFaceData;

        private Builder(ExtendedModelTemplate template) {
            this.model = template.model;
            this.suffix = template.suffix;
            this.requiredSlots = template.requiredSlots;
            this.elements = new ArrayList<>(template.elements);
            this.transforms = new LinkedHashMap<>(template.transforms);
            this.ambientOcclusion = template.ambientOcclusion;
            this.guiLight = template.guiLight;
            this.customLoader = template.customLoader;
            this.itemLayerFaceData = new LinkedHashMap<>(template.itemLayerFaceData);
        }

        public Builder element(Consumer<ElementBuilder> action) {
            ElementBuilder element = new ElementBuilder();
            action.accept(element);
            this.elements.add(element);
            return this;
        }

        public Builder transform(ItemDisplayContext context, Consumer<TransformVecBuilder> action) {
            TransformVecBuilder transform = this.transforms.computeIfAbsent(context, k -> new TransformVecBuilder());
            action.accept(transform);
            return this;
        }

        public Builder ambientOcclusion(boolean ambientOcclusion) {
            this.ambientOcclusion = ambientOcclusion;
            return this;
        }

        public Builder guiLight(GuiLight guiLight) {
            this.guiLight = guiLight;
            return this;
        }

        public <L extends CustomLoaderBuilder> Builder customLoader(Supplier<L> factory, Consumer<L> action) {
            L loader = factory.get();
            action.accept(loader);
            this.customLoader = loader;
            return this;
        }

        /**
         * Per-texture-layer face data for flat item models (NeoForge's {@code itemLayerFaceData}); needs the
         * {@code aether_ii:face_data} model deserializer on the client.
         */
        public Builder itemLayerFaceData(String layer, ExtraFaceData data) {
            this.itemLayerFaceData.put(layer, data);
            return this;
        }

        public ExtendedModelTemplate build() {
            return new ExtendedModelTemplate(this.model, this.suffix, this.requiredSlots, this.elements, this.transforms, this.ambientOcclusion, this.guiLight, this.customLoader, this.itemLayerFaceData);
        }
    }

    public static class ElementBuilder {
        private final Vector3f from = new Vector3f();
        private final Vector3f to = new Vector3f(16.0F, 16.0F, 16.0F);
        private final Map<Direction, FaceBuilder> faces = new LinkedHashMap<>();
        @Nullable
        private RotationBuilder rotation;
        private boolean shade = true;
        private int lightEmission = 0;

        public ElementBuilder from(float x, float y, float z) {
            this.from.set(x, y, z);
            return this;
        }

        public ElementBuilder to(float x, float y, float z) {
            this.to.set(x, y, z);
            return this;
        }

        public ElementBuilder face(Direction direction, Consumer<FaceBuilder> action) {
            FaceBuilder face = this.faces.computeIfAbsent(direction, k -> new FaceBuilder());
            action.accept(face);
            return this;
        }

        public ElementBuilder allFaces(BiConsumer<Direction, FaceBuilder> action) {
            for (Direction direction : Direction.values()) {
                this.face(direction, face -> action.accept(direction, face));
            }
            return this;
        }

        public ElementBuilder rotation(Consumer<RotationBuilder> action) {
            if (this.rotation == null) {
                this.rotation = new RotationBuilder();
            }
            action.accept(this.rotation);
            return this;
        }

        public ElementBuilder shade(boolean shade) {
            this.shade = shade;
            return this;
        }

        public ElementBuilder lightEmission(int lightEmission) {
            this.lightEmission = lightEmission;
            return this;
        }

        JsonObject toJson() {
            JsonObject json = new JsonObject();
            json.add("from", vector(this.from));
            json.add("to", vector(this.to));
            if (this.rotation != null) {
                json.add("rotation", this.rotation.toJson());
            }
            if (!this.shade) {
                json.addProperty("shade", false);
            }
            if (this.lightEmission != 0) {
                json.addProperty("light_emission", this.lightEmission);
            }
            JsonObject faces = new JsonObject();
            this.faces.forEach((direction, face) -> faces.add(direction.getSerializedName(), face.toJson()));
            json.add("faces", faces);
            return json;
        }
    }

    public static class RotationBuilder {
        private final Vector3f origin = new Vector3f();
        private Direction.Axis axis = Direction.Axis.Y;
        private float angle = 0.0F;
        private boolean rescale = false;

        public RotationBuilder origin(float x, float y, float z) {
            this.origin.set(x, y, z);
            return this;
        }

        public RotationBuilder singleAxis(Direction.Axis axis, float angle) {
            this.axis = axis;
            this.angle = angle;
            return this;
        }

        public RotationBuilder rescale(boolean rescale) {
            this.rescale = rescale;
            return this;
        }

        JsonObject toJson() {
            JsonObject json = new JsonObject();
            json.add("origin", vector(this.origin));
            json.addProperty("axis", this.axis.getSerializedName());
            json.addProperty("angle", this.angle);
            if (this.rescale) {
                json.addProperty("rescale", true);
            }
            return json;
        }
    }

    public static class FaceBuilder {
        @Nullable
        private Direction cullface;
        private int tintindex = -1;
        @Nullable
        private TextureSlot texture;
        private float @Nullable [] uvs;
        private Quadrant rotation = Quadrant.R0;
        @Nullable
        private ExtraFaceData faceData;

        public FaceBuilder cullface(@Nullable Direction direction) {
            this.cullface = direction;
            return this;
        }

        public FaceBuilder tintindex(int tintindex) {
            this.tintindex = tintindex;
            return this;
        }

        public FaceBuilder texture(TextureSlot texture) {
            this.texture = texture;
            return this;
        }

        public FaceBuilder uvs(float u1, float v1, float u2, float v2) {
            this.uvs = new float[]{u1, v1, u2, v2};
            return this;
        }

        public FaceBuilder rotation(Quadrant rotation) {
            this.rotation = rotation;
            return this;
        }

        public FaceBuilder faceData(ExtraFaceData faceData) {
            this.faceData = faceData;
            return this;
        }

        JsonObject toJson() {
            if (this.texture == null) {
                throw new IllegalStateException("A model face must have a texture");
            }
            JsonObject json = new JsonObject();
            if (this.uvs != null) {
                JsonArray uv = new JsonArray();
                for (float value : this.uvs) {
                    uv.add(value);
                }
                json.add("uv", uv);
            }
            json.addProperty("texture", "#" + this.texture.getId());
            if (this.cullface != null) {
                json.addProperty("cullface", this.cullface.getSerializedName());
            }
            if (this.rotation != Quadrant.R0) {
                json.addProperty("rotation", this.rotation.shift * 90);
            }
            if (this.tintindex != -1) {
                json.addProperty("tintindex", this.tintindex);
            }
            if (this.faceData != null) {
                json.add("face_data", this.faceData.toJson());
            }
            return json;
        }
    }

    public static class TransformVecBuilder {
        private final Vector3f rotation = new Vector3f();
        private final Vector3f translation = new Vector3f();
        private final Vector3f scale = new Vector3f(1.0F, 1.0F, 1.0F);

        public TransformVecBuilder rotation(float x, float y, float z) {
            this.rotation.set(x, y, z);
            return this;
        }

        public TransformVecBuilder translation(float x, float y, float z) {
            this.translation.set(x, y, z);
            return this;
        }

        public TransformVecBuilder scale(float scale) {
            return this.scale(scale, scale, scale);
        }

        public TransformVecBuilder scale(float x, float y, float z) {
            this.scale.set(x, y, z);
            return this;
        }

        JsonObject toJson() {
            JsonObject json = new JsonObject();
            if (!this.rotation.equals(0.0F, 0.0F, 0.0F)) {
                json.add("rotation", vector(this.rotation));
            }
            if (!this.translation.equals(0.0F, 0.0F, 0.0F)) {
                json.add("translation", vector(this.translation));
            }
            if (!this.scale.equals(1.0F, 1.0F, 1.0F)) {
                json.add("scale", vector(this.scale));
            }
            return json;
        }
    }

    /**
     * Extra lighting data for a face or an item texture layer (NeoForge's {@code ExtraFaceData}).
     */
    public record ExtraFaceData(int color, int blockLight, int skyLight, boolean ambientOcclusion) {
        public ExtraFaceData(int color, int lightEmission, boolean ambientOcclusion) {
            this(color, lightEmission, lightEmission, ambientOcclusion);
        }

        JsonObject toJson() {
            JsonObject json = new JsonObject();
            if (this.color != -1) {
                json.addProperty("color", this.color);
            }
            if (this.blockLight == this.skyLight) {
                if (this.blockLight != 0) {
                    json.addProperty("light_emission", this.blockLight);
                }
            } else {
                json.addProperty("block_light", this.blockLight);
                json.addProperty("sky_light", this.skyLight);
            }
            if (!this.ambientOcclusion) {
                json.addProperty("ambient_occlusion", false);
            }
            return json;
        }
    }

    /**
     * Writes the extra keys a custom model loader reads. Loaders are picked on the client through Fabric's
     * {@code fabric:type} key.
     */
    public abstract static class CustomLoaderBuilder {
        protected final Identifier loaderId;

        protected CustomLoaderBuilder(Identifier loaderId) {
            this.loaderId = loaderId;
        }

        protected void toJson(JsonObject json) {
            json.addProperty("fabric:type", this.loaderId.toString());
        }
    }

    /**
     * A model made of several child models, rendered together (NeoForge's {@code neoforge:composite}). Read on the
     * client by the {@code aether_ii:composite} model deserializer.
     */
    public static class CompositeModelBuilder extends CustomLoaderBuilder {
        private final Map<String, Identifier> children = new LinkedHashMap<>();
        private final List<String> itemRenderOrder = new ArrayList<>();

        public CompositeModelBuilder() {
            super(AetherIIModelTemplates.COMPOSITE_LOADER);
        }

        public CompositeModelBuilder child(String name, Identifier model) {
            this.children.put(name, model);
            this.itemRenderOrder.add(name);
            return this;
        }

        public CompositeModelBuilder itemRenderOrder(String... names) {
            this.itemRenderOrder.clear();
            this.itemRenderOrder.addAll(List.of(names));
            return this;
        }

        @Override
        protected void toJson(JsonObject json) {
            super.toJson(json);
            JsonObject children = new JsonObject();
            this.children.forEach((name, model) -> children.addProperty(name, model.toString()));
            json.add("children", children);
            JsonArray order = new JsonArray();
            this.itemRenderOrder.forEach(order::add);
            json.add("item_render_order", order);
        }
    }

    private static JsonElement vector(Vector3f vector) {
        JsonArray array = new JsonArray();
        array.add(number(vector.x()));
        array.add(number(vector.y()));
        array.add(number(vector.z()));
        return array;
    }

    private static Number number(float value) {
        return value == (int) value ? (Number) (int) value : (Number) value;
    }
}
