package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.item.AttributeTooltipUtil;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

/**
 * NeoForge's {@code AttributeUtil#applyTextFor} replaced vanilla's default attribute lines: custom base modifiers
 * ({@code BaseRangedAttribute#getBaseId}), {@code PercentageAttribute} formatting and the unique weapon tooltip colors
 * ({@code AttributeUtilMixin} on NeoForge). Only the default display is replaced; hidden/override displays stay vanilla.
 */
@Mixin(ItemAttributeModifiers.Display.Default.class)
public class ItemAttributeModifiersDisplayMixin {
    @Inject(method = "apply", at = @At("HEAD"), cancellable = true)
    private void aether_ii$apply(Consumer<Component> consumer, @Nullable Player player, Holder<Attribute> attribute, AttributeModifier modifier, CallbackInfo ci) {
        ItemStack stack = AttributeTooltipUtil.CURRENT_STACK.get();
        AttributeTooltipUtil.addModifierTooltip(stack != null ? stack : ItemStack.EMPTY, consumer, player, player != null ? player.level().registryAccess() : null, attribute, modifier);
        ci.cancel();
    }
}
