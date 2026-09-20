package com.aetherteam.aetherii.item.equipment.tools.skyroot;

import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.item.equipment.AetherIIToolMaterials;
import com.aetherteam.aetherii.item.equipment.tools.abilities.SkyrootTool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class SkyrootShovelItem extends Item implements SkyrootTool {
    public SkyrootShovelItem(Properties properties) {
        super(properties.shovel(AetherIIToolMaterials.SKYROOT, 1.5F, -3.0F));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}