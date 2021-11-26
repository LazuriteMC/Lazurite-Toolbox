package dev.lazurite.toolbox.api.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Collection;

/**
 * @since 1.2.5
 */
public class PlayerUtil {
    @ExpectPlatform
    public static Collection<ServerPlayer> tracking(Entity entity) {
        throw new AssertionError();
    }
}