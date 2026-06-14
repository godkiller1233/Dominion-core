package com.godkiller1233.dominion.achievement;

import java.util.*;

/**
 * Achievement and quest system
 */
public class Achievement {
    private String id;
    private String name;
    private String description;
    private String category;
    private int rewardPoints;
    private List<String> requirements = new ArrayList<>();
    private boolean isHidden;
    
    public Achievement(String id, String name, String description, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.rewardPoints = 10;
        this.isHidden = false;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public int getRewardPoints() { return rewardPoints; }
    public void setRewardPoints(int points) { this.rewardPoints = points; }
    public boolean isHidden() { return isHidden; }
    public void setHidden(boolean hidden) { this.isHidden = hidden; }
    
    public void addRequirement(String requirement) {
        requirements.add(requirement);
    }
    
    public List<String> getRequirements() {
        return requirements;
    }
    
    // Predefined achievements
    public static final Map<String, Achievement> ACHIEVEMENTS = new HashMap<>();
    
    static {
        // Bloodline achievements
        Achievement selectBloodline = new Achievement("select_bloodline", "First Blood", 
            "Select your first bloodline", "bloodline");
        selectBloodline.setRewardPoints(10);
        ACHIEVEMENTS.put(selectBloodline.id, selectBloodline);
        
        Achievement upgradeBloodline = new Achievement("upgrade_bloodline", "Evolved",
            "Upgrade your bloodline to the next tier", "bloodline");
        upgradeBloodline.setRewardPoints(25);
        ACHIEVEMENTS.put(upgradeBloodline.id, upgradeBloodline);
        
        // Dominion achievements
        Achievement equipDominion = new Achievement("equip_dominion", "Power Wielder",
            "Equip your first dominion", "dominion");
        equipDominion.setRewardPoints(15);
        ACHIEVEMENTS.put(equipDominion.id, equipDominion);
        
        // Religion achievements
        Achievement createReligion = new Achievement("create_religion", "Prophet",
            "Create your own religion", "religion");
        createReligion.setRewardPoints(50);
        ACHIEVEMENTS.put(createReligion.id, createReligion);
        
        Achievement gainFollowers = new Achievement("gain_followers", "Messiah",
            "Gain 20 followers in your religion", "religion");
        gainFollowers.setRewardPoints(100);
        ACHIEVEMENTS.put(gainFollowers.id, gainFollowers);
        
        // God achievements
        Achievement becomeGod = new Achievement("become_god", "Ascended",
            "Become a God", "progression");
        becomeGod.setRewardPoints(500);
        ACHIEVEMENTS.put(becomeGod.id, becomeGod);
        
        Achievement becomeTitan = new Achievement("become_titan", "Titan",
            "Reach Titan status", "progression");
        becomeTitan.setRewardPoints(750);
        ACHIEVEMENTS.put(becomeTitan.id, becomeTitan);
        
        // Combat achievements
        Achievement killWithSpell = new Achievement("kill_with_spell", "Spellcaster",
            "Kill an enemy with a spell", "combat");
        killWithSpell.setRewardPoints(20);
        ACHIEVEMENTS.put(killWithSpell.id, killWithSpell);
        
        Achievement surviveEvent = new Achievement("survive_event", "Survivor",
            "Survive a world event", "combat");
        surviveEvent.setRewardPoints(50);
        ACHIEVEMENTS.put(surviveEvent.id, surviveEvent);
    }
}
