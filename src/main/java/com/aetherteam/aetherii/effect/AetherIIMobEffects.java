package com.aetherteam.aetherii.effect;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.effect.beneficial.*;
import com.aetherteam.aetherii.effect.harmful.*;
import com.aetherteam.aetherii.entity.attributes.AetherIIAttributes;
import com.aetherteam.aetherii.event.AetherIIEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AetherIIMobEffects {
        private static Holder.Reference<MobEffect> register(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(AetherII.MODID, name), effect);
    }

    public static void init() {}

    // Beneficial
    public static final Holder.Reference<MobEffect> SATURATION_BOOST = register("saturation_boost", new SaturationBoostEffect()
            .addAttributeModifier(AetherIIAttributes.SATURATION_BOOST, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.saturation_boost.double_saturation"), 1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder.Reference<MobEffect> NATURAL_CAMOUFLAGE = register("natural_camouflage", new NaturalCamouflageEffect());
    public static final Holder.Reference<MobEffect> HEALING_OVERFLOW = register("healing_overflow", new HealingOverflowEffect()
            .addAttributeModifier(Attributes.MAX_ABSORPTION, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.healing_overflow.bonus_absorption"), 8.0F, AttributeModifier.Operation.ADD_VALUE));

    // Harmful
    public static final Holder.Reference<MobEffect> VULNERABILITY = register("vulnerability", new VulnerabilityEffect()
            .addAttributeModifier(AetherIIAttributes.SLASH_RESISTANCE, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.vulnerability.slash_weakness"), -1.0F, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(AetherIIAttributes.IMPACT_RESISTANCE, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.vulnerability.impact_weakness"), -1.0F, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(AetherIIAttributes.PIERCE_RESISTANCE, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.vulnerability.pierce_weakness"), -1.0F, AttributeModifier.Operation.ADD_VALUE));
    public static final Holder.Reference<MobEffect> WOUND = register("wound", new WoundEffect());
    public static final Holder.Reference<MobEffect> STUN = register("stun", new StunEffect());
    public static final Holder.Reference<MobEffect> FRACTURE = register("fracture", new FractureEffect()
            .addAttributeModifier(Attributes.JUMP_STRENGTH, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.fracture.jump_hinder"), -0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.fracture.fall_increase"), -2.0F, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(AetherIIAttributes.SLASH_RESISTANCE, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.fracture.slash_weakness"), 0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
            .addAttributeModifier(AetherIIAttributes.IMPACT_RESISTANCE, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.fracture.impact_weakness"), 0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
            .addAttributeModifier(AetherIIAttributes.PIERCE_RESISTANCE, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.fracture.pierce_weakness"), 0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static final Holder.Reference<MobEffect> AMBROSIUM_POISONING = register("ambrosium_poisoning", new AmbrosiumPoisoningEffect());
    public static final Holder.Reference<MobEffect> TOXIN = register("toxin", new ToxinEffect());
    public static final Holder.Reference<MobEffect> VENOM = register("venom", new VenomEffect());
    public static final Holder.Reference<MobEffect> CHARGED = register("charged", new ChargedEffect());
    public static final Holder.Reference<MobEffect> WEBBED = register("webbed", new WebbedEffect()
            .addAttributeModifier(Attributes.JUMP_STRENGTH, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.webbed.jump_hinder"), -0.9F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder.Reference<MobEffect> IMMOLATION = register("immolation", new ImmolationEffect());
    public static final Holder.Reference<MobEffect> FROSTBITE = register("frostbite", new FrostbiteEffect()
            .addAttributeModifier(Attributes.JUMP_STRENGTH, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.frostbite.jump_hinder"), -0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.frostbite.mining_fatigue"), -0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder.Reference<MobEffect> FUNGAL_ROT = register("fungal_rot", new FungalRotEffect()); //todo
    public static final Holder.Reference<MobEffect> CRYSTALLIZED = register("crystallized", new CrystallizedEffect()); //todo

    public static final Holder.Reference<MobEffect> ELECTRIC_SHOCK = register("electric_shock", new ElectricShockEffect());
    public static final Holder.Reference<MobEffect> CARRION_TRAP = register("carrion_trap", new CarrionPullEffect()
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.carrion_pull.slowness"), -0.8F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.JUMP_STRENGTH, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.carrion_pull.jump_hinder"), -0.8F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.carrion_pull.knockback_resistance"), 1.0F, AttributeModifier.Operation.ADD_VALUE));
    public static final Holder.Reference<MobEffect> GRAVITATIONAL_PULL = register("gravitational_pull", new GravitationalPullEffect()
            .addAttributeModifier(Attributes.GRAVITY, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.gravitational_pull.gravity"), 2.0F, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.fromNamespaceAndPath(AetherII.MODID, "effect.gravitational_pull.slowness"), -0.375F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    /**
     * NeoForge event-bus listeners of the effects, on the Fabric callbacks / {@link AetherIIEvents}.
     */
    public static void registerUniqueBehaviors() {
        AetherIIEvents.LIVING_HEAL.register(AmbrosiumPoisoningEffect::preventHealing);

        AetherIIEvents.ENTITY_TICK_POST.register(FractureEffect::onEntityPostTick);

        AetherIIEvents.ENTITY_TICK_POST.register(FrostbiteEffect::onEntityPostTick);

        AetherIIEvents.ENTITY_TICK_POST.register(ImmolationEffect::onEntityPostTick);

        AetherIIEvents.ENTITY_TICK_POST.register(StunEffect::onEntityPostTick);
        AttackEntityCallback.EVENT.register(StunEffect::disableAttacks);
        AetherIIEvents.LIVING_INCOMING_DAMAGE.register(StunEffect::disableDamage);
        // PlayerInteractEvent.EntityInteractSpecific / EntityInteract
        UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> StunEffect.isStunned(player) ? InteractionResult.FAIL : InteractionResult.PASS);
        // PlayerInteractEvent.RightClickBlock
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> StunEffect.isStunned(player) ? InteractionResult.FAIL : InteractionResult.PASS);
        // PlayerInteractEvent.RightClickItem
        UseItemCallback.EVENT.register((player, level, hand) -> StunEffect.isStunned(player) ? InteractionResult.FAIL : InteractionResult.PASS);
        // PlayerInteractEvent.LeftClickBlock
        AttackBlockCallback.EVENT.register((player, level, hand, pos, direction) -> StunEffect.isStunned(player) ? InteractionResult.FAIL : InteractionResult.PASS);

        AetherIIEvents.ENTITY_TICK_POST.register(WebbedEffect::onEntityPostTick);
        AetherIIEvents.LIVING_JUMP.register(WebbedEffect::reduceByJumping);

        AetherIIEvents.ENTITY_TICK_POST.register(NaturalCamouflageEffect::onEntityPostTick);
        AetherIIEvents.LIVING_VISIBILITY.register(NaturalCamouflageEffect::adjustVisibilityModifier);
    }
}
