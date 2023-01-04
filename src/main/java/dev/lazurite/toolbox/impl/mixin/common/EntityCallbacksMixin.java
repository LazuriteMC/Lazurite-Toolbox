package dev.lazurite.toolbox.impl.mixin.common;

import dev.lazurite.toolbox.api.event.ServerEvents;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/minecraft/server/level/ServerLevel$EntityCallbacks")
public class EntityCallbacksMixin {
    @Inject(method = "onTrackingStart*", at = @At("TAIL"))
    public void onTrackingStart(Entity entity, CallbackInfo ci) {
        ServerEvents.Entity.LOAD.invoke(entity);
    }

    @Inject(method = "onTrackingEnd*", at = @At("HEAD"))
    public void onTrackingEnd(Entity entity, CallbackInfo info) {
        ServerEvents.Entity.UNLOAD.invoke(entity);
    }
}
