package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.item.miscellaneous.glider.AercloudGliderItem;
import com.aetherteam.aetherii.mixin.MixinHooks;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @Inject(method = "submitArmWithItem(Lnet/minecraft/client/player/AbstractClientPlayer;FFLnet/minecraft/world/InteractionHand;FLnet/minecraft/world/item/ItemStack;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V", at = @At(value = "HEAD"), cancellable = true)
    private void renderArmWithItem(AbstractClientPlayer player, float partialTicks, float pitch, InteractionHand hand, float swingProgress, ItemStack stack, float equippedProgress, PoseStack poseStack, SubmitNodeCollector collector, int combinedLight, CallbackInfo ci) {
        if (!player.isScoping() && player instanceof net.minecraft.client.player.LocalPlayer localPlayer && player.getUsedItemHand() == hand && com.aetherteam.aetherii.client.AetherIIClientExtensions.isThrowable(stack) && player.isUsingItem()) {
            HumanoidArm arm = hand == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
            poseStack.pushPose();
            com.aetherteam.aetherii.client.AetherIIClientExtensions.transformThrowable(poseStack, localPlayer, arm, stack, partialTicks, equippedProgress, swingProgress);
            ((ItemInHandRenderer) (Object) this).renderItem(player, stack, arm == HumanoidArm.RIGHT ? net.minecraft.world.item.ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : net.minecraft.world.item.ItemDisplayContext.FIRST_PERSON_LEFT_HAND, poseStack, collector, combinedLight);
            poseStack.popPose();
            ci.cancel();
            return;
        }
        if (player.getUseItem().getItem() instanceof AercloudGliderItem && player.getUsedItemHand() != hand) {
            ci.cancel();
        }
    }

    @Inject(method = "submitArmWithItem(Lnet/minecraft/client/player/AbstractClientPlayer;FFLnet/minecraft/world/InteractionHand;FLnet/minecraft/world/item/ItemStack;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", shift = At.Shift.AFTER))
    private void renderArmWithItemAccessory(AbstractClientPlayer player, float partialTicks, float pitch, InteractionHand hand, float swingProgress, ItemStack stack, float equippedProgress, PoseStack poseStack, SubmitNodeCollector collector, int combinedLight, CallbackInfo ci, @Local boolean flag, @Local HumanoidArm humanoidarm) {
        if (stack.isEmpty() && flag && player.isInvisible()) {
            poseStack.pushPose();
            MixinHooks.RENDERING_ACCESSORY = true;
            this.renderPlayerArm(poseStack, collector, combinedLight, equippedProgress, swingProgress, humanoidarm);
            MixinHooks.RENDERING_ACCESSORY = false;
            poseStack.popPose();
        }
    }

    @Shadow
    public abstract void renderPlayerArm(PoseStack poseStack, SubmitNodeCollector collector, int combinedLight, float equippedProgress, float swingProgress, HumanoidArm side);
}
