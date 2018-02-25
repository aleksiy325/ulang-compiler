public abstract class Atom {
    public abstract void accept (Visitor v);
    public abstract Type accept (TypeVisitor v);
}