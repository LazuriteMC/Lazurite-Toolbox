package dev.lazurite.toolbox.impl.mixin.common;

import dev.lazurite.toolbox.api.event.ServerEvents;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerEntity.class)
public class ServerEntityMixin {
    @Shadow @Final private Entity entity;

    @Inject(method = "addPairing", at = @At("HEAD"))
    public void addPairing(ServerPlayer player, CallbackInfo info) {
        ServerEvents.Entity.START_TRACKING.invoke(entity, player);
    }

    @Inject(method = "removePairing", at = @At("TAIL"))
    public void onStopTracking(ServerPlayer player, CallbackInfo info) {
        ServerEvents.Entity.STOP_TRACKING.invoke(entity, player);
    }
}
