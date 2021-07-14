package dev.lazurite.toolbox.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;

/**
 * These events are slightly more useful versions of what exist in FAPI.
 */
@Environment(EnvType.CLIENT)
public final class BetterClientLifecycleEvents {
    public static final Event<LoadWorld> LOAD_WORLD = EventFactory.createArrayBacked(LoadWorld.class, (callbacks) -> (client, world) -> {
        for (LoadWorld event : callbacks) {
            event.onLoadWorld(client, world);
        }
    });

    public static final Event<GameJoin> GAME_JOIN = EventFactory.createArrayBacked(GameJoin.class, (callbacks) -> (client, world, player) -> {
        for (GameJoin event : callbacks) {
            event.onGameJoin(client, world, player);
        }
    });

    public static final Event<Disconnect> DISCONNECT = EventFactory.createArrayBacked(Disconnect.class, (callbacks) -> (client, world) -> {
        for (Disconnect event : callbacks) {
            event.onDisconnect(client, world);
        }
    });

    private BetterClientLifecycleEvents() { }

    @FunctionalInterface
    public interface LoadWorld {
        void onLoadWorld(MinecraftClient client, ClientWorld world);
    }

    @FunctionalInterface
    public interface GameJoin {
        void onGameJoin(MinecraftClient client, ClientWorld world, ClientPlayerEntity player);
    }

    @FunctionalInterface
    public interface Disconnect {
        void onDisconnect(MinecraftClient client, ClientWorld world);
    }
}

