package com.godkiller1233.dominion.core;

import java.util.List;

public interface ScriptFunction {
    Object execute(List<Object> args, ScriptContext context) throws Exception;
    String getName();
    int getParameterCount();
}
