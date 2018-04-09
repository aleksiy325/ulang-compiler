import java.util.ArrayList;

public class IRBody {
    ArrayList<IRTemp> temps;
    ArrayList<String> lines;

    public IRBody () {

    }

    public void addTemps(ArrayList<IRTemp> temps) {
        this.temps = temps;
    }

    public void addLines(ArrayList<String> lines) {
        this.lines = lines;
    }

    public void print() {
        for (IRTemp temp : temps) {
            temp.printDecl();
        }
        for (String line : lines) {
            System.out.println(line);
        }
        if (!lines.get(lines.size() - 1 ).startsWith("RETURN")) { // MAke sure all funcs have return hack
            System.out.println("RETURN;");
        }
    }

    public void printJVM(int params) {
        System.out.println(".limit locals " + String.valueOf(this.temps.size()));
        for (int i = params; i < temps.size(); i++) {
            temps.get(i).printJVMDecl();
        }
        System.out.println(".limit stack 16");
        for (String line : lines) {
            System.out.println(line);
        }
        if (!lines.get(lines.size() - 1 ).startsWith("return")) { // MAke sure all funcs have return hack
            System.out.println("return");
        }
    }
}