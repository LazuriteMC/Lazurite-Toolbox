package dev.lazurite.toolbox.common.event;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;

/**
 * These events are slightly more useful versions of what exist in FAPI.
 */
@Environment(EnvType.CLIENT)
public final class ClientLifecycleEvents {
    public static final Event<LoadLevel> LOAD_LEVEL = EventFactory.of((callbacks) -> (minecraft, level) -> {
        for (var event : callbacks) {
            event.onLoadWorld(minecraft, level);
        }
    });

    public static final Event<Login> LOGIN = EventFactory.of((callbacks) -> (minecraft, level, player) -> {
        for (var event : callbacks) {
            event.onLogin(minecraft, level, player);
        }
    });

    public static final Event<Disconnect> DISCONNECT = EventFactory.of((callbacks) -> (minecraft, level) -> {
        for (var event : callbacks) {
            event.onDisconnect(minecraft, level);
        }
    });

    private ClientLifecycleEvents() { }

    @FunctionalInterface
    public interface LoadLevel {
        void onLoadWorld(Minecraft minecraft, ClientLevel level);
    }

    @FunctionalInterface
    public interface Login {
        void onLogin(Minecraft minecraft, ClientLevel level, LocalPlayer player);
    }

    @FunctionalInterface
    public interface Disconnect {
        void onDisconnect(Minecraft minecraft, ClientLevel level);
    }
}