package com.aetherteam.aetherii.entity.ai.brain.sensor;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.entity.ai.brain.BurrukaiAi;
import com.aetherteam.aetherii.entity.ai.brain.KirridAi;
import com.aetherteam.aetherii.entity.ai.brain.TaegoreAi;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.sensing.TemptingSensor;

import java.util.function.Supplier;

public class AetherIISensorTypes {
    private static <T extends Sensor<?>> SensorType<T> register(String name, Supplier<T> factory) {
        return Registry.register(BuiltInRegistries.SENSOR_TYPE, Identifier.fromNamespaceAndPath(AetherII.MODID, name), new SensorType<>(factory));
    }

    public static final SensorType<TemptingSensor> TAEGORE_TEMPTATIONS = register("taegore_temptations", () -> new TemptingSensor(TaegoreAi.getTemptations()));
    public static final SensorType<TemptingSensor> BURRUKAI_TEMPTATIONS = register("burrukai_temptations", () -> new TemptingSensor(BurrukaiAi.getTemptations()));
    public static final SensorType<TemptingSensor> KIRRID_TEMPTATIONS = register("kirrid_temptations", () -> new TemptingSensor(KirridAi.getTemptations()));

    public static void init() {}
}

