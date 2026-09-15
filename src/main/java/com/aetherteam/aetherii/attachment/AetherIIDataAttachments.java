package com.aetherteam.aetherii.attachment;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.attachment.entity.DroppedItemAttachment;
import com.aetherteam.aetherii.attachment.living.DamageSystemAttachment;
import com.aetherteam.aetherii.attachment.living.EffectsSystemAttachment;
import com.aetherteam.aetherii.attachment.player.*;
import com.aetherteam.aetherii.inventory.container.AccessoryContainer;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class AetherIIDataAttachments {
    // Entity
    public static final AttachmentType<Boolean> LASSO_CONNECTION = AttachmentRegistry.<Boolean>builder()
            .initializer(() -> false)
            .persistent(Codec.BOOL)
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "lasso_connection"));

    public static final AttachmentType<Boolean> COMPANION = AttachmentRegistry.<Boolean>builder()
            .initializer(() -> false)
            .persistent(Codec.BOOL)
            .syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all())
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "companion"));

    public static final AttachmentType<DroppedItemAttachment> DROPPED_ITEM = AttachmentRegistry.<DroppedItemAttachment>builder()
            .initializer(DroppedItemAttachment::new)
            .persistent(DroppedItemAttachment.CODEC.codec())
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "dropped_item"));

    // Living
    public static final AttachmentType<DamageSystemAttachment> DAMAGE_SYSTEM = AttachmentRegistry.<DamageSystemAttachment>builder()
            .initializer(DamageSystemAttachment::new)
            .syncWith(DamageSystemAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "damage_system"));

    public static final AttachmentType<EffectsSystemAttachment> EFFECTS_SYSTEM = AttachmentRegistry.<EffectsSystemAttachment>builder()
            .initializer(EffectsSystemAttachment::new)
            .persistent(EffectsSystemAttachment.CODEC.codec())
            .syncWith(EffectsSystemAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "effects_system"));

    public static final AttachmentType<AccessoryContainer> ACCESSORIES = AttachmentRegistry.<AccessoryContainer>builder()
            .initializer(AccessoryContainer::new)
            .persistent(AccessoryContainer.CODEC.codec())
            .syncWith(AccessoryContainer.STREAM_CODEC, AttachmentSyncPredicate.all())
            .copyOnDeath()
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "accessories"));

    // Player
    public static final AttachmentType<AetherIIPlayerAttachment> PLAYER = AttachmentRegistry.<AetherIIPlayerAttachment>builder()
            .initializer(AetherIIPlayerAttachment::new)
            .persistent(AetherIIPlayerAttachment.CODEC.codec())
            .syncWith(AetherIIPlayerAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "player"));

    public static final AttachmentType<SwetLatchAttachment> SWET_LATCH = AttachmentRegistry.<SwetLatchAttachment>builder()
            .initializer(SwetLatchAttachment::new)
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "swet_latch"));

    public static final AttachmentType<AerbunnyMountAttachment> AERBUNNY_MOUNT = AttachmentRegistry.<AerbunnyMountAttachment>builder()
            .initializer(AerbunnyMountAttachment::new)
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "aerbunny_mount"));

    public static final AttachmentType<AbilityBehaviorAttachment> ABILITY_BEHAVIOR = AttachmentRegistry.<AbilityBehaviorAttachment>builder()
            .initializer(AbilityBehaviorAttachment::new)
            .persistent(AbilityBehaviorAttachment.CODEC.codec())
            .syncWith(AbilityBehaviorAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "ability_behavior"));

    public static final AttachmentType<CurrencyAttachment> CURRENCY = AttachmentRegistry.<CurrencyAttachment>builder()
            .initializer(CurrencyAttachment::new)
            .persistent(CurrencyAttachment.CODEC.codec())
            .syncWith(CurrencyAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
            .copyOnDeath()
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "currency"));

    public static final AttachmentType<GuidebookDiscoveryAttachment> GUIDEBOOK_DISCOVERY = AttachmentRegistry.<GuidebookDiscoveryAttachment>builder()
            .initializer(GuidebookDiscoveryAttachment::new)
            .persistent(GuidebookDiscoveryAttachment.CODEC.codec())
            .syncWith(GuidebookDiscoveryAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
            .copyOnDeath()
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "guidebook_discovery"));

    public static final AttachmentType<OutpostTrackerAttachment> OUTPOST_TRACKER = AttachmentRegistry.<OutpostTrackerAttachment>builder()
            .initializer(OutpostTrackerAttachment::new)
            .persistent(OutpostTrackerAttachment.CODEC.codec())
            .syncWith(OutpostTrackerAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
            .copyOnDeath()
            .buildAndRegister(Identifier.fromNamespaceAndPath(AetherII.MODID, "outpost_tracker"));

    public static void init() {
    }
}