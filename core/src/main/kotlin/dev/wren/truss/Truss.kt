package dev.wren.truss

import dev.wren.truss.platform.LANG
import dev.wren.truss.platform.PLATFORM
import dev.wren.truss.util.logger
import org.apache.logging.log4j.Logger

object Truss {
    fun init() {
        LOGGER.info("services:")
        LOGGER.info("lang: ${LANG.javaClass}")
        LOGGER.info("platform: ${PLATFORM.javaClass}")

        LOGGER.info("core init for {} ({})", NAME, ID)
    }
}

val LOGGER: Logger = logger("truss")
const val ID: String = "truss"
const val NAME: String = "Truss"