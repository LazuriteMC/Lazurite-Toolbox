package dev.lazurite.toolbox.fabric;

import dev.lazurite.toolbox.impl.Toolbox;
import net.fabricmc.api.ModInitializer;

public class ToolboxFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Toolbox.init();
    }
}