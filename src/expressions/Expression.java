public abstract class Expression extends Atom {
    public abstract void accept (Visitor v);
    public abstract CompoundType accept (TypeVisitor v);
}