public abstract class Expression extends Atom{
	public abstract void accept (Visitor v);
}