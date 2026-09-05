package dev.wren.truss.platform;

import dev.wren.truss.platform.services.Platform;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatform implements Platform {

    @Override
    public String getName() {
        return "forge";
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