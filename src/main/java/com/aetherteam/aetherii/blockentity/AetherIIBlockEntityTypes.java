package com.aetherteam.aetherii.blockentity;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;

import java.util.Set;

public class AetherIIBlockEntityTypes {
    public static final BlockEntityType<IcestoneBlockEntity> ICESTONE = register("icestone", IcestoneBlockEntity::new,
            AetherIIBlocks.ICESTONE, AetherIIBlocks.ICESTONE_STAIRS, AetherIIBlocks.ICESTONE_SLAB, AetherIIBlocks.ICESTONE_WALL);

    public static final BlockEntityType<HolystoneFurnaceBlockEntity> HOLYSTONE_FURNACE = register("holystone_furnace", HolystoneFurnaceBlockEntity::new,
            AetherIIBlocks.HOLYSTONE_FURNACE);

    public static final BlockEntityType<HolystoneSmokerBlockEntity> HOLYSTONE_SMOKER = register("holystone_smoker", HolystoneSmokerBlockEntity::new,
            AetherIIBlocks.HOLYSTONE_SMOKER);

    public static final BlockEntityType<SkyrootChestBlockEntity> SKYROOT_CHEST = register("skyroot_chest", SkyrootChestBlockEntity::new,
            AetherIIBlocks.SKYROOT_CHEST);

    public static final BlockEntityType<AmberHourglassBlockEntity> AMBER_HOURGLASS = register("amber_hourglass", AmberHourglassBlockEntity::new,
            AetherIIBlocks.AMBER_HOURGLASS);

    public static final BlockEntityType<AltarBlockEntity> ALTAR = register("altar", AltarBlockEntity::new,
            AetherIIBlocks.ALTAR);

    public static final BlockEntityType<ArkeniumForgeBlockEntity> ARKENIUM_FORGE = register("arkenium_forge", ArkeniumForgeBlockEntity::new,
            AetherIIBlocks.ARKENIUM_FORGE);

    public static final BlockEntityType<AlkahestPurifierBlockEntity> ALKAHEST_PURIFIER = register("alkahest_purifier", AlkahestPurifierBlockEntity::new,
            AetherIIBlocks.ALKAHEST_PURIFIER);

    public static final BlockEntityType<MusicBlockEntity> MUSIC_BLOCK = register("music_block", MusicBlockEntity::new,
            AetherIIBlocks.MUSIC_BLOCK);

    public static final BlockEntityType<AmbrosiumCampfireBlockEntity> AMBROSIUM_CAMPFIRE = register("ambrosium_campfire", AmbrosiumCampfireBlockEntity::new,
            AetherIIBlocks.AMBROSIUM_CAMPFIRE);


    public static final BlockEntityType<AnimalStashBlockEntity> ANIMAL_STASH = register("animal_stash", AnimalStashBlockEntity::new,
            AetherIIBlocks.ANIMAL_STASH);

    public static final BlockEntityType<MoaEggBlockEntity> MOA_EGG = register("moa_egg", MoaEggBlockEntity::new,
            AetherIIBlocks.MOA_EGG);

    public static final BlockEntityType<OutpostCampfireBlockEntity> OUTPOST_CAMPFIRE = register("outpost_campfire", OutpostCampfireBlockEntity::new,
            AetherIIBlocks.OUTPOST_CAMPFIRE);

    public static final BlockEntityType<MuralBlockEntity> MURAL = register("mural", MuralBlockEntity::new,
            AetherIIBlocks.MURAL);

    public static final BlockEntityType<VaseBlockEntity> VASE = register("vase", VaseBlockEntity::new,
            AetherIIBlocks.HOLYSTONE_VASE, AetherIIBlocks.VERADEXIAN_VASE, AetherIIBlocks.BREXALLEN_VASE);

    public static final BlockEntityType<SentryCrateBlockEntity> SENTRY_CRATE = register("sentry_crate", SentryCrateBlockEntity::new,
            AetherIIBlocks.SENTRY_CRATE);

    public static final BlockEntityType<SentrySpawnerBlockEntity> SENTRY_SPAWNER = register("wall_spawner", SentrySpawnerBlockEntity::new,
            AetherIIBlocks.SENTRY_SPAWNER);

