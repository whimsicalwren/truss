package dev.wren.truss.platform

import dev.wren.truss.LOGGER
import dev.wren.truss.platform.services.LangHelper
import dev.wren.truss.platform.services.PlatformHelper
import java.util.ServiceLoader

val PLATFORM: PlatformHelper = load(PlatformHelper::class.java)
val LANG: LangHelper = load(LangHelper::class.java)

fun <T> load(clazz: Class<T>): T {
    val loadedService = ServiceLoader.load<T>(clazz, clazz.classLoader)
        .findFirst()
        .orElseThrow { NullPointerException("Failed to load service for " + clazz.getName()) }
    LOGGER.debug("loaded {} for service {}", loadedService, clazz)
    return loadedService
}