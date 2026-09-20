package com.aetherteam.aetherii.data.generators;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.api.registries.AetherIIRegistries;
import com.aetherteam.aetherii.data.resources.registries.*;
import com.aetherteam.aetherii.data.resources.registries.pools.AetherIIPools;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Exports every entry the mod adds to the dynamic registries (built by {@link #build} through
 * {@link com.aetherteam.aetherii.data.AetherIIData#buildRegistry}).
 */
public class AetherIIRegistrySets extends FabricDynamicRegistryProvider {
    /** Reloadable (datapack-reload) registries live in their own layer; see {@link com.aetherteam.aetherii.data.AetherIIData#buildReloadableRegistry}. */
    public static RegistrySetBuilder buildReloadable(RegistrySetBuilder builder) {
        return builder.add(Registries.CONTEXT_INT_PROVIDER, AetherIIContextIntProviders::bootstrap);
    }

    public static RegistrySetBuilder build(RegistrySetBuilder builder) {
        return builder
                .add(Registries.BIOME, AetherIIBiomes::bootstrap)
                .add(Registries.DENSITY_FUNCTION, AetherIIDensityFunctions::bootstrap)
                .add(Registries.NOISE, AetherIINoises::bootstrap)
                .add(Registries.NOISE_SETTINGS, AetherIINoiseSettings::bootstrap)
                .add(Registries.DIMENSION_TYPE, AetherIIDimensions::bootstrapDimensionType)
                .add(Registries.LEVEL_STEM, AetherIIDimensions::bootstrapLevelStem)
                .add(Registries.FEATURE, AetherIIConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, AetherIIPlacedFeatures::bootstrap)
                .add(Registries.CARVER, AetherIICarvers::bootstrap)
                .add(Registries.STRUCTURE, AetherIIStructures::bootstrap)
                .add(Registries.STRUCTURE_SET, AetherIIStructureSets::bootstrap)
                .add(Registries.TEMPLATE_POOL, AetherIIPools::bootstrap)
                .add(Registries.PROCESSOR_LIST, AetherIIProcessorLists::bootstrap)
                .add(Registries.DAMAGE_TYPE, AetherIIDamageTypes::bootstrap)
                .add(Registries.JUKEBOX_SONG, AetherIIJukeboxSongs::bootstrap)
                .add(AetherIIRegistries.BESTIARY_ENTRY, AetherIIBestiaryEntries::bootstrap)
                .add(AetherIIRegistries.EFFECTS_ENTRY, AetherIIEffectsEntries::bootstrap)
                .add(AetherIIRegistries.EXPLORATION_ENTRY, AetherIIExplorationEntries::bootstrap)
                .add(AetherIIRegistries.STYLE_DESIGN, AetherIIStyleDesigns::bootstrap)
                .add(AetherIIRegistries.STYLE_MATERIAL, AetherIIStyleMaterials::bootstrap)
                .add(AetherIIRegistries.ITEM_REINFORCEMENT, AetherIIItemReinforcements::bootstrap)
                .add(AetherIIRegistries.SKYROOT_LIZARD_VARIANT, AetherIISkyrootLizardVariants::bootstrap)
                .add(AetherIIRegistries.GLITTERWING_VARIANT, AetherIIGlitterwingVariants::bootstrap)
                .add(AetherIIRegistries.SHROUDWING_VARIANT, AetherIIShroudwingVariants::bootstrap)
                .add(AetherIIRegistries.REWARD_WRAPPER, AetherIIRewardWrappers::bootstrap);
    }

    /** Every registry {@link #build} adds to; {@link net.minecraft.core.RegistrySetBuilder} keeps its keys private. */
    public static final List<ResourceKey<? extends Registry<?>>> REGISTRIES = List.of(
            Registries.BIOME,
            Registries.DENSITY_FUNCTION,
            Registries.NOISE,
            Registries.NOISE_SETTINGS,
            Registries.DIMENSION_TYPE,
            Registries.LEVEL_STEM,
            Registries.FEATURE,
            Registries.PLACED_FEATURE,
            Registries.CARVER,
            Registries.STRUCTURE,
            Registries.STRUCTURE_SET,
            Registries.TEMPLATE_POOL,
            Registries.PROCESSOR_LIST,
            Registries.DAMAGE_TYPE,
            Registries.JUKEBOX_SONG,
            Registries.CONTEXT_INT_PROVIDER,
            AetherIIRegistries.BESTIARY_ENTRY,
            AetherIIRegistries.EFFECTS_ENTRY,
            AetherIIRegistries.EXPLORATION_ENTRY,
            AetherIIRegistries.STYLE_DESIGN,
            AetherIIRegistries.STYLE_MATERIAL,
            AetherIIRegistries.ITEM_REINFORCEMENT,
            AetherIIRegistries.SKYROOT_LIZARD_VARIANT,
            AetherIIRegistries.GLITTERWING_VARIANT,
            AetherIIRegistries.SHROUDWING_VARIANT,
            AetherIIRegistries.REWARD_WRAPPER
    );

    public AetherIIRegistrySets(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        for (ResourceKey<? extends Registry<?>> key : REGISTRIES) {
            this.addAll(registries, entries, key);
        }
    }

    private <T> void addAll(HolderLookup.Provider registries, Entries entries, ResourceKey<? extends Registry<?>> key) {
        @SuppressWarnings("unchecked")
        ResourceKey<? extends Registry<T>> typed = (ResourceKey<? extends Registry<T>>) key;
        entries.addAll(registries.lookupOrThrow(typed));
    }

    @Override
    public String getName() {
        return "Registry Sets : " + AetherII.MODID;
    }
}