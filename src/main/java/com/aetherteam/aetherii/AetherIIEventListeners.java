package com.aetherteam.aetherii;

import com.aetherteam.aetherii.advancement.trigger.AetherIIAdvancementTriggers;
import com.aetherteam.aetherii.attachment.AetherIIDataAttachments;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.OrangeTreeBlock;
import com.aetherteam.aetherii.block.utility.BedrollBlock;
import com.aetherteam.aetherii.client.event.hooks.BiomeHooks;
import com.aetherteam.aetherii.data.resources.registries.AetherIIDamageTypes;
import com.aetherteam.aetherii.effect.buildup.EffectBuildupPresets;
import com.aetherteam.aetherii.entity.AetherIIEntityTypes;
import com.aetherteam.aetherii.entity.ExtraSpawnData;
import com.aetherteam.aetherii.event.AetherIIEvents;
import com.aetherteam.aetherii.event.FreezeEvent;
import com.aetherteam.aetherii.event.hooks.BlockHooks;
import com.aetherteam.aetherii.event.hooks.PlayerHooks;
import com.aetherteam.aetherii.item.components.AetherIIDataComponents;
import com.aetherteam.aetherii.network.packet.clientbound.ExtraSpawnDataPacket;
import com.aetherteam.aetherii.recipe.recipes.AetherIIRecipeTypes;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityLevelChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.EntityTrackingEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.Optional;

/**
 * Game event listeners. Vanilla/Fabric callbacks are used where they exist; everything else goes through
 * {@link AetherIIEvents}, which mirrors the NeoForge events this class listened to originally.
 */
