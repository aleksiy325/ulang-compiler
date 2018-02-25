public class Identifier extends BaseElement {
    String val;

    public Identifier (String val) {
        this.val = val;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}