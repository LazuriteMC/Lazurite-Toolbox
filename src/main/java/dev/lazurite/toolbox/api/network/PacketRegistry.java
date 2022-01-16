package dev.lazurite.toolbox.api.network;

import dev.lazurite.toolbox.impl.network.PacketRegistryImpl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface PacketRegistry {
    static void registerServerbound(ResourceLocation identifier, Consumer<ServerboundContext> packetHandler) {
        PacketRegistryImpl.registerServerbound(identifier, packetHandler);
    }

    static void registerClientbound(ResourceLocation identifier, Consumer<ClientboundContext> packetHandler) {
        PacketRegistryImpl.registerClientbound(identifier, packetHandler);
    }

    record ServerboundContext(Executor threadExecutor, FriendlyByteBuf byteBuf, ServerPlayer player) { }
    record ClientboundContext(Executor threadExecutor, FriendlyByteBuf byteBuf) { }
}