package com.aetherteam.aetherii.client;

import net.minecraft.world.item.Item;
import java.util.Set;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.effect.AetherIIMobEffects;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.TerrainParticle;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector4f;

public class AetherIIClientExtensions {
    private static final Set<Item> BEAST_PELT = Set.of(AetherIIItems.BEAST_PELT_HELMET, AetherIIItems.BEAST_PELT_CHESTPLATE, AetherIIItems.BEAST_PELT_LEGGINGS, AetherIIItems.BEAST_PELT_BOOTS, AetherIIItems.BEAST_PELT_GLOVES);
    private static final Set<Item> BURRUKAI_PLATE = Set.of(AetherIIItems.BURRUKAI_PLATE_HELMET, AetherIIItems.BURRUKAI_PLATE_CHESTPLATE, AetherIIItems.BURRUKAI_PLATE_LEGGINGS, AetherIIItems.BURRUKAI_PLATE_BOOTS, AetherIIItems.BURRUKAI_PLATE_GLOVES);
    private static final Set<Item> THROWABLES = Set.of(AetherIIBlocks.HOLYSTONE_ROCK.asItem(), AetherIIItems.PRISMALLARD_EGG, AetherIIItems.SKYROOT_PINECONE, AetherIIItems.ARCTIC_SNOWBALL, AetherIIItems.BRETTL_LASSO);
    private static final Set<Item> GLIDERS = Set.of(AetherIIItems.COLD_AERCLOUD_GLIDER, AetherIIItems.GOLDEN_AERCLOUD_GLIDER, AetherIIItems.BLUE_AERCLOUD_GLIDER, AetherIIItems.PURPLE_AERCLOUD_GLIDER);
    public static final Identifier ALKAHEST_OVERLAY = Identifier.fromNamespaceAndPath(AetherII.MODID, "textures/misc/alkahest.png");

    public static int getDefaultDyeColor(ItemStack stack) {
        int color = BEAST_PELT.contains(stack.getItem()) ? 0xFFCFEEF9
                : BURRUKAI_PLATE.contains(stack.getItem()) ? 0xFF619CC0
                : stack.is(AetherIIItems.MOA_SADDLE) ? 0xFF7D8BA3 : 0;
        return DyedItemColor.getOrDefault(stack, color);
    }

    public static boolean isThrowable(ItemStack stack) {
        return THROWABLES.contains(stack.getItem());
    }

    public static @Nullable HumanoidModel.ArmPose getArmPose(LivingEntity entity, InteractionHand hand, ItemStack stack) {
        if (entity.isUsingItem() && entity.getUsedItemHand() == hand) {
            if (isThrowable(stack)) return HumanoidModel.ArmPose.THROW_TRIDENT;
            if (stack.is(AetherIIItems.DART_SHOOTER)) return AetherIIArmPoses.DART_SHOOTER;
            if (GLIDERS.contains(stack.getItem())) return AetherIIArmPoses.GLIDING;
        }
        return null;
    }

    public static boolean transformThrowable(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
            if (player.isUsingItem() && isThrowable(itemInHand)) {
                int i = arm == HumanoidArm.RIGHT ? 1 : -1;
                poseStack.translate((float)i * 0.56F, -0.52F + equipProcess * -0.6F, -0.72F);

                boolean flag3 = arm == HumanoidArm.RIGHT;
                int k = flag3 ? 1 : -1;

                poseStack.translate((float)k * -0.2385682F, 0.28344387F, 0.15731531F);
//                poseStack.rotateDegrees(Axis.XP, -13.935F);
                poseStack.rotateDegrees(Axis.YP, (float)k * 35.3F);
                poseStack.rotateDegrees(Axis.ZP, (float)k * -9.785F);
                float f8 = (float)itemInHand.getUseDuration(player) - ((float)player.getUseItemRemainingTicks() - partialTick + 1.0F);
                float f12 = f8 / 20.0F;
                f12 = (f12 * f12 + f12 * 2.0F) / 3.0F;
                if (f12 > 1.0F) {
                    f12 = 1.0F;
                }

                if (f12 > 0.1F) {
                    float f15 = Mth.sin((f8 - 0.1F) * 1.25F);
                    float f18 = f12 - 0.1F;
                    float f20 = f15 * f18;
                    poseStack.translate(f20 * 0.0F, f20 * 0.004F, f20 * 0.0F);
                }

                poseStack.translate(f12 * 0.0F, f12 * 0.0F, f12 * 0.04F);
//                poseStack.scale(1.0F, 1.0F, 1.0F + f12 * 0.2F);
                poseStack.rotateDegrees(Axis.YN, (float)k * 45.0F);
                return true;
            }
            return false;
        
    }

    public static boolean isUnstableBlock(BlockState state) {
        return state.is(AetherIIBlocks.UNSTABLE_HOLYSTONE) || state.is(AetherIIBlocks.UNSTABLE_UNDERSHALE)
                || state.is(AetherIIBlocks.FRAGILE_ARCTIC_ICE) || state.is(AetherIIBlocks.UNSTABLE_GUARDIAN_ROOTS);
    }

    public static boolean addUnstableDestroyEffects(BlockState state, Level level, BlockPos pos, ParticleEngine manager) {
            VoxelShape voxelshape = state.getShape(level, pos);
            voxelshape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
                double d1 = Math.min(1.0F, maxX - minX);
                double d2 = Math.min(1.0F, maxY - minY);
                double d3 = Math.min(1.0F, maxZ - minZ);
                int i = Math.max(2, Mth.ceil(d1 / (double) 0.25F));
                int j = Math.max(2, Mth.ceil(d2 / (double) 0.25F));
                int k = Math.max(2, Mth.ceil(d3 / (double) 0.25F));

                for (int l = 0; l < i; ++l) {
                    for (int i1 = 0; i1 < j; ++i1) {
                        for (int j1 = 0; j1 < k; ++j1) {
                            double d4 = ((double)l + (double)0.5F) / (double)i;
                            double d5 = ((double)i1 + (double)0.5F) / (double)j;
                            double d6 = ((double)j1 + (double)0.5F) / (double)k;
                            double d7 = d4 * d1 + minX;
                            double d8 = d5 * d2 + minY;
                            double d9 = d6 * d3 + minZ;
                            if (level.getRandom().nextInt(5) == 0) {
                                manager.add((new TerrainParticle((ClientLevel) level, (double) pos.getX() + d7, (double) pos.getY() + d8, (double) pos.getZ() + d9, d4 - (double) 0.5F, d5 - (double) 0.5F, d6 - (double) 0.5F, state, pos)));
                            }
                        }
                    }
                }
            });
            return true;
        
    }

    public static boolean hideEffect(MobEffectInstance effect) {
        var type = effect.getEffect();
        return type.equals(AetherIIMobEffects.NATURAL_CAMOUFLAGE) || type.equals(AetherIIMobEffects.ELECTRIC_SHOCK)
                || type.equals(AetherIIMobEffects.CARRION_TRAP) || type.equals(AetherIIMobEffects.GRAVITATIONAL_PULL)
                || type.equals(AetherIIMobEffects.HEALING_OVERFLOW);
    }
}
