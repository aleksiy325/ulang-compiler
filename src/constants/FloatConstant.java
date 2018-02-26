public class FloatConstant extends Constant {
    Float val;
    final Type type = TypeDefs.floatType;

    public FloatConstant (Float val) {
        this.val = val;
    }

    public FloatConstant (Float val, int line, int charPos) {
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
}