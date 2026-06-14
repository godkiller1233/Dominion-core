package com.godkiller1233.dominion.faction;

import java.util.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public class Faction {
    private String factionId;
    private String name;
    private String king;
    private List<FactionMember> members = new ArrayList<>();
    private double treasury = 0;
    private List<String> allies = new ArrayList<>();
    private List<String> enemies = new ArrayList<>();
    private Set<String> claimedChunks = new HashSet<>();
    private String mainCapital;
    private List<String> cities = new ArrayList<>();
    
    public Faction(String factionId, String name, String king) {
        this.factionId = factionId;
        this.name = name;
        this.king = king;
        addMember(new FactionMember(king, "King"));
    }
    
    public void addMember(FactionMember member) {
        if (members.size() < 100) { // Max members
            members.add(member);
        }
    }
    
    public void removeMember(String uuid) {
        members.removeIf(m -> m.uuid.equals(uuid));
    }
    
    public void addTreasury(double amount) {
        treasury += amount;
    }
    
    public boolean withdrawTreasury(double amount) {
        if (treasury >= amount) {
            treasury -= amount;
            return true;
        }
        return false;
    }
    
    public void claimChunk(String chunkPos) {
        claimedChunks.add(chunkPos);
    }
    
    public void unclaimChunk(String chunkPos) {
        claimedChunks.remove(chunkPos);
    }
    
    public void addAlly(String factionId) {
        if (!allies.contains(factionId)) {
            allies.add(factionId);
        }
    }
    
    public void removeAlly(String factionId) {
        allies.remove(factionId);
    }
    
    public void addEnemy(String factionId) {
        if (!enemies.contains(factionId)) {
            enemies.add(factionId);
            removeAlly(factionId);
        }
    }
    
    public void removeEnemy(String factionId) {
        enemies.remove(factionId);
    }
    
    public boolean isAtWar() {
        return !enemies.isEmpty();
    }
    
    // Getters
    public String getFactionId() { return factionId; }
    public String getName() { return name; }
    public String getKing() { return king; }
    public List<FactionMember> getMembers() { return members; }
    public double getTreasury() { return treasury; }
    public List<String> getAllies() { return allies; }
    public List<String> getEnemies() { return enemies; }
    public Set<String> getClaimedChunks() { return claimedChunks; }
    public String getMainCapital() { return mainCapital; }
    public void setMainCapital(String capital) { this.mainCapital = capital; }
    public List<String> getCities() { return cities; }
    
    public static class FactionMember {
        public String uuid;
        public String name;
        public String rank;
        public long joinedDate;
        public int level;
        
        public FactionMember(String uuid, String rank) {
            this.uuid = uuid;
            this.rank = rank;
            this.joinedDate = System.currentTimeMillis();
            this.level = 1;
        }
    }
}
