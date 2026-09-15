package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.mixin.mixins.common.invoker.MobCategoryInvoker;
import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adds the Aether II mob categories to the vanilla enum. {@code CODEC} snapshots {@code values()} in vanilla's
 * static initializer, so it is rebuilt after the new constants are appended.
 */
@Mixin(MobCategory.class)
public abstract class MobCategoryMixin {
    @Shadow
    @Final
    @Mutable
    private static MobCategory[] $VALUES;

    @Shadow
    @Final
    @Mutable
    public static Codec<MobCategory> CODEC;

    static {
        List<MobCategory> categories = new ArrayList<>(Arrays.asList($VALUES));
        categories.add(MobCategoryInvoker.aether_ii$create("AETHER_II_AETHER_MOA", categories.size(), "aether_ii:aether_moa", "AMO", 10, true, true, 128));
        categories.add(MobCategoryInvoker.aether_ii$create("AETHER_II_AETHER_PLANT_HAZARD", categories.size(), "aether_ii:aether_plant_hazard", "APH", 11, false, false, 128));
        categories.add(MobCategoryInvoker.aether_ii$create("AETHER_II_AETHER_WATER_SURFACE_CREATURE", categories.size(), "aether_ii:aether_water_surface_creature", "AWSC", 20, true, false, 128));
        categories.add(MobCategoryInvoker.aether_ii$create("AETHER_II_AETHER_DARKNESS_HAZARD", categories.size(), "aether_ii:aether_darkness_hazard", "ADH", 25, false, false, 128));
        categories.add(MobCategoryInvoker.aether_ii$create("AETHER_II_AETHER_DARKNESS_MONSTER", categories.size(), "aether_ii:aether_darkness_monster", "ADM", 28, false, false, 128));
        categories.add(MobCategoryInvoker.aether_ii$create("AETHER_II_AETHER_BLIGHT_MONSTER", categories.size(), "aether_ii:aether_blight_monster", "ABM", 21, false, false, 128));
        categories.add(MobCategoryInvoker.aether_ii$create("AETHER_II_AETHER_SKY_HAZARD", categories.size(), "aether_ii:aether_sky_hazard", "ASH", 3, false, false, 128));
        categories.add(MobCategoryInvoker.aether_ii$create("AETHER_II_AETHER_DUNGEON_MONSTER", categories.size(), "aether_ii:aether_dungeon_monster", "ADUM", 50, false, false, 128));
        categories.add(MobCategoryInvoker.aether_ii$create("AETHER_II_AETHER_AMBIENT", categories.size(), "aether_ii:aether_ambient", "AAM", 10, true, false, 96));
        categories.add(MobCategoryInvoker.aether_ii$create("AETHER_II_AETHER_AERWHALE", categories.size(), "aether_ii:aether_aerwhale", "AAW", 1, true, false, 128));
        $VALUES = categories.toArray(new MobCategory[0]);

        MobCategory[] values = $VALUES;
        CODEC = StringRepresentable.fromEnum(() -> values);
    }
}
