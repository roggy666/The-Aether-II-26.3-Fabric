package com.aetherteam.aetherii.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * NeoForge's {@code Item#onStopUsing}: called whenever an entity stops using the item, whether it was released or
 * interrupted. Hooked in {@link com.aetherteam.aetherii.mixin.mixins.common.LivingEntityMixin}.
 */
public interface StopUsingItem {
    void onStopUsing(ItemStack stack, LivingEntity entity, int count);
}
