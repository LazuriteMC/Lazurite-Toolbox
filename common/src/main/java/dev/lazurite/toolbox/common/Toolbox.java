package dev.lazurite.toolbox.common;

import dev.lazurite.toolbox.common.event.ClientLifecycleEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Toolbox {
    public static final String MODID = "toolbox";
    public static final Logger LOGGER = LogManager.getLogger("Lazurite Toolbox");

    public static void init() {
        LOGGER.info("Test");
        ClientLifecycleEvents.LOAD_LEVEL.register((minecraft, level) -> System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
    }
}