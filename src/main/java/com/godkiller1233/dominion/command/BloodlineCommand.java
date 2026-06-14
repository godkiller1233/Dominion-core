package com.godkiller1233.dominion.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import com.godkiller1233.dominion.data.DominionDataManager;
import com.godkiller1233.dominion.data.PlayerDominionData;

public class BloodlineCommand {
    
    public static int execute(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerPlayer player = source.getPlayer();
        
        if (player == null) {
            source.sendFailure(Component.literal("This command can only be run by a player"));
            return 0;
        }
        
        PlayerDominionData data = DominionDataManager.getOrCreateData(player);
        String bloodline = data.getBloodlineId();
        float level = data.getBloodlineLevel();
        
        if (bloodline == null || bloodline.isEmpty()) {
            source.sendSuccess(() -> Component.literal("§c[Dominion] No bloodline selected. Use /dominion bloodline select <name>"), false);
        } else {
            source.sendSuccess(() -> Component.literal("§6[Dominion] Current Bloodline: §e" + bloodline + " §6(Level: §e" + level + "§6)"), false);
        }
        
        return 1;
    }
}
