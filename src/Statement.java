public class Statement extends BaseElement {
	Expression expr;
	boolean isEmpty;

	public Statement(){
		this.isEmpty = true;
	}

	public Statement( Expression expr ){
		this.expr = expr;
		this.isEmpty = false;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}

}