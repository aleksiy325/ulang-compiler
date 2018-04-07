public class StringConstant extends Constant {
    String val;
    final Type type = TypeDefs.stringType;

    public StringConstant (String val) {
        this.val = val;
    }

    public StringConstant (String val, int line, int charPos) {
        this.val = val;
        this.charPos = charPos;
        this.line = line;
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