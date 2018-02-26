import java.util.Objects;

public class Type extends BaseElement implements Comparable<Type> {
    String val;

    public Type (String val) {
        this.val = val;
    }

    @Override
    public int compareTo(Type other) {
        return this.val.compareTo(other.val);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Type)) return false;
        Type other = (Type) obj;
        return this.val.equals(other.val);
    }

    @Override
    public int hashCode() {
        return this.val.hashCode();
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public CompoundType accept (TypeVisitor v) {
        return v.visit(this);
    }
}