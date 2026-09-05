package dev.wren.truss.platform;

import dev.wren.truss.platform.services.Platform;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatform implements Platform {

    @Override
    public String getName() {
        return "fabric";
    }

    @Override
    public boolean isLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevEnv() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
