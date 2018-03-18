public class SimpleStatement extends Statement {
    Expression expr;
    boolean isEmpty;

    public SimpleStatement() {
        this.isEmpty = true;
    }

    public SimpleStatement( Expression expr ) {
        this.expr = expr;
        this.isEmpty = false;
        this.line = expr.line;
        this.charPos = expr.charPos;
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