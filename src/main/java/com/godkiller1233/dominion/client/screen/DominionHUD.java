package com.godkiller1233.dominion.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.godkiller1233.dominion.DominionCore;
import com.godkiller1233.dominion.data.PlayerDominionData;

/**
 * HUD overlay showing player's Dominion status
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = DominionCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DominionHUD {
    private static PlayerDominionData cachedData;
    private static float glowAmount = 0;
    private static float glowDirection = 0.02f;
    
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (Minecraft.getInstance().player == null) return;
        
        Minecraft minecraft = Minecraft.getInstance();
        PoseStack poseStack = new PoseStack();
        
        // Update glow animation
        glowAmount += glowDirection;
        if (glowAmount >= 1.0f || glowAmount <= 0.0f) {
            glowDirection *= -1;
        }
    }
    
    public static void renderHUD(PoseStack poseStack, float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;
        
        int screenWidth = minecraft.getWindow().getWidth();
        int screenHeight = minecraft.getWindow().getHeight();
        int scaleFactor = minecraft.getWindow().getGuiScale();
        
        int x = 10;
        int y = 10;
        
        // Render HUD background
        drawBox(poseStack, x, y, 250, 120, 0xAA1a1a2e, 0xFFFF6B35);
        
        // Render bloodline info
        drawLine(poseStack, x + 10, y + 10, "§6Bloodline:", 0xFFFFFF);
        drawLine(poseStack, x + 10, y + 20, "  §eGenesis §7(Lvl 5)", 0xFFFFFF);
        
        // Render dominion info
        drawLine(poseStack, x + 10, y + 35, "§5Dominion:", 0xFFFFFF);
        drawLine(poseStack, x + 10, y + 45, "  §dFire §7(Power 8.5)", 0xFFFFFF);
        
        // Render status indicators
        drawLine(poseStack, x + 10, y + 60, "§e⚡ Divine Energy: 75/100", 0xFFFFFF);
        drawLine(poseStack, x + 10, y + 70, "§c❤ Faith: 150", 0xFFFFFF);
        
        // Draw progress bar
        drawProgressBar(poseStack, x + 10, y + 85, 230, 12, 0.75f, 0xFF4ECDC4);
    }
    
    private static void drawBox(PoseStack poseStack, int x, int y, int width, int height, int backgroundColor, int borderColor) {
        fill(poseStack, x, y, x + width, y + height, backgroundColor);
        
        // Border
        fill(poseStack, x, y, x + width, y + 2, borderColor);
        fill(poseStack, x, y + height - 2, x + width, y + height, borderColor);
        fill(poseStack, x, y, x + 2, y + height, borderColor);
        fill(poseStack, x + width - 2, y, x + width, y + height, borderColor);
    }
    
    private static void drawLine(PoseStack poseStack, int x, int y, String text, int color) {
        Minecraft.getInstance().font.draw(poseStack, Component.literal(text), x, y, color);
    }
    
    private static void drawProgressBar(PoseStack poseStack, int x, int y, int width, int height, float progress, int color) {
        // Background
        fill(poseStack, x, y, x + width, y + height, 0xFF333333);
        
        // Progress
        int progressWidth = (int) (width * progress);
        fill(poseStack, x, y, x + progressWidth, y + height, color);
        
        // Border
        fill(poseStack, x, y, x + width, y + 1, 0xFFFFFFFF);
        fill(poseStack, x, y + height - 1, x + width, y + height, 0xFFFFFFFF);
    }
    
    private static void fill(PoseStack poseStack, int x1, int y1, int x2, int y2, int color) {
        GuiComponent.fill(poseStack, x1, y1, x2, y2, color);
    }
}
