package com.aetherteam.aetherii.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.aetherteam.aetherii.client.AetherIIClientExtensions;

@Mixin(ClientLevel.class)
public class UnstableParticlesMixin {
    @Inject(method = "addDestroyBlockEffect", at = @At("HEAD"), cancellable = true)
    private void destroy(BlockPos pos, BlockState state, CallbackInfo ci) {
        if (AetherIIClientExtensions.isUnstableBlock(state)) {
            AetherIIClientExtensions.addUnstableDestroyEffects(state, (ClientLevel) (Object) this, pos, Minecraft.getInstance().particleEngine);
            ci.cancel();
        }
    }
}
