package dev.wren.truss

import dev.wren.truss.TrussCommon.ID
import dev.wren.truss.config.ConfigUpdater
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import net.neoforged.fml.event.config.ModConfigEvent
import net.neoforged.neoforge.common.NeoForge

@Mod(ID)
class TrussNeoForge(container: ModContainer) {
    init {
        val neoBus = NeoForge.EVENT_BUS
        val modBus = container.eventBus!! // amaze amaze amaze

        modBus.addListener(::onConfigLoad)
        modBus.addListener(::onConfigReload)

        container.registerConfig(ModConfig.Type.CLIENT, ConfigUpdater.CLIENT_SPEC)
        container.registerConfig(ModConfig.Type.COMMON, ConfigUpdater.COMMON_SPEC)
        container.registerConfig(ModConfig.Type.SERVER, ConfigUpdater.SERVER_SPEC)

        TrussCommon.init()
    }

    // region event listeners
    private fun onConfigLoad(event: ModConfigEvent.Loading) {
        if (event.config.modId == ID) {
            val config = event.config.loadedConfig?.config() ?: return
            ConfigUpdater.update(config)
        }
    }

    private fun onConfigReload(event: ModConfigEvent.Reloading) {
        if (event.config.modId == ID) {
            val config = event.config.loadedConfig?.config() ?: return
            ConfigUpdater.update(config)
        }
    }
    // endregion
}