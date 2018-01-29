public class ArrayDerefrence extends Atom{
	Identifier id;
	Expression expr;
		
	public ArrayDerefrence (Identifier id, Expression expr){
		this.id = id;
		this.expr = expr;
	}
	
	public void accept (Visitor v) {
		v.visit(this);
	}
}