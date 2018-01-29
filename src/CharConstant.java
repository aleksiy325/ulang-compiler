public class CharConstant extends Constant{
	char val;
		
	public CharConstant (char val){
		this.val = val;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}