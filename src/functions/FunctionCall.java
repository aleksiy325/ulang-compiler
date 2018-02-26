public class FunctionCall extends Atom {
    Identifier id;
    ExpressionList exprs;

    public FunctionCall (Identifier id, ExpressionList exprs) {
        this.id = id;
        this.exprs = exprs;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}