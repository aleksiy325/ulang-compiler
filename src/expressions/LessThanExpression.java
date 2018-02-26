public class LessThanExpression extends Expression {
    Expression left;
    Expression right;

    public LessThanExpression (Expression left) {
        this.left = left;
    }

    public LessThanExpression (Expression left, Expression right) {
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