package com.godkiller1233.dominion.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import com.godkiller1233.dominion.data.PlayerDominionData;

public class StatusScreen extends Screen {
    private PlayerDominionData playerData;
    
    public StatusScreen() {
        super(Component.literal("Status Screen"));
    }
    
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        
        int centerX = this.width / 2;
        int startY = 20;
        
        this.font.drawCentered(poseStack, Component.literal("§e§lPROGRESSION STATUS"), centerX, startY, 0xFFFFFF);
        
        if (this.minecraft.player != null) {
            String playerName = this.minecraft.player.getName().getString();
            this.font.draw(poseStack, Component.literal("§7Player: §r" + playerName), centerX - 100, startY + 30, 0xFFFFFF);
            
            // Draw progression path
            drawProgressionPath(poseStack, centerX, startY + 60);
        }
        
        super.render(poseStack, mouseX, mouseY, partialTick);
    }
    
    private void drawProgressionPath(PoseStack poseStack, int centerX, int y) {
        String[] path = {"Mortal", "→", "Bloodline Holder", "→", "Dominion Wielder", "→", "Religious Figure", "→", "Saint/Champion", "→", "God", "→", "Titan", "→", "Primordial", "→", "Living Concept"};
        
        int x = centerX - 150;
        for (String step : path) {
            this.font.draw(poseStack, Component.literal(step), x, y, 0xFF4ECDC4);
            x += this.font.width(step) + 5;
            if (x > this.width - 50) {
                y += 15;
                x = centerX - 150;
            }
        }
    }
    
    @Override
    public void onClose() {
        this.minecraft.setScreen(new DominionMenuScreen());
    }
    
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
