package dev.wren.truss.platform;

import dev.wren.truss.platform.services.PlatformHelper;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgePlatformHelper implements PlatformHelper {
    @Override
    public boolean isClient() {
        return FMLLoader.getDist().isClient();
    }

    @Override
    public boolean isDedicatedServer() {
        return FMLLoader.getDist().isClient();
    }

    @Override
    public String getDist() {
        return FMLLoader.getDist().name();
    }

    @Override
    public Platform getPlatform() {
        return Platform.NEOFORGE;
    }
}
