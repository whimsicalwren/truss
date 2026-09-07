package dev.wren.truss.platform

import dev.wren.truss.Truss
import dev.wren.truss.platform.services.PlatformHelper
import java.util.ServiceLoader

val PLATFORM: PlatformHelper = load(PlatformHelper::class.java)

fun <T> load(clazz: Class<T>): T {
    val loadedService = ServiceLoader.load<T>(clazz, clazz.classLoader)
        .findFirst()
        .orElseThrow { NullPointerException("Failed to load service for " + clazz.getName()) }
    Truss.LOGGER.debug("Loaded {} for service {}", loadedService, clazz)
    return loadedService
}