package com.matthewperiut.dispenserbehaviorapi;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DispenserBehaviorAPI implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    public static ItemStack[] dispenserInventory = null;

    public static Integer slotDispensed = -1;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
    }

    // sometimes the easiest solution is the best solution
}
