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
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import static io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings.FOOD;

public class HungerIHudElement implements IHudElement {
    private boolean renderSide;
    private int currentHunger;
    private int currentSaturation;
    private int currentExhaust;
    private final int maxProgress = 81;
    @Override
    public void renderBar() {
        assert ASBModUtils.getPlayer() != null;
        if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.HUNGER)) {
            RenderHudFunctions.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR, Settings.colorSettings.get(FOOD.name() + "_hunger"));
        } else {
            RenderHudFunctions.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR);
        }

        getHunger();
        RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, currentHunger, Settings.colorSettings.get(FOOD.name()), 1);

        if (Settings.shouldRenderSettings.get(FOOD.name() + "_saturation")) {
            getSaturation();
            RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, currentSaturation, Settings.colorSettings.get(FOOD.name() + "_saturation"), 1);
        }

        if (Settings.shouldRenderSettings.get(FOOD.name() + "_exhaustion")) {
            getExhaust();
            RenderHudFunctions.drawExhaustBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.EXHAUSTION_BAR, currentExhaust, Settings.alphaSettings.get(FOOD.name() + "_exhaustion"));
        }
    }

    @Override
    public void renderIcon() {
        RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HUNGER_OUTLINE_ICON, 81);

        assert ASBModUtils.getPlayer() != null;
        if(ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.HUNGER)) {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HUNGER_EFFECT_ICON, 81);
        } else {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HUNGER_ICON, 81);
        }
    }

    @Override
    public void renderText() {
        assert ASBModUtils.getPlayer() != null;
        if (Settings.shouldRenderSettings.get(FOOD.name() + "_saturation") && ASBModUtils.getPlayer().getHungerManager().getSaturationLevel() > 0) {
            RenderHudFunctions.drawText(String.valueOf(Math.round(ASBModUtils.getPlayer().getHungerManager().getFoodLevel() + ASBModUtils.getPlayer().getHungerManager().getSaturationLevel())), getSide(), shouldRenderIcon(), ASBModUtils.getPosYMod(getSide()),
                    Settings.textColorSettings.get(FOOD.name() + "_saturation"), 81);
        } else {
            RenderHudFunctions.drawText(String.valueOf(ASBModUtils.getPlayer().getHungerManager().getFoodLevel()), getSide(), shouldRenderIcon(), ASBModUtils.getPosYMod(getSide()), Settings.textColorSettings.get(FOOD.name()), 81);
        }
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
        return Settings.shouldRenderSettings.get(FOOD.name());
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.iconSettings.get(FOOD.name());
    }

    @Override
    public boolean shouldRenderText() {
        return shouldRender() && Settings.textSettings.get(FOOD.name());
    }

    @Override
    public void registerSettings(ConfigCategory mainCategory, ConfigCategory iconCategory, ConfigCategory textCategory, ConfigCategory colorCategory, ConfigCategory textColorSettings, ConfigCategory alphaCategory, ConfigEntryBuilder entryBuilder) {
        /* * Food * */
        BooleanListEntry toggleFoodBar = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_food_bar"), Settings.shouldRenderSettings.get(FOOD.name()))
                .setDefaultValue(FOOD.shouldRender())
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace(FOOD.name(), newValue))
                .build();
        mainCategory.addEntry(toggleFoodBar);

        BooleanListEntry toggleFoodIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_food_icon"), Settings.iconSettings.get(FOOD.name()))
                .setDefaultValue(FOOD.shouldRenderIcon())
                .setSaveConsumer(newValue -> Settings.iconSettings.replace(FOOD.name(), newValue))
                .build();
        iconCategory.addEntry(toggleFoodIcon);

        BooleanListEntry toggleFoodText = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_food_text"), Settings.textSettings.get(FOOD.name()))
                .setDefaultValue(FOOD.shouldRenderText())
                .setSaveConsumer(newValue -> Settings.textSettings.replace(FOOD.name(), newValue))
                .build();
        textCategory.addEntry(toggleFoodText);

        ColorEntry foodColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.food_color"), Settings.colorSettings.get(FOOD.name()))
                .setDefaultValue(FOOD.color())
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(FOOD.name(), newValue))
                .build();
        colorCategory.addEntry(foodColor);

        ColorEntry foodTextColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.food_text_color"), Settings.textColorSettings.get(FOOD.name()))
                .setDefaultValue(FOOD.color())
                .setSaveConsumer(newValue -> Settings.textColorSettings.replace(FOOD.name(), newValue))
                .build();
        textColorSettings.addEntry(foodTextColor);

        FloatListEntry foodAlpha = entryBuilder.startFloatField(Text.translatable("option.anakus_status_bars.food_alpha"), Settings.alphaSettings.get(FOOD.name()))
                .setDefaultValue(FOOD.alpha())
                .setMin(0.0f)
                .setMax(1.0f)
                .setTooltip(Text.translatable("option.anakus_status_bars.alpha_tooltip"))
                .setSaveConsumer(newValue -> Settings.alphaSettings.replace(FOOD.name(), newValue))
                .build();
        alphaCategory.addEntry(foodAlpha);

        /* * Status Effects * */
        ColorEntry hungerColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.hunger_color"), Settings.colorSettings.get(FOOD.name() + "_hunger"))
                .setDefaultValue(Settings.HUNGER_COLOR_DEFAULT)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(FOOD.name() + "_hunger", newValue))
                .build();
        colorCategory.addEntry(hungerColor);

        /* * Saturation * */
        BooleanListEntry toggleSaturationBar = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_saturation_bar"), Settings.shouldRenderSettings.get(FOOD.name() + "_saturation"))
                .setDefaultValue(FOOD.shouldRender())
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace(FOOD.name() + "_saturation", newValue))
                .build();
        mainCategory.addEntry(toggleSaturationBar);

        ColorEntry saturationColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.saturation_color"), Settings.colorSettings.get(FOOD.name() + "_saturation"))
                .setDefaultValue(Settings.SATURATION_COLOR_DEFAULT)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(FOOD.name() + "_saturation", newValue))
                .build();
        colorCategory.addEntry(saturationColor);

        ColorEntry saturationTextColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.saturation_text_color"), Settings.textColorSettings.get(FOOD.name() + "_saturation"))
                .setDefaultValue(Settings.SATURATION_COLOR_DEFAULT)
                .setSaveConsumer(newValue -> Settings.textColorSettings.replace(FOOD.name() + "_saturation", newValue))
                .build();
        textColorSettings.addEntry(saturationTextColor);

        FloatListEntry saturationAlpha = entryBuilder.startFloatField(Text.translatable("option.anakus_status_bars.saturation_alpha"), Settings.alphaSettings.get(FOOD.name() + "_saturation"))
                .setDefaultValue(FOOD.alpha())
                .setMin(0.0f)
                .setMax(1.0f)
                .setTooltip(Text.translatable("option.anakus_status_bars.alpha_tooltip"))
                .setSaveConsumer(newValue -> Settings.alphaSettings.replace(FOOD.name() + "_saturation", newValue))
                .build();
        alphaCategory.addEntry(saturationAlpha);

        /* * Exhaustion * */
        BooleanListEntry toggleExhaustionBar = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_exhaustion_bar"), Settings.shouldRenderSettings.get(FOOD.name() + "_exhaustion"))
                .setDefaultValue(FOOD.shouldRender())
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace(FOOD.name() + "_exhaustion", newValue))
                .build();
        mainCategory.addEntry(toggleExhaustionBar);

        FloatListEntry exhaustionAlpha = entryBuilder.startFloatField(Text.translatable("option.anakus_status_bars.exhaustion_alpha"), Settings.alphaSettings.get(FOOD.name() + "_exhaustion"))
                .setDefaultValue(Settings.ALPHA_DEFAULT)
                .setMin(0.0f)
                .setMax(1.0f)
                .setTooltip(Text.translatable("option.anakus_status_bars.alpha_tooltip"))
                .setSaveConsumer(newValue -> Settings.alphaSettings.replace(FOOD.name() + "_exhaustion", newValue))
                .build();
        alphaCategory.addEntry(exhaustionAlpha);
    }

    @Override
    public String name() {
        return FOOD.name();
    }

    private void getHunger() {
        assert ASBModUtils.getPlayer() != null;
        int cFoodLevel = ASBModUtils.getPlayer().getHungerManager().getFoodLevel();
        float ratio = Math.min(1, Math.max(0, cFoodLevel / 20f));
        currentHunger = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress) + 2);
    }

    private void getSaturation() {
        assert ASBModUtils.getPlayer() != null;
        float cSatLevel = ASBModUtils.getPlayer().getHungerManager().getSaturationLevel();
        float ratio = Math.min(1, Math.max(0, cSatLevel / 20f));
        currentSaturation = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress) + 2);
    }

    private void getExhaust() {
        assert ASBModUtils.getPlayer() != null;
        float cExhLevel = ASBModUtils.getPlayer().getHungerManager().getExhaustion();
        float maxExhaust = 4f;
        float ratio = Math.min(1, Math.max(0, cExhLevel / maxExhaust));
        currentExhaust = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress));
    }
}
