package com.aetherteam.aetherii.advancement.trigger;

import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.core.Holder;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Validatable;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Optional;

public class FeedMoaTrigger extends SimpleCriterionTrigger<FeedMoaTrigger.Instance> {
    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public void trigger(ServerPlayer player, ItemStack item, Entity entity) {
        LootContext lootcontext = EntityPredicate.createContext(player, entity);
        this.trigger(player, (instance) -> instance.matches(item, lootcontext));
    }

    public record Instance(Optional<Holder<LootItemCondition>> player, Optional<ItemPredicate> item, Optional<Holder<LootItemCondition>> entity) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<Instance> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                LootItemCondition.CODEC.optionalFieldOf("player").forGetter(Instance::player),
                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(Instance::item),
                LootItemCondition.CODEC.optionalFieldOf("entity").forGetter(Instance::entity)
        ).apply(instance, Instance::new));

        public static Criterion<Instance> itemUsedOnEntity(Optional<Holder<LootItemCondition>> player, ItemPredicate.Builder item, Optional<Holder<LootItemCondition>> entity) {
            return AetherIIAdvancementTriggers.FEED_MOA.createCriterion(new Instance(player, Optional.of(item.build()), entity));
        }

        public static Criterion<Instance> itemUsedOnEntity(ItemPredicate.Builder item, Optional<Holder<LootItemCondition>> entity) {
            return itemUsedOnEntity(Optional.empty(), item, entity);
        }

        public static Criterion<Instance> itemUsedOnEntity(ItemPredicate.Builder item) {
            return itemUsedOnEntity(Optional.empty(), item, Optional.empty());
        }

        public boolean matches(ItemStack item, LootContext lootContext) {
            return (this.item.isEmpty() || this.item.get().test(item)) && (this.entity.isEmpty() || this.entity.get().value().test(lootContext));
        }

        @Override
        public void validate(ValidationContextSource validator) {
            SimpleCriterionTrigger.SimpleInstance.super.validate(validator);
            Validatable.validateHolder(validator.entityContext(), "entity", this.entity);
        }
    }
}

