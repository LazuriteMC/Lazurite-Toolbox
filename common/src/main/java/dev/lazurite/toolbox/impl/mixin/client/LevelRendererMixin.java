package dev.lazurite.toolbox.impl.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import dev.lazurite.toolbox.api.event.ClientEvents;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Shadow @Nullable private ClientLevel level;
    private PoseStack poseStack;
    private Camera camera;
    private float tickDelta;

    @Inject(method = "renderLevel", at = @At("HEAD"))
    public void renderLevel(PoseStack poseStack, float tickDelta, long l, boolean bl, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, CallbackInfo info) {
        this.poseStack = poseStack;
        this.camera = camera;
        this.tickDelta = tickDelta;
    }

    @Inject(
        method = "renderLevel",
        at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/debug/DebugRenderer;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;DDD)V",
                ordinal = 0
        )
    )
    public void renderLevel(CallbackInfo info) {
        ClientEvents.Render.BEFORE_DEBUG.invoke(poseStack, camera, level, tickDelta);
    }
}
