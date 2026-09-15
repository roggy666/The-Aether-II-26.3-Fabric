package com.aetherteam.aetherii.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.HitResult;

import java.util.Collection;
import java.util.List;

/**
 * Fabric events for the NeoForge game events The Aether II listens to. Each one is fired from a mixin at the same
 * point NeoForge fires its counterpart (see the {@code mixin.mixins.common} package).
 */
public final class AetherIIEvents {
    private AetherIIEvents() {
    }

    // ---- Ticks ----

    /** {@code PlayerTickEvent.Pre}: start of {@code Player#tick} (both sides). */
    public static final Event<PlayerTick> PLAYER_TICK_PRE = EventFactory.createArrayBacked(PlayerTick.class, callbacks -> player -> {
        for (PlayerTick callback : callbacks) callback.onPlayerTick(player);
    });

    /** {@code PlayerTickEvent.Post}: end of {@code Player#tick} (both sides). */
    public static final Event<PlayerTick> PLAYER_TICK_POST = EventFactory.createArrayBacked(PlayerTick.class, callbacks -> player -> {
        for (PlayerTick callback : callbacks) callback.onPlayerTick(player);
    });

    /** {@code EntityTickEvent.Pre}: start of {@code Entity#tick} (both sides). */
    public static final Event<EntityTick> ENTITY_TICK_PRE = EventFactory.createArrayBacked(EntityTick.class, callbacks -> entity -> {
        for (EntityTick callback : callbacks) callback.onEntityTick(entity);
    });

    /** {@code EntityTickEvent.Post}: end of {@code Entity#tick} (both sides). */
    public static final Event<EntityTick> ENTITY_TICK_POST = EventFactory.createArrayBacked(EntityTick.class, callbacks -> entity -> {
        for (EntityTick callback : callbacks) callback.onEntityTick(entity);
    });

    // ---- Living ----

    /** {@code LivingIncomingDamageEvent}: start of {@code LivingEntity#hurtServer}; the container can change the amount or cancel. */
    public static final Event<LivingDamage> LIVING_INCOMING_DAMAGE = EventFactory.createArrayBacked(LivingDamage.class, callbacks -> (entity, container) -> {
        for (LivingDamage callback : callbacks) callback.onDamage(entity, container);
    });

    /** {@code LivingDamageEvent.Pre}: start of {@code LivingEntity#actuallyHurt}, before armor/absorption. */
    public static final Event<LivingDamage> LIVING_DAMAGE_PRE = EventFactory.createArrayBacked(LivingDamage.class, callbacks -> (entity, container) -> {
        for (LivingDamage callback : callbacks) callback.onDamage(entity, container);
    });

    /** {@code LivingKnockBackEvent}: return {@code false} to cancel the knockback. */
    public static final Event<LivingKnockback> LIVING_KNOCKBACK = EventFactory.createArrayBacked(LivingKnockback.class, callbacks -> entity -> {
        for (LivingKnockback callback : callbacks) {
            if (!callback.allowKnockback(entity)) return false;
        }
        return true;
    });

    /** {@code LivingShieldBlockEvent}: fired after an attack was blocked by an item. */
    public static final Event<LivingShieldBlock> LIVING_SHIELD_BLOCK = EventFactory.createArrayBacked(LivingShieldBlock.class, callbacks -> (entity, source, blockedDamage) -> {
        for (LivingShieldBlock callback : callbacks) callback.onShieldBlock(entity, source, blockedDamage);
    });

    /** {@code LivingEntityUseItemEvent.Finish}. */
    public static final Event<LivingUseItemFinish> LIVING_USE_ITEM_FINISH = EventFactory.createArrayBacked(LivingUseItemFinish.class, callbacks -> (entity, stack) -> {
        for (LivingUseItemFinish callback : callbacks) callback.onFinishUsing(entity, stack);
    });

