public class Function extends BaseElement {
    FunctionDeclaration decl;
    FunctionBody body;

    public Function (FunctionDeclaration decl, FunctionBody body) {
        this.decl = decl;
        this.body = body;
        this.line = decl.line;
        this.charPos = decl.charPos;
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