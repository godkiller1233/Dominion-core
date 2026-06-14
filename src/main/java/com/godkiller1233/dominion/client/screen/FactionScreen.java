package com.godkiller1233.dominion.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class FactionScreen extends Screen {
    private String[] factionRanks = {"Recruit", "Member", "Elite", "Recruiter", "General", "Advisor", "King"};
    
    public FactionScreen() {
        super(Component.literal("Faction Management"));
    }
    
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        
        int centerX = this.width / 2;
        
        this.font.drawCentered(poseStack, Component.literal("§4§lFACTION MANAGEMENT"), centerX, 20, 0xFFFFFF);
        
        // Draw faction buttons
        int startY = 60;
        String[] buttons = {"Create Faction", "Join Faction", "Faction Info", "Members", "Treasury", "Claims"};
        
        for (int i = 0; i < buttons.length; i++) {
            int buttonY = startY + i * 30;
            boolean hovered = mouseX > centerX - 80 && mouseX < centerX + 80 && mouseY > buttonY && mouseY < buttonY + 20;
            fill(poseStack, centerX - 80, buttonY, centerX + 80, buttonY + 20, hovered ? 0xFF4ECDC4 : 0xFF1a1a2e);
            GuiComponent.drawCenteredString(poseStack, this.font, buttons[i], centerX, buttonY + 5, 0xFFFFFF);
        }
        
        super.render(poseStack, mouseX, mouseY, partialTick);
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
