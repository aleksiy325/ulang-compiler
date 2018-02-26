public class WhileStatement extends Statement {
    Expression expr;
    Block block;

    public WhileStatement(Expression expr, Block block) {
        this.expr = expr;
        this.block = block;
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