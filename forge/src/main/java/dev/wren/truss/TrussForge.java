package dev.wren.truss;

import net.minecraftforge.fml.common.Mod;

@Mod(TrussCommon.ID)
public class TrussForge {

    public TrussForge() {
        TrussCommon.init();
    }
}