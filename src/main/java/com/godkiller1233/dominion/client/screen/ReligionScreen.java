package com.godkiller1233.dominion.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ReligionScreen extends Screen {
    private String religionName = "";
    private int textCursorCounter = 0;
    private boolean creatingNew = false;
    
    public ReligionScreen() {
        super(Component.literal("Religion Management"));
    }
    
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        
        int centerX = this.width / 2;
        
        this.font.drawCentered(poseStack, Component.literal("§3§lRELIGION MANAGEMENT"), centerX, 20, 0xFFFFFF);
        
        // Draw create religion button
        int buttonY = 60;
        boolean hovered = mouseX > centerX - 80 && mouseX < centerX + 80 && mouseY > buttonY && mouseY < buttonY + 20;
        fill(poseStack, centerX - 80, buttonY, centerX + 80, buttonY + 20, hovered ? 0xFF4ECDC4 : 0xFF1a1a2e);
        GuiComponent.drawCenteredString(poseStack, this.font, "Create Religion", centerX, buttonY + 5, 0xFFFFFF);
        
        // Draw join religion button
        buttonY += 30;
        hovered = mouseX > centerX - 80 && mouseX < centerX + 80 && mouseY > buttonY && mouseY < buttonY + 20;
        fill(poseStack, centerX - 80, buttonY, centerX + 80, buttonY + 20, hovered ? 0xFF4ECDC4 : 0xFF1a1a2e);
        GuiComponent.drawCenteredString(poseStack, this.font, "Join Religion", centerX, buttonY + 5, 0xFFFFFF);
        
        super.render(poseStack, mouseX, mouseY, partialTick);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int centerX = this.width / 2;
        
        int buttonY = 60;
        if (mouseX > centerX - 80 && mouseX < centerX + 80 && mouseY > buttonY && mouseY < buttonY + 20) {
            creatingNew = true;
            return true;
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
