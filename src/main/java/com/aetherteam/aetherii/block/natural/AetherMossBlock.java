package com.aetherteam.aetherii.block.natural;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.block.BonemealSource;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class AetherMossBlock extends Block implements BonemealableBlock {
    private final ResourceKey<Feature> mossFeature;

    public AetherMossBlock(ResourceKey<Feature> mossFeature, BlockBehaviour.Properties properties) {
        super(properties);
        this.mossFeature = mossFeature;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source) {
        return level.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        // Neo: Fire the BlockGrowFeatureEvent and change the ifPresent call to use the event's result.
        var featureHolder = this.getFeature(level).orElse(null);
        Optional.ofNullable(featureHolder).ifPresent(p_256352_ -> p_256352_.value().place(level, level.getChunkSource().getGenerator(), random, pos.above()));
    }

    private Optional<? extends Holder<Feature>> getFeature(LevelReader level) {
        return level.registryAccess().lookupOrThrow(Registries.FEATURE).get(this.mossFeature);
    }


    @Override
    public BonemealableBlock.Type getType() {
        return BonemealableBlock.Type.NEIGHBOR_SPREADER;
    }
}