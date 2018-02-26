abstract public class BaseElement {
    int line;
    int charPos;
    public abstract void accept (Visitor v);
    public abstract Type accept (TypeVisitor v);
}