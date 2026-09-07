package dev.wren.truss

import dev.wren.truss.TrussCommon.ID
import dev.wren.truss.config.ConfigUpdater
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.config.ModConfigEvent
import net.neoforged.neoforge.common.NeoForge

@Mod(ID)
class TrussNeoForge(modBus: IEventBus) {
    init {
        val neoBus = NeoForge.EVENT_BUS

        modBus.addListener(::onConfigLoad)
        modBus.addListener(::onConfigReload)

        TrussCommon.init()
    }

    // region event listeners
    private fun onConfigLoad(event: ModConfigEvent.Loading) {
        if (event.config.modId == ID) {
            ConfigUpdater.update(event.config.configData)
        }
    }

    private fun onConfigReload(event: ModConfigEvent.Reloading) {
        if (event.config.modId == ID) {
            ConfigUpdater.update(event.config.configData)
        }
    }
    // endregion
}