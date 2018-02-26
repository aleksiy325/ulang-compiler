public class ReturnStatement extends Statement {
    Expression expr;
    boolean isEmpty;

    public ReturnStatement() {
        this.isEmpty = true;
    }

    public ReturnStatement( Expression expr ) {
        this.expr = expr;
        this.isEmpty = false;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}