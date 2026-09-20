package com.aetherteam.aetherii.data.resources.registries;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.VeryBiasedToBottomInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;

/**
 * Cave carvers. Since 26.3 carvers no longer take a replaceable block set: everything that is not tagged
 * {@code minecraft:uncarvable} is carved, and the lava level moved into the noise settings' aquifer config.
 */
public class AetherIICarvers {
    public static final ResourceKey<WorldCarver> HOLY_ISLES_CAVE = createKey("holy_isles_cave");
    public static final ResourceKey<WorldCarver> HESTVEIL_CAVE = createKey("hestveil_cave");

    private static ResourceKey<WorldCarver> createKey(String name) {
        return ResourceKey.create(Registries.CARVER, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
    }

    public static void bootstrap(BootstrapContext<WorldCarver> context) {
        context.register(HOLY_ISLES_CAVE, new CaveWorldCarver(
                0.25F,
                TrapezoidHeight.of(VerticalAnchor.aboveBottom(-16), VerticalAnchor.absolute(256)),
                VeryBiasedToBottomInt.of(0, 14),
                TrapezoidFloat.of(0.0F, 3.0F, 1.0F),
                true,
                UniformFloat.of(0.3F, 0.9F),
                UniformFloat.of(0.9F, 2.2F),
                UniformFloat.of(1.0F, 2.0F),
                ConstantFloat.of(1.0F),
                UniformFloat.of(-1.0F, -0.4F)));
        context.register(HESTVEIL_CAVE, new CaveWorldCarver(
                0.3F,
                TrapezoidHeight.of(VerticalAnchor.aboveBottom(-16), VerticalAnchor.absolute(72)),
                VeryBiasedToBottomInt.of(0, 14),
                TrapezoidFloat.of(0.0F, 3.0F, 1.0F),
                true,
                UniformFloat.of(0.4F, 0.9F),
                UniformFloat.of(0.5F, 1.25F),
                UniformFloat.of(1.3F, 2.4F),
                ConstantFloat.of(1.0F),
                UniformFloat.of(-1.0F, -0.3F)));
    }
}
