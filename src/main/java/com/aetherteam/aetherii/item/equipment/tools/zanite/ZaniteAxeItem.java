package com.aetherteam.aetherii.item.equipment.tools.zanite;

import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.item.equipment.AetherIIToolMaterials;
import com.aetherteam.aetherii.item.equipment.tools.abilities.ZaniteTool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class ZaniteAxeItem extends Item implements ZaniteTool {
    public ZaniteAxeItem(Properties properties) {
        super(properties.axe(AetherIIToolMaterials.ZANITE, 1.5F, -3.2F));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}
