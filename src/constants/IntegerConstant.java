import java.util.Objects;

public class IntegerConstant extends Constant {
    Integer val;
    Type type;

    public IntegerConstant (Integer val, String typeStr) {
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