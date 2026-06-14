package com.godkiller1233.dominion;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafxmod.FXModLauncher;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.godkiller1233.dominion.core.DominionScriptEngine;
import com.godkiller1233.dominion.registry.DominionRegistries;

@Mod(DominionCore.MOD_ID)
public class DominionCore {
    public static final String MOD_ID = "dominion";
    public static final Logger LOGGER = LogManager.getLogger();
    
    public static DominionScriptEngine scriptEngine;
    public static DominionRegistries registries;
    
    public DominionCore() {
        IEventBus modEventBus = net.minecraftforge.fml.ModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        
        ModLoadingContext.getInstance().registerConfig(ModConfig.Type.COMMON, DominionConfig.SPEC);
        
        // Initialize script engine and registries
        scriptEngine = new DominionScriptEngine();
        registries = new DominionRegistries();
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("DominionCore is loading...");
        scriptEngine.initialize();
        registries.registerAll();
    }
    
    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("DominionCore client setup");
    }
}
