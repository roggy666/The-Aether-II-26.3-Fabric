package com.aetherteam.aetherii.mixin.mixins.client;

import net.minecraft.world.entity.HumanoidArm;
import com.aetherteam.aetherii.client.AetherIIArmPoseTransformers;
import com.aetherteam.aetherii.client.AetherIIArmPoses;
import com.aetherteam.aetherii.client.renderer.AetherIIRenderers;
import com.aetherteam.aetherii.mixin.MixinHooks;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin<T extends HumanoidRenderState> {
    @Shadow
    @Final
    public ModelPart head;
    @Shadow
    @Final
    public ModelPart body;
    @Shadow
    @Final
    public ModelPart rightArm;
    @Shadow
    @Final
    public ModelPart leftArm;
    @Shadow
    @Final
    public ModelPart rightLeg;
    @Shadow
    @Final
    public ModelPart leftLeg;

    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V", at = @At("TAIL"))
    private void setupAnim(T renderState, CallbackInfo ci) {
        if (renderState.getDataOrDefault(AetherIIRenderers.RIDING_MOA_KEY, false)) {
            MixinHooks.positionMoaRider(renderState, this.head, this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg);
        }
    }
    @Inject(method = "poseRightArm", at = @At("HEAD"), cancellable = true)
    private void aether_ii$rightPose(T state, CallbackInfo ci) {
        if (aether_ii$applyPose(state.rightArmPose, state, HumanoidArm.RIGHT)) ci.cancel();
    }

    @Inject(method = "poseLeftArm", at = @At("HEAD"), cancellable = true)
    private void aether_ii$leftPose(T state, CallbackInfo ci) {
        if (aether_ii$applyPose(state.leftArmPose, state, HumanoidArm.LEFT)) ci.cancel();
    }

    @org.spongepowered.asm.mixin.Unique
    private boolean aether_ii$applyPose(HumanoidModel.ArmPose pose, T state, HumanoidArm arm) {
        HumanoidModel<?> model = (HumanoidModel<?>) (Object) this;
        if (pose == AetherIIArmPoses.DART_SHOOTER) AetherIIArmPoseTransformers.DART_SHOOTER_TRANSFORMER(model, state, arm);
        else if (pose == AetherIIArmPoses.GLIDING) AetherIIArmPoseTransformers.GLIDING_TRANSFORMER(model, state, arm);
        else if (pose == AetherIIArmPoses.SKIFF_SAILING) AetherIIArmPoseTransformers.SKIFF_SAILING_TRANSFORMER(model, state, arm);
        else return false;
        return true;
    }
}
