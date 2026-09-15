package com.aetherteam.aetherii.item.equipment.tools.abilities;

import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.block.entity.BlockEntity;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.item.CustomEnchantmentItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface HolystoneTool extends CustomEnchantmentItem {
    static void dropAmbrosium(Level level, Player player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity) {
        ItemStack itemStack = player.getMainHandItem();

        if (itemStack.getItem() instanceof HolystoneTool) {
            if (!level.isClientSide() && blockState.getDestroySpeed(level, blockPos) > 0 && itemStack.isCorrectToolForDrops(blockState) && (player.getRandom().nextInt(8) == 0 || blockState.is(AetherIITags.Blocks.HOLYSTONE_ABILITY_GUARANTEED))) {
                ItemEntity itemEntity = new ItemEntity(level, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, new ItemStack(AetherIIItems.AMBROSIUM_SHARD));
                level.addFreshEntity(itemEntity);
            }
        }
    }
}
