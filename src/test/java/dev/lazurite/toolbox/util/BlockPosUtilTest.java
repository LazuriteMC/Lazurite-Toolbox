package dev.lazurite.toolbox.util;

import dev.lazurite.toolbox.api.util.BlockPosUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class BlockPosUtilTest {

    @Test
    public void testPositivePosToBlockCoord() {
        Assertions.assertEquals(0, BlockPosUtil.posToBlockCoord(0.0));
        Assertions.assertEquals(0, BlockPosUtil.posToBlockCoord(0.25));
        Assertions.assertEquals(0, BlockPosUtil.posToBlockCoord(0.5));
        Assertions.assertEquals(0, BlockPosUtil.posToBlockCoord(0.75));
        Assertions.assertNotEquals(0, BlockPosUtil.posToBlockCoord(1.0));

        Assertions.assertEquals(1, BlockPosUtil.posToBlockCoord(1.0));
        Assertions.assertEquals(1, BlockPosUtil.posToBlockCoord(1.25));
        Assertions.assertEquals(1, BlockPosUtil.posToBlockCoord(1.5));
        Assertions.assertEquals(1, BlockPosUtil.posToBlockCoord(1.75));
        Assertions.assertNotEquals(1, BlockPosUtil.posToBlockCoord(2.0));
    }

    @Test
    public void testNegativePosToBlockCoord() {
        Assertions.assertNotEquals(-1, BlockPosUtil.posToBlockCoord(-0.0));
        Assertions.assertEquals(-1, BlockPosUtil.posToBlockCoord(-0.25));
        Assertions.assertEquals(-1, BlockPosUtil.posToBlockCoord(-0.5));
        Assertions.assertEquals(-1, BlockPosUtil.posToBlockCoord(-0.75));
        Assertions.assertEquals(-1, BlockPosUtil.posToBlockCoord(-1.0));

        Assertions.assertNotEquals(-2, BlockPosUtil.posToBlockCoord(-1.0));
        Assertions.assertEquals(-2, BlockPosUtil.posToBlockCoord(-1.25));
        Assertions.assertEquals(-2, BlockPosUtil.posToBlockCoord(-1.5));
        Assertions.assertEquals(-2, BlockPosUtil.posToBlockCoord(-1.75));
        Assertions.assertEquals(-2, BlockPosUtil.posToBlockCoord(-2.0));
    }

}
