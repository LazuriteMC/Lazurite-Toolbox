package dev.lazurite.toolbox;


import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ToolBox.MODID)
public class ToolBox {
    public static final String MODID = "toolbox";
    public static final Logger LOGGER = LogManager.getLogger("Transporter");

    public ToolBox(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Lazurite toolbox loaded");
    }
}
