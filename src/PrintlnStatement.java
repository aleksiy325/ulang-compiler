public class PrintlnStatement extends Statement {

	public PrintlnStatement( Expression expr ){
		this.expr = expr;
		this.isEmpty = false;
	}


	public void accept (Visitor v) {
		v.visit(this);
	}
}