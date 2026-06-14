package com.godkiller1233.dominion.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import com.godkiller1233.dominion.data.DominionDataManager;
import com.godkiller1233.dominion.data.PlayerDominionData;

public class InfoCommand {
    
    public static int execute(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerPlayer player = source.getPlayer();
        
        if (player == null) {
            source.sendFailure(Component.literal("This command can only be run by a player"));
            return 0;
        }
        
        PlayerDominionData data = DominionDataManager.getOrCreateData(player);
        
        StringBuilder info = new StringBuilder();
        info.append("\n§6=== DOMINION STATUS ===");
        info.append("\n§eBloodline: §r").append(data.getBloodlineId() != null ? data.getBloodlineId() : "None");
        info.append(" §8(Level ").append(String.format("%.1f", data.getBloodlineLevel())).append(")\n");
        info.append("§eDominion: §r").append(data.getActiveDominion() != null ? data.getActiveDominion() : "None");
        info.append(" §8(Power ").append(String.format("%.1f", data.getDominionPower())).append(")\n");
        info.append("§eReligion: §r").append(data.getReligionId() != null ? data.getReligionId() : "None");
        info.append(" §8(").append(data.getWorshippers()).append(" worshippers)\n");
        info.append("§eFaction: §r").append(data.getFactionId() != null ? data.getFactionId() : "None");
        info.append(" §8(").append(data.getFactionRank()).append(")\n");
        info.append("§eDivine Energy: §r").append(String.format("%.1f", data.getDivineEnergy())).append("/100\n");
        
        if (data.isGod()) {
            info.append("§c✦ GOD §c✦\n");
        }
        if (data.isTitan()) {
            info.append("§4⚔ TITAN ⚔\n");
        }
        if (data.isPrimordial()) {
            info.append("§5⚡ PRIMORDIAL ⚡\n");
        }
        
        info.append("§6======================");
        
        source.sendSuccess(() -> Component.literal(info.toString()), false);
        return 1;
    }
}
