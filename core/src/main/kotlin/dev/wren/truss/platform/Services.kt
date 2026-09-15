package dev.wren.truss.platform

import dev.wren.truss.LOGGER
import dev.wren.truss.platform.services.LangHelper
import dev.wren.truss.platform.services.Core2CommonCommunication
import dev.wren.truss.platform.services.PlatformHelper
import java.util.ServiceLoader

val PLATFORM: PlatformHelper = load(PlatformHelper::class.java)
val LANG: LangHelper = load(LangHelper::class.java)
val MESSAGE: Core2CommonCommunication = load(Core2CommonCommunication::class.java)

fun <T> load(tClass: Class<T>): T {
    val loadedService = ServiceLoader.load<T>(tClass, tClass.classLoader)
        .findFirst()
        .orElseThrow { NullPointerException("Failed to load service for " + tClass.getName()) }
    LOGGER.debug("loaded {} for service {}", loadedService, tClass)
    return loadedService
}