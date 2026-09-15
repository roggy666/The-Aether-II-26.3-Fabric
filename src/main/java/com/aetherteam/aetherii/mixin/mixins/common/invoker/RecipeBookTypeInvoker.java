package com.aetherteam.aetherii.mixin.mixins.common.invoker;

import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RecipeBookType.class)
public interface RecipeBookTypeInvoker {
    @Invoker("<init>")
    static RecipeBookType aether_ii$create(String internalName, int internalOrdinal) {
        throw new AssertionError();
    }
}
