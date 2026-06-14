package com.godkiller1233.dominion.data;

import java.util.*;

public class PlayerDominionData {
    private String uuid;
    private String bloodlineId;
    private float bloodlineLevel = 1.0f;
    private List<String> bloodlineAbilities = new ArrayList<>();
    private String activeDominion;
    private float dominionPower = 1.0f;
    private String religionId;
    private String factionId;
    private String factionRank = "recruit";
    private float divineEnergy = 0.0f;
    private float faith = 0.0f;
    private int worshippers = 0;
    private Map<String, Float> abilityResistance = new HashMap<>();
    private Map<String, Long> abilityCooldowns = new HashMap<>();
    private String devilStatus = "mortal";
    private int soulCount = 0;
    private boolean isGod = false;
    private boolean isTitan = false;
    private boolean isPrimordial = false;
    private boolean isLivingConcept = false;
    
    public PlayerDominionData(String uuid) {
        this.uuid = uuid;
    }
    
    // Bloodline Methods
    public String getBloodlineId() { return bloodlineId; }
    public void setBloodlineId(String id) { this.bloodlineId = id; }
    
    public float getBloodlineLevel() { return bloodlineLevel; }
    public void setBloodlineLevel(float level) { this.bloodlineLevel = level; }
    public void addBloodlineLevel(float amount) { this.bloodlineLevel += amount; }
    
    public void addBloodlineAbility(String ability) {
        if (!bloodlineAbilities.contains(ability)) {
            bloodlineAbilities.add(ability);
        }
    }
    public List<String> getBloodlineAbilities() { return bloodlineAbilities; }
    
    // Dominion Methods
    public String getActiveDominion() { return activeDominion; }
    public void setActiveDominion(String dominion) { this.activeDominion = dominion; }
    
    public float getDominionPower() { return dominionPower; }
    public void setDominionPower(float power) { this.dominionPower = power; }
    
    // Religion Methods
    public String getReligionId() { return religionId; }
    public void setReligionId(String id) { this.religionId = id; }
    
    public float getDivineEnergy() { return divineEnergy; }
    public void setDivineEnergy(float energy) { this.divineEnergy = energy; }
    public void addDivineEnergy(float amount) { this.divineEnergy = Math.min(100, divineEnergy + amount); }
    
    public float getFaith() { return faith; }
    public void setFaith(float faith) { this.faith = faith; }
    public void addFaith(float amount) { this.faith += amount; }
    
    public int getWorshippers() { return worshippers; }
    public void setWorshippers(int count) { this.worshippers = count; }
    public void addWorshipper() { this.worshippers++; }
    
    public boolean canBecomeGod() { return worshippers >= 20; }
    public void becomeGod() { this.isGod = true; }
    public boolean isGod() { return isGod; }
    
    // Faction Methods
    public String getFactionId() { return factionId; }
    public void setFactionId(String id) { this.factionId = id; }
    
    public String getFactionRank() { return factionRank; }
    public void setFactionRank(String rank) { this.factionRank = rank; }
    
    // Devil Methods
    public String getDevilStatus() { return devilStatus; }
    public void setDevilStatus(String status) { this.devilStatus = status; }
    
    public int getSoulCount() { return soulCount; }
    public void setSoulCount(int count) { this.soulCount = count; }
    public void addSoul() { this.soulCount++; }
    public void loseSoul() { this.soulCount = Math.max(0, soulCount - 1); }
    
    // Progression
    public boolean isTitan() { return isTitan; }
    public void becomeTitan() { this.isTitan = true; }
    
    public boolean isPrimordial() { return isPrimordial; }
    public void becomePrimordial() { this.isPrimordial = true; }
    
    public boolean isLivingConcept() { return isLivingConcept; }
    public void becomeLivingConcept() { this.isLivingConcept = true; }
    
    // Ability Management
    public void setCooldown(String ability, long cooldownMs) {
        abilityCooldowns.put(ability, System.currentTimeMillis() + cooldownMs);
    }
    
    public boolean isAbilityReady(String ability) {
        Long cooldown = abilityCooldowns.get(ability);
        return cooldown == null || System.currentTimeMillis() >= cooldown;
    }
    
    public long getAbilityCooldownRemaining(String ability) {
        Long cooldown = abilityCooldowns.get(ability);
        if (cooldown == null) return 0;
        long remaining = cooldown - System.currentTimeMillis();
        return Math.max(0, remaining);
    }
    
    public boolean hasActiveAbility() {
        return activeDominion != null && !activeDominion.isEmpty();
    }
    
    public void decrementAbilityCooldown() {
        // Implementation for cooldown decrement
    }
    
    public String getUUID() { return uuid; }
}
