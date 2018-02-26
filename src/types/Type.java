import java.util.Objects;

public class Type extends BaseElement implements Comparable<Type>  {
    PrimitiveType primType;
    IntegerConstant size;
    boolean isArray;
    int iSize;

    public Type (String id) {
        isArray = false;
        this.primType = new PrimitiveType(id);
        this.iSize = 1;
    }

    public Type (PrimitiveType primType) {
        isArray = false;
        this.primType = primType;
        this.iSize = 1;
    }

    public void makeArray (IntegerConstant size) {
        this.isArray = true;
        this.size = size;
        this.iSize = size.val;
    }

    @Override
    public int compareTo(Type other) {
        int cmp = this.primType.compareTo(other.primType);
        if (cmp != 0) return cmp;
        Boolean a = new Boolean(this.isArray);
        Boolean b = new Boolean(other.isArray);
        cmp = a.compareTo(b);
        if (cmp != 0) return cmp;
        Integer sa = this.iSize;
        Integer sb = other.iSize;
        return sa.compareTo(sb);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Type)) return false;
        Type other = (Type) obj;
        return this.primType.equals(other.primType) && this.size == other.size && this.isArray == other.isArray;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.primType, this.size, this.isArray);
    }


    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }

    public String toString() {
        String str = primType.toString();
        if (isArray) {
            str += "[" + this.iSize +  "]";
        }
        return str;
    }
}