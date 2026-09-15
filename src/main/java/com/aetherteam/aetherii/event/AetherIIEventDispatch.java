package com.aetherteam.aetherii.event;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class AetherIIEventDispatch {
    /**
     * @see EggLayEvent
     */
    public static EggLayEvent onLayEgg(Entity entity, SoundEvent sound, float volume, float pitch, ItemStack item) {
        EggLayEvent event = new EggLayEvent(entity, sound, volume, pitch, item);
        if (EggLayEvent.EVENT.invoker().onLayEgg(event)) {
            event.setCanceled(true);
        }
        return event;
    }
}
