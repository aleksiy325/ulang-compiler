import java.util.ArrayList;

public class Program {
    ArrayList<Function> statementList;

    public Program () {
        statementList = new ArrayList<Function>();
    }

    public void add (Function f) {
        statementList.add(f);
    }

    public Function get (int index) {
        return statementList.get(index);
    }

    public int size () {
        return statementList.size();
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }

    public IRProgram accept (IRVisitor v) {
        return v.visit(this);
    }

    public IRProgram accept (JVMVisitor v) {
        return v.visit(this);
    }
}