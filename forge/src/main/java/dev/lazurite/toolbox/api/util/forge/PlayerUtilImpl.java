package dev.lazurite.toolbox.api.util.forge;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class PlayerUtilImpl {
    /**
     * @param entity the {@link Entity} being tracked.
     * @return the set of {@link ServerPlayer}s tracking said {@link Entity}
     * @author tmvkrpxl0
     */
    public static Collection<ServerPlayer> tracking(Entity entity) {
        final var chunkMap = ((ServerChunkCache) entity.getCommandSenderWorld().getChunkSource()).chunkMap;
        final var tracked = chunkMap.entityMap.get(entity.getId());

        if (tracked != null) {
            return tracked.seenBy.stream().map(ServerPlayerConnection::getPlayer).collect(Collectors.toSet());
        }

        return Collections.emptySet();
    }

    public static Collection<ServerPlayer> around(ServerLevel level, Vec3 pos, double radius) {
        return level(level).stream()
                .filter(player -> player.distanceToSqr(pos) <= radius * radius)
                .collect(Collectors.toList());
    }

    public static Collection<ServerPlayer> level(ServerLevel level) {
        return Collections.unmodifiableCollection(level.players());
    }

    public static Collection<ServerPlayer> all(MinecraftServer server) {
        return Collections.unmodifiableCollection(server.getPlayerList().getPlayers());
    }
}