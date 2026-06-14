package com.godkiller1233.dominion.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class DominionSelectionScreen extends Screen {
    private String[] dominions = {"Simple", "Beast", "Fire", "Water", "Time", "Divine", "Demonic", "Dark", "Light", "Unstable"};
    private int selectedIndex = -1;
    
    public DominionSelectionScreen() {
        super(Component.literal("Select Dominion"));
    }
    
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        
        int centerX = this.width / 2;
        int startY = 40;
        int lineHeight = 25;
        
        this.font.drawCentered(poseStack, Component.literal("§5§lSELECT DOMINION"), centerX, 20, 0xFFFFFF);
        
        for (int i = 0; i < dominions.length; i++) {
            int y = startY + i * lineHeight;
            if (y > this.height - 50) break;
            
            boolean hovered = mouseX > centerX - 100 && mouseX < centerX + 100 && mouseY > y && mouseY < y + 20;
            int color = i == selectedIndex ? 0xFFFF6B35 : (hovered ? 0xFF4ECDC4 : 0xFFAAAAAA);
            
            fill(poseStack, centerX - 100, y, centerX + 100, y + 20, 0xFF1a1a2e);
            GuiComponent.drawCenteredString(poseStack, this.font, dominions[i], centerX, y + 5, color);
        }
        
        if (selectedIndex >= 0) {
            int buttonX = centerX - 40;
            int buttonY = this.height - 40;
            fill(poseStack, buttonX, buttonY, buttonX + 80, buttonY + 20, 0xFF4ECDC4);
            GuiComponent.drawCenteredString(poseStack, this.font, "Equip", centerX, buttonY + 5, 0xFFFFFF);
        }
        
        super.render(poseStack, mouseX, mouseY, partialTick);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int centerX = this.width / 2;
        int startY = 40;
        int lineHeight = 25;
        
        for (int i = 0; i < dominions.length; i++) {
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
                this.minecraft.player.displayClientMessage(Component.literal("Equipped: " + dominions[selectedIndex]), true);
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
