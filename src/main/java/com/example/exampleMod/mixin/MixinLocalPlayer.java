package com.example.exampleMod.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LocalPlayer.class, remap = false)
public abstract class MixinLocalPlayer {
    @Shadow public abstract void sendSystemMessage(Component component);

    @Inject(at = @At("HEAD"), method = "sendOpenInventory", remap = false)
    public void chat(CallbackInfo ci) {
        this.sendSystemMessage(Component.literal("You opened inventory"));
    }
}
