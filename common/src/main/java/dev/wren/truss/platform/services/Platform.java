package dev.wren.truss.platform.services;

public interface Platform {

    String getName();

    boolean isLoaded(String modId);

    boolean isDevEnv();

    default String getEnvName() {
        return isDevEnv() ? "development" : "production";
    }
}