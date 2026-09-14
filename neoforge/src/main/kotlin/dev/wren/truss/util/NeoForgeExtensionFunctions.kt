package dev.wren.truss.util

import net.neoforged.fml.ModContainer
import net.neoforged.fml.config.IConfigSpec
import net.neoforged.fml.config.ModConfig


fun ModContainer.trussConfig(type: ModConfig.Type, spec: IConfigSpec) {
    registerConfig(type, spec, "truss/" + type.name.lowercase() + ".toml")
}