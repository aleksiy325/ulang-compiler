public class PlusMinusExpression extends Expression{
	Expression left;
	Expression right;
	boolean isAddition;
	
	public PlusMinusExpression (Expression left) {
		this.left = left;
	}

	public PlusMinusExpression (Expression left, Expression right, String operator) {
		this.left = left;
		this.right = right;
		this.isAddition = operator.equals("+");
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}