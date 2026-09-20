package com.aetherteam.aetherii.client.renderer.blockentity;

import com.aetherteam.aetherii.client.AetherIIAtlases;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import org.jspecify.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.MultiblockChestResources;
import net.minecraft.client.model.object.chest.ChestModel;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class SkyrootChestRenderer<T extends BlockEntity & LidBlockEntity> extends ChestRenderer<T> {
	private final SpriteGetter sprites;
	private final MultiblockChestResources<ChestModel> models;
	public SkyrootChestRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
        this.sprites = context.sprites();
        this.models = LAYERS.map(layer -> new ChestModel(context.bakeLayer(layer)));
	}

    @Override
    public void submit(ChestRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.mulPose(modelTransformation(state.facing));
        float open = 1.0F - state.open;
        open = 1.0F - open * open * open;
        var model = this.models.select(state.type);
        SpriteId spriteId = getCustomSprite(null, state);
        collector.submitModel(model, open, poseStack, state.lightCoords, OverlayTexture.NO_OVERLAY, -1, spriteId, this.sprites, 0);
        if (state.breakProgress != null) {
            collector.order(1).submitCrumblingOverlay(model, open, poseStack, spriteId.renderType(model::renderType), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, state.breakProgress);
        }
        poseStack.popPose();
    }

	protected @Nullable SpriteId getCustomSprite(T blockEntity, ChestRenderState renderState) {
		return switch (renderState.type) {
			case LEFT -> AetherIIAtlases.SKYROOT_CHEST_LEFT_MATERIAL;
			case RIGHT -> AetherIIAtlases.SKYROOT_CHEST_RIGHT_MATERIAL;
			case SINGLE -> AetherIIAtlases.SKYROOT_CHEST_MATERIAL;
		};
	}
}