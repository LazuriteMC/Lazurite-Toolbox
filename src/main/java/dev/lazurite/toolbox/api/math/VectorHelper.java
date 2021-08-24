package dev.lazurite.toolbox.api.math;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

/**
 * A useful helper for dealing with Minecraft vectors.
 */
public class VectorHelper {
    public static Vec3f toVec3f(Vec3d vec3d) {
        return new Vec3f((float) vec3d.x, (float) vec3d.y, (float) vec3d.z);
    }

    public static Vec3d toVec3d(Vec3f vec3f) {
        return new Vec3d(vec3f.getX(), vec3f.getY(), vec3f.getZ());
    }

    /**
     * Lerps two {@link Vec3f} objects using tick delta.
     * @param vec1 the first float vector
     * @param vec2 the second float vector
     * @param delta minecraft tick delta
     * @return the newly lerped {@link Vec3f}
     */
    public static Vec3f lerp(Vec3f vec1, Vec3f vec2, float delta) {
        return new Vec3f(
            MathHelper.lerp(delta, vec1.getX(), vec2.getX()),
            MathHelper.lerp(delta, vec1.getY(), vec2.getY()),
            MathHelper.lerp(delta, vec1.getZ(), vec2.getZ())
        );
    }

    /**
     * Lerps two {@link Vec3d} objects using tick delta.
     * @param vec1 the first double vector
     * @param vec2 the second double vector
     * @param delta minecraft tick delta
     * @return the newly lerped {@link Vec3d}
     */
    public static Vec3d lerp(Vec3d vec1, Vec3d vec2, float delta) {
        return new Vec3d(
                MathHelper.lerp(delta, vec1.getX(), vec2.getX()),
                MathHelper.lerp(delta, vec1.getY(), vec2.getY()),
                MathHelper.lerp(delta, vec1.getZ(), vec2.getZ())
        );
    }

    /**
     * Converts the given {@link Vec3f} into a new {@link NbtCompound}.
     * @param vec the {@link Vec3f} to convert
     * @return the new {@link NbtCompound}
     */
    public static NbtCompound toTag(Vec3f vec) {
        var tag = new NbtCompound();
        tag.putFloat("x", vec.getX());
        tag.putFloat("y", vec.getY());
        tag.putFloat("z", vec.getZ());
        return tag;
    }

    /**
     * Retrieves a {@link Vec3f} from the given {@link NbtCompound}.
     * @param tag the {@link NbtCompound} to retrieve the {@link Vec3f} from
     * @return the new {@link Vec3f}
     */
    public static Vec3f fromTag(NbtCompound tag) {
        return new Vec3f(
                tag.getFloat("x"),
                tag.getFloat("y"),
                tag.getFloat("z")
        );
    }

    /**
     * Stuffs a {@link Vec3f} into a {@link PacketByteBuf}.
     * @param buf the buffer to stuff into
     * @param vec the vector to stuff
     */
    public static void toBuffer(PacketByteBuf buf, Vec3f vec) {
        buf.writeFloat(vec.getX());
        buf.writeFloat(vec.getY());
        buf.writeFloat(vec.getZ());
    }

    /**
     * Unpacks a {@link Vec3f} from a {@link PacketByteBuf}.
     * @param buf the buffer to unpack from
     * @return the newly unpacked vector
     */
    public static Vec3f fromBuffer(PacketByteBuf buf) {
        return new Vec3f(
                buf.readFloat(),
                buf.readFloat(),
                buf.readFloat()
        );
    }
}
