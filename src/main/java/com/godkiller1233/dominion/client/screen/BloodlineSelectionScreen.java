package com.godkiller1233.dominion.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class BloodlineSelectionScreen extends Screen {
    private int scrollOffset = 0;
    private String[] bloodlines = {"Genesis", "Null", "Dragon", "Phoenix", "Elemental Wolf", "Farmer", "Chaos Keeper", "Chrono Wolf"};
    private int selectedIndex = -1;
    
    public BloodlineSelectionScreen() {
        super(Component.literal("Select Bloodline"));
    }
    
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        
        int centerX = this.width / 2;
        int startY = 40;
        int lineHeight = 25;
        
        this.font.drawCentered(poseStack, Component.literal("§6§lSELECT BLOODLINE"), centerX, 20, 0xFFFFFF);
        
        // Draw bloodline list
        for (int i = 0; i < bloodlines.length; i++) {
            int y = startY + i * lineHeight;
            if (y > this.height - 50) break;
            
            boolean hovered = mouseX > centerX - 100 && mouseX < centerX + 100 && mouseY > y && mouseY < y + 20;
            int color = i == selectedIndex ? 0xFFFF6B35 : (hovered ? 0xFF4ECDC4 : 0xFFAAAAAA);
            
            fill(poseStack, centerX - 100, y, centerX + 100, y + 20, 0xFF1a1a2e);
            GuiComponent.drawCenteredString(poseStack, this.font, bloodlines[i], centerX, y + 5, color);
        }
        
        // Draw confirm button
        if (selectedIndex >= 0) {
            int buttonX = centerX - 40;
            int buttonY = this.height - 40;
            fill(poseStack, buttonX, buttonY, buttonX + 80, buttonY + 20, 0xFF4ECDC4);
            GuiComponent.drawCenteredString(poseStack, this.font, "Confirm", centerX, buttonY + 5, 0xFFFFFF);
        }
        
        super.render(poseStack, mouseX, mouseY, partialTick);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int centerX = this.width / 2;
        int startY = 40;
        int lineHeight = 25;
        
        for (int i = 0; i < bloodlines.length; i++) {
            int y = startY + i * lineHeight;
            if (mouseX > centerX - 100 && mouseX < centerX + 100 && mouseY > y && mouseY < y + 20) {
                selectedIndex = i;
                return true;
            }
        }
        
        if (selectedIndex >= 0) {
            int buttonX = centerX - 40;
            int buttonY = this.height - 40;
            if (mouseX > buttonX && mouseX < buttonX + 80 && mouseY > buttonY && mouseY < buttonY + 20) {
                // Send command to server
                this.minecraft.player.displayClientMessage(Component.literal("Selected: " + bloodlines[selectedIndex]), true);
                this.onClose();
            }
        }
        
        return super.mouseClicked(mouseX, mouseY, button);
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
