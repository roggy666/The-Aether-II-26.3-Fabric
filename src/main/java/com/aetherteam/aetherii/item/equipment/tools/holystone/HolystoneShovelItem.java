package com.aetherteam.aetherii.item.equipment.tools.holystone;

import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.item.equipment.AetherIIToolMaterials;
import com.aetherteam.aetherii.item.equipment.tools.abilities.HolystoneTool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class HolystoneShovelItem extends Item implements HolystoneTool {
    public HolystoneShovelItem(Properties properties) {
        super(properties.shovel(AetherIIToolMaterials.HOLYSTONE, 1.5F, -3.0F));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}
