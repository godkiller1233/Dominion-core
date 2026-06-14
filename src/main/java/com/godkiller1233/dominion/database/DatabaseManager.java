package com.godkiller1233.dominion.database;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DatabaseManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DB_DIR = "dominion/database";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    private Map<String, JsonObject> cache = new HashMap<>();
    private WebSyncManager webSync;
    
    public DatabaseManager(WebSyncManager webSync) {
        this.webSync = webSync;
        initializeDatabase();
    }
    
    private void initializeDatabase() {
        try {
            Files.createDirectories(Paths.get(DB_DIR));
            LOGGER.info("Database initialized at " + DB_DIR);
        } catch (Exception e) {
            LOGGER.error("Failed to initialize database", e);
        }
    }
    
    /**
     * Save player data to local database
     */
    public void savePlayerData(String uuid, JsonObject data) {
        try {
            File playerFile = new File(DB_DIR + "/players/" + uuid + ".json");
            playerFile.getParentFile().mkdirs();
            
            FileWriter writer = new FileWriter(playerFile);
            gson.toJson(data, writer);
            writer.close();
            
            cache.put(uuid, data);
            
            // Sync to web server
            webSync.syncPlayerData(uuid, data);
            
            LOGGER.info("Saved player data: " + uuid);
        } catch (Exception e) {
            LOGGER.error("Failed to save player data", e);
        }
    }
    
    /**
     * Load player data from local database
     */
    public JsonObject loadPlayerData(String uuid) {
        try {
            // Check cache first
            if (cache.containsKey(uuid)) {
                return cache.get(uuid);
            }
            
            File playerFile = new File(DB_DIR + "/players/" + uuid + ".json");
            if (!playerFile.exists()) {
                return createNewPlayerData(uuid);
            }
            
            FileReader reader = new FileReader(playerFile);
            JsonObject data = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            
            cache.put(uuid, data);
            return data;
        } catch (Exception e) {
            LOGGER.error("Failed to load player data", e);
            return createNewPlayerData(uuid);
        }
    }
    
    /**
     * Save faction data
     */
    public void saveFactionData(String factionId, JsonObject data) {
        try {
            File factionFile = new File(DB_DIR + "/factions/" + factionId + ".json");
            factionFile.getParentFile().mkdirs();
            
            FileWriter writer = new FileWriter(factionFile);
            gson.toJson(data, writer);
            writer.close();
            
            // Sync to web
            webSync.syncFactionData(factionId, data);
            LOGGER.info("Saved faction data: " + factionId);
        } catch (Exception e) {
            LOGGER.error("Failed to save faction data", e);
        }
    }
    
    /**
     * Load faction data
     */
    public JsonObject loadFactionData(String factionId) {
        try {
            File factionFile = new File(DB_DIR + "/factions/" + factionId + ".json");
            if (!factionFile.exists()) {
                return new JsonObject();
            }
            
            FileReader reader = new FileReader(factionFile);
            JsonObject data = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            return data;
        } catch (Exception e) {
            LOGGER.error("Failed to load faction data", e);
            return new JsonObject();
        }
    }
    
    /**
     * Save religion data
     */
    public void saveReligionData(String religionId, JsonObject data) {
        try {
            File religionFile = new File(DB_DIR + "/religions/" + religionId + ".json");
            religionFile.getParentFile().mkdirs();
            
            FileWriter writer = new FileWriter(religionFile);
            gson.toJson(data, writer);
            writer.close();
            
            webSync.syncReligionData(religionId, data);
            LOGGER.info("Saved religion data: " + religionId);
        } catch (Exception e) {
            LOGGER.error("Failed to save religion data", e);
        }
    }
    
    /**
     * Load religion data
     */
    public JsonObject loadReligionData(String religionId) {
        try {
            File religionFile = new File(DB_DIR + "/religions/" + religionId + ".json");
            if (!religionFile.exists()) {
                return new JsonObject();
            }
            
            FileReader reader = new FileReader(religionFile);
            JsonObject data = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            return data;
        } catch (Exception e) {
            LOGGER.error("Failed to load religion data", e);
            return new JsonObject();
        }
    }
    
    /**
     * Save dimension/realm data
     */
    public void saveDimensionData(String dimensionId, JsonObject data) {
        try {
            File dimFile = new File(DB_DIR + "/dimensions/" + dimensionId + ".json");
            dimFile.getParentFile().mkdirs();
            
            FileWriter writer = new FileWriter(dimFile);
            gson.toJson(data, writer);
            writer.close();
            
            webSync.syncDimensionData(dimensionId, data);
        } catch (Exception e) {
            LOGGER.error("Failed to save dimension data", e);
        }
    }
    
    public JsonObject loadDimensionData(String dimensionId) {
        try {
            File dimFile = new File(DB_DIR + "/dimensions/" + dimensionId + ".json");
            if (!dimFile.exists()) {
                return new JsonObject();
            }
            
            FileReader reader = new FileReader(dimFile);
            JsonObject data = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            return data;
        } catch (Exception e) {
            LOGGER.error("Failed to load dimension data", e);
            return new JsonObject();
        }
    }
    
    /**
     * Sync all data from web server (admin function)
     */
    public void syncFromWeb() {
        LOGGER.info("Syncing data from web server...");
        webSync.pullAllData();
    }
    
    private JsonObject createNewPlayerData(String uuid) {
        JsonObject obj = new JsonObject();
        obj.addProperty("uuid", uuid);
        obj.addProperty("bloodline", "");
        obj.addProperty("bloodline_level", 1.0);
        obj.addProperty("active_dominion", "");
        obj.addProperty("dominion_power", 1.0);
        obj.addProperty("divine_energy", 0.0);
        obj.addProperty("faith", 0.0);
        obj.addProperty("worshippers", 0);
        obj.addProperty("devil_status", "mortal");
        obj.addProperty("souls", 0);
        obj.addProperty("is_god", false);
        obj.addProperty("is_titan", false);
        obj.addProperty("created_at", System.currentTimeMillis());
        
        cache.put(uuid, obj);
        return obj;
    }
}
