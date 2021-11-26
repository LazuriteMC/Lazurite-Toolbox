package dev.lazurite.toolbox.api.util.fabric;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Collection;

public class PlayerUtilImpl {
    public static Collection<ServerPlayer> tracking(Entity entity) {
        return PlayerLookup.tracking(entity);
    }
}