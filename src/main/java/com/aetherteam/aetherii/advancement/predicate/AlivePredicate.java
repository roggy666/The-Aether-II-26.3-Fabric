package com.aetherteam.aetherii.advancement.predicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public record AlivePredicate() implements EntitySubPredicate {
    public static final Codec<AlivePredicate> CODEC = MapCodec.unit(new AlivePredicate()).codec();

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        return entity.isAlive();
    }
}
