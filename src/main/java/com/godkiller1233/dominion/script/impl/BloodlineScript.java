package com.godkiller1233.dominion.script.impl;

import java.util.List;
import com.godkiller1233.dominion.core.ScriptContext;
import com.godkiller1233.dominion.script.DominionScript;

public class BloodlineScript implements DominionScript {
    private String name;
    private String scriptName;
    private List<String> properties;
    
    public BloodlineScript(String name, String scriptName, List<String> properties) {
        this.name = name;
        this.scriptName = scriptName;
        this.properties = properties;
    }
    
    @Override
    public void execute(ScriptContext context) throws Exception {
        // Register bloodline with the registry
        context.setVariable("bloodline_" + name, new BloodlineDefinition(name, properties));
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "bloodline";
    }
    
    public static class BloodlineDefinition {
        public String name;
        public List<String> properties;
        
        public BloodlineDefinition(String name, List<String> properties) {
            this.name = name;
            this.properties = properties;
        }
    }
}
