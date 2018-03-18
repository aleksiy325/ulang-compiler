public abstract class Statement extends Element {
    public abstract IRTemp accept(IRVisitor v);
}