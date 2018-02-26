import java.util.Objects;

public class IntegerConstant extends Constant {
    Integer val;
    CompoundType type;

    public IntegerConstant (Integer val, String typeStr) {
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