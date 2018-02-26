public class FloatConstant extends Constant {
    Float val;
    CompoundType type;

    public FloatConstant (Float val, String typeStr) {
        this.val = val;
        this.type = new CompoundType(typeStr);

    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public CompoundType accept (TypeVisitor v) {
        return v.visit(this);
    }
}