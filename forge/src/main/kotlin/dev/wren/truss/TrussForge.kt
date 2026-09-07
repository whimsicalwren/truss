package dev.wren.truss

import dev.wren.truss.TrussCommon.ID
import dev.wren.truss.config.ConfigUpdater
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.config.ModConfigEvent

@Mod(ID)
class TrussForge(modBus: IEventBus) {

    init {
        val forgeBus = MinecraftForge.EVENT_BUS

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