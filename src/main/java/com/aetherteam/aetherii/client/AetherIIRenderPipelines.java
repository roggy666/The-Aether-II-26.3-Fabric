package com.aetherteam.aetherii.client;

import net.minecraft.client.renderer.BindGroupLayouts;
import com.mojang.blaze3d.PrimitiveTopology;
import com.aetherteam.aetherii.AetherII;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public class AetherIIRenderPipelines {
    public static final RenderPipeline.Snippet ENTITY_DITHER_SNIPPET = RenderPipeline.builder(RenderPipelines.MATRICES_FOG_LIGHT_DIR_SNIPPET)
            .withVertexShader(Identifier.withDefaultNamespace("core/entity"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(AetherII.MODID, "core/entity_dither"))
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER2)
            .withVertexBinding(0, DefaultVertexFormat.ENTITY)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .buildSnippet();

    private static final RenderPipeline ENTITY_DITHER_NO_CULL = RenderPipeline.builder(ENTITY_DITHER_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath(AetherII.MODID, "pipeline/entity_dither_no_cull"))
            .withShaderDefine("ALPHA_CUTOUT", 0.1F)
            .withShaderDefine("PER_FACE_LIGHTING")
            .withBindGroupLayout(BindGroupLayouts.SAMPLER1)
            .withCull(false)
            .build();

    private static final RenderPipeline CLOUD_COVER_SHADER = RenderPipeline.builder(RenderPipelines.MATRICES_FOG_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath(AetherII.MODID, "pipeline/cloud_cover"))
            .withVertexShader("core/position_color")
            .withFragmentShader(Identifier.fromNamespaceAndPath(AetherII.MODID, "core/cloud_cover"))
            .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .withPrimitiveTopology(PrimitiveTopology.TRIANGLE_FAN)
            .build();

    public static void registerShaders() {
        RenderPipelines.register(ENTITY_DITHER_NO_CULL);
        RenderPipelines.register(CLOUD_COVER_SHADER);
    }

    public static RenderPipeline getEntityDitherNoCull() {
        return ENTITY_DITHER_NO_CULL;
    }

    public static RenderPipeline getCloudCoverShader() {
        return CLOUD_COVER_SHADER;
    }
}
