package com.godkiller1233.dominion.achievement;

import java.util.*;
import com.google.gson.JsonObject;

/**
 * Tracks player achievements and quests
 */
public class AchievementTracker {
    private String playerUUID;
    private Set<String> unlockedAchievements = new HashSet<>();
    private Map<String, Integer> questProgress = new HashMap<>();
    private int totalPoints = 0;
    
    public AchievementTracker(String playerUUID) {
        this.playerUUID = playerUUID;
    }
    
    public void unlockAchievement(String achievementId) {
        if (!unlockedAchievements.contains(achievementId)) {
            unlockedAchievements.add(achievementId);
            Achievement ach = Achievement.ACHIEVEMENTS.get(achievementId);
            if (ach != null) {
                totalPoints += ach.getRewardPoints();
            }
        }
    }
    
    public boolean hasAchievement(String achievementId) {
        return unlockedAchievements.contains(achievementId);
    }
    
    public void updateQuestProgress(String questId, int progress) {
        questProgress.put(questId, progress);
    }
    
    public int getQuestProgress(String questId) {
        return questProgress.getOrDefault(questId, 0);
    }
    
    public Set<String> getUnlockedAchievements() {
        return new HashSet<>(unlockedAchievements);
    }
    
    public int getTotalPoints() {
        return totalPoints;
    }
    
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("player_uuid", playerUUID);
        obj.addProperty("total_points", totalPoints);
        
        JsonObject achievements = new JsonObject();
        for (String ach : unlockedAchievements) {
            achievements.addProperty(ach, true);
        }
        obj.add("achievements", achievements);
        
        return obj;
    }
}
