package com.aetherteam.aetherii.world.surfacerule;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.material.MaterialRuleContext;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import net.minecraft.world.level.levelgen.material.rule.RuleEvaluator;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;

import java.util.ArrayList;
import java.util.List;

/**
 * Scatters a block through the surface material using a fixed-seed 3D Perlin noise, weighted by the spot/empty ratio.
 */
public record NoisePalette3DPlacementRule(BlockState spot, int spotRatio, int emptyRatio, double noiseFreq) implements MaterialRule {
    public static final MapCodec<NoisePalette3DPlacementRule> KEY_CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            BlockState.CODEC.fieldOf("spot_blockstate").forGetter(NoisePalette3DPlacementRule::spot),
            Codec.INT.fieldOf("spot_ratio").forGetter(NoisePalette3DPlacementRule::spotRatio),
            Codec.INT.fieldOf("empty_ratio").forGetter(NoisePalette3DPlacementRule::emptyRatio),
            Codec.DOUBLE.fieldOf("noise_frequency").forGetter(NoisePalette3DPlacementRule::noiseFreq)
    ).apply(inst, NoisePalette3DPlacementRule::new));

    @Override
    public RuleEvaluator compile(MaterialRuleContext context) {
        PerlinNoise noise = new PerlinNoise(new XoroshiroRandomSource(0));
        List<BlockState> blockStates = new ArrayList<>();
        for (int i = 0; i < this.spotRatio; i++) blockStates.add(this.spot);
        for (int i = 0; i < this.emptyRatio; i++) blockStates.add(null);
        return (x, y, z) -> {
            double noiseValue = noise.get(x * this.noiseFreq, y * this.noiseFreq, z * this.noiseFreq);
            double normalizedNoise = noiseValue * 0.5 + 0.5;
            double clampedNoise = Mth.clamp(normalizedNoise, 0, 1);
            int lerpedNoise = Mth.lerpDiscrete((float) clampedNoise, 0, blockStates.size() - 1);
            return blockStates.get(lerpedNoise);
        };
    }

    @Override
    public MapCodec<NoisePalette3DPlacementRule> codec() {
        return KEY_CODEC;
    }
}
