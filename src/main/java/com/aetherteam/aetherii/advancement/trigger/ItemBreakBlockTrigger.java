package com.aetherteam.aetherii.advancement.trigger;

import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.core.Holder;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.world.phys.Vec3;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.Validatable;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;

import java.util.Optional;

public class ItemBreakBlockTrigger extends SimpleCriterionTrigger<ItemBreakBlockTrigger.Instance> {
    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public void trigger(ServerPlayer player, BlockPos pos, ItemStack stack) {
        ServerLevel level = player.level();
        BlockState state = level.getBlockState(pos);
        LootParams parameters = new LootParams.Builder(level)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                .withParameter(LootContextParams.THIS_ENTITY, player)
                .withParameter(LootContextParams.BLOCK_STATE, state)
                .withParameter(LootContextParams.TOOL, stack)
                .create(LootContextParamSets.ADVANCEMENT_LOCATION);
        LootContext context = new LootContext.Builder(parameters).create(Optional.empty());
        this.trigger(player, (instance) -> instance.matches(context));
    }

    public record Instance(Optional<Holder<LootItemCondition>> player, Optional<Holder<LootItemCondition>> location) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<Instance> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                LootItemCondition.CODEC.optionalFieldOf("player").forGetter(Instance::player),
                LootItemCondition.CODEC.optionalFieldOf("location").forGetter(Instance::location)
        ).apply(instance, Instance::new));

        public static Criterion<Instance> itemBrokeBlock(LocationPredicate.Builder location, ItemPredicate.Builder tool) {
            Holder<LootItemCondition> contextawarepredicate = Holder.direct(LocationCheck.checkLocation(location).and(MatchTool.toolMatches(tool)).build());
            Instance instance = new Instance(Optional.empty(), Optional.of(contextawarepredicate));
            return AetherIIAdvancementTriggers.ITEM_BREAK_BLOCK.createCriterion(instance);
        }

        public boolean matches(LootContext context) {
            return this.location.isEmpty() || this.location.get().value().test(context);
        }

        @Override
        public void validate(ValidationContextSource validator) {
            SimpleCriterionTrigger.SimpleInstance.super.validate(validator);
            Validatable.validateHolder(validator.context(LootContextParamSets.ADVANCEMENT_LOCATION), "location", this.location);
        }
    }
}