package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.client.gui.screen.menu.CustomBranding;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @WrapOperation(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)V"))
    private void aether_ii$versionBranding(GuiGraphicsExtractor graphics, Font font, String text, int x, int y, int color, Operation<Void> original) {
        if ((Object) this instanceof CustomBranding branding) {
            branding.drawVersionBranding(graphics, text, color);
        } else {
            original.call(graphics, font, text, x, y, color);
        }
    }
}
