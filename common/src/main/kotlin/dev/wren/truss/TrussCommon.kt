package dev.wren.truss


object TrussCommon {

    fun init() {
        Truss.init()
        LOGGER.info("common init for {} ({})", NAME, ID)
    }

}