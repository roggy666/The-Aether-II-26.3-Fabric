package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.client.event.hooks.RenderHooks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.BossEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Map;
import java.util.UUID;

@Mixin(BossHealthOverlay.class)
public abstract class BossHealthOverlayMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow @Final private Map<UUID, LerpingBossEvent> events;
    @Shadow protected abstract void extractBar(GuiGraphicsExtractor graphics, int x, int y, BossEvent event);

    @Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true)
    private void aether_ii$bossBars(GuiGraphicsExtractor graphics, CallbackInfo ci) {
        if (this.events.keySet().stream().noneMatch(RenderHooks::isAetherBossBar)) return;
        graphics.nextStratum();
        int y = 12;
        for (LerpingBossEvent event : this.events.values()) {
            int x = graphics.guiWidth() / 2 - 91;
            if (RenderHooks.isAetherBossBar(event.getId())) {
                RenderHooks.drawBossHealthBar(graphics, x, y, event);
                y += 32;
            } else {
                this.extractBar(graphics, x, y, event);
                Component name = event.getName();
                graphics.text(this.minecraft.font, name, graphics.guiWidth() / 2 - this.minecraft.font.width(name) / 2, y - 9, -1);
                y += 19;
            }
            if (y >= graphics.guiHeight() / 3) break;
        }
        ci.cancel();
    }
}
