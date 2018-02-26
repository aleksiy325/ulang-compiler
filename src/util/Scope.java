import java.util.Stack;

public class Scope {
    private ScopeFrame global;
    private Stack<ScopeFrame> local;

    public Scope() {
        global = new ScopeFrame();
        local = new Stack<ScopeFrame>();
    }

    public void enterLocalScope() {
        local.push(new ScopeFrame());
    }

    public void exitLocalScope() {
        local.pop();
    }

    public void putGlobalFunction(Identifier id, CompoundType type) {
        global.putFunction(id, type);
    }

    public void putGlobalVariable(Identifier id, CompoundType type) {
        global.putVariable(id, type);
    }

    public void putLocalFunction(Identifier id, CompoundType type) {
        local.peek().putFunction(id, type);
    }

    public void putLocalVariable(Identifier id, CompoundType type) {
        local.peek().putVariable(id, type);
    }


    public boolean functionExists(Identifier id) {
        if (global.containsFunction(id)) {
            return true;
        }
        for (ScopeFrame frame : local) {
            if (frame.containsFunction(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean variableExists(Identifier id) {
        if (global.containsVariable(id)) {
            return true;
        }
        for (ScopeFrame frame : local) {
            if (frame.containsVariable(id)) {
                return true;
            }
        }
        return false;
    }

    public CompoundType getFunctionType(Identifier id) {
        if (global.containsFunction(id)) {
            return global.getFunctionType(id);
        }
        for (ScopeFrame frame : local) {
            if (frame.containsFunction(id)) {
                return frame.getFunctionType(id);
            }
        }
        return null;
    }

    public CompoundType getVariableType(Identifier id) {
        if (global.containsVariable(id)) {
            return global.getVariableType(id);
        }
        for (ScopeFrame frame : local) {
            if (frame.containsVariable(id)) {
                return frame.getVariableType(id);
            }
        }
        return null;
    }
}