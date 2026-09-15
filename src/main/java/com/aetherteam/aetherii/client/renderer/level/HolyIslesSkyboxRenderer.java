package com.aetherteam.aetherii.client.renderer.level;

import java.util.OptionalDouble;
import java.util.Optional;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.PrimitiveTopology;
import com.aetherteam.aetherii.client.AetherIIRenderPipelines;
import com.aetherteam.aetherii.client.renderer.AetherIIDimensionRenderers;
import com.aetherteam.aetherii.client.renderer.AetherIIRenderTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

import java.awt.*;

public class HolyIslesSkyboxRenderer {
    public void renderCloudCover(LevelRenderState state, RenderTarget target) {
        float time = state.getDataOrDefault(AetherIIDimensionRenderers.DATA_TIME_OF_DAY_KEY, 0.0F);
        try (ByteBufferBuilder bytes = new ByteBufferBuilder(4096)) {
            BufferBuilder vertices = new BufferBuilder(bytes, PrimitiveTopology.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
            this.renderCloudCoverDisc(state, new PoseStack(), vertices, time, state.skyRenderState.skyColor, getSunriseOrSunsetColor(time));
            try (MeshData mesh = vertices.buildOrThrow();
                 GpuBuffer buffer = RenderSystem.getDevice().createBuffer(() -> "Aether cloud cover", GpuBuffer.USAGE_VERTEX, mesh.vertexBuffer())) {
                var transform = RenderSystem.getDynamicUniforms().writeTransform(RenderSystem.getModelViewMatrixCopy(), new org.joml.Vector4f(1.0F));
                try (RenderPass pass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Aether cloud cover", target.getColorTextureView(), Optional.empty(), target.getDepthTextureView(), OptionalDouble.empty())) {
                    pass.setPipeline(AetherIIRenderPipelines.getCloudCoverShader());
                    RenderSystem.bindDefaultUniforms(pass);
                    pass.setUniform("DynamicTransforms", transform);
                    pass.setVertexBuffer(0, buffer.slice());
                    pass.draw(mesh.drawState().vertexCount(), 1, 0, 0);
                }
            }
        }
    }

    public void renderCloudCoverDisc(LevelRenderState levelRenderState, PoseStack poseStack, VertexConsumer cloudCoverBuffer, float timeOfDay, int skyColor, int sunColor) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
        Matrix4f matrix4f = poseStack.last().pose();


        float r = ARGB.redFloat(skyColor);
        float g = ARGB.greenFloat(skyColor);
        float b = ARGB.blueFloat(skyColor);
        Color color = new Color((int) (r * 255), (int) (g * 255), (int) (b * 255)).brighter();
        float weatherMultiplier = Math.max(1.0F - (((Math.abs(levelRenderState.skyRenderState.rainBrightness - 1) + levelRenderState.getDataOrDefault(AetherIIDimensionRenderers.DATA_THUNDER_KEY, 0.0F)) * 0.5F) * 0.275F), 0.175F);
        float bluePower = Math.min(0.5F / weatherMultiplier, 0.85F);
        r = (Math.min(color.getRed() + 20, 255.0F) / 255.0F) * weatherMultiplier;
        g = (Math.min(color.getGreen() + 20, 255.0F) / 255.0F) * weatherMultiplier;
        b = (Math.min(color.getBlue() + 35, 255.0F) / 255.0F) * (float) Math.pow(weatherMultiplier, bluePower);

        if (this.isSunriseOrSunset(timeOfDay)) {
            float cosTime = Mth.cos(timeOfDay * Mth.TWO_PI);
            float alpha;
            if (cosTime > 0) {
                alpha = Math.clamp(20.0F * (float) Math.pow(0.4F - Mth.abs(cosTime), 2.5F), 0.0F, 0.6F);
            } else {
                alpha = (1.5F * (float) Math.pow(0.4F - Mth.abs(cosTime), 1.0F));
            }
            r = Mth.clamp(((ARGB.redFloat(sunColor)) * alpha + r * (1.0F - alpha)), 0.0F, 1.0F);
            g = Mth.clamp(((ARGB.greenFloat(sunColor)) * alpha + g * (1.0F - alpha)), 0.0F, 1.0F);
            b = Mth.clamp(((ARGB.blueFloat(sunColor)) * alpha + b * (1.0F - alpha)), 0.0F, 1.0F);
        }

        double cameraHeight = (levelRenderState.cameraRenderState.pos.y - 66) * 0.03125F;
        if (cameraHeight < 1.0) {
            if (cameraHeight < 0.0) {
                cameraHeight = 0.0;
            }
            cameraHeight *= cameraHeight;
            r *= (float) Math.clamp(cameraHeight, 0.15F, 1.0F);
            g *= (float) Math.clamp(cameraHeight, 0.15F, 1.0F);
            b *= (float) Math.clamp(cameraHeight * 1.25F, 0.15F * 1.25F, 1.0F);
        }

        cloudCoverBuffer.addVertex(matrix4f, 0.0F, -16.0F, 0.0F).setColor(ARGB.colorFromFloat(1.0F, r, g, b));
        for (int i = -180; i <= 180; i += 9) {
            cloudCoverBuffer.addVertex(matrix4f, Math.signum(-16.0F) * 512.0F * Mth.cos((float) i * (float) (Math.PI / 180.0)), -16.0F, 512.0F * Mth.sin((float) i * (float) (Math.PI / 180.0))).setColor(ARGB.colorFromFloat(0.0F, r, g, b));
        }

        poseStack.popPose();
    }

    public boolean isSunriseOrSunset(float timeOfDay) {
        float f = Mth.cos(timeOfDay * Mth.TWO_PI);
        return f >= -0.4F && f <= 0.4F;
    }

    public int getSunriseOrSunsetColor(float timeOfDay) {
        float f = Mth.cos(timeOfDay * Mth.TWO_PI);
        float f1 = f / 0.4F * 0.5F + 0.5F;
        float f2 = Mth.square(1.0F - (1.0F - Mth.sin(f1 * Mth.PI)) * 0.99F);
        return ARGB.colorFromFloat(f2, f1 * 0.3F + 0.65F, f1 * f1 * 0.7F + 0.25F, 0.4F);
    }
}