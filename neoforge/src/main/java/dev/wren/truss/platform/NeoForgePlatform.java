package dev.wren.truss.platform;

import dev.wren.truss.platform.services.Platform;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgePlatform implements Platform {

    @Override
    public String getName() {
        return "neoforge";
    }

    @Override
    public boolean isLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevEnv() {
        return !FMLLoader.isProduction();
    }
}