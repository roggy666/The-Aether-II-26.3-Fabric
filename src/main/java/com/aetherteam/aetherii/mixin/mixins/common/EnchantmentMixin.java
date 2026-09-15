package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.item.CustomEnchantmentItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "canEnchant(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void aether_ii$canEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof CustomEnchantmentItem custom && !custom.supportsEnchantment(stack, Holder.direct((Enchantment) (Object) this))) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "isSupportedItem(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void aether_ii$isSupportedItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof CustomEnchantmentItem custom && !custom.supportsEnchantment(stack, Holder.direct((Enchantment) (Object) this))) {
            cir.setReturnValue(false);
        }
    }
}
