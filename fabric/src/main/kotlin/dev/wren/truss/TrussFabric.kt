package dev.wren.truss

import dev.wren.truss.config.ConfigUpdater
import dev.wren.truss.util.trussConfig
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeModConfigEvents
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.neoforged.fml.config.ModConfig

class TrussFabric : ModInitializer {
    override fun onInitialize() {
        NeoForgeConfigRegistry.INSTANCE.trussConfig(ModConfig.Type.CLIENT, ConfigUpdater.CLIENT_SPEC)
        NeoForgeConfigRegistry.INSTANCE.trussConfig(ModConfig.Type.COMMON, ConfigUpdater.COMMON_SPEC)
        NeoForgeConfigRegistry.INSTANCE.trussConfig(ModConfig.Type.SERVER, ConfigUpdater.SERVER_SPEC)

        registerEventListeners()

        TrussCommon.init()
        LOGGER.info("fabric init")
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
        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            TrussCommon.registerCommands(dispatcher)
        }
    }
}