package com.godkiller1233.dominion.core;

import java.util.*;

/**
 * Runtime context for script execution
 * Manages variables, functions, and state
 */
public class ScriptContext {
    private Map<String, Object> variables = new HashMap<>();
    private Map<String, ScriptFunction> functions = new HashMap<>();
    private Map<String, Object> metadata = new HashMap<>();
    private ScriptContext parentContext;
    
    public ScriptContext() {
        this(null);
    }
    
    public ScriptContext(ScriptContext parent) {
        this.parentContext = parent;
    }
    
    public void setVariable(String name, Object value) {
        variables.put(name, value);
    }
    
    public Object getVariable(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        if (parentContext != null) {
            return parentContext.getVariable(name);
        }
        return null;
    }
    
    public void setFunction(String name, ScriptFunction function) {
        functions.put(name, function);
    }
    
    public ScriptFunction getFunction(String name) {
        if (functions.containsKey(name)) {
            return functions.get(name);
        }
        if (parentContext != null) {
            return parentContext.getFunction(name);
        }
        return null;
    }
    
    public void setMetadata(String key, Object value) {
        metadata.put(key, value);
    }
    
    public Object getMetadata(String key) {
        return metadata.get(key);
    }
    
    public Map<String, Object> getAllVariables() {
        return new HashMap<>(variables);
    }
    
    public Map<String, ScriptFunction> getAllFunctions() {
        return new HashMap<>(functions);
    }
}
