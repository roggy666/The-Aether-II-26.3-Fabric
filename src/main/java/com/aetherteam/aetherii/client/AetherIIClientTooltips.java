package com.aetherteam.aetherii.client;

import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;
import com.aetherteam.aetherii.client.renderer.item.tooltip.ClientCharmTooltip;

public class AetherIIClientTooltips {
    public static void registerClientTooltipComponents() {
        ClientTooltipComponentCallback.EVENT.register(data -> data instanceof ClientCharmTooltip.CharmTooltip charm
                ? new ClientCharmTooltip(charm.base(), charm.items()) : null);
    }
}
