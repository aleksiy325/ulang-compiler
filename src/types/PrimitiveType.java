import java.util.Objects;

public class PrimitiveType extends BaseElement implements Comparable<PrimitiveType> {
    String id;

    public PrimitiveType (String id) {
        this.id = id;
    }

    @Override
    public int compareTo(PrimitiveType other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Type)) return false;
        PrimitiveType other = (PrimitiveType) obj;
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }

    public String accept (IRVisitor v) {
        return v.visit(this);
    }

    public String accept (JVMVisitor v) {
        return v.visit(this);
    }

    public String toString() {
        return id;
    }
}