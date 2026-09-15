package com.aetherteam.aetherii.entity.attributes;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.effect.AetherIIMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import java.util.function.Supplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class AetherIIAttributes {
        private static Holder.Reference<Attribute> register(String name, Supplier<Attribute> supplier) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), supplier.get());
    }

    public static void init() {}

    public static final Holder.Reference<Attribute> SLASH_DAMAGE = register("slash_damage", () -> new BaseRangedAttribute("attributes.aether_ii.slash_damage", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> IMPACT_DAMAGE = register("impact_damage", () -> new BaseRangedAttribute("attributes.aether_ii.impact_damage", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> PIERCE_DAMAGE = register("pierce_damage", () -> new BaseRangedAttribute("attributes.aether_ii.pierce_damage", 0.0, 0.0, 1024.0));

    public static final Holder.Reference<Attribute> SLASH_RANGED_DAMAGE = register("slash_ranged_damage", () -> new BaseRangedAttribute("attributes.aether_ii.slash_ranged_damage", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> IMPACT_RANGED_DAMAGE = register("impact_ranged_damage", () -> new BaseRangedAttribute("attributes.aether_ii.impact_ranged_damage", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> PIERCE_RANGED_DAMAGE = register("pierce_ranged_damage", () -> new BaseRangedAttribute("attributes.aether_ii.pierce_ranged_damage", 0.0, 0.0, 1024.0));

    public static final Holder.Reference<Attribute> SLASH_RESISTANCE = register("slash_resistance", () -> new RangedAttribute("attributes.aether_ii.slash_resistance", 0.0, -1024.0, 1024.0));
    public static final Holder.Reference<Attribute> IMPACT_RESISTANCE = register("impact_resistance", () -> new RangedAttribute("attributes.aether_ii.impact_resistance", 0.0, -1024.0, 1024.0));
    public static final Holder.Reference<Attribute> PIERCE_RESISTANCE = register("pierce_resistance", () -> new RangedAttribute("attributes.aether_ii.pierce_resistance", 0.0, -1024.0, 1024.0));

    public static final Holder.Reference<Attribute> SWEEP_RANGE = register("sweep_range", () -> new BaseRangedAttribute("attributes.aether_ii.sweep_range", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> SWEEP_KNOCKBACK = register("sweep_knockback", () -> new RangedAttribute("attributes.aether_ii.sweep_knockback", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> SWEEP_DAMAGE = register("sweep_damage", () -> new RangedAttribute("attributes.aether_ii.sweep_damage", 0.0, 0.0, 1024.0));

    public static final Holder.Reference<Attribute> SHOCK_RANGE = register("shock_range", () -> new BaseRangedAttribute("attributes.aether_ii.shock_range", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> SHOCK_KNOCKBACK = register("shock_knockback", () -> new RangedAttribute("attributes.aether_ii.shock_knockback", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> SHOCK_DAMAGE = register("shock_damage", () -> new RangedAttribute("attributes.aether_ii.shock_damage", 0.0, 0.0, 1024.0));

    public static final Holder.Reference<Attribute> STAB_RADIUS = register("stab_radius", () -> new BaseRangedAttribute("attributes.aether_ii.stab_radius", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> STAB_DISTANCE = register("stab_distance", () -> new BaseRangedAttribute("attributes.aether_ii.stab_distance", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> STAB_KNOCKBACK = register("stab_knockback", () -> new RangedAttribute("attributes.aether_ii.stab_knockback", 0.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> STAB_DAMAGE = register("stab_damage", () -> new RangedAttribute("attributes.aether_ii.stab_damage", 0.0, 0.0, 1024.0));

    public static final Holder.Reference<Attribute> MAXIMUM_ENDURANCE = register("maximum_endurance", () -> new RangedAttribute("attributes.aether_ii.maximum_endurance", 100.0, 100.0, 1000.0).setSyncable(true));
    public static final Holder.Reference<Attribute> ENDURANCE_RECOVERY = register("endurance_recovery", () -> new RangedAttribute("attributes.aether_ii.endurance_recovery", 0.3, 0.3, 500.0).setSyncable(true));

    public static final Holder.Reference<Attribute> BLOCKING_STRENGTH = register("blocking_strength", () -> new RangedAttribute("attributes.aether_ii.blocking_strength", 0.0, 0.0, 1.0).setSyncable(true));

    public static final Holder.Reference<Attribute> WOUND_EFFECT_RESISTANCE = register("wound_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.WOUND, "attributes.aether_ii.wound_effect_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> STUN_EFFECT_RESISTANCE = register("stun_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.STUN, "attributes.aether_ii.stun_effect_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> FRACTURE_EFFECT_RESISTANCE = register("fracture_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.FRACTURE, "attributes.aether_ii.fracture_effect_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> AMBROSIUM_POISONING_EFFECT_RESISTANCE = register("ambrosium_poisoning_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.AMBROSIUM_POISONING, "attributes.aether_ii.ambrosium_poisoning_effect_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> TOXIN_EFFECT_RESISTANCE = register("toxin_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.TOXIN, "attributes.aether_ii.toxin_effect_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> VENOM_EFFECT_RESISTANCE = register("venom_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.VENOM, "attributes.aether_ii.venom_effect_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> CHARGED_EFFECT_RESISTANCE = register("charged_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.CHARGED, "attributes.aether_ii.charged_effect_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> WEBBED_EFFECT_RESISTANCE = register("webbed_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.WEBBED, "attributes.aether_ii.webbed_effect_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> IMMOLATION_EFFECT_RESISTANCE = register("immolation_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.IMMOLATION, "attributes.aether_ii.immolation_effect_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> FROSTBITE_EFFECT_RESISTANCE = register("frostbite_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.FROSTBITE, "attributes.aether_ii.frostbite_effect_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> FUNGAL_ROT_EFFECT_RESISTANCE = register("fungal_rot_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.FUNGAL_ROT, "attributes.aether_ii.fungal_rot_resistance", 0.0, -10.0, 10.0));
    public static final Holder.Reference<Attribute> CRYSTALLIZED_EFFECT_RESISTANCE = register("crystallized_effect_resistance", () -> new EffectResistanceAttribute(AetherIIMobEffects.CRYSTALLIZED, "attributes.aether_ii.crystallized_resistance", 0.0, -10.0, 10.0));

    public static final Holder.Reference<Attribute> SATURATION_BOOST = register("saturation_boost", () -> new BaseRangedAttribute("attributes.aether_ii.saturation_boost", 1.0, 0.0, 1024.0));
    public static final Holder.Reference<Attribute> MOA_STAMINA = register("moa_stamina", () -> new BaseRangedAttribute("attributes.aether_ii.moa_stamina", 5.0, 3.0, 1024.0));
    public static final Holder.Reference<Attribute> MOA_STRENGTH = register("moa_strength", () -> new BaseRangedAttribute("attributes.aether_ii.moa_strength", 1.0, 1.0, 1024.0));
    public static final Holder.Reference<Attribute> MOA_SPEED = register("moa_speed", () -> new BaseRangedAttribute("attributes.aether_ii.moa_speed", 1.0, 0.0, 1024.0));

    public static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.MODIFY.register(context -> {
            context.modify(EntityTypes.PLAYER, (entityType, builder) -> {
                builder.add(SLASH_DAMAGE, 0.0);
                builder.add(IMPACT_DAMAGE, 0.0);
                builder.add(PIERCE_DAMAGE, 0.0);

                builder.add(SLASH_RANGED_DAMAGE, 0.0);
                builder.add(IMPACT_RANGED_DAMAGE, 0.0);
                builder.add(PIERCE_RANGED_DAMAGE, 0.0);

                builder.add(SWEEP_RANGE, 0.0);
                builder.add(SWEEP_KNOCKBACK, 0.4);
                builder.add(SWEEP_DAMAGE, 1.0);

                builder.add(SHOCK_RANGE, 0.0);
                builder.add(SHOCK_KNOCKBACK, 1.0);
                builder.add(SHOCK_DAMAGE, 0.1);

                builder.add(STAB_RADIUS, 0.0);
                builder.add(STAB_DISTANCE, 0.0);
                builder.add(STAB_KNOCKBACK, 0.2);
                builder.add(STAB_DAMAGE, 2.0);

                builder.add(MAXIMUM_ENDURANCE, 100.0);
                builder.add(ENDURANCE_RECOVERY, 0.3);
                builder.add(BLOCKING_STRENGTH, 0.0);

                builder.add(WOUND_EFFECT_RESISTANCE, 0.0);
                builder.add(STUN_EFFECT_RESISTANCE, 0.0);
                builder.add(FRACTURE_EFFECT_RESISTANCE, 0.0);
                builder.add(AMBROSIUM_POISONING_EFFECT_RESISTANCE, 0.0);
                builder.add(TOXIN_EFFECT_RESISTANCE, 0.0);
                builder.add(VENOM_EFFECT_RESISTANCE, 0.0);
                builder.add(CHARGED_EFFECT_RESISTANCE, 0.0);
                builder.add(WEBBED_EFFECT_RESISTANCE, 0.0);
                builder.add(IMMOLATION_EFFECT_RESISTANCE, 0.0);
                builder.add(FROSTBITE_EFFECT_RESISTANCE, 0.0);
                builder.add(FUNGAL_ROT_EFFECT_RESISTANCE, 0.0);
                builder.add(CRYSTALLIZED_EFFECT_RESISTANCE, 0.0);

                builder.add(SATURATION_BOOST, 1.0);
            });
        });
    }

    public static int getMaxEndurance(LivingEntity entity) {
        if (entity.getAttribute(MAXIMUM_ENDURANCE) != null) {
            return (int) entity.getAttributeValue(MAXIMUM_ENDURANCE);
        } else {
            return (int) MAXIMUM_ENDURANCE.value().getDefaultValue();
        }
    }
}
