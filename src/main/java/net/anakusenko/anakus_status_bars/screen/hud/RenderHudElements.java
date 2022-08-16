package net.anakusenko.anakus_status_bars.screen.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.anakusenko.anakus_status_bars.screen.hud.custom.HealthBarElement;
import net.anakusenko.anakus_status_bars.screen.hud.custom.HungerBarElement;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class RenderHudElements implements HudRenderCallback {
    private static int posXLeft;
    private static int posXRight;
    private static int posY;
    private static MatrixStack hudMatrix;

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        hudInit(matrixStack);
        HealthBarElement.drawHealthBar();
        HungerBarElement.drawHungerBar();
    }

    private static void hudInit(MatrixStack matrixStack) {
        if (ModUtils.getClient() != null) {
            posXLeft = (ModUtils.getScaledWindowWidth() / 2) - 91;
            posXRight = (ModUtils.getScaledWindowWidth() / 2) + 10;
            posY = ModUtils.getScaledWindowHeight();

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, ModUtils.STATUS_BAR_TEXTURE);
        }
        if (matrixStack != null) {
            hudMatrix = matrixStack;
        }
    }

    public static void drawDefaultBar(boolean leftSide, int posYMod) {
        int side = (leftSide) ? posXLeft : posXRight;
        RenderSystem.setShaderColor(1, 1, 1, 1);
        DrawableHelper.drawTexture(hudMatrix, side, posY + posYMod, 0,0, 81,9,256,256);
    }

    public static void drawExhaustBar(boolean leftSide, int posYMod, int progress, float alpha) {
        if(leftSide) {
            RenderSystem.setShaderColor(1, 1, 1, alpha);
            DrawableHelper.drawTexture(hudMatrix, posXLeft, posY + posYMod, 0, 0, progress, 27, 256, 256);
        } else {
            RenderSystem.setShaderColor(1, 1, 1, alpha);
            DrawableHelper.drawTexture(hudMatrix, posXRight+(81-progress), posY + posYMod, 81-progress, 27, 81, 9, 256, 256);
        }
    }

    public static void drawProgressBar(boolean leftSide, int posYMod, int progress, float colorR, float colorG, float colorB, float alpha) {
        if (leftSide) {
            RenderSystem.setShaderColor(colorR, colorG, colorB, alpha);
            DrawableHelper.drawTexture(hudMatrix, posXLeft, posY + posYMod, 0, 9, progress, 9, 256, 256);
        } else {
            RenderSystem.setShaderColor(colorR, colorG, colorB, alpha);
            DrawableHelper.drawTexture(hudMatrix, posXRight+(81-progress), posY + posYMod, 81-progress, 9, 81, 9, 256, 256);
        }

    }
}
