package dev.lazurite.toolbox.forge;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("toolbox")
public class ToolboxForge {

    public static final Logger LOGGER = LogManager.getLogger("Lazurite Toolbox");

    public ToolboxForge() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void onInitializeClient(FMLClientSetupEvent event) {
        LOGGER.info("Lazurite Toolbox Initialized.");
    }

}
