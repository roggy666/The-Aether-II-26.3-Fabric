package com.aetherteam.aetherii.loot.functions;

import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.Validatable;
import java.util.Optional;
import net.minecraft.core.Holder;
import com.aetherteam.aetherii.entity.monster.Swet;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;


public class GelDropsFunction extends LootItemConditionalFunction {
    public static final MapCodec<GelDropsFunction> CODEC = RecordCodecBuilder.mapCodec((instance) -> commonFields(instance)
            .and(ContextIntProviders.CODEC.fieldOf("count").forGetter((function) -> function.value))
            .apply(instance, GelDropsFunction::new));
    private final Holder<ContextIntProvider> value;

    private GelDropsFunction(Optional<Holder<LootItemCondition>> conditions, Holder<ContextIntProvider> value) {
        super(conditions);
        this.value = value;
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        Entity entity = context.getOptional(LootContextParams.THIS_ENTITY);
        if (entity instanceof Swet swet) {
            if (swet.isWaterDamaged()) {
                stack.setCount(0);
            } else if (swet.getSwetScale() > 0.95F) {
                stack.setCount(stack.getCount() + this.value.value().getInt(context));
            }
        }
        return stack;
    }

    public static LootItemConditionalFunction.Builder<?> extra(Holder<ContextIntProvider> countValue) {
        return simpleBuilder((conditions) -> new GelDropsFunction(conditions, countValue));
    }

    @Override
    public void validate(ValidationContext context) {
        super.validate(context);
        Validatable.validateHolder(context, "count", this.value);
    }

    @Override
    public MapCodec<? extends LootItemConditionalFunction> codec() {
        return CODEC;
    }

}
