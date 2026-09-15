package com.aetherteam.aetherii.mixin.mixins.common.accessor;

import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemEntity.class)
public interface ItemEntityAccessor {
    @Accessor("age")
    int aether_ii$getAge();

    @Accessor("age")
    void aether_ii$setAge(int age);
}
