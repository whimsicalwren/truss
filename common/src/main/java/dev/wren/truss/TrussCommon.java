package dev.wren.truss;

import dev.wren.truss.platform.Services;

public class TrussCommon {

    public static final String ID = "truss";
    public static final String NAME = "Truss";

    public static void init() {
        Truss.LOGGER.info("common init for {} on {}", NAME, Services.PLATFORM.getPlatform());
    }
}