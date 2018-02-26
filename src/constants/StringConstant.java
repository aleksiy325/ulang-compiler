public class StringConstant extends Constant {
    String val;
    final Type type = TypeDefs.stringType;


    public StringConstant (String val) {
        this.val = val;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}