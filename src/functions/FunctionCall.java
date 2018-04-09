public class FunctionCall extends Atom {
    Identifier id;
    ExpressionList exprs;

    public FunctionCall (Identifier id, ExpressionList exprs) {
        this.id = id;
        this.exprs = exprs;
        this.line = id.line;
        this.charPos = id.charPos;
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