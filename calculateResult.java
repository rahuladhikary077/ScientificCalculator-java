private void calculateResult() {
    try {
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        ScriptEngine engine = factory.getScriptEngine();
        Object result = engine.eval("Math." + currentInput);
        outputLabel.setText("Result: " + result);
        clearInput();
    } catch (ScriptException e) {
        outputLabel.setText("Error: Invalid Expression");
        clearInput();
    }
}
