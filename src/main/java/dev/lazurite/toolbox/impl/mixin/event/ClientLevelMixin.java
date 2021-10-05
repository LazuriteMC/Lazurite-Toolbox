package dev.lazurite.toolbox.impl.mixin.event;

import dev.lazurite.toolbox.api.event.BetterClientLifecycleEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

/**
 * @see BetterClientLifecycleEvents
 */
@Mixin(ClientLevel.class)
public class ClientLevelMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(ClientPacketListener clientPacketListener, ClientLevel.ClientLevelData clientLevelData, ResourceKey<Level> levelResourceKey, DimensionType dimensionType, int loadDistance, Supplier<ProfilerFiller> profiler, LevelRenderer levelRenderer, boolean debugWorld, long seed, CallbackInfo info) {
        BetterClientLifecycleEvents.LOAD_LEVEL.invoker().onLoadWorld(minecraft, (ClientLevel) (Object) this);
    }

    @Inject(method = "disconnect", at = @At("HEAD"))
    public void disconnect(CallbackInfo info) {
        BetterClientLifecycleEvents.DISCONNECT.invoker().onDisconnect(minecraft, (ClientLevel) (Object) this);
    }
}
