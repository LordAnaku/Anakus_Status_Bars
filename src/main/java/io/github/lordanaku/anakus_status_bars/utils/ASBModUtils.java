package io.github.lordanaku.anakus_status_bars.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


import java.util.*;

@Environment(EnvType.CLIENT)
public class ASBModUtils {

    public static final String MOD_ID = "anakus_status_bars";
    public static final Identifier STATUS_BAR_TEXTURE = new Identifier(MOD_ID, "textures/gui/status_bar.png");

    public final static boolean leftSide = true;
    public final static boolean rightSide = false;

    public static final Map<String, HudElements> registry = new HashMap<>();
    public static final List<HudElements> hudElementsList = new ArrayList<>();

    public static ArrayList<String> leftOrderDefault = new ArrayList<>(Arrays.asList("health", "hunger"));
    public static ArrayList<String> rightOrderDefault = new ArrayList<>(Arrays.asList("armor", "absorption", "breath", "mount"));

    public static int leftSideIncrement = -40;
    public static int rightSideIncrement = -40;

    public static MinecraftClient getClient() {
        return MinecraftClient.getInstance();
    }
    public static PlayerEntity getPlayer() {
        if(!(getClient().getCameraEntity() instanceof PlayerEntity)) {
            return null;
        }
        return (PlayerEntity) getClient().getCameraEntity();
    }

    public static int getScaledWindowWidth() {
        if(!(getClient().getCameraEntity() instanceof PlayerEntity)) {
            return 0;
        }
        return getClient().getWindow().getScaledWidth();
    }
    public static int getScaledWindowHeight() {
        if(!(getClient().getCameraEntity() instanceof PlayerEntity)) {
            return 0;
        }
        return getClient().getWindow().getScaledHeight();
    }

    public static LivingEntity getRiddenEntity() {
        PlayerEntity playerEntity = getPlayer();
        if (playerEntity != null) {
            Entity entity = playerEntity.getVehicle();
            if (entity == null) {
                return null;
            }
            if (entity instanceof LivingEntity) {
                return (LivingEntity)entity;
            }
        }
        return null;
    }

    public static void registerHudElements(HudElements... hudElements) {
        Arrays.stream(hudElements).forEach(hudElement -> registry.put(hudElement.name(), hudElement));
    }


    public static void setupHudElements() {
        hudElementsList.clear();
        for (String hudElements : Settings.positionSettings.get("left")) {
            if (registry.containsKey(hudElements)) {
                hudElementsList.add(registry.get(hudElements).setSide(leftSide));
            }
        }
        for (String hudElements : Settings.positionSettings.get("right")) {
            if (registry.containsKey(hudElements)) {
                hudElementsList.add(registry.get(hudElements).setSide(rightSide));
            }
        }
    }

    public static void incrementBars(boolean side, int amount) {
        if(side) {
            leftSideIncrement += amount;
        } else {
            rightSideIncrement += amount;
        }
    }

    public static void resetIncrements() {
        leftSideIncrement = -40;
        rightSideIncrement = -40;
    }

    @SuppressWarnings("unused")
    public static void sendMessage(String message) {
        if(getPlayer() != null) {
            getPlayer().sendMessage(Text.of(message));
        }
    }

    @SuppressWarnings("rawtypes")
    public static JsonArray getJsonArray(List list) {
        JsonArray jsonArray = new JsonArray();
        for(Object object : list) {
            jsonArray.add(object.toString());
        }
        return jsonArray;
    }

    public static ArrayList<String> getStringList(JsonArray jsonArray) {
        ArrayList<String> list = new ArrayList<>();
        for(JsonElement element : jsonArray) {
            list.add(element.getAsString());
        }
        return list;
    }

    public static int getPosYMod(boolean side) {
        if(side) {
            return leftSideIncrement;
        } else {
            return rightSideIncrement;
        }
    }
}
