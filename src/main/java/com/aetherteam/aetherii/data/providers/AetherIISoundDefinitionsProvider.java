package com.aetherteam.aetherii.data.providers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Writes {@code assets/<modid>/sounds.json}; same builder shape and output as NeoForge's {@code SoundDefinitionsProvider}.
 */
public abstract class AetherIISoundDefinitionsProvider implements DataProvider {
    private final Path output;
    private final String modId;
    private final Map<String, SoundDefinition> sounds = new LinkedHashMap<>();

    protected AetherIISoundDefinitionsProvider(PackOutput output, String modId) {
        this.output = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(modId).resolve("sounds.json");
        this.modId = modId;
    }

    public abstract void registerSounds();

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        this.sounds.clear();
        this.registerSounds();
        JsonObject json = new JsonObject();
        this.sounds.forEach((name, definition) -> json.add(name, definition.serialize()));
        return DataProvider.saveStable(cache, json, this.output);
    }

    @Override
    public String getName() {
        return "Sound Definitions : " + this.modId;
    }

    protected static SoundDefinition definition() {
        return new SoundDefinition();
    }

    protected static SoundDefinition.Sound sound(Identifier name, SoundDefinition.SoundType type) {
        return new SoundDefinition.Sound(name, type);
    }

    protected static SoundDefinition.Sound sound(Identifier name) {
        return sound(name, SoundDefinition.SoundType.SOUND);
    }

    protected static SoundDefinition.Sound sound(String name, SoundDefinition.SoundType type) {
        return sound(Identifier.parse(name), type);
    }

    protected static SoundDefinition.Sound sound(String name) {
        return sound(Identifier.parse(name));
    }

    protected void add(SoundEvent soundEvent, SoundDefinition definition) {
        this.add(soundEvent.location(), definition);
    }

    protected void add(Identifier soundEvent, SoundDefinition definition) {
        this.addSounds(soundEvent.getPath(), definition);
    }

    protected void add(String soundEvent, SoundDefinition definition) {
        this.add(Identifier.fromNamespaceAndPath(this.modId, soundEvent), definition);
    }

    private void addSounds(String name, SoundDefinition definition) {
        if (this.sounds.put(name, definition) != null) {
            throw new IllegalStateException("Sound event " + this.modId + ":" + name + " already exists");
        }
    }

    public static final class SoundDefinition {
        private final List<Sound> sounds = new ArrayList<>();
        private boolean replace = false;
        @Nullable
        private String subtitle = null;

        private SoundDefinition() {
        }

        public SoundDefinition replace(boolean replace) {
            this.replace = replace;
            return this;
        }

        public SoundDefinition subtitle(@Nullable String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public SoundDefinition with(Sound sound) {
            this.sounds.add(sound);
            return this;
        }

        public SoundDefinition with(Sound... sounds) {
            this.sounds.addAll(List.of(sounds));
            return this;
        }

        JsonObject serialize() {
            JsonObject json = new JsonObject();
            if (this.replace) {
                json.addProperty("replace", true);
            }
            if (this.subtitle != null) {
                json.addProperty("subtitle", this.subtitle);
            }
            JsonArray array = new JsonArray();
            this.sounds.forEach(sound -> array.add(sound.serialize()));
            json.add("sounds", array);
            return json;
        }

        public enum SoundType {
            SOUND("sound"),
            EVENT("event");

            private final String jsonString;

            SoundType(String jsonString) {
                this.jsonString = jsonString;
            }
        }

        public static final class Sound {
            private static final float DEFAULT_VOLUME = 1.0F;
            private static final float DEFAULT_PITCH = 1.0F;
            private static final int DEFAULT_WEIGHT = 1;
            private static final boolean DEFAULT_STREAM = false;
            private static final int DEFAULT_ATTENUATION_DISTANCE = 16;
            private static final boolean DEFAULT_PRELOAD = false;

            private final Identifier name;
            private final SoundType type;
            private float volume = DEFAULT_VOLUME;
            private float pitch = DEFAULT_PITCH;
            private int weight = DEFAULT_WEIGHT;
            private boolean stream = DEFAULT_STREAM;
            private int attenuationDistance = DEFAULT_ATTENUATION_DISTANCE;
            private boolean preload = DEFAULT_PRELOAD;

            private Sound(Identifier name, SoundType type) {
                this.name = name;
                this.type = type;
            }

            public Sound volume(double volume) {
                return this.volume((float) volume);
            }

            public Sound volume(float volume) {
                if (volume <= 0) {
                    throw new IllegalArgumentException("Volume must be positive for sound " + this.name);
                }
                this.volume = volume;
                return this;
            }

            public Sound pitch(double pitch) {
                return this.pitch((float) pitch);
            }

            public Sound pitch(float pitch) {
                if (pitch <= 0) {
                    throw new IllegalArgumentException("Pitch must be positive for sound " + this.name);
                }
                this.pitch = pitch;
                return this;
            }

            public Sound weight(int weight) {
                if (weight <= 0) {
                    throw new IllegalArgumentException("Weight must be positive for sound " + this.name);
                }
                this.weight = weight;
                return this;
            }

            public Sound stream() {
                return this.stream(true);
            }

            public Sound stream(boolean stream) {
                this.stream = stream;
                return this;
            }

            public Sound attenuationDistance(int attenuationDistance) {
                if (attenuationDistance <= 0) {
                    throw new IllegalArgumentException("Attenuation distance must be positive for sound " + this.name);
                }
                this.attenuationDistance = attenuationDistance;
                return this;
            }

            public Sound preload() {
                return this.preload(true);
            }

            public Sound preload(boolean preload) {
                this.preload = preload;
                return this;
            }

            JsonElement serialize() {
                if (this.canBePlainString()) {
                    return new JsonPrimitive(this.name.toString());
                }
                JsonObject json = new JsonObject();
                json.addProperty("name", this.name.toString());
                if (this.type != SoundType.SOUND) {
                    json.addProperty("type", this.type.jsonString);
                }
                if (this.volume != DEFAULT_VOLUME) {
                    json.addProperty("volume", this.volume);
                }
                if (this.pitch != DEFAULT_PITCH) {
                    json.addProperty("pitch", this.pitch);
                }
                if (this.weight != DEFAULT_WEIGHT) {
                    json.addProperty("weight", this.weight);
                }
                if (this.stream != DEFAULT_STREAM) {
                    json.addProperty("stream", this.stream);
                }
                if (this.attenuationDistance != DEFAULT_ATTENUATION_DISTANCE) {
                    json.addProperty("attenuation_distance", this.attenuationDistance);
                }
                if (this.preload != DEFAULT_PRELOAD) {
                    json.addProperty("preload", this.preload);
                }
                return json;
            }

            private boolean canBePlainString() {
                return this.type == SoundType.SOUND && this.volume == DEFAULT_VOLUME && this.pitch == DEFAULT_PITCH && this.weight == DEFAULT_WEIGHT
                        && this.stream == DEFAULT_STREAM && this.attenuationDistance == DEFAULT_ATTENUATION_DISTANCE && this.preload == DEFAULT_PRELOAD;
            }
        }
    }
}
