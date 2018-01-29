public class IfElseStatement extends Statement {
	Block ifblock;
	Block elseblock;
	boolean hasElse;

	public IfElseStatement(Expression expr, Block ifblock){
		this.expr = expr;
		this.ifblock = ifblock;
		this.isEmpty = false;
		this.hasElse = false;
	}

	public IfElseStatement(Expression expr, Block ifblock, Block elseblock){
		this.expr = expr;
		this.ifblock = ifblock;
		this.elseblock = elseblock;
		this.isEmpty = false;
		this.hasElse = true;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}