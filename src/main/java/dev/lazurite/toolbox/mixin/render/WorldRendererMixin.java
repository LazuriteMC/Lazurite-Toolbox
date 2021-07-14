package dev.lazurite.toolbox.mixin.render;

import dev.lazurite.toolbox.render.Viewable;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/Camera;isThirdPerson()Z"
            )
    )
    public boolean render_isThirdPerson(Camera camera) {
        return camera.isThirdPerson() || (camera.getFocusedEntity() instanceof Viewable viewable && viewable.shouldRenderSelf());
    }

    // for production environment
    @Redirect(
            method = "render",
            at = @At(
                    value = "CONSTANT",
                    args = "classValue=net/minecraft/class_746"
            ),
            require = 0
    )
    public boolean render_instanceof_prod(Object entity, Class<?> clazz, MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera) {
        return clazz.isInstance(entity) && camera.getFocusedEntity() instanceof Viewable viewable && !viewable.shouldRenderPlayer();
    }

    // for development environment
    @Redirect(
            method = "render",
            at = @At(
                    value = "CONSTANT",
                    args = "classValue=net/minecraft/client/network/ClientPlayerEntity"
            ),
            require = 0
    )
    public boolean render_instanceof_dev(Object entity, Class<?> clazz, MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera) {
        return clazz.isInstance(entity) && camera.getFocusedEntity() instanceof Viewable viewable && !viewable.shouldRenderPlayer();
    }
}
