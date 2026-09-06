package dev.wren.truss;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TrussCommon.ID)
public class TrussNeoForge {

    public TrussNeoForge(IEventBus eventBus) {
        TrussCommon.init();
    }
}