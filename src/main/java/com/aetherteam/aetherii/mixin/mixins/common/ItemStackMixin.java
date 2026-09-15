package com.aetherteam.aetherii.mixin.mixins.common;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.DataComponentType;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.aetherteam.aetherii.event.ItemAttributeModifierEvent;
import com.aetherteam.aetherii.item.AttributeTooltipUtil;
import org.jetbrains.annotations.Nullable;
import com.aetherteam.aetherii.mixin.MixinHooks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin<E> {
    /**
     * Unbreakable loot items turn into a broken item instead of vanishing (vanilla 26.2 passes the owning
     * {@link ServerPlayer}; NeoForge passed the {@link LivingEntity}).
     */
    @Inject(method = "applyDamage(ILnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;"))
    private void applyDamage(int damage, @Nullable ServerPlayer player, Consumer<Item> itemConsumer, CallbackInfo ci) {
        if (player != null) {
            MixinHooks.breakLootItem((ItemStack) (Object) this, player);
        }
    }

    @Inject(method = "isEnchantable()Z", at = @At("HEAD"), cancellable = true)
    private void aether_ii$isEnchantable(org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemStack = (ItemStack) (Object) this;
        if (itemStack.getItem() instanceof com.aetherteam.aetherii.item.CustomEnchantmentItem custom && !custom.supportsEnchantment(itemStack, null)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * NeoForge's {@code ItemAttributeModifierEvent}: modifiers added by listeners (charms, zanite buffs) are included
     * everywhere vanilla iterates a stack's modifiers.
     */
    @WrapOperation(method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getOrDefault(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object aether_ii$modifiersByGroup(ItemStack stack, DataComponentType<?> type, Object defaultValue, Operation<Object> original) {
        if (type == DataComponents.ATTRIBUTE_MODIFIERS) {
            return ItemAttributeModifierEvent.compute(stack);
        }
        return original.call(stack, type, defaultValue);
    }

    @WrapOperation(method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getOrDefault(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object aether_ii$modifiersBySlot(ItemStack stack, DataComponentType<?> type, Object defaultValue, Operation<Object> original) {
        if (type == DataComponents.ATTRIBUTE_MODIFIERS) {
            return ItemAttributeModifierEvent.compute(stack);
        }
        return original.call(stack, type, defaultValue);
    }

    /**
     * The stack whose attribute lines are being built, for {@link ItemAttributeModifiersDisplayMixin}: vanilla's
     * {@code Display#apply} does not receive it (NeoForge's {@code AttributeUtil#applyTextFor} did).
     */
    @Inject(method = "addAttributeTooltips", at = @At("HEAD"))
    private void aether_ii$beginAttributeTooltips(CallbackInfo ci) {
        AttributeTooltipUtil.CURRENT_STACK.set((ItemStack) (Object) this);
    }

    @Inject(method = "addAttributeTooltips", at = @At("RETURN"))
    private void aether_ii$endAttributeTooltips(CallbackInfo ci) {
        AttributeTooltipUtil.CURRENT_STACK.remove();
    }
}
