package dev.lazurite.toolbox.api.network;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public interface ClientNetworking {
    static void send(ResourceLocation identifier, Consumer<FriendlyByteBuf> consumer) {
        final var buf = new FriendlyByteBuf(Unpooled.buffer());
        consumer.accept(buf);
        Minecraft.getInstance().getConnection().send(new ServerboundCustomPayloadPacket(identifier, buf));
    }
}
