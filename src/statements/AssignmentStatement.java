public class AssignmentStatement extends Statement {
    Identifier id;
    ArrayDereference deref;
    Expression expr;
    boolean isArray;

    public AssignmentStatement(Identifier id, Expression expr) {
        this.id = id;
        this.expr = expr;
        this.isArray = false;
        this.line = id.line;
        this.charPos = id.charPos;
    }

    public AssignmentStatement(Identifier id, Expression size, Expression expr) {
        this.id = id;
        this.expr = expr;
        this.isArray = true;
        this.deref = new ArrayDereference(id, size);
        this.line = id.line;
        this.charPos = id.charPos;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}