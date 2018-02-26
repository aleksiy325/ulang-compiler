public class CharConstant extends Constant {
    char val;
    CompoundType type;

    public CharConstant (char val, String typeStr) {
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