package dev.lazurite.toolbox.impl.mixin.common;

import dev.lazurite.toolbox.api.event.ServerEvents;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public class PlayerListMixin {
    @Inject(
            method = "placeNewPlayer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/protocol/game/ClientboundCustomPayloadPacket;<init>(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/network/FriendlyByteBuf;)V"
            )
    )
    public void placeNewPlayer(Connection connection, ServerPlayer player, CallbackInfo info) {
        ServerEvents.Lifecycle.JOIN.invoke(player);
    }
}
