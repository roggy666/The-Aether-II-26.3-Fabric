package com.aetherteam.aetherii.client.network;

import com.aetherteam.aetherii.client.AetherIIClientCaches;
import com.aetherteam.aetherii.network.packet.clientbound.RecipeSyncPacket;
import com.aetherteam.aetherii.network.packet.clientbound.ExtraSpawnDataPacket;
import com.aetherteam.aetherii.network.packet.clientbound.DataMapSyncPacket;
import com.aetherteam.aetherii.network.AetherIIPackets;
import com.aetherteam.aetherii.network.packet.clientbound.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

@Environment(EnvType.CLIENT)
public class AetherIIClientPackets {
    public static void init() {
        AetherIIPackets.setClientSender(ClientPlayNetworking::send);

        ClientPlayNetworking.registerGlobalReceiver(AerbunnyMessagePacket.TYPE, (payload, context) -> AerbunnyMessagePacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(AlkahestDamageBlockPacket.TYPE, (payload, context) -> AlkahestDamageBlockPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(AlkahestFizzPacket.TYPE, (payload, context) -> AlkahestFizzPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(AlkahestItemSmokePacket.TYPE, (payload, context) -> AlkahestItemSmokePacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(AltarParticlesPacket.TYPE, (payload, context) -> AltarParticlesPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(AttackShockParticlePacket.TYPE, (payload, context) -> AttackShockParticlePacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(AttackStabParticlePacket.TYPE, (payload, context) -> AttackStabParticlePacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(BossInfoPacket.Display.TYPE, (payload, context) -> BossInfoPacket.Display.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(BossInfoPacket.Remove.TYPE, (payload, context) -> BossInfoPacket.Remove.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(BreakItemPacket.TYPE, (payload, context) -> BreakItemPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(ClientGrabItemPacket.TYPE, (payload, context) -> ClientGrabItemPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(DamageTypeParticlePacket.TYPE, (payload, context) -> DamageTypeParticlePacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(FlushGuidebookDataPacket.TYPE, (payload, context) -> FlushGuidebookDataPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(ForgeSoundPacket.TYPE, (payload, context) -> ForgeSoundPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(FreezingParticlePacket.TYPE, (payload, context) -> FreezingParticlePacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(GrassTintSyncPacket.TYPE, (payload, context) -> GrassTintSyncPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(DataMapSyncPacket.TYPE, (payload, context) -> DataMapSyncPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(ExtraSpawnDataPacket.TYPE, (payload, context) -> ExtraSpawnDataPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(RecipeSyncPacket.TYPE, (payload, context) -> {
            AetherIIClientCaches.clearMuralCaches();
            RecipeSyncPacket.handleClient(payload, context.player());
        });
        ClientPlayNetworking.registerGlobalReceiver(GuidebookToastPacket.TYPE, (payload, context) -> GuidebookToastPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(HestveilExplosionEffectsPacket.TYPE, (payload, context) -> HestveilExplosionEffectsPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(HourglassFinishParticlesPacket.TYPE, (payload, context) -> HourglassFinishParticlesPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(HourglassProcessParticlesPacket.TYPE, (payload, context) -> HourglassProcessParticlesPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(MusicBlockPlayPacket.TYPE, (payload, context) -> MusicBlockPlayPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(PortalTravelSoundPacket.TYPE, (payload, context) -> PortalTravelSoundPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(RemountAerbunnyPacket.TYPE, (payload, context) -> RemountAerbunnyPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(ResistanceKnockbackPacket.TYPE, (payload, context) -> ResistanceKnockbackPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(SetAccessoriesPacket.TYPE, (payload, context) -> SetAccessoriesPacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(SetVehiclePacket.TYPE, (payload, context) -> SetVehiclePacket.handleClient(payload, context.player()));
        ClientPlayNetworking.registerGlobalReceiver(SwetSyncPacket.TYPE, (payload, context) -> SwetSyncPacket.handleClient(payload, context.player()));
    }

    public static void sendToServer(CustomPacketPayload payload) {
        ClientPlayNetworking.send(payload);
    }
}
