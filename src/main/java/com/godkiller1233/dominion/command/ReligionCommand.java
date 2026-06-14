package com.godkiller1233.dominion.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import com.godkiller1233.dominion.data.DominionDataManager;
import com.godkiller1233.dominion.data.PlayerDominionData;

public class ReligionCommand {
    
    public static int execute(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerPlayer player = source.getPlayer();
        
        if (player == null) {
            source.sendFailure(Component.literal("This command can only be run by a player"));
            return 0;
        }
        
        PlayerDominionData data = DominionDataManager.getOrCreateData(player);
        String religion = data.getReligionId();
        int worshippers = data.getWorshippers();
        float faith = data.getFaith();
        
        if (religion == null || religion.isEmpty()) {
            source.sendSuccess(() -> Component.literal("§3[Dominion] No religion created. Use /dominion religion create <name>"), false);
        } else {
            source.sendSuccess(() -> Component.literal(
                "§b[Dominion] Religion: §3" + religion + 
                "\n§b  Worshippers: §3" + worshippers + 
                "\n§b  Faith: §3" + String.format("%.1f", faith) + "\n"), 
                false);
        }
        
        return 1;
    }
}
