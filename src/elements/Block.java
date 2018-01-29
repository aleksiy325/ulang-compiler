import java.util.ArrayList;

public class Block extends BaseElement{
	ArrayList<Statement> statementList;
	
	public Block () {
		statementList = new ArrayList<Statement>();
	}
	
	public void add (Statement stmt) {
		statementList.add(stmt);
	}
	
	public Statement get (int index) {
		return statementList.get(index);
	}
	
	public int size () {
		return statementList.size();
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}