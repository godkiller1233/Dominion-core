package com.godkiller1233.dominion.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import java.util.*;

/**
 * Custom legendary items and relics
 */
public class LegendaryItem extends Item {
    private String itemId;
    private String rarity; // common, rare, epic, legendary, mythic
    private float damageBonus;
    private Map<String, Float> attributeModifiers = new HashMap<>();
    private List<String> specialAbilities = new ArrayList<>();
    
    public LegendaryItem(Item.Properties properties, String itemId, String rarity) {
        super(properties);
        this.itemId = itemId;
        this.rarity = rarity;
    }
    
    public void addAttributeModifier(String attribute, float value) {
        attributeModifiers.put(attribute, value);
    }
    
    public void addSpecialAbility(String ability) {
        specialAbilities.add(ability);
    }
    
    public String getItemId() { return itemId; }
    public String getRarity() { return rarity; }
    public float getDamageBonus() { return damageBonus; }
    public List<String> getSpecialAbilities() { return specialAbilities; }
    public Map<String, Float> getAttributeModifiers() { return attributeModifiers; }
    
    public void setDamageBonus(float bonus) { this.damageBonus = bonus; }
}
