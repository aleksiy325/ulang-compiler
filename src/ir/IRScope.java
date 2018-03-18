import java.util.HashMap;
import java.util.ArrayList;

public class IRScope {
    private HashMap<Identifier, Integer> varMap;
    private ArrayList<IRTemp> temps;
    int curTemps;
    int curLabel;

    public IRScope() {
        this.varMap = new HashMap<Identifier, Integer>();
        this.temps = new ArrayList<IRTemp>();
        curTemps = 0;
        curLabel = 0;
    }

    public void reset() {
        this.varMap = new HashMap<Identifier, Integer>();
        this.temps = new ArrayList<IRTemp>();
        curTemps = 0;
        curLabel = 0;
    }

    public IRTemp newTemp(Identifier id, String type) {
        int var = curTemps;
        varMap.put(id, var);
        curTemps++;
        IRTemp t = new IRTemp(var, type);
        temps.add(t);
        return t;
    }

    public IRTemp newTemp(String type) {
        int var = curTemps;
        curTemps++;
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

    public IRLabel newLabel() {
        int var = curLabel;
        curLabel++;
        return new IRLabel(var);
    }
}