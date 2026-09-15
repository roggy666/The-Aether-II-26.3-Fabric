package com.aetherteam.aetherii.advancement;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.api.AdvancementSoundOverride;
import com.aetherteam.aetherii.api.registries.AetherIIRegistries;
import com.aetherteam.aetherii.client.AetherIIClientProxy;
import com.aetherteam.aetherii.client.sound.AetherIISoundEvents;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.Nullable;

public class AetherIIAdvancementSoundOverrides {
    public static final Registry<AdvancementSoundOverride> ADVANCEMENT_SOUND_OVERRIDE_REGISTRY = FabricRegistryBuilder.create(AetherIIRegistries.ADVANCEMENT_SOUND_OVERRIDE).attribute(RegistryAttribute.SYNCED).buildAndRegister();

    public static final AdvancementSoundOverride GENERAL = register("general", new AdvancementSoundOverride(0, (advancement) -> checkRoot(advancement, Identifier.fromNamespaceAndPath(AetherII.MODID, "the_holy_isles")), () -> AetherIISoundEvents.UI_TOAST_AETHER_GENERAL));
    public static final AdvancementSoundOverride SLIDER = register("slider", new AdvancementSoundOverride(1, (advancement) -> checkAdvancement(advancement, Identifier.fromNamespaceAndPath(AetherII.MODID, "slider")), () -> AetherIISoundEvents.UI_TOAST_AETHER_SLIDER));
    public static final AdvancementSoundOverride EMPTY = register("empty", new AdvancementSoundOverride(10, advancement -> checkAdvancement(advancement, Identifier.fromNamespaceAndPath(AetherII.MODID, "enter_holy_isles")), () -> SoundEvents.EMPTY));

    private static AdvancementSoundOverride register(String name, AdvancementSoundOverride override) {
        return Registry.register(ADVANCEMENT_SOUND_OVERRIDE_REGISTRY, Identifier.fromNamespaceAndPath(AetherII.MODID, name), override);
    }

    public static void init() {}

    @Nullable
    public static AdvancementSoundOverride get(Identifier location) {
        return ADVANCEMENT_SOUND_OVERRIDE_REGISTRY.getValue(location);
    }

    /**
     * Retrieves the {@link SoundEvent} to use in an override for the given {@link Advancement}.
     * @param advancement The {@link Advancement}.
     * @return The new {@link SoundEvent}.
     */
    @Nullable
    public static SoundEvent retrieveOverride(AdvancementHolder advancement) {
        @Nullable AdvancementSoundOverride usedOverride = null;
        for (AdvancementSoundOverride override : AetherIIAdvancementSoundOverrides.ADVANCEMENT_SOUND_OVERRIDE_REGISTRY) {
            if (override.matches(advancement) && (usedOverride == null || override.priority() > usedOverride.priority())) {
                usedOverride = override;
            }
        }
        return usedOverride == null ? null : usedOverride.sound().get();
    }

    /**
     * Checks all the way up to the root of the advancement tree to determine if it matches a given root.
     */
    public static boolean checkRoot(AdvancementHolder holder, Identifier root) {
        for (AdvancementHolder advancement = holder; advancement != null && advancement.value().parent().isPresent(); advancement = AetherIIClientProxy.getAdvancementParent(advancement)) {
            if (advancement.value().parent().get().equals(root)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for a specific advancement
     */
    public static boolean checkAdvancement(AdvancementHolder holder, Identifier root) {
        return holder.id().equals(root);
    }
}