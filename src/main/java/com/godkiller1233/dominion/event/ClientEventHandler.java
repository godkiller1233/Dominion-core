package com.godkiller1233.dominion.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLServerStartingEvent;

import com.godkiller1233.dominion.DominionCore;
import com.godkiller1233.dominion.client.screen.BloodlineSelectionScreen;
import com.godkiller1233.dominion.client.screen.DominionHUD;

@Mod.EventBusSubscriber(modid = DominionCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
    
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        DominionCore.LOGGER.info("Client setup for DominionCore");
    }
}
