package dev.lazurite.toolbox.api.event;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;

/**
 * These events are slightly more useful versions of what exist in FAPI.
 * @see Event
 * @since 1.0.0
 */
public final class ClientEvents {
    public static class Lifecycle {
        public static final Event<LoadLevel> LOAD_LEVEL = Event.create();
        public static final Event<PreLogin> PRE_LOGIN = Event.create();
        public static final Event<PostLogin> POST_LOGIN = Event.create();
        public static final Event<Disconnect> DISCONNECT = Event.create();

        @FunctionalInterface
        public interface LoadLevel {
            void onLoadLevel(Minecraft minecraft, ClientLevel level);
        }

        @FunctionalInterface
        public interface PreLogin {
            void onPreLogin(Minecraft minecraft);
        }

        @FunctionalInterface
        public interface PostLogin {
            void onPostLogin(Minecraft minecraft, ClientLevel level, LocalPlayer player);
        }

        @FunctionalInterface
        public interface Disconnect {
            void onDisconnect(Minecraft minecraft, ClientLevel level);
        }
    }

    public static class Tick {
        public static final Event<StartLevelTick> START_LEVEL_TICK = Event.create();
        public static final Event<EndLevelTick> END_LEVEL_TICK = Event.create();
        public static final Event<StartClientTick> START_CLIENT_TICK = Event.create();
        public static final Event<EndClientTick> END_CLIENT_TICK = Event.create();

        @FunctionalInterface
        public interface StartLevelTick {
            void onStartTick(ClientLevel level);
        }

        @FunctionalInterface
        public interface EndLevelTick {
            void onEndTick(ClientLevel level);
        }

        @FunctionalInterface
        public interface StartClientTick {
            void onStartTick(Minecraft minecraft);
        }

        @FunctionalInterface
        public interface EndClientTick {
            void onEndTick(Minecraft minecraft);
        }
    }

    public static class Entity {
        public static final Event<EntityLoad> LOAD = Event.create();
        public static final Event<EntityUnload> UNLOAD = Event.create();

        @FunctionalInterface
        public interface EntityLoad {
            void onStartTick(net.minecraft.world.entity.Entity entity);
        }

        @FunctionalInterface
        public interface EntityUnload {
            void onEndTick(net.minecraft.world.entity.Entity entity);
        }
    }

    public static class Player {
        public static final Event<PlayerAdd> ADD = Event.create();

        @FunctionalInterface
        public interface PlayerAdd {
            void onAdd(AbstractClientPlayer abstractClientPlayer, boolean isLocalPlayer);
        }
    }

    public static class Render {
        public static final Event<BeforeDebug> BEFORE_DEBUG = Event.create();

        @FunctionalInterface
        public interface BeforeDebug {
            void onBeforeDebug(PoseStack poseStack, Camera camera, ClientLevel level, float tickDelta);
        }
    }
}