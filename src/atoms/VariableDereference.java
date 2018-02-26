public class VariableDereference extends Atom {
    Identifier id;

    public VariableDereference (Identifier id) {
        this.id = id;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public CompoundType accept (TypeVisitor v) {
        return v.visit(this);
    }
}