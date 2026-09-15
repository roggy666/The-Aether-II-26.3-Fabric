package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.mixin.mixins.common.invoker.RecipeBookTypeInvoker;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(RecipeBookType.class)
public abstract class RecipeBookTypeMixin {
    @Shadow
    @Final
    @Mutable
    private static RecipeBookType[] $VALUES;

    static {
        List<RecipeBookType> types = new ArrayList<>(Arrays.asList($VALUES));
        types.add(RecipeBookTypeInvoker.aether_ii$create("AETHER_II_AMBER_HOURGLASS", types.size()));
        types.add(RecipeBookTypeInvoker.aether_ii$create("AETHER_II_ALTAR", types.size()));
        types.add(RecipeBookTypeInvoker.aether_ii$create("AETHER_II_ALKAHEST_PURIFIER", types.size()));
        $VALUES = types.toArray(new RecipeBookType[0]);
    }
}
