public class PlusMinusExpression extends Expression {
    Expression left;
    Expression right;
    boolean isAddition;

    public PlusMinusExpression (Expression left) {
        this.left = left;
        this.line = left.line;
        this.charPos = left.charPos;
    }

    public PlusMinusExpression (Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.isAddition = operator.equals("+");
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

    public IRTemp accept (JVMVisitor v) {
        return v.visit(this);
    }
}