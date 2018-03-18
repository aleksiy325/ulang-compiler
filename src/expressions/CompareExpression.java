public class CompareExpression extends Expression {
    Expression left;
    Expression right;

    public CompareExpression (Expression left) {
        this.left = left;
        this.line = left.line;
        this.charPos = left.charPos;
    }

    public CompareExpression (Expression left, Expression right) {
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

    public IRTemp accept (IRVisitor v) {
        return v.visit(this);
    }
}