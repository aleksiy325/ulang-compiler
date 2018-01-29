public class CompoundType extends BaseElement{
	Type type;
	IntegerConstant size;
	boolean isArray;
		
	public CompoundType (Type type) {
		isArray = false;
		this.type = type;
	}

	public void makeArray (IntegerConstant size) {
		this.isArray = true;
		this.size = size;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}