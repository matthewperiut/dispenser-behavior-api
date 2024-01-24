package com.matthewperiut.dispenserbehaviorapi.mixin;

import com.matthewperiut.dispenserbehaviorapi.DispenserBehavior;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.item.EggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EggItem.class)
public class EggMixin implements DispenserBehavior {
    @Override
    public void dispense(World world, ItemStack item, Vec3d dispenserPos, Vec3d direction, Vec3d velocity) {
        EggEntity egg = new EggEntity(world, velocity.x, velocity.y, velocity.z);
        egg.method_1682((double)direction.x, 0.10000000149011612, (double)direction.z, 1.1F, 6.0F);
        world.method_210(egg);
        world.method_230(1002, (int) dispenserPos.x, (int) dispenserPos.y, (int) dispenserPos.z, 0);
    }
}
