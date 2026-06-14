package com.godkiller1233.dominion.script.parser;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.godkiller1233.dominion.script.DominionScript;
import com.godkiller1233.dominion.script.impl.*;

/**
 * Parser for .dc (Dominion Config) files
 * 
 * Supports syntax like:
 * 
 * bloodline Genesis {
 *   type: creation
 *   passive: create_structures
 *   active: create_life
 * }
 * 
 * dominion Fire {
 *   type: fire
 *   abilities: [fire_attack, fire_shield]
 * }
 */
public class DCParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private String scriptName;
    private String content;
    private int position = 0;
    private List<Token> tokens;
    
    public DominionScript parse(String name, String content) {
        this.scriptName = name;
        this.content = content;
        this.position = 0;
        this.tokens = new ArrayList<>();
        
        try {
            tokenize();
            position = 0;
            return parseScript();
        } catch (Exception e) {
            LOGGER.error("Failed to parse script: " + name, e);
            return null;
        }
    }
    
    private void tokenize() {
        while (position < content.length()) {
            char c = content.charAt(position);
            
            if (Character.isWhitespace(c)) {
                position++;
                continue;
            }
            
            if (c == '#') {
                skipComment();
                continue;
            }
            
            if (c == '{' || c == '}' || c == ':' || c == ',') {
                tokens.add(new Token(String.valueOf(c), TokenType.SYMBOL));
                position++;
                continue;
            }
            
            if (c == '[' || c == ']') {
                tokens.add(new Token(String.valueOf(c), TokenType.BRACKET));
                position++;
                continue;
            }
            
            if (Character.isLetter(c) || c == '_') {
                tokens.add(new Token(readIdentifier(), TokenType.IDENTIFIER));
                continue;
            }
            
            if (c == '"') {
                tokens.add(new Token(readString(), TokenType.STRING));
                continue;
            }
            
            if (Character.isDigit(c)) {
                tokens.add(new Token(readNumber(), TokenType.NUMBER));
                continue;
            }
            
            position++;
        }
    }
    
    private String readIdentifier() {
        StringBuilder sb = new StringBuilder();
        while (position < content.length() && (Character.isLetterOrDigit(content.charAt(position)) || content.charAt(position) == '_')) {
            sb.append(content.charAt(position++));
        }
        return sb.toString();
    }
    
    private String readString() {
        position++; // skip opening quote
        StringBuilder sb = new StringBuilder();
        while (position < content.length() && content.charAt(position) != '"') {
            sb.append(content.charAt(position++));
        }
        position++; // skip closing quote
        return sb.toString();
    }
    
    private String readNumber() {
        StringBuilder sb = new StringBuilder();
        while (position < content.length() && (Character.isDigit(content.charAt(position)) || content.charAt(position) == '.')) {
            sb.append(content.charAt(position++));
        }
        return sb.toString();
    }
    
    private void skipComment() {
        while (position < content.length() && content.charAt(position) != '\n') {
            position++;
        }
    }
    
    private DominionScript parseScript() {
        if (tokens.isEmpty()) return null;
        
        String type = tokens.get(0).value;
        
        switch (type) {
            case "bloodline":
                return parseBloodline();
            case "dominion":
                return parseDominion();
            case "religion":
                return parseReligion();
            case "item":
                return parseItem();
            default:
                LOGGER.warn("Unknown script type: " + type);
                return null;
        }
    }
    
    private DominionScript parseBloodline() {
        position++; // skip "bloodline"
        String name = tokens.get(position++).value;
        return new BloodlineScript(name, scriptName, parseBlock());
    }
    
    private DominionScript parseDominion() {
        position++; // skip "dominion"
        String name = tokens.get(position++).value;
        return new DominionScript(name, scriptName, parseBlock());
    }
    
    private DominionScript parseReligion() {
        position++; // skip "religion"
        String name = tokens.get(position++).value;
        return new ReligionScript(name, scriptName, parseBlock());
    }
    
    private DominionScript parseItem() {
        position++; // skip "item"
        String name = tokens.get(position++).value;
        return new ItemScript(name, scriptName, parseBlock());
    }
    
    private List<String> parseBlock() {
        List<String> properties = new ArrayList<>();
        if (position < tokens.size() && "{" .equals(tokens.get(position).value)) {
            position++; // skip {
            while (position < tokens.size() && !"}" .equals(tokens.get(position).value)) {
                properties.add(tokens.get(position).value);
                position++;
            }
            position++; // skip }
        }
        return properties;
    }
    
    static class Token {
        String value;
        TokenType type;
        
        Token(String value, TokenType type) {
            this.value = value;
            this.type = type;
        }
    }
    
    enum TokenType {
        IDENTIFIER, STRING, NUMBER, SYMBOL, BRACKET
    }
}
