package dev.lazurite.toolbox.api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

/**
 * Utility class for dealing with {@link BlockPos}.
 * @since 1.2.7
 */
public class BlockPosUtil {

    public static int posToBlockCoord(double dCoord) {
        final var iCoord = (int) dCoord;
        return dCoord < (double) iCoord ? iCoord - 1 : iCoord;
    }

    public static BlockPos of(int x, int y, int z) {
        return new BlockPos(x, y, z);
    }

    public static BlockPos of(Vec3 pos) {
        return BlockPosUtil.of(
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
