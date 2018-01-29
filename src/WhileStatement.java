public class WhileStatement extends Statement {
	Block block;

	public WhileStatement(Expression expr, Block block){
		this.expr = expr;
		this.block = block;
		this.isEmpty = false;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}