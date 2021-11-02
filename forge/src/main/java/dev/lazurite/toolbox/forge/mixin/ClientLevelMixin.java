package dev.lazurite.toolbox.forge.mixin;

import dev.lazurite.toolbox.common.event.ClientLifecycleEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

/**
 * @see ClientLifecycleEvents
 */
@Mixin(ClientLevel.class)
public class ClientLevelMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(ClientPacketListener clientPacketListener, ClientLevel.ClientLevelData clientLevelData, ResourceKey resourceKey, DimensionType dimensionType, int i, Supplier supplier, LevelRenderer levelRenderer, boolean bl, long l, CallbackInfo ci) {
        ClientLifecycleEvents.LOAD_LEVEL.invoker().onLoadWorld(minecraft, (ClientLevel) (Object) this);
    }

    @Inject(method = "disconnect", at = @At("HEAD"))
    public void disconnect(CallbackInfo info) {
        ClientLifecycleEvents.DISCONNECT.invoker().onDisconnect(minecraft, (ClientLevel) (Object) this);
    }
}