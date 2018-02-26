public class FunctionDeclaration extends BaseElement {
    Type type;
    Identifier id;
    FormalParameterList params;

    public FunctionDeclaration (Type type, Identifier id, FormalParameterList params) {
        this.type = type;
        this.id = id;
        this.params = params;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}