package com.godkiller1233.dominion.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.TickEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.godkiller1233.dominion.DominionCore;
import com.godkiller1233.dominion.world.WorldEvent;

import java.util.Random;

/**
 * Handles world events like invasions, apocalypses, titan awakenings
 */
@Mod.EventBusSubscriber(modid = DominionCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random random = new Random();
    private static int eventCounter = 0;
    private static WorldEvent currentEvent = null;
    private static final int EVENT_INTERVAL = 24000; // 1 in-game day
    
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        
        MinecraftServer server = event.getServer();
        if (server == null) return;
        
        eventCounter++;
        
        // Check for random events
        if (eventCounter >= EVENT_INTERVAL) {
            eventCounter = 0;
            triggerRandomEvent(server);
        }
        
        // Update ongoing event
        if (currentEvent != null) {
            currentEvent.update(server);
            if (currentEvent.isComplete()) {
                LOGGER.info("World event completed: " + currentEvent.getEventName());
                currentEvent = null;
            }
        }
    }
    
    private static void triggerRandomEvent(MinecraftServer server) {
        int eventType = random.nextInt(100);
        
        if (eventType < 20) {
            currentEvent = new WorldEvent.DemonInvasion(server);
        } else if (eventType < 40) {
            currentEvent = new WorldEvent.TitanAwakening(server);
        } else if (eventType < 60) {
            currentEvent = new WorldEvent.DivineWar(server);
        } else if (eventType < 80) {
            currentEvent = new WorldEvent.BloodMoon(server);
        } else {
            currentEvent = new WorldEvent.RealityCollapse(server);
        }
        
        if (currentEvent != null) {
            currentEvent.start();
            LOGGER.info("World event started: " + currentEvent.getEventName());
            
            // Notify all players
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal(
                        "\n\u00a7c\u00a7l" + currentEvent.getEventName() + "!\n"),
                    false
                );
            }
        }
    }
    
    public static WorldEvent getCurrentEvent() {
        return currentEvent;
    }
}
