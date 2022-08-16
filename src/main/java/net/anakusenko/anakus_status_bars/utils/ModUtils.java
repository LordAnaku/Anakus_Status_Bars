package net.anakusenko.anakus_status_bars.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Environment(EnvType.CLIENT)
public class ModUtils {

    public static final String MOD_ID = "anakus_status_bars";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Identifier STATUS_BAR_TEXTURE = new Identifier(MOD_ID, "textures/gui/status_bar.png");

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

    public static void sendMessage(String message) {
        if(getPlayer() != null) {
            getPlayer().sendMessage(Text.of(message));
        }
    }
}
