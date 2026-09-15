package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.AetherIITags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Replaces NeoForge's {@code Item.Properties#setNoCombineRepair()}: items in {@link AetherIITags.Items#NO_COMBINE_REPAIR}
 * (the gliders) can't be repaired by combining two of them in the crafting grid.
 */
@Mixin(RepairItemRecipe.class)
public class RepairItemRecipeMixin {
    @Inject(method = "canCombine", at = @At("HEAD"), cancellable = true)
    private static void aether_ii$noCombineRepair(ItemStack first, ItemStack second, CallbackInfoReturnable<Boolean> cir) {
        if (first.is(AetherIITags.Items.NO_COMBINE_REPAIR) || second.is(AetherIITags.Items.NO_COMBINE_REPAIR)) {
            cir.setReturnValue(false);
        }
    }
}
