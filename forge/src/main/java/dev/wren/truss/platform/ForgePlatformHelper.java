package dev.wren.truss.platform;

import dev.wren.truss.platform.services.PlatformHelper;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements PlatformHelper {
    @Override
    public boolean isClient() {
        return FMLLoader.getDist().isClient();
    }

    @Override
    public boolean isDedicatedServer() {
        return FMLLoader.getDist().isDedicatedServer();
    }

    @Override
    public String getDist() {
        return FMLLoader.getDist().name();
    }

    @Override
    public Platform getPlatform() {
        return Platform.FORGE;
    }
}
