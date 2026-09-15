package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.aetherteam.aetherii.client.event.hooks.AudioHooks;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(Minecraft.class)
public class MinecraftMusicMixin {
    @Inject(method = "getSituationalMusic", at = @At("HEAD"), cancellable = true)
    private void aether_ii$music(CallbackInfoReturnable<Music> cir) {
        Music music = AudioHooks.getSituationalMusic();
        if (music != null) cir.setReturnValue(music);
    }
}
