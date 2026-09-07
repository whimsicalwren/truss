package dev.wren.truss.platform

import dev.wren.truss.platform.services.PlatformHelper
import net.fabricmc.api.EnvType
import net.fabricmc.loader.api.FabricLoader

class FabricPlatformHelper : PlatformHelper {
    override val isClient: Boolean = FabricLoader.getInstance().environmentType == EnvType.CLIENT
    override val isDedicatedServer: Boolean = FabricLoader.getInstance().environmentType == EnvType.SERVER
    override val dist: String = FabricLoader.getInstance().environmentType.name
    override val platform: PlatformHelper.Platform = PlatformHelper.Platform.FABRIC
}