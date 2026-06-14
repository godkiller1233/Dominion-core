package com.godkiller1233.dominion.discord;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * Discord bot integration for server notifications and commands
 */
public class DiscordBotIntegration {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final HttpClient client = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();
    
    private String webhookUrl;
    private String botToken;
    private boolean isConnected = false;
    
    public DiscordBotIntegration(String webhookUrl, String botToken) {
        this.webhookUrl = webhookUrl;
        this.botToken = botToken;
        connect();
    }
    
    /**
     * Connect to Discord
     */
    public void connect() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(webhookUrl))
                .timeout(Duration.ofSeconds(5))
                .POST(HttpRequest.BodyPublishers.ofString("{\"content\":\"Bot connected\"}" ))
                .build();
            
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() == 204) {
                        isConnected = true;
                        LOGGER.info("Connected to Discord webhook");
                    }
                });
        } catch (Exception e) {
            LOGGER.warn("Failed to connect to Discord", e);
        }
    }
    
    /**
     * Send a message to Discord
     */
    public void sendMessage(String message) {
        if (!isConnected) return;
        
        CompletableFuture.runAsync(() -> {
            try {
                String jsonPayload = String.format("{\"content\":\"%s\"}", escapeJson(message));
                
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(webhookUrl))
                    .timeout(Duration.ofSeconds(5))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();
                
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                LOGGER.debug("Failed to send Discord message", e);
            }
        });
    }
    
    /**
     * Send an embed message to Discord
     */
    public void sendEmbed(String title, String description, int color) {
        if (!isConnected) return;
        
        CompletableFuture.runAsync(() -> {
            try {
                String jsonPayload = String.format(
                    "{\"embeds\":[{\"title\":\"%s\",\"description\":\"%s\",\"color\":%d}]}",
                    escapeJson(title),
                    escapeJson(description),
                    color
                );
                
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(webhookUrl))
                    .timeout(Duration.ofSeconds(5))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();
                
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                LOGGER.debug("Failed to send Discord embed", e);
            }
        });
    }
    
    /**
     * Notify when player becomes god
     */
    public void notifyBecomeGod(String playerName) {
        sendEmbed("👑 New God Ascended",
            playerName + " has ascended to godhood!",
            0xFF6B35);
    }
    
    /**
     * Notify when world event starts
     */
    public void notifyWorldEvent(String eventName) {
        sendEmbed("🌙 World Event Started",
            "The " + eventName + " has begun!",
            0xFF4444);
    }
    
    /**
     * Notify when faction is created
     */
    public void notifyFactionCreation(String factionName, String king) {
        sendEmbed("🏰 New Faction Founded",
            "King: " + king + "\nFaction: " + factionName,
            0x4ECDC4);
    }
    
    /**
     * Notify when religion is created
     */
    public void notifyReligionCreation(String religionName, String founder) {
        sendEmbed("⛪ New Religion Founded",
            "Founder: " + founder + "\nReligion: " + religionName,
            0x00d084);
    }
    
    /**
     * Send server status
     */
    public void sendServerStatus(int onlinePlayers, int totalPlayers, String serverName) {
        sendEmbed("📊 Server Status",
            "Server: " + serverName + "\nOnline: " + onlinePlayers + "/" + totalPlayers,
            0x0084ff);
    }
    
    private String escapeJson(String input) {
        return input
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r");
    }
    
    public boolean isConnected() {
        return isConnected;
    }
}
