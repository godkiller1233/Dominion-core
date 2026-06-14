package com.godkiller1233.dominion.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLServerStartingEvent;
import net.minecraft.commands.Commands;

import com.godkiller1233.dominion.DominionCore;
import com.godkiller1233.dominion.command.*;

@Mod.EventBusSubscriber(modid = DominionCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {
    
    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event) {
        DominionCore.LOGGER.info("Server starting - registering DominionCore commands");
        
        // Register all commands
        event.getServer().getCommands().getDispatcher().register(
            Commands.literal("dominion")
                .then(Commands.literal("bloodline").executes(BloodlineCommand::execute))
                .then(Commands.literal("dominion").executes(DominionCommand::execute))
                .then(Commands.literal("religion").executes(ReligionCommand::execute))
                .then(Commands.literal("faction").executes(FactionCommand::execute))
                .then(Commands.literal("reload").executes(ReloadCommand::execute))
                .then(Commands.literal("info").executes(InfoCommand::execute))
        );
    }
}
