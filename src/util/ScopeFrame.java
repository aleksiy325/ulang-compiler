import java.util.HashMap;

public class ScopeFrame {
    private HashMap<Identifier, CompoundType> functionMap;
    private HashMap<Identifier, CompoundType> varMap;

    public ScopeFrame() {
        functionMap = new HashMap<Identifier, CompoundType>();
        varMap = new HashMap<Identifier, CompoundType>();
    }

    public void putFunction(Identifier id, CompoundType type) {
        functionMap.put(id, type);
    }

    public void putVariable(Identifier id, CompoundType type) {
        varMap.put(id, type);
    }

    public boolean containsFunction(Identifier id) {
        return functionMap.containsKey(id);
    }

    public boolean containsVariable(Identifier id) {
        return varMap.containsKey(id);
    }

    public CompoundType getFunctionType(Identifier id) {
        return functionMap.get(id);
    }

    public CompoundType getVariableType(Identifier id) {
        return varMap.get(id);
    }
}