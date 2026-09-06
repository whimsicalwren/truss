package dev.wren.truss;

import net.fabricmc.api.ModInitializer;

public class TrussFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        TrussCommon.init();
    }
}
