import java.util.HashMap;
import java.util.ArrayList;

public class IRScope {
    private HashMap<Identifier, Integer> varMap;
    private ArrayList<IRTemp> temps;
    int cur;

    public IRScope() {
        this.varMap = new HashMap<Identifier, Integer>();
        this.temps = new ArrayList<IRTemp>();
        cur = 0;
    }

    public void reset() {
        this.varMap = new HashMap<Identifier, Integer>();
        this.temps = new ArrayList<IRTemp>();
        cur = 0;
    }

    public int putVariable(Identifier id) {
        int var = cur;
        varMap.put(id, var);
        cur++;
        return var;
    }

    public boolean containsVariable(Identifier id) {
        return varMap.containsKey(id);
    }


    public int getVariableSymbol(Identifier id) {
        return varMap.get(id);
    }

}