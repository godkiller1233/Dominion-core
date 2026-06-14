package com.godkiller1233.dominion.core;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.godkiller1233.dominion.DominionCore;
import com.godkiller1233.dominion.script.DominionScript;
import com.godkiller1233.dominion.script.parser.DCParser;

/**
 * DominionCore Script Engine (DSE)
 * 
 * A powerful, extensible scripting system for creating custom Dominion content.
 * Similar to Skript but designed specifically for DominionCore.
 * 
 * Supports:
 * - Custom bloodlines
 * - Custom dominions
 * - Custom religions
 * - Custom items/abilities
 * - Event handlers
 * - Custom commands
 */
public class DominionScriptEngine {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SCRIPT_DIR = "dominion/scripts";
    private static final String CUSTOM_DIR = "dominion/custom";
    
    private Map<String, DominionScript> loadedScripts = new HashMap<>();
    private DCParser parser = new DCParser();
    private ScriptContext globalContext = new ScriptContext();
    
    public void initialize() {
        LOGGER.info("Initializing DominionCore Script Engine...");
        createDirectories();
        loadAllScripts();
    }
    
    private void createDirectories() {
        try {
            Files.createDirectories(Paths.get(SCRIPT_DIR));
            Files.createDirectories(Paths.get(CUSTOM_DIR));
            LOGGER.info("Created script directories");
        } catch (Exception e) {
            LOGGER.error("Failed to create script directories", e);
        }
    }
    
    public void loadAllScripts() {
        LOGGER.info("Loading all .dc scripts...");
        
        // Load core scripts
        loadScriptsFromDirectory(SCRIPT_DIR);
        
        // Load custom scripts
        loadScriptsFromDirectory(CUSTOM_DIR);
    }
    
    private void loadScriptsFromDirectory(String directory) {
        try {
            Path path = Paths.get(directory);
            if (!Files.exists(path)) return;
            
            Files.list(path)
                .filter(p -> p.toString().endsWith(".dc"))
                .forEach(this::loadScript);
        } catch (Exception e) {
            LOGGER.error("Failed to load scripts from " + directory, e);
        }
    }
    
    public void loadScript(Path scriptPath) {
        try {
            String scriptName = scriptPath.getFileName().toString();
            String content = Files.readString(scriptPath);
            DominionScript script = parser.parse(scriptName, content);
            
            if (script != null) {
                loadedScripts.put(scriptName, script);
                script.execute(globalContext);
                LOGGER.info("Loaded script: " + scriptName);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to load script: " + scriptPath, e);
        }
    }
    
    public void reloadScript(String scriptName) {
        LOGGER.info("Reloading script: " + scriptName);
        Path scriptPath = findScript(scriptName);
        if (scriptPath != null) {
            loadScript(scriptPath);
        }
    }
    
    public void reloadAllScripts() {
        LOGGER.info("Reloading all scripts...");
        loadedScripts.clear();
        globalContext = new ScriptContext();
        loadAllScripts();
    }
    
    private Path findScript(String name) {
        try {
            List<Path> results = Files.list(Paths.get(CUSTOM_DIR))
                .filter(p -> p.getFileName().toString().equals(name + ".dc"))
                .toList();
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            return null;
        }
    }
    
    public DominionScript getScript(String name) {
        return loadedScripts.get(name + ".dc");
    }
    
    public Map<String, DominionScript> getAllScripts() {
        return new HashMap<>(loadedScripts);
    }
    
    public ScriptContext getGlobalContext() {
        return globalContext;
    }
}
