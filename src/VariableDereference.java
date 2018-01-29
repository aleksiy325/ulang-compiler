public class VariableDereference extends Atom{
	Identifier id;
		
	public VariableDereference (Identifier id){
		this.id = id;
	}
	
	public void accept (Visitor v) {
		v.visit(this);
	}
}