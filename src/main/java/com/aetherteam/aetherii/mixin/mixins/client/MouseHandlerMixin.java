package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.aetherteam.aetherii.client.AetherIIClientEventListeners;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Inject(method = "onButton", at = @At("RETURN"))
    private void aether_ii$mouseInput(long window, MouseButtonInfo button, int action, CallbackInfo ci) {
        if (window == Minecraft.getInstance().getWindow().handle()) AetherIIClientEventListeners.onMouseInputPost(button.button(), action);
    }
}
