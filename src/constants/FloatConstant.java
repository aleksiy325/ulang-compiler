public class FloatConstant extends Constant {
    Float val;
    final Type type = TypeDefs.floatType;

    public FloatConstant (Float val) {
        this.val = val;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}