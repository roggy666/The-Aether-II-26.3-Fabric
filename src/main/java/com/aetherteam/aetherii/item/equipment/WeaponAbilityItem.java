package com.aetherteam.aetherii.item.equipment;

import net.minecraft.world.item.ItemStack;

public interface WeaponAbilityItem {
    boolean canPerformAction(ItemStack stack, WeaponAbility ability);
}
