package com.aetherteam.aetherii.world;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class AetherIIPoi {
    public static Holder.Reference<PoiType> AETHER_PORTAL;

    private static Set<BlockState> getBlockStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }

    public static void init() {
        AETHER_PORTAL = Registry.registerForHolder(BuiltInRegistries.POINT_OF_INTEREST_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, "aether_portal"), new PoiType(getBlockStates(AetherIIBlocks.AETHER_PORTAL), 0, 1));
    }
}