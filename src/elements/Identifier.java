public class Identifier extends BaseElement implements Comparable<Identifier> {
    String val;

    @Override
    public int compareTo(Identifier other) {
        return this.val.compareTo(other.val);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Identifier)) return false;
        Identifier other = (Identifier) obj;
        return this.val.equals(other.val);
    }

    @Override
    public int hashCode() {
        return this.val.hashCode();
    }

    public Identifier (String val) {
        this.val = val;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}