    /** {@code LivingDropsEvent}: item entities added to the collection are spawned after the vanilla drops. */
    public static final Event<LivingDrops> LIVING_DROPS = EventFactory.createArrayBacked(LivingDrops.class, callbacks -> (entity, source, drops) -> {
        for (LivingDrops callback : callbacks) callback.onDrops(entity, source, drops);
    });

    /** {@code LivingHealEvent}: return {@code false} to cancel healing. */
    public static final Event<LivingHeal> LIVING_HEAL = EventFactory.createArrayBacked(LivingHeal.class, callbacks -> (entity, amount) -> {
        for (LivingHeal callback : callbacks) {
            if (!callback.allowHeal(entity, amount)) return false;
        }
        return true;
    });

    /** {@code LivingEvent.LivingJumpEvent}. */
    public static final Event<LivingJump> LIVING_JUMP = EventFactory.createArrayBacked(LivingJump.class, callbacks -> entity -> {
        for (LivingJump callback : callbacks) callback.onJump(entity);
    });

    /** {@code LivingEvent.LivingVisibilityEvent}: multiplies the visibility of {@code entity} to {@code lookingEntity}. */
    public static final Event<LivingVisibility> LIVING_VISIBILITY = EventFactory.createArrayBacked(LivingVisibility.class, callbacks -> (entity, lookingEntity, visibility) -> {
        double result = visibility;
        for (LivingVisibility callback : callbacks) result = callback.modifyVisibility(entity, lookingEntity, result);
        return result;
    });

    /** {@code LivingFallEvent}: the container can change the fall distance/multiplier or cancel the fall damage. */
    public static final Event<LivingFall> LIVING_FALL = EventFactory.createArrayBacked(LivingFall.class, callbacks -> (entity, container) -> {
        for (LivingFall callback : callbacks) callback.onFall(entity, container);
    });

    /** {@code MobEffectEvent.Remove}: return {@code false} to keep the effect. */
    public static final Event<MobEffectRemove> MOB_EFFECT_REMOVE = EventFactory.createArrayBacked(MobEffectRemove.class, callbacks -> (entity, effect) -> {
        for (MobEffectRemove callback : callbacks) {
            if (!callback.allowRemove(entity, effect)) return false;
        }
        return true;
    });

    /** {@code LivingBreatheEvent}: return {@code false} if the entity can't breathe where it is. */
    public static final Event<LivingBreathe> LIVING_BREATHE = EventFactory.createArrayBacked(LivingBreathe.class, callbacks -> (entity, canBreathe) -> {
        boolean result = canBreathe;
        for (LivingBreathe callback : callbacks) result = callback.canBreathe(entity, result);
        return result;
    });

    /** {@code ArmorHurtEvent}: return {@code false} to skip damaging that armor piece. */
    public static final Event<ArmorHurt> ARMOR_HURT = EventFactory.createArrayBacked(ArmorHurt.class, callbacks -> (entity, source, armor) -> {
        for (ArmorHurt callback : callbacks) {
            if (!callback.allowArmorDamage(entity, source, armor)) return false;
        }
        return true;
    });

    // ---- Player ----

    /** {@code CriticalHitEvent}: fired for every player melee attack with the vanilla multiplier (1.5 for crits, 1.0 otherwise). */
    public static final Event<CriticalHit> CRITICAL_HIT = EventFactory.createArrayBacked(CriticalHit.class, callbacks -> (player, target, critical, multiplier) -> {
        for (CriticalHit callback : callbacks) callback.onCriticalHit(player, target, critical, multiplier);
    });

    /** {@code AdvancementEvent.AdvancementProgressEvent}. */
    public static final Event<AdvancementProgress> ADVANCEMENT_PROGRESS = EventFactory.createArrayBacked(AdvancementProgress.class, callbacks -> (player, advancement) -> {
        for (AdvancementProgress callback : callbacks) callback.onProgress(player, advancement);
    });

    // ---- Entity ----

