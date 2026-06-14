package com.godkiller1233.dominion.script;

import com.godkiller1233.dominion.core.ScriptContext;

public interface DominionScript {
    void execute(ScriptContext context) throws Exception;
    String getName();
    String getType(); // "bloodline", "dominion", "religion", etc.
}
