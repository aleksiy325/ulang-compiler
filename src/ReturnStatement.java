public class ReturnStatement extends Statement {

	public ReturnStatement() {
		this.isEmpty = true;
	}

	public ReturnStatement( Expression expr ){
		this.expr = expr;
		this.isEmpty = false;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}