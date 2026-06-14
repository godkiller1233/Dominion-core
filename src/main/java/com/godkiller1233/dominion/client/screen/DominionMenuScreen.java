package com.godkiller1233.dominion.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

import com.godkiller1233.dominion.DominionCore;

import java.util.ArrayList;
import java.util.List;

public class DominionMenuScreen extends Screen {
    private int centerX, centerY;
    private float rotation = 0;
    private List<MenuOption> options = new ArrayList<>();
    private int selectedOption = 0;
    
    public DominionMenuScreen() {
        super(Component.literal("DominionCore Menu"));
        setupOptions();
    }
    
    private void setupOptions() {
        options.add(new MenuOption("Bloodline", "§6Select or upgrade your bloodline", 0));
        options.add(new MenuOption("Dominion", "§5Equip and manage dominions", 1));
        options.add(new MenuOption("Religion", "§3Create or manage religions", 2));
        options.add(new MenuOption("Faction", "§4Join or manage a faction", 3));
        options.add(new MenuOption("Status", "§eView your progression status", 4));
        options.add(new MenuOption("Close", "§7Close this menu", 5));
    }
    
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        rotation += 0.5f;
        this.renderBackground(poseStack);
        
        centerX = this.width / 2;
        centerY = this.height / 2;
        
        // Draw central circle
        fill(poseStack, centerX - 50, centerY - 50, centerX + 50, centerY + 50, 0xFF1a1a2e);
        drawBorder(poseStack, centerX - 50, centerY - 50, centerX + 50, centerY + 50, 0xFFFF6B35, 2);
        
        // Draw menu options in circle
        float radius = 150;
        for (int i = 0; i < options.size(); i++) {
            MenuOption option = options.get(i);
            float angle = (float) (Math.PI * 2 * i / options.size() + Math.toRadians(rotation));
            int optionX = (int) (centerX + Math.cos(angle) * radius);
            int optionY = (int) (centerY + Math.sin(angle) * radius);
            
            boolean hovered = option.isHovered(mouseX, mouseY, optionX, optionY);
            drawOption(poseStack, optionX, optionY, option, hovered);
        }
        
        // Draw center text
        this.font.drawCentered(poseStack, Component.literal("§6§lDOMINION"), centerX, centerY - 10, 0xFFFFFF);
        this.font.drawCentered(poseStack, Component.literal("§b§lCORE"), centerX, centerY + 5, 0xFFFFFF);
        
        // Draw selected option description
        if (selectedOption >= 0 && selectedOption < options.size()) {
            String description = options.get(selectedOption).description;
            this.font.drawCentered(poseStack, Component.literal(description), centerX, this.height - 30, 0xFFFFFF);
        }
        
        super.render(poseStack, mouseX, mouseY, partialTick);
    }
    
    private void drawOption(PoseStack poseStack, int x, int y, MenuOption option, boolean hovered) {
        int color = hovered ? 0xFFFF6B35 : 0xFF4ECDC4;
        fill(poseStack, x - 30, y - 15, x + 30, y + 15, 0xFF1a1a2e);
        drawBorder(poseStack, x - 30, y - 15, x + 30, y + 15, color, 1);
        
        if (hovered) {
            fill(poseStack, x - 30, y - 15, x + 30, y + 15, 0x44FF6B35);
        }
        
        this.font.drawCentered(poseStack, Component.literal(option.label), x, y - 3, 0xFFFFFF);
    }
    
    private void drawBorder(PoseStack poseStack, int x1, int y1, int x2, int y2, int color, int thickness) {
        fill(poseStack, x1, y1, x2, y1 + thickness, color);
        fill(poseStack, x1, y2 - thickness, x2, y2, color);
        fill(poseStack, x1, y1, x1 + thickness, y2, color);
        fill(poseStack, x2 - thickness, y1, x2, y2, color);
    }
    
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        selectedOption = (int) ((selectedOption + delta) % options.size());
        return true;
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            float radius = 150;
            for (int i = 0; i < options.size(); i++) {
                float angle = (float) (Math.PI * 2 * i / options.size() + Math.toRadians(rotation));
                int optionX = (int) (centerX + Math.cos(angle) * radius);
                int optionY = (int) (centerY + Math.sin(angle) * radius);
                
                if (options.get(i).isHovered((int)mouseX, (int)mouseY, optionX, optionY)) {
                    handleOptionClick(i);
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    private void handleOptionClick(int index) {
        switch (index) {
            case 0:
                this.minecraft.setScreen(new BloodlineSelectionScreen());
                break;
            case 1:
                this.minecraft.setScreen(new DominionSelectionScreen());
                break;
            case 2:
                this.minecraft.setScreen(new ReligionScreen());
                break;
            case 3:
                this.minecraft.setScreen(new FactionScreen());
                break;
            case 4:
                this.minecraft.setScreen(new StatusScreen());
                break;
            case 5:
                this.onClose();
                break;
        }
    }
    
    @Override
    public void onClose() {
        this.minecraft.setScreen(null);
    }
    
    @Override
    public boolean isPauseScreen() {
        return false;
    }
    
    static class MenuOption {
        String label;
        String description;
        int index;
        
        MenuOption(String label, String description, int index) {
            this.label = label;
            this.description = description;
            this.index = index;
        }
        
        boolean isHovered(int mouseX, int mouseY, int x, int y) {
            return mouseX >= x - 30 && mouseX <= x + 30 && mouseY >= y - 15 && mouseY <= y + 15;
        }
    }
}
