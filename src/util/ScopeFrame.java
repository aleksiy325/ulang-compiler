import java.util.HashMap;

public class ScopeFrame {
    private HashMap<Identifier, Type> functionMap;
    private HashMap<Identifier, Type> varMap;

    public ScopeFrame() {
        functionMap = new HashMap<Identifier, Type>();
        varMap = new HashMap<Identifier, Type>();
    }

    public void putFunction(Identifier id, Type type) {
        functionMap.put(id, type);
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

    public Type getFunctionType(Identifier id) {
        return functionMap.get(id);
    }

    public Type getVariableType(Identifier id) {
        return varMap.get(id);
    }
}