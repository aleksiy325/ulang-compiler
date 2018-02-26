import java.util.Objects;

public class IntegerConstant extends Constant {
    Integer val;
    final Type type = TypeDefs.integerType;

    public IntegerConstant (Integer val) {
        this.val = val;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}