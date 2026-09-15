package com.aetherteam.aetherii.client.renderer.item.properties;

import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.client.renderer.item.properties.conditional.*;
import com.aetherteam.aetherii.client.renderer.item.properties.range.*;
import com.aetherteam.aetherii.client.renderer.item.properties.select.SelectFeatherColor;
import com.aetherteam.aetherii.client.renderer.item.properties.select.SelectMoaEggType;
import net.minecraft.resources.Identifier;

public class AetherIIItemModelProperties {
    public static void registerConditionalProperties() {
        ConditionalItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "attached_companion"), AttachedCompanion.MAP_CODEC);
        ConditionalItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "stored_companion"), StoredCompanion.MAP_CODEC);
        ConditionalItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "using_item"), BetterIsUsingItem.MAP_CODEC);
        ConditionalItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "has_block_state"), HasBlockState.MAP_CODEC);
        ConditionalItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "holding_shift"), HoldingShift.MAP_CODEC);
        ConditionalItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "lasso_throw"), LassoThrow.MAP_CODEC);
    }

    public static void registerSelectProperties() {
        SelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "feather_color"), SelectFeatherColor.TYPE);
        SelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "moa_egg/feather_color"), SelectMoaEggType.FeatherColor.TYPE);
        SelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "moa_egg/feather_shape"), SelectMoaEggType.FeatherShape.TYPE);
        SelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "moa_egg/eye_color"), SelectMoaEggType.EyeColor.TYPE);
        SelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "moa_egg/keratin_color"), SelectMoaEggType.KeratinColor.TYPE);
    }

    public static void registerRangeSelectProperties() {
        RangeSelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "better_cooldown"), BetterCooldown.MAP_CODEC);
        RangeSelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "reinforcement_tier"), ReinforcementTierRange.MAP_CODEC);
        RangeSelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "darts_loaded"), DartsLoadedRange.MAP_CODEC);
        RangeSelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "healing_stone_charges"), HealingStoneChargeRange.MAP_CODEC);
        RangeSelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "parachuting"), ParachutingRange.MAP_CODEC);
        RangeSelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AetherII.MODID, "dull_ability"), DullAbilityRange.MAP_CODEC);
    }
}
