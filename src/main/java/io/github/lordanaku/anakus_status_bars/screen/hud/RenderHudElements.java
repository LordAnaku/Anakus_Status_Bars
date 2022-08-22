package io.github.lordanaku.anakus_status_bars.screen.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;


import java.util.function.Supplier;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class RenderHudElements implements HudRenderCallback {
    public static int posXLeft;
    public static int posXRight;
    public static int posY;
    public static MatrixStack hudMatrix;

    public static int yModIncrement = 10;



    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        hudInit(matrixStack);

        assert ASBModUtils.getPlayer() != null;
        if (!ASBModUtils.getPlayer().isCreative()) {
            if (!ASBModUtils.getPlayer().isSpectator()){
                Supplier<Stream<HudElements>> supplier = () -> Settings.hudElementsList.stream().filter(HudElements::shouldRender);
                supplier.get().forEach(hudElements -> {
                    hudElements.renderBar();
                    ASBModUtils.incrementBars(hudElements.getSide(), -yModIncrement);
                });

                ASBModUtils.resetIncrements();

                supplier.get().forEach(hudElements -> {
                    if (hudElements.shouldRenderIcon()) {
                        hudElements.renderIcon();
                    }
                    ASBModUtils.incrementBars(hudElements.getSide(), -yModIncrement);
                });

                ASBModUtils.resetIncrements();
            }
        }
    }

    private static void hudInit(MatrixStack matrixStack) {
        if (ASBModUtils.getClient() != null) {
            posXLeft = (ASBModUtils.getScaledWindowWidth() / 2) - 91;
            posXRight = (ASBModUtils.getScaledWindowWidth() / 2) + 10;
            posY = ASBModUtils.getScaledWindowHeight();

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
        }
        if (matrixStack != null) {
            hudMatrix = matrixStack;
        }
    }
}
