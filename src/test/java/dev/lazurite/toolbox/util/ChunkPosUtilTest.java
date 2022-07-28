package dev.lazurite.toolbox.util;

import dev.lazurite.toolbox.api.util.ChunkPosUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class ChunkPosUtilTest {

    @Test
    public void testPositivePosToChunkCoord() {
        Assertions.assertEquals(0, ChunkPosUtil.posToChunkCoord(0.0));
        Assertions.assertEquals(0, ChunkPosUtil.posToChunkCoord(4.0));
        Assertions.assertEquals(0, ChunkPosUtil.posToChunkCoord(8.0));
        Assertions.assertEquals(0, ChunkPosUtil.posToChunkCoord(12.0));
        Assertions.assertNotEquals(0, ChunkPosUtil.posToChunkCoord(16.0));

        Assertions.assertEquals(1, ChunkPosUtil.posToChunkCoord(16.0));
        Assertions.assertEquals(1, ChunkPosUtil.posToChunkCoord(20.0));
        Assertions.assertEquals(1, ChunkPosUtil.posToChunkCoord(24.0));
        Assertions.assertEquals(1, ChunkPosUtil.posToChunkCoord(28.0));
        Assertions.assertNotEquals(1, ChunkPosUtil.posToChunkCoord(32.0));
    }

    @Test
    public void testNegativePosToChunkCoord() {
        Assertions.assertNotEquals(-1, ChunkPosUtil.posToChunkCoord(-0.0));
        Assertions.assertEquals(-1, ChunkPosUtil.posToChunkCoord(-4.0));
        Assertions.assertEquals(-1, ChunkPosUtil.posToChunkCoord(-8.0));
        Assertions.assertEquals(-1, ChunkPosUtil.posToChunkCoord(-12.0));
        Assertions.assertEquals(-1, ChunkPosUtil.posToChunkCoord(-16.0));

        Assertions.assertNotEquals(-2, ChunkPosUtil.posToChunkCoord(-16.0));
        Assertions.assertEquals(-2, ChunkPosUtil.posToChunkCoord(-20.0));
        Assertions.assertEquals(-2, ChunkPosUtil.posToChunkCoord(-24.0));
        Assertions.assertEquals(-2, ChunkPosUtil.posToChunkCoord(-28.0));
        Assertions.assertEquals(-2, ChunkPosUtil.posToChunkCoord(-32.0));
    }

}
