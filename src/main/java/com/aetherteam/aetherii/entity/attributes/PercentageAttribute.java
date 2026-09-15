package com.aetherteam.aetherii.entity.attributes;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.TooltipFlag;

import java.text.DecimalFormat;

/**
 * Custom attribute that formats its modifiers as percentages.
 */
public class PercentageAttribute extends RangedAttribute {
    public static final DecimalFormat FORMAT = ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT;
    protected final double scaleFactor;

    public PercentageAttribute(String descriptionId, double defaultValue, double min, double max, double scaleFactor) {
        super(descriptionId, defaultValue, min, max);
        this.scaleFactor = scaleFactor;
    }

    public PercentageAttribute(String descriptionId, double defaultValue, double min, double max) {
        this(descriptionId, defaultValue, min, max, 100.0);
    }

    public double getScaleFactor() {
        return this.scaleFactor;
    }

    public ChatFormatting getStyle(boolean positive) {
        return positive ? ChatFormatting.BLUE : ChatFormatting.RED;
    }

    public MutableComponent toComponent(AttributeModifier modifier, TooltipFlag flag) {
        double value = modifier.amount();
        String key = value > 0.0 ? "attribute.modifier.plus.0" : "attribute.modifier.take.0";
        ChatFormatting color = this.getStyle(value > 0.0);
        Component valueComp = this.toValueComponent(modifier.operation(), Math.abs(value), flag);
        MutableComponent comp = Component.translatable(key, valueComp, Component.translatable(this.getDescriptionId())).withStyle(color);
        return comp.append(this.getDebugInfo(modifier, flag));
    }

    public MutableComponent toValueComponent(AttributeModifier.Operation op, double value, TooltipFlag flag) {
        boolean addition = op == AttributeModifier.Operation.ADD_VALUE;
        double displayValue = addition ? value * this.scaleFactor : value * 100.0;
        return Component.literal(FORMAT.format(displayValue) + "%");
    }

    public Component getDebugInfo(AttributeModifier modifier, TooltipFlag flag) {
        if (flag.isAdvanced()) {
            return Component.literal(" (" + modifier.id() + ")").withStyle(ChatFormatting.GRAY);
        }
        return CommonComponents.EMPTY;
    }
}
