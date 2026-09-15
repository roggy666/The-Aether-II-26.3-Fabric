package com.aetherteam.aetherii.data.providers;

import net.minecraft.core.Registry;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

/**
 * Wraps the vanilla {@link TagAppender} (which only takes {@link ResourceKey}s in 26.2) so tag data can keep adding
 * registry objects directly, like NeoForge's {@code TagAppender<T, E>} allowed.
 */
public class AetherTagAppender<T> {
    private final Sink<T> sink;
    private final Registry<T> registry;

    public AetherTagAppender(TagAppender<T> appender, Registry<T> registry) {
        this(Sink.of(appender), registry);
    }

    public AetherTagAppender(Sink<T> sink, Registry<T> registry) {
        this.sink = sink;
        this.registry = registry;
    }

    @SafeVarargs
    public final AetherTagAppender<T> add(T... elements) {
        for (T element : elements) {
            this.sink.add(this.registry.getResourceKey(element).orElseThrow(() -> new IllegalArgumentException("Unregistered tag element " + element)));
        }
        return this;
    }

    @SafeVarargs
    public final AetherTagAppender<T> addKeys(ResourceKey<T>... keys) {
        for (ResourceKey<T> key : keys) {
            this.sink.add(key);
        }
        return this;
    }

    public AetherTagAppender<T> addOptional(ResourceKey<T> key) {
        this.sink.addOptional(key);
        return this;
    }

    public AetherTagAppender<T> addTag(TagKey<T> tag) {
        this.sink.addTag(tag);
        return this;
    }

    @SafeVarargs
    public final AetherTagAppender<T> addTags(TagKey<T>... tags) {
        for (TagKey<T> tag : tags) {
            this.sink.addTag(tag);
        }
        return this;
    }

    public AetherTagAppender<T> addOptionalTag(TagKey<T> tag) {
        this.sink.addOptionalTag(tag);
        return this;
    }

    /**
     * The four operations a tag entry needs; lets one appender forward into another registry's tag
     * (block tags mirrored into item tags).
     */
    public interface Sink<T> {
        void add(ResourceKey<T> key);

        void addOptional(ResourceKey<T> key);

        void addTag(TagKey<T> tag);

        void addOptionalTag(TagKey<T> tag);

        static <T> Sink<T> of(TagAppender<T> appender) {
            return new Sink<>() {
                @Override
                public void add(ResourceKey<T> key) {
                    appender.add(key);
                }

                @Override
                public void addOptional(ResourceKey<T> key) {
                    appender.addOptional(key);
                }

                @Override
                public void addTag(TagKey<T> tag) {
                    appender.addTag(tag);
                }

                @Override
                public void addOptionalTag(TagKey<T> tag) {
                    appender.addOptionalTag(tag);
                }
            };
        }
    }
}
