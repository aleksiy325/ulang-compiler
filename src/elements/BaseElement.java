abstract public class BaseElement {
    public abstract void accept (Visitor v);
    public abstract CompoundType accept (TypeVisitor v);
}