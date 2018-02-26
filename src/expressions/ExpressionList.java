import java.util.ArrayList;

public class ExpressionList extends BaseElement {
    ArrayList<Expression> expressionList;

    public ExpressionList () {
        expressionList = new ArrayList<Expression>();
    }

    public void add (Expression f) {
        expressionList.add(f);
    }

    public Expression get (int index) {
        return expressionList.get(index);
    }

    public int size () {
        return expressionList.size();
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public CompoundType accept (TypeVisitor v) {
        return v.visit(this);
    }
}