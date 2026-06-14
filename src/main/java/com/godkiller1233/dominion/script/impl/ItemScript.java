package com.godkiller1233.dominion.script.impl;

import java.util.List;
import com.godkiller1233.dominion.core.ScriptContext;
import com.godkiller1233.dominion.script.DominionScript;

public class ItemScript implements DominionScript {
    private String name;
    private String scriptName;
    private List<String> properties;
    
    public ItemScript(String name, String scriptName, List<String> properties) {
        this.name = name;
        this.scriptName = scriptName;
        this.properties = properties;
    }
    
    @Override
    public void execute(ScriptContext context) throws Exception {
        // Register item with the registry
        context.setVariable("item_" + name, new ItemDefinition(name, properties));
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "item";
    }
    
    public static class ItemDefinition {
        public String name;
        public List<String> properties;
        
        public ItemDefinition(String name, List<String> properties) {
            this.name = name;
            this.properties = properties;
        }
    }
}
