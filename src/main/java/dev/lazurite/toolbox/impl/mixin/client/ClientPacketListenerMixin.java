package dev.lazurite.toolbox.impl.mixin.client;

import dev.lazurite.toolbox.api.event.ClientLifecycleEvents;
import dev.lazurite.toolbox.api.network.PacketRegistry;
import dev.lazurite.toolbox.impl.network.PacketRegistryImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.game.ClientboundLoginPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Shadow @Final private Minecraft minecraft;
    @Shadow private ClientLevel level;

    /**
     * @see ClientLifecycleEvents
     */
    @Inject(
            method = "handleLogin",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/protocol/PacketUtils;ensureRunningOnSameThread(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketListener;Lnet/minecraft/util/thread/BlockableEventLoop;)V",
                    shift = At.Shift.AFTER
            )
    )
    public void handleLogin(ClientboundLoginPacket packet, CallbackInfo info) {
        ClientLifecycleEvents.LOGIN.invoke(minecraft, level, minecraft.player);
    }

    @Inject(
            method = "handleCustomPayload",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/protocol/PacketUtils;ensureRunningOnSameThread(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketListener;Lnet/minecraft/util/thread/BlockableEventLoop;)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    public void handleCustomPayload(ClientboundCustomPayloadPacket clientboundCustomPayloadPacket, CallbackInfo ci) {
        PacketRegistryImpl.getClientbound(clientboundCustomPayloadPacket.getIdentifier()).ifPresent(consumer -> {
            consumer.accept(new PacketRegistry.ClientboundContext(Minecraft.getInstance(), clientboundCustomPayloadPacket.getData()));
            ci.cancel();
        });
    }
}