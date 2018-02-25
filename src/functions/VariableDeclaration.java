public class VariableDeclaration extends BaseElement {
    CompoundType type;
    Identifier id;

    public VariableDeclaration (CompoundType type, Identifier id) {
        this.type = type;
        this.id = id;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}