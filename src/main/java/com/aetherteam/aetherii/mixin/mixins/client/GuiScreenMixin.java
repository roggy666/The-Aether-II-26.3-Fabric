package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.aetherteam.aetherii.client.AetherIIClientEventListeners;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
@Mixin(Gui.class)
public class GuiScreenMixin {
    @ModifyVariable(method = "setScreen", at = @At("HEAD"), argsOnly = true)
    private Screen aether_ii$screen(Screen screen) {
        return AetherIIClientEventListeners.onGuiOpen(screen);
    }
}
