package com.harmex.deathcube.client.gui.overlay;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.thirst.ClientThirstData;
import com.harmex.deathcube.thirst.ThirstConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodConstants;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.awt.*;

public class ModGuiOverlay {
    private static final ResourceLocation ICONS_LOCATION = new ResourceLocation(DeathCube.MODID,
            "textures/gui/icons.png");
    private static final int textureWidth = 256;
    private static final int textureHeight = 256;

    private static int lastHealth;
    private static int displayHealth;
    private static long healthBlinkTime;
    private static long lastHealthTime;
    private static final RandomSource random = RandomSource.create();

    public static final IGuiOverlay PLAYER_HEALTH = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (!gui.getMinecraft().options.hideGui && gui.shouldDrawSurvivalElements()) {
            gui.setupOverlayRenderState(true, false, ICONS_LOCATION);
            gui.getMinecraft().getProfiler().push("health");
            RenderSystem.enableBlend();

            Player player = (Player) gui.getMinecraft().getCameraEntity();
            int health = Mth.ceil(player.getHealth());
            boolean highlight = healthBlinkTime > (long) gui.getGuiTicks() && (healthBlinkTime - (long) gui.getGuiTicks()) / 3L % 2L == 1;

            if (health < lastHealth && player.invulnerableTime > 0) {
                lastHealthTime = Util.getMillis();
                healthBlinkTime = gui.getGuiTicks() + 20;
            } else if (health > lastHealth && player.invulnerableTime > 0) {
                lastHealthTime = Util.getMillis();
                healthBlinkTime = gui.getGuiTicks() + 10;
            }

            if (Util.getMillis() - lastHealthTime > 1000L) {
                lastHealth = health;
                displayHealth = health;
                lastHealthTime = Util.getMillis();
            }

            lastHealth = health;

            AttributeInstance attrMaxHealth = player.getAttribute(Attributes.MAX_HEALTH);
            float maxHealth = (float) attrMaxHealth.getValue();
            int absorption = Mth.ceil(player.getAbsorptionAmount());

            random.setSeed(gui.getGuiTicks() * 312871L);

            int left = screenWidth / 2 - 91;
            int top = screenHeight - gui.leftHeight + 3;
            gui.leftHeight += 7;

            int uOffset = player.level.getLevelData().isHardcore() ? 81 : 0;
            int vOffset = 24;
            if (highlight) vOffset = 30;
            if (player.hasEffect(MobEffects.POISON)) {
                if (highlight) vOffset = 42;
                else vOffset = 36;
            } else if (player.hasEffect(MobEffects.WITHER)) {
                if (highlight) vOffset = 54;
                else vOffset = 48;
            } else if (player.isFullyFrozen()) {
                vOffset = 66;
            }

            int healthBarSize = Mth.ceil(health * 79 / maxHealth);
            int thirtyPercent = Mth.ceil((maxHealth * 30 / 100) * 79 / maxHealth);
            int absorptionBarSize = Mth.ceil(absorption * 81 / maxHealth);

            GuiComponent.blit(poseStack, left, top, uOffset, highlight ? 6 : healthBarSize >= thirtyPercent ? 0 : 12, 81, 6, textureWidth, textureHeight);
            GuiComponent.blit(poseStack, left, top, uOffset, highlight ? 6 : 60, absorptionBarSize, 6, textureWidth, textureHeight);
            GuiComponent.blit(poseStack, left + 1, top, uOffset + 1, vOffset, healthBarSize, 6, textureWidth, textureHeight);

            RenderSystem.disableBlend();
            gui.getMinecraft().getProfiler().pop();
        }
    };
    public static final IGuiOverlay ARMOR_LEVEL = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (!gui.getMinecraft().options.hideGui && gui.shouldDrawSurvivalElements()) {
            gui.setupOverlayRenderState(true, false, ICONS_LOCATION);
            gui.getMinecraft().getProfiler().push("armor");
            RenderSystem.enableBlend();

            Player player = gui.getMinecraft().player;

            int left = screenWidth / 2 - 91;
            int top = screenHeight - gui.leftHeight + 3;
            gui.leftHeight += 7;

            int armorLevel = player.getArmorValue();
            int armorBarLevel = Mth.ceil(armorLevel * 79 / 20.0F);
            int armorBarSize = 79;
            int armorFullBarNumber = 0;
            Color barColor = new Color(88, 88, 196);
            Color prevBarColor = new Color(184, 185, 196);

            if (armorLevel > 0) {
                GuiComponent.blit(poseStack, left, top, 0, 0, 81, 6, textureWidth, textureHeight);

                while (armorBarLevel > armorBarSize) {
                    armorBarLevel -= armorBarSize;
                    armorFullBarNumber++;
                }

                if (armorFullBarNumber > 0) {
                    for (int i = 1; i < armorFullBarNumber; i++) {
                        prevBarColor = barColor;
                        if (i % 6 == 0) barColor = new Color(88, 88, 196);
                        if (i % 6 == 1) barColor = new Color(88, 196, 196);
                        if (i % 6 == 2) barColor = new Color(88, 196, 88);
                        if (i % 6 == 3) barColor = new Color(196, 196, 88);
                        if (i % 6 == 4) barColor = new Color(196, 88, 88);
                        if (i % 6 == 5) barColor = new Color(196, 88, 196);
                    }
                    GuiComponent.fill(poseStack, left + 1 + armorBarLevel, top + 1, left + 80, top + 5, prevBarColor.getRGB());
                    GuiComponent.fill(poseStack, left + 1, top + 1, left + 1 + armorBarLevel, top + 5, barColor.getRGB());
                } else {
                    GuiComponent.fill(poseStack, left + 1, top + 1, left + 1 + armorBarLevel, top + 5, 0xFFB8B9C4);
                }
            }

            RenderSystem.disableBlend();
            gui.getMinecraft().getProfiler().pop();
        }
    };
    public static final IGuiOverlay FOOD_LEVEL = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        Minecraft minecraft = gui.getMinecraft();
        Player player = !(minecraft.getCameraEntity() instanceof Player) ? null : (Player) minecraft.getCameraEntity();
        if (gui.shouldDrawSurvivalElements()
                && !(player.getVehicle() instanceof LivingEntity)
                && !minecraft.options.hideGui) {
            gui.setupOverlayRenderState(true, false, ICONS_LOCATION);

            RenderSystem.enableBlend();

            minecraft.getProfiler().popPush("food");

            int left = screenWidth / 2 + 91 - 81;
            int top = screenHeight - gui.rightHeight + 3;
            gui.rightHeight += 7;

            FoodData stats = player.getFoodData();
            int foodLevel = Mth.ceil(stats.getFoodLevel() * 79 / (float) FoodConstants.MAX_FOOD);
            int thirtyPercent = Mth.ceil(((float) FoodConstants.MAX_FOOD * 30 / 100) * 79 / (float) FoodConstants.MAX_FOOD);
            int foodSaturationLevel = Mth.ceil(stats.getSaturationLevel() * 81 / FoodConstants.MAX_SATURATION);

            int uOffset = 162;
            int vOffsetContainer = 0;
            int vOffsetFood = 24;
            int vOffsetSaturation = 54;

            if (player.hasEffect(MobEffects.HUNGER)) {
                vOffsetContainer = 48;
                vOffsetFood = 30;
            } else if (foodLevel <= thirtyPercent) {
                vOffsetContainer = 12;
            }

            GuiComponent.blit(poseStack, left, top, uOffset, vOffsetContainer, 81, 6, textureWidth, textureHeight);
            GuiComponent.blit(poseStack, left + 81 - foodSaturationLevel, top, uOffset + 81 - foodSaturationLevel, vOffsetSaturation, foodSaturationLevel, 6, textureWidth, textureHeight);
            GuiComponent.blit(poseStack, left + 80 - foodLevel, top, uOffset + 80 - foodLevel, vOffsetFood, foodLevel, 6, textureWidth, textureHeight);

            RenderSystem.disableBlend();
            minecraft.getProfiler().pop();
        }
    };
    public static final IGuiOverlay THIRST_LEVEL = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        Minecraft minecraft = gui.getMinecraft();
        Player player = !(minecraft.getCameraEntity() instanceof Player) ? null : (Player)minecraft.getCameraEntity();
        if (gui.shouldDrawSurvivalElements()
                && !(player.getVehicle() instanceof LivingEntity)
                && !minecraft.options.hideGui) {
            gui.setupOverlayRenderState(true, false, ICONS_LOCATION);

            RenderSystem.enableBlend();

            minecraft.getProfiler().popPush("food");

            int left = screenWidth / 2 + 91 - 81;
            int top = screenHeight - gui.rightHeight + 3;
            gui.rightHeight += 7;

            int thirstLevel = Mth.ceil(ClientThirstData.getPlayerThirst() * 79 / (float) ThirstConstants.MAX_THIRST);
            int thirtyPercent = Mth.ceil(((float) ThirstConstants.MAX_THIRST * 30 / 100) * 79 / (float) ThirstConstants.MAX_THIRST);
            int thirstSaturationLevel = Mth.ceil(ClientThirstData.getPlayerThirstSaturation() * 81 / (float) ThirstConstants.MAX_SATURATION);

            int uOffset = 162;
            int vOffsetContainer = 0;
            int vOffsetThirst = 36;
            int vOffsetSaturation = 54;

            if (player.hasEffect(MobEffects.HUNGER)) {
                vOffsetContainer = 48;
                vOffsetThirst = 42;
            } else if (thirstLevel <= thirtyPercent) {
                vOffsetContainer = 12;
            }

            GuiComponent.blit(poseStack, left, top, uOffset, vOffsetContainer, 81, 6, textureWidth, textureHeight);
            GuiComponent.blit(poseStack, left + 81 - thirstSaturationLevel, top, uOffset + 81 - thirstSaturationLevel, vOffsetSaturation, thirstSaturationLevel, 6, textureWidth, textureHeight);
            GuiComponent.blit(poseStack, left + 80 - thirstLevel, top, uOffset + 80 - thirstLevel, vOffsetThirst, thirstLevel, 6, textureWidth, textureHeight);

            RenderSystem.disableBlend();
            minecraft.getProfiler().pop();
        }
    };
}
