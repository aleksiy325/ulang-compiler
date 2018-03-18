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

    public IRTemp newTemp(Identifier id, String type) {
        int var = cur;
        varMap.put(id, var);
        cur++;
        IRTemp t = new IRTemp(var, type);
        temps.add(t);
        return t;
    }

    public IRTemp newTemp(String type) {
        int var = cur;
        cur++;
        IRTemp t = new IRTemp(var, type);
        temps.add(t);
        return t;
    }

    public boolean containsVariable(Identifier id) {
        return varMap.containsKey(id);
    }

    public IRTemp getVariableSymbol(Identifier id) {
        return temps.get(varMap.get(id));
    }

    public ArrayList<IRTemp> getTemps() {
        return this.temps;
    }

}