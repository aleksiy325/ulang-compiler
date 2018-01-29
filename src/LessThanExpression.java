import java.util.ArrayList;

public class LessThanExpression extends Expression{
	ArrayList<PlusMinusExpression> exprList;
	
	public LessThanExpression (PlusMinusExpression f) {
		exprList = new ArrayList<PlusMinusExpression>();
		exprList.add(f);
	}
	
	public void add (PlusMinusExpression f) {
		exprList.add(f);
	}
	
	public PlusMinusExpression get (int index) {
		return exprList.get(index);
	}
	
	public int size () {
		return exprList.size();
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}