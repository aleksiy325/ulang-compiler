abstract public class Element extends BaseElement {
    public abstract void accept (Visitor v);
    public abstract Type accept (TypeVisitor v);
}