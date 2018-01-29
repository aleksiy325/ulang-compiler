import java.util.ArrayList;

public class PlusMinusExpression extends Expression{
	ArrayList<MultiplicationExpression> mpexprList;
	ArrayList<Boolean> isAdditionList;
	
	public PlusMinusExpression (MultiplicationExpression f) {
		mpexprList = new ArrayList<MultiplicationExpression>();
	}
	
	public void add (MultiplicationExpression f, String operator) {
		mpexprList.add(f);
		isAdditionList.add(operator.equals("+"));
	}
	
	public MultiplicationExpression get (int index) {
		return mpexprList.get(index);
	}

	public boolean isAddition (int index) {
		return isAdditionList.get(index - 1);
	}
	
	public int size () {
		return mpexprList.size();
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}