    public static final BlockEntityType<SentryTrapBlockEntity> SENTRY_TRAP = register("sentry_trap", SentryTrapBlockEntity::new,
            AetherIIBlocks.SENTRY_TRAP);

    public static final BlockEntityType<GuardianDonationBoxBlockEntity> GUARDIAN_DONATION_BOX = register("guardian_donation_box", GuardianDonationBoxBlockEntity::new,
            AetherIIBlocks.GUARDIAN_DONATION_BOX);

    public static final BlockEntityType<AbandonedBagBlockEntity> ABANDONED_BAG = register("abandoned_bag", AbandonedBagBlockEntity::new,
            AetherIIBlocks.ABANDONED_BAG);

    public static final BlockEntityType<FungalCacheBlockEntity> FUNGAL_CACHE = register("fungal_cache", FungalCacheBlockEntity::new,
            AetherIIBlocks.FUNGAL_CACHE);

    public static final BlockEntityType<SageChestBlockEntity> SAGE_CHEST = register("sage_chest", SageChestBlockEntity::new,
            AetherIIBlocks.SAGE_CHEST);

    public static final BlockEntityType<LockedBlockEntity> LOCKED_BLOCK = register("locked_block", LockedBlockEntity::new,
            AetherIIBlocks.LOCKED_BLOCK);

    public static final BlockEntityType<BossDoorwayBlockEntity> BOSS_DOORWAY_BLOCK = register("boss_doorway_block", BossDoorwayBlockEntity::new,
            AetherIIBlocks.BOSS_DOORWAY_BLOCK);

    public static final BlockEntityType<TreasureDoorwayBlockEntity> TREASURE_DOORWAY_BLOCK = register("treasure_doorway_block", TreasureDoorwayBlockEntity::new,
            AetherIIBlocks.TREASURE_DOORWAY_BLOCK);

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), new BlockEntityType<>(factory, Set.of(blocks)));
    }

    private static void addBlocksToType(BlockEntityType<?> type, Block... blocks) {
        for (Block block : blocks) {
            type.addValidBlock(block);
        }
    }

    public static void registerValidBlockEntityTypes() {
        addBlocksToType(BlockEntityTypes.SIGN,
                AetherIIBlocks.SKYROOT_WALL_SIGN, AetherIIBlocks.SKYROOT_SIGN,
                AetherIIBlocks.GREATROOT_WALL_SIGN, AetherIIBlocks.GREATROOT_SIGN,
                AetherIIBlocks.WISPROOT_WALL_SIGN, AetherIIBlocks.WISPROOT_SIGN,
                AetherIIBlocks.AMBEROOT_WALL_SIGN, AetherIIBlocks.AMBEROOT_SIGN);
        addBlocksToType(BlockEntityTypes.HANGING_SIGN,
                AetherIIBlocks.SKYROOT_WALL_HANGING_SIGN, AetherIIBlocks.SKYROOT_HANGING_SIGN,
                AetherIIBlocks.GREATROOT_WALL_HANGING_SIGN, AetherIIBlocks.GREATROOT_HANGING_SIGN,
                AetherIIBlocks.WISPROOT_WALL_HANGING_SIGN, AetherIIBlocks.WISPROOT_HANGING_SIGN,
                AetherIIBlocks.AMBEROOT_WALL_HANGING_SIGN, AetherIIBlocks.AMBEROOT_HANGING_SIGN);
        addBlocksToType(BlockEntityTypes.SHELF,
                AetherIIBlocks.SKYROOT_SHELF,
                AetherIIBlocks.GREATROOT_SHELF,
                AetherIIBlocks.WISPROOT_SHELF,
                AetherIIBlocks.AMBEROOT_SHELF);
        addBlocksToType(BlockEntityTypes.BARREL,
                AetherIIBlocks.SKYROOT_BARREL);
        addBlocksToType(BlockEntityTypes.CAMPFIRE,
                AetherIIBlocks.AMBROSIUM_CAMPFIRE);
    }

    public static void init() {}
}