package com.jamieswhiteshirt.developermode.mixin.client.gui;

import com.jamieswhiteshirt.developermode.client.DeveloperModeClient;
import net.minecraft.client.gui.MainMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MainMenuScreen.class)
public abstract class MainMenuScreenMixin {
    @Shadow private boolean doBackgroundFade;

    @Inject(
        at = @At("RETURN"),
        method = "<init>(Z)V"
    )
    private void constructor(boolean boolean_1, CallbackInfo ci) {
        if (DeveloperModeClient.splashFadeTime <= 0) {
            doBackgroundFade = false;
        }
    }

    @ModifyConstant(
        constant = @Constant(
            floatValue = 1000.F,
            ordinal = 0
        ),
        method = "render(IIF)V",
        remap = false
    )
    private float modifyFadeTime1(float originalValue) {
        return DeveloperModeClient.splashFadeTime / 2.0F;
    }
}
