public class CharConstant extends Constant {
    char val;
    Type type;

    public CharConstant (char val, String typeStr) {
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