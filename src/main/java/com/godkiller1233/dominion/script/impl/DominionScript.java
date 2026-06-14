package com.godkiller1233.dominion.script.impl;

import java.util.List;
import com.godkiller1233.dominion.core.ScriptContext;

public class DominionScript implements com.godkiller1233.dominion.script.DominionScript {
    private String name;
    private String scriptName;
    private List<String> properties;
    
    public DominionScript(String name, String scriptName, List<String> properties) {
        this.name = name;
        this.scriptName = scriptName;
        this.properties = properties;
    }
    
    @Override
    public void execute(ScriptContext context) throws Exception {
        // Register dominion with the registry
        context.setVariable("dominion_" + name, new DominionDefinition(name, properties));
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "dominion";
    }
    
    public static class DominionDefinition {
        public String name;
        public List<String> properties;
        
        public DominionDefinition(String name, List<String> properties) {
            this.name = name;
            this.properties = properties;
        }
    }
}
