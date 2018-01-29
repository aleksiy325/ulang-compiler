public class StringConstant extends Constant{
	String val;
		
	public StringConstant (String val){
		this.val = val;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}