package dev.wren.truss

import dev.wren.truss.platform.PLATFORM
import net.minecraftforge.fml.config.ModConfig

object TrussCommon {

    const val ID: String = "truss"
    const val NAME: String = "Truss"

    fun init() {
        Truss.LOGGER.info("common init for {} on {}", NAME, PLATFORM.platform)
    }

}