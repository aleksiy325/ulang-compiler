import java.util.ArrayList;

public class MultiplicationExpression extends Expression{
	ArrayList<Atom> atomList;
	
	public MultiplicationExpression (Atom  f) {
		atomList = new ArrayList<Atom>();
		atomList.add(f);
	}
	
	public void add (Atom f) {
		atomList.add(f);
	}
	
	public Atom get (int index) {
		return atomList.get(index);
	}

	public int size () {
		return atomList.size();
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}