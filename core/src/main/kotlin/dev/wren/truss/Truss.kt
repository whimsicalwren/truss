package dev.wren.truss

import dev.wren.truss.platform.LANG
import dev.wren.truss.platform.PLATFORM
import dev.wren.truss.util.logger
import org.apache.logging.log4j.Logger

object Truss {
    fun init() {
        LOGGER.info(
            "\n\tServices:" +
                    "\n\t\tLang: ${LANG.javaClass}" +
                    "\n\t\tPlatform: ${PLATFORM.javaClass}"
        )

        LOGGER.info("core init")
    }
}

val LOGGER: Logger = logger("truss")
const val ID: String = "truss"
const val NAME: String = "Truss"