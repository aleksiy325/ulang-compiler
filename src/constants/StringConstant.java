public class StringConstant extends Constant {
    String val;
    Type type;

    public StringConstant (String val, String typeStr) {
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