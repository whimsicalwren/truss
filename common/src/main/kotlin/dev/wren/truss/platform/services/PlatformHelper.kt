package dev.wren.truss.platform.services

import java.util.Optional
import java.util.function.Supplier


interface PlatformHelper {

    val isClient: Boolean
    val isDedicatedServer: Boolean
    val dist: String
    val platform: Platform

    enum class Platform {
        FORGE,
        FABRIC,
        NEOFORGE
    }

    fun executeWhenOnClient(toExecute: Supplier<Runnable>) {
        if (isClient) toExecute.get().run()
    }

    fun executeWhenOnServer(toExecute: Supplier<Runnable>) {
        if (isDedicatedServer) toExecute.get().run()
    }

    fun <T : Any> runWhenOnClient(toRun: Supplier<Supplier<T>>) =
        if (isClient) Optional.of<T>(toRun.get().get()) else Optional.empty<T>()

    fun <T : Any> runWhenOnServer(toRun: Supplier<Supplier<T>>) =
        if (isDedicatedServer) Optional.of<T>(toRun.get().get()) else Optional.empty<T>()
}