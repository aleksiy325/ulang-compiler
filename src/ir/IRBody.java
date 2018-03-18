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
    }
}