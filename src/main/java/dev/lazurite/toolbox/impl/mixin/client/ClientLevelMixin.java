package dev.lazurite.toolbox.impl.mixin.client;

import dev.lazurite.toolbox.api.event.ClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

/**
 * @see ClientEvents
 */
@Mixin(ClientLevel.class)
public class ClientLevelMixin {
    @Shadow @Final private Minecraft minecraft;

    /**
     * @see ClientEvents.Lifecycle#LOAD_LEVEL
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(ClientPacketListener clientPacketListener, ClientLevel.ClientLevelData clientLevelData, ResourceKey resourceKey, Holder holder, int i, int j, Supplier supplier, LevelRenderer levelRenderer, boolean bl, long l, CallbackInfo ci) {
        ClientEvents.Lifecycle.LOAD_LEVEL.invoke(minecraft, this);
    }

    /**
     * @see ClientEvents.Tick#START_LEVEL_TICK
     */
    @Inject(method = "tickEntities", at = @At("HEAD"))
    public void tickEntities(CallbackInfo info) {
        ClientEvents.Tick.START_LEVEL_TICK.invoke(this);
    }

    /**
     * @see ClientEvents.Player#ADD
     */
    @Inject(method = "addPlayer", at = @At("TAIL"))
    public void addPlayer(int i, AbstractClientPlayer abstractClientPlayer, CallbackInfo ci) {
        ClientEvents.Player.ADD.invoke(abstractClientPlayer, abstractClientPlayer instanceof LocalPlayer);
    }

    /**
     * @see ClientEvents.Lifecycle#DISCONNECT
     */
    @Inject(method = "disconnect", at = @At("HEAD"))
    public void disconnect(CallbackInfo info) {
        ClientEvents.Lifecycle.DISCONNECT.invoke(minecraft, this);
    }
}