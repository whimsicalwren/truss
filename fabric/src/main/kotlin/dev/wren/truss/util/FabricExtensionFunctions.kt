package dev.wren.truss.util

import dev.wren.truss.ID
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry
import net.neoforged.fml.config.IConfigSpec
import net.neoforged.fml.config.ModConfig

fun NeoForgeConfigRegistry.trussConfig(type: ModConfig.Type, spec: IConfigSpec) {
    register(ID, type, spec, "truss/" + type.name.lowercase() + ".toml")
}