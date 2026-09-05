package dev.wren.truss;

import net.fabricmc.api.ModInitializer;
import static dev.wren.truss.TrussCommon.LOGGER;

public class TrussFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        LOGGER.info("fabric init");
        TrussCommon.init();
    }
}
