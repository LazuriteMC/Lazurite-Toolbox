package dev.lazurite.toolbox.util;

import dev.lazurite.toolbox.api.util.SectionPosUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class SectionPosUtilTest {

    @Test
    public void testPositivePosToChunkCoord() {
        Assertions.assertEquals(0, SectionPosUtil.posToSectionCoord(0.0));
        Assertions.assertEquals(0, SectionPosUtil.posToSectionCoord(4.0));
        Assertions.assertEquals(0, SectionPosUtil.posToSectionCoord(8.0));
        Assertions.assertEquals(0, SectionPosUtil.posToSectionCoord(12.0));
        Assertions.assertNotEquals(0, SectionPosUtil.posToSectionCoord(16.0));

        Assertions.assertEquals(1, SectionPosUtil.posToSectionCoord(16.0));
        Assertions.assertEquals(1, SectionPosUtil.posToSectionCoord(20.0));
        Assertions.assertEquals(1, SectionPosUtil.posToSectionCoord(24.0));
        Assertions.assertEquals(1, SectionPosUtil.posToSectionCoord(28.0));
        Assertions.assertNotEquals(1, SectionPosUtil.posToSectionCoord(32.0));
    }

    @Test
    public void testNegativePosToChunkCoord() {
        Assertions.assertNotEquals(-1, SectionPosUtil.posToSectionCoord(-0.0));
        Assertions.assertEquals(-1, SectionPosUtil.posToSectionCoord(-4.0));
        Assertions.assertEquals(-1, SectionPosUtil.posToSectionCoord(-8.0));
        Assertions.assertEquals(-1, SectionPosUtil.posToSectionCoord(-12.0));
        Assertions.assertEquals(-1, SectionPosUtil.posToSectionCoord(-16.0));

        Assertions.assertNotEquals(-2, SectionPosUtil.posToSectionCoord(-16.0));
        Assertions.assertEquals(-2, SectionPosUtil.posToSectionCoord(-20.0));
        Assertions.assertEquals(-2, SectionPosUtil.posToSectionCoord(-24.0));
        Assertions.assertEquals(-2, SectionPosUtil.posToSectionCoord(-28.0));
        Assertions.assertEquals(-2, SectionPosUtil.posToSectionCoord(-32.0));
    }

}
