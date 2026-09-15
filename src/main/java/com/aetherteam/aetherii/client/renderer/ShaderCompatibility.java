package com.aetherteam.aetherii.client.renderer;

import net.fabricmc.loader.api.FabricLoader;
import java.lang.reflect.Method;

public final class ShaderCompatibility {
    private static final java.util.function.BooleanSupplier SHADERS_ACTIVE = findIris();

    private static java.util.function.BooleanSupplier findIris() {
        if (!FabricLoader.getInstance().isModLoaded("iris")) return () -> false;
        try {
            Class<?> api = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
            Object instance = api.getMethod("getInstance").invoke(null);
            Method active = api.getMethod("isShaderPackInUse");
            return () -> {
                try { return (boolean) active.invoke(instance); }
                catch (ReflectiveOperationException exception) { throw new IllegalStateException("Cannot query Iris shader state", exception); }
            };
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Cannot access the installed Iris API", exception);
        }
    }

    public static boolean areShadersActive() {
        return SHADERS_ACTIVE.getAsBoolean();
    }
}
