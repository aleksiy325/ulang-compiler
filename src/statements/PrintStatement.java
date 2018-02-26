public class PrintStatement extends Statement {
    Expression expr;

    public PrintStatement( Expression expr ) {
        this.expr = expr;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public CompoundType accept (TypeVisitor v) {
        return v.visit(this);
    }
}