public class AetherIIEventListeners {
    public static void register() {
        // Player
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> onPlayerLogin(handler.getPlayer()));
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> onPlayerLogout(handler.getPlayer()));
        ServerEntityEvents.ENTITY_LOAD.register((entity, level) -> onPlayerJoinLevel(entity));
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> onPlayerRespawn(newPlayer));
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> onPlayerClone(oldPlayer, newPlayer, !alive));
        ServerEntityLevelChangeEvents.AFTER_PLAYER_CHANGE_LEVEL.register((player, origin, destination) -> onPlayerChangedDimension(player, destination));
        AetherIIEvents.PLAYER_TICK_POST.register(AetherIIEventListeners::onPlayerPostTick);
        UseBlockCallback.EVENT.register(AetherIIEventListeners::onPlayerRightClickBlock);
        UseEntityCallback.EVENT.register(AetherIIEventListeners::onPlayerEntityInteractSpecific);
        AetherIIEvents.CRITICAL_HIT.register(AetherIIEventListeners::onPlayerCriticalHitAttack);
        AetherIIEvents.ADVANCEMENT_PROGRESS.register(AetherIIEventListeners::onPlayerAdvancementProgression);
        EntitySleepEvents.ALLOW_SETTING_SPAWN.register(AetherIIEventListeners::onPlayerSetSpawn);
        EntitySleepEvents.ALLOW_BED.register(AetherIIEventListeners::allowBedroll);
        EntitySleepEvents.ALLOW_SLEEPING.register(AetherIIEventListeners::canPlayerSleep);
        EntitySleepEvents.STOP_SLEEPING.register((entity, sleepingPos) -> onPlayerWakeUp(entity));
        AetherIIEvents.ARMOR_HURT.register(AetherIIEventListeners::onArmorDamaged);
        AetherIIEvents.ENTITY_MOUNT.register(AetherIIEventListeners::onPlayerMount);

        // Entity
        AetherIIEvents.ENTITY_TICK_POST.register(AetherIIEventListeners::onEntityPostTick);
        AetherIIEvents.SPAWN_PLACEMENT_CHECK.register(AetherIIEventListeners::onEntitySpawn);
        AetherIIEvents.ENTITY_TRAVEL_TO_DIMENSION.register(AetherIIEventListeners::onEntityTravelToDimension);
        AetherIIEvents.EXPLOSION_DETONATE.register(AetherIIEventListeners::onEntityCauseExplosion);
        AetherIIEvents.PROJECTILE_IMPACT.register(AetherIIEventListeners::onProjectileImpact);
        EntityTrackingEvents.START_TRACKING.register(AetherIIEventListeners::onStartTracking);

        // Living
        AetherIIEvents.LIVING_DAMAGE_PRE.register(AetherIIEventListeners::onLivingPreDamaged);
        AetherIIEvents.LIVING_KNOCKBACK.register(AetherIIEventListeners::onLivingKnockBack);
        AetherIIEvents.LIVING_SHIELD_BLOCK.register(AetherIIEventListeners::onLivingBlockAttack);
        AetherIIEvents.LIVING_USE_ITEM_FINISH.register(AetherIIEventListeners::onLivingItemUsed);
        AetherIIEvents.LIVING_DROPS.register(AetherIIEventListeners::onLivingDrops);
        AetherIIEvents.MOB_EFFECT_REMOVE.register(AetherIIEventListeners::onEffectRemove);
        AetherIIEvents.LIVING_BREATHE.register(AetherIIEventListeners::onBreatheInBlock);

        // Block
        PlayerBlockBreakEvents.BEFORE.register(AetherIIEventListeners::onBreakBlock);
        AetherIIEvents.NEIGHBOR_NOTIFY.register(AetherIIEventListeners::onBlockUpdateNeighbor);
        UseBlockCallback.EVENT.register(AetherIIEventListeners::onModifyBlock);
        FreezeEvent.FREEZE_FROM_BLOCK.register(AetherIIEventListeners::onBlockFreeze);

        // Level
        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register(AetherIIEventListeners::onDatapackSync);
    }

    public static void onPlayerLogin(ServerPlayer player) {
        player.getAttachedOrCreate(AetherIIDataAttachments.PLAYER).login(player);
        player.getAttachedOrCreate(AetherIIDataAttachments.AERBUNNY_MOUNT).login(player);
        player.getAttachedOrCreate(AetherIIDataAttachments.ABILITY_BEHAVIOR).login(player);
        player.getAttachedOrCreate(AetherIIDataAttachments.GUIDEBOOK_DISCOVERY).login(player);
        player.getAttachedOrCreate(AetherIIDataAttachments.OUTPOST_TRACKER).login(player);
        BiomeHooks.sendColors(player);
    }

    public static void onPlayerLogout(ServerPlayer player) {
        player.getAttachedOrCreate(AetherIIDataAttachments.PLAYER).logout(player);
        player.getAttachedOrCreate(AetherIIDataAttachments.ABILITY_BEHAVIOR).logout(player);
    }

    public static void onPlayerJoinLevel(Entity entity) {
        if (entity instanceof Player player) {
            player.getAttachedOrCreate(AetherIIDataAttachments.PLAYER).onJoinLevel(player);
            player.getAttachedOrCreate(AetherIIDataAttachments.DAMAGE_SYSTEM).onJoinLevel(player);
            player.getAttachedOrCreate(AetherIIDataAttachments.ABILITY_BEHAVIOR).onJoinLevel(player);
        }
    }

    public static void onPlayerRespawn(Player player) {
        player.getAttachedOrCreate(AetherIIDataAttachments.OUTPOST_TRACKER).respawn(player);
    }

    public static void onPlayerClone(Player original, Player player, boolean wasDeath) {
        player.getAttachedOrCreate(AetherIIDataAttachments.GUIDEBOOK_DISCOVERY).clone(player);
    }

    public static void onPlayerChangedDimension(Player player, ServerLevel destination) {
        player.getAttachedOrCreate(AetherIIDataAttachments.PLAYER).changeDimension(player, destination.dimension());
        player.getAttachedOrCreate(AetherIIDataAttachments.ABILITY_BEHAVIOR).changeDimension(player);
        player.getAttachedOrCreate(AetherIIDataAttachments.AERBUNNY_MOUNT).remountAerbunny(player);
    }

    public static void onPlayerPostTick(Player player) {
        player.getAttachedOrCreate(AetherIIDataAttachments.PLAYER).postTickUpdate(player);
        player.getAttachedOrCreate(AetherIIDataAttachments.AERBUNNY_MOUNT).postTickUpdate(player);
        player.getAttachedOrCreate(AetherIIDataAttachments.SWET_LATCH).postTickUpdate(player);
        player.getAttachedOrCreate(AetherIIDataAttachments.ABILITY_BEHAVIOR).postTickUpdate(player);
        player.getAttachedOrCreate(AetherIIDataAttachments.GUIDEBOOK_DISCOVERY).postTickUpdate(player);
        PlayerHooks.forceSpecialLoadingCrouch(player);
        PlayerHooks.mountAercloudEffects(player);
    }

    public static InteractionResult onPlayerRightClickBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockPos pos = hitResult.getBlockPos();
        Direction face = hitResult.getDirection();
        boolean cancelled = false;

        cancelled = PlayerHooks.playerActivatePortal(player, level, pos, face, itemStack, hand, cancelled);
        cancelled = PlayerHooks.cancelPlacementOnAercloud(player, level, pos, itemStack, cancelled);
        cancelled = PlayerHooks.snowlogBlock(player, level, pos, itemStack, hand, cancelled);
        cancelled = PlayerHooks.ferrositeMudBottleConversion(player, level, pos, itemStack, hand, face, cancelled);
        cancelled = PlayerHooks.interactWithMimicContainer(level, pos, cancelled);

        return cancelled ? InteractionResult.FAIL : InteractionResult.PASS;
    }

    public static InteractionResult onPlayerEntityInteractSpecific(Player player, Level level, InteractionHand interactionHand, Entity targetEntity, EntityHitResult hitResult) {
        Optional<InteractionResult> result = Optional.empty();

        PlayerHooks.milkWithSkyrootBucket(targetEntity, player, interactionHand);
        PlayerHooks.feedCarrionSprout(level, targetEntity, player, interactionHand);
        PlayerHooks.useGoldenWyndberry(targetEntity, player, interactionHand);

        result = PlayerHooks.pickupBucketableTarget(targetEntity, player, interactionHand, result);

        return result.orElse(InteractionResult.PASS);
    }

    public static void onPlayerCriticalHitAttack(Player player, Entity target, boolean critical, float modifier) {
        player.getAttachedOrCreate(AetherIIDataAttachments.DAMAGE_SYSTEM).setCriticalDamageModifier(modifier);
    }

    public static void onPlayerAdvancementProgression(Player player, net.minecraft.advancements.AdvancementHolder advancementHolder) {
        player.getAttachedOrCreate(AetherIIDataAttachments.GUIDEBOOK_DISCOVERY).progressAdvancement(player, advancementHolder);
    }

    public static boolean onPlayerSetSpawn(Player player, BlockPos pos) {
        return !PlayerHooks.cancelBedrollSpawn(player, pos);
    }

    /**
     * NeoForge's {@code IBlockExtension#isBed} for the bedroll.
     */
    public static net.fabricmc.fabric.api.util.EventResult allowBedroll(LivingEntity entity, BlockPos sleepingPos, BlockState state, boolean vanillaResult) {
        return state.getBlock() instanceof BedrollBlock ? net.fabricmc.fabric.api.util.EventResult.ALLOW : net.fabricmc.fabric.api.util.EventResult.PASS;
    }

    public static Player.BedSleepingProblem canPlayerSleep(Player player, BlockPos pos) {
        if (player instanceof ServerPlayer serverPlayer) {
            Level level = player.level();
            BlockState state = level.getBlockState(pos);
            return PlayerHooks.handleBedrollSleeping(serverPlayer, level, pos, state, null);
        }
        return null;
    }

    public static void onPlayerWakeUp(LivingEntity entity) {
        if (entity instanceof Player player) {
            PlayerHooks.breakBedrollAfterSleeping(player);
        }
    }

    public static boolean onArmorDamaged(LivingEntity livingEntity, net.minecraft.world.damagesource.DamageSource source, ItemStack armor) {
        if (source != null && source.is(AetherIIDamageTypes.ALKAHEST) && armor.has(AetherIIDataComponents.REINFORCEMENT_TIER)) {
            return false;
        }
        return true;
    }

    public static boolean onPlayerMount(Entity riderEntity, Entity mountEntity, boolean isDismounting) {
        return !PlayerHooks.dismountPrevention(riderEntity, mountEntity, isDismounting);
    }

    public static void onEntityPostTick(Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.getAttachedOrCreate(AetherIIDataAttachments.DAMAGE_SYSTEM).postTickUpdate(livingEntity);
            livingEntity.getAttachedOrCreate(AetherIIDataAttachments.EFFECTS_SYSTEM).postTickUpdate(livingEntity);
            livingEntity.getAttachedOrCreate(AetherIIDataAttachments.ACCESSORIES).postTickUpdate(livingEntity);
        }
    }

    public static boolean onEntitySpawn(EntityType<?> type, net.minecraft.world.level.ServerLevelAccessor level, BlockPos pos) {
        ServerLevel serverLevel = level.getLevel();
        StructureManager structureManager = serverLevel.structureManager();
        Registry<Structure> structureRegistry = serverLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE);

        if (!type.builtInRegistryHolder().is(AetherIITags.EntityTypes.DUNGEON_MOBS)) {
            for (Holder<Structure> structure : structureRegistry.getTagOrEmpty(AetherIITags.Structures.DUNGEONS)) {
                StructureStart structureStart = structureManager.getStructureAt(pos, structure.value());
                if (structureStart.isValid() && structureManager.structureHasPieceAt(pos, structureStart)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean onEntityTravelToDimension(Entity entity, net.minecraft.resources.ResourceKey<Level> dimension) {
        if (entity instanceof Player player && !player.level().dimension().equals(dimension)) {
            player.getAttachedOrCreate(AetherIIDataAttachments.AERBUNNY_MOUNT).removeAerbunny();
        }
        return true;
    }

    public static void onEntityCauseExplosion(net.minecraft.world.level.ServerExplosion explosion, java.util.List<Entity> affectedEntities) {
        Entity directSource = explosion.getDirectSourceEntity();
        Entity indirectSource = explosion.getIndirectSourceEntity();

        if (indirectSource != null && (indirectSource.getType() == AetherIIEntityTypes.DETONATION_SENTRY || indirectSource.getType() == AetherIIEntityTypes.SENTRY_GOLEM)) {
            affectedEntities.removeIf((entity) -> entity instanceof ItemEntity);
            affectedEntities.forEach((entity) -> {
                if (entity instanceof LivingEntity livingEntity) {
                    if (!livingEntity.isBlocking()) {
                        livingEntity.getAttachedOrCreate(AetherIIDataAttachments.EFFECTS_SYSTEM).addBuildup(livingEntity, indirectSource, directSource, EffectBuildupPresets.STUN, 150);
                    }
                }
            });
        }
    }

    public static void onProjectileImpact(net.minecraft.world.entity.projectile.Projectile projectile, net.minecraft.world.phys.HitResult hitResult) {
        if (hitResult instanceof EntityHitResult entityHitResult) {
            if (entityHitResult.getEntity() instanceof Player player) {
                player.getAttachedOrCreate(AetherIIDataAttachments.PLAYER).stickProjectile(projectile, player);
            }
        }
    }

    /**
     * Sends {@link ExtraSpawnData} (NeoForge's {@code IEntityWithComplexSpawn}) to players that start tracking an entity.
     */
    public static void onStartTracking(Entity entity, ServerPlayer player) {
        if (entity instanceof ExtraSpawnData && ServerPlayNetworking.canSend(player, ExtraSpawnDataPacket.TYPE)) {
            ServerPlayNetworking.send(player, ExtraSpawnDataPacket.create(entity));
        }
    }

    public static void onLivingPreDamaged(LivingEntity target, AetherIIEvents.DamageContainer container) {
        float damage = target.getAttachedOrCreate(AetherIIDataAttachments.DAMAGE_SYSTEM).getDamageTypeModifiedValue(target, container.getSource(), container.getNewDamage());
        container.setNewDamage(damage);
    }

    public static boolean onLivingKnockBack(LivingEntity livingEntity) {
        return !livingEntity.getAttachedOrCreate(AetherIIDataAttachments.DAMAGE_SYSTEM).cancelKnockback(livingEntity);
    }

    public static void onLivingBlockAttack(LivingEntity livingEntity, net.minecraft.world.damagesource.DamageSource source, float blockedDamage) {
        livingEntity.getAttachedOrCreate(AetherIIDataAttachments.DAMAGE_SYSTEM).buildUpShieldStun(livingEntity, source.getEntity(), blockedDamage);
    }

    public static void onLivingItemUsed(LivingEntity entity, ItemStack itemStack) {
        if (entity instanceof Player player) {
            PlayerHooks.valkyrieTeaAbility(player, itemStack);
        }
    }

    public static void onLivingDrops(LivingEntity entity, net.minecraft.world.damagesource.DamageSource source, java.util.Collection<ItemEntity> drops) {
        entity.getAttachedOrCreate(AetherIIDataAttachments.ACCESSORIES).dropItems(entity, drops);
        if (entity instanceof Player player) {
            player.getAttachedOrCreate(AetherIIDataAttachments.CURRENCY).dropAll(player, drops);
        }
    }

    public static boolean onEffectRemove(LivingEntity livingEntity, Holder<net.minecraft.world.effect.MobEffect> effect) {
        ItemStack useItem = livingEntity.getUseItem();
        return !(effect.is(AetherIITags.MobEffects.MILK_DOESNT_CLEAR) && (useItem.is(ConventionalItemTags.MILK_BUCKETS) || useItem.is(ConventionalItemTags.MILK_DRINKS)));
    }

    public static boolean onBreatheInBlock(LivingEntity entity, boolean canBreathe) {
        return canBreathe && BlockHooks.canBreathe(entity);
    }

    public static boolean onBreakBlock(Level level, Player player, BlockPos pos, BlockState state, net.minecraft.world.level.block.entity.BlockEntity blockEntity) {
        ItemStack stack = player.getMainHandItem();

        PlayerHooks.interactWithMimicContainer(level, pos, false);
        if (player instanceof ServerPlayer serverPlayer) {
            AetherIIAdvancementTriggers.ITEM_BREAK_BLOCK.trigger(serverPlayer, pos, stack);
        }
        // NeoForge's OrangeTreeBlock#onDestroyedByPlayer: harvesting the ripe top keeps the block
        return !(state.getBlock() instanceof OrangeTreeBlock && OrangeTreeBlock.keepsBlockOnBreak(state));
    }

    public static boolean onBlockUpdateNeighbor(net.minecraft.world.level.LevelAccessor levelAccessor, BlockPos blockPos) {
        boolean cancelled = false;

        BlockHooks.sendIcestoneFreezableUpdateEvent(levelAccessor, blockPos);

        cancelled = BlockHooks.activatePortalFromBlockUpdate(levelAccessor, blockPos, cancelled);

        return !cancelled;
    }

    /**
     * NeoForge's {@code BlockToolModificationEvent}: extra drops when stripping mossy wisproot / amberoot deposits with an
     * axe. The block conversions themselves are registered through the Fabric registries in {@link AetherIIBlocks}.
     */
    public static InteractionResult onModifyBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(ItemTags.AXES) && !player.isSpectator()) {
            BlockState oldState = level.getBlockState(hitResult.getBlockPos());
            UseOnContext context = new UseOnContext(player, hand, hitResult);
            BlockHooks.stripMossyWisproot(level, oldState, itemStack, context);
            BlockHooks.stripAmberoot(level, oldState, itemStack, context);
        }
        return InteractionResult.PASS;
    }

    public static boolean onBlockFreeze(FreezeEvent.FreezeFromBlock event) {
        net.minecraft.world.level.LevelAccessor level = event.getLevel();
        BlockPos sourcePos = event.getSourcePos();
        BlockPos pos = event.getPos();
        boolean cancelled = false;

        cancelled = BlockHooks.preventBlockFreezing(level, sourcePos, pos, cancelled);

        return cancelled;
    }

    public static void onDatapackSync(ServerPlayer player, boolean joined) {
        // NeoForge's OnDatapackSyncEvent#sendRecipes: the client needs these recipe types for the guidebook and integrations
        AetherIIRecipeTypes.syncRecipes(player);
    }
}
