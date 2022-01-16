package dev.lazurite.toolbox.api.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Consumer;

public interface ServerNetworking {
    static void send(ServerPlayer player, ResourceLocation identifier, Consumer<FriendlyByteBuf> consumer) {
        final var buf = new FriendlyByteBuf(Unpooled.buffer());
        consumer.accept(buf);
        player.connection.send(new ClientboundCustomPayloadPacket(identifier, buf));
    }
}