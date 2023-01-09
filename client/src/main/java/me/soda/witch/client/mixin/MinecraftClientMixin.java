package me.soda.witch.client.mixin;

import me.soda.witch.client.Witch;
import me.soda.witch.client.events.TickEvent;
import me.soda.witch.shared.events.EventBus;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo info) {
        Witch.init();
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        EventBus.INSTANCE.post(TickEvent.get());
    }
}
