public class Type extends BaseElement{
	String val;

	public Type (String val){
		this.val = val;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}