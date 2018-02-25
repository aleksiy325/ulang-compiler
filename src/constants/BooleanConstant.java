public class BooleanConstant extends Constant {
    Boolean val;
    Type type;

    public BooleanConstant (Boolean val, String typeStr) {
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