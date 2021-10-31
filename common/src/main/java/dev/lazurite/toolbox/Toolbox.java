package dev.lazurite.toolbox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Toolbox {
    public static final String MODID = "toolbox";
    public static final Logger LOGGER = LogManager.getLogger("Lazurite Toolbox");

    public static void init() {
        LOGGER.info("Test");
    }
}