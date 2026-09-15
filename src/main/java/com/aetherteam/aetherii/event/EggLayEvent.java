package com.aetherteam.aetherii.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

/**
 * EggLayEvent is fired before a Moa lays an egg.
 * <br>
 * If canceled, the Moa will not lay an egg.
 */
public class EggLayEvent {
    @FunctionalInterface
    public interface Callback {
        boolean onLayEgg(EggLayEvent event);
    }

    public static final Event<Callback> EVENT = EventFactory.createArrayBacked(Callback.class, callbacks -> event -> {
        for (Callback callback : callbacks) {
            if (callback.onLayEgg(event)) {
                return true;
            }
        }
        return false;
    });

    private final Entity entity;
    @Nullable
    private ItemStack item;
    @Nullable
    private SoundEvent sound;
    private float volume;
    private float pitch;
    private boolean canceled = false;

    public EggLayEvent(Entity entity, @Nullable SoundEvent sound, float volume, float pitch, @Nullable ItemStack item) {
        this.entity = entity;
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        this.item = item;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Nullable
    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(@Nullable ItemStack item) {
        this.item = item;
    }

    @Nullable
    public SoundEvent getSound() {
        return this.sound;
    }

    public void setSound(@Nullable SoundEvent sound) {
        this.sound = sound;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
}