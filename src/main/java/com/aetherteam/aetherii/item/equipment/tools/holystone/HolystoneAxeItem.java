package com.aetherteam.aetherii.item.equipment.tools.holystone;

import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.item.equipment.AetherIIToolMaterials;
import com.aetherteam.aetherii.item.equipment.tools.abilities.HolystoneTool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class HolystoneAxeItem extends Item implements HolystoneTool {
    public HolystoneAxeItem(Properties properties) {
        super(properties.axe(AetherIIToolMaterials.HOLYSTONE, 1.5F, -3.2F));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}
