package dev.wren.truss

import dev.wren.truss.TrussCommon.ID
import dev.wren.truss.config.ConfigUpdater
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeModConfigEvents
import net.fabricmc.api.ModInitializer
import net.neoforged.fml.config.ModConfig

class TrussFabric : ModInitializer {
    override fun onInitialize() {
        NeoForgeConfigRegistry.INSTANCE.register(ID, ModConfig.Type.CLIENT, ConfigUpdater.CLIENT_SPEC)
        NeoForgeConfigRegistry.INSTANCE.register(ID, ModConfig.Type.COMMON, ConfigUpdater.COMMON_SPEC)
        NeoForgeConfigRegistry.INSTANCE.register(ID, ModConfig.Type.SERVER, ConfigUpdater.SERVER_SPEC)

        registerEventListeners()

        TrussCommon.init()
    }

    private fun registerEventListeners() {
        NeoForgeModConfigEvents.loading(ID).register {
            val config = it.loadedConfig?.config() ?: return@register
            ConfigUpdater.update(config)
        }
        NeoForgeModConfigEvents.reloading(ID).register {
            val config = it.loadedConfig?.config() ?: return@register
            ConfigUpdater.update(config)
        }
    }
}