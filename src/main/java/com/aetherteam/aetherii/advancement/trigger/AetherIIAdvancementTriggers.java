package com.aetherteam.aetherii.advancement.trigger;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class AetherIIAdvancementTriggers {
    private static <T extends CriterionTrigger<?>> T register(String name, T trigger) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, Identifier.fromNamespaceAndPath(AetherII.MODID, name), trigger);
    }

    public static final ItemBreakBlockTrigger ITEM_BREAK_BLOCK = register("item_break_block", new ItemBreakBlockTrigger());
    public static final FallOnGroundTrigger FALL_ON_GROUND = register("fall_on_ground", new FallOnGroundTrigger());
    public static final OutpostCampfireTrigger OUTPOST_CAMPFIRE = register("outpost_campfire", new OutpostCampfireTrigger());
    public static final CurrencyTrigger CURRENCY = register("currency", new CurrencyTrigger());
    public static final SleptInBedrollTrigger SLEPT_IN_BEDROLL = register("slept_in_bedroll", new SleptInBedrollTrigger());
    public static final IncubationTrigger INCUBATION = register("incubation", new IncubationTrigger());
    public static final FeedMoaTrigger FEED_MOA = register("feed_moa", new FeedMoaTrigger());
    public static final EffectBuildupTrigger EFFECT_BUILDUP = register("effect_buildup", new EffectBuildupTrigger());
    public static final ForgingCharmTrigger FORGING_CHARM = register("forging_charm", new ForgingCharmTrigger());

    public static void init() {}
}