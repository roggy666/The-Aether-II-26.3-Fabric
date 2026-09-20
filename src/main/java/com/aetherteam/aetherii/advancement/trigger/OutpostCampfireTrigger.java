package com.aetherteam.aetherii.advancement.trigger;

import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.core.Holder;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class OutpostCampfireTrigger extends SimpleCriterionTrigger<OutpostCampfireTrigger.Instance> {
    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, (instance) -> true);
    }

    public record Instance(Optional<Holder<LootItemCondition>> player) implements SimpleInstance {
        public static final Codec<Instance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        LootItemCondition.CODEC.optionalFieldOf("player").forGetter(OutpostCampfireTrigger.Instance::player))
                .apply(instance, OutpostCampfireTrigger.Instance::new));

        public static Criterion<Instance> setSpawn() {
            return AetherIIAdvancementTriggers.OUTPOST_CAMPFIRE.createCriterion(new OutpostCampfireTrigger.Instance(Optional.empty()));
        }
    }
}