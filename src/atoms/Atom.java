public abstract class Atom {
    public abstract void accept (Visitor v);
    public abstract CompoundType accept (TypeVisitor v);
}