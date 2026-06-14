package com.godkiller1233.dominion.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import com.godkiller1233.dominion.data.DominionDataManager;
import com.godkiller1233.dominion.data.PlayerDominionData;

public class DominionCommand {
    
    public static int execute(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerPlayer player = source.getPlayer();
        
        if (player == null) {
            source.sendFailure(Component.literal("This command can only be run by a player"));
            return 0;
        }
        
        PlayerDominionData data = DominionDataManager.getOrCreateData(player);
        String dominion = data.getActiveDominion();
        float power = data.getDominionPower();
        
        if (dominion == null || dominion.isEmpty()) {
            source.sendSuccess(() -> Component.literal("§c[Dominion] No dominion equipped. Use /dominion dominion equip <name>"), false);
        } else {
            source.sendSuccess(() -> Component.literal("§5[Dominion] Active Dominion: §d" + dominion + " §5(Power: §d" + power + "§5)"), false);
        }
        
        return 1;
    }
}
