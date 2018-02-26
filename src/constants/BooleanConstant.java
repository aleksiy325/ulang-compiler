public class BooleanConstant extends Constant {
    Boolean val;
    CompoundType type;

    public BooleanConstant (Boolean val, String typeStr) {
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