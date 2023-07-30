package dev.lazurite.toolbox.api.util;

import dev.lazurite.toolbox.impl.mixin.common.access.IChunkMapMixin;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerUtil {
    /**
     * @param entity the {@link Entity} being tracked.
     * @return the set of {@link ServerPlayer}s tracking said {@link Entity}
     * @author tmvkrpxl0
     */
    public static Set<ServerPlayer> tracking(Entity entity) {
        final var chunkMap = ((ServerChunkCache) entity.level().getChunkSource()).chunkMap;
        final var tracked = ((IChunkMapMixin) chunkMap).getEntityMap().get(entity.getId());

        if (tracked != null) {
            return tracked.getSeenBy().stream().map(ServerPlayerConnection::getPlayer).collect(Collectors.toSet());
        }

        return Collections.emptySet();
    }

    public static Set<ServerPlayer> around(ServerLevel level, Vec3 pos, double radius) {
        return level(level).stream()
                .filter(player -> player.distanceToSqr(pos) <= radius * radius)
                .collect(Collectors.toSet());
    }

    public static Set<ServerPlayer> level(ServerLevel level) {
        return Set.copyOf(level.players());
    }

    public static Set<ServerPlayer> all(MinecraftServer server) {
        return Set.copyOf(server.getPlayerList().getPlayers());
    }
}