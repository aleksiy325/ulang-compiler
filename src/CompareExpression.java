import java.util.ArrayList;

public class CompareExpression extends Expression{
	ArrayList<LessThanExpression> exprList;
	
	public CompareExpression (LessThanExpression f) {
		exprList = new ArrayList<LessThanExpression>();
		exprList.add(f);
		
	}
	
	public void add (LessThanExpression f) {
		exprList.add(f);
	}
	
	public LessThanExpression get (int index) {
		return exprList.get(index);
	}
	
	public int size () {
		return exprList.size();
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}