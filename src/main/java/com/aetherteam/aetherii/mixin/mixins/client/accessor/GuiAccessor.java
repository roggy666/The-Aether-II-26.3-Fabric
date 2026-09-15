package com.aetherteam.aetherii.mixin.mixins.client.accessor;

import net.minecraft.client.gui.Hud;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Hud.class)
public interface GuiAccessor {
    @Accessor("overlayMessageString")
    Component aether$getOverlayMessageString();

    @Accessor("overlayMessageTime")
    int aether$getOverlayMessageTime();

    @Accessor("overlayMessageTime")
    void aether$setOverlayMessageTime(int overlayMessageTime);
}
