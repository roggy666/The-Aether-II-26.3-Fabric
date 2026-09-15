package com.aetherteam.aetherii.mixin.mixins.common.invoker;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Rarity.class)
public interface RarityInvoker {
    @Invoker("<init>")
    static Rarity aether_ii$create(String internalName, int internalOrdinal, int id, String name, ChatFormatting formatting) {
        throw new AssertionError();
    }
}
