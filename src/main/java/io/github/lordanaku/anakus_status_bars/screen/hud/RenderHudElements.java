package io.github.lordanaku.anakus_status_bars.screen.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.lordanaku.anakus_status_bars.api.hudelements.IHudElement;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

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
                for (IHudElement hudElement : Settings.I_HUD_ELEMENTS_LIST) {
                    if (hudElement.shouldRender()) {
                        hudElement.renderBar();
                        if(hudElement.shouldRenderIcon()) {
                            hudElement.renderIcon();
                        }
                        ASBModUtils.incrementBars(hudElement.getSide(), -yModIncrement);
                    }
                }
                ASBModUtils.resetIncrements();
                for (IHudElement hudElement : Settings.I_HUD_ELEMENTS_LIST) {
                    if (hudElement.shouldRender()) {
                        if (hudElement.shouldRenderText()) {
                            hudElement.renderText();
                        }
                        ASBModUtils.incrementBars(hudElement.getSide(), -yModIncrement);
                    }
                }
                ASBModUtils.resetIncrements();
            }
        }
    }

//    Supplier<Stream<IHudElement>> supplier = () -> Settings.I_HUD_ELEMENTS_LIST.stream().filter(IHudElement::shouldRender);
//                supplier.get().forEach(hudElements -> {
//        hudElements.renderBar();
//        ASBModUtils.incrementBars(hudElements.getSide(), -yModIncrement);
//    });
//
//                ASBModUtils.resetIncrements();
//
//                supplier.get().forEach(hudElements -> {
//        if (hudElements.shouldRenderIcon()) {
//            hudElements.renderIcon();
//        }
//        ASBModUtils.incrementBars(hudElements.getSide(), -yModIncrement);
//    });
//
//                ASBModUtils.resetIncrements();

    private static void hudInit(MatrixStack matrixStack) {
        if (ASBModUtils.getClient() != null) {
            posXLeft = ((ASBModUtils.getScaledWindowWidth() / 2) - 91) + Settings.positionOffsets.get("left_x_offset");
            posXRight = ((ASBModUtils.getScaledWindowWidth() / 2) + 10) + Settings.positionOffsets.get("right_x_offset");
            posY = ASBModUtils.getScaledWindowHeight();

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
        }
        if (matrixStack != null) {
            hudMatrix = matrixStack;
        }
    }
}
