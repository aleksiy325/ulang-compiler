import java.util.ArrayList;

public class IRProgram {
    ArrayList<IRFunction> functions;

    public IRProgram () {
        functions = new ArrayList<IRFunction>();
    }

    void addFunction(IRFunction func) {
        functions.add(func);
    }

    void print() {
        System.out.print("PROG simple");
        for (IRFunction func : functions) {
            func.print();
        }
        System.out.println();
    }
}