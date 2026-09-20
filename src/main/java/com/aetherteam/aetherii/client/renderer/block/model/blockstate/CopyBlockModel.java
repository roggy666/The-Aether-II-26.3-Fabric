package com.aetherteam.aetherii.client.renderer.block.model.blockstate;

import java.util.function.Predicate;
import net.fabricmc.fabric.api.client.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.client.model.loading.v1.wrapper.WrapperBlockStateModel;
import com.aetherteam.aetherii.blockentity.LockedBlockEntity;
import com.aetherteam.aetherii.mixin.mixins.client.accessor.BlockModelRenderStateAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.SimpleModelWrapper;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyBlockModel extends WrapperBlockStateModel {
    private static final Direction[] DIRECTIONS = Arrays.copyOfRange(Direction.values(), 0, 7);

    public CopyBlockModel(BlockStateModel delegate) {
        super(delegate);
    }

    @Override
    public void emitQuads(QuadEmitter emitter, BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random, Predicate<Direction> cullTest) {
        if (!(level.getBlockEntityRenderData(pos) instanceof LockedBlockEntity.CopyData data)) return;
        BlockState mimicState = data.state();
        BlockStateModel model = Minecraft.getInstance().getModelManager().getBlockStateModelSet().get(mimicState);
        if (model instanceof CopyBlockModel) return;
        emitter.pushTransform(quad -> {
            quad.chunkLayer(quad.lightmap(0) > 0 ? ChunkSectionLayer.CUTOUT : ChunkSectionLayer.SOLID);
            quad.tintIndex(0).multiplyColor(-4276546);
            return true;
        });
        try {
            model.emitQuads(emitter, level, pos, mimicState, random, cullTest);
        } finally {
            emitter.popTransform();
        }
    }

    @Override
    public Object createGeometryKey(BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random) {
        return null;
    }

    public void collectCopyParts(List<BlockStateModelPart> baseList, List<BlockStateModelPart> addTo) {
        for (BlockStateModelPart modelPart : baseList) {
            QuadCollection.Builder builder = new QuadCollection.Builder();
            for (Direction side : DIRECTIONS) {
                List<BakedQuad> quads = modelPart.getQuads(side);
                for (BakedQuad oldQuad : quads) {
                    ChunkSectionLayer blockRenderType = oldQuad.materialInfo().lightEmission() > 0 ? ChunkSectionLayer.CUTOUT : ChunkSectionLayer.SOLID;
                    BakedQuad newQuad = new BakedQuad(
                            oldQuad.position0(),
                            oldQuad.position1(),
                            oldQuad.position2(),
                            oldQuad.position3(),
                            oldQuad.packedUV0(),
                            oldQuad.packedUV1(),
                            oldQuad.packedUV2(),
                            oldQuad.packedUV3(),
                            oldQuad.direction(),
                            new BakedQuad.MaterialInfo(oldQuad.materialInfo().sprite(), blockRenderType, Sheets.cutoutBlockItemSheet(), Sheets.cutoutBlockItemGlintSheet(), Sheets.cutoutBlockItemGlintSpecialSheet(), 0, oldQuad.materialInfo().shadeDirectionOverride(), oldQuad.materialInfo().lightEmission())
                    );
                    if (side == null) {
                        builder.addUnculledFace(newQuad);
                    } else {
                        builder.addCulledFace(side, newQuad);
                    }
                }
            }
            addTo.add(new SimpleModelWrapper(builder.build(), modelPart.useAmbientOcclusion(), modelPart.particleMaterial()));
        }
    }

    @Override
    public Material.Baked particleMaterial(BlockAndTintGetter level, BlockPos pos, BlockState state) {
        Object renderData = level.getBlockEntityRenderData(pos);
        if (!(renderData instanceof LockedBlockEntity.CopyData data)) {
            return super.particleMaterial(level, pos, state);
        }
        BlockState mimicState = data.state();
        return Minecraft.getInstance().getModelManager().getBlockStateModelSet().get(mimicState).particleMaterial(level, pos, mimicState);
    }
}
