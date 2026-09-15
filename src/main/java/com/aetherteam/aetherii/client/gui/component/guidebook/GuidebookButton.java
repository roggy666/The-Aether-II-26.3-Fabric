package com.aetherteam.aetherii.client.gui.component.guidebook;

import com.aetherteam.aetherii.mixin.mixins.client.accessor.AbstractWidgetAccessor;
import com.aetherteam.aetherii.mixin.mixins.client.accessor.ButtonAccessor;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class GuidebookButton extends Button.Plain {
    private final ItemLike renderItem;

    public GuidebookButton(ItemLike renderItem, Builder builder) {
        this(renderItem, builder.build());
    }

    private GuidebookButton(ItemLike renderItem, Button source) {
        super(source.getX(), source.getY(), source.getWidth(), source.getHeight(), source.getMessage(),
                ((ButtonAccessor) source).aether_ii$getOnPress(), ignored -> ((ButtonAccessor) source).callCreateNarrationMessage());
        this.setTooltip(((AbstractWidgetAccessor) source).aether_ii$getTooltipHolder().get());
        this.renderItem = renderItem;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractContents(graphics, mouseX, mouseY, a);
        graphics.item(new ItemStack(this.renderItem), this.getX() + 3, this.getY() + 3);
    }

}
