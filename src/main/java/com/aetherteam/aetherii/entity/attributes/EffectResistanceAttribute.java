package com.aetherteam.aetherii.entity.attributes;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.TooltipFlag;

import java.util.function.Supplier;

public class EffectResistanceAttribute extends PercentageAttribute {
    // Effects also reference attributes during registration; resolve only after both registries initialize.
    private final Supplier<? extends Holder<MobEffect>> effect;

    public EffectResistanceAttribute(Supplier<? extends Holder<MobEffect>> effect, String pDescriptionId, double pDefaultValue, double pMin, double pMax, double scaleFactor) {
        super(pDescriptionId, pDefaultValue, pMin, pMax, scaleFactor);
        this.effect = effect;
    }

    public EffectResistanceAttribute(Supplier<? extends Holder<MobEffect>> effect, String pDescriptionId, double pDefaultValue, double pMin, double pMax) {
        super(pDescriptionId, pDefaultValue, pMin, pMax);
        this.effect = effect;
    }

    public Holder<MobEffect> getEffect() {
        return this.effect.get();
    }

    /**
     * The description key takes the effect's name as an argument ("%s Resistance").
     */
    @Override
    public MutableComponent toComponent(AttributeModifier modifier, TooltipFlag flag) {
        double value = modifier.amount();
        String key = value > 0.0 ? "attribute.modifier.plus.0" : "attribute.modifier.take.0";
        ChatFormatting color = this.getStyle(value > 0.0);
        Component attrDesc = Component.translatable(this.getDescriptionId(), Component.translatable(this.getEffect().value().getDescriptionId()));
        Component valueComp = this.toValueComponent(modifier.operation(), Math.abs(value), flag);
        MutableComponent comp = Component.translatable(key, valueComp, attrDesc).withStyle(color);
        return comp.append(this.getDebugInfo(modifier, flag));
    }
}
