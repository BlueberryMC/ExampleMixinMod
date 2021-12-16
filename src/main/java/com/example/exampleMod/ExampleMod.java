package com.example.exampleMod;

import net.blueberrymc.common.bml.BlueberryMod;
import net.minecraft.server.MinecraftServer;

public class ExampleMod extends BlueberryMod {
    @Override
    public void onLoad() {
        MinecraftServer.class.getClassLoader(); // If your setup is working, this line will not show error (do patchVanillaJar if it shows error)
        getLogger().info("Hello world!");
    }

    @Override
    public void onPostInit() {
        getLogger().info("Hello world again!");
    }
}
