package com.aetherteam.aetherii.inventory.menu.provider;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.function.Function;

/**
 * Menu provider that sends extra data to the client (Fabric's {@link ExtendedMenuProvider}; NeoForge used
 * {@code MenuProvider#writeClientSideData}). The menu type must be an {@code ExtendedMenuType} with a matching codec.
 */
public class ExtraDataMenuProvider<D> implements ExtendedMenuProvider<D> {
    private final Component title;
    private final Function<ServerPlayer, D> clientExtraData;
    private final MenuConstructor menuConstructor;

    public ExtraDataMenuProvider(MenuConstructor menuConstructor, Function<ServerPlayer, D> clientExtraData, Component title) {
        this.menuConstructor = menuConstructor;
        this.clientExtraData = clientExtraData;
        this.title = title;
    }

    @Override
    public Component getDisplayName() {
        return this.title;
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return this.menuConstructor.createMenu(i, inventory, player);
    }

    @Override
    public D getScreenOpeningData(ServerPlayer player) {
        return this.clientExtraData.apply(player);
    }
}
