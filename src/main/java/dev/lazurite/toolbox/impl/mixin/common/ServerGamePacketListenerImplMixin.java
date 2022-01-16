package dev.lazurite.toolbox.impl.mixin.common;

import dev.lazurite.toolbox.api.network.PacketRegistry;
import dev.lazurite.toolbox.impl.network.PacketRegistryImpl;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin {

    @Shadow public ServerPlayer player;

    @Inject(
            method = "handleCustomPayload",
            at = @At("HEAD"),
            cancellable = true
    )
    public void handleCustomPayload(ServerboundCustomPayloadPacket serverboundCustomPayloadPacket, CallbackInfo ci) {
        PacketUtils.ensureRunningOnSameThread(serverboundCustomPayloadPacket, (ServerGamePacketListener) this, this.player.getLevel());

        PacketRegistryImpl.getServerbound(serverboundCustomPayloadPacket.getIdentifier()).ifPresent(consumer -> {
            consumer.accept(new PacketRegistry.ServerboundContext(serverboundCustomPayloadPacket.getData(), this.player));
            serverboundCustomPayloadPacket.getData().release();
            ci.cancel();
        });
    }

}
