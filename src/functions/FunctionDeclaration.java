public class FunctionDeclaration extends BaseElement{
	CompoundType type;
	Identifier id;
	FormalParameterList params;
		
	public FunctionDeclaration (CompoundType type, Identifier id, FormalParameterList params){
		this.type = type;
		this.id = id;
		this.params = params;
	}
	
	public void accept (Visitor v) {
		v.visit(this);
	}
}