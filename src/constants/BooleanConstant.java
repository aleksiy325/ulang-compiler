public class BooleanConstant extends Constant {
    Boolean val;
    final Type type = TypeDefs.booleanType;

    public BooleanConstant (Boolean val) {
        this.val = val;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}