import java.util.ArrayList;

public class IRProgram {
    String name;
    ArrayList<IRFunction> functions;


    public IRProgram () {
        functions = new ArrayList<IRFunction>();
    }

    public IRProgram (String name) {
        functions = new ArrayList<IRFunction>();
        this.name = name;
    }

    void addFunction(IRFunction func) {
        functions.add(func);
    }

    void print() {
        System.out.print("PROG " + name);
        for (IRFunction func : functions) {
            func.print();
        }
        System.out.println();
    }
}