package com.aetherteam.aetherii.item.equipment.tools.zanite;

import net.minecraft.world.item.Item;
import com.aetherteam.aetherii.item.equipment.AetherIIToolMaterials;
import com.aetherteam.aetherii.item.equipment.tools.abilities.ZaniteTool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class ZaniteShovelItem extends Item implements ZaniteTool {
    public ZaniteShovelItem(Properties properties) {
        super(properties.shovel(AetherIIToolMaterials.ZANITE, 1.5F, -3.0F));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }
}
