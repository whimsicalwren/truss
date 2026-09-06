package dev.wren.truss.platform;

import dev.wren.truss.platform.services.PlatformHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements PlatformHelper {
    @Override
    public boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    @Override
    public boolean isDedicatedServer() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER;
    }

    @Override
    public String getDist() {
        return FabricLoader.getInstance().getEnvironmentType().name();
    }

    @Override
    public Platform getPlatform() {
        return Platform.FABRIC;
    }
}
