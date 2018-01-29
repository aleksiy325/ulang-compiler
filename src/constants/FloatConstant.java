public class FloatConstant extends Constant{
	Float val;
		
	public FloatConstant (Float val){
		this.val = val;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}