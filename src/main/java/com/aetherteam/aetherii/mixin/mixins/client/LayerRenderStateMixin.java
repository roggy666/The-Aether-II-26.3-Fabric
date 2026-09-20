package com.aetherteam.aetherii.mixin.mixins.client;

import net.minecraft.client.resources.model.geometry.ItemQuads;
import com.aetherteam.aetherii.mixin.wrappers.client.IrradiatedDataWrapper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemStackRenderState.LayerRenderState.class)
public class LayerRenderStateMixin implements IrradiatedDataWrapper {

    @Shadow
    @Final
    private ItemQuads quads;
    @Shadow
    private ItemStackRenderState.FoilType foilType;
    @Unique
    private boolean aether_ii$isIrradiated;

    @Inject(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitItem(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/item/ItemDisplayContext;III[ILnet/minecraft/client/resources/model/geometry/ItemQuads;Lnet/minecraft/client/renderer/item/ItemStackRenderState$FoilType;)V"))
    private void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, int outlineColor, CallbackInfo ci) {
        if (this.aether_ii$isIrradiated && outlineColor == 0) {
            List<BakedQuad> snapshot = this.quads.all();
            submitNodeCollector.submitCustomGeometry(poseStack, com.aetherteam.aetherii.client.renderer.AetherIIRenderTypes.irradiatedGlint(), (pose, buffer) -> {
                com.mojang.blaze3d.vertex.QuadInstance instance = new com.mojang.blaze3d.vertex.QuadInstance();
                instance.setLightCoords(lightCoords);
                instance.setOverlayCoords(overlayCoords);
                instance.setColor(-1);
                for (BakedQuad quad : snapshot) buffer.putBakedQuad(pose, quad, instance);
            });
        }
    }

    @Inject(method = "clear", at = @At("HEAD"))
    private void clearIrradiated(CallbackInfo ci) {
        this.aether_ii$isIrradiated = false;
    }

    @Override
    public void aether_ii$setIrradiated(boolean irradiated) {
        this.aether_ii$isIrradiated = irradiated;
    }

    @Override
    public boolean aether_ii$getIrradiated() {
        return this.aether_ii$isIrradiated;
    }
}
