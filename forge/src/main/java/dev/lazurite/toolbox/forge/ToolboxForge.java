package dev.lazurite.toolbox.forge;

import dev.lazurite.toolbox.impl.Toolbox;
import net.minecraftforge.fml.common.Mod;

@Mod(Toolbox.MODID)
public class ToolboxForge {
    public ToolboxForge() {
        Toolbox.init();
    }
}