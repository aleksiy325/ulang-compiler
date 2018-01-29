public class BooleanConstant extends Constant{
	Boolean val;
		
	public BooleanConstant (Boolean val){
		this.val = val;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}