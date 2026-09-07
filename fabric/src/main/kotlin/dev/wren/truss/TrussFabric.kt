package dev.wren.truss

import dev.wren.truss.TrussCommon.ID
import dev.wren.truss.config.ConfigUpdater
import fuzs.forgeconfigapiport.api.config.v3.ModConfigEvents
import net.fabricmc.api.ModInitializer

class TrussFabric : ModInitializer {
    override fun onInitialize() {
        registerEventListeners()

        TrussCommon.init()
    }

    private fun registerEventListeners() {
        ModConfigEvents.loading(ID).register { ConfigUpdater.update(it.configData) }
        ModConfigEvents.reloading(ID).register { ConfigUpdater.update(it.configData) }
    }
}