package dev.lazurite.toolbox.fabric;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToolboxFabric implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("Lazurite Toolbox");

    @Override
    public void onInitialize() {
        LOGGER.info("Lazurite Toolbox Initialized.");
    }

}
