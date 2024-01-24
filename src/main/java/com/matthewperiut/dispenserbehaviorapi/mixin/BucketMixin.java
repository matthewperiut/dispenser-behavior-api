package com.matthewperiut.dispenserbehaviorapi.mixin;

import com.matthewperiut.dispenserbehaviorapi.DispenserBehavior;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(BucketItem.class)
public class BucketMixin implements DispenserBehavior {
    @Shadow private int field_842;

    @Override
    public void dispense(World world, ItemStack item, Vec3d dispenserPos, Vec3d direction, Vec3d velocity) {

        boolean good = false;
        if (field_842 > 0) {
            Vec3d target = dispenserPos.add(direction.x, direction.y, direction.z);
            if (world.getBlockId((int) target.x, (int) target.y, (int) target.z) == 0) {
                world.setBlock((int) target.x, (int) target.y, (int) target.z, field_842);
                good = true;
            }
        }

        if (!good) {
            Random r = new Random();
            ItemEntity itemEntity = new ItemEntity(world, velocity.x, velocity.y - 0.3, velocity.z, item);
            double var20 = r.nextDouble() * 0.1 + 0.2;
            itemEntity.velocityX = (double)direction.x * var20;
            itemEntity.velocityY = 0.20000000298023224;
            itemEntity.velocityZ = (double)direction.z * var20;
            itemEntity.velocityX += r.nextGaussian() * 0.007499999832361937 * 6.0;
            itemEntity.velocityY += r.nextGaussian() * 0.007499999832361937 * 6.0;
            itemEntity.velocityZ += r.nextGaussian() * 0.007499999832361937 * 6.0;
            world.method_210(itemEntity);
            world.method_230(1000, (int) dispenserPos.x, (int) dispenserPos.y, (int) dispenserPos.z, 0);
        }

        world.method_230(1002, (int) dispenserPos.x, (int) dispenserPos.y, (int) dispenserPos.z, 0);
    }
}
