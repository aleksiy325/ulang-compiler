public abstract class Expression extends Atom {
    public abstract void accept (Visitor v);
    public abstract Type accept (TypeVisitor v);
    public abstract IRTemp accept (IRVisitor v);
    public abstract IRTemp accept (JVMVisitor v);
}