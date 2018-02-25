abstract public class BaseElement {
    public abstract void accept (Visitor v);
    public abstract Type accept (TypeVisitor v);
}