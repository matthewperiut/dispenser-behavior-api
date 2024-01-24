package com.matthewperiut.dispenserbehaviorapi.mixin;

import com.matthewperiut.dispenserbehaviorapi.DispenserBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {
    @Inject(method = "method_1774", at = @At("HEAD"), cancellable = true)
    private void dispense(World world, int x, int y, int z, Random random, CallbackInfo ci)
    {
        int meta = world.getBlockMeta(x, y, z);
        byte xDir = 0;
        byte zDir = 0;
        if (meta == 3) {
            zDir = 1;
        } else if (meta == 2) {
            zDir = -1;
        } else if (meta == 5) {
            xDir = 1;
        } else {
            xDir = -1;
        }

        DispenserBlockEntity dispenserBlockEntity = (DispenserBlockEntity)world.method_1777(x, y, z);
        // method 665 is get and remove item from inventory
        ItemStack item = dispenserBlockEntity.method_665();
        double xVel = (double)x + (double)xDir * 0.6 + 0.5;
        double yVel = (double)y + 0.5;
        double zVel = (double)z + (double)zDir * 0.6 + 0.5;

        if (item == null)
            world.method_230(1001, x, y, z, 0);
        else if (item.itemId == Item.ARROW.id) {
            // avoidable but not worth
            ArrowEntity arrow = new ArrowEntity(world, xVel, yVel, zVel);
            arrow.method_1291((double)xDir, 0.10000000149011612, (double)zDir, 1.1F, 6.0F);
            arrow.field_1574 = true;
            world.method_210(arrow);
            world.method_230(1002, x, y, z, 0);
        }
        else if (item.getItem() instanceof DispenserBehavior)
            ((DispenserBehavior) item.getItem()).dispense(world, item, Vec3d.create(x, y, z), Vec3d.create(xDir,0,zDir), Vec3d.create(xVel, yVel, zVel));
        else {
            ItemEntity itemEntity = new ItemEntity(world, xVel, yVel - 0.3, zVel, item);
            double var20 = random.nextDouble() * 0.1 + 0.2;
            itemEntity.velocityX = (double)xDir * var20;
            itemEntity.velocityY = 0.20000000298023224;
            itemEntity.velocityZ = (double)zDir * var20;
            itemEntity.velocityX += random.nextGaussian() * 0.007499999832361937 * 6.0;
            itemEntity.velocityY += random.nextGaussian() * 0.007499999832361937 * 6.0;
            itemEntity.velocityZ += random.nextGaussian() * 0.007499999832361937 * 6.0;
            world.method_210(itemEntity);
            world.method_230(1000, x, y, z, 0);
        }

        world.method_230(2000, x, y, z, xDir + 1 + (zDir + 1) * 3);

        ci.cancel();
    }
}
