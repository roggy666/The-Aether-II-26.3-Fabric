package com.aetherteam.aetherii.item.equipment.tools.skyroot;

import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.item.equipment.AetherIIToolMaterials;
import com.aetherteam.aetherii.item.equipment.tools.abilities.SkyrootTool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class SkyrootAxeItem extends Item implements SkyrootTool {
    public SkyrootAxeItem(Properties properties) {
        super(properties.axe(AetherIIToolMaterials.SKYROOT, 1.5F, -3.2F));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}
