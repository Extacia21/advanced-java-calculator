package com.calculator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorEngine {

    private ScriptEngine engine;
    private String memory = "";
    private final long memoryUsageLimit = 2L * 1024 * 1024 * 1024; // 2GB

    public CalculatorEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        this.engine = manager.getEngineByName("JavaScript");
    }

    public String evaluate(String expr) {
        try {
            expr = expr.replace("sqrt", "Math.sqrt")
                    .replace("log", "Math.log10")
                    .replace("sin", "Math.sin")
                    .replace("cos", "Math.cos")
                    .replace("tan", "Math.tan")
                    .replace("x^2", "Math.pow(x,2)");

            Object result = engine.eval(expr);
            return result.toString();
        } catch (ScriptException | NullPointerException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public void store(String value) {
        if (value.getBytes().length <= memoryUsageLimit) {
            memory = value;
        }
    }

    public String recall() {
        return memory;
    }

    public void clearMemory() {
        memory = "";
    }
}
