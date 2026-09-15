package com.aetherteam.aetherii.data.generators;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.client.particle.AetherIIParticleTypes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Writes {@code assets/aether_ii/particles/<name>.json}; same layout NeoForge's {@code ParticleDescriptionProvider} produced.
 */
public class AetherIIParticleData implements DataProvider {
    private final PackOutput.PathProvider pathProvider;
    private final Map<Identifier, List<Identifier>> descriptions = new LinkedHashMap<>();

    public AetherIIParticleData(PackOutput output) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "particles");
    }

    protected void spriteSet(ParticleType<?> type, Identifier texture) {
        this.sprite(type, List.of(texture));
    }

    protected void spriteSet(ParticleType<?> type, Identifier baseName, int numOfTextures, boolean reverse) {
        List<Identifier> textures = new ArrayList<>(numOfTextures);
        for (int i = 0; i < numOfTextures; i++) {
            textures.add(baseName.withSuffix("_" + (reverse ? numOfTextures - 1 - i : i)));
        }
        this.sprite(type, textures);
    }

    protected void spriteSet(ParticleType<?> type, Identifier texture, Identifier... textures) {
        List<Identifier> list = new ArrayList<>(textures.length + 1);
        list.add(texture);
        list.addAll(List.of(textures));
        this.sprite(type, list);
    }

    private void sprite(ParticleType<?> type, List<Identifier> textures) {
        Identifier id = BuiltInRegistries.PARTICLE_TYPE.getKey(type);
        if (this.descriptions.putIfAbsent(id, textures) != null) {
            throw new IllegalArgumentException("Particle description for " + id + " already added");
        }
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        this.addDescriptions();
        List<CompletableFuture<?>> futures = new ArrayList<>();
        this.descriptions.forEach((id, textures) -> {
            JsonArray array = new JsonArray();
            textures.forEach(texture -> array.add(texture.toString()));
            JsonObject json = new JsonObject();
            json.add("textures", array);
            Path path = this.pathProvider.json(id);
            futures.add(DataProvider.saveStable(cache, json, path));
        });
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Particle Descriptions : " + AetherII.MODID;
    }

    protected void addDescriptions() {
        this.spriteSet(AetherIIParticleTypes.AETHER_PORTAL, Identifier.withDefaultNamespace("generic"), 8, false);
        this.spriteSet(AetherIIParticleTypes.SKYROOT_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.SKYPLANE_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.SKYBIRCH_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.SKYPINE_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.WISPROOT_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.WISPTOP_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.GREATROOT_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.GREATOAK_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.GREATBOA_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.AMBEROOT_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.IRRADIATED_LEAVES, Identifier.fromNamespaceAndPath(AetherII.MODID, "leaf"), 4, false);
        this.spriteSet(AetherIIParticleTypes.DRIPPING_WATER, Identifier.fromNamespaceAndPath(AetherII.MODID, "dripping_water"));
        this.spriteSet(AetherIIParticleTypes.FALLING_WATER, Identifier.fromNamespaceAndPath(AetherII.MODID, "falling_water"));
        this.spriteSet(AetherIIParticleTypes.SPLASH, Identifier.fromNamespaceAndPath(AetherII.MODID, "splash"), 4, false);
        this.spriteSet(AetherIIParticleTypes.AMBROSIUM, Identifier.withDefaultNamespace("generic_0"), Identifier.withDefaultNamespace("generic_1"), Identifier.fromNamespaceAndPath(AetherII.MODID, "generic_1_mirrored"));
        this.spriteSet(AetherIIParticleTypes.GLASS_FEATHERS, Identifier.fromNamespaceAndPath(AetherII.MODID, "glass_feathers"), 6, false);
        this.spriteSet(AetherIIParticleTypes.ALKAHEST, Identifier.fromNamespaceAndPath(AetherII.MODID, "alkahest"), 6, false);
        this.spriteSet(AetherIIParticleTypes.HESTVEIL, Identifier.withDefaultNamespace("generic_0"));
        this.spriteSet(AetherIIParticleTypes.DRIPPING_ALKAHEST, Identifier.withDefaultNamespace("drip_hang"));
        this.spriteSet(AetherIIParticleTypes.FALLING_ALKAHEST, Identifier.withDefaultNamespace("drip_fall"));
        this.spriteSet(AetherIIParticleTypes.DRIPPING_DRIPSTONE_ALKAHEST, Identifier.withDefaultNamespace("drip_hang"));
        this.spriteSet(AetherIIParticleTypes.FALLING_DRIPSTONE_ALKAHEST, Identifier.withDefaultNamespace("drip_fall"));
        this.spriteSet(AetherIIParticleTypes.GRAVITY_DUST, Identifier.withDefaultNamespace("generic"), 8, true);

        this.spriteSet(AetherIIParticleTypes.RAIN, Identifier.fromNamespaceAndPath(AetherII.MODID, "splash"), 4, false);
        this.spriteSet(AetherIIParticleTypes.IRRADIATION, Identifier.withDefaultNamespace("generic_0"));

        this.spriteSet(AetherIIParticleTypes.ZEPHYR_SNOWFLAKE, Identifier.withDefaultNamespace("generic"), 8, true);
        this.spriteSet(AetherIIParticleTypes.TEMPEST_ELECTRICITY, Identifier.withDefaultNamespace("generic"), 8, true);
        this.spriteSet(AetherIIParticleTypes.SLASH_DAMAGE, Identifier.fromNamespaceAndPath(AetherII.MODID, "slash_damage"));
        this.spriteSet(AetherIIParticleTypes.IMPACT_DAMAGE, Identifier.fromNamespaceAndPath(AetherII.MODID, "impact_damage"));
        this.spriteSet(AetherIIParticleTypes.PIERCE_DAMAGE, Identifier.fromNamespaceAndPath(AetherII.MODID, "pierce_damage"));
        this.spriteSet(AetherIIParticleTypes.SWEEP_ATTACK, Identifier.fromNamespaceAndPath(AetherII.MODID, "sweep_attack"), 8, false);
        this.spriteSet(AetherIIParticleTypes.SHOCK_ATTACK, Identifier.fromNamespaceAndPath(AetherII.MODID, "shock_attack"), 4, false);
        this.spriteSet(AetherIIParticleTypes.STAB_ATTACK, Identifier.fromNamespaceAndPath(AetherII.MODID, "stab_attack"), 10, false);
        this.spriteSet(AetherIIParticleTypes.EFFECT_BUILDUP, Identifier.withDefaultNamespace("generic"), 8, true);

        this.spriteSet(AetherIIParticleTypes.TEMPEST_SMOKE, Identifier.fromNamespaceAndPath(AetherII.MODID, "tempest_smoke"), 12, false);
        this.spriteSet(AetherIIParticleTypes.MOA_HUNGRY, Identifier.fromNamespaceAndPath(AetherII.MODID, "moa_hungry"));

        this.spriteSet(AetherIIParticleTypes.LOCKED_BLOCK, Identifier.fromNamespaceAndPath(AetherII.MODID, "dungeon_lock"));
        this.spriteSet(AetherIIParticleTypes.BOSS_DOORWAY_BLOCK, Identifier.fromNamespaceAndPath(AetherII.MODID, "dungeon_doorway"));
        this.spriteSet(AetherIIParticleTypes.TREASURE_DOORWAY_BLOCK, Identifier.fromNamespaceAndPath(AetherII.MODID, "dungeon_treasure"));
    }
}