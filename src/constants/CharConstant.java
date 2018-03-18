public class CharConstant extends Constant {
    char val;
    final Type type = TypeDefs.charType;

    public CharConstant (char val, int line, int charPos) {
        this.val = val;
        this.charPos = charPos;
        this.line = line;
    }

    public CharConstant (char val) {
        this.val = val;
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