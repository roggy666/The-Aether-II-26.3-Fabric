package com.aetherteam.aetherii.client;

import java.util.ArrayList;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIIConfig;
import com.aetherteam.aetherii.attachment.AetherIIDataAttachments;
import com.aetherteam.aetherii.attachment.player.AetherIIPlayerAttachment;
import com.aetherteam.aetherii.client.event.hooks.AudioHooks;
import com.aetherteam.aetherii.client.event.hooks.RenderHooks;
import com.aetherteam.aetherii.client.gui.screen.AlphaInfoScreen;
import com.aetherteam.aetherii.client.gui.screen.guidebook.Guidebook;
import com.aetherteam.aetherii.mixin.mixins.client.accessor.DialogScreenAccessor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Either;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.dialog.DialogScreen;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.Identifier;
import net.minecraft.server.dialog.Dialog;
import net.minecraft.sounds.Music;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.UUID;

public class AetherIIClientEventListeners {
    public static void listen() {
        ScreenEvents.AFTER_INIT.register((client, screen, width, height) -> {
            onGuiInitializePost(screen);
            ScreenEvents.remove(screen).register(RenderHooks::storeGuidebookScreen);
        });
    }

    public static Screen onGuiOpen(Screen screen) {

        Screen storedScreen = RenderHooks.openStoredGuidebookScreen(screen);
        if (storedScreen != null) {
            return storedScreen;
        }

        if (screen instanceof DialogScreen<?> dialogScreen) {
            Dialog dialog = ((DialogScreenAccessor<?>) dialogScreen).aether_ii$getDialog();
            if (dialog.common().title().equals(AetherIIPlayerAttachment.getDialog().common().title())) {
                return new AlphaInfoScreen(null);
            }
        }
        return screen;
    }

    public static void onGuiInitializePost(Screen screen) {
        List<GuiEventListener> listeners = new ArrayList<>(screen.children());

        Button inventoryAccessoryButton = RenderHooks.setupAccessoryButton(screen);
        if (inventoryAccessoryButton != null) {
            Screens.getWidgets(screen).add(inventoryAccessoryButton);
        }

        Button outpostRespawnButton = RenderHooks.setupOutpostRespawnButton(screen, listeners);
        if (outpostRespawnButton != null) {
            Screens.getWidgets(screen).add(outpostRespawnButton);
        }

        if (screen instanceof Guidebook) {
            String spriteName = AetherIIConfig.COMMON.yellow_alpha_button.get() ? "alpha_info_yellow" : "alpha_info";
            Button button = SpriteIconButton.builder(Component.literal("Alpha Info"),  (b) -> {
                Minecraft.getInstance().gui.setScreen(new AlphaInfoScreen(screen));
                AetherIIConfig.COMMON.yellow_alpha_button.set(false);
            }, true).size(22, 22).sprite(Identifier.fromNamespaceAndPath(AetherII.MODID, "icon/" + spriteName), 14, 14).build();
            button.setPosition((screen.width / 2) + 54, (screen.height / 2) + 101);
            button.setTooltip(Tooltip.create(Component.literal("Alpha Info")));
            Screens.getWidgets(screen).add(button);
        }
    }









    public static boolean allowSound(SoundEngine soundEngine, SoundInstance sound) {
        boolean allowed = !AudioHooks.preventAmbientPortalSound(soundEngine, sound) && !AudioHooks.preventMusicDuringPortal(soundEngine, sound);
        AudioHooks.overrideActivatedPortalSound(soundEngine, sound);
        return allowed;
    }



    public static void onMouseInputPost(int button, int action) {
        Player player = Minecraft.getInstance().player;
        boolean isUseItem = button == KeyMappingHelper.getBoundKeyOf(Minecraft.getInstance().options.keyUse).getValue();

        if (player != null) {
            player.getAttachedOrCreate(AetherIIDataAttachments.PLAYER).mouseInput(player, isUseItem, action);
        }
    }

    public static void onMovementInputUpdate(Player player, ClientInput input) {

        player.getAttachedOrCreate(AetherIIDataAttachments.PLAYER).movementInput(player, input);
    }




}
