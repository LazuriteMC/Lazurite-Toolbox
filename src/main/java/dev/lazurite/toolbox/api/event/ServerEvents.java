package dev.lazurite.toolbox.api.event;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class ServerEvents {
    public static class Lifecycle {
        public static final Event<LoadLevel> LOAD_LEVEL = Event.create();
        public static final Event<UnloadLevel> UNLOAD_LEVEL = Event.create();
        public static final Event<ServerLoad> LOAD_SERVER = Event.create();
        public static final Event<ServerUnload> UNLOAD_SERVER = Event.create();

        @FunctionalInterface
        public interface LoadLevel {
            void onLoadLevel(MinecraftServer server, ServerLevel level);
        }

        @FunctionalInterface
        public interface UnloadLevel {
            void onUnloadLevel(MinecraftServer server, ServerLevel level);
        }

        @FunctionalInterface
        public interface ServerLoad {
            void onServerLoad(MinecraftServer server);
        }

        @FunctionalInterface
        public interface ServerUnload {
            void onServerUnload(MinecraftServer server);
        }
    }

    public static class Tick {
        public static final Event<StartLevelTick> START_LEVEL_TICK = Event.create();
        public static final Event<EndLevelTick> END_LEVEL_TICK = Event.create();
        public static final Event<StartServerTick> START_SERVER_TICK = Event.create();
        public static final Event<EndServerTick> END_SERVER_TICK = Event.create();

        @FunctionalInterface
        public interface StartLevelTick {
            void onStartLevelTick(ServerLevel level);
        }

        @FunctionalInterface
        public interface EndLevelTick {
            void onEndLevelTick(ServerLevel level);
        }

        @FunctionalInterface
        public interface StartServerTick {
            void onStartServerTick(MinecraftServer server);
        }

        @FunctionalInterface
        public interface EndServerTick {
            void onEndServerTick(MinecraftServer server);
        }
    }

    public static class Entity {
        public static final Event<Load> LOAD = Event.create();
        public static final Event<Unload> UNLOAD = Event.create();
        public static final Event<StartTracking> START_TRACKING = Event.create();
        public static final Event<StopTracking> STOP_TRACKING = Event.create();

        @FunctionalInterface
        public interface Load {
            void onLoad(net.minecraft.world.entity.Entity entity);
        }

        @FunctionalInterface
        public interface Unload {
            void onUnload(net.minecraft.world.entity.Entity entity);
        }

        @FunctionalInterface
        public interface StartTracking {
            void onStartTracking(net.minecraft.world.entity.Entity entity, ServerPlayer player);
        }

        @FunctionalInterface
        public interface StopTracking{
            void onStopTracking(net.minecraft.world.entity.Entity entity, ServerPlayer player);
        }
    }
}
