import java.util.ArrayList;

public class IRBody {
    ArrayList<IRTemp> temps;
    ArrayList<String> statements;

    public IRBody () {
        temps = new ArrayList<IRTemp>();
        statements = new ArrayList<String>();
    }

    public void addTemp(IRTemp temp) {
        temps.add(temp);
    }

    public void addStatement(String stmt) {
        statements.add(stmt);
    }

    public void print() {
        for (IRTemp temp : temps) {
            temp.printDecl();
        }
    }
}