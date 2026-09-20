package com.aetherteam.aetherii.item.equipment.tools.holystone;

import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.item.equipment.AetherIIToolMaterials;
import com.aetherteam.aetherii.item.equipment.tools.abilities.HolystoneTool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class HolystoneTrowelItem extends Item implements HolystoneTool {
    public HolystoneTrowelItem(Properties properties) {
        super(properties.hoe(AetherIIToolMaterials.HOLYSTONE, 0.5F, -2.5F));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}
