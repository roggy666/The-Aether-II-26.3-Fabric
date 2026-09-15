package com.aetherteam.aetherii.item;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.Nullable;

public interface CustomEnchantmentItem {
    default boolean supportsEnchantment(ItemStack stack, @Nullable Holder<Enchantment> enchantment) {
        return false;
    }
}
