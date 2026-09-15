package com.aetherteam.aetherii.item.equipment.armor.abilities;

import com.aetherteam.aetherii.event.AetherIIEvents;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.AetherIITags;
import com.aetherteam.aetherii.item.equipment.EquipmentUtil;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public interface ArkeniumArmor {
    Identifier ARKENIUM_BLAST_RESISTANCE = Identifier.fromNamespaceAndPath(AetherII.MODID, "armor_set.ability.arkenium.blast_resistance");

    static void updatePlayerAttributes(Player player) {
        AttributeInstance blastResistanceAttribute = player.getAttribute(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE);

        if (EquipmentUtil.hasArmorAbility(player, AetherIITags.Items.ARKENIUM_ARMOR)) {
            if (blastResistanceAttribute != null && !blastResistanceAttribute.hasModifier(ARKENIUM_BLAST_RESISTANCE)) {
                blastResistanceAttribute.addTransientModifier(new AttributeModifier(ARKENIUM_BLAST_RESISTANCE, 0.3F, AttributeModifier.Operation.ADD_VALUE));
            }
        } else {
            if (blastResistanceAttribute != null && blastResistanceAttribute.hasModifier(ARKENIUM_BLAST_RESISTANCE)) {
                blastResistanceAttribute.removeModifier(ARKENIUM_BLAST_RESISTANCE);
            }
        }
    }

    /**
     * Explosions are reduced as if the wearer had 4 extra armor points (NeoForge's armor reduction modifier).
     */
    static void modifyIncomingDamage(LivingEntity entity, AetherIIEvents.DamageContainer container) {
        DamageSource damageSource = container.getSource();
        if (EquipmentUtil.hasArmorAbility(entity, AetherIITags.Items.ARKENIUM_ARMOR)) {
            if (damageSource.is(DamageTypeTags.IS_EXPLOSION) && !damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                float f = Mth.clamp(4.0F, 0.0F, 20.0F);
                container.setNewDamage(container.getNewDamage() * (1.0F - f / 25.0F));
            }
        }
    }
}