    /** {@code EntityMountEvent}: return {@code false} to cancel mounting/dismounting. */
    public static final Event<EntityMount> ENTITY_MOUNT = EventFactory.createArrayBacked(EntityMount.class, callbacks -> (rider, mount, dismounting) -> {
        for (EntityMount callback : callbacks) {
            if (!callback.allowMount(rider, mount, dismounting)) return false;
        }
        return true;
    });

    /** {@code EntityTravelToDimensionEvent}: fired before an entity changes dimension; return {@code false} to cancel. */
    public static final Event<EntityTravelToDimension> ENTITY_TRAVEL_TO_DIMENSION = EventFactory.createArrayBacked(EntityTravelToDimension.class, callbacks -> (entity, dimension) -> {
        for (EntityTravelToDimension callback : callbacks) {
            if (!callback.allowTravel(entity, dimension)) return false;
        }
        return true;
    });

    /** {@code ExplosionEvent.Detonate}: the affected entity list is mutable. */
    public static final Event<ExplosionDetonate> EXPLOSION_DETONATE = EventFactory.createArrayBacked(ExplosionDetonate.class, callbacks -> (explosion, entities) -> {
        for (ExplosionDetonate callback : callbacks) callback.onDetonate(explosion, entities);
    });

    /** {@code ProjectileImpactEvent}. */
    public static final Event<ProjectileImpact> PROJECTILE_IMPACT = EventFactory.createArrayBacked(ProjectileImpact.class, callbacks -> (projectile, hitResult) -> {
        for (ProjectileImpact callback : callbacks) callback.onImpact(projectile, hitResult);
    });

    /** {@code MobSpawnEvent.SpawnPlacementCheck}: return {@code false} to fail the placement check. */
    public static final Event<SpawnPlacementCheck> SPAWN_PLACEMENT_CHECK = EventFactory.createArrayBacked(SpawnPlacementCheck.class, callbacks -> (type, level, pos) -> {
        for (SpawnPlacementCheck callback : callbacks) {
            if (!callback.checkSpawn(type, level, pos)) return false;
        }
        return true;
    });

    /** {@code EntityJoinLevelEvent} (both sides). */
    public static final Event<EntityJoinLevel> ENTITY_JOIN_LEVEL = EventFactory.createArrayBacked(EntityJoinLevel.class, callbacks -> (entity, level) -> {
        for (EntityJoinLevel callback : callbacks) callback.onJoin(entity, level);
    });

    // ---- Level ----

    /** {@code BlockEvent.NeighborNotifyEvent}: return {@code false} to cancel the neighbour updates. */
    public static final Event<NeighborNotify> NEIGHBOR_NOTIFY = EventFactory.createArrayBacked(NeighborNotify.class, callbacks -> (level, pos) -> {
        for (NeighborNotify callback : callbacks) {
            if (!callback.onNeighborNotify(level, pos)) return false;
        }
        return true;
    });

    // ---- Callbacks ----

    @FunctionalInterface
    public interface PlayerTick {
        void onPlayerTick(Player player);
    }

    @FunctionalInterface
    public interface EntityTick {
        void onEntityTick(Entity entity);
    }

    @FunctionalInterface
    public interface LivingDamage {
        void onDamage(LivingEntity entity, DamageContainer container);
    }

    @FunctionalInterface
    public interface LivingKnockback {
        boolean allowKnockback(LivingEntity entity);
    }

    @FunctionalInterface
    public interface LivingShieldBlock {
        void onShieldBlock(LivingEntity entity, DamageSource source, float blockedDamage);
    }

    @FunctionalInterface
    public interface LivingUseItemFinish {
        void onFinishUsing(LivingEntity entity, ItemStack stack);
    }

    @FunctionalInterface
    public interface LivingDrops {
        void onDrops(LivingEntity entity, DamageSource source, Collection<ItemEntity> drops);
    }

