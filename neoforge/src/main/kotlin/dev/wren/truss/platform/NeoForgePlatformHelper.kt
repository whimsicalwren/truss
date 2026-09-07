package dev.wren.truss.platform

import dev.wren.truss.platform.services.PlatformHelper
import net.neoforged.fml.loading.FMLLoader

class NeoForgePlatformHelper : PlatformHelper {
    override val isClient: Boolean = FMLLoader.getDist().isClient
    override val isDedicatedServer: Boolean = FMLLoader.getDist().isDedicatedServer
    override val dist: String = FMLLoader.getDist().name
    override val platform: PlatformHelper.Platform = PlatformHelper.Platform.FORGE
}