package dev.wren.truss

import dev.wren.truss.platform.PLATFORM


object TrussCommon {

    const val ID: String = "truss"
    const val NAME: String = "Truss"

    fun init() {
        Truss.LOGGER.info("common init for {} on {}", NAME, PLATFORM.platform)
    }

}