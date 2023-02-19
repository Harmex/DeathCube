package com.harmex.deathcube.client.gui.overlay;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.thirst.ClientThirstData;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
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
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

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

    public static final IGuiOverlay THIRST_LEVEL = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        Minecraft minecraft = gui.getMinecraft();
        Player player = !(minecraft.getCameraEntity() instanceof Player) ? null : (Player)minecraft.getCameraEntity();
        if (gui.shouldDrawSurvivalElements()
                && !(player.getVehicle() instanceof LivingEntity)
                && !minecraft.options.hideGui) {
            gui.setupOverlayRenderState(true, false, ICONS_LOCATION);

            RenderSystem.enableBlend();

            minecraft.getProfiler().popPush("thirst");

            int left = screenWidth / 2 + 91;
            int top = screenHeight - gui.rightHeight;
            gui.rightHeight += 10;

            int thirstLevel = ClientThirstData.getPlayerThirst();
            int thirstSaturationLevel = ClientThirstData.getPlayerThirstSaturation();

            for (int i = 0; i < 10 ; i++) {
                int idx = i * 2 + 1;
                int x = left - i * 8 - 9;
                int y = top;

                if (thirstSaturationLevel <= 0.0F
                        && gui.getGuiTicks() % (thirstLevel * 3 + 1) == 0
                        && gui.getGuiTicks() % 2 == 0
                        && !minecraft.isPaused()) {
                    y = top + (random.nextInt(3) - 1);

                }

                GuiComponent.blit(poseStack, x, y, 0, 247, 9, 9, textureWidth, textureHeight);

                if (i < thirstSaturationLevel) {
                    GuiComponent.blit(poseStack, x, y, 27, 247, 9, 9, textureWidth, textureHeight);
                }

                if (idx < thirstLevel) {
                    GuiComponent.blit(poseStack, x, y, 18, 247, 9, 9, textureWidth, textureHeight);
                }

                if (idx == thirstLevel) {
                    GuiComponent.blit(poseStack, x, y, 9, 247, 9, 9, textureWidth, textureHeight);
                }

            }

            RenderSystem.disableBlend();
            minecraft.getProfiler().pop();
        }
    };
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
            int healthLast = displayHealth;

            AttributeInstance attrMaxHealth = player.getAttribute(Attributes.MAX_HEALTH);
            float maxHealth = (float) attrMaxHealth.getValue();
            int absorb = Mth.ceil(player.getAbsorptionAmount());

            random.setSeed(gui.getGuiTicks() * 312871L);

            int left = screenWidth / 2 - 91;
            int top = screenHeight - gui.leftHeight + 3;
            gui.leftHeight += 7;

            int regen = -1;
            if (player.hasEffect(MobEffects.REGENERATION)) {
                regen = gui.getGuiTicks() % Mth.ceil(maxHealth + 5.0F);
            }

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

            GuiComponent.blit(poseStack, left, top, uOffset, highlight ? 6 : 0, 81, 6, textureWidth, textureHeight);

            int healthBarSize = Mth.ceil(health * 79 / maxHealth);

            GuiComponent.blit(poseStack, left + 1, top, uOffset + 1, vOffset, healthBarSize, 6, textureWidth, textureHeight);

            RenderSystem.disableBlend();
            gui.getMinecraft().getProfiler().pop();
        }
    };
}
