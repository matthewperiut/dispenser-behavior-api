package com.matthewperiut.dispenserbehaviorapi;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public interface DispenserBehavior {
    void dispense(World world, ItemStack item, Vec3d dispenserPos, Vec3d direction, Vec3d velocity);
}
