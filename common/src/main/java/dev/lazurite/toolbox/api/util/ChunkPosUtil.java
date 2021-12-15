package dev.lazurite.toolbox.api.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec3;

/**
 * Utility class for dealing with {@link ChunkPos}.
 * @since 1.2.7
 */
public final class ChunkPosUtil {

    public static int posToChunkCoord(double coord) {
        return ChunkPosUtil.blockToChunkCoord(BlockPosUtil.posToBlockCoord(coord));
    }

    public static int blockToChunkCoord(int coord) {
        return coord >> 4;
    }

    public static ChunkPos of(Vec3 pos) {
        return new ChunkPos(
                ChunkPosUtil.posToChunkCoord(pos.x()),
                ChunkPosUtil.posToChunkCoord(pos.z())
        );
    }

    public static ChunkPos of(Entity entity) {
        return ChunkPosUtil.of(entity.position());
    }

    private ChunkPosUtil() { }

}
