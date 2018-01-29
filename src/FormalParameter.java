public class FormalParameter extends BaseElement{
	CompoundType type;
	Identifier id;
		
	public FormalParameter (CompoundType type, Identifier id){
		this.type = type;
		this.id = id;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}