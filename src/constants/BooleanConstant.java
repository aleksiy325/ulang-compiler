public class BooleanConstant extends Constant {
    Boolean val;
    final Type type = TypeDefs.booleanType;

    public BooleanConstant (Boolean val) {
        this.val = val;
    }

    public BooleanConstant (Boolean val, int line, int charPos) {
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

    public String accept (IRVisitor v) {
        return v.visit(this);
    }
}