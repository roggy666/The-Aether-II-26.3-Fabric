package com.aetherteam.aetherii.item.equipment.tools.skyroot;

import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.item.equipment.AetherIIToolMaterials;
import com.aetherteam.aetherii.item.equipment.tools.abilities.SkyrootTool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class SkyrootTrowelItem extends Item implements SkyrootTool {
    public SkyrootTrowelItem(Properties properties) {
        super(properties.hoe(AetherIIToolMaterials.SKYROOT, 0.5F, -2.5F));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}
