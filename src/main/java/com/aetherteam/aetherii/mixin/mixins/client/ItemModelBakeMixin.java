package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.aetherteam.aetherii.client.renderer.block.model.AetherIIModelLoaders;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.renderer.item.CuboidItemModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import org.joml.Matrix4fc;
@Mixin(CuboidItemModelWrapper.Unbaked.class)
public class ItemModelBakeMixin {
    @WrapMethod(method = "bake")
    private ItemModel aether_ii$itemOrder(ItemModel.BakingContext context, Matrix4fc transform, Operation<ItemModel> original) {
        boolean previous = AetherIIModelLoaders.BAKING_ITEM.get();
        AetherIIModelLoaders.BAKING_ITEM.set(true);
        try { return original.call(context, transform); }
        finally { AetherIIModelLoaders.BAKING_ITEM.set(previous); }
    }
}
