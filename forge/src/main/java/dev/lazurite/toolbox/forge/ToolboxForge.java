package dev.lazurite.toolbox.forge;

import dev.lazurite.toolbox.Toolbox;
import net.minecraftforge.fml.common.Mod;

@Mod("toolbox")
public class ToolboxForge {
    public ToolboxForge() {
        Toolbox.init();
    }
}
