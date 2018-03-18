import java.util.ArrayList;

public class IRFunction {
    Identifier id;
    ArrayList<IRTemp> params;
    IRBody body;
    String rtype;

    public IRFunction () {
        params = new ArrayList<IRTemp>();
    }

    public IRFunction (Identifier id, ArrayList<IRTemp> params, String rtype) {
        this.id = id;
        this.params = params;
        this.rtype = rtype;
    }

    public void addBody(IRBody body) {
        this.body = body;
    }

    public void print() {
        System.out.println();
        System.out.print("FUNC " + id.val + " (");
        for (IRTemp param : params) {
            System.out.print(param.type);
        }
        System.out.print(")" + rtype);
        for (IRTemp param : params) {
            param.printDecl();
        }
        body.print();
    }
}