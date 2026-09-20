package com.aetherteam.aetherii.network.packet.clientbound;

import com.aetherteam.aetherii.client.ClientRecipeCache;
import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.client.AetherIIClientCaches;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

/**
 * Sends the recipes of the mod's custom recipe types to the client (NeoForge's {@code OnDatapackSyncEvent#sendRecipes});
 * the guidebook and integrations read them from {@link AetherIIClientCaches#CLIENT_CACHES}.
 */
public record RecipeSyncPacket(List<RecipeHolder<?>> recipes) implements CustomPacketPayload {
    public static final Type<RecipeSyncPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "sync_recipes"));

    public static final StreamCodec<RegistryFriendlyByteBuf, RecipeSyncPacket> STREAM_CODEC = StreamCodec.composite(
            RecipeHolder.STREAM_CODEC.apply(ByteBufCodecs.list()), RecipeSyncPacket::recipes,
            RecipeSyncPacket::new);

    @Environment(EnvType.CLIENT)
    public static void handleClient(RecipeSyncPacket packet, Player player) {
        AetherIIClientCaches.CLIENT_CACHES = ClientRecipeCache.of(packet.recipes);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
