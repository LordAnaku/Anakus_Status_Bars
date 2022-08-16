package net.anakusenko.anakus_status_bars.mixin;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class RenderOverlayMixin extends DrawableHelper {

    @Inject(method = "renderStatusBars",
            at = @At("HEAD"),
            cancellable = true)
    private void renderStatusBars(CallbackInfo info) {
        info.cancel();
    }
}
