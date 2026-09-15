package com.aetherteam.aetherii.data.resources.builders.models;

import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelDispatcher;
import net.minecraft.client.renderer.block.dispatch.multipart.Condition;
import net.minecraft.client.renderer.block.dispatch.multipart.Selector;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * {@link net.minecraft.client.data.models.blockstates.MultiPartGenerator} only accepts {@link MultiVariant} parts;
 * this one also takes arbitrary {@link BlockStateModel.Unbaked} parts, so custom (Fabric {@code fabric:type})
 * block state models like the trunk corners can sit in a multipart definition.
 */
public class AetherIIMultiPartGenerator implements BlockModelDefinitionGenerator {
    private final Block block;
    private final List<Selector> parts = new ArrayList<>();

    private AetherIIMultiPartGenerator(Block block) {
        this.block = block;
    }

    public static AetherIIMultiPartGenerator multiPart(Block block) {
        return new AetherIIMultiPartGenerator(block);
    }

    @Override
    public Block block() {
        return this.block;
    }

    public AetherIIMultiPartGenerator with(BlockStateModel.Unbaked model) {
        this.parts.add(new Selector(Optional.empty(), model));
        return this;
    }

    public AetherIIMultiPartGenerator with(MultiVariant variants) {
        return this.with(variants.toUnbaked());
    }

    public AetherIIMultiPartGenerator with(Condition condition, BlockStateModel.Unbaked model) {
        this.validateCondition(condition);
        this.parts.add(new Selector(Optional.of(condition), model));
        return this;
    }

    public AetherIIMultiPartGenerator with(Condition condition, MultiVariant variants) {
        return this.with(condition, variants.toUnbaked());
    }

    public AetherIIMultiPartGenerator with(ConditionBuilder condition, MultiVariant variants) {
        return this.with(condition.build(), variants);
    }

    private void validateCondition(Condition condition) {
        condition.instantiate(this.block.getStateDefinition());
    }

    @Override
    public BlockStateModelDispatcher create() {
        return new BlockStateModelDispatcher(Optional.empty(), Optional.of(new BlockStateModelDispatcher.MultiPartDefinition(List.copyOf(this.parts))));
    }
}
