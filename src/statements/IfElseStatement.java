public class IfElseStatement extends Statement {
    Expression expr;
    Block ifblock;
    Block elseblock;
    boolean hasElse;

    public IfElseStatement(Expression expr, Block ifblock) {
        this.expr = expr;
        this.ifblock = ifblock;
        this.hasElse = false;
        this.line = expr.line;
        this.charPos = expr.charPos;
    }

    public IfElseStatement(Expression expr, Block ifblock, Block elseblock) {
        this.expr = expr;
        this.ifblock = ifblock;
        this.elseblock = elseblock;
        this.hasElse = true;
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