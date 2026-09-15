package com.aetherteam.aetherii.mixin.mixins.client;

import com.aetherteam.aetherii.mixin.mixins.client.invoker.ArmPoseInvoker;
import net.minecraft.client.model.HumanoidModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(HumanoidModel.ArmPose.class)
public abstract class ArmPoseMixin {
    @Shadow
    @Final
    @Mutable
    private static HumanoidModel.ArmPose[] $VALUES;

    static {
        List<HumanoidModel.ArmPose> poses = new ArrayList<>(Arrays.asList($VALUES));
        poses.add(ArmPoseInvoker.aether_ii$create("AETHER_II_DART_SHOOTER_ARM_POSE", poses.size(), true, true));
        poses.add(ArmPoseInvoker.aether_ii$create("AETHER_II_GLIDING_ARM_POSE", poses.size(), true, true));
        poses.add(ArmPoseInvoker.aether_ii$create("AETHER_II_SKIFF_SAILING_ARM_POSE", poses.size(), true, true));
        $VALUES = poses.toArray(new HumanoidModel.ArmPose[0]);
    }
}
