package com.aetherteam.aetherii.item.equipment;

import net.minecraft.core.registries.BuiltInRegistries;
import com.aetherteam.aetherii.client.sound.AetherIISoundEvents;
import com.aetherteam.aetherii.entity.AetherIIEntityTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.equipment.Equippable;

public class AetherEquippable {
    public static Equippable moaSaddle() {
        return Equippable.builder(EquipmentSlot.SADDLE)
                .setEquipSound(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(AetherIISoundEvents.ENTITY_MOA_SADDLE))
                .setAllowedEntities(AetherIIEntityTypes.MOA)
                .setEquipOnInteract(true)
                .setCanBeSheared(true)
                .setShearingSound(SoundEvents.SADDLE_UNEQUIP)
                .build();
    }

}
