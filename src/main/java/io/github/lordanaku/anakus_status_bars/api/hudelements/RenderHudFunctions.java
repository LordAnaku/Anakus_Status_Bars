package io.github.lordanaku.anakus_status_bars.api.hudelements;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.utils.ColorUtils;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecord;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;

import static io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements.*;

@SuppressWarnings("unused")
public class RenderHudFunctions {
    public static void drawDefaultBar(boolean leftSide, int posYMod, TextureRecord textureRecord) {
        RenderSystem.setShaderTexture(0, textureRecord.texture());
        int side = (leftSide) ? posXLeft : posXRight;
        RenderSystem.setShaderColor(1, 1, 1, 1);
        DrawableHelper.drawTexture(hudMatrix, side, posY + posYMod,
                textureRecord.startX(), textureRecord.startY(),
                textureRecord.width(), textureRecord.height(),
                textureRecord.maxWidth(), textureRecord.maxHeight());
    }

    public static void drawExhaustBar(boolean leftSide, int posYMod, TextureRecord textureRecord, int progress, float alpha) {
        RenderSystem.setShaderTexture(0, textureRecord.texture());
        RenderSystem.setShaderColor(1, 1, 1, alpha);
        drawProgress(leftSide, posYMod, textureRecord, progress);
    }

    public static void drawProgressBar(boolean leftSide, int posYMod, TextureRecord textureRecord, int progress, int color, float alpha) {
        RenderSystem.setShaderTexture(0, textureRecord.texture());
        RenderSystem.setShaderColor(ColorUtils.fromHex(color).getRedF(), ColorUtils.fromHex(color).getGreenF(), ColorUtils.fromHex(color).getBlueF(), alpha);
        drawProgress(leftSide, posYMod, textureRecord, progress);
    }

    public static void drawStatusEffectBar(boolean leftSide, int posYMod, TextureRecord textureRecord, int color) {
        RenderSystem.setShaderTexture(0, textureRecord.texture());
        RenderSystem.setShaderColor(ColorUtils.fromHex(color).getRedF(), ColorUtils.fromHex(color).getGreenF(), ColorUtils.fromHex(color).getBlueF(), 1);
        int side = (leftSide) ? posXLeft : posXRight;
        DrawableHelper.drawTexture(hudMatrix, side, posY + posYMod,
                textureRecord.startX(), textureRecord.startY(),
                textureRecord.width(), textureRecord.height(),
                textureRecord.maxWidth(), textureRecord.maxHeight());
    }

    public static void drawIcon(boolean side, int posYMod, TextureRecord textureRecord, int barWidth) {
        RenderSystem.setShaderTexture(0, Screen.GUI_ICONS_TEXTURE);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if (side) {
            DrawableHelper.drawTexture(hudMatrix,
                    posXLeft - (textureRecord.width() + 1), posY + posYMod,
                    textureRecord.startX(), textureRecord.startY(),
                    textureRecord.width(), textureRecord.height(),
                    textureRecord.maxWidth(), textureRecord.maxHeight());
        } else {
            DrawableHelper.drawTexture(hudMatrix,
                    posXRight + (barWidth + 1), posY + posYMod,
                    textureRecord.startX(), textureRecord.startY(),
                    textureRecord.width(), textureRecord.height(),
                    textureRecord.maxWidth(), textureRecord.maxHeight());
        }
    }

    public static void setYModIncrement(int incrementAmount) {
        yModIncrement = incrementAmount;
    }

    public static void setDefaultTexture(Identifier texture) {
        ASBModUtils.defaultTexture = texture;
    }

    private static void drawProgress(boolean leftSide, int posYMod, TextureRecord textureRecord, int progress) {
        if (leftSide) {
            DrawableHelper.drawTexture(hudMatrix,
                    posXLeft, posY + posYMod,
                    textureRecord.startX(), textureRecord.startY(),
                    progress, textureRecord.height(),
                    textureRecord.maxWidth(), textureRecord.maxHeight());
        } else {
            DrawableHelper.drawTexture(hudMatrix,
                    posXRight + (textureRecord.width() - progress), posY + posYMod,
                    textureRecord.startX() + (textureRecord.width() - progress), textureRecord.startY(),
                    textureRecord.width(), textureRecord.height(),
                    textureRecord.maxWidth(), textureRecord.maxHeight());
        }
    }
}
