import java.util.HashMap;

public class ScopeFrame {
    private HashMap<Identifier, FunctionSignature> functionMap;
    private HashMap<Identifier, Type> varMap;

    public ScopeFrame() {
        functionMap = new HashMap<Identifier, FunctionSignature>();
        varMap = new HashMap<Identifier, Type>();
    }

    public void putFunction(Identifier id, FunctionSignature fs) {
        functionMap.put(id, fs);
    }

    public void putVariable(Identifier id, Type type) {
        varMap.put(id, type);
    }

    public boolean containsFunction(Identifier id) {
        return functionMap.containsKey(id);
    }

    public boolean containsVariable(Identifier id) {
        return varMap.containsKey(id);
    }

    public FunctionSignature getFunctionSignature(Identifier id) {
        return functionMap.get(id);
    }

    public Type getVariableType(Identifier id) {
        return varMap.get(id);
    }

}