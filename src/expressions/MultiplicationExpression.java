public class MultiplicationExpression extends Expression {
    Atom left;
    Atom right;

    public MultiplicationExpression (Atom left) {
        this.left = left;
    }

    public MultiplicationExpression (Atom left, Atom right) {
        this.left = left;
        this.right = right;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}