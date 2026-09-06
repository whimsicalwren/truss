package dev.wren.truss.platform;

import dev.wren.truss.TrussCommon;
import dev.wren.truss.platform.services.PlatformHelper;

import java.util.ServiceLoader;

public class Services {

    public static final PlatformHelper PLATFORM = load(PlatformHelper.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        TrussCommon.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}