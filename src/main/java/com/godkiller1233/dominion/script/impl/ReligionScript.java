package com.godkiller1233.dominion.script.impl;

import java.util.List;
import com.godkiller1233.dominion.core.ScriptContext;
import com.godkiller1233.dominion.script.DominionScript;

public class ReligionScript implements DominionScript {
    private String name;
    private String scriptName;
    private List<String> properties;
    
    public ReligionScript(String name, String scriptName, List<String> properties) {
        this.name = name;
        this.scriptName = scriptName;
        this.properties = properties;
    }
    
    @Override
    public void execute(ScriptContext context) throws Exception {
        // Register religion with the registry
        context.setVariable("religion_" + name, new ReligionDefinition(name, properties));
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "religion";
    }
    
    public static class ReligionDefinition {
        public String name;
        public List<String> properties;
        
        public ReligionDefinition(String name, List<String> properties) {
            this.name = name;
            this.properties = properties;
        }
    }
}
