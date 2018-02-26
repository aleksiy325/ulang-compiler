public class StringConstant extends Constant {
    String val;
    CompoundType type;

    public StringConstant (String val, String typeStr) {
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