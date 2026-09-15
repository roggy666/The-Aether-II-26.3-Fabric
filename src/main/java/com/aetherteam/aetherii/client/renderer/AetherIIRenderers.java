package com.aetherteam.aetherii.client.renderer;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.CustomUnbakedBlockStateModel;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.client.renderer.item.ItemModels;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityRenderLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.attachment.AetherIIDataAttachments;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.AetherIIFluids;
import com.aetherteam.aetherii.blockentity.AetherIIBlockEntityTypes;
import com.aetherteam.aetherii.client.renderer.accessory.AccessoryLayer;
import com.aetherteam.aetherii.client.renderer.accessory.GlovesLayer;
import com.aetherteam.aetherii.client.renderer.accessory.model.GlovesModel;
import com.aetherteam.aetherii.client.renderer.block.model.blockstate.*;
import com.aetherteam.aetherii.client.renderer.blockentity.*;
import com.aetherteam.aetherii.client.renderer.blockentity.model.*;
import com.aetherteam.aetherii.client.renderer.entity.*;
import com.aetherteam.aetherii.client.renderer.entity.layers.ProjectilesStuckLayer;
import com.aetherteam.aetherii.client.renderer.entity.layers.SwetLatchLayer;
import com.aetherteam.aetherii.client.renderer.entity.model.*;
import com.aetherteam.aetherii.client.renderer.entity.model.burrukai.ArcticBurrukaiModel;
import com.aetherteam.aetherii.client.renderer.entity.model.burrukai.BurrukaiBabyModel;
import com.aetherteam.aetherii.client.renderer.entity.model.burrukai.BurrukaiModel;
import com.aetherteam.aetherii.client.renderer.entity.model.kirrid.*;
import com.aetherteam.aetherii.client.renderer.entity.model.taegore.TaegoreBabyModel;
import com.aetherteam.aetherii.client.renderer.entity.model.taegore.TaegoreModel;
import com.aetherteam.aetherii.client.renderer.entity.state.SwetRenderState;
import com.aetherteam.aetherii.client.renderer.item.model.*;
import com.aetherteam.aetherii.client.renderer.level.DungeonBlockOverlayRenderer;
import com.aetherteam.aetherii.entity.AetherIIEntityTypes;
import com.aetherteam.aetherii.entity.monster.Swet;
import com.aetherteam.aetherii.entity.passive.Aerbunny;
import com.aetherteam.aetherii.entity.passive.Moa;
import com.aetherteam.aetherii.entity.vehicle.CloudSkiff;
import com.aetherteam.aetherii.integration.AccessoryUtil;
import com.aetherteam.aetherii.inventory.container.AccessoryContainer;
import com.google.common.reflect.TypeToken;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AetherIIRenderers {
    public static final RenderStateDataKey<Boolean> RIDING_SKIFF_KEY = RenderStateDataKey.create(() -> AetherII.MODID + ":riding_skiff");
    public static final RenderStateDataKey<Float> SKIFF_STEERING_KEY = RenderStateDataKey.create(() -> AetherII.MODID + ":skiff_steering");
    public static final RenderStateDataKey<Boolean> RIDING_MOA_KEY = RenderStateDataKey.create(() -> AetherII.MODID + ":riding_moa");
    public static final RenderStateDataKey<Boolean> HAS_AERBUNNY = RenderStateDataKey.create(() -> AetherII.MODID + ":has_aerbunny");
    public static final RenderStateDataKey<List<SwetRenderState>> SWET_KEY = RenderStateDataKey.create(() -> AetherII.MODID + ":swet");
    public static final RenderStateDataKey<List<EntityType<?>>> STUCK_PROJECTILES_KEY = RenderStateDataKey.create(() -> AetherII.MODID + ":stuck_projectiles");
    public static final RenderStateDataKey<ItemStack> HANDWEAR_EQUIPMENT_KEY = RenderStateDataKey.create(() -> AetherII.MODID + ":handwear_equipment");
    public static final RenderStateDataKey<ItemStack> ACCESSORY_EQUIPMENT_KEY = RenderStateDataKey.create(() -> AetherII.MODID + ":accessory_equipment");

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void registerAddLayer() {
        LivingEntityRenderLayerRegistrationCallback.EVENT.register((entityType, renderer, helper, context) -> {
            if (renderer instanceof AvatarRenderer playerRenderer) {
                helper.register(new SwetLatchLayer<>(playerRenderer));
                helper.register(new GlovesLayer<>(playerRenderer));
                helper.register(new AccessoryLayer(playerRenderer));
                helper.register(new ProjectilesStuckLayer<>(playerRenderer, context));
            }
        });
    }



    public static void extractAvatarRenderState(Avatar avatar, AvatarRenderState avatarRenderState) {
            avatarRenderState.setData(SWET_KEY, List.of());
            avatarRenderState.setData(RIDING_SKIFF_KEY, false);
            avatarRenderState.setData(SKIFF_STEERING_KEY, 0.0F);
            List<Swet> swets = avatar.getAttachedOrCreate(AetherIIDataAttachments.SWET_LATCH).getLatchedSwets();
            if (swets != null) {
                List<SwetRenderState> states = new ArrayList<>();
                for (Swet swet : swets) {
                    SwetRenderState state = new SwetRenderState();
                    state.entityType = swet.getType();
                    state.swetScale = swet.getSwetScale();
                    states.add(state);
                }
                avatarRenderState.setData(SWET_KEY, states);
            }
            avatarRenderState.setData(RIDING_MOA_KEY, avatar.getVehicle() instanceof Moa);
            if (avatar.getVehicle() instanceof CloudSkiff cloudSkiff) {
                avatarRenderState.setData(RIDING_SKIFF_KEY, true);
                avatarRenderState.setData(SKIFF_STEERING_KEY, cloudSkiff.steering);
            }
            avatarRenderState.setData(STUCK_PROJECTILES_KEY, avatar.getAttachedOrCreate(AetherIIDataAttachments.PLAYER).getStuckProjectiles());
            avatarRenderState.setData(HAS_AERBUNNY, avatar.getFirstPassenger() instanceof Aerbunny);
            avatarRenderState.setData(HANDWEAR_EQUIPMENT_KEY, AccessoryUtil.getFirst(avatar, AccessoryContainer.SlotType.HANDWEAR).orElse(ItemStack.EMPTY));
            avatarRenderState.setData(ACCESSORY_EQUIPMENT_KEY, AccessoryUtil.getFirst(avatar, AccessoryContainer.SlotType.ACCESSORY).orElse(ItemStack.EMPTY));
    }


    public static void registerEntityRenderers() {
        // Blocks
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.SKYROOT_CHEST, SkyrootChestRenderer::new);
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.MOA_EGG, MoaEggRenderer::new);
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.ALTAR, AltarRenderer::new);
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.ARKENIUM_FORGE, ArkeniumForgeRenderer::new);
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.ALKAHEST_PURIFIER, AlkahestPurifierRenderer::new);
        net.minecraft.client.renderer.blockentity.BlockEntityRenderers.register(AetherIIBlockEntityTypes.AMBROSIUM_CAMPFIRE, CampfireRenderer::new);
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.VASE, VaseRenderer::new);
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.SENTRY_CRATE, SentryCrateRenderer::new);
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.SENTRY_SPAWNER, SentrySpawnerRenderer::new);
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.ABANDONED_BAG, AbandonedBagRenderer::new);
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.FUNGAL_CACHE, FungalCacheRenderer::new);
        BlockEntityRendererRegistry.register(AetherIIBlockEntityTypes.SAGE_CHEST, SageChestRenderer::new);


        // Entities
        // Passive
        EntityRendererRegistry.register(AetherIIEntityTypes.AERBUNNY, AerbunnyRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.PHYG, PhygRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.FLYING_COW, FlyingCowRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.SHEEPUFF, SheepuffRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.AERWHALE, AerwhaleRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.HIGHFIELDS_TAEGORE, (context) -> new TaegoreRenderer(context, BiomeVariantPresets.HIGHFIELDS_TAEGORE));
        EntityRendererRegistry.register(AetherIIEntityTypes.MAGNETIC_TAEGORE, (context) -> new TaegoreRenderer(context, BiomeVariantPresets.MAGNETIC_TAEGORE));
        EntityRendererRegistry.register(AetherIIEntityTypes.ARCTIC_TAEGORE, (context) -> new TaegoreRenderer(context, BiomeVariantPresets.ARCTIC_TAEGORE));
        EntityRendererRegistry.register(AetherIIEntityTypes.HIGHFIELDS_BURRUKAI, (context) -> new BurrukaiRenderer(context, BiomeVariantPresets.HIGHFIELDS_BURRUKAI));
        EntityRendererRegistry.register(AetherIIEntityTypes.MAGNETIC_BURRUKAI, (context) -> new BurrukaiRenderer(context, BiomeVariantPresets.MAGNETIC_BURRUKAI));
        EntityRendererRegistry.register(AetherIIEntityTypes.ARCTIC_BURRUKAI, (context) -> new BurrukaiRenderer(context, BiomeVariantPresets.ARCTIC_BURRUKAI));
        EntityRendererRegistry.register(AetherIIEntityTypes.HIGHFIELDS_KIRRID, (context) -> new KirridRenderer(context, BiomeVariantPresets.HIGHFIELDS_KIRRID));
        EntityRendererRegistry.register(AetherIIEntityTypes.MAGNETIC_KIRRID, (context) -> new KirridRenderer(context, BiomeVariantPresets.MAGNETIC_KIRRID));
        EntityRendererRegistry.register(AetherIIEntityTypes.ARCTIC_KIRRID, (context) -> new KirridRenderer(context, BiomeVariantPresets.ARCTIC_KIRRID));
        EntityRendererRegistry.register(AetherIIEntityTypes.MOA, MoaRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.PRISMALLARD, PrismallardRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.SKYROOT_LIZARD, SkyrootLizardRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.CARRION_SPROUT, CarrionSproutRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.GLITTERWING, GlitterwingRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.SHROUDWING, ShroudwingRenderer::new);

        // Hostile
        EntityRendererRegistry.register(AetherIIEntityTypes.AECHOR_PLANT, AechorPlantRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.ZEPHYR, ZephyrRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.TEMPEST, TempestRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.COCKATRICE, CockatriceRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.BLUE_SWET, BlueSwetRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.GOLDEN_SWET, GoldenSwetRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.SKEPHID, SkephidRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.ARKENIUM_TALUTON, ArkeniumTalutonRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.GRAVITITE_TALUTON, GravititeTalutonRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.MIMIC, MimicRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.DETONATION_SENTRY, DetonationSentryRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.SENTRY_GOLEM, SentryGolemRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.SLIDER, SliderRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.BLADESHROOM_HUNTER, BladeshroomHunterRenderer::new);

        // NPCs
        EntityRendererRegistry.register(AetherIIEntityTypes.EDWARD, EdwardRenderer::new);

        // Projectiles
        EntityRendererRegistry.register(AetherIIEntityTypes.HOLYSTONE_ROCK, ThrownItemRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.ARCTIC_SNOWBALL, ThrownItemRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.SKYROOT_PINECONE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.PRISMALLARD_EGG, ThrownItemRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.LASSO_LOOP, LassoLoopRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.SCATTERGLASS_BOLT, ScatterglassBoltRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.AMBER_DART, AmberDartRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.TOXIC_DART, ToxicDartRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.VENOMOUS_DART, VenomousDartRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.ZEPHYR_WEBBING_BALL, ZephyrWebbingBallRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.TEMPEST_THUNDERBALL, TempestThunderballRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.SKEPHID_WEBBING_BALL, SkephidWebbingBallRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.GRAVITITE_DEBRIS_SHOT, GravititeDebrisShotRenderer::new);

        EntityRendererRegistry.register(AetherIIEntityTypes.DEMOLITION_PROJECTILE, DemolitionProjectileRenderer::new);

        // Blocks
        EntityRendererRegistry.register(AetherIIEntityTypes.SITTABLE, NoopRenderer::new);
        EntityRendererRegistry.register(AetherIIEntityTypes.HOVERING_BLOCK, HoveringBlockRenderer::new);

        // Vehicles
        EntityRendererRegistry.register(AetherIIEntityTypes.CLOUD_SKIFF, CloudSkiffRenderer::new);

        // Misc
        EntityRendererRegistry.register(AetherIIEntityTypes.ELECTRIC_FIELD, NoopRenderer::new);
    }

    public static void registerLayerDefinition() {
        // Blocks
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SKYROOT_BED_HEAD, SkyrootBedRenderer::createHeadLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SKYROOT_BED_FOOT, SkyrootBedRenderer::createFootLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MOA_EGG, MoaEggModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ALKAHEST_PURIFIER, AlkahestPurifierModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.VASE, VaseModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SENTRY_SPAWNER, SentrySpawnerModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SENTRY_SPAWNER_PISTON, SentrySpawnerPistonModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SENTRY_CRATE, SentryCrateModel::createSingleBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.DOUBLE_SENTRY_CRATE_RIGHT, SentryCrateModel::createDoubleBodyRightLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.DOUBLE_SENTRY_CRATE_LEFT, SentryCrateModel::createDoubleBodyLeftLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ABANDONED_BAG, AbandonedBagModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.FUNGAL_CACHE, FungalCacheModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SAGE_CHEST, SageChestModel::createSingleBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.DOUBLE_SAGE_CHEST_RIGHT, SageChestModel::createDoubleBodyRightLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.DOUBLE_SAGE_CHEST_LEFT, SageChestModel::createDoubleBodyLeftLayer);

        // Entities
        // Passive
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.AERBUNNY, AerbunnyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.AERBUNNY_COLLAR, AerbunnyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.PHYG, PhygModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.FLYING_COW, FlyingCowModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SHEEPUFF, SheepuffModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.AERWHALE, AerwhaleModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.HIGHFIELDS_TAEGORE, TaegoreModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.HIGHFIELDS_TAEGORE_BABY, TaegoreBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MAGNETIC_TAEGORE, TaegoreModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MAGNETIC_TAEGORE_BABY, TaegoreBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ARCTIC_TAEGORE, TaegoreModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ARCTIC_TAEGORE_BABY, TaegoreBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.HIGHFIELDS_BURRUKAI, BurrukaiModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.HIGHFIELDS_BURRUKAI_BABY, BurrukaiBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MAGNETIC_BURRUKAI, BurrukaiModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MAGNETIC_BURRUKAI_BABY, BurrukaiBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ARCTIC_BURRUKAI, ArcticBurrukaiModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ARCTIC_BURRUKAI_BABY, BurrukaiBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.HIGHFIELDS_KIRRID, HighfieldsKirridModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.HIGHFIELDS_KIRRID_BABY, HighfieldsKirridBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MAGNETIC_KIRRID, MagneticKirridModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MAGNETIC_KIRRID_BABY, MagneticKirridBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ARCTIC_KIRRID, ArcticKirridModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ARCTIC_KIRRID_BABY, ArcticKirridBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MOA, MoaModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MOA_BABY, MoaBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MOA_SADDLE, MoaSaddleModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MOA_SADDLEBAG, MoaSaddlebagModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MOA_LARGE_SADDLEBAG, MoaLargeSaddlebagModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.PRISMALLARD, PrismallardModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SKYROOT_LIZARD, SkyrootLizardModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.GLITTERWING, GlitterwingModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SHROUDWING, ShroudwingModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.CARRION_SPROUT, CarrionSproutModel::createBodyLayer);

        // Hostile
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.AECHOR_PLANT, AechorPlantModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ZEPHYR, ZephyrModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.TEMPEST, TempestModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.COCKATRICE, CockatriceModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.BLUE_SWET, SwetModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.GOLDEN_SWET, SwetModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SKEPHID, SkephidModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ARKENIUM_TALUTON, ArkeniumTalutonModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.GRAVITITE_TALUTON, GravititeTalutonModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.DETONATION_SENTRY, DetonationSentryModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.MIMIC, MimicModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SENTRY_GOLEM, SentryGolemModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.SLIDER, SliderModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.BLADESHROOM_HUNTER, BladeshroomHunterModel::createBodyLayer);

        // Projectiles
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.GRAVITITE_DEBRIS_SHOT, GravititeDebrisShotModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.DEMOLITION_PROJECTILE, DemolitionProjectileModel::createBodyLayer);
        // NPCs
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.EDWARD, EdwardModel::createBodyLayer);

        // Vehicles
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.CLOUD_SKIFF, CloudSkiffModel::createLayer);

        // Accessories
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.GLOVES, () -> GlovesModel.createLayer(new CubeDeformation(0.6F), false));
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.GLOVES_SLIM, () -> GlovesModel.createLayer(new CubeDeformation(0.6F), true));
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.GLOVES_FIRST_PERSON, () -> GlovesModel.createLayer(new CubeDeformation(0.25F), false));
        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.GLOVES_SLIM_FIRST_PERSON, () -> GlovesModel.createLayer(new CubeDeformation(0.25F), true));

        ModelLayerRegistry.registerModelLayer(AetherIIModelLayers.ACCESSORY, () -> LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.5F), 0.0F), 64, 32));
    }

    public static void registerItemModels() {
        ItemModels.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "shield"), ShieldModel.Unbaked.MAP_CODEC);
        ItemModels.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "music_player_disc"), MusicPlayerDiscModel.Unbaked.MAP_CODEC);
