import java.util.ArrayList;

public class Program extends BaseElement{
	ArrayList<Function> statementList;
	
	public Program ()
	{
		statementList = new ArrayList<Function>();
	}
	
	public void add (Function f)
	{
		statementList.add(f);
	}
	
	public Function get (int index)
	{
		return statementList.get(index);
	}
	
	public int size ()
	{
		return statementList.size();
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}