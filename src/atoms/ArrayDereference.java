public class ArrayDereference extends Atom {
    Identifier id;
    Expression expr;

    public ArrayDereference (Identifier id, Expression expr) {
        this.id = id;
        this.expr = expr;
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