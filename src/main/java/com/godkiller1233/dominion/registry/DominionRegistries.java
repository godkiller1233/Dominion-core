package com.godkiller1233.dominion.registry;

import java.util.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Central registry for all DominionCore content
 */
public class DominionRegistries {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private BloodlineRegistry bloodlineRegistry = new BloodlineRegistry();
    private DominionRegistry dominionRegistry = new DominionRegistry();
    private ReligionRegistry religionRegistry = new ReligionRegistry();
    private ItemRegistry itemRegistry = new ItemRegistry();
    private FactorRegistry factionRegistry = new FactorRegistry();
    
    public void registerAll() {
        LOGGER.info("Registering DominionCore content...");
        registerCoreBloodlines();
        registerCoreDominions();
    }
    
    private void registerCoreBloodlines() {
        bloodlineRegistry.register("genesis", "Creation");
        bloodlineRegistry.register("null", "Nothingness");
        bloodlineRegistry.register("dragon", "Ancient Dragon");
        bloodlineRegistry.register("elemental_wolf", "Elemental Wolf");
        bloodlineRegistry.register("farmer", "Agriculture");
        LOGGER.info("Registered core bloodlines");
    }
    
    private void registerCoreDominions() {
        dominionRegistry.register("simple", "Basic Dominion");
        dominionRegistry.register("beast", "Beast Dominion");
        dominionRegistry.register("fire", "Fire Dominion");
        dominionRegistry.register("water", "Water Dominion");
        dominionRegistry.register("time", "Time Dominion");
        LOGGER.info("Registered core dominions");
    }
    
    public BloodlineRegistry getBloodlineRegistry() {
        return bloodlineRegistry;
    }
    
    public DominionRegistry getDominionRegistry() {
        return dominionRegistry;
    }
    
    public ReligionRegistry getReligionRegistry() {
        return religionRegistry;
    }
    
    public ItemRegistry getItemRegistry() {
        return itemRegistry;
    }
    
    public FactorRegistry getFactionRegistry() {
        return factionRegistry;
    }
    
    public static class BloodlineRegistry {
        private Map<String, String> bloodlines = new HashMap<>();
        
        public void register(String id, String displayName) {
            bloodlines.put(id, displayName);
        }
        
        public String get(String id) {
            return bloodlines.get(id);
        }
        
        public Collection<String> getAll() {
            return bloodlines.keySet();
        }
    }
    
    public static class DominionRegistry {
        private Map<String, String> dominions = new HashMap<>();
        
        public void register(String id, String displayName) {
            dominions.put(id, displayName);
        }
        
        public String get(String id) {
            return dominions.get(id);
        }
        
        public Collection<String> getAll() {
            return dominions.keySet();
        }
    }
    
    public static class ReligionRegistry {
        private Map<String, Object> religions = new HashMap<>();
        
        public void register(String id, Object religion) {
            religions.put(id, religion);
        }
        
        public Object get(String id) {
            return religions.get(id);
        }
    }
    
    public static class ItemRegistry {
        private Map<String, Object> items = new HashMap<>();
        
        public void register(String id, Object item) {
            items.put(id, item);
        }
        
        public Object get(String id) {
            return items.get(id);
        }
    }
    
    public static class FactorRegistry {
        private Map<String, Object> factions = new HashMap<>();
        
        public void register(String id, Object faction) {
            factions.put(id, faction);
        }
        
        public Object get(String id) {
            return factions.get(id);
        }
    }
}
