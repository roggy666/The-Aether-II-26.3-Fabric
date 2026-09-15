package com.aetherteam.aetherii.mixin.mixins.client.accessor;

import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.util.context.ContextMap;
import org.spongepowered.asm.mixin.gen.Invoker;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GhostSlots.class)
public interface GhostSlotsAccessor {
    @Accessor("ingredients")
    Reference2ObjectMap<Slot, GhostSlots.GhostSlot> aether_ii$getIngredients();
    @Invoker("setInput")
    void aether_ii$setInput(Slot slot, ContextMap context, SlotDisplay display);

    @Invoker("setResult")
    void aether_ii$setResult(Slot slot, ContextMap context, SlotDisplay display);
}
