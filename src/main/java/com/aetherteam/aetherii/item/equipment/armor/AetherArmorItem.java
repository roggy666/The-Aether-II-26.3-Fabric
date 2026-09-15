package com.aetherteam.aetherii.item.equipment.armor;

import com.aetherteam.aetherii.item.CustomEnchantmentItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class AetherArmorItem extends Item implements CustomEnchantmentItem {
    public AetherArmorItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}
