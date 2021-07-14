package dev.lazurite.toolbox.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;

/**
 * When implemented by an {@link Entity}, it allows it to determine
 * whether or not to render itself and the {@link ClientPlayerEntity}
 * when set as the {@link MinecraftClient#cameraEntity}.
 */
public interface Viewable {

    /**
     * Allows the {@link Entity} to control whether to render
     * itself when set as the {@link MinecraftClient#cameraEntity}.
     * @return {@code true} if it should be rendered in first person
     */
    boolean shouldRenderSelf();

    /**
     * Allows the {@link Entity} to control whether to render the
     * {@link ClientPlayerEntity} when set as the {@link MinecraftClient#cameraEntity}.
     * @return {@code true} if the {@link ClientPlayerEntity} should be rendered in first person
     */
    boolean shouldRenderPlayer();
}