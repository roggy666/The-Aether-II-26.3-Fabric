package com.aetherteam.aetherii.data.resources.builders.models;

import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class AetherIIBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();

    public static final BlockFamily SKYROOT_PLANKS = familyBuilder(AetherIIBlocks.SKYROOT_PLANKS)
            .button(AetherIIBlocks.SKYROOT_BUTTON)
            .fence(AetherIIBlocks.SKYROOT_FENCE)
            .fenceGate(AetherIIBlocks.SKYROOT_FENCE_GATE)
            .pressurePlate(AetherIIBlocks.SKYROOT_PRESSURE_PLATE)
            .sign(AetherIIBlocks.SKYROOT_SIGN, AetherIIBlocks.SKYROOT_WALL_SIGN)
            .slab(AetherIIBlocks.SKYROOT_SLAB)
            .stairs(AetherIIBlocks.SKYROOT_STAIRS)
            .door(AetherIIBlocks.SKYROOT_DOOR)
            .trapdoor(AetherIIBlocks.SKYROOT_TRAPDOOR)
            .getFamily();
    public static final BlockFamily GREATROOT_PLANKS = familyBuilder(AetherIIBlocks.GREATROOT_PLANKS)
            .button(AetherIIBlocks.GREATROOT_BUTTON)
            .fence(AetherIIBlocks.GREATROOT_FENCE)
            .fenceGate(AetherIIBlocks.GREATROOT_FENCE_GATE)
            .pressurePlate(AetherIIBlocks.GREATROOT_PRESSURE_PLATE)
            .sign(AetherIIBlocks.GREATROOT_SIGN, AetherIIBlocks.GREATROOT_WALL_SIGN)
            .slab(AetherIIBlocks.GREATROOT_SLAB)
            .stairs(AetherIIBlocks.GREATROOT_STAIRS)
            .door(AetherIIBlocks.GREATROOT_DOOR)
            .trapdoor(AetherIIBlocks.GREATROOT_TRAPDOOR)
            .getFamily();
    public static final BlockFamily WISPROOT_PLANKS = familyBuilder(AetherIIBlocks.WISPROOT_PLANKS)
            .button(AetherIIBlocks.WISPROOT_BUTTON)
            .fence(AetherIIBlocks.WISPROOT_FENCE)
            .fenceGate(AetherIIBlocks.WISPROOT_FENCE_GATE)
            .pressurePlate(AetherIIBlocks.WISPROOT_PRESSURE_PLATE)
            .sign(AetherIIBlocks.WISPROOT_SIGN, AetherIIBlocks.WISPROOT_WALL_SIGN)
            .slab(AetherIIBlocks.WISPROOT_SLAB)
            .stairs(AetherIIBlocks.WISPROOT_STAIRS)
            .door(AetherIIBlocks.WISPROOT_DOOR)
            .trapdoor(AetherIIBlocks.WISPROOT_TRAPDOOR)
            .getFamily();
    public static final BlockFamily AMBEROOT_PLANKS = familyBuilder(AetherIIBlocks.AMBEROOT_PLANKS)
            .button(AetherIIBlocks.AMBEROOT_BUTTON)
            .fence(AetherIIBlocks.AMBEROOT_FENCE)
            .fenceGate(AetherIIBlocks.AMBEROOT_FENCE_GATE)
            .pressurePlate(AetherIIBlocks.AMBEROOT_PRESSURE_PLATE)
            .sign(AetherIIBlocks.AMBEROOT_SIGN, AetherIIBlocks.AMBEROOT_WALL_SIGN)
            .slab(AetherIIBlocks.AMBEROOT_SLAB)
            .stairs(AetherIIBlocks.AMBEROOT_STAIRS)
            .door(AetherIIBlocks.AMBEROOT_DOOR)
            .trapdoor(AetherIIBlocks.AMBEROOT_TRAPDOOR)
            .getFamily();
    public static final BlockFamily HOLYSTONE = familyBuilder(AetherIIBlocks.HOLYSTONE)
            .button(AetherIIBlocks.HOLYSTONE_BUTTON)
            .pressurePlate(AetherIIBlocks.HOLYSTONE_PRESSURE_PLATE)
            .wall(AetherIIBlocks.HOLYSTONE_WALL)
            .slab(AetherIIBlocks.HOLYSTONE_SLAB)
            .stairs(AetherIIBlocks.HOLYSTONE_STAIRS)
            .getFamily();
    public static final BlockFamily MOSSY_HOLYSTONE = familyBuilder(AetherIIBlocks.MOSSY_HOLYSTONE)
            .wall(AetherIIBlocks.MOSSY_HOLYSTONE_WALL)
            .slab(AetherIIBlocks.MOSSY_HOLYSTONE_SLAB)
            .stairs(AetherIIBlocks.MOSSY_HOLYSTONE_STAIRS)
            .getFamily();
    public static final BlockFamily IRRADIATED_HOLYSTONE = familyBuilder(AetherIIBlocks.IRRADIATED_HOLYSTONE)
            .wall(AetherIIBlocks.IRRADIATED_HOLYSTONE_WALL)
            .slab(AetherIIBlocks.IRRADIATED_HOLYSTONE_SLAB)
            .stairs(AetherIIBlocks.IRRADIATED_HOLYSTONE_STAIRS)
            .getFamily();
    public static final BlockFamily HOLYSTONE_BRICKS = familyBuilder(AetherIIBlocks.HOLYSTONE_BRICKS)
            .wall(AetherIIBlocks.HOLYSTONE_BRICK_WALL)
            .slab(AetherIIBlocks.HOLYSTONE_BRICK_SLAB)
            .stairs(AetherIIBlocks.HOLYSTONE_BRICK_STAIRS)
            .getFamily();
    public static final BlockFamily FADED_HOLYSTONE_BRICKS = familyBuilder(AetherIIBlocks.FADED_HOLYSTONE_BRICKS)
            .wall(AetherIIBlocks.FADED_HOLYSTONE_BRICK_WALL)
            .slab(AetherIIBlocks.FADED_HOLYSTONE_BRICK_SLAB)
            .stairs(AetherIIBlocks.FADED_HOLYSTONE_BRICK_STAIRS)
            .getFamily();
    public static final BlockFamily UNDERSHALE = familyBuilder(AetherIIBlocks.UNDERSHALE)
            .wall(AetherIIBlocks.UNDERSHALE_WALL)
            .slab(AetherIIBlocks.UNDERSHALE_SLAB)
            .stairs(AetherIIBlocks.UNDERSHALE_STAIRS)
            .getFamily();
    public static final BlockFamily UNDERSHALE_BRICKS = familyBuilder(AetherIIBlocks.UNDERSHALE_BRICKS)
            .wall(AetherIIBlocks.UNDERSHALE_BRICK_WALL)
            .slab(AetherIIBlocks.UNDERSHALE_BRICK_SLAB)
            .stairs(AetherIIBlocks.UNDERSHALE_BRICK_STAIRS)
            .getFamily();
    public static final BlockFamily ICHORITE = familyBuilder(AetherIIBlocks.ICHORITE)
            .wall(AetherIIBlocks.ICHORITE_WALL)
            .slab(AetherIIBlocks.ICHORITE_SLAB)
            .stairs(AetherIIBlocks.ICHORITE_STAIRS)
            .getFamily();
    public static final BlockFamily SMOOTH_ICHORITE = familyBuilder(AetherIIBlocks.SMOOTH_ICHORITE)
            .wall(AetherIIBlocks.SMOOTH_ICHORITE_WALL)
            .slab(AetherIIBlocks.SMOOTH_ICHORITE_SLAB)
            .stairs(AetherIIBlocks.SMOOTH_ICHORITE_STAIRS)
            .getFamily();
    public static final BlockFamily ICHORITE_BRICKS = familyBuilder(AetherIIBlocks.ICHORITE_BRICKS)
            .wall(AetherIIBlocks.ICHORITE_BRICK_WALL)
            .slab(AetherIIBlocks.ICHORITE_BRICK_SLAB)
            .stairs(AetherIIBlocks.ICHORITE_BRICK_STAIRS)
            .getFamily();
    public static final BlockFamily MARBLED_ICHORITE = familyBuilder(AetherIIBlocks.MARBLED_ICHORITE)
            .wall(AetherIIBlocks.MARBLED_ICHORITE_WALL)
            .slab(AetherIIBlocks.MARBLED_ICHORITE_SLAB)
            .stairs(AetherIIBlocks.MARBLED_ICHORITE_STAIRS)
            .getFamily();
    public static final BlockFamily MARBLED_BRICKS = familyBuilder(AetherIIBlocks.MARBLED_BRICKS)
            .wall(AetherIIBlocks.MARBLED_BRICK_WALL)
            .slab(AetherIIBlocks.MARBLED_BRICK_SLAB)
            .stairs(AetherIIBlocks.MARBLED_BRICK_STAIRS)
            .getFamily();
    public static final BlockFamily AGIOSITE = familyBuilder(AetherIIBlocks.AGIOSITE)
            .wall(AetherIIBlocks.AGIOSITE_WALL)
            .slab(AetherIIBlocks.AGIOSITE_SLAB)
            .stairs(AetherIIBlocks.AGIOSITE_STAIRS)
            .getFamily();
    public static final BlockFamily AGIOSITE_BRICKS = familyBuilder(AetherIIBlocks.AGIOSITE_BRICKS)
            .wall(AetherIIBlocks.AGIOSITE_BRICK_WALL)
            .slab(AetherIIBlocks.AGIOSITE_BRICK_SLAB)
            .stairs(AetherIIBlocks.AGIOSITE_BRICK_STAIRS)
            .getFamily();
    public static final BlockFamily ICESTONE = familyBuilder(AetherIIBlocks.ICESTONE)
            .wall(AetherIIBlocks.ICESTONE_WALL)
            .slab(AetherIIBlocks.ICESTONE_SLAB)
            .stairs(AetherIIBlocks.ICESTONE_STAIRS)
            .getFamily();
    public static final BlockFamily ICESTONE_BRICKS = familyBuilder(AetherIIBlocks.ICESTONE_BRICKS)
            .wall(AetherIIBlocks.ICESTONE_BRICK_WALL)
            .slab(AetherIIBlocks.ICESTONE_BRICK_SLAB)
            .stairs(AetherIIBlocks.ICESTONE_BRICK_STAIRS)
            .getFamily();

    private static BlockFamily.Builder familyBuilder(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockfamily = MAP.put(baseBlock, builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(baseBlock));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
