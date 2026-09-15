package com.aetherteam.aetherii.client.animation;

import com.aetherteam.aetherii.AetherII;
import com.google.gson.*;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.client.animation.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.entity.AnimationState;
import org.joml.Vector3f;
import java.util.*;

public final class AetherIIAnimations {
    private static volatile Map<Identifier, AnimationDefinition> definitions = Map.of();
    private static final String DIRECTORY = "neoforge/animations/entity";

    public static void register() {
        ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(Identifier.fromNamespaceAndPath(AetherII.MODID, "entity_animations"), (ResourceManagerReloadListener) manager -> {
            Map<Identifier, AnimationDefinition> loaded = new HashMap<>();
            manager.listResources(DIRECTORY, id -> id.getPath().endsWith(".json")).forEach((path, resource) -> {
                try (var reader = resource.openAsReader()) {
                    String name = path.getPath().substring(DIRECTORY.length() + 1, path.getPath().length() - 5);
                    loaded.put(Identifier.fromNamespaceAndPath(path.getNamespace(), name), parse(JsonParser.parseReader(reader).getAsJsonObject()));
                } catch (Exception exception) {
                    throw new IllegalArgumentException("Invalid entity animation " + path, exception);
                }
            });
            definitions = Map.copyOf(loaded);
        });
    }

    public static AnimationDefinition parse(JsonObject json) {
        AnimationDefinition.Builder builder = AnimationDefinition.Builder.withLength(json.get("length").getAsFloat());
        if (json.has("loop") && json.get("loop").getAsBoolean()) builder.looping();
        for (JsonElement entry : json.getAsJsonArray("animations")) {
            JsonObject channel = entry.getAsJsonObject();
            String target = channel.get("target").getAsString().replace("minecraft:", "");
            AnimationChannel.Target destination = switch (target) {
                case "position" -> AnimationChannel.Targets.POSITION;
                case "rotation" -> AnimationChannel.Targets.ROTATION;
                case "scale" -> AnimationChannel.Targets.SCALE;
                default -> throw new JsonParseException("Unknown animation target " + target);
            };
            List<Keyframe> frames = new ArrayList<>();
            for (JsonElement value : channel.getAsJsonArray("keyframes")) {
                JsonObject frame = value.getAsJsonObject();
                JsonElement vector = frame.get("target");
                Vector3f pre = vector(target, vector.isJsonObject() ? vector.getAsJsonObject().get("pre") : vector);
                Vector3f post = vector(target, vector.isJsonObject() ? vector.getAsJsonObject().get("post") : vector);
                String interpolation = frame.get("interpolation").getAsString().replace("minecraft:", "");
                frames.add(new Keyframe(frame.get("timestamp").getAsFloat(), pre, post, switch (interpolation) {
                    case "linear" -> AnimationChannel.Interpolations.LINEAR;
                    case "catmullrom" -> AnimationChannel.Interpolations.CATMULLROM;
                    default -> throw new JsonParseException("Unknown interpolation " + interpolation);
                }));
            }
            frames.sort(Comparator.comparingDouble(Keyframe::timestamp));
            if (frames.isEmpty()) throw new JsonParseException("Empty animation channel");
            builder.addAnimation(channel.get("bone").getAsString(), new AnimationChannel(destination, frames.toArray(Keyframe[]::new)));
        }
        return builder.build();
    }

    private static Vector3f vector(String target, JsonElement json) {
        JsonArray vector = json.getAsJsonArray();
        if (vector.size() != 3) throw new JsonParseException("Animation vector must have three components");
        float x = vector.get(0).getAsFloat(), y = vector.get(1).getAsFloat(), z = vector.get(2).getAsFloat();
        return switch (target) {
            case "position" -> KeyframeAnimations.posVec(x, y, z);
            case "rotation" -> KeyframeAnimations.degreeVec(x, y, z);
            case "scale" -> KeyframeAnimations.scaleVec(x, y, z);
            default -> throw new JsonParseException("Unknown target " + target);
        };
    }

    public static ReloadableAnimation bake(Identifier id, ModelPart root) {
        return new ReloadableAnimation(id, root);
    }

    public static final class ReloadableAnimation {
        private final Identifier id;
        private final ModelPart root;
        private AnimationDefinition definition;
        private KeyframeAnimation baked;

        private ReloadableAnimation(Identifier id, ModelPart root) { this.id = id; this.root = root; }
        private KeyframeAnimation current() {
            AnimationDefinition next = Objects.requireNonNull(definitions.get(this.id), "Missing animation " + this.id);
            if (next != this.definition) { this.baked = next.bake(this.root); this.definition = next; }
            return this.baked;
        }
        public void apply(AnimationState state, float time) { current().apply(state, time); }
        public void apply(AnimationState state, float time, float speed) { current().apply(state, time, speed); }
        public void applyWalk(float position, float speed, float factor, float scale) { current().applyWalk(position, speed, factor, scale); }
        public void applyStatic() { current().applyStatic(); }
    }
}
