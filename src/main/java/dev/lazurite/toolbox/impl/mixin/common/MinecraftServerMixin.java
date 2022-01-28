package dev.lazurite.toolbox.impl.mixin.common;

import dev.lazurite.toolbox.api.event.ServerEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Shadow @Final private Map<ResourceKey<Level>, ServerLevel> levels;

    /**
     * @see ServerEvents.Lifecycle#LOAD_SERVER
     */
    @Inject(
            method = "runServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"
            )
    )
    public void runServer(CallbackInfo info) {
        ServerEvents.Lifecycle.LOAD_SERVER.invoke(this);
    }

    /**
     * @see ServerEvents.Lifecycle#UNLOAD_SERVER
     */
    @Inject(method = "stopServer", at = @At("HEAD"))
    public void stopServer(CallbackInfo info) {
        ServerEvents.Lifecycle.UNLOAD_SERVER.invoke(this);
    }

    /**
     * @see ServerEvents.Tick#START_SERVER_TICK
     */
    @Inject(
            method = "tickServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;tickChildren(Ljava/util/function/BooleanSupplier;)V"
            )
    )
    public void tickServer_Start(BooleanSupplier shouldKeepTicking, CallbackInfo info) {
        ServerEvents.Tick.START_SERVER_TICK.invoke(this);
    }

    /**
     * @see ServerEvents.Tick#END_SERVER_TICK
     */
    @Inject(method = "tickServer", at = @At("TAIL"))
    public void tickServer_End(BooleanSupplier shouldKeepTicking, CallbackInfo info) {
        ServerEvents.Tick.END_SERVER_TICK.invoke(this);
    }

    /**
     * @see ServerEvents.Lifecycle#LOAD_LEVEL
     */
    @Inject(method = "createLevels", at = @At("TAIL"))
    public void createLevels(CallbackInfo info) {
        // yeah i know this is lazy but it suits my needs. sorry i5
        for (var level : this.levels.values()) {
            ServerEvents.Lifecycle.LOAD_LEVEL.invoke(level);
        }
    }

    /**
     * @see ServerEvents.Lifecycle#UNLOAD_LEVEL
     */
    @Inject(
            method = "stopServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;close()V"
            ),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    public void stopServer(CallbackInfo info, Iterator<ServerLevel> levels, ServerLevel level) {
        ServerEvents.Lifecycle.UNLOAD_LEVEL.invoke(this, level);
    }
}
