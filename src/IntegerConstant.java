public class IntegerConstant extends Constant{
	Integer val;
		
	public IntegerConstant (Integer val){
		this.val = val;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}