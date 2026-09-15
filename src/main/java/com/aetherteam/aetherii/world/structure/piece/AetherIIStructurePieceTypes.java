package com.aetherteam.aetherii.world.structure.piece;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.world.structure.piece.sentry.SentryRuinsBossRoom;
import com.aetherteam.aetherii.world.structure.piece.sentry.SentryRuinsRoom;
import com.aetherteam.aetherii.world.structure.piece.sentry.SentryRuinsTunnel;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.Locale;

public class AetherIIStructurePieceTypes {
    public static final StructurePieceType SENTRY_RUINS_BOSS_ROOM = register("srbossroom", SentryRuinsBossRoom::new);
    public static final StructurePieceType SENTRY_RUINS_ROOM = register("srdungeonroom", SentryRuinsRoom::new);
    public static final StructurePieceType SENTRY_RUINS_TUNNEL = register("srtunnel", SentryRuinsTunnel::new);

    private static StructurePieceType register(String name, StructurePieceType structurePieceType) {
        return Registry.register(BuiltInRegistries.STRUCTURE_PIECE, Identifier.fromNamespaceAndPath(AetherII.MODID, name.toLowerCase(Locale.ROOT)), structurePieceType);
    }

    public static void init() {}
}