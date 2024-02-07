package com.matthewperiut.dispenserbehaviorapi.mixin;

import com.matthewperiut.dispenserbehaviorapi.DispenserBehaviorAPI;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(DispenserBlockEntity.class)
abstract class DispenserBlockEntityMixin extends BlockEntity implements Inventory {

    @Shadow
    private ItemStack[] inventory;

    @Shadow private Random random;

    public DispenserBlockEntityMixin() {
    }


    @Redirect(
            method = "method_665",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/entity/DispenserBlockEntity;removeStack(II)Lnet/minecraft/item/ItemStack;"
            )
    )
    private ItemStack dispenserBehaviorAPI_removeStack(DispenserBlockEntity instance, int slot, int amount) {
        DispenserBehaviorAPI.dispenserInventory = inventory;
        DispenserBehaviorAPI.slotDispensed = slot;
        return instance.removeStack(slot, amount);
    }
}
