package dev.lazurite.toolbox.api.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;

/**
 * These events are slightly more useful versions of what exist in FAPI.
 */
@Environment(EnvType.CLIENT)
public final class BetterClientLifecycleEvents {
    public static final Event<LoadLevel> LOAD_LEVEL = EventFactory.createArrayBacked(LoadLevel.class, (callbacks) -> (minecraft, level) -> {
        for (var event : callbacks) {
            event.onLoadWorld(minecraft, level);
        }
    });

    public static final Event<Login> LOGIN = EventFactory.createArrayBacked(Login.class, (callbacks) -> (minecraft, level, player) -> {
        for (var event : callbacks) {
            event.onLogin(minecraft, level, player);
        }
    });

    public static final Event<Disconnect> DISCONNECT = EventFactory.createArrayBacked(Disconnect.class, (callbacks) -> (minecraft, level) -> {
        for (var event : callbacks) {
            event.onDisconnect(minecraft, level);
        }
    });

    private BetterClientLifecycleEvents() { }

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

