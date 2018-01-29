public class VariableDerefrence extends Atom{
	Identifier id;
		
	public VariableDerefrence (Identifier id){
		this.id = id;
	}
	
	public void accept (Visitor v) {
		v.visit(this);
	}
}