package dev.wren.truss;

import dev.wren.truss.platform.Services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrussCommon {

    public static final String ID = "truss";
    public static final String NAME = "Truss";
    public static final Logger LOGGER = LogManager.getLogger("truss");

    public static void init() {
        LOGGER.info("common init for {} on {}", NAME, Services.PLATFORM.getName());
    }
}