package dev.lazurite.toolbox.api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

/**
 * Utility class for dealing with {@link BlockPos}.
 * @since 1.2.7
 */
public final class BlockPosUtil {

    public static int posToBlockCoord(double dCoord) {
        final var iCoord = (int) dCoord;
        return dCoord < (double) iCoord ? iCoord - 1 : iCoord;
    }

    public static BlockPos of(Vec3 pos) {
        return new BlockPos(
                BlockPosUtil.posToBlockCoord(pos.x()),
                BlockPosUtil.posToBlockCoord(pos.y()),
                BlockPosUtil.posToBlockCoord(pos.z())
        );
    }

    public static BlockPos of(Entity entity) {
        return BlockPosUtil.of(entity.position());
    }

    private BlockPosUtil() { }

}
