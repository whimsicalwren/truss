package dev.wren.truss.platform.services;

import java.util.Optional;
import java.util.function.Supplier;

public interface PlatformHelper {

    boolean isClient();

    boolean isDedicatedServer();

    String getDist();

    Platform getPlatform();

    enum Platform {
        FORGE,
        FABRIC,
        NEOFORGE
    }

    default void executeWhenOnClient(Supplier<Runnable> toExecute) {
        if (isClient()) toExecute.get().run();
    }

    default void executeWhenOnServer(Supplier<Runnable> toExecute) {
        if (isDedicatedServer()) toExecute.get().run();
    }

    default <T> Optional<T> runWhenOnClient(Supplier<Supplier<T>> toRun) {
        return isClient() ? Optional.of(toRun.get().get()) : Optional.empty();
    }

    default <T> Optional<T> runWhenOnServer(Supplier<Supplier<T>> toRun) {
        return isDedicatedServer() ? Optional.of(toRun.get().get()) : Optional.empty();
    }
}
