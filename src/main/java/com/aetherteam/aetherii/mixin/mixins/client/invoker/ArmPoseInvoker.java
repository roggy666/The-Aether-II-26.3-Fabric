package com.aetherteam.aetherii.mixin.mixins.client.invoker;

import net.minecraft.client.model.HumanoidModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HumanoidModel.ArmPose.class)
public interface ArmPoseInvoker {
    @Invoker("<init>")
    static HumanoidModel.ArmPose aether_ii$create(String internalName, int internalOrdinal, boolean twoHanded, boolean affectsOffhandPose) {
        throw new AssertionError();
    }
}
