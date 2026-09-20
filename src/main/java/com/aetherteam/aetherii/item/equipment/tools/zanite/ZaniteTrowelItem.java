package com.aetherteam.aetherii.item.equipment.tools.zanite;

import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.item.equipment.AetherIIToolMaterials;
import com.aetherteam.aetherii.item.equipment.tools.abilities.ZaniteTool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class ZaniteTrowelItem extends Item implements ZaniteTool {
    public ZaniteTrowelItem(Properties properties) {
        super(properties.hoe(AetherIIToolMaterials.ZANITE, 0.5F, -2.5F));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}
