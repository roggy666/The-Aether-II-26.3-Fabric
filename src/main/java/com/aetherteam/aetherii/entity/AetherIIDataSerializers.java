package com.aetherteam.aetherii.entity;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.entity.monster.BladeshroomHunter;
import com.aetherteam.aetherii.entity.passive.Kirrid;
import com.aetherteam.aetherii.entity.passive.Sheepuff;
import com.aetherteam.aetherii.entity.variant.GlitterwingVariant;
import com.aetherteam.aetherii.entity.variant.ShroudwingVariant;
import com.aetherteam.aetherii.entity.variant.SkyrootLizardVariant;
import com.aetherteam.aetherii.entity.vehicle.CloudSkiff;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityDataRegistry;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.Identifier;

import java.util.Optional;

public class AetherIIDataSerializers {
    public static final StreamCodec<ByteBuf, CompoundTag> COMPOUND_TAG_STREAM_CODEC = new StreamCodec<ByteBuf, CompoundTag>() {
        public CompoundTag decode(ByteBuf p_331901_) {
            return FriendlyByteBuf.readNbt(p_331901_);
        }

        public void encode(ByteBuf p_331539_, CompoundTag p_455271_) {
            FriendlyByteBuf.writeNbt(p_331539_, p_455271_);
        }
    };

    private static <T> EntityDataSerializer<T> register(String name, EntityDataSerializer<T> serializer) {
        FabricEntityDataRegistry.register(Identifier.fromNamespaceAndPath(AetherII.MODID, name), serializer);
        return serializer;
    }

    public static final EntityDataSerializer<Sheepuff.SheepuffColor> SHEEPUFF_COLOR = register("sheepuff_color", EntityDataSerializer.forValueType(Sheepuff.SheepuffColor.STREAM_CODEC));
    public static final EntityDataSerializer<Optional<Kirrid.KirridColor>> OPTIONAL_KIRRID_COLOR = register("optional_kirrid_color", EntityDataSerializer.forValueType(Kirrid.KirridColor.STREAM_CODEC.apply(ByteBufCodecs::optional)));
    public static final EntityDataSerializer<Holder<SkyrootLizardVariant>> SKYROOT_LIZARD_VARIANT = register("skyroot_lizard_variant", EntityDataSerializer.forValueType(SkyrootLizardVariant.STREAM_CODEC));
    public static final EntityDataSerializer<Holder<GlitterwingVariant>> GLITTERWING_VARIANT = register("glitterwing_variant", EntityDataSerializer.forValueType(GlitterwingVariant.STREAM_CODEC));
    public static final EntityDataSerializer<Holder<ShroudwingVariant>> SHROUDWING_VARIANT = register("shroudwing_variant", EntityDataSerializer.forValueType(ShroudwingVariant.STREAM_CODEC));
    public static final EntityDataSerializer<BladeshroomHunter.State> BLADESHROOM_HUNTER_STATE = register("bladeshroom_hunter_state", EntityDataSerializer.forValueType(BladeshroomHunter.State.STREAM_CODEC));
    public static final EntityDataSerializer<CloudSkiff.SteeringState> CLOUD_SKIFF_STEERING_STATE = register("cloud_skiff_steering_state", EntityDataSerializer.forValueType(CloudSkiff.SteeringState.STREAM_CODEC));
    public static final EntityDataSerializer<CompoundTag> COMPOUND_TAG = register("compound_tag", EntityDataSerializer.forValueType(COMPOUND_TAG_STREAM_CODEC));

    public static void init() {}
}

