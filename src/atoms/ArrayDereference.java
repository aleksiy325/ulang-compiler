public class ArrayDereference extends Atom {
    Identifier id;
    Expression expr;

    public ArrayDereference (Identifier id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public CompoundType accept (TypeVisitor v) {
        return v.visit(this);
    }
}