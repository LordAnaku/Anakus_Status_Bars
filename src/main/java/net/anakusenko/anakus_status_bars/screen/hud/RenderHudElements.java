package net.anakusenko.anakus_status_bars.screen.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.anakusenko.anakus_status_bars.utils.ColorUtils;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.anakusenko.anakus_status_bars.utils.TextureUtils;
import net.anakusenko.anakus_status_bars.utils.interfaces.HudElements;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static net.anakusenko.anakus_status_bars.utils.ModUtils.hudElementsList;

@Environment(EnvType.CLIENT)
public class RenderHudElements implements HudRenderCallback {
    private static int posXLeft;
    private static int posXRight;
    private static int posY;
    private static MatrixStack hudMatrix;



    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        hudInit(matrixStack);

        Supplier<Stream<HudElements>> supplier = () -> hudElementsList.stream().filter(HudElements::shouldRender);
        supplier.get().forEach(hudElements -> {
            hudElements.renderBar();
            ModUtils.incrementBars(hudElements.getSide(), -10);
        });

        ModUtils.resetIncrements();

        supplier.get().forEach(hudElements -> {
            if(hudElements.shouldRenderIcon()) { hudElements.renderIcon(); }
            ModUtils.incrementBars(hudElements.getSide(), -10);
        });

        ModUtils.resetIncrements();
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
        DrawableHelper.drawTexture(hudMatrix, side, posY + posYMod, TextureUtils.DEFAULT_BAR.getStartX(), TextureUtils.DEFAULT_BAR.getStartY(), TextureUtils.DEFAULT_BAR.getWidth(), TextureUtils.DEFAULT_BAR.getHeight(), 256,256);
    }

    public static void drawExhaustBar(boolean leftSide, int posYMod, int progress, float alpha) {
        RenderSystem.setShaderColor(1, 1, 1, alpha);
        if(leftSide) {
            DrawableHelper.drawTexture(hudMatrix,
                    posXLeft, posY + posYMod,
                    TextureUtils.EXHAUSTION_BAR.getStartX(), TextureUtils.EXHAUSTION_BAR.getStartY(),
                    progress, TextureUtils.EXHAUSTION_BAR.getHeight(),
                    256, 256);
        } else {
            DrawableHelper.drawTexture(hudMatrix,
                    posXRight+(TextureUtils.EXHAUSTION_BAR.getWidth()-progress), posY + posYMod,
                    TextureUtils.EXHAUSTION_BAR.getWidth()-progress, TextureUtils.EXHAUSTION_BAR.getStartY(),
                    TextureUtils.EXHAUSTION_BAR.getWidth(), TextureUtils.EXHAUSTION_BAR.getHeight(),
                    256, 256);
        }
    }

    public static void drawProgressBar(boolean leftSide, int posYMod, int progress, int color, float alpha) {
        RenderSystem.setShaderColor(ColorUtils.fromHex(color).getRedF(), ColorUtils.fromHex(color).getGreenF(), ColorUtils.fromHex(color).getBlueF(), alpha);
        if (leftSide) {
            DrawableHelper.drawTexture(hudMatrix,
                    posXLeft, posY + posYMod,
                    TextureUtils.PROGRESS_BAR.getStartX(), TextureUtils.PROGRESS_BAR.getStartY(),
                    progress, TextureUtils.PROGRESS_BAR.getHeight(),
                    256, 256);
        } else {
            DrawableHelper.drawTexture(hudMatrix,
                    posXRight+(TextureUtils.PROGRESS_BAR.getWidth()-progress), posY + posYMod,
                    TextureUtils.PROGRESS_BAR.getWidth()-progress, TextureUtils.PROGRESS_BAR.getStartY(),
                    TextureUtils.PROGRESS_BAR.getWidth(), TextureUtils.PROGRESS_BAR.getHeight(),
                    256, 256);
        }
    }

    public static void drawStatusEffectBar(boolean leftSide, int posYMod, int color, float alpha) {
        RenderSystem.setShaderColor(ColorUtils.fromHex(color).getRedF(), ColorUtils.fromHex(color).getGreenF(), ColorUtils.fromHex(color).getBlueF(), alpha);
        if (leftSide) {
            DrawableHelper.drawTexture(hudMatrix,
                    posXLeft, posY + posYMod,
                    TextureUtils.DEFAULT_BAR.getStartX(), TextureUtils.DEFAULT_BAR.getStartY(),
                    TextureUtils.DEFAULT_BAR.getWidth(), TextureUtils.DEFAULT_BAR.getHeight(),
                    256, 256);
        } else {
            DrawableHelper.drawTexture(hudMatrix,
                    posXRight, posY + posYMod,
                    TextureUtils.DEFAULT_BAR.getStartX(), TextureUtils.DEFAULT_BAR.getStartY(),
                    TextureUtils.DEFAULT_BAR.getWidth(), TextureUtils.DEFAULT_BAR.getHeight(),
                    256, 256);
        }
    }

    public static void drawIcon(boolean side, int posYMod, TextureUtils textureUtils) {
        RenderSystem.setShaderTexture(0, Screen.GUI_ICONS_TEXTURE);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if (side) {
            DrawableHelper.drawTexture(hudMatrix,
                    posXLeft-10, posY + posYMod,
                    textureUtils.getStartX(), textureUtils.getStartY(),
                    textureUtils.getWidth(), textureUtils.getHeight(),
                    256, 256);
        } else {
            DrawableHelper.drawTexture(hudMatrix,
                    posXRight+82, posY + posYMod,
                    textureUtils.getStartX(), textureUtils.getStartY(),
                    textureUtils.getWidth(), textureUtils.getHeight(),
                    256, 256);
        }
    }
}
