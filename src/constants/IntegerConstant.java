import java.util.Objects;

public class IntegerConstant extends Constant {
    Integer val;
    final Type type = TypeDefs.integerType;

    public IntegerConstant (Integer val) {
        this.val = val;
    }

    public IntegerConstant (int val, int line, int charPos) {
        this.val = val;
        this.charPos = charPos;
        this.line = line;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }    

    public IRTemp accept (IRVisitor v) {
        return v.visit(this);
    }

    public IRTemp accept (JVMVisitor v) {
        return v.visit(this);
    }
}