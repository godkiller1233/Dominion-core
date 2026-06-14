package com.godkiller1233.dominion.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import com.godkiller1233.dominion.data.DominionDataManager;
import com.godkiller1233.dominion.data.PlayerDominionData;

public class FactionCommand {
    
    public static int execute(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerPlayer player = source.getPlayer();
        
        if (player == null) {
            source.sendFailure(Component.literal("This command can only be run by a player"));
            return 0;
        }
        
        PlayerDominionData data = DominionDataManager.getOrCreateData(player);
        String faction = data.getFactionId();
        String rank = data.getFactionRank();
        
        if (faction == null || faction.isEmpty()) {
            source.sendSuccess(() -> Component.literal("§4[Dominion] No faction joined. Use /dominion faction join <name>"), false);
        } else {
            source.sendSuccess(() -> Component.literal("§c[Dominion] Faction: §4" + faction + " §c(Rank: §4" + rank + "§c)"), false);
        }
        
        return 1;
    }
}
