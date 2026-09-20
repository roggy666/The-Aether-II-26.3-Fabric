package com.aetherteam.aetherii.item.equipment.tools.arkenium;

import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.item.equipment.AetherIIToolMaterials;
import com.aetherteam.aetherii.item.equipment.tools.abilities.ArkeniumTool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class ArkeniumTrowelItem extends Item implements ArkeniumTool {
    public ArkeniumTrowelItem(Properties properties) {
        super(properties.hoe(AetherIIToolMaterials.ARKENIUM, 0.5F, -2.5F));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}
