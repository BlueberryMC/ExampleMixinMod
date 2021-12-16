package com.example.exampleMod.mixin;

import net.minecraft.Util;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(value = LocalPlayer.class, remap = false)
public abstract class MixinLocalPlayer {
    @Shadow public abstract void sendMessage(Component component, UUID uuid);

    @Inject(at = @At("HEAD"), method = "chat", remap = false)
    public void chat(String s, CallbackInfo ci) {
        this.sendMessage(new TextComponent("You sent: " + s), Util.NIL_UUID);
    }
}
