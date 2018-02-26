import java.util.Objects;

public class CompoundType extends BaseElement implements Comparable<CompoundType>  {
    Type type;
    IntegerConstant size;
    boolean isArray;
    int iSize;

    public CompoundType (String val) {
        isArray = false;
        this.type = new Type(val);
        this.iSize = 1;
    }

    public CompoundType (Type type) {
        isArray = false;
        this.type = type;
        this.iSize = 1;
    }

    public void makeArray (IntegerConstant size) {
        this.isArray = true;
        this.size = size;
        this.iSize = size.val;
    }

    @Override
    public int compareTo(CompoundType other) {
        int cmp = this.type.compareTo(other.type);
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
        if (!(obj instanceof CompoundType)) return false;
        CompoundType other = (CompoundType) obj;
        return this.type.equals(other.type) && this.size == other.size && this.isArray == other.isArray;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.size, this.isArray);
    }


    public void accept (Visitor v) {
        v.visit(this);
    }

    public CompoundType accept (TypeVisitor v) {
        return v.visit(this);
    }
}