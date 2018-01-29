public class PrintStatement extends Statement {

	public PrintStatement( Expression expr ){
		this.expr = expr;
		this.isEmpty = false;
	}


	public void accept (Visitor v) {
		v.visit(this);
	}
}