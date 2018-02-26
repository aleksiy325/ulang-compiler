public class MultiplicationExpression extends Expression {
    Atom left;
    Atom right;

    public MultiplicationExpression (Atom left) {
        this.left = left;
        this.line = left.line;
        this.charPos = left.charPos;
    }

    public MultiplicationExpression (Atom left, Atom right) {
        this.left = left;
        this.right = right;
        this.line = left.line;
        this.charPos = left.charPos;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}