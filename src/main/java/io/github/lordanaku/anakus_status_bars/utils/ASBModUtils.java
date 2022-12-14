package io.github.lordanaku.anakus_status_bars.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.github.lordanaku.anakus_status_bars.api.hudelements.IHudElement;
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
    public static Identifier defaultTexture = new Identifier(MOD_ID, "textures/gui/status_bar.png");

    public final static boolean leftSide = true;
    public final static boolean rightSide = false;
    public static int leftSideIncrement;
    public static int rightSideIncrement;

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

    public static void registerHudElements(IHudElement... IHudElements) {
        Arrays.stream(IHudElements).forEach(hudElement -> Settings.registry.put(hudElement.name(), hudElement));
    }

    public static void setOrderDefaults(String id, boolean side) {
        if (side) {
            Settings.leftOrderDefault.add(id);
        } else {
            Settings.rightOrderDefault.add(id);
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
        leftSideIncrement = -Settings.positionOffsets.get("left_y_offset");
        rightSideIncrement = -Settings.positionOffsets.get("right_y_offset");
    }

    @SuppressWarnings("unused")
    public static void sendMessage(String message) {
        if(getPlayer() != null) {
            getPlayer().sendMessage(Text.of(message));
        }
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