    @FunctionalInterface
    public interface LivingHeal {
        boolean allowHeal(LivingEntity entity, float amount);
    }

    @FunctionalInterface
    public interface LivingJump {
        void onJump(LivingEntity entity);
    }

    @FunctionalInterface
    public interface LivingVisibility {
        double modifyVisibility(LivingEntity entity, Entity lookingEntity, double visibility);
    }

    @FunctionalInterface
    public interface LivingFall {
        void onFall(LivingEntity entity, FallContainer container);
    }

    @FunctionalInterface
    public interface MobEffectRemove {
        boolean allowRemove(LivingEntity entity, Holder<MobEffect> effect);
    }

    @FunctionalInterface
    public interface LivingBreathe {
        boolean canBreathe(LivingEntity entity, boolean canBreathe);
    }

    @FunctionalInterface
    public interface ArmorHurt {
        boolean allowArmorDamage(LivingEntity entity, DamageSource source, ItemStack armor);
    }

    @FunctionalInterface
    public interface CriticalHit {
        void onCriticalHit(Player player, Entity target, boolean critical, float damageMultiplier);
    }

    @FunctionalInterface
    public interface AdvancementProgress {
        void onProgress(Player player, AdvancementHolder advancement);
    }

    @FunctionalInterface
    public interface EntityMount {
        boolean allowMount(Entity rider, Entity mount, boolean dismounting);
    }

    @FunctionalInterface
    public interface EntityTravelToDimension {
        boolean allowTravel(Entity entity, ResourceKey<Level> dimension);
    }

    @FunctionalInterface
    public interface ExplosionDetonate {
        void onDetonate(ServerExplosion explosion, List<Entity> affectedEntities);
    }

    @FunctionalInterface
    public interface ProjectileImpact {
        void onImpact(Projectile projectile, HitResult hitResult);
    }

    @FunctionalInterface
    public interface SpawnPlacementCheck {
        boolean checkSpawn(EntityType<?> type, ServerLevelAccessor level, BlockPos pos);
    }

    @FunctionalInterface
    public interface EntityJoinLevel {
        void onJoin(Entity entity, Level level);
    }

    @FunctionalInterface
    public interface NeighborNotify {
        boolean onNeighborNotify(LevelAccessor level, BlockPos pos);
    }

    /**
     * Mutable damage state shared by {@link #LIVING_INCOMING_DAMAGE} and {@link #LIVING_DAMAGE_PRE}.
     */
    public static final class DamageContainer {
        private final DamageSource source;
        private final float originalDamage;
        private float newDamage;
        private boolean cancelled;

        public DamageContainer(DamageSource source, float damage) {
            this.source = source;
            this.originalDamage = damage;
            this.newDamage = damage;
        }

        public DamageSource getSource() {
            return this.source;
        }

        public float getOriginalDamage() {
            return this.originalDamage;
        }

        public float getNewDamage() {
            return this.newDamage;
        }

        public void setNewDamage(float damage) {
            this.newDamage = damage;
        }

        public boolean isCanceled() {
            return this.cancelled;
        }

        public void setCanceled(boolean cancelled) {
            this.cancelled = cancelled;
        }
    }

    /**
     * Mutable fall state for {@link #LIVING_FALL}.
     */
    public static final class FallContainer {
        private double distance;
        private float damageMultiplier;
        private boolean cancelled;

        public FallContainer(double distance, float damageMultiplier) {
            this.distance = distance;
            this.damageMultiplier = damageMultiplier;
        }

        public double getDistance() {
            return this.distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public float getDamageMultiplier() {
            return this.damageMultiplier;
        }

        public void setDamageMultiplier(float damageMultiplier) {
            this.damageMultiplier = damageMultiplier;
        }

        public boolean isCanceled() {
            return this.cancelled;
        }

        public void setCanceled(boolean cancelled) {
            this.cancelled = cancelled;
        }
    }
}
