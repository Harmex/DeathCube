package com.harmex.deathcube.client.gui.overlay;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.capabilities.mana.ClientManaData;
import com.harmex.deathcube.capabilities.thirst.ClientThirstData;
import com.harmex.deathcube.capabilities.thirst.ThirstConstants;
import com.harmex.deathcube.entity.attribute.ModAttributes;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class ModGuiOverlay {
    private static final ResourceLocation ICONS_LOCATION = new ResourceLocation(DeathCube.MODID, "textures/gui/icons.png");
    private static final int textureWidth = 256;
    private static final int textureHeight = 256;

    private static int lastHealth;
    private static long healthBlinkTime;
    private static long lastHealthTime;
    private static final RandomSource random = RandomSource.create();

    public static final IGuiOverlay PLAYER_HEALTH = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (!gui.getMinecraft().options.hideGui && gui.shouldDrawSurvivalElements()) {
            gui.setupOverlayRenderState(true, false, ICONS_LOCATION);
            gui.getMinecraft().getProfiler().push("health");
            RenderSystem.enableBlend();

            Player player = (Player) gui.getMinecraft().getCameraEntity();
            assert player != null;
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
                lastHealthTime = Util.getMillis();
            }

            lastHealth = health;

            AttributeInstance attrMaxHealth = player.getAttribute(Attributes.MAX_HEALTH);
            assert attrMaxHealth != null;
            float maxHealth = (float) attrMaxHealth.getValue();
            int absorption = Mth.ceil(player.getAbsorptionAmount());

            random.setSeed(gui.getGuiTicks() * 312871L);

            int left = screenWidth / 2 - 91;
            int top = screenHeight - gui.leftHeight - 4;
            gui.leftHeight += 14;

            Color textColor = new Color(128, 9, 9);
            int uOffset = player.level.getLevelData().isHardcore() ? 81 : 0;
            int vOffset = 52;
            if (highlight) {
                vOffset = 65;
                textColor = new Color(128, 80, 80);
            }
            if (player.hasEffect(MobEffects.POISON)) {
                if (highlight) {
                    vOffset = 91;
                    textColor = new Color(82, 74, 47);
                } else {
                    vOffset = 78;
                    textColor = new Color(74, 59, 12);
                }
            } else if (player.hasEffect(MobEffects.WITHER)) {
                if (highlight) {
                    vOffset = 117;
                    textColor = new Color(128, 128, 128);
                } else {
                    vOffset = 104;
                    textColor = new Color(87, 87, 87);
                }
            } else if (player.isFullyFrozen()) {
                vOffset = 143;
                textColor = new Color(65, 115, 120);
            }

            int healthBarSize = Mth.ceil(health * 79 / maxHealth);
            int thirtyPercent = Mth.ceil((maxHealth * 30 / 100) * 79 / maxHealth);
            int absorptionBarSize = Mth.ceil(absorption * 79 / maxHealth);

            GuiComponent.blit(poseStack, left, top, uOffset, highlight ? 13 : healthBarSize >= thirtyPercent ? 0 : 26, 81, 13, textureWidth, textureHeight);
            GuiComponent.blit(poseStack, left + 1, top + 1, uOffset + 1, vOffset + 1, healthBarSize, 11, textureWidth, textureHeight);
            GuiComponent.blit(poseStack, left + 1, top + 1, uOffset + 1, 130 + 1, absorptionBarSize, 11, textureWidth, textureHeight);

            String healthText = String.valueOf(Mth.ceil(health + absorption));
            String maxHealthText = String.valueOf(Mth.ceil(maxHealth));

            if (Mth.ceil(absorption) > 0) {
                textColor = new Color(105, 86, 27);
            }

            Font guiFont = gui.getFont();

            guiFont.draw(poseStack, healthText, left + 38 - guiFont.width(healthText) - guiFont.width("/") + 2, top + 2, textColor.getRGB());
            guiFont.draw(poseStack, "/", left + 38, top + 2, textColor.getRGB());
            guiFont.draw(poseStack, maxHealthText, left + 38 + (guiFont.width("/") * 2) - 2, top + 2, textColor.getRGB());


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

            assert player != null;
            int armorLevel = player.getArmorValue();
            int armorBarLevel = Mth.ceil(armorLevel * 79 / 20.0F);
            int armorBarSize = 79;
            int armorFullBarNumber = 0;
            int prevVOffset = 60;
            int vOffset = 66;
            int uOffset = 162;

            if (armorLevel > 0) {
                GuiComponent.blit(poseStack, left, top, uOffset, 0, 81, 6, textureWidth, textureHeight);

                while (armorBarLevel > armorBarSize) {
                    armorBarLevel -= armorBarSize;
                    armorFullBarNumber++;
                }

                if (armorFullBarNumber > 0) {
                    for (int i = 1; i < armorFullBarNumber; i++) {
                        prevVOffset = vOffset;
                        vOffset = prevVOffset + 6 * i;

                    }
                    GuiComponent.blit(poseStack, left + 1, top, uOffset + 1, prevVOffset, armorBarSize, 6, textureWidth, textureHeight);
                    GuiComponent.blit(poseStack, left + 1, top, uOffset + 1, vOffset, armorBarLevel, 6, textureWidth, textureHeight);
                } else {
                    GuiComponent.blit(poseStack, left + 1, top, uOffset + 1, prevVOffset, armorBarLevel, 6, textureWidth, textureHeight);
                }
            }

            RenderSystem.disableBlend();
            gui.getMinecraft().getProfiler().pop();
        }
    };
    public static final IGuiOverlay PLAYER_MANA = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        Minecraft minecraft = gui.getMinecraft();
        Player player = !(minecraft.getCameraEntity() instanceof Player) ? null : (Player) minecraft.getCameraEntity();
        if (gui.shouldDrawSurvivalElements()) {
            assert player != null;
            if (!(player.getVehicle() instanceof LivingEntity) && !minecraft.options.hideGui) {
                gui.setupOverlayRenderState(true, false, ICONS_LOCATION);

                RenderSystem.enableBlend();

                minecraft.getProfiler().popPush("mana");

                int left = screenWidth / 2 + 91 - 81;
                int top = screenHeight - gui.rightHeight - 4;
                gui.rightHeight += 14;

                AttributeInstance manaAttribute = player.getAttribute(ModAttributes.MAX_MANA.get());
                assert manaAttribute != null;
                float maxMana = (float) manaAttribute.getValue();
                float manaLevel = ClientManaData.getManaLevel();
                int manaBarSize = Mth.ceil(manaLevel * 79 / maxMana);
                int thirtyPercent = Mth.ceil((maxMana * 30 / 100) * 79 / maxMana);
                Color textColor = new Color(89, 50, 102);

                int uOffset = player.level.getLevelData().isHardcore() ? 81 : 0;;
                int vOffsetContainer = 0;
                int vOffsetMana = 157;

                if (manaBarSize <= thirtyPercent) {
                    vOffsetContainer = 13;
                }

                GuiComponent.blit(poseStack, left, top, uOffset, vOffsetContainer, 81, 13, textureWidth, textureHeight);
                GuiComponent.blit(poseStack, left + 80 - manaBarSize, top + 1, uOffset + 80 - manaBarSize, vOffsetMana, manaBarSize, 11, textureWidth, textureHeight);

                String manaText = String.valueOf(Mth.ceil(manaLevel));
                String maxManaText = String.valueOf(Mth.ceil(maxMana));
                Font guiFont = gui.getFont();
                guiFont.draw(poseStack, manaText, left + 38 - guiFont.width(manaText) - guiFont.width("/") + 2, top + 2, textColor.getRGB());
                guiFont.draw(poseStack, "/", left + 38, top + 2, textColor.getRGB());
                guiFont.draw(poseStack, maxManaText, left + 38 + (guiFont.width("/") * 2) - 2, top + 2, textColor.getRGB());

                RenderSystem.disableBlend();
                minecraft.getProfiler().pop();
            }
        }
    };
    public static final IGuiOverlay FOOD_LEVEL = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        Minecraft minecraft = gui.getMinecraft();
        Player player = !(minecraft.getCameraEntity() instanceof Player) ? null : (Player) minecraft.getCameraEntity();
        if (gui.shouldDrawSurvivalElements()) {
            assert player != null;
            if (!(player.getVehicle() instanceof LivingEntity) && !minecraft.options.hideGui) {
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
        }
    };
    public static final IGuiOverlay THIRST_LEVEL = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        Minecraft minecraft = gui.getMinecraft();
        Player player = !(minecraft.getCameraEntity() instanceof Player) ? null : (Player) minecraft.getCameraEntity();
        if (gui.shouldDrawSurvivalElements()) {
            assert player != null;
            if (!(player.getVehicle() instanceof LivingEntity) && !minecraft.options.hideGui) {
                gui.setupOverlayRenderState(true, false, ICONS_LOCATION);

                RenderSystem.enableBlend();

                minecraft.getProfiler().popPush("food");

                int left = screenWidth / 2 + 91 - 81;
                int top = screenHeight - gui.rightHeight + 3;
                gui.rightHeight += 7;

                int thirstLevel = Mth.ceil(ClientThirstData.getThirstLevel() * 79 / (float) ThirstConstants.MAX_THIRST);
                int thirtyPercent = Mth.ceil(((float) ThirstConstants.MAX_THIRST * 30 / 100) * 79 / (float) ThirstConstants.MAX_THIRST);
                int thirstSaturationLevel = Mth.ceil(ClientThirstData.getSaturationLevel() * 81 / ThirstConstants.MAX_SATURATION);

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
        }
    };
}
