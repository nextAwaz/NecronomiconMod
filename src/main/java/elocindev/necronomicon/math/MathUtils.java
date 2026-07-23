package elocindev.necronomicon.math;

//#if FABRIC==1
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
//#else
//$$ import net.minecraft.world.entity.LivingEntity;
//$$ import net.minecraft.util.Mth;
//$$ import net.minecraft.world.phys.Vec3;
//#endif

public class MathUtils {
    //#if FABRIC==1
    public static Vec3d getLookingVec(LivingEntity entity) {
    //#else
    //$$ public static Vec3 getLookingVec(LivingEntity entity) {
    //#endif
        float yaw = entity.getYaw(); float pitch = entity.getPitch();

        float radian = 0.017453292F;

        //#if FABRIC==1
        float x = -MathHelper.sin(yaw * radian) * MathHelper.cos(pitch * radian);
        float y = -MathHelper.sin(pitch * radian);
        float z = MathHelper.cos(yaw * radian) * MathHelper.cos(pitch * radian);
        float m = MathHelper.sqrt(x * x + y * y + z * z);
        //#else
        //$$ float x = -Mth.sin(yaw * radian) * Mth.cos(pitch * radian);
        //$$ float y = -Mth.sin(pitch * radian);
        //$$ float z = Mth.cos(yaw * radian) * Mth.cos(pitch * radian);
        //$$ float m = Mth.sqrt(x * x + y * y + z * z);
        //#endif

        //#if FABRIC==1
        return new Vec3d(
        //#else
        //$$ return new Vec3(
        //#endif
            x/m, y/m, z/m);
    }
}
