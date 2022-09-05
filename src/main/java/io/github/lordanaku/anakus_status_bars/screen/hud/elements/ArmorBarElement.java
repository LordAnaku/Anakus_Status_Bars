package io.github.lordanaku.anakus_status_bars.screen.hud.elements;

import io.github.lordanaku.anakus_status_bars.api.hudelements.IHudElement;
import io.github.lordanaku.anakus_status_bars.api.hudelements.RenderHudFunctions;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecords;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.ColorEntry;
import me.shedaniel.clothconfig2.gui.entries.FloatListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import static io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings.ARMOR;

public class ArmorBarElement implements IHudElement {
    private boolean renderSide;
    private int armorDmgAmount;
    @Override
    public void renderBar() {
        assert ASBModUtils.getPlayer() != null;
        RenderHudFunctions.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR);
        RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, getArmorProgress(), Settings.colorSettings.get(ARMOR.name()), Settings.alphaSettings.get(ARMOR.name()));
    }

    @Override
    public void renderIcon() {
        RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.ARMOR_ICON, 81);
    }

    @Override
    public void renderText() {
        RenderHudFunctions.drawText(String.valueOf(armorDmgAmount), getSide(), shouldRenderIcon(), ASBModUtils.getPosYMod(getSide()), Settings.textColorSettings.get(ARMOR.name()), 81);
    }

    @Override
    public boolean getSide() {
        return renderSide;
    }

    @Override
    public IHudElement setSide(boolean side) {
        this.renderSide = side;
        return this;
    }

    @Override
    public boolean shouldRender() {
        assert ASBModUtils.getPlayer() != null;
        return Settings.shouldRenderSettings.get(ARMOR.name()) && ASBModUtils.getPlayer().getArmor() > 0;
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.iconSettings.get(ARMOR.name());
    }

    @Override
    public boolean shouldRenderText() {
        return shouldRender() && Settings.textSettings.get(ARMOR.name());
    }

    @Override
    public void registerSettings(ConfigCategory mainCategory, ConfigCategory iconCategory, ConfigCategory textCategory, ConfigCategory colorCategory, ConfigCategory textColorSettings, ConfigCategory alphaCategory, ConfigEntryBuilder entryBuilder) {
        BooleanListEntry toggleArmorBar = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_armor_bar"), Settings.shouldRenderSettings.get(ARMOR.name()))
                .setDefaultValue(ARMOR.shouldRender())
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace(ARMOR.name(), newValue))
                .build();
        mainCategory.addEntry(toggleArmorBar);

        BooleanListEntry toggleArmorIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_armor_icon"), Settings.iconSettings.get(ARMOR.name()))
                .setDefaultValue(ARMOR.shouldRenderIcon())
                .setSaveConsumer(newValue -> Settings.iconSettings.replace(ARMOR.name(), newValue))
                .build();
        iconCategory.addEntry(toggleArmorIcon);

        BooleanListEntry toggleArmorText = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_armor_text"), Settings.textSettings.get(ARMOR.name()))
                .setDefaultValue(ARMOR.shouldRenderText())
                .setSaveConsumer(newValue -> Settings.textSettings.replace(ARMOR.name(), newValue))
                .build();
        textCategory.addEntry(toggleArmorText);

        ColorEntry armorColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.armor_color"), Settings.colorSettings.get(ARMOR.name()))
                .setDefaultValue(ARMOR.color())
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(ARMOR.name(), newValue))
                .build();
        colorCategory.addEntry(armorColor);

        ColorEntry armorTextColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.armor_text_color"), Settings.textColorSettings.get(ARMOR.name()))
                .setDefaultValue(ARMOR.color())
                .setSaveConsumer(newValue -> Settings.textColorSettings.replace(ARMOR.name(), newValue))
                .build();
        textColorSettings.addEntry(armorTextColor);

        FloatListEntry armorAlpha = entryBuilder.startFloatField(Text.translatable("option.anakus_status_bars.armor_alpha"), Settings.alphaSettings.get(ARMOR.name()))
                .setDefaultValue(ARMOR.alpha())
                .setMin(0.0f)
                .setMax(1.0f)
                .setTooltip(Text.translatable("option.anakus_status_bars.alpha_tooltip"))
                .setSaveConsumer(newValue -> Settings.alphaSettings.replace(ARMOR.name(), newValue))
                .build();
        alphaCategory.addEntry(armorAlpha);
    }

    @Override
    public String name() {
        return ARMOR.name();
    }

    private int getArmorProgress() {
        float ratio = getArmorPercentFloat();
        int maxProgress = 81;
        return Math.min(maxProgress, MathHelper.ceil(ratio * maxProgress));
    }

    private float getArmorPercentFloat() {
        assert ASBModUtils.getPlayer() != null; PlayerEntity player = ASBModUtils.getPlayer();
        final float[] armorDmg = {0, 0};
        player.getArmorItems().forEach(itemStack -> {
            armorDmg[0] += itemStack.getMaxDamage() - itemStack.getDamage();
            armorDmg[1] += itemStack.getMaxDamage();
        });
        this.armorDmgAmount = Math.round(armorDmg[0]);
        return Math.min(1, Math.max(0, armorDmg[0] / armorDmg[1]));
    }
}
