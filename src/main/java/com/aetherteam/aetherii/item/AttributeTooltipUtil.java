package com.aetherteam.aetherii.item;

import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.entity.attributes.BaseRangedAttribute;
import com.aetherteam.aetherii.entity.attributes.PercentageAttribute;
import com.aetherteam.aetherii.item.components.ReinforcementTier;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Attribute modifier tooltip lines for accessories, charms and shields. Mirrors vanilla's
 * {@code ItemAttributeModifiers.Display.Default} (NeoForge exposed it as {@code AttributeUtil#applyTextFor}) and adds
 * the mod's {@link PercentageAttribute} formatting and unique weapon tooltip colors.
 */
public final class AttributeTooltipUtil {
    private AttributeTooltipUtil() {
    }

    /** Stack whose attribute tooltip is being built (set by {@code ItemStackMixin} around {@code addAttributeTooltips}). */
    public static final ThreadLocal<ItemStack> CURRENT_STACK = new ThreadLocal<>();

    /**
     * NeoForge's {@code AttributeTooltipContext}.
     */
    public record Context(@Nullable Player player, Item.TooltipContext tooltipContext, TooltipDisplay display, TooltipFlag flag) {
        public static Context of(@Nullable Player player, Item.TooltipContext tooltipContext, TooltipDisplay display, TooltipFlag flag) {
            return new Context(player, tooltipContext, display, flag);
        }

        public @Nullable HolderLookup.Provider registries() {
            return this.tooltipContext.registries();
        }
    }

    public static void applyTextFor(ItemStack stack, Consumer<Component> tooltip, Multimap<Holder<Attribute>, AttributeModifier> modifiers, Context ctx) {
        for (Map.Entry<Holder<Attribute>, AttributeModifier> entry : modifiers.entries()) {
            addModifierTooltip(stack, tooltip, ctx.player(), ctx.registries(), entry.getKey(), entry.getValue());
        }
    }

    /**
     * One modifier line, formatted like vanilla ({@code ItemAttributeModifiers.Display.Default#apply}).
     */
    public static void addModifierTooltip(ItemStack stack, Consumer<Component> consumer, @Nullable Player player, @Nullable HolderLookup.Provider registries, Holder<Attribute> attribute, AttributeModifier modifier) {
        double amount = modifier.amount();
        boolean displayWithBase = false;
        if (player != null) {
            if (modifier.is(Item.BASE_ATTACK_DAMAGE_ID)) {
                amount += player.getAttributeBaseValue(Attributes.ATTACK_DAMAGE);
                displayWithBase = true;
            } else if (modifier.is(Item.BASE_ATTACK_SPEED_ID)) {
                amount += player.getAttributeBaseValue(Attributes.ATTACK_SPEED);
                displayWithBase = true;
            } else if (attribute.value() instanceof BaseRangedAttribute base && base.getBaseId() != null && modifier.is(base.getBaseId())) {
                // NeoForge's IAttributeExtension#getBaseId: slash/impact/pierce damage, sweep/shock/stab ranges
                amount += player.getAttributeBaseValue(attribute);
                displayWithBase = true;
            }
        }

        if (attribute.value() instanceof PercentageAttribute percentage && !displayWithBase) {
            consumer.accept(percentage.toComponent(modifier, TooltipFlag.NORMAL));
            return;
        }

        double displayAmount;
        if (modifier.operation() == AttributeModifier.Operation.ADD_MULTIPLIED_BASE || modifier.operation() == AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL) {
            displayAmount = amount * 100.0;
        } else if (attribute.is(Attributes.KNOCKBACK_RESISTANCE)) {
            displayAmount = amount * 10.0;
        } else {
            displayAmount = amount;
        }

        if (displayWithBase) {
            MutableComponent line = CommonComponents.space().append(Component.translatable("attribute.modifier.equals." + modifier.operation().id(), ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(displayAmount), Component.translatable(attribute.value().getDescriptionId())));
            consumer.accept(withBaseStyle(stack, registries, line));
        } else if (amount > 0.0) {
            consumer.accept(Component.translatable("attribute.modifier.plus." + modifier.operation().id(), ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(displayAmount), Component.translatable(attribute.value().getDescriptionId())).withStyle(attribute.value().getStyle(true)));
        } else if (amount < 0.0) {
            consumer.accept(Component.translatable("attribute.modifier.take." + modifier.operation().id(), ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(-displayAmount), Component.translatable(attribute.value().getDescriptionId())).withStyle(attribute.value().getStyle(false)));
        }
    }

    /**
     * Vanilla colors "equals" lines dark green; items in {@code aether_ii:unique_tooltip_color} use the weapon colors instead.
     */
    public static MutableComponent withBaseStyle(ItemStack stack, @Nullable HolderLookup.Provider registries, MutableComponent line) {
        if (stack.is(AetherIITags.Items.UNIQUE_TOOLTIP_COLOR) && registries != null) {
            if (ReinforcementTier.isItemAtMaxTier(registries, stack)) {
                return line.withStyle(AetherIIItems.UPGRADED_WEAPON_COLOR);
            } else {
                return line.withStyle(AetherIIItems.WEAPON_TOOLTIP_COLOR);
            }
        }
        return line.withStyle(ChatFormatting.DARK_GREEN);
    }

    /**
     * NeoForge's {@code AttributeUtil#addPotionTooltip}: the "when drank" modifier lines, formatted like {@code PotionContents#addPotionTooltip}.
     */
    public static void addPotionTooltip(List<Pair<Holder<Attribute>, AttributeModifier>> modifiers, Consumer<Component> lines) {
        for (Pair<Holder<Attribute>, AttributeModifier> entry : modifiers) {
            AttributeModifier modifier = entry.getSecond();
            double amount = modifier.amount();
            double displayAmount;
            if (modifier.operation() != AttributeModifier.Operation.ADD_MULTIPLIED_BASE && modifier.operation() != AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL) {
                displayAmount = modifier.amount();
            } else {
                displayAmount = modifier.amount() * 100.0;
            }

            if (amount > 0.0) {
                lines.accept(Component.translatable("attribute.modifier.plus." + modifier.operation().id(), ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(displayAmount), Component.translatable(entry.getFirst().value().getDescriptionId())).withStyle(ChatFormatting.BLUE));
            } else if (amount < 0.0) {
                displayAmount *= -1.0;
                lines.accept(Component.translatable("attribute.modifier.take." + modifier.operation().id(), ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(displayAmount), Component.translatable(entry.getFirst().value().getDescriptionId())).withStyle(ChatFormatting.RED));
            }
        }
    }
}
