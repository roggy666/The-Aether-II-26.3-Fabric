package com.aetherteam.aetherii.entity;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIIStats;
import com.aetherteam.aetherii.data.resources.AetherIIMobCategory;
import com.aetherteam.aetherii.data.resources.registries.AetherIIEntityIds;
import com.aetherteam.aetherii.entity.block.HoveringBlockEntity;
import com.aetherteam.aetherii.entity.block.SittableEntity;
import com.aetherteam.aetherii.entity.monster.*;
import com.aetherteam.aetherii.entity.monster.dungeon.DetonationSentry;
import com.aetherteam.aetherii.entity.monster.dungeon.Mimic;
import com.aetherteam.aetherii.entity.monster.dungeon.SentryGolem;
import com.aetherteam.aetherii.entity.monster.dungeon.boss.Slider;
import com.aetherteam.aetherii.entity.npc.outpost.Edward;
import com.aetherteam.aetherii.entity.passive.*;
import com.aetherteam.aetherii.entity.projectile.*;
import com.aetherteam.aetherii.entity.vehicle.CloudSkiff;
import net.minecraft.core.registries.Registries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

public class AetherIIEntityTypes {
    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType<T> type) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type);
    }

    // Passive
    public static final EntityType<Phyg> PHYG = register(AetherIIEntityIds.PHYG, EntityType.Builder.of(Phyg::new, MobCategory.CREATURE).sized(0.95F, 0.85F).clientTrackingRange(10).build(AetherIIEntityIds.PHYG));
    public static final EntityType<FlyingCow> FLYING_COW = register(AetherIIEntityIds.FLYING_COW, EntityType.Builder.of(FlyingCow::new, MobCategory.CREATURE).sized(0.95F, 1.1F).eyeHeight(1.05F).clientTrackingRange(10).build(AetherIIEntityIds.FLYING_COW));
    public static final EntityType<Sheepuff> SHEEPUFF = register(AetherIIEntityIds.SHEEPUFF, EntityType.Builder.of(Sheepuff::new, MobCategory.CREATURE).sized(0.9F, 1.25F).eyeHeight(0.85F).clientTrackingRange(10).build(AetherIIEntityIds.SHEEPUFF));

    public static final EntityType<Aerbunny> AERBUNNY = register(AetherIIEntityIds.AERBUNNY, EntityType.Builder.of(Aerbunny::new, MobCategory.CREATURE).sized(0.55F, 0.45F).eyeHeight(0.25F).clientTrackingRange(10).ridingOffset(0).build(AetherIIEntityIds.AERBUNNY));

    public static final EntityType<Aerwhale> AERWHALE = register(AetherIIEntityIds.AERWHALE, EntityType.Builder.of(Aerwhale::new, AetherIIMobCategory.AETHER_AERWHALE).fireImmune().sized(3.0F, 3.0F).clientTrackingRange(10).build(AetherIIEntityIds.AERWHALE));

    public static final EntityType<Taegore> HIGHFIELDS_TAEGORE = register(AetherIIEntityIds.HIGHFIELDS_TAEGORE, EntityType.Builder.of(Taegore::new, MobCategory.CREATURE).sized(1.3F, 1.7F).eyeHeight(0.85F).clientTrackingRange(10).build(AetherIIEntityIds.HIGHFIELDS_TAEGORE));
    public static final EntityType<Taegore> MAGNETIC_TAEGORE = register(AetherIIEntityIds.MAGNETIC_TAEGORE, EntityType.Builder.of(Taegore::new, MobCategory.CREATURE).sized(1.3F, 1.7F).eyeHeight(0.85F).clientTrackingRange(10).build(AetherIIEntityIds.MAGNETIC_TAEGORE));
    public static final EntityType<Taegore> ARCTIC_TAEGORE = register(AetherIIEntityIds.ARCTIC_TAEGORE, EntityType.Builder.of(Taegore::new, MobCategory.CREATURE).sized(1.3F, 1.7F).eyeHeight(0.85F).clientTrackingRange(10).build(AetherIIEntityIds.ARCTIC_TAEGORE));

    public static final EntityType<Burrukai> HIGHFIELDS_BURRUKAI = register(AetherIIEntityIds.HIGHFIELDS_BURRUKAI, EntityType.Builder.of(Burrukai::new, MobCategory.CREATURE).sized(1.5F, 1.95F).eyeHeight(1.2F).clientTrackingRange(10).build(AetherIIEntityIds.HIGHFIELDS_BURRUKAI));
    public static final EntityType<Burrukai> MAGNETIC_BURRUKAI = register(AetherIIEntityIds.MAGNETIC_BURRUKAI, EntityType.Builder.of(Burrukai::new, MobCategory.CREATURE).sized(1.5F, 1.95F).eyeHeight(1.2F).clientTrackingRange(10).build(AetherIIEntityIds.MAGNETIC_BURRUKAI));
    public static final EntityType<Burrukai> ARCTIC_BURRUKAI = register(AetherIIEntityIds.ARCTIC_BURRUKAI, EntityType.Builder.of(Burrukai::new, MobCategory.CREATURE).sized(1.5F, 1.95F).eyeHeight(1.2F).clientTrackingRange(10).build(AetherIIEntityIds.ARCTIC_BURRUKAI));

    public static final EntityType<Kirrid> HIGHFIELDS_KIRRID = register(AetherIIEntityIds.HIGHFIELDS_KIRRID, EntityType.Builder.of(Kirrid::new, MobCategory.CREATURE).sized(0.95F, 1.35F).eyeHeight(1.1F).clientTrackingRange(10).build(AetherIIEntityIds.HIGHFIELDS_KIRRID));
    public static final EntityType<Kirrid> MAGNETIC_KIRRID = register(AetherIIEntityIds.MAGNETIC_KIRRID, EntityType.Builder.of(Kirrid::new, MobCategory.CREATURE).sized(0.95F, 1.35F).eyeHeight(1.1F).clientTrackingRange(10).build(AetherIIEntityIds.MAGNETIC_KIRRID));
    public static final EntityType<Kirrid> ARCTIC_KIRRID = register(AetherIIEntityIds.ARCTIC_KIRRID, EntityType.Builder.of(Kirrid::new, MobCategory.CREATURE).sized(0.95F, 1.35F).eyeHeight(1.1F).clientTrackingRange(10).build(AetherIIEntityIds.ARCTIC_KIRRID));

    public static final EntityType<Moa> MOA = register(AetherIIEntityIds.MOA, EntityType.Builder.of(Moa::new, AetherIIMobCategory.AETHER_MOA).sized(1.25F, 2.35F).eyeHeight(2.1F).clientTrackingRange(10).build(AetherIIEntityIds.MOA));

    public static final EntityType<Prismallard> PRISMALLARD = register(AetherIIEntityIds.PRISMALLARD, EntityType.Builder.of(Prismallard::new, AetherIIMobCategory.AETHER_WATER_SURFACE_CREATURE).sized(0.5F, 0.6F).eyeHeight(0.55F).clientTrackingRange(10).build(AetherIIEntityIds.PRISMALLARD));


    public static final EntityType<SkyrootLizard> SKYROOT_LIZARD = register(AetherIIEntityIds.SKYROOT_LIZARD, EntityType.Builder.of(SkyrootLizard::new, AetherIIMobCategory.AETHER_AMBIENT).sized(0.95F, 0.35F).clientTrackingRange(10).build(AetherIIEntityIds.SKYROOT_LIZARD));

    public static final EntityType<Glitterwing> GLITTERWING = register(AetherIIEntityIds.GLITTERWING, EntityType.Builder.<Glitterwing>of(Glitterwing::new, AetherIIMobCategory.AETHER_AMBIENT).sized(0.75F, 0.75F).eyeHeight(0.25F).clientTrackingRange(5).build(AetherIIEntityIds.GLITTERWING));
    public static final EntityType<Shroudwing> SHROUDWING = register(AetherIIEntityIds.SHROUDWING, EntityType.Builder.<Shroudwing>of(Shroudwing::new, AetherIIMobCategory.AETHER_AMBIENT).sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(5).build(AetherIIEntityIds.SHROUDWING));

    // Hostile
    public static final EntityType<AechorPlant> AECHOR_PLANT = register(AetherIIEntityIds.AECHOR_PLANT, EntityType.Builder.of(AechorPlant::new, AetherIIMobCategory.AETHER_PLANT_HAZARD).sized(0.9F, 0.6F).notInPeaceful().eyeHeight(0.25F).clientTrackingRange(8).build(AetherIIEntityIds.AECHOR_PLANT));
    public static final EntityType<CarrionSprout> CARRION_SPROUT = register(AetherIIEntityIds.CARRION_SPROUT, EntityType.Builder.of(CarrionSprout::new, AetherIIMobCategory.AETHER_PLANT_HAZARD).sized(1.0F, 1.0F).clientTrackingRange(8).build(AetherIIEntityIds.CARRION_SPROUT));

    public static final EntityType<Zephyr> ZEPHYR = register(AetherIIEntityIds.ZEPHYR, EntityType.Builder.of(Zephyr::new, AetherIIMobCategory.AETHER_SKY_HAZARD).sized(2.0F, 1.75F).notInPeaceful().eyeHeight(1.2F).clientTrackingRange(10).build(AetherIIEntityIds.ZEPHYR));

    public static final EntityType<Swet> BLUE_SWET = register(AetherIIEntityIds.BLUE_SWET, EntityType.Builder.of(Swet::new, AetherIIMobCategory.AETHER_DARKNESS_HAZARD).sized(0.95F, 0.95F).notInPeaceful().clientTrackingRange(10).build(AetherIIEntityIds.BLUE_SWET));
    public static final EntityType<Swet> GOLDEN_SWET = register(AetherIIEntityIds.GOLDEN_SWET, EntityType.Builder.of(Swet::new, AetherIIMobCategory.AETHER_DARKNESS_HAZARD).sized(0.95F, 0.95F).notInPeaceful().clientTrackingRange(10).build(AetherIIEntityIds.GOLDEN_SWET));
    public static final EntityType<Skephid> SKEPHID = register(AetherIIEntityIds.SKEPHID, EntityType.Builder.of(Skephid::new, AetherIIMobCategory.AETHER_DARKNESS_HAZARD).sized(0.8F, 0.8F).notInPeaceful().clientTrackingRange(10).build(AetherIIEntityIds.SKEPHID));

    public static final EntityType<Tempest> TEMPEST = register(AetherIIEntityIds.TEMPEST, EntityType.Builder.of(Tempest::new, AetherIIMobCategory.AETHER_BLIGHT_MONSTER).sized(1.5F, 1.4F).notInPeaceful().eyeHeight(0.85F).clientTrackingRange(10).build(AetherIIEntityIds.TEMPEST));
    public static final EntityType<Cockatrice> COCKATRICE = register(AetherIIEntityIds.COCKATRICE, EntityType.Builder.of(Cockatrice::new, AetherIIMobCategory.AETHER_BLIGHT_MONSTER).sized(0.9F, 2.15F).notInPeaceful().clientTrackingRange(10).build(AetherIIEntityIds.COCKATRICE));

    public static final EntityType<ArkeniumTaluton> ARKENIUM_TALUTON = register(AetherIIEntityIds.ARKENIUM_TALUTON, EntityType.Builder.of(ArkeniumTaluton::new, AetherIIMobCategory.AETHER_DARKNESS_MONSTER).sized(1.0F, 1.65F).notInPeaceful().eyeHeight(1.25F).clientTrackingRange(10).build(AetherIIEntityIds.ARKENIUM_TALUTON));
    public static final EntityType<GravititeTaluton> GRAVITITE_TALUTON = register(AetherIIEntityIds.GRAVITITE_TALUTON, EntityType.Builder.of(GravititeTaluton::new, AetherIIMobCategory.AETHER_DARKNESS_MONSTER).sized(0.75F, 1.9F).notInPeaceful().eyeHeight(1.4F).clientTrackingRange(10).build(AetherIIEntityIds.GRAVITITE_TALUTON));

    public static final EntityType<Mimic> MIMIC = register(AetherIIEntityIds.MIMIC, EntityType.Builder.of(Mimic::new, AetherIIMobCategory.AETHER_DUNGEON_MONSTER).sized(0.9F, 1.35F).notInPeaceful().clientTrackingRange(8).build(AetherIIEntityIds.MIMIC));

    public static final EntityType<DetonationSentry> DETONATION_SENTRY = register(AetherIIEntityIds.DETONATION_SENTRY, EntityType.Builder.of(DetonationSentry::new, AetherIIMobCategory.AETHER_DUNGEON_MONSTER).sized(0.9F, 0.9F).notInPeaceful().eyeHeight(0.45F).clientTrackingRange(10).build(AetherIIEntityIds.DETONATION_SENTRY));
    public static final EntityType<SentryGolem> SENTRY_GOLEM = register(AetherIIEntityIds.SENTRY_GOLEM, EntityType.Builder.of(SentryGolem::new, AetherIIMobCategory.AETHER_DUNGEON_MONSTER).sized(0.6F, 1.95F).notInPeaceful().eyeHeight(1.8F).clientTrackingRange(8).build(AetherIIEntityIds.SENTRY_GOLEM));

    public static final EntityType<Slider> SLIDER = register(AetherIIEntityIds.SLIDER, EntityType.Builder.of(Slider::new, AetherIIMobCategory.AETHER_DUNGEON_MONSTER).sized(2.0F, 2.0F).notInPeaceful().fireImmune().clientTrackingRange(10).build(AetherIIEntityIds.SLIDER));

    public static final EntityType<BladeshroomHunter> BLADESHROOM_HUNTER = register(AetherIIEntityIds.BLADESHROOM_HUNTER, EntityType.Builder.of(BladeshroomHunter::new, AetherIIMobCategory.AETHER_DUNGEON_MONSTER).sized(0.9F, 1.3F).notInPeaceful().eyeHeight(1.2F).clientTrackingRange(10).build(AetherIIEntityIds.BLADESHROOM_HUNTER));


    // NPCs
    public static final EntityType<Edward> EDWARD = register(AetherIIEntityIds.EDWARD, EntityType.Builder.of(Edward::new, MobCategory.MISC).sized(0.6F, 1.95F).eyeHeight(1.75F).clientTrackingRange(8).build(AetherIIEntityIds.EDWARD));

    // Projectiles
    public static final EntityType<HolystoneRock> HOLYSTONE_ROCK = register(AetherIIEntityIds.HOLYSTONE_ROCK, EntityType.Builder.<HolystoneRock>of(HolystoneRock::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).noLootTable().build(AetherIIEntityIds.HOLYSTONE_ROCK));
    public static final EntityType<ArcticSnowball> ARCTIC_SNOWBALL = register(AetherIIEntityIds.ARCTIC_SNOWBALL, EntityType.Builder.<ArcticSnowball>of(ArcticSnowball::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).noLootTable().build(AetherIIEntityIds.ARCTIC_SNOWBALL));
    public static final EntityType<SkyrootPinecone> SKYROOT_PINECONE = register(AetherIIEntityIds.SKYROOT_PINECONE, EntityType.Builder.<SkyrootPinecone>of(SkyrootPinecone::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).noLootTable().build(AetherIIEntityIds.SKYROOT_PINECONE));
    public static final EntityType<ThrownPrismallardEgg> PRISMALLARD_EGG = register(AetherIIEntityIds.PRISMALLARD_EGG, EntityType.Builder.<ThrownPrismallardEgg>of(ThrownPrismallardEgg::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(10).noLootTable().build(AetherIIEntityIds.PRISMALLARD_EGG));
    public static final EntityType<LassoLoop> LASSO_LOOP = register(AetherIIEntityIds.LASSO_LOOP, EntityType.Builder.<LassoLoop>of(LassoLoop::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(10).noLootTable().build(AetherIIEntityIds.LASSO_LOOP));

    public static final EntityType<ScatterglassBolt> SCATTERGLASS_BOLT = register(AetherIIEntityIds.SCATTERGLASS_BOLT, EntityType.Builder.<ScatterglassBolt>of(ScatterglassBolt::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).noLootTable().build(AetherIIEntityIds.SCATTERGLASS_BOLT));
    public static final EntityType<AmberDart> AMBER_DART = register(AetherIIEntityIds.AMBER_DART, EntityType.Builder.<AmberDart>of(AmberDart::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).noLootTable().build(AetherIIEntityIds.AMBER_DART));

    public static final EntityType<ToxicDart> TOXIC_DART = register(AetherIIEntityIds.TOXIC_DART, EntityType.Builder.<ToxicDart>of(ToxicDart::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).noLootTable().build(AetherIIEntityIds.TOXIC_DART));
    public static final EntityType<VenomousDart> VENOMOUS_DART = register(AetherIIEntityIds.VENOMOUS_DART, EntityType.Builder.<VenomousDart>of(VenomousDart::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).noLootTable().build(AetherIIEntityIds.VENOMOUS_DART));

    public static final EntityType<ZephyrWebbingBall> ZEPHYR_WEBBING_BALL = register(AetherIIEntityIds.ZEPHYR_WEBBING_BALL, EntityType.Builder.<ZephyrWebbingBall>of(ZephyrWebbingBall::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(10).noLootTable().build(AetherIIEntityIds.ZEPHYR_WEBBING_BALL));
    public static final EntityType<SkephidWebbingBall> SKEPHID_WEBBING_BALL = register(AetherIIEntityIds.SKEPHID_WEBBING_BALL, EntityType.Builder.<SkephidWebbingBall>of(SkephidWebbingBall::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(10).noLootTable().build(AetherIIEntityIds.SKEPHID_WEBBING_BALL));

    public static final EntityType<TempestThunderball> TEMPEST_THUNDERBALL = register(AetherIIEntityIds.TEMPEST_THUNDERBALL, EntityType.Builder.<TempestThunderball>of(TempestThunderball::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(10).noLootTable().build(AetherIIEntityIds.TEMPEST_THUNDERBALL));

    public static final EntityType<GravititeDebrisShot> GRAVITITE_DEBRIS_SHOT = register(AetherIIEntityIds.GRAVITITE_DEBRIS_SHOT, EntityType.Builder.<GravititeDebrisShot>of(GravititeDebrisShot::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).noLootTable().build(AetherIIEntityIds.GRAVITITE_DEBRIS_SHOT));

    // Blocks
    public static final EntityType<SittableEntity> SITTABLE = register(AetherIIEntityIds.SITTABLE, EntityType.Builder.<SittableEntity>of(SittableEntity::new, MobCategory.MISC).sized(0.0F, 0.0F).noLootTable().build(AetherIIEntityIds.SITTABLE));

    public static final EntityType<HoveringBlockEntity> HOVERING_BLOCK = register(AetherIIEntityIds.HOVERING_BLOCK, EntityType.Builder.<HoveringBlockEntity>of(HoveringBlockEntity::new, MobCategory.MISC).sized(0.9F, 0.9F).clientTrackingRange(10).updateInterval(1).noLootTable().build(AetherIIEntityIds.HOVERING_BLOCK));

    // Vehicles
    public static final EntityType<CloudSkiff> CLOUD_SKIFF = register(AetherIIEntityIds.CLOUD_SKIFF, EntityType.Builder.<CloudSkiff>of(CloudSkiff::new, MobCategory.MISC).noLootTable().sized(1.75F, 0.2125F).eyeHeight(0.5625F).clientTrackingRange(10).build(AetherIIEntityIds.CLOUD_SKIFF));

    // Misc
    public static final EntityType<ElectricField> ELECTRIC_FIELD = register(AetherIIEntityIds.ELECTRIC_FIELD, EntityType.Builder.<ElectricField>of(ElectricField::new, MobCategory.MISC).fireImmune().sized(6.0F, 1.5F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).noLootTable().build(AetherIIEntityIds.ELECTRIC_FIELD));
    public static final EntityType<DemolitionProjectile> DEMOLITION_PROJECTILE = register(AetherIIEntityIds.DETONATION_PROJECTILE, EntityType.Builder.<DemolitionProjectile>of(DemolitionProjectile::new, MobCategory.MISC).clientTrackingRange(4).updateInterval(10).sized(0.9F, 0.9F).noLootTable().fireImmune().build(AetherIIEntityIds.DETONATION_PROJECTILE));


    public static void registerSpawnPlacements() {
        // Passive
        SpawnPlacements.register(AetherIIEntityTypes.FLYING_COW, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.SHEEPUFF, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.PHYG, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.AERBUNNY, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherTamableAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.AERWHALE, AetherIISpawnPlacementTypes.NOT_IN_LIQUID, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Aerwhale::checkAerwhaleSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.HIGHFIELDS_TAEGORE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.MAGNETIC_TAEGORE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.ARCTIC_TAEGORE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.MAGNETIC_BURRUKAI, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.ARCTIC_BURRUKAI, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.HIGHFIELDS_KIRRID, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.MAGNETIC_KIRRID, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.ARCTIC_KIRRID, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.MOA, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.PRISMALLARD, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Prismallard::checkPrismallardSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.GLITTERWING, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Insect::checkInsectSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.SHROUDWING, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Insect::checkInsectSpawnRules);

        // Hostile
        SpawnPlacements.register(AetherIIEntityTypes.AECHOR_PLANT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AechorPlant::checkAechorPlantSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.CARRION_SPROUT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CarrionSprout::checkCarrionSproutSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.ZEPHYR, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Zephyr::checkZephyrSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.TEMPEST, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Tempest::checkTempestSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.COCKATRICE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Cockatrice::checkMonsterSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.BLUE_SWET, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Swet::checkSwetSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.GOLDEN_SWET, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Swet::checkSwetSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.SKEPHID, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Skephid::checkSkephidSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.ARKENIUM_TALUTON, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Taluton::checkTalutonSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.GRAVITITE_TALUTON, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Taluton::checkTalutonSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.DETONATION_SENTRY, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.SENTRY_GOLEM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        SpawnPlacements.register(AetherIIEntityTypes.BLADESHROOM_HUNTER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }

    public static void registerEntityAttributes() {
        // Passive
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.FLYING_COW, AetherIIStats.merge(FlyingCow.createMobAttributes(), AetherIIStats.FLYING_COW).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.SHEEPUFF, AetherIIStats.merge(Sheepuff.createMobAttributes(), AetherIIStats.SHEEPUFF).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.PHYG, AetherIIStats.merge(Phyg.createMobAttributes(), AetherIIStats.PHYG).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.AERBUNNY, AetherIIStats.merge(Aerbunny.createMobAttributes(), AetherIIStats.AERBUNNY).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.AERWHALE, AetherIIStats.merge(Aerwhale.createMobAttributes(), AetherIIStats.AERWHALE).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.HIGHFIELDS_TAEGORE, AetherIIStats.merge(Taegore.createMobAttributes(), AetherIIStats.HIGHFIELDS_TAEGORE).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.MAGNETIC_TAEGORE, AetherIIStats.merge(Taegore.createMobAttributes(), AetherIIStats.MAGNETIC_TAEGORE).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.ARCTIC_TAEGORE, AetherIIStats.merge(Taegore.createMobAttributes(), AetherIIStats.ARCTIC_TAEGORE).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI, AetherIIStats.merge(Burrukai.createMobAttributes(), AetherIIStats.HIGHFIELDS_BURRUKAI).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.MAGNETIC_BURRUKAI, AetherIIStats.merge(Burrukai.createMobAttributes(), AetherIIStats.MAGNETIC_BURRUKAI).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.ARCTIC_BURRUKAI, AetherIIStats.merge(Burrukai.createMobAttributes(), AetherIIStats.ARCTIC_BURRUKAI).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.HIGHFIELDS_KIRRID, AetherIIStats.merge(Kirrid.createMobAttributes(), AetherIIStats.HIGHFIELDS_KIRRID).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.MAGNETIC_KIRRID, AetherIIStats.merge(Kirrid.createMobAttributes(), AetherIIStats.MAGNETIC_KIRRID).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.ARCTIC_KIRRID, AetherIIStats.merge(Kirrid.createMobAttributes(), AetherIIStats.ARCTIC_KIRRID).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.MOA, AetherIIStats.merge(Moa.createMobAttributes(), AetherIIStats.MOA).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.PRISMALLARD, AetherIIStats.merge(Prismallard.createAttributes(), AetherIIStats.PRISMALLARD).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.SKYROOT_LIZARD, AetherIIStats.merge(SkyrootLizard.createMobAttributes(), AetherIIStats.SKYROOT_LIZARD).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.GLITTERWING, Glitterwing.createMobAttributes().build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.SHROUDWING, Shroudwing.createMobAttributes().build());

        // Hostile
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.AECHOR_PLANT, AetherIIStats.merge(AechorPlant.createMobAttributes(), AetherIIStats.AECHOR_PLANT).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.CARRION_SPROUT, AetherIIStats.merge(CarrionSprout.createMobAttributes(), AetherIIStats.CARRION_SPROUT).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.ZEPHYR, AetherIIStats.merge(Zephyr.createMobAttributes(), AetherIIStats.ZEPHYR).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.TEMPEST, AetherIIStats.merge(Tempest.createMobAttributes(), AetherIIStats.TEMPEST).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.COCKATRICE, AetherIIStats.merge(Cockatrice.createMobAttributes(), AetherIIStats.COCKATRICE).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.BLUE_SWET, AetherIIStats.merge(Swet.createMobAttributes(), AetherIIStats.SWET).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.GOLDEN_SWET, AetherIIStats.merge(Swet.createMobAttributes(), AetherIIStats.SWET).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.SKEPHID, AetherIIStats.merge(Skephid.createMobAttributes(), AetherIIStats.SKEPHID).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.ARKENIUM_TALUTON, AetherIIStats.merge(ArkeniumTaluton.createMobAttributes(), AetherIIStats.ARKENIUM_TALUTON).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.GRAVITITE_TALUTON, AetherIIStats.merge(GravititeTaluton.createMobAttributes(), AetherIIStats.GRAVITITE_TALUTON).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.MIMIC, AetherIIStats.merge(Mimic.createMobAttributes(), AetherIIStats.MIMIC).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.DETONATION_SENTRY, AetherIIStats.merge(DetonationSentry.createMobAttributes(), AetherIIStats.DETONATION_SENTRY).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.SENTRY_GOLEM, AetherIIStats.merge(SentryGolem.createMobAttributes(), AetherIIStats.SENTRY_GOLEM).build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.SLIDER, Slider.createMobAttributes().build());
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.BLADESHROOM_HUNTER, AetherIIStats.merge(BladeshroomHunter.createMobAttributes(), AetherIIStats.BLADESHROOM_HUNTER).build());

        // NPCs
        FabricDefaultAttributeRegistry.register(AetherIIEntityTypes.EDWARD, Edward.createMobAttributes().build());
    }

    public static void init() {
    }
}
