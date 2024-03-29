public class VariableDeclaration extends BaseElement {
    Type type;
    Identifier id;

    public VariableDeclaration (Type type, Identifier id) {
        this.type = type;
        this.id = id;
        this.line = id.line;
        this.charPos = id.charPos;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }    

    public IRTemp accept (IRVisitor v) {
        return v.visit(this);
    }

        public IRTemp accept (JVMVisitor v) {
        return v.visit(this);
    }
}