//        ItemModels.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "mural"), MuralItemModel.Unbaked.MAP_CODEC);
    }

    public static void registerBlockStateModels() {
        CustomUnbakedBlockStateModel.register(TrunkModel.Unbaked.ID, TrunkModel.Unbaked.CODEC);
    }

    public static void registerFluidModels() {
        FluidModel.Unbaked alkahestModel = new FluidModel.Unbaked(
                new Material(Identifier.fromNamespaceAndPath(AetherII.MODID, "fluid/alkahest_still")),
                new Material(Identifier.fromNamespaceAndPath(AetherII.MODID, "fluid/alkahest_flow")),
                new Material(Identifier.fromNamespaceAndPath(AetherII.MODID, "fluid/alkahest_overlay")),
                null
        );
        FluidRenderingRegistry.register(AetherIIFluids.ALKAHEST, AetherIIFluids.FLOWING_ALKAHEST, alkahestModel);
    }

    public static void registerBakedModels() {
        List<Block> overlaidLeafBlocks = List.of(
                AetherIIBlocks.SKYROOT_LEAVES,
                AetherIIBlocks.SKYPLANE_LEAVES,
                AetherIIBlocks.SKYBIRCH_LEAVES,
                AetherIIBlocks.SKYPINE_LEAVES,
                AetherIIBlocks.WISPROOT_LEAVES,
                AetherIIBlocks.WISPTOP_LEAVES,
                AetherIIBlocks.GREATROOT_LEAVES,
                AetherIIBlocks.GREATOAK_LEAVES,
                AetherIIBlocks.GREATBOA_LEAVES,
                AetherIIBlocks.AMBEROOT_LEAVES,
                AetherIIBlocks.IRRADIATED_SKYROOT_LEAVES,
                AetherIIBlocks.IRRADIATED_SKYPLANE_LEAVES,
                AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAVES,
                AetherIIBlocks.IRRADIATED_SKYPINE_LEAVES,
                AetherIIBlocks.IRRADIATED_WISPROOT_LEAVES,
                AetherIIBlocks.IRRADIATED_WISPTOP_LEAVES,
                AetherIIBlocks.IRRADIATED_GREATROOT_LEAVES,
                AetherIIBlocks.IRRADIATED_GREATOAK_LEAVES,
                AetherIIBlocks.IRRADIATED_GREATBOA_LEAVES);
        List<Block> aoBlocks = List.of(
                AetherIIBlocks.AMBROSIUM_ORE,
                AetherIIBlocks.UNDERSHALE_AMBROSIUM_ORE,
                AetherIIBlocks.SENTRY_BRICKS,
                AetherIIBlocks.SENTRY_BRICK_STAIRS,
                AetherIIBlocks.SENTRY_BRICK_SLAB,
                AetherIIBlocks.SENTRY_BRICK_WALL,
                AetherIIBlocks.SENTRY_LIGHTSTONE,
                AetherIIBlocks.SENTRY_FLAGSTONES,
                AetherIIBlocks.SENTRY_TILE,
                AetherIIBlocks.SENTRY_BASE_BRICKS,
                AetherIIBlocks.SENTRY_CAPSTONE_BRICKS,
                AetherIIBlocks.SENTRY_BASE_PILLAR,
                AetherIIBlocks.SENTRY_CAPSTONE_PILLAR,
                AetherIIBlocks.SENTRY_PILLAR,
                AetherIIBlocks.BLOOMING_ARILUM,
                AetherIIBlocks.BLOOMING_ARILUM_PLANT,
                AetherIIBlocks.SPOTTED_MAGNETIC_SHROOM_BLOCK,
                AetherIIBlocks.LUCENT_GUARDIAN_ROOTS,
                AetherIIBlocks.GUARDIAN_LAMP);
        List<Block> breakingFixBlocks = List.of(
                AetherIIBlocks.AETHER_GRASS_BLOCK);
        List<Block> copyBlocks = List.of(
                AetherIIBlocks.LOCKED_BLOCK,
                AetherIIBlocks.BOSS_DOORWAY_BLOCK,
                AetherIIBlocks.TREASURE_DOORWAY_BLOCK);

        ModelLoadingPlugin.register(plugin -> plugin.modifyBlockModelAfterBake().register((model, context) -> {
            Block block = context.state().getBlock();
            if (overlaidLeafBlocks.contains(block)) model = new OverlaidLeavesModel(model);
            if (aoBlocks.contains(block)) model = new AmbientOcclusionLightModel(model);
            if (breakingFixBlocks.contains(block)) model = new BreakingFixModel(model);
            if (copyBlocks.contains(block)) model = new CopyBlockModel(model);
            return model;
        }));
    }

    public static void registerSpecialModelRenderers() {
        SpecialModelRenderers.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "skyroot_bed"), SkyrootBedSpecialRenderer.Unbaked.MAP_CODEC);
        SpecialModelRenderers.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "alkahest_purifier"), AlkahestPurifierSpecialRenderer.Unbaked.MAP_CODEC);
        SpecialModelRenderers.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "vase"), VaseSpecialRenderer.Unbaked.MAP_CODEC);
        SpecialModelRenderers.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "sentry_crate"), SentryCrateSpecialRenderer.Unbaked.MAP_CODEC);
        SpecialModelRenderers.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "sentry_spawner"), SentrySpawnerSpecialRenderer.Unbaked.MAP_CODEC);
        SpecialModelRenderers.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "abandoned_bag"), AbandonedBagSpecialRenderer.Unbaked.MAP_CODEC);
        SpecialModelRenderers.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "fungal_cache"), FungalCacheSpecialRenderer.Unbaked.MAP_CODEC);
        SpecialModelRenderers.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "sage_chest"), SageChestSpecialRenderer.Unbaked.MAP_CODEC);
        SpecialModelRenderers.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "copy_block"), CopyBlockSpecialRenderer.Unbaked.MAP_CODEC);
    }

    public static void submitCustomGeometryRendering(LevelRenderContext event) {
        LevelRenderState levelRenderState = event.levelState();
        PoseStack poseStack = event.poseStack();
        SubmitNodeCollector submitNodeCollector = event.submitNodeCollector();
        CameraRenderState cameraRenderState = levelRenderState.cameraRenderState;

        DungeonBlockOverlayRenderer.submitDungeonBlockOverlays(poseStack, submitNodeCollector, cameraRenderState.pos, cameraRenderState.cullFrustum, Minecraft.getInstance());
    }

    public static boolean isFastBlock(BlockState state) {
        List<Block> fastBlocks = List.of(
                AetherIIBlocks.SKYROOT_LEAF_PILE,
                AetherIIBlocks.SKYPLANE_LEAF_PILE,
                AetherIIBlocks.SKYBIRCH_LEAF_PILE,
                AetherIIBlocks.SKYPINE_LEAF_PILE,
                AetherIIBlocks.WISPROOT_LEAF_PILE,
                AetherIIBlocks.WISPTOP_LEAF_PILE,
                AetherIIBlocks.GREATROOT_LEAF_PILE,
                AetherIIBlocks.GREATOAK_LEAF_PILE,
                AetherIIBlocks.GREATBOA_LEAF_PILE,
                AetherIIBlocks.AMBEROOT_LEAF_PILE,
                AetherIIBlocks.AETHER_BUSH,
                AetherIIBlocks.BLUEBERRY_BUSH,
                AetherIIBlocks.POTTED_AETHER_BUSH,
                AetherIIBlocks.POTTED_BLUEBERRY_BUSH,
                AetherIIBlocks.TANGLED_BRANCHES,
                AetherIIBlocks.UNDERGROWTH_LEAVES);
        return fastBlocks.contains(state.getBlock());
    }
}