public class FunctionDeclaration extends BaseElement {
    Type type;
    Identifier id;
    FormalParameterList params;

    public FunctionDeclaration (Type type, Identifier id, FormalParameterList params) {
        this.type = type;
        this.id = id;
        this.params = params;
        this.line = id.line;
        this.charPos = id.charPos;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }

    public IRFunction accept (IRVisitor v) {
        return v.visit(this);
    }

    public IRFunction accept (JVMVisitor v) {
        return v.visit(this);
    }
}