public class VariableDeclaration extends BaseElement {
    Type type;
    Identifier id;

    public VariableDeclaration (Type type, Identifier id) {
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