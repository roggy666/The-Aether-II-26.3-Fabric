package com.aetherteam.aetherii.world.structure.piece.sentry;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.construction.SentryBlock;
import com.aetherteam.aetherii.world.structure.piece.AetherTemplateStructurePiece;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.List;
import java.util.function.Function;

/**
 * Superclass for all Bronze Dungeon structure pieces. This exists to simplify the code.
 */
public abstract class SentryRuinsPiece extends AetherTemplateStructurePiece {

    public static final RuleProcessor CAVE_REPLACEABLE = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new BlockMatchTest(AetherIIBlocks.ORANGE_CLOUDWOOL), new BlockMatchTest(Blocks.AIR), Blocks.AIR.defaultBlockState()),
            new ProcessorRule(new BlockMatchTest(AetherIIBlocks.ORANGE_CLOUDWOOL), AlwaysTrueTest.INSTANCE, AetherIIBlocks.UNDERSHALE_BRICKS.defaultBlockState()),
            new ProcessorRule(new BlockMatchTest(AetherIIBlocks.LIME_CLOUDWOOL), new BlockMatchTest(Blocks.AIR), Blocks.AIR.defaultBlockState()),
            new ProcessorRule(new BlockMatchTest(AetherIIBlocks.LIME_CLOUDWOOL), AlwaysTrueTest.INSTANCE, AetherIIBlocks.UNDERSHALE_FLAGSTONES.defaultBlockState()),
            new ProcessorRule(new BlockMatchTest(AetherIIBlocks.CYAN_CLOUDWOOL), new BlockMatchTest(Blocks.AIR), Blocks.AIR.defaultBlockState()),
            new ProcessorRule(new BlockMatchTest(AetherIIBlocks.CYAN_CLOUDWOOL), AlwaysTrueTest.INSTANCE, AetherIIBlocks.UNDERSHALE_BASE_BRICKS.defaultBlockState())
    ));

    public static final RuleProcessor SENTRY_STONE = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_BRICKS, 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BRICKS.defaultBlockState()),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_FLAGSTONES, 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_FLAGSTONES.defaultBlockState()),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_BASE_BRICKS, 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BASE_BRICKS.defaultBlockState()),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_CAPSTONE_BRICKS, 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_CAPSTONE_BRICKS.defaultBlockState()),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_BASE_PILLAR.defaultBlockState(), 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BASE_PILLAR.defaultBlockState()),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_CAPSTONE_PILLAR.defaultBlockState(), 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_CAPSTONE_PILLAR.defaultBlockState()),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_PILLAR.defaultBlockState(), 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_PILLAR.defaultBlockState()),

            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_BRICKS, 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BRICKS.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_FLAGSTONES, 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_FLAGSTONES.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_BASE_BRICKS, 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BASE_BRICKS.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_CAPSTONE_BRICKS, 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_CAPSTONE_BRICKS.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_BASE_PILLAR.defaultBlockState(), 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BASE_PILLAR.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_CAPSTONE_PILLAR.defaultBlockState(), 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_CAPSTONE_PILLAR.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_PILLAR.defaultBlockState(), 0.05F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_PILLAR.defaultBlockState().setValue(SentryBlock.LIT, false))
    ));

    public static final List<ProcessorRule> SENTRY_STONE_LIST_REDUCED = ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_BRICKS, 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BRICKS.defaultBlockState()),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_FLAGSTONES, 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_FLAGSTONES.defaultBlockState()),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_BASE_BRICKS, 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BASE_BRICKS.defaultBlockState()),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_CAPSTONE_BRICKS, 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_CAPSTONE_BRICKS.defaultBlockState()),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_BASE_PILLAR.defaultBlockState(), 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BASE_PILLAR.defaultBlockState()),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_CAPSTONE_PILLAR.defaultBlockState(), 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_CAPSTONE_PILLAR.defaultBlockState()),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_PILLAR.defaultBlockState(), 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_PILLAR.defaultBlockState()),

            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_BRICKS, 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BRICKS.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_FLAGSTONES, 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_FLAGSTONES.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_BASE_BRICKS, 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BASE_BRICKS.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.UNDERSHALE_CAPSTONE_BRICKS, 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_CAPSTONE_BRICKS.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_BASE_PILLAR.defaultBlockState(), 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_BASE_PILLAR.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_CAPSTONE_PILLAR.defaultBlockState(), 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_CAPSTONE_PILLAR.defaultBlockState().setValue(SentryBlock.LIT, false)),
            new ProcessorRule(new RandomBlockStateMatchTest(AetherIIBlocks.UNDERSHALE_PILLAR.defaultBlockState(), 0.02F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.SENTRY_PILLAR.defaultBlockState().setValue(SentryBlock.LIT, false))
    );

    public static final RuleProcessor SENTRY_STONE_REDUCED = new RuleProcessor(SENTRY_STONE_LIST_REDUCED);

    public static final RuleProcessor ROOM_DECORATION_RANDOMIZATION = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.SENTRY_TRAP, 0.8F), AlwaysTrueTest.INSTANCE, AetherIIBlocks.UNDERSHALE_TILE.defaultBlockState()),
            new ProcessorRule(new RandomBlockMatchTest(AetherIIBlocks.SENTRY_CRATE, 0.65F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState())
    ));

    public static final RuleProcessor STAIRCASE_EXPOSED = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new BlockMatchTest(AetherIIBlocks.UNDERSHALE_BRICK_WALL), new BlockMatchTest(Blocks.AIR), Blocks.AIR.defaultBlockState()),
            new ProcessorRule(new BlockMatchTest(AetherIIBlocks.BROWN_CLOUDWOOL), new RandomBlockMatchTest(Blocks.AIR, 0.25F), Blocks.AIR.defaultBlockState()),
            new ProcessorRule(new BlockMatchTest(AetherIIBlocks.BROWN_CLOUDWOOL), AlwaysTrueTest.INSTANCE, AetherIIBlocks.UNDERSHALE_BRICKS.defaultBlockState())

    ));

    public SentryRuinsPiece(StructurePieceType type, StructureTemplateManager manager, String name, StructurePlaceSettings settings, BlockPos pos, Holder<StructureProcessorList> processors) {
        this(type, manager, makeLocation(name), settings, pos, processors);
    }

    public SentryRuinsPiece(StructurePieceType type, StructureTemplateManager manager, Identifier name, StructurePlaceSettings settings, BlockPos pos, Holder<StructureProcessorList> processors) {
        super(type, manager, name, settings, pos, processors);
    }

    public SentryRuinsPiece(StructurePieceType type, RegistryAccess access, CompoundTag tag, StructureTemplateManager manager, Function<Identifier, StructurePlaceSettings> settingsFactory) {
        super(type, access, tag, manager, settingsFactory);
    }

    protected static Identifier makeLocation(String name) {
        return Identifier.fromNamespaceAndPath(AetherII.MODID, "sentry_ruins/" + name);
    }
}