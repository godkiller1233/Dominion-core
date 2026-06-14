package com.godkiller1233.dominion.database;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * Handles synchronization with DominionCore web dashboard
 * Website allows server admins to manage content and player progression in real-time
 */
public class WebSyncManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String API_BASE = "https://dominion-core.godkiller1233.dev/api";
    private static final HttpClient client = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();
    
    private String serverToken;
    private boolean isConnected = false;
    private long lastSyncTime = 0;
    private static final long SYNC_INTERVAL = 300000; // 5 minutes
    
    public WebSyncManager(String token) {
        this.serverToken = token;
        connect();
    }
    
    /**
     * Connect to web server and authenticate
     */
    public void connect() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_BASE + "/auth/verify"))
                .header("Authorization", "Bearer " + serverToken)
                .GET()
                .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                isConnected = true;
                LOGGER.info("Successfully connected to DominionCore web server");
            } else {
                LOGGER.warn("Failed to connect to web server: " + response.statusCode());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to connect to web server", e);
        }
    }
    
    /**
     * Sync player data to web
     */
    public void syncPlayerData(String uuid, JsonObject data) {
        if (!isConnected || !shouldSync()) return;
        
        CompletableFuture.runAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_BASE + "/players/" + uuid))
                    .header("Authorization", "Bearer " + serverToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
                    .build();
                
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    LOGGER.debug("Synced player data to web: " + uuid);
                }
            } catch (Exception e) {
                LOGGER.debug("Failed to sync player data to web", e);
            }
        });
    }
    
    /**
     * Sync faction data to web
     */
    public void syncFactionData(String factionId, JsonObject data) {
        if (!isConnected || !shouldSync()) return;
        
        CompletableFuture.runAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_BASE + "/factions/" + factionId))
                    .header("Authorization", "Bearer " + serverToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
                    .build();
                
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                LOGGER.debug("Failed to sync faction data", e);
            }
        });
    }
    
    /**
     * Sync religion data to web
     */
    public void syncReligionData(String religionId, JsonObject data) {
        if (!isConnected || !shouldSync()) return;
        
        CompletableFuture.runAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_BASE + "/religions/" + religionId))
                    .header("Authorization", "Bearer " + serverToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
                    .build();
                
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                LOGGER.debug("Failed to sync religion data", e);
            }
        });
    }
    
    /**
     * Sync dimension data to web
     */
    public void syncDimensionData(String dimensionId, JsonObject data) {
        if (!isConnected || !shouldSync()) return;
        
        CompletableFuture.runAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_BASE + "/dimensions/" + dimensionId))
                    .header("Authorization", "Bearer " + serverToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
                    .build();
                
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                LOGGER.debug("Failed to sync dimension data", e);
            }
        });
    }
    
    /**
     * Pull all data from web server
     */
    public void pullAllData() {
        if (!isConnected) return;
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_BASE + "/sync/all"))
                .header("Authorization", "Bearer " + serverToken)
                .GET()
                .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                LOGGER.info("Successfully pulled all data from web server");
                // Parse and apply data
            }
        } catch (Exception e) {
            LOGGER.error("Failed to pull data from web", e);
        }
    }
    
    /**
     * Check if enough time has passed for sync
     */
    private boolean shouldSync() {
        long now = System.currentTimeMillis();
        if (now - lastSyncTime > SYNC_INTERVAL) {
            lastSyncTime = now;
            return true;
        }
        return false;
    }
    
    public boolean isConnected() {
        return isConnected;
    }
}
