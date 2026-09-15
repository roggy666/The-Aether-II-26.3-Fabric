package com.aetherteam.aetherii.mixin.mixins.common.invoker;

import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MobCategory.class)
public interface MobCategoryInvoker {
    @Invoker("<init>")
    static MobCategory aether_ii$create(String internalName, int internalOrdinal, String name, String debugAbbreviation, int max, boolean isFriendly, boolean isPersistent, int despawnDistance) {
        throw new AssertionError();
    }
}
