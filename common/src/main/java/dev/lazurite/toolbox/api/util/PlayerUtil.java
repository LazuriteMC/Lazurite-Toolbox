package dev.lazurite.toolbox.api.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;

/**
 * @since 1.2.5
 */
public class PlayerUtil {
    @ExpectPlatform
    public static Collection<ServerPlayer> tracking(Entity entity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Collection<ServerPlayer> around(ServerLevel level, Vec3 pos, double radius) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Collection<ServerPlayer> level(ServerLevel level) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Collection<ServerPlayer> all(MinecraftServer server) {
        throw new AssertionError();
    }
}