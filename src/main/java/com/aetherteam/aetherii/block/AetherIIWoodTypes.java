package com.aetherteam.aetherii.block;

import com.aetherteam.aetherii.AetherII;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AetherIIWoodTypes {
    public static final BlockSetType SKYROOT_BLOCK_SET = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Identifier.fromNamespaceAndPath(AetherII.MODID, "skyroot"));
    public static final WoodType SKYROOT = WoodTypeBuilder.copyOf(WoodType.OAK).register(Identifier.fromNamespaceAndPath(AetherII.MODID, "skyroot"), SKYROOT_BLOCK_SET);

    public static final BlockSetType GREATROOT_BLOCK_SET = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Identifier.fromNamespaceAndPath(AetherII.MODID, "greatroot"));
    public static final WoodType GREATROOT = WoodTypeBuilder.copyOf(WoodType.OAK).register(Identifier.fromNamespaceAndPath(AetherII.MODID, "greatroot"), GREATROOT_BLOCK_SET);

    public static final BlockSetType WISPROOT_BLOCK_SET = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Identifier.fromNamespaceAndPath(AetherII.MODID, "wisproot"));
    public static final WoodType WISPROOT = WoodTypeBuilder.copyOf(WoodType.OAK).register(Identifier.fromNamespaceAndPath(AetherII.MODID, "wisproot"), WISPROOT_BLOCK_SET);

    public static final BlockSetType AMBEROOT_BLOCK_SET = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Identifier.fromNamespaceAndPath(AetherII.MODID, "amberoot"));
    public static final WoodType AMBEROOT = WoodTypeBuilder.copyOf(WoodType.OAK).register(Identifier.fromNamespaceAndPath(AetherII.MODID, "amberoot"), AMBEROOT_BLOCK_SET);
}