package com.aetherteam.aetherii.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lets listeners change the attribute modifiers of an item stack (NeoForge's {@code ItemAttributeModifierEvent}).
 * Fired from {@code ItemStackMixin} every time the stack's modifiers are queried, so the returned modifiers reflect
 * the current stack contents (charms, reinforcement tiers, ...).
 */
public final class ItemAttributeModifierEvent {
    public static final Event<Callback> EVENT = EventFactory.createArrayBacked(Callback.class, callbacks -> event -> {
        for (Callback callback : callbacks) callback.modify(event);
    });

    private final ItemStack stack;
    private final ItemAttributeModifiers defaultModifiers;
    private final List<ItemAttributeModifiers.Entry> modifiers;

    private ItemAttributeModifierEvent(ItemStack stack, ItemAttributeModifiers defaultModifiers) {
        this.stack = stack;
        this.defaultModifiers = defaultModifiers;
        this.modifiers = new ArrayList<>(defaultModifiers.modifiers());
    }

    /**
     * @return The modifiers of the stack after every listener had its say; the stack's own component when nobody is listening.
     */
    public static ItemAttributeModifiers compute(ItemStack stack) {
        ItemAttributeModifiers defaults = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        ItemAttributeModifierEvent event = new ItemAttributeModifierEvent(stack, defaults);
        EVENT.invoker().modify(event);
        return event.build();
    }

    public ItemStack getItemStack() {
        return this.stack;
    }

    public ItemAttributeModifiers getDefaultModifiers() {
        return this.defaultModifiers;
    }

    public List<ItemAttributeModifiers.Entry> getModifiers() {
        return Collections.unmodifiableList(this.modifiers);
    }

    public void addModifier(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot) {
        this.modifiers.add(new ItemAttributeModifiers.Entry(attribute, modifier, slot));
    }

    public boolean removeModifier(Holder<Attribute> attribute, net.minecraft.resources.Identifier id) {
        return this.modifiers.removeIf(entry -> entry.matches(attribute, id));
    }

    public void replaceModifier(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot) {
        this.removeModifier(attribute, modifier.id());
        this.addModifier(attribute, modifier, slot);
    }

    public ItemAttributeModifiers build() {
        if (this.modifiers.equals(this.defaultModifiers.modifiers())) {
            return this.defaultModifiers;
        }
        return new ItemAttributeModifiers(List.copyOf(this.modifiers));
    }

    @FunctionalInterface
    public interface Callback {
        void modify(ItemAttributeModifierEvent event);
    }
}
