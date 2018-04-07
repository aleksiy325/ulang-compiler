public abstract class Atom extends BaseElement {
    public abstract void accept (Visitor v);
    public abstract Type accept (TypeVisitor v);
    public abstract IRTemp accept (IRVisitor v);
    public abstract IRTemp accept (JVMVisitor v);
}