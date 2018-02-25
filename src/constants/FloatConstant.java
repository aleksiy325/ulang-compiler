public class FloatConstant extends Constant {
    Float val;
    Type type;

    public FloatConstant (Float val, String typeStr) {
        this.val = val;
        this.type = new Type(typeStr);

    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}