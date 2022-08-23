package io.github.lordanaku.anakus_status_bars.api.hudelements;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.utils.ColorUtils;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecord;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.util.Identifier;

import static io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements.*;

@SuppressWarnings("unused")
public class RenderHudFunctions {

    /**
     * Draws the defalut background bar for the HUD.
     * @param side - true if the bar is on the left side of the screen, false if on the right side.
     * @param posYMod - the amount you want to add to the base -40 y position.
     * @param textureRecord - the texture record for the bar.
     */
    public static void drawDefaultBar(boolean side, int posYMod, TextureRecord textureRecord) {
        RenderSystem.setShaderTexture(0, textureRecord.texture());
        int finalSide = (side) ? posXLeft : posXRight;
        RenderSystem.setShaderColor(1, 1, 1, 1);
        DrawableHelper.drawTexture(hudMatrix, finalSide, posY + posYMod,
                textureRecord.startX(), textureRecord.startY(),
                textureRecord.width(), textureRecord.height(),
                textureRecord.maxWidth(), textureRecord.maxHeight());
    }

    /**
     * Draws an overlay on the bar for an alternative way of showing info.
     * @param side - true if the bar is on the left side of the screen, false if on the right side.
     * @param posYMod - the amount you want to add to the base -40 y position.
     * @param textureRecord - the texture record for the bar.
     * @param progress - the progress of the bar.
     * @param alpha - the color of the bar. (Hex Value)
     */
    public static void drawExhaustBar(boolean side, int posYMod, TextureRecord textureRecord, int progress, float alpha) {
        RenderSystem.setShaderTexture(0, textureRecord.texture());
        RenderSystem.setShaderColor(1, 1, 1, alpha);
        drawProgress(side, posYMod, textureRecord, progress);
    }

    /**
     * Draws the progress bar for the HUD.
     * @param side - true if the bar is on the left side of the screen, false if on the right side.
     * @param posYMod - the amount you want to add to the base -40 y position.
     * @param textureRecord - the texture record for the bar.
     * @param progress - the progress of the bar.
     * @param color - the color of the bar. (Hex Value)
     * @param alpha - the alpha of the bar.
     */
    public static void drawProgressBar(boolean side, int posYMod, TextureRecord textureRecord, int progress, int color, float alpha) {
        RenderSystem.setShaderTexture(0, textureRecord.texture());
        RenderSystem.setShaderColor(ColorUtils.fromHex(color).getRedF(), ColorUtils.fromHex(color).getGreenF(), ColorUtils.fromHex(color).getBlueF(), alpha);
        drawProgress(side, posYMod, textureRecord, progress);
    }

    /**
     * Draws Colorized bar for when status effect is applied.
     * @param side - true if the bar is on the left side of the screen, false if on the right side.
     * @param posYMod - the amount you want to add to the base -40 y position.
     * @param textureRecord - the texture record for the bar.
     * @param color - the color of the bar. (Hex Value)
     */
    public static void drawStatusEffectBar(boolean side, int posYMod, TextureRecord textureRecord, int color) {
        RenderSystem.setShaderTexture(0, textureRecord.texture());
        RenderSystem.setShaderColor(ColorUtils.fromHex(color).getRedF(), ColorUtils.fromHex(color).getGreenF(), ColorUtils.fromHex(color).getBlueF(), 1);
        int finalSide = (side) ? posXLeft : posXRight;
        DrawableHelper.drawTexture(hudMatrix, finalSide, posY + posYMod,
                textureRecord.startX(), textureRecord.startY(),
                textureRecord.width(), textureRecord.height(),
                textureRecord.maxWidth(), textureRecord.maxHeight());
    }

    /**
     * Draws the icon for the bar.
     * @param side - true if the bar is on the left side of the screen, false if on the right side.
     * @param posYMod - the amount you want to add to the base -40 y position.
     * @param textureRecord - the texture record for the bar.
     * @param barWidth - the width of the bar so method can determine offset.
     */
    public static void drawIcon(boolean side, int posYMod, TextureRecord textureRecord, int barWidth) {
        RenderSystem.setShaderTexture(0, textureRecord.texture());
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

    /**
     * Sets the amount that the bars will iterate up and down as what is rendered changes. (set to height of bar + 1 for default))
     * @param incrementAmount - amount Y is incremented by.
     */
    public static void setYModIncrement(int incrementAmount) {
        yModIncrement = incrementAmount;
    }

    /**
     * Sets defalut texture for the bars. (only call if you wish to set your own texture for vanilla hud elements)
     * @param texture - New texture for the bars.
     */
    public static void setDefaultTexture(Identifier texture) {
        ASBModUtils.defaultTexture = texture;
    }

    private static void drawProgress(boolean side, int posYMod, TextureRecord textureRecord, int progress) {
        if (side) {
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
