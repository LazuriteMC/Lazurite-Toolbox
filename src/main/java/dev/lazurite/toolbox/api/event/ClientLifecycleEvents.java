package dev.lazurite.toolbox.api.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;

/**
 * These events are slightly more useful versions of what exist in FAPI.
 * @see Event
 * @since 1.0.0
 */
public final class ClientLifecycleEvents {
    public static final Event<LoadLevel> LOAD_LEVEL = Event.create();
    public static final Event<Login> LOGIN = Event.create();
    public static final Event<Disconnect> DISCONNECT = Event.create();

    private ClientLifecycleEvents() { }

    @FunctionalInterface
    public interface LoadLevel {
        void onLoadLevel(Minecraft minecraft, ClientLevel level);
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