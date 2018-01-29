public class AssignmentStatement extends Statement {
	Identifier id;
	Expression size;
	Expression expr;
	boolean isArray;

	public AssignmentStatement(Identifier id, Expression expr) {
		this.id = id;
		this.expr = expr;
		this.isEmpty = false;
		this.isArray = false;
	}

	public AssignmentStatement(Identifier id, Expression size, Expression expr) {
		this.id = id;
		this.size = size;
		this.expr = expr;
		this.isEmpty = false;
		this.isArray = false;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}