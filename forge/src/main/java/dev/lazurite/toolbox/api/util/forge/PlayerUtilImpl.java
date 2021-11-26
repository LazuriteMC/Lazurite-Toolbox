package dev.lazurite.toolbox.api.util.forge;

import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.Entity;

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
        ChunkMap chunkMap = ((ServerChunkCache) entity.getCommandSenderWorld().getChunkSource()).chunkMap;
        ChunkMap.TrackedEntity tracked = chunkMap.entityMap.get(entity.getId());
        if(tracked != null){
            return tracked.seenBy.stream().map(ServerPlayerConnection::getPlayer).collect(Collectors.toSet());
        }
        else return Collections.emptySet();
    }
}