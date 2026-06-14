package com.godkiller1233.dominion.religion;

import java.util.*;

public class Religion {
    private String religionId;
    private String name;
    private String founder;
    private String symbol;
    private List<String> beliefs = new ArrayList<>();
    private Set<String> followers = new HashSet<>();
    private double faith = 0;
    private int level = 1;
    private String currentProphet;
    private List<String> saints = new ArrayList<>();
    
    public Religion(String religionId, String name, String founder, String symbol) {
        this.religionId = religionId;
        this.name = name;
        this.founder = founder;
        this.symbol = symbol;
        followers.add(founder);
    }
    
    public void addFollower(String uuid) {
        followers.add(uuid);
    }
    
    public void removeFollower(String uuid) {
        followers.remove(uuid);
    }
    
    public void addBelief(String belief) {
        beliefs.add(belief);
    }
    
    public void addFaith(double amount) {
        faith += amount;
    }
    
    public void levelUp() {
        if (faith >= 1000) {
            faith -= 1000;
            level++;
        }
    }
    
    public boolean canMakeGod() {
        return followers.size() >= 20;
    }
    
    public void makeProphet(String uuid) {
        this.currentProphet = uuid;
    }
    
    public void makeSaint(String uuid) {
        saints.add(uuid);
    }
    
    // Getters
    public String getReligionId() { return religionId; }
    public String getName() { return name; }
    public String getFounder() { return founder; }
    public String getSymbol() { return symbol; }
    public List<String> getBeliefs() { return beliefs; }
    public Set<String> getFollowers() { return followers; }
    public double getFaith() { return faith; }
    public int getLevel() { return level; }
    public String getCurrentProphet() { return currentProphet; }
    public List<String> getSaints() { return saints; }
}
