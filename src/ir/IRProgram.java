import java.util.ArrayList;

public class IRProgram {
    String name;
    ArrayList<IRFunction> functions;
    IRScope scope = new IRScope();


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

    void printJVM() {
        System.out.println(".source " + name);
        System.out.println(".class public " + name);
        System.out.println(".super java/lang/Object");
        for (IRFunction func : functions) {
            func.printJVM();
        }
        System.out.println(".method public static main([Ljava/lang/String;)V");
        System.out.println("    ; set limits used by this method");
        System.out.println("    .limit locals 1");
        System.out.println("    .limit stack 4");
        System.out.println("    invokestatic ar2/__main()V");
        System.out.println("    return");
        System.out.println("    .end method");
        System.out.println(".method public <init>()V");
        System.out.println("    aload_0");
        System.out.println("    invokenonvirtual java/lang/Object/<init>()V");
        System.out.println("    return");
        System.out.println("    .end method");
    }
}