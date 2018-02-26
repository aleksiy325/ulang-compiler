public class PrintStatement extends Statement {
    Expression expr;

    public PrintStatement( Expression expr ) {
        this.expr = expr;
        this.line = expr.line;
        this.charPos = expr.charPos;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}