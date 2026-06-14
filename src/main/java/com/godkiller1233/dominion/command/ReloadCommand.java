package com.godkiller1233.dominion.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import com.godkiller1233.dominion.DominionCore;

public class ReloadCommand {
    
    public static int execute(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        
        if (!source.hasPermission(4)) {
            source.sendFailure(Component.literal("You do not have permission to reload scripts"));
            return 0;
        }
        
        DominionCore.LOGGER.info("Reloading all DominionCore scripts...");
        DominionCore.scriptEngine.reloadAllScripts();
        source.sendSuccess(() -> Component.literal("§a[Dominion] All scripts reloaded successfully!"), true);
        
        return 1;
    